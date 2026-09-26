package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;

import net.mcreator.hammercraftfantasy.procedures.Nurglemobs_bornProcedure;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Comparator;

public class GreatuncleanoneEntity extends Monster {
    public final AnimationState animationState0 = new AnimationState(); // constant (怠速)
    public final AnimationState animationState2 = new AnimationState(); // attack_1 (2.25s)
    public final AnimationState animationState3 = new AnimationState(); // attack_2 (4.0s)
    public final AnimationState animationState4 = new AnimationState(); // rangeattack (5.0s)
    public final AnimationState animationState5 = new AnimationState(); // born (5.0s)

    private static final EntityDataAccessor<Integer> ANIMATION_STATE =
            SynchedEntityData.defineId(GreatuncleanoneEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.GREEN, ServerBossEvent.BossBarOverlay.PROGRESS);

    private int bornTimer = 100;              // 5秒 born 动画 (100 Ticks)
    private int attackAnimationTimer = 0;   // 当前攻击总剩余倒计时
    private int currentAttackMaxTicks = 0;  // 当前所选攻击的总时长
    private float lockedYRot = 0.0F;        // 攻击锁定朝向

    public GreatuncleanoneEntity(EntityType<GreatuncleanoneEntity> type, Level world) {
        super(type, world);
        xpReward = 70;
        setNoAi(false);
        setPersistenceRequired();
    }

    public boolean isIntroPlaying() {
        return this.bornTimer > 0;
    }

    public boolean isAttacking() {
        return this.attackAnimationTimer > 0;
    }

    private double getAttackReachSqr(LivingEntity enemy) {
        double attackReach = (this.getBbWidth() + enemy.getBbWidth()) / 2.0 + 4.0;
        return attackReach * attackReach;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANIMATION_STATE, 5);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>(
                this, LivingEntity.class, 10, true, false,
                target -> !this.isIntroPlaying() && isTargetValid(target)
        ) {
            private int lockTicks = 0;
            private int rescanTicks = 0;

            @Override
            public boolean canUse() {
                if (GreatuncleanoneEntity.this.isIntroPlaying()) return false;
                
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

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            public boolean canUse() {
                return !GreatuncleanoneEntity.this.isIntroPlaying() && super.canUse();
            }

            @Override
            public boolean canContinueToUse() {
                return !GreatuncleanoneEntity.this.isIntroPlaying() && super.canContinueToUse();
            }

            @Override
            protected void checkAndPerformAttack(LivingEntity enemy) {
                if (this.canPerformAttack(enemy) && !GreatuncleanoneEntity.this.isAttacking()) {
                    this.resetAttackCooldown();
                    ((GreatuncleanoneEntity) this.mob).startDelayedAttack();
                }
            }

            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack()
                        && this.mob.distanceToSqr(entity) <= ((GreatuncleanoneEntity) this.mob).getAttackReachSqr(entity)
                        && this.mob.getSensing().hasLineOfSight(entity);
            }
        });

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return !GreatuncleanoneEntity.this.isIntroPlaying() && super.canUse();
            }
        });

        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8, 30) {
            @Override
            public boolean canUse() {
                return !GreatuncleanoneEntity.this.isIntroPlaying() && !GreatuncleanoneEntity.this.isAttacking() && super.canUse();
            }
        });

        this.goalSelector.addGoal(4, new FloatGoal(this));
    }

    private boolean isTargetValid(LivingEntity target) {
        return target != null 
            && target.isAlive() 
            && !target.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs")));
    }

    public void startDelayedAttack() {
        if (this.level().isClientSide() || this.isAttacking() || this.isIntroPlaying()) return;

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
        if (rand < 0.60F) {
            this.currentAttackMaxTicks = 45;
            this.attackAnimationTimer = 45;
            this.entityData.set(ANIMATION_STATE, 2);
        } else if (rand < 0.65F) {
            this.currentAttackMaxTicks = 22;
            this.attackAnimationTimer = 22;
            this.entityData.set(ANIMATION_STATE, 6);
        } else if (rand < 0.90F) {
            this.currentAttackMaxTicks = 80;
            this.attackAnimationTimer = 80;
            this.entityData.set(ANIMATION_STATE, 3);
        } else {
            this.currentAttackMaxTicks = 100;
            this.attackAnimationTimer = 100;
            this.entityData.set(ANIMATION_STATE, 4);
        }

        this.getNavigation().stop();
        this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata) {
        SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata);
        Nurglemobs_bornProcedure.execute(this);
        return retval;
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            if (this.isIntroPlaying()) {
                this.setTarget(null);
                this.setLastHurtByMob(null);
                this.setDeltaMovement(0, this.getDeltaMovement().y, 0);

                this.bornTimer--;
                if (this.bornTimer == 40) {
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:guo_laugh2")),
                            SoundSource.HOSTILE, 1.0F, 1.0F);
                }
                if (this.bornTimer == 0) {
                    this.entityData.set(ANIMATION_STATE, 0);
                }
            } else if (this.isAttacking()) {
                this.getNavigation().stop();
                this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
                this.setYRot(this.lockedYRot);
                this.setYHeadRot(this.lockedYRot);
                this.setYBodyRot(this.lockedYRot);

                int elapsedTicks = this.currentAttackMaxTicks - this.attackAnimationTimer;
                int state = this.entityData.get(ANIMATION_STATE);

                if (state == 2) {
                    if (elapsedTicks == 17) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                                BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:havy_foot_steps")),
                                SoundSource.HOSTILE, 1.2F, 0.9F);
                    }
                    if (elapsedTicks == 25) {
                        Vec3 look = this.getLookAngle();
                        Vec3 attackCenter = this.position().add(look.scale(4.5));
                        AABB frontAOE = new AABB(
                                attackCenter.x - 3.5, attackCenter.y - 1.5, attackCenter.z - 3.5,
                                attackCenter.x + 3.5, attackCenter.y + 2.5, attackCenter.z + 3.5
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, frontAOE,
                                e -> e != this && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs"))));

                        for (LivingEntity target : targets) {
                            this.doHurtTarget(target);
                        }

                        triggerScreenShake(16.0D, 0.1D);
                        if (this.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                                    attackCenter.x, this.getY(), attackCenter.z,
                                    3, 0.6, 0.1, 0.6, 0.0);
                        }
                    }
                } else if (state == 6) { // 快速爪击
                    if (elapsedTicks == 8) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                                BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:havy_foot_steps")),
                                SoundSource.HOSTILE, 1.3F, 1.1F);
                    }
                    if (elapsedTicks == 12) {
                        Vec3 look = this.getLookAngle();
                        Vec3 attackCenter = this.position().add(look.scale(5.5));
                        AABB frontAOE = new AABB(
                                attackCenter.x - 4.5, attackCenter.y - 1.5, attackCenter.z - 4.5,
                                attackCenter.x + 4.5, attackCenter.y + 2.5, attackCenter.z + 4.5
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, frontAOE,
                                e -> e != this && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs"))));

                        for (LivingEntity target : targets) {
                            if (target.isBlocking() && target instanceof Player player) {
                                player.disableShield(); // 无参调用，破盾并使盾牌进入冷却
                            }
                            this.doHurtTarget(target);
                        }

                        triggerScreenShake(16.0D, 0.12D);
                        if (this.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                                    attackCenter.x, this.getY(), attackCenter.z,
                                    5, 1.0, 0.1, 1.0, 0.0);
                        }
                    }
                } else if (state == 3) { // 泰山压顶
                    if (elapsedTicks == 20) {
                        this.setDeltaMovement(this.getDeltaMovement().x, 0.65D, this.getDeltaMovement().z);
                        this.hasImpulse = true;
                    }
                    if (elapsedTicks == 32) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                                BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:havy_foot_steps")),
                                SoundSource.HOSTILE, 1.5F, 0.7F);
                    }
                    if (elapsedTicks == 40) {
                        this.setDeltaMovement(this.getDeltaMovement().x, -0.5D, this.getDeltaMovement().z);
                        triggerScreenShake(16.0D, 0.15D);

                        if (this.level() instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                                    this.getX(), this.getY(), this.getZ(), 40, 2.5, 0.2, 2.5, 0.08);
                            serverLevel.sendParticles(ParticleTypes.EXPLOSION,
                                    this.getX(), this.getY(), this.getZ(), 6, 1.2, 0.2, 1.2, 0.0);
                        }

                        double baseAttack = this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class,
                                this.getBoundingBox().inflate(10.0D),
                                e -> e != this && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs"))));

                        for (LivingEntity entity : targets) {
                            double dist = this.distanceTo(entity);
                            float damage = 0.0F;

                            if (dist <= 5.0D) {
                                damage = (float) (baseAttack * 2.0F);  // 5格内2倍
                            } else if (dist <= 8.0D) {
                                damage = (float) (baseAttack * 0.7F);  // 8格内0.7倍
                            } else if (dist <= 10.0D) {
                                damage = (float) (baseAttack * 0.3F);  // 10格内0.3倍
                            }

                            if (damage > 0.0F) {
                                if (entity.isBlocking() && entity instanceof Player player) {
                                    player.disableShield(); // 无参调用，破盾
                                }
                                entity.hurt(this.damageSources().mobAttack(this), damage);
                                entity.setDeltaMovement(entity.getDeltaMovement().add(0, 0.25 * ((10.0 - dist) / 10.0), 0));
                            }
                        }
                    }
                } else if (state == 4) {
                    if (elapsedTicks >= 40 && elapsedTicks < 70) {
                        Vec3 look = this.getLookAngle();
                        double bellyX = this.getX() + look.x * 1.3;
                        double bellyY = this.getY() + 1.8;
                        double bellyZ = this.getZ() + look.z * 1.3;

                        if (this.level() instanceof ServerLevel serverLevel) {
                            var particleType = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:nurglepuke"));
                            if (particleType instanceof SimpleParticleType simpleParticle) {
                                for (int i = 0; i < 8; i++) {
                                    double pX = bellyX + (this.random.nextDouble() - 0.5) * 2.2;
                                    double pY = bellyY + (this.random.nextDouble() - 0.5) * 0.8;
                                    double pZ = bellyZ + (this.random.nextDouble() - 0.5) * 2.2;

                                    double forwardSpeed = 0.4 + this.random.nextDouble() * 0.4;
                                    double spreadSide = (this.random.nextDouble() - 0.5) * 0.6;
                                    double vx = look.x * forwardSpeed + look.z * spreadSide;
                                    double vy = 0.25D + this.random.nextDouble() * 0.3D;
                                    double vz = look.z * forwardSpeed - look.x * spreadSide;

                                    serverLevel.sendParticles(simpleParticle, pX, pY, pZ, 0, vx, vy, vz, 1.0);
                                }
                            }
                        }

                        if ((elapsedTicks - 40) % 3 != 2) {
                            EntityType<?> maggotType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:rotmaggot"));
                            if (maggotType != null) {
                                Entity maggot = maggotType.create(this.level());
                                if (maggot != null) {
                                    double maggotX = bellyX + (this.random.nextDouble() - 0.5) * 2.2;
                                    double maggotY = bellyY + (this.random.nextDouble() - 0.5) * 0.8;
                                    double maggotZ = bellyZ + (this.random.nextDouble() - 0.5) * 2.2;

                                    maggot.moveTo(maggotX, maggotY, maggotZ, this.getYRot(), 0.0F);

                                    double forwardSpeed = 0.4 + this.random.nextDouble() * 0.4;
                                    double spreadSide = (this.random.nextDouble() - 0.5) * 0.6;
                                    maggot.setDeltaMovement(
                                            look.x * forwardSpeed + look.z * spreadSide,
                                            0.25D + this.random.nextDouble() * 0.3D,
                                            look.z * forwardSpeed - look.x * spreadSide
                                    );
                                    this.level().addFreshEntity(maggot);
                                }
                            }
                        }

                        Vec3 pos3 = this.position().add(look.scale(3.5));
                        Vec3 pos6 = this.position().add(look.scale(6.0));

                        List<LivingEntity> nearFront = this.level().getEntitiesOfClass(LivingEntity.class,
                                new AABB(pos3.x - 3.5, pos3.y - 1.5, pos3.z - 3.5, pos3.x + 3.5, pos3.y + 3.0, pos3.z + 3.5),
                                e -> e != this && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs"))));
                        for (LivingEntity e : nearFront) {
                            e.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 1));
                            e.addEffect(new MobEffectInstance(MobEffects.WITHER, 600, 1));
                        }

                        List<LivingEntity> farFront = this.level().getEntitiesOfClass(LivingEntity.class,
                                new AABB(pos6.x - 4.5, pos6.y - 1.5, pos6.z - 4.5, pos6.x + 4.5, pos6.y + 3.0, pos6.z + 4.5),
                                e -> e != this && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs"))));
                        for (LivingEntity e : farFront) {
                            if (!e.hasEffect(MobEffects.POISON)) {
                                e.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 0));
                            }
                        }
                    }
                }

                this.attackAnimationTimer--;
                if (this.attackAnimationTimer == 0) {
                    this.entityData.set(ANIMATION_STATE, 0);
                }
            }

            if (!this.isIntroPlaying() && this.getTarget() != null && this.tickCount % 10 == 0) {
                destroyBlocksInAABB();
            }
        } else {
            int state = this.entityData.get(ANIMATION_STATE);

            this.animationState0.animateWhen(state == 0, this.tickCount);
            this.animationState2.animateWhen(state == 2 || state == 6, this.tickCount);
            this.animationState3.animateWhen(state == 3, this.tickCount);
            this.animationState4.animateWhen(state == 4, this.tickCount);
            this.animationState5.animateWhen(state == 5, this.tickCount);
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
    protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource source, boolean recentlyHitIn) {
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
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:guo_laugh1"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:guo_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:guo_laugh2"));
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        if (damagesource.getDirectEntity() instanceof ThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
            return false;
        if (damagesource.is(DamageTypes.DROWN))
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
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
        builder = builder.add(Attributes.MAX_HEALTH, 1000);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 28);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 2);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        return builder;
    }
}