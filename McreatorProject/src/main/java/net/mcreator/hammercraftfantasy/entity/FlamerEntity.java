package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.Difficulty;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FlamerEntity extends Monster implements RangedAttackMob {
    public final AnimationState animationState0 = new AnimationState(); // 待机/移动
    public final AnimationState animationState2 = new AnimationState(); // 喷火攻击动画

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(FlamerEntity.class, EntityDataSerializers.BOOLEAN);
    private int attackTicks = 0;

    public FlamerEntity(EntityType<FlamerEntity> type, Level world) {
        super(type, world);
        xpReward = 20;
        setNoAi(false);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // 动态索敌 Goal：防抽陀螺锁敌 + 奸奇 (Tzeentch) 阵营过滤
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>(
                this, LivingEntity.class, 10, true, false,
                target -> isTargetValid(target)
        ) {
            private int lockTicks = 0;
            private int rescanTicks = 0;

            @Override
            public boolean canUse() {
                LivingEntity lastHurtBy = this.mob.getLastHurtByMob();
                if (lastHurtBy != null && lastHurtBy.isAlive() && lockTicks <= 0 && isTargetValid(lastHurtBy)) {
                    this.target = lastHurtBy;
                    this.lockTicks = 60; // 受到攻击强行锁敌 3 秒防抽陀螺
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
                    ).stream().min(Comparator.comparingDouble(e -> this.mob.distanceToSqr(e))).orElse(null);

                    if (closest != null && closest != currentTarget && this.mob.distanceToSqr(closest) < currentDistSqr - 9.0) {
                        this.mob.setTarget(closest);
                        this.target = closest;
                        this.lockTicks = 40;
                    }
                }
            }
        });

        // 攻击/追击 AI，设置起手喷火触发半径为 8.0 格
        this.goalSelector.addGoal(1, new FlameSprayGoal(this, 8.0D));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
    }

    /**
     * 目标有效性校验（过滤 奸奇/Tzeentch 阵营生物）
     */
    private boolean isTargetValid(LivingEntity e) {
        return e != null
            && e.isAlive()
            && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs")));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:flamer_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:flamer_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:flamer_dead"));
    }

    @Override
    public void tick() {
        super.tick();

        // 攻击计时器双端同步
        if (this.isAttacking()) {
            this.attackTicks++;
        } else {
            this.attackTicks = 0;
        }

        // 客户端动画与火焰喷射粒子
        if (this.level().isClientSide()) {
            this.animationState0.animateWhen(true, this.tickCount);
            this.animationState2.animateWhen(this.isAttacking(), this.tickCount);

            if (this.isAttacking() && this.attackTicks >= 30 && this.attackTicks <= 70) {
                spawnFlameParticles();
            }
        }

        // 服务端攻击与伤害判定
        if (!this.level().isClientSide() && this.isAttacking()) {
            LivingEntity target = this.getTarget();
            if (target != null && target.isAlive()) {
                // 强行同步身体与头部朝向目标
                this.getLookControl().setLookAt(target, 60.0F, 60.0F);
                this.setYBodyRot(this.getYHeadRot());
            }

            // 刚开启喷火时（30 Tick），播放烈焰人喷火音效
            if (this.attackTicks == 30) {
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                        SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            }

            // 1.5s ~ 3.5s (30t ~ 70t) 喷火段，每 0.2 秒 (4t) 伤害判定一次
            // 判定射程 12.0 格，单次伤害 10.0 点
            if (this.attackTicks >= 30 && this.attackTicks <= 70 && this.attackTicks % 4 == 0) {
                performFlameAreaDamage(12.0D, 10.0F);
            }

            // 4.0s (80t) 流程结束
            if (this.attackTicks >= 80) {
                this.setAttacking(false);
            }
        }
    }

    /**
     * 计算当前头部正前端视角向量
     */
    private Vec3 getForwardVector() {
        float f = this.getXRot() * ((float) Math.PI / 180F);
        float f1 = -this.getYHeadRot() * ((float) Math.PI / 180F);
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        float f4 = Mth.cos(f);
        float f5 = Mth.sin(f);
        return new Vec3((double) (f3 * f4), (double) (-f5), (double) (f2 * f4));
    }

    /**
     * 12格范围锥形伤害判定 + 击退抗性抑制
     */
    private void performFlameAreaDamage(double range, float damagePerHit) {
        Vec3 lookVec = getForwardVector();
        Vec3 eyePos = this.position().add(0, this.getEyeHeight(), 0);

        AABB searchBox = this.getBoundingBox().inflate(range);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, searchBox, entity -> 
            entity != this && entity.isAlive() && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs")))
        );

        for (LivingEntity target : targets) {
            Vec3 toTarget = target.position().add(0, target.getBbHeight() / 2.0, 0).subtract(eyePos);
            double distance = toTarget.length();

            if (distance <= range) {
                double dot = lookVec.dot(toTarget.normalize());
                if (dot > 0.6D) { // 约 53 度角锥形区域
                    DamageSource ds = this.damageSources().mobAttack(this);
                    Vec3 oldDelta = target.getDeltaMovement();

                    if (target.hurt(ds, damagePerHit)) {
                        target.setRemainingFireTicks(60);
                        // 抑制击退移速，防止目标受击后被击退离场
                        target.setDeltaMovement(oldDelta.x * 0.3, target.getDeltaMovement().y, oldDelta.z * 0.3);
                    }
                }
            }
        }
    }

    /**
     * 客户端双色混合喷火粒子：通过 ResourceLocation 注册表按 ID 动态加载
     */
    private void spawnFlameParticles() {
        Vec3 look = getForwardVector();
        double startX = this.getX() + look.x * 0.8;
        double startY = this.getY() + this.getEyeHeight() * 0.85 + look.y * 0.8;
        double startZ = this.getZ() + look.z * 0.8;

        ParticleType<?> customType = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:pinkfalme"));

        for (int i = 0; i < 8; i++) {
            double speedScale = 0.5 + this.random.nextDouble() * 0.5;
            double vx = look.x * speedScale + (this.random.nextDouble() - 0.5) * 0.12;
            double vy = look.y * speedScale + (this.random.nextDouble() - 0.5) * 0.12;
            double vz = look.z * speedScale + (this.random.nextDouble() - 0.5) * 0.18;

            if (i % 2 == 0 && customType instanceof SimpleParticleType pinkFlameType) {
                this.level().addParticle(pinkFlameType, startX, startY, startZ, vx, vy, vz);
            } else {
                this.level().addParticle(ParticleTypes.SOUL_FIRE_FLAME, startX, startY, startZ, vx, vy, vz);
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float flval) {}

    public static void init(RegisterSpawnPlacementsEvent event) {
        event.register(HammercraftfantasyModEntities.FLAMER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
        builder = builder.add(Attributes.MAX_HEALTH, 60);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 20);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 4);
        return builder;
    }

    /**
     * 重构 AI Goal：24 格大范围寻路追击 + 8 格内站桩喷火
     */
    private static class FlameSprayGoal extends Goal {
        private final FlamerEntity flamer;
        private final double attackRange;
        private int cooldown = 0;

        public FlameSprayGoal(FlamerEntity flamer, double attackRange) {
            this.flamer = flamer;
            this.attackRange = attackRange;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.cooldown > 0) {
                this.cooldown--;
                return false;
            }
            LivingEntity target = this.flamer.getTarget();
            // 目标存在、存活且距离在 24 格范围内均可触发追击
            return target != null && target.isAlive() && this.flamer.distanceToSqr(target) <= (24.0D * 24.0D);
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = this.flamer.getTarget();
            if (target == null || !target.isAlive()) {
                return false;
            }
            // 正在喷火，或者目标仍然在 24 格追击范围内
            return this.flamer.isAttacking() || this.flamer.distanceToSqr(target) <= (24.0D * 24.0D);
        }

        @Override
        public void start() {
            this.flamer.attackTicks = 0;
        }

        @Override
        public void stop() {
            this.flamer.setAttacking(false);
            this.flamer.attackTicks = 0;
            // 修改：攻击冷却由 20 Ticks (1秒) 延长 2 倍至 40 Ticks (2秒)
            this.cooldown = 40; 
        }

        @Override
        public void tick() {
            LivingEntity target = this.flamer.getTarget();
            if (target == null) return;

            double distanceSq = this.flamer.distanceToSqr(target);
            this.flamer.getLookControl().setLookAt(target, 60.0F, 60.0F);

            // 1. 如果已开启喷火阶段：停步站桩输出
            if (this.flamer.isAttacking()) {
                this.flamer.getNavigation().stop();
                return;
            }

            // 2. 距离大于 8 格（但在 24 格内）：主动寻路追击目标
            if (distanceSq > (this.attackRange * this.attackRange)) {
                this.flamer.getNavigation().moveTo(target, 1.25D); // 以 1.25 倍速度主动逼近
            } 
            // 3. 进入 8 格触发射程：停步开启喷火
            else {
                this.flamer.getNavigation().stop();
                this.flamer.setAttacking(true);
                this.flamer.attackTicks = 0;
            }
        }
    }
}