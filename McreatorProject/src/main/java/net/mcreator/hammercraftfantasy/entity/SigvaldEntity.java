package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class SigvaldEntity extends Monster {
    public static final EntityDataAccessor<Boolean> IS_ANGRY = SynchedEntityData.defineId(SigvaldEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> ANIM_STATE = SynchedEntityData.defineId(SigvaldEntity.class, EntityDataSerializers.INT);

    // 客户端动画控制器
    public final AnimationState animationState1 = new AnimationState(); // attack_1
    public final AnimationState animationState2 = new AnimationState(); // attack_2
    public final AnimationState animationState3 = new AnimationState(); // attack_3
    public final AnimationState animationState4 = new AnimationState(); // attack_4
    public final AnimationState animationState5 = new AnimationState(); // angry 变身
    public final AnimationState animationState7 = new AnimationState(); // angry_attack_1
    public final AnimationState animationState8 = new AnimationState(); // angry_attack_2

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.PINK, ServerBossEvent.BossBarOverlay.PROGRESS);

    private int summonTimer = 0;
    private int invulnerableTicks = 0;
    private boolean hasTriggeredAngry = false;
    private int currentAttackType = 0;
    private int lastAnimState = 0;

    // 方块挖掘相关状态记录变量
    private BlockPos currentMiningPos = null;
    private int currentMiningTicks = 0;

    public SigvaldEntity(EntityType<SigvaldEntity> type, Level world) {
        super(type, world);
        xpReward = 100;
        setNoAi(false);
        setPersistenceRequired();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_ANGRY, false);
        builder.define(ANIM_STATE, 0);
    }

    public boolean isAngry() {
        return this.entityData.get(IS_ANGRY);
    }

    public void setAngry(boolean angry) {
        this.entityData.set(IS_ANGRY, angry);
    }

    public int getAnimState() {
        return this.entityData.get(ANIM_STATE);
    }

    public void setAnimState(int state) {
        this.entityData.set(ANIM_STATE, state);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // 动态索敌 Goal：支持报复攻击 + 动态寻址最近目标 + 防抽陀螺锁敌
        this.targetSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<LivingEntity>(
                this, LivingEntity.class, 24, true, false,
                target -> isTargetValid(target)
        ) {
            private int lockTicks = 0;
            private int rescanTicks = 0;

            @Override
            public boolean canUse() {
                LivingEntity lastHurtBy = this.mob.getLastHurtByMob();
                if (lastHurtBy != null && lastHurtBy.isAlive() && lockTicks <= 0 && isTargetValid(lastHurtBy)) {
                    this.target = lastHurtBy;
                    this.lockTicks = 60; // 受到攻击强行锁敌 3 秒
                    return true;
                }
                return super.canUse();
            }

            @Override
            public void start() {
                super.start();
                if (this.lockTicks <= 0) {
                    this.lockTicks = 40;
                }
            }

            @Override
            public void tick() {
                super.tick();
                if (this.lockTicks > 0) {
                    this.lockTicks--;
                }

                this.rescanTicks++;
                if (this.rescanTicks >= 40) { // 每 2 秒重扫描一次
                    this.rescanTicks = 0;
                    if (this.lockTicks > 0) return;

                    LivingEntity currentTarget = this.mob.getTarget();
                    if (currentTarget == null || !currentTarget.isAlive()) return;

                    double currentDistSqr = this.mob.distanceToSqr(currentTarget);
                    LivingEntity closest = this.mob.level().getEntitiesOfClass(
                        LivingEntity.class,
                        this.mob.getBoundingBox().inflate(24.0),
                        e -> isTargetValid(e)
                    ).stream().min(java.util.Comparator.comparingDouble(e -> this.mob.distanceToSqr(e))).orElse(null);

                    // 仅当新目标比当前目标近 3 格以上时才切换，防止频繁切目标
                    if (closest != null && closest != currentTarget && this.mob.distanceToSqr(closest) < currentDistSqr - 9.0) {
                        this.mob.setTarget(closest);
                        this.target = closest;
                        this.lockTicks = 40;
                    }
                }
            }
        });

        this.goalSelector.addGoal(1, new SigvaldCustomAttackGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
    }

    private static boolean isTargetValid(LivingEntity e) {
        return e != null
            && e.isAlive()
            && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:slaanesh_mobs")));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SpawnGroupData retval = super.finalizeSpawn(world, difficulty, spawnType, spawnGroupData);
        if (world instanceof ServerLevel serverLevel) {
            checkAndSummonGuardians(serverLevel, 4, 4);
        }
        return retval;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        // 彻底免疫摔落伤害
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        // 客户端动画重置与播报同步
        if (this.level().isClientSide()) {
            int state = getAnimState();
            if (state != this.lastAnimState) {
                this.animationState1.stop();
                this.animationState2.stop();
                this.animationState3.stop();
                this.animationState4.stop();
                this.animationState5.stop();
                this.animationState7.stop();
                this.animationState8.stop();

                if (state == 1) this.animationState1.start(this.tickCount);
                if (state == 2) this.animationState2.start(this.tickCount);
                if (state == 3) this.animationState3.start(this.tickCount);
                if (state == 4) this.animationState4.start(this.tickCount);
                if (state == 5) this.animationState5.start(this.tickCount);
                if (state == 6) this.animationState7.start(this.tickCount);
                if (state == 7) this.animationState8.start(this.tickCount);

                this.lastAnimState = state;
            }
        } else {
            // 服务端逻辑：基于方块硬度按 Tick 逐步破坏阻挡方块
            updateBlockMiningLogic();

            summonTimer++;
            // 修改 3：召唤小弟间隔由 600 Ticks(30s) 缩短至 300 Ticks(15s)
            if (summonTimer >= 400) {
                summonTimer = 0;
                if (this.level() instanceof ServerLevel serverLevel) {
                    checkAndSummonGuardians(serverLevel, 6, 1);
                }
            }

            if (invulnerableTicks > 0) {
                invulnerableTicks--;
                this.getNavigation().stop();
                this.setDeltaMovement(0, this.getDeltaMovement().y, 0);

                if (invulnerableTicks == 0) {
                    setAnimState(0);
                    this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 1, false, false));
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 1, false, false));
                }
            }
        }
    }

    /**
     * 基于方块硬度的精准 Tick 挖掘逻辑（硬度值等于需要消耗的 Tick 数，最高破坏到黑曜石）
     */
    private void updateBlockMiningLogic() {
        if (this.getTarget() == null || !this.getTarget().isAlive()) {
            resetMiningProgress();
            return;
        }

        BlockPos targetPos = findBlockToMine();

        if (targetPos == null) {
            resetMiningProgress();
            return;
        }

        if (!targetPos.equals(this.currentMiningPos)) {
            resetMiningProgress();
            this.currentMiningPos = targetPos;
        }

        BlockState state = this.level().getBlockState(targetPos);
        float hardness = state.getDestroySpeed(this.level(), targetPos);

        // 无法破坏基岩(hardness < 0) 或 硬度超过黑曜石(hardness > 50.0F) 的方块
        if (hardness < 0.0F || hardness > 50.0F) {
            resetMiningProgress();
            return;
        }

        // 所需 Tick 数直接等于方块硬度（向上取整，至少为 1 Tick）
        int requiredTicks = Math.max(1, (int) Math.ceil(hardness));

        this.currentMiningTicks++;

        // 广播客户端破裂纹理（0 ~ 9 级纹理）
        float progressRatio = (float) this.currentMiningTicks / (float) requiredTicks;
        int stage = Math.min(9, (int) (progressRatio * 10.0F));
        this.level().destroyBlockProgress(this.getId(), this.currentMiningPos, stage);

        // 达到对应 Tick 数后破方块
        if (this.currentMiningTicks >= requiredTicks) {
            this.level().destroyBlock(this.currentMiningPos, true, this);
            resetMiningProgress();
        }
    }

    /**
     * 智能探测面前挡路或上方阻碍的方块
     */
    private BlockPos findBlockToMine() {
        BlockPos mobPos = this.blockPosition();
        BlockPos frontPos = mobPos.relative(this.getDirection());

        // 优先检查：1. 面前脚部 2. 面前头部 3. 正上方(防止顶板卡住)
        BlockPos[] checkPositions = new BlockPos[]{
            frontPos,
            frontPos.above(),
            mobPos.above(2)
        };

        for (BlockPos pos : checkPositions) {
            BlockState state = this.level().getBlockState(pos);
            if (!state.isAir()) {
                float hardness = state.getDestroySpeed(this.level(), pos);
                if (hardness >= 0.0F && hardness <= 50.0F) {
                    return pos;
                }
            }
        }
        return null;
    }

    /**
     * 重置挖掘状态并清除客户端破裂效果
     */
    private void resetMiningProgress() {
        if (this.currentMiningPos != null) {
            this.level().destroyBlockProgress(this.getId(), this.currentMiningPos, -1);
            this.currentMiningPos = null;
        }
        this.currentMiningTicks = 0;
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    private void checkAndSummonGuardians(ServerLevel level, int targetThreshold, int maxToSpawn) {
        List<Entity> guards = level.getEntities(this, this.getBoundingBox().inflate(16.0D), 
            e -> e.getType().equals(BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:silverguard")))
        );

        if (guards.size() < targetThreshold) {
            int needed = Math.min(targetThreshold - guards.size(), maxToSpawn);
            EntityType<?> guardType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:silverguard"));
            if (guardType != null) {
                for (int i = 0; i < needed; i++) {
                    guardType.spawn(level, this.blockPosition().offset(this.random.nextInt(3) - 1, 0, this.random.nextInt(3) - 1), MobSpawnType.MOB_SUMMONED);
                }
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        // 免疫弹射物（远程攻击）伤害
        if (source.is(DamageTypeTags.IS_PROJECTILE)) {
            return false;
        }

        if (this.invulnerableTicks > 0) {
            return false;
        }

        boolean result = super.hurt(source, amount);

        if (result && !hasTriggeredAngry && this.getHealth() <= (this.getMaxHealth() / 3.0F)) {
            hasTriggeredAngry = true;
            invulnerableTicks = 100; // 5秒变身无敌
            this.currentAttackType = 0;
            setAngry(true);         // 设为 Angry
            setAnimState(5);        // 播放变身动画
        }

        return result;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return this.invulnerableTicks > 0 || super.isInvulnerableTo(source);
    }

    public ResourceLocation getTextureLocation() {
        if (isAngry()) {
            return ResourceLocation.parse("hammercraftfantasy:textures/entities/sigvard_angry_skin.png");
        }
        return ResourceLocation.parse("hammercraftfantasy:textures/entities/sigvard_skin.png");
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:sigvard_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.mace.smash_ground_heavy"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:sigvard_dead"));
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 200);
        builder = builder.add(Attributes.ARMOR, 24);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 30);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 0.5);
        return builder;
    }

    /**
     * 带高低差无视寻路卡死与前摇匹配跳跃的 CustomAttackGoal
     */
    private static class SigvaldCustomAttackGoal extends Goal {
        private final SigvaldEntity mob;
        private final double speed;
        private int attackAnimationTicks = 0;
        private int totalAnimationDuration = 0;
        private int damageHitTick = -1;
        private boolean hasDealtDamage = false;
        private boolean hasJumpedThisAttack = false;
        private LivingEntity target;

        public SigvaldCustomAttackGoal(SigvaldEntity mob, double speed) {
            this.mob = mob;
            this.speed = speed;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity target = this.mob.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void start() {
            this.target = this.mob.getTarget();
            this.attackAnimationTicks = 0;
            this.hasDealtDamage = false;
            this.hasJumpedThisAttack = false;
        }

        @Override
        public void stop() {
            this.target = null;
            this.mob.currentAttackType = 0;
            if (this.mob.invulnerableTicks <= 0) {
                this.mob.setAnimState(0);
            }
            this.attackAnimationTicks = 0;
            this.hasDealtDamage = false;
            this.hasJumpedThisAttack = false;
        }

        @Override
        public void tick() {
            if (this.mob.invulnerableTicks > 0) {
                this.mob.getNavigation().stop();
                return;
            }

            this.target = this.mob.getTarget();
            if (this.target == null || !this.target.isAlive()) return;

            this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

            // 修改 1：以脚下 BlockPos 方块的高度差为精准基准，范围扩展至 1 ~ 5 格高
            int blockYDiff = this.target.blockPosition().getY() - this.mob.blockPosition().getY();
            boolean isTargetAbove = blockYDiff >= 1 && blockYDiff <= 5;

            double attackReachSqr = (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + this.target.getBbWidth());
            double distSqr = this.mob.distanceToSqr(this.target);

            // 目标在上方时，大幅度扩张起手水平判定（水平 5 格内均可起手触发跃击）
            if (isTargetAbove) {
                attackReachSqr += 25.0D;
            }

            if (this.mob.currentAttackType == 0) {
                if (distSqr <= attackReachSqr) {
                    chooseAndStartAttack();
                } else {
                    // 如果目标在上方导致导航断路，使用 MoveControl 强行推送至玩家下方
                    if (isTargetAbove) {
                        this.mob.getMoveControl().setWantedPosition(this.target.getX(), this.mob.getY(), this.target.getZ(), this.speed);
                    } else {
                        this.mob.getNavigation().moveTo(this.target, this.speed);
                    }
                }
            } else {
                if (this.mob.onGround()) {
                    this.mob.getNavigation().stop();
                }

                this.attackAnimationTicks++;

                // 精确在伤害判定前 4 Tick 起跳
                int jumpTriggerTick = Math.max(1, this.damageHitTick - 4);
                if (this.attackAnimationTicks == jumpTriggerTick && !this.hasJumpedThisAttack) {
                    tryJumpToTarget(blockYDiff);
                }

                if (this.attackAnimationTicks >= this.damageHitTick && !this.hasDealtDamage) {
                    executeAttackEffect();
                    this.hasDealtDamage = true;
                }

                if (this.attackAnimationTicks >= this.totalAnimationDuration) {
                    this.mob.currentAttackType = 0;
                    this.mob.setAnimState(0);
                    this.attackAnimationTicks = 0;
                    this.hasDealtDamage = false;
                    this.hasJumpedThisAttack = false;
                }
            }
        }

        private void tryJumpToTarget(int blockYDiff) {
            if (this.target != null && this.target.isAlive()) {
                // 修改 1：跳跃触发高度差扩展至 1 ~ 5 格
                if (blockYDiff >= 1 && blockYDiff <= 5) {
                    this.hasJumpedThisAttack = true;

                    double dx = this.target.getX() - this.mob.getX();
                    double dz = this.target.getZ() - this.mob.getZ();
                    double distSq = dx * dx + dz * dz;
                    double len = Math.max(0.1D, Math.sqrt(distSq));

                    // 根据垫高高度动态赋予向上初速度，支持最高跃上 5 格平台
                    double jumpY;
                    if (blockYDiff <= 2) {
                        jumpY = 0.76D;
                    } else if (blockYDiff <= 3) {
                        jumpY = 0.88D;
                    } else if (blockYDiff <= 4) {
                        jumpY = 0.98D;
                    } else {
                        jumpY = 1.08D; // 5 格高平台跳跃力
                    }

                    double pushX = (dx / len) * 0.45D;
                    double pushZ = (dz / len) * 0.45D;

                    this.mob.setDeltaMovement(pushX, jumpY, pushZ);
                    this.mob.hasImpulse = true;
                }
            }
        }

        private void chooseAndStartAttack() {
            this.attackAnimationTicks = 0;
            this.hasDealtDamage = false;
            this.hasJumpedThisAttack = false;
            float roll = this.mob.random.nextFloat() * 100.0F;

            if (!this.mob.isAngry()) {
                if (roll < 35.0F) {
                    this.mob.currentAttackType = 1;
                    this.mob.setAnimState(1);
                    this.totalAnimationDuration = 35; 
                    this.damageHitTick = 10;          
                } else if (roll < 55.0F) {
                    this.mob.currentAttackType = 2;
                    this.mob.setAnimState(2);
                    this.totalAnimationDuration = 20; 
                    this.damageHitTick = 7;           
                } else if (roll < 90.0F) {
                    this.mob.currentAttackType = 3;
                    this.mob.setAnimState(3);
                    this.totalAnimationDuration = 25; 
                    this.damageHitTick = 8;           
                } else {
                    this.mob.currentAttackType = 4;
                    this.mob.setAnimState(4);
                    this.totalAnimationDuration = 25; 
                    this.damageHitTick = 8;           
                }
            } else {
                if (roll < 70.0F) {
                    this.mob.currentAttackType = 5;
                    this.mob.setAnimState(6);
                    this.totalAnimationDuration = 10; 
                    this.damageHitTick = 2;           
                } else {
                    this.mob.currentAttackType = 6;
                    this.mob.setAnimState(7);
                    this.totalAnimationDuration = 10; 
                    this.damageHitTick = 4;           
                }
            }
        }

        private void executeAttackEffect() {
            if (this.target == null || !this.target.isAlive()) return;

            float baseDamage = (float) this.mob.getAttributeValue(Attributes.ATTACK_DAMAGE);

            switch (this.mob.currentAttackType) {
                case 1:
                    doSweepAttack(baseDamage);
                    break;
                case 2:
                    if (isTargetInVerticalAttackRange()) {
                        this.target.hurt(this.mob.damageSources().mobAttack(this.mob), baseDamage * 0.5F);
                        applyCustomKnockback(this.target, 2.5F);
                    }
                    break;
                case 3:
                    doSweepAttack(baseDamage);
                    break;
                case 4:
                    if (isTargetInVerticalAttackRange()) {
                        this.target.hurt(this.mob.damageSources().mobAttack(this.mob), baseDamage * 2.0F);
                        applyCustomKnockback(this.target, 1.25F);
                    }
                    break;
                case 5:
                    if (isTargetInVerticalAttackRange()) {
                        this.mob.doHurtTarget(this.target);
                    }
                    break;
                case 6:
                    if (isTargetInVerticalAttackRange()) {
                        this.target.hurt(this.mob.damageSources().mobAttack(this.mob), baseDamage * 0.5F);
                        applyCustomKnockback(this.target, 4.0F);
                    }
                    break;
            }
        }

        private boolean isTargetInVerticalAttackRange() {
            double distSqr = this.mob.distanceToSqr(this.target);
            double maxDistSqr = this.mob.getBbWidth() * 3.0F * this.mob.getBbWidth() * 3.0F;
            if (this.hasJumpedThisAttack) {
                maxDistSqr += 36.0D; // 跳跃攻击扩大垂直判定容错（支持 5 格高攻击）
            }
            return distSqr <= maxDistSqr;
        }

        private void doSweepAttack(float damage) {
            this.mob.level().playSound(null, this.mob.getX(), this.mob.getY(), this.mob.getZ(), 
                SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.0F, 1.0F);

            double inflateY = this.hasJumpedThisAttack ? 6.5D : 3.5D;
            List<LivingEntity> targets = this.mob.level().getEntitiesOfClass(LivingEntity.class, 
                this.mob.getBoundingBox().inflate(3.5D, inflateY, 3.5D), 
                e -> e != this.mob && isTargetValid(e)
            );

            for (LivingEntity e : targets) {
                e.hurt(this.mob.damageSources().mobAttack(this.mob), damage);
            }
        }

        private void applyCustomKnockback(LivingEntity entity, float strength) {
            double d0 = entity.getX() - this.mob.getX();
            double d1 = entity.getZ() - this.mob.getZ();
            double len = Math.max(0.1D, Math.sqrt(d0 * d0 + d1 * d1));
            entity.knockback(strength, -d0 / len, -d1 / len);
        }
    }
}