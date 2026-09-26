package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;

import java.util.Comparator;
import java.util.List;

public class EricspawnEntity extends Monster {
    public final AnimationState animationState0 = new AnimationState(); // twisting
    public final AnimationState animationState1 = new AnimationState(); // breathing
    public final AnimationState animationState3 = new AnimationState(); // attack_1
    public final AnimationState animationState4 = new AnimationState(); // attack_2

    private static final EntityDataAccessor<Integer> ANIMATION_STATE = 
        SynchedEntityData.defineId(EricspawnEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.PROGRESS);

    private int attackAnimationTimer = 0;
    private int currentAttackMaxTicks = 0;
    private float lockedYRot = 0.0F;

    // 能力计时器 (20 Ticks = 1 秒, 100~200 Ticks = 5~10 秒)
    private int lightningTimer = 100 + this.random.nextInt(101);
    private int fangsTimer = 100 + this.random.nextInt(101);

    public EricspawnEntity(EntityType<EricspawnEntity> type, Level world) {
        super(type, world);
        xpReward = 100;
        setNoAi(false);
        setPersistenceRequired();
    }

    public boolean isAttacking() {
        return this.attackAnimationTimer > 0;
    }

    private double getAttackReachSqr(LivingEntity enemy) {
        double attackReach = (this.getBbWidth() + enemy.getBbWidth()) / 2.0 + 3.0;
        return attackReach * attackReach;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANIMATION_STATE, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>(
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
                    this.lockTicks = 60;
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
                if (this.rescanTicks >= 40) {
                    this.rescanTicks = 0;
                    if (this.lockTicks > 0) return;

                    LivingEntity currentTarget = this.mob.getTarget();
                    if (currentTarget == null || !currentTarget.isAlive()) return;

                    double currentDistSqr = this.mob.distanceToSqr(currentTarget);
                    LivingEntity closest = this.mob.level().getEntitiesOfClass(
                        LivingEntity.class,
                        this.mob.getBoundingBox().inflate(24.0),
                        e -> isTargetValid(e)
                    ).stream().min(Comparator.comparingDouble(e -> this.mob.distanceToSqr(e))).orElse(null);

                    if (closest != null && closest != currentTarget && this.mob.distanceToSqr(closest) < currentDistSqr - 9.0) {
                        this.mob.setTarget(closest);
                        this.target = closest;
                        this.lockTicks = 40;
                    }
                }
            }
        });

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true) {
            @Override
            protected void checkAndPerformAttack(LivingEntity enemy) {
                if (this.canPerformAttack(enemy) && !EricspawnEntity.this.isAttacking()) {
                    this.resetAttackCooldown();
                    ((EricspawnEntity) this.mob).startDelayedAttack();
                }
            }

            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() 
                    && this.mob.distanceToSqr(entity) <= ((EricspawnEntity) this.mob).getAttackReachSqr(entity) 
                    && this.mob.getSensing().hasLineOfSight(entity);
            }
        });

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0) {
            @Override
            public boolean canUse() {
                return !EricspawnEntity.this.isAttacking() && super.canUse();
            }
        });

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
    }

    private boolean isTargetValid(LivingEntity target) {
        return target != null 
            && target.isAlive() 
            && !target.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs")));
    }

    public void startDelayedAttack() {
        if (this.level().isClientSide() || this.isAttacking()) return;

        LivingEntity target = this.getTarget();
        if (target != null) {
            double d0 = target.getX() - this.getX();
            double d1 = target.getZ() - this.getZ();
            this.lockedYRot = (float) (Mth.atan2(d1, d0) * (180.0D / Math.PI)) - 90.0F;
            this.setYRot(this.lockedYRot);
            this.setYHeadRot(this.lockedYRot);
            this.setYBodyRot(this.lockedYRot);
        } else {
            this.lockedYRot = this.getYRot();
        }

        if (this.random.nextBoolean()) {
            this.currentAttackMaxTicks = 20;
            this.attackAnimationTimer = 20;
            this.entityData.set(ANIMATION_STATE, 1);
        } else {
            this.currentAttackMaxTicks = 20;
            this.attackAnimationTimer = 20;
            this.entityData.set(ANIMATION_STATE, 2);
        }

        this.getNavigation().stop();
        this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
    }

    private void dealShieldBypassingDamage(LivingEntity target, float damageAmount) {
        if (target instanceof Player player && player.isBlocking()) {
            player.disableShield();
        }
        
        DamageSource bypassSource = this.damageSources().mobAttack(this);
        target.hurt(bypassSource, damageAmount);
    }

    /**
     * 破坏指定 AABB 范围内的方块（强制避开实体脚下地面）
     */
    private void destroyBlocksInAABB(AABB area) {
        if (this.level().isClientSide() || !this.level().getGameRules().getBoolean(net.minecraft.world.level.GameRules.RULE_MOBGRIEFING)) {
            return;
        }

        int feetY = this.blockPosition().getY();

        int minX = Mth.floor(area.minX);
        int minY = Math.max(feetY + 1, Mth.floor(area.minY)); // 确保最低只破坏脚底线以上 1 格的方块，绝不破坏脚踩的地面
        int minZ = Mth.floor(area.minZ);
        int maxX = Mth.floor(area.maxX);
        int maxY = Mth.floor(area.maxY);
        int maxZ = Mth.floor(area.maxZ);

        boolean brokeAnyBlock = false;

        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState state = this.level().getBlockState(pos);

                    if (!state.isAir() && canDestroyBlock(state, pos)) {
                        brokeAnyBlock = this.level().destroyBlock(pos, true, this) || brokeAnyBlock;
                    }
                }
            }
        }

        if (brokeAnyBlock) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.WITHER_BREAK_BLOCK, SoundSource.HOSTILE, 1.0F, 1.0F);
        }
    }

    /**
     * 判断方块是否可破坏（排除基岩、末影龙/凋零免伤方块等）
     */
    private boolean canDestroyBlock(BlockState state, BlockPos pos) {
        return state.getDestroySpeed(this.level(), pos) >= 0 
            && !state.is(net.minecraft.tags.BlockTags.WITHER_IMMUNE);
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            // --- 常态平地巡逻/移动时破坏挡路的方块（水平方向拓宽，但高度仅限脚踏面以上） ---
            if (this.tickCount % 8 == 0) {
                AABB moveObstacleBox = this.getBoundingBox().inflate(0.5, 0.0, 0.5);
                destroyBlocksInAABB(moveObstacleBox);
            }

            // --- 技能 1: 5-10秒 随机召唤落雷 ---
            if (--this.lightningTimer <= 0) {
                this.lightningTimer = 100 + this.random.nextInt(101);
                int count = 1 + this.random.nextInt(2);

                for (int i = 0; i < count; i++) {
                    double angle = this.random.nextDouble() * Math.PI * 2.0;
                    double dist = 4.0 + this.random.nextDouble() * 4.0;
                    double strikeX = this.getX() + Math.cos(angle) * dist;
                    double strikeZ = this.getZ() + Math.sin(angle) * dist;
                    double strikeY = this.getY();

                    BlockPos pos = BlockPos.containing(strikeX, strikeY, strikeZ);
                    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(this.level());
                    if (lightning != null) {
                        lightning.moveTo(Vec3.atBottomCenterOf(pos));
                        this.level().addFreshEntity(lightning);
                    }
                }
            }

            // --- 技能 2: 5-10秒 地刺魔法 ---
            if (--this.fangsTimer <= 0) {
                this.fangsTimer = 100 + this.random.nextInt(101);

                for (int dir = 0; dir < 2; dir++) {
                    float angleDegree = this.random.nextFloat() * 360.0F;
                    double rad = Math.toRadians(angleDegree);
                    double dirX = Math.cos(rad);
                    double dirZ = Math.sin(rad);

                    for (int i = 1; i <= 5; i++) {
                        double fangX = this.getX() + dirX * i * 1.2;
                        double fangZ = this.getZ() + dirZ * i * 1.2;
                        double fangY = this.getY();

                        EvokerFangs fangs = new EvokerFangs(this.level(), fangX, fangY, fangZ, (float) rad, i * 2, this);
                        this.level().addFreshEntity(fangs);
                    }
                }
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.EVOKER_FANGS_ATTACK, SoundSource.HOSTILE, 1.0F, 1.0F);
            }

            // --- 近战攻击逻辑 ---
            if (this.isAttacking()) {
                this.getNavigation().stop();
                this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
                this.setYRot(this.lockedYRot);
                this.setYHeadRot(this.lockedYRot);
                this.setYBodyRot(this.lockedYRot);

                int elapsedTicks = this.currentAttackMaxTicks - this.attackAnimationTimer;
                int state = this.entityData.get(ANIMATION_STATE);

                if (state == 1) {
                    if (elapsedTicks == 10) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:havy_foot_steps")),
                            SoundSource.HOSTILE, 1.2F, 1.0F);
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.GENERIC_EXPLODE,
                            SoundSource.HOSTILE, 1.0F, 0.9F);

                        if (this.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 0.5, this.getZ(), 3, 1.0, 0.2, 1.0, 0.0);
                            serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY() + 0.1, this.getZ(), 20, 2.0, 0.1, 2.0, 0.05);
                        }

                        AABB earthSlamAOE = new AABB(
                            this.getX() - 4.5, this.getY() - 1.5, this.getZ() - 4.5,
                            this.getX() + 4.5, this.getY() + 2.5, this.getZ() + 4.5
                        );

                        // 践踏攻击时只粉碎腰部/头部高度挡路的方块
                        destroyBlocksInAABB(earthSlamAOE);

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, earthSlamAOE,
                            e -> e != this && isTargetValid(e));

                        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                        for (LivingEntity target : targets) {
                            dealShieldBypassingDamage(target, baseDamage * 1.2F);
                        }
                        triggerScreenShake(12.0D, 0.12D);
                    }
                } 
                else if (state == 2) {
                    if (elapsedTicks == 13) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:havy_foot_steps")),
                            SoundSource.HOSTILE, 1.2F, 1.0F);

                        Vec3 look = this.getLookAngle();
                        Vec3 slamCenter = this.position().add(look.scale(1.5));
                        AABB frontSlamAOE = new AABB(
                            slamCenter.x - 2.5, slamCenter.y - 1.5, slamCenter.z - 2.5,
                            slamCenter.x + 2.5, slamCenter.y + 2.5, slamCenter.z + 2.5
                        );

                        // 正面重击时破坏前方方块
                        destroyBlocksInAABB(frontSlamAOE);

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, frontSlamAOE,
                            e -> e != this && isTargetValid(e));

                        float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                        for (LivingEntity target : targets) {
                            dealShieldBypassingDamage(target, baseDamage);
                        }
                        triggerScreenShake(8.0D, 0.08D);
                    }
                }

                this.attackAnimationTimer--;
                if (this.attackAnimationTimer == 0) {
                    this.entityData.set(ANIMATION_STATE, 0);
                }
            }
        } else {
            int state = this.entityData.get(ANIMATION_STATE);
            this.animationState0.animateWhen(true, this.tickCount);
            this.animationState1.animateWhen(true, this.tickCount);

            this.animationState3.animateWhen(state == 1, this.tickCount);
            this.animationState4.animateWhen(state == 2, this.tickCount);
        }
    }

    private void triggerScreenShake(double radius, double intensity) {
        for (ServerPlayer player : this.level().getEntitiesOfClass(ServerPlayer.class, this.getBoundingBox().inflate(radius))) {
            player.hurtMarked = true;
            player.setDeltaMovement(player.getDeltaMovement().add(
                (this.random.nextDouble() - 0.5) * intensity,
                intensity * 0.8,
                (this.random.nextDouble() - 0.5) * intensity
            ));
        }
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        if (damagesource.is(DamageTypes.IN_FIRE))
            return false;
        if (damagesource.is(DamageTypes.LIGHTNING_BOLT))
            return false;
        if (damagesource.is(DamageTypes.EXPLOSION) || damagesource.is(DamageTypes.PLAYER_EXPLOSION))
            return false;

        boolean result = super.hurt(damagesource, amount);

        // 受击时清理周围挡路的方块 (安全高度：Y > feetY)
        if (!this.level().isClientSide() && result) {
            destroyBlocksInAABB(this.getBoundingBox().inflate(1.0, 0.5, 1.0));
        }

        return result;
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

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:espawn_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:espawn_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:espawn_dead"));
    }

    @Override
    public boolean ignoreExplosion(Explosion explosion) {
        return true;
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
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35);
        builder = builder.add(Attributes.MAX_HEALTH, 500);
        builder = builder.add(Attributes.ARMOR, 4);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 28);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 3);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        return builder;
    }
}