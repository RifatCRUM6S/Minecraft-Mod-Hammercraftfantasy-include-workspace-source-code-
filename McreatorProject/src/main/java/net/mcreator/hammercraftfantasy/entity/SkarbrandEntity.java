package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.tags.TagKey;

import java.util.List;
import java.util.Comparator;

public class SkarbrandEntity extends Monster {
    public static final int STATE_IDLE = 0;
    public static final int STATE_ATTACK_LIGHT = 2;   // attack2
    public static final int STATE_ATTACK_SWEEP = 3;   // attack1
    public static final int STATE_ATTACK_HEAVY = 4;   // attack4
    public static final int STATE_ATTACK_SLAM = 6;    // attack3
    public static final int STATE_ATTACK_BERSERK = 7; // rangeattack (吐息)

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState attackLightAnimationState = new AnimationState();
    public final AnimationState attackSweepAnimationState = new AnimationState();
    public final AnimationState attackHeavyAnimationState = new AnimationState();
    public final AnimationState attackSlamAnimationState = new AnimationState();
    public final AnimationState attackBerserkAnimationState = new AnimationState();

    private static final EntityDataAccessor<Integer> ANIMATION_STATE = 
        SynchedEntityData.defineId(SkarbrandEntity.class, EntityDataSerializers.INT);
    // 【关键修复 1】：新增用于双端同步的攻击计时器
    private static final EntityDataAccessor<Integer> ATTACK_TIMER = 
        SynchedEntityData.defineId(SkarbrandEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.RED, ServerBossEvent.BossBarOverlay.PROGRESS);

    private int currentAttackMaxTicks = 0;
    private float lockedYRot = 0.0F;

    public SkarbrandEntity(EntityType<SkarbrandEntity> type, Level world) {
        super(type, world);
        xpReward = 150;
        setNoAi(false);
        setPersistenceRequired();
    }

    public int getAttackState() {
        return this.entityData.get(ANIMATION_STATE);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACK_TIMER) > 0;
    }

    private double getAttackReachSqr(LivingEntity enemy) {
        double attackReach = (this.getBbWidth() + enemy.getBbWidth()) / 2.0 + 4.5;
        return attackReach * attackReach;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANIMATION_STATE, STATE_IDLE);
        builder.define(ATTACK_TIMER, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

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

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.35, false) {
            @Override
            protected void checkAndPerformAttack(LivingEntity enemy) {
                if (this.canPerformAttack(enemy) && !SkarbrandEntity.this.isAttacking()) {
                    this.resetAttackCooldown();
                    ((SkarbrandEntity) this.mob).startDelayedAttack();
                }
            }

            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() 
                    && this.mob.distanceToSqr(entity) <= ((SkarbrandEntity) this.mob).getAttackReachSqr(entity) 
                    && this.mob.getSensing().hasLineOfSight(entity);
            }
        });

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());

        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.9, 30) {
            @Override
            public boolean canUse() {
                return !SkarbrandEntity.this.isAttacking() && super.canUse();
            }
        });

        this.goalSelector.addGoal(4, new FloatGoal(this));
    }

    private boolean isTargetValid(LivingEntity target) {
        return target != null 
            && target.isAlive() 
            && !target.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs")));
    }

    private void playCustomSound(String soundPath, float volume, float pitch) {
        this.level().playSound(
            null, 
            this.getX(), this.getY(), this.getZ(),
            SoundEvent.createVariableRangeEvent(ResourceLocation.parse("hammercraftfantasy:" + soundPath)),
            SoundSource.HOSTILE, 
            volume, 
            pitch
        );
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

        float rand = this.random.nextFloat();
        int ticks = 0;
        int state = STATE_IDLE;

        if (rand < 0.15F) {
            ticks = 15;
            state = STATE_ATTACK_LIGHT;
        } else if (rand < 0.45F) {
            ticks = 30;
            state = STATE_ATTACK_SWEEP;
        } else if (rand < 0.70F) {
            ticks = 30;
            state = STATE_ATTACK_HEAVY;
        } else if (rand < 0.88F) {
            ticks = 40;
            state = STATE_ATTACK_SLAM;
        } else {
            ticks = 80;
            state = STATE_ATTACK_BERSERK;
        }

        this.currentAttackMaxTicks = ticks;
        this.entityData.set(ATTACK_TIMER, ticks);
        this.entityData.set(ANIMATION_STATE, state);

        this.getNavigation().stop();
        this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
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
            entity != this && entity.isAlive() && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs")))
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

    /**
     * 【关键修复 2】：更加稳健的粒子喷射方案
     */
    private void spawnFlameParticles() {
        Vec3 look = getForwardVector();
        // 稍微往嘴巴/头部前上方偏移
        double startX = this.getX() + look.x * 1.2;
        double startY = this.getY() + this.getEyeHeight() * 0.9 + look.y * 1.2;
        double startZ = this.getZ() + look.z * 1.2;

        for (int i = 0; i < 12; i++) { // 增加粒子密度
            double speedScale = 0.6 + this.random.nextDouble() * 0.6;
            double vx = look.x * speedScale + (this.random.nextDouble() - 0.5) * 0.2;
            double vy = look.y * speedScale + (this.random.nextDouble() - 0.5) * 0.2;
            double vz = look.z * speedScale + (this.random.nextDouble() - 0.5) * 0.2;

            this.level().addParticle(ParticleTypes.FLAME, startX, startY, startZ, vx, vy, vz);
        }
    }

    @Override
    public void tick() {
        super.tick();

        int state = this.entityData.get(ANIMATION_STATE);
        int timer = this.entityData.get(ATTACK_TIMER);

        // 客户端逻辑
        if (this.level().isClientSide()) {
            // 【关键修复 3】：基于倒计时 timer 直接判断粒子播放，不依赖本地未同步变量
            if (state == STATE_ATTACK_BERSERK && timer >= 10 && timer <= 50) {
                spawnFlameParticles();
            }

            this.idleAnimationState.animateWhen(state == STATE_IDLE, this.tickCount);
            this.attackLightAnimationState.animateWhen(state == STATE_ATTACK_LIGHT, this.tickCount);
            this.attackSweepAnimationState.animateWhen(state == STATE_ATTACK_SWEEP, this.tickCount);
            this.attackHeavyAnimationState.animateWhen(state == STATE_ATTACK_HEAVY, this.tickCount);
            this.attackSlamAnimationState.animateWhen(state == STATE_ATTACK_SLAM, this.tickCount);
            this.attackBerserkAnimationState.animateWhen(state == STATE_ATTACK_BERSERK, this.tickCount);
        } else {
            // 服务端逻辑
            if (timer > 0) {
                this.getNavigation().stop();
                this.setDeltaMovement(0, this.getDeltaMovement().y, 0);

                LivingEntity mainTarget = this.getTarget();
                if (state == STATE_ATTACK_BERSERK && mainTarget != null && mainTarget.isAlive()) {
                    this.getLookControl().setLookAt(mainTarget, 60.0F, 60.0F);
                    this.setYRot(this.getYHeadRot());
                    this.setYBodyRot(this.getYHeadRot());
                } else {
                    this.setYRot(this.lockedYRot);
                    this.setYHeadRot(this.lockedYRot);
                    this.setYBodyRot(this.lockedYRot);
                }

                int elapsedTicks = this.currentAttackMaxTicks - timer;

                // 1. STATE_ATTACK_LIGHT
                if (state == STATE_ATTACK_LIGHT) {
                    if (elapsedTicks == 1) playCustomSound("skarbrand_attack2", 2.0F, 1.0F);
                    if (elapsedTicks == 7) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.4F, 0.9F + this.random.nextFloat() * 0.2F);

                        if (mainTarget != null && this.distanceToSqr(mainTarget) <= this.getAttackReachSqr(mainTarget) && this.getSensing().hasLineOfSight(mainTarget)) {
                            this.doHurtTarget(mainTarget);
                        }
                    }
                } 
                // 2. STATE_ATTACK_SWEEP
                else if (state == STATE_ATTACK_SWEEP) {
                    if (elapsedTicks == 1) playCustomSound("skarbrand_attack1and3", 2.0F, 1.0F);
                    if (elapsedTicks == 13) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.8F, 0.6F + this.random.nextFloat() * 0.2F);

                        Vec3 look = this.getLookAngle();
                        Vec3 attackCenter = this.position().add(look.scale(3.8));
                        AABB sweepAOE = new AABB(
                            attackCenter.x - 4.5, attackCenter.y - 1.5, attackCenter.z - 4.5,
                            attackCenter.x + 4.5, attackCenter.y + 2.5, attackCenter.z + 4.5
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, sweepAOE,
                            e -> e != this && e.isAlive() && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs"))));

                        for (LivingEntity target : targets) {
                            if (target == mainTarget) {
                                this.doHurtTarget(target);
                            } else {
                                float halfDmg = (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.6F);
                                target.hurt(this.damageSources().mobAttack(this), halfDmg);
                            }
                        }
                        triggerScreenShake(14.0D, 0.1D);
                    }
                } 
                // 3. STATE_ATTACK_HEAVY
                else if (state == STATE_ATTACK_HEAVY) {
                    if (elapsedTicks == 1) playCustomSound("skarbrand_attack4", 2.0F, 1.0F);
                    if (elapsedTicks == 20) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.HOSTILE, 2.0F, 0.6F);

                        if (mainTarget != null && this.distanceToSqr(mainTarget) <= this.getAttackReachSqr(mainTarget) && this.getSensing().hasLineOfSight(mainTarget)) {
                            float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mainTarget.hurt(this.damageSources().mobAttack(this), baseDamage * 1.75F);
                        }
                        triggerScreenShake(12.0D, 0.14D);
                    }
                } 
                // 4. STATE_ATTACK_SLAM
                else if (state == STATE_ATTACK_SLAM) {
                    if (elapsedTicks == 1) playCustomSound("skarbrand_attack1and3", 2.0F, 1.0F);
                    if (elapsedTicks == 8) playCustomSound("havy_foot_steps", 1.8F, 1.0F);
                    if (elapsedTicks == 13) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_KNOCKBACK, SoundSource.HOSTILE, 2.0F, 0.5F);

                        Vec3 look = this.getLookAngle();
                        Vec3 attackCenter = this.position().add(look.scale(3.5));
                        AABB pushAOE = new AABB(
                            attackCenter.x - 4.0, attackCenter.y - 1.5, attackCenter.z - 4.0,
                            attackCenter.x + 4.0, attackCenter.y + 2.5, attackCenter.z + 4.0
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, pushAOE,
                            e -> e != this && e.isAlive() && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs"))));

                        for (LivingEntity target : targets) {
                            float dmg = (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.7F);
                            target.hurt(this.damageSources().mobAttack(this), dmg);

                            Vec3 knockbackDir = target.position().subtract(this.position()).normalize();
                            target.setDeltaMovement(target.getDeltaMovement().add(knockbackDir.x * 2.2D, 0.55D, knockbackDir.z * 2.2D));
                            target.hurtMarked = true;
                        }
                        triggerScreenShake(18.0D, 0.18D);
                    }
                } 
                // 5. STATE_ATTACK_BERSERK (吐息)
                else if (state == STATE_ATTACK_BERSERK) {
                    if (elapsedTicks == 1) {
                        playCustomSound("skarbrand_rangeattack", 2.0F, 1.0F);
                    }

                    if (elapsedTicks == 30) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                                SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1.5F, 0.8F + this.random.nextFloat() * 0.3F);
                    }

                    if (elapsedTicks >= 30 && elapsedTicks <= 70 && elapsedTicks % 4 == 0) {
                        performFlameAreaDamage(16.0D, 20.0F);
                    }
                }

                // 递减服务端和同步数据中的攻击倒计时
                timer--;
                this.entityData.set(ATTACK_TIMER, timer);

                if (timer <= 0) {
                    this.entityData.set(ANIMATION_STATE, STATE_IDLE);
                }
            }

            if (this.getTarget() != null && this.tickCount % 8 == 0) {
                destroyBlocksInAABB();
            }
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

    private void destroyBlocksInAABB() {
        int feetY = this.blockPosition().getY();

        int minX = (int) Math.floor(this.getBoundingBox().minX - 0.5);
        int maxX = (int) Math.ceil(this.getBoundingBox().maxX + 0.5);
        int minY = feetY + 1; 
        int maxY = (int) Math.ceil(this.getBoundingBox().maxY + 1);
        int minZ = (int) Math.floor(this.getBoundingBox().minZ - 0.5);
        int maxZ = (int) Math.ceil(this.getBoundingBox().maxZ + 0.5);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockState state = this.level().getBlockState(pos);
                    if (!state.isAir() && state.getDestroySpeed(this.level(), pos) >= 0 && state.getDestroySpeed(this.level(), pos) < 50.0F) {
                        this.level().destroyBlock(pos, true, this);
                    }
                }
            }
        }
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
    public boolean hurt(DamageSource damagesource, float amount) {
        if (damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
            return false;
        if (damagesource.is(DamageTypes.WITHER) || damagesource.is(DamageTypes.WITHER_SKULL))
            return false;

        return super.hurt(damagesource, amount);
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
        builder = builder.add(Attributes.MAX_HEALTH, 800);
        builder = builder.add(Attributes.ARMOR, 12);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 40);
        builder = builder.add(Attributes.FOLLOW_RANGE, 40);
        builder = builder.add(Attributes.STEP_HEIGHT, 3);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1.0);
        return builder;
    }
}