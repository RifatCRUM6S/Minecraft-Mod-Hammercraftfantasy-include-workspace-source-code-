package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.tags.DamageTypeTags;
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
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.List;
import java.util.EnumSet;

public class EricEntity extends Monster {
    // 动画状态定义
    public final AnimationState animationState0 = new AnimationState(); // flying
    public final AnimationState animationState1 = new AnimationState(); // breathing
    public final AnimationState animationState2 = new AnimationState(); // spell_1
    public final AnimationState animationState3 = new AnimationState(); // spell_2
    public final AnimationState animationState4 = new AnimationState(); // dying

    // 数据同步 SynchedData
    private static final EntityDataAccessor<Integer> SPELL_ANIM_STATE = SynchedEntityData.defineId(EricEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_DYING = SynchedEntityData.defineId(EricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_BREATHING = SynchedEntityData.defineId(EricEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_STUNNED = SynchedEntityData.defineId(EricEntity.class, EntityDataSerializers.BOOLEAN);

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.BLUE, ServerBossEvent.BossBarOverlay.PROGRESS);

    // 护盾与受击避险控制
    private int shieldTimer = 0;
    private int fleeParticleTimer = 0; // 受伤后5秒内持续粒子特效计时器 (100 ticks)
    private boolean triggerHitFlee = false; // 受伤跑路触发标志

    // 死亡阶段控制
    private int dyingTimer = 0;

    // 吐息计时器
    private int breathTicks = 0;

    // 飞行状态与呆滞（疲劳）控制
    private int flightTimer = 0;
    private int stunnedTimer = 0;

    public EricEntity(EntityType<EricEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setPersistenceRequired();
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new FlyingPathNavigation(this, world);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SPELL_ANIM_STATE, 0);
        builder.define(IS_DYING, false);
        builder.define(IS_BREATHING, false);
        builder.define(IS_STUNNED, false);
    }

    public boolean isDyingState() {
        return this.entityData.get(IS_DYING);
    }

    public boolean isBreathingState() {
        return this.entityData.get(IS_BREATHING);
    }

    public boolean isStunnedState() {
        return this.entityData.get(IS_STUNNED);
    }

    public int getSpellAnimState() {
        return this.entityData.get(SPELL_ANIM_STATE);
    }

    public boolean consumeHitFleeTrigger() {
        if (this.triggerHitFlee) {
            this.triggerHitFlee = false;
            return true;
        }
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 24, true, false,
            target -> target != null && target.isAlive() 
                && !target.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs")))));
        this.goalSelector.addGoal(1, new EricSpellGoal(this));
        this.goalSelector.addGoal(2, new FloatGoal(this));
    }

    // 免疫火焰与闪电伤害
    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypeTags.IS_LIGHTNING)) {
            return true;
        }
        return super.isInvulnerableTo(source);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (isDyingState()) {
            return false;
        }
        // 免疫远程弹射物伤害
        if (this.shieldTimer > 0 && source.is(DamageTypeTags.IS_PROJECTILE)) {
            if (this.level().isClientSide()) {
                spawnShieldParticles();
            }
            return false;
        }
        boolean result = super.hurt(source, amount);
        if (result && !this.level().isClientSide()) {
            this.shieldTimer = 100;
            // 开启受击后持续 5 秒 (100 Ticks) 的粒子特效计时器
            this.fleeParticleTimer = 100;
            this.triggerHitFlee = true;
        }
        return result;
    }

    // 计算悬浮目标点 (距离目标 8 ~ 18 格，高 3 ~ 6 格)
    public Vec3 getNewHoverTarget(Vec3 targetPos) {
        double angle = this.random.nextDouble() * Math.PI * 2.0;
        double dist = 8.0 + this.random.nextDouble() * 10.0;
        double x = targetPos.x + Math.cos(angle) * dist;
        double z = targetPos.z + Math.sin(angle) * dist;
        double y = targetPos.y + 3.0 + this.random.nextDouble() * 3.0;
        return new Vec3(x, y, z);
    }

    @Override
    public void tick() {
        super.tick();

        // 护盾倒计时
        if (this.shieldTimer > 0) {
            this.shieldTimer--;
            if (this.level().isClientSide()) {
                spawnShieldParticles();
            }
        }

        // 受伤后持续 5 秒 (100 ticks) 的周围逃跑粒子
        if (this.fleeParticleTimer > 0) {
            this.fleeParticleTimer--;
            spawnContinuousFleeParticles();
        }

        if (!this.level().isClientSide()) {
            // 死亡判断
            if (!isDyingState() && this.getHealth() <= (this.getMaxHealth() / 3.0F)) {
                startDyingSequence();
            }
            if (isDyingState()) {
                this.getNavigation().stop();
                this.setDeltaMovement(Vec3.ZERO);
                this.dyingTimer++;
                if (this.dyingTimer >= 80) {
                    completeDyingAndSpawnNextPhase();
                    return;
                }
            }

            // 飞行与呆滞逻辑判断
            if (!isDyingState() && !isBreathingState()) {
                if (isStunnedState()) {
                    this.stunnedTimer--;
                    this.getNavigation().stop();
                    this.setDeltaMovement(Vec3.ZERO);
                    if (this.stunnedTimer <= 0) {
                        this.entityData.set(IS_STUNNED, false);
                        this.flightTimer = 0;
                    }
                } else {
                    if (this.getDeltaMovement().lengthSqr() > 0.005D) {
                        this.flightTimer++;
                        // 连续飞行 15 秒 (300 ticks) 后进入 5 秒呆滞
                        if (this.flightTimer >= 300) {
                            this.entityData.set(IS_STUNNED, true);
                            this.stunnedTimer = 100;
                            this.getNavigation().stop();
                            this.setDeltaMovement(Vec3.ZERO);
                        }
                    }
                }
            }
        }

        // 吐息攻击更新逻辑
        if (isBreathingState()) {
            this.breathTicks++;
            if (this.level().isClientSide()) {
                spawnFlameBreathParticles();
            } else {
                LivingEntity target = this.getTarget();
                if (target != null && target.isAlive()) {
                    this.getLookControl().setLookAt(target, 60.0F, 60.0F);
                    this.setYBodyRot(this.getYHeadRot());
                }
                if (this.breathTicks % 4 == 0) {
                    performFlameAreaDamage(24.0D, 10.0F);
                }
            }
        } else {
            this.breathTicks = 0;
        }

        // 客户端动画逻辑
        if (this.level().isClientSide()) {
            this.animationState0.animateWhen(!isStunnedState(), this.tickCount);
            this.animationState1.animateWhen(true, this.tickCount);
            this.animationState2.animateWhen(getSpellAnimState() == 1, this.tickCount);
            this.animationState3.animateWhen(getSpellAnimState() == 2, this.tickCount);
            this.animationState4.animateWhen(isDyingState(), this.tickCount);
        }
    }

    private void startDyingSequence() {
        this.entityData.set(IS_DYING, true);
        this.entityData.set(IS_BREATHING, false);
        this.entityData.set(IS_STUNNED, false);
        this.entityData.set(SPELL_ANIM_STATE, 0);
        this.dyingTimer = 0;
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:eric_dying")),
            SoundSource.HOSTILE, 1.5F, 1.0F);
    }

    private void completeDyingAndSpawnNextPhase() {
        if (this.level() instanceof ServerLevel serverLevel) {
            LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
            if (bolt != null) {
                bolt.moveTo(this.position());
                serverLevel.addFreshEntity(bolt);
            }
            Entity nextPhaseEntity = HammercraftfantasyModEntities.ERICSPAWN.get().create(serverLevel);
            if (nextPhaseEntity instanceof Mob mob) {
                mob.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                mob.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(mob.blockPosition()), MobSpawnType.CONVERSION, null);
                if (this.getTarget() != null) {
                    mob.setTarget(this.getTarget());
                }
                serverLevel.addFreshEntity(mob);
            }
        }
        this.discard();
    }

    private void spawnShieldParticles() {
        ParticleType<?> particle = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:tzeentchseye"));
        if (particle instanceof SimpleParticleType simpleType) {
            for (int i = 0; i < 3; i++) {
                double rx = this.getX() + (this.random.nextDouble() - 0.5) * 1.5;
                double ry = this.getY() + this.getEyeHeight() + (this.random.nextDouble() - 0.5) * 1.5;
                double rz = this.getZ() + (this.random.nextDouble() - 0.5) * 1.5;
                this.level().addParticle(simpleType, rx, ry, rz, 0, 0, 0);
            }
        }
    }

    // 受伤后5秒内持续触发的粒子特效
    private void spawnContinuousFleeParticles() {
        if (this.level() instanceof ServerLevel serverLevel) {
            ParticleType<?> particle = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:tzeentchseye"));
            if (particle instanceof SimpleParticleType simpleType) {
                for (int i = 0; i < 3; i++) {
                    double rx = this.getX() + (this.random.nextDouble() - 0.5) * 1.8;
                    double ry = this.getY() + (this.random.nextDouble()) * 2.0;
                    double rz = this.getZ() + (this.random.nextDouble() - 0.5) * 1.8;
                    serverLevel.sendParticles(simpleType, rx, ry, rz, 1, 0, 0.05, 0, 0.02);
                }
            }
        }
    }

    private Vec3 getForwardVector() {
        float f = this.getXRot() * ((float) Math.PI / 180F);
        float f1 = -this.getYHeadRot() * ((float) Math.PI / 180F);
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        float f4 = Mth.cos(f);
        float f5 = Mth.sin(f);
        return new Vec3((double) (f3 * f4), (double) (-f5), (double) (f2 * f4));
    }

    private void performFlameAreaDamage(double range, float damagePerHit) {
        Vec3 lookVec = getForwardVector();
        Vec3 eyePos = this.position().add(0, this.getEyeHeight(), 0);
        AABB searchBox = this.getBoundingBox().inflate(range);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, searchBox, entity -> 
            entity != this && entity.isAlive() 
                && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs")))
        );
        for (LivingEntity target : targets) {
            Vec3 toTarget = target.position().add(0, target.getBbHeight() / 2.0, 0).subtract(eyePos);
            double distance = toTarget.length();
            if (distance <= range) {
                double dot = lookVec.dot(toTarget.normalize());
                if (dot > 0.6D) {
                    DamageSource ds = this.damageSources().mobAttack(this);
                    Vec3 oldDelta = target.getDeltaMovement();
                    if (target.hurt(ds, damagePerHit)) {
                        target.setRemainingFireTicks(60);
                        target.setDeltaMovement(oldDelta.x * 0.3, target.getDeltaMovement().y, oldDelta.z * 0.3);
                    }
                }
            }
        }
    }

    private void spawnFlameBreathParticles() {
        Vec3 look = getForwardVector();
        double startX = this.getX() + look.x * 1.2;
        double startY = this.getY() + this.getEyeHeight() * 0.85 + look.y * 1.2;
        double startZ = this.getZ() + look.z * 1.2;
        ParticleType<?> pinkFlame = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:pinkfalme"));
        for (int i = 0; i < 10; i++) {
            double speedScale = 0.8 + this.random.nextDouble() * 0.6;
            double vx = look.x * speedScale + (this.random.nextDouble() - 0.5) * 0.15;
            double vy = look.y * speedScale + (this.random.nextDouble() - 0.5) * 0.15;
            double vz = look.z * speedScale + (this.random.nextDouble() - 0.5) * 0.15;
            if (i % 2 == 0 && pinkFlame instanceof SimpleParticleType simpleType) {
                this.level().addParticle(simpleType, startX, startY, startZ, vx, vy, vz);
            } else {
                this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, startX, startY, startZ, vx, vy, vz);
            }
        }
    }

    public void playRandomSpellSound() {
        String soundName = "hammercraftfantasy:eric_attack" + (1 + this.random.nextInt(3));
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(soundName)),
            SoundSource.HOSTILE, 1.2F, 1.0F);
    }

    public void triggerRandomSpellAnim() {
        this.entityData.set(SPELL_ANIM_STATE, 1 + this.random.nextInt(2));
    }

    public void clearSpellAnim() {
        this.entityData.set(SPELL_ANIM_STATE, 0);
    }

    public void setBreathing(boolean breathing) {
        this.entityData.set(IS_BREATHING, breathing);
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
    public SoundEvent getAmbientSound() {
        return isDyingState() ? null : BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:eric_breath"));
    }

    @Override
    public boolean causeFallDamage(float l, float d, DamageSource source) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

    @Override
    public void setNoGravity(boolean ignored) { super.setNoGravity(true); }

    @Override
    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void travel(Vec3 dir) {
        if (this.isDyingState() || this.isBreathingState() || this.isStunnedState()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }
        this.travelFlying(dir);
    }

    private void travelFlying(Vec3 dir) {
        if (this.isInWater()) {
            this.moveRelative(0.02F, dir);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
        } else {
            this.moveRelative((float) this.getAttributeValue(Attributes.FLYING_SPEED), dir);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.91));
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) { return false; }

    public static void init(RegisterSpawnPlacementsEvent event) {}

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
        builder = builder.add(Attributes.MAX_HEALTH, 200);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        builder = builder.add(Attributes.FLYING_SPEED, 0.22);
        return builder;
    }

    // =========================================================================
    //                            Eric 法术与飞行 AI 核心 Goal
    // =========================================================================
    private static class EricSpellGoal extends Goal {
        private final EricEntity eric;
        private Vec3 currentHoverTarget = null;
        private int stayTimer = 0;
        private int spellCastTimer = 0;
        private int activeSpellType = 0; // 1~6
        private Vec3 spellSnapshotPos = null;
        private int cooldown = 0;

        public EricSpellGoal(EricEntity eric) {
            this.eric = eric;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.eric.isDyingState() || this.eric.isStunnedState()) return false;
            LivingEntity target = this.eric.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void start() {
            LivingEntity target = this.eric.getTarget();
            if (target != null) {
                this.currentHoverTarget = this.eric.getNewHoverTarget(target.position());
            }
            this.stayTimer = 0;
            this.spellCastTimer = 0;
            this.activeSpellType = 0;
            this.cooldown = 60 + this.eric.getRandom().nextInt(61);
        }

        @Override
        public void stop() {
            this.eric.clearSpellAnim();
            this.eric.setBreathing(false);
            this.activeSpellType = 0;
            this.spellCastTimer = 0;
            this.stayTimer = 0;
        }

        @Override
        public void tick() {
            if (this.eric.isDyingState() || this.eric.isStunnedState()) {
                this.eric.getNavigation().stop();
                return;
            }
            LivingEntity target = this.eric.getTarget();
            if (target == null || !target.isAlive()) return;

            // 挨打检测：触发逃跑跑路
            if (this.eric.consumeHitFleeTrigger()) {
                this.activeSpellType = 0;
                this.spellCastTimer = 0;
                this.eric.clearSpellAnim();
                this.eric.setBreathing(false);
                this.currentHoverTarget = this.eric.getNewHoverTarget(target.position());
                this.stayTimer = 0;
                this.eric.getNavigation().moveTo(this.currentHoverTarget.x, this.currentHoverTarget.y, this.currentHoverTarget.z, 1.2D);
            }

            // 保持头部与身体朝向目标
            this.eric.getLookControl().setLookAt(target, 60.0F, 60.0F);

            // --- 正在释放法术阶段 ---
            if (this.activeSpellType > 0) {
                this.eric.getNavigation().stop();
                this.spellCastTimer++;
                performSpellLogic(target);
                return;
            }

            // --- 悬浮飞行点寻路与停留逻辑 ---
            if (this.currentHoverTarget == null) {
                this.currentHoverTarget = this.eric.getNewHoverTarget(target.position());
            }

            double distSqr = this.eric.position().distanceToSqr(this.currentHoverTarget);
            if (distSqr < 2.25D) {
                if (this.stayTimer <= 0) {
                    this.stayTimer = 40 + this.eric.getRandom().nextInt(41);
                    this.eric.getNavigation().stop();
                    this.eric.setDeltaMovement(Vec3.ZERO);
                } else {
                    this.stayTimer--;
                    this.eric.getNavigation().stop();
                    this.eric.setDeltaMovement(Vec3.ZERO);
                    if (this.stayTimer == 0) {
                        this.currentHoverTarget = this.eric.getNewHoverTarget(target.position());
                    }
                }
            } else {
                this.stayTimer = 0;
                this.eric.getNavigation().moveTo(this.currentHoverTarget.x, this.currentHoverTarget.y, this.currentHoverTarget.z, 1.0D);
            }

            // --- 施法冷却计算与选中率判断 ---
            if (this.cooldown > 0) {
                this.cooldown--;
            } else {
                int roll = this.eric.getRandom().nextInt(100);
                if (roll < 20) {
                    this.activeSpellType = 1;
                } else if (roll < 40) {
                    this.activeSpellType = 2;
                } else if (roll < 60) {
                    this.activeSpellType = 3;
                } else if (roll < 80) {
                    this.activeSpellType = 5;
                } else if (roll < 90) {
                    this.activeSpellType = 4;
                } else {
                    this.activeSpellType = 6;
                }
                this.spellCastTimer = 0;
                this.spellSnapshotPos = target.position();
                this.eric.playRandomSpellSound();
                this.eric.triggerRandomSpellAnim();
                if (this.activeSpellType == 4) {
                    this.eric.level().playSound(null, this.eric.getX(), this.eric.getY(), this.eric.getZ(),
                        SoundEvents.CREEPER_PRIMED, SoundSource.HOSTILE, 1.2F, 0.5F);
                } else if (this.activeSpellType == 5) {
                    this.eric.level().playSound(null, this.eric.getX(), this.eric.getY(), this.eric.getZ(),
                        SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.HOSTILE, 1.0F, 1.2F);
                }
            }
        }

        private void performSpellLogic(LivingEntity target) {
            Level world = this.eric.level();

            // 1 秒前摇阶段 (20 Ticks)
            if (this.spellCastTimer < 20) {
                if (world instanceof ServerLevel serverLevel) {
                    if (this.activeSpellType == 2) {
                        spawnServerParticle(serverLevel, "hammercraftfantasy:pinkfalme", 6, 1.2);
                    } else if (this.activeSpellType == 3) {
                        spawnServerParticle(serverLevel, "hammercraftfantasy:pinkfalme", 6, 1.2);
                        serverLevel.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, 
                            this.eric.getX(), this.eric.getEyeY(), this.eric.getZ(), 4, 0.5, 0.5, 0.5, 0.05);
                    } else if (this.activeSpellType == 6) {
                        serverLevel.sendParticles(ParticleTypes.ENCHANT, 
                            this.eric.getX(), this.eric.getY() + 1.0, this.eric.getZ(), 8, 0.8, 0.8, 0.8, 0.2);
                        if (this.spellSnapshotPos != null) {
                            serverLevel.sendParticles(ParticleTypes.ENCHANT, 
                                this.spellSnapshotPos.x, this.spellSnapshotPos.y + 1.0, this.spellSnapshotPos.z, 12, 1.5, 1.0, 1.5, 0.2);
                        }
                    }
                }
                return;
            }

            // 施法技能触发 (Tick >= 20)
            if (!world.isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) world;
                switch (this.activeSpellType) {
                    case 1: { // 地面召唤奸角兽
                        spawnSummonOnGround(serverLevel, "hammercraftfantasy:tzaangor", 2);
                        spawnSummonOnGround(serverLevel, "hammercraftfantasy:tzaangorhalberd", 2);
                        finishSpell();
                        break;
                    }
                    case 2: { // 弹射物箭矢
                        if (this.spellCastTimer == 20) {
                            serverLevel.playSound(null, this.eric.getX(), this.eric.getY(), this.eric.getZ(),
                                SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1.5F, 0.8F);
                            for (int i = 0; i < 5; i++) {
                                EntityType<?> arrowType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:pink_horror_arrow"));
                                Entity arrowEntity = arrowType.create(serverLevel);
                                if (arrowEntity instanceof AbstractArrow arrow) {
                                    arrow.moveTo(this.eric.getX(), this.eric.getEyeY(), this.eric.getZ(), this.eric.getYRot(), this.eric.getXRot());
                                    double dx = target.getX() - this.eric.getX() + (this.eric.getRandom().nextDouble() - 0.5) * 2.5;
                                    double dy = target.getY(0.5) - this.eric.getEyeY() + (this.eric.getRandom().nextDouble() - 0.5) * 1.5;
                                    double dz = target.getZ() - this.eric.getZ() + (this.eric.getRandom().nextDouble() - 0.5) * 2.5;
                                    arrow.shoot(dx, dy, dz, 1.5F, 12.0F);
                                    arrow.setOwner(this.eric);
                                    serverLevel.addFreshEntity(arrow);
                                }
                            }
                            finishSpell();
                        }
                        break;
                    }
                    case 3: { // 吐息
                        if (this.spellCastTimer == 20) {
                            this.eric.setBreathing(true);
                        }
                        if (this.spellCastTimer >= 60) {
                            this.eric.setBreathing(false);
                            finishSpell();
                        }
                        break;
                    }
                    case 4: { // 充能苦力怕
                        if (this.spellCastTimer == 20) {
                            Creeper creeper = EntityType.CREEPER.create(serverLevel);
                            if (creeper != null) {
                                BlockPos spawnPos = BlockPos.containing(this.spellSnapshotPos.x, this.spellSnapshotPos.y + 3.0, this.spellSnapshotPos.z);
                                creeper.moveTo(Vec3.atBottomCenterOf(spawnPos));
                                creeper.setTarget(target);
                                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
                                if (lightning != null) {
                                    lightning.moveTo(Vec3.atBottomCenterOf(spawnPos));
                                    lightning.setVisualOnly(true);
                                    serverLevel.addFreshEntity(lightning);
                                }
                                creeper.thunderHit(serverLevel, lightning);
                                serverLevel.addFreshEntity(creeper);
                            }
                            finishSpell();
                        }
                        break;
                    }
                    case 5: { // 3道雷电
                        if (this.spellCastTimer == 20) {
                            for (int i = 0; i < 3; i++) {
                                LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
                                if (bolt != null) {
                                    double ox = (this.eric.getRandom().nextDouble() - 0.5) * 2.0;
                                    double oz = (this.eric.getRandom().nextDouble() - 0.5) * 2.0;
                                    bolt.moveTo(this.spellSnapshotPos.x + ox, this.spellSnapshotPos.y, this.spellSnapshotPos.z + oz);
                                    serverLevel.addFreshEntity(bolt);
                                }
                            }
                            finishSpell();
                        }
                        break;
                    }
                    case 6: { // 围困书架
                        if (this.spellCastTimer == 20) {
                            BlockPos center = BlockPos.containing(this.spellSnapshotPos);
                            int radius = 2;
                            for (int y = 0; y < 3; y++) {
                                for (int x = -radius; x <= radius; x++) {
                                    for (int z = -radius; z <= radius; z++) {
                                        if (Math.abs(x) == radius || Math.abs(z) == radius) {
                                            BlockPos pos = center.offset(x, y, z);
                                            BlockState existingState = serverLevel.getBlockState(pos);
                                            if (!existingState.is(Blocks.BEDROCK) && !existingState.is(Blocks.BARRIER)) {
                                                if (!existingState.isAir()) {
                                                    serverLevel.destroyBlock(pos, false);
                                                }
                                                serverLevel.setBlockAndUpdate(pos, Blocks.BOOKSHELF.defaultBlockState());
                                                serverLevel.scheduleTick(pos, Blocks.BOOKSHELF, 200);
                                            }
                                        }
                                    }
                                }
                            }
                            finishSpell();
                        }
                        break;
                    }
                }
            }
        }

        private void spawnSummonOnGround(ServerLevel level, String entityRl, int count) {
            EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(entityRl));
            for (int i = 0; i < count; i++) {
                Entity entity = type.create(level);
                if (entity instanceof LivingEntity mob) {
                    double ox = (this.eric.getRandom().nextDouble() - 0.5) * 4.0;
                    double oz = (this.eric.getRandom().nextDouble() - 0.5) * 4.0;
                    BlockPos searchStart = BlockPos.containing(this.eric.getX() + ox, this.eric.getY(), this.eric.getZ() + oz);
                    BlockPos groundPos = searchStart;
                    while (groundPos.getY() > level.getMinBuildHeight() && level.getBlockState(groundPos.below()).isAir()) {
                        groundPos = groundPos.below();
                    }
                    mob.moveTo(groundPos.getX() + 0.5, groundPos.getY(), groundPos.getZ() + 0.5, this.eric.getYRot(), 0.0F);
                    mob.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0));
                    mob.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 0));
                    if (mob instanceof Mob m && this.eric.getTarget() != null) {
                        m.setTarget(this.eric.getTarget());
                    }
                    level.addFreshEntity(mob);
                }
            }
        }

        private void spawnServerParticle(ServerLevel level, String particleRl, int count, double spread) {
            ParticleType<?> particle = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse(particleRl));
            if (particle instanceof SimpleParticleType simpleType) {
                level.sendParticles(simpleType, 
                    this.eric.getX(), this.eric.getY() + 1.2, this.eric.getZ(), 
                    count, spread / 2.0, spread / 2.0, spread / 2.0, 0.05);
            }
        }

        private void finishSpell() {
            this.eric.clearSpellAnim();
            this.activeSpellType = 0;
            this.spellCastTimer = 0;
            this.cooldown = 60 + this.eric.getRandom().nextInt(61);
            if (this.eric.getTarget() != null) {
                this.currentHoverTarget = this.eric.getNewHoverTarget(this.eric.getTarget().position());
            }
        }
    }
}