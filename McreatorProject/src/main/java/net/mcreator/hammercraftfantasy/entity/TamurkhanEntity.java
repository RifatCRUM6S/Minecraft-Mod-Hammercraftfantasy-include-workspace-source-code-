package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;

import net.mcreator.hammercraftfantasy.procedures.Nurglemobs_bornProcedure;
import net.mcreator.hammercraftfantasy.procedures.NurgelmobsgethurtProcedure;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Comparator;

public class TamurkhanEntity extends Monster {
    public final AnimationState animationState0 = new AnimationState(); // constant
    public final AnimationState animationState1 = new AnimationState(); // attack_1
    public final AnimationState animationState2 = new AnimationState(); // attack_2
    public final AnimationState animationState3 = new AnimationState(); // attack_3
    public final AnimationState animationState4 = new AnimationState(); // attack_4
    public final AnimationState animationState5 = new AnimationState(); // attack_range

    private static final EntityDataAccessor<Integer> ANIMATION_STATE = 
        SynchedEntityData.defineId(TamurkhanEntity.class, EntityDataSerializers.INT);

    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.GREEN, ServerBossEvent.BossBarOverlay.PROGRESS);

    private int attackAnimationTimer = 0;
    private int currentAttackMaxTicks = 0;
    private float lockedYRot = 0.0F;

    public TamurkhanEntity(EntityType<TamurkhanEntity> type, Level world) {
        super(type, world);
        xpReward = 100;
        setNoAi(false);
        setPersistenceRequired();
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
        builder.define(ANIMATION_STATE, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // 动态索敌 Goal：防抽陀螺锁敌 + 纳垢 (Nurgle) 阵营过滤
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
                    this.lockTicks = 60; // 被打后强行锁定攻击者 3 秒
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
                if (this.rescanTicks >= 40) { // 每 2 秒重扫描周围实体
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
            protected void checkAndPerformAttack(LivingEntity enemy) {
                if (this.canPerformAttack(enemy) && !TamurkhanEntity.this.isAttacking()) {
                    this.resetAttackCooldown();
                    ((TamurkhanEntity) this.mob).startDelayedAttack();
                }
            }

            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() 
                    && this.mob.distanceToSqr(entity) <= ((TamurkhanEntity) this.mob).getAttackReachSqr(entity) 
                    && this.mob.getSensing().hasLineOfSight(entity);
            }
        });

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setAlertOthers());

        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 0.8, 30) {
            @Override
            public boolean canUse() {
                return !TamurkhanEntity.this.isAttacking() && super.canUse();
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
        if (rand < 0.10F) {
            this.currentAttackMaxTicks = 15;
            this.attackAnimationTimer = 15;
            this.entityData.set(ANIMATION_STATE, 2);
        } else if (rand < 0.40F) {
            this.currentAttackMaxTicks = 30;
            this.attackAnimationTimer = 30;
            this.entityData.set(ANIMATION_STATE, 3);
        } else if (rand < 0.70F) {
            this.currentAttackMaxTicks = 30;
            this.attackAnimationTimer = 30;
            this.entityData.set(ANIMATION_STATE, 4);
        } else if (rand < 0.90F) {
            this.currentAttackMaxTicks = 40;
            this.attackAnimationTimer = 40;
            this.entityData.set(ANIMATION_STATE, 6);
        } else {
            this.currentAttackMaxTicks = 60;
            this.attackAnimationTimer = 60;
            this.entityData.set(ANIMATION_STATE, 7);
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
            if (this.isAttacking()) {
                this.getNavigation().stop();
                this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
                this.setYRot(this.lockedYRot);
                this.setYHeadRot(this.lockedYRot);
                this.setYBodyRot(this.lockedYRot);

                int elapsedTicks = this.currentAttackMaxTicks - this.attackAnimationTimer;
                int state = this.entityData.get(ANIMATION_STATE);
                LivingEntity mainTarget = this.getTarget();

                if (state == 2) {
                    if (elapsedTicks == 8) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.2F, 0.8F + this.random.nextFloat() * 0.2F);

                        if (mainTarget != null && this.distanceToSqr(mainTarget) <= this.getAttackReachSqr(mainTarget) && this.getSensing().hasLineOfSight(mainTarget)) {
                            this.doHurtTarget(mainTarget);
                        }
                    }
                } else if (state == 3) {
                    if (elapsedTicks == 20) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.5F, 0.7F + this.random.nextFloat() * 0.2F);

                        Vec3 look = this.getLookAngle();
                        Vec3 attackCenter = this.position().add(look.scale(3.5));
                        AABB sweepAOE = new AABB(
                            attackCenter.x - 4.0, attackCenter.y - 1.5, attackCenter.z - 4.0,
                            attackCenter.x + 4.0, attackCenter.y + 2.5, attackCenter.z + 4.0
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, sweepAOE,
                            e -> e != this && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs"))));

                        for (LivingEntity target : targets) {
                            if (target == mainTarget) {
                                this.doHurtTarget(target); // 主目标全额伤害
                            } else {
                                float halfDmg = (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.5F);
                                target.hurt(this.damageSources().mobAttack(this), halfDmg); // 范围其他目标半额伤害
                            }
                        }
                        triggerScreenShake(12.0D, 0.08D);
                    }
                } else if (state == 4) {
                    if (elapsedTicks == 20) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.HOSTILE, 1.5F, 0.7F + this.random.nextFloat() * 0.2F);

                        if (mainTarget != null && this.distanceToSqr(mainTarget) <= this.getAttackReachSqr(mainTarget) && this.getSensing().hasLineOfSight(mainTarget)) {
                            float baseDamage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
                            mainTarget.hurt(this.damageSources().mobAttack(this), baseDamage * 1.5F);
                        }
                        triggerScreenShake(10.0D, 0.1D);
                    }
                } else if (state == 6) {
                    if (elapsedTicks == 20) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.PLAYER_ATTACK_KNOCKBACK, SoundSource.HOSTILE, 1.5F, 0.7F + this.random.nextFloat() * 0.2F);

                        Vec3 look = this.getLookAngle();
                        Vec3 attackCenter = this.position().add(look.scale(3.5));
                        AABB pushAOE = new AABB(
                            attackCenter.x - 3.5, attackCenter.y - 1.5, attackCenter.z - 3.5,
                            attackCenter.x + 3.5, attackCenter.y + 2.5, attackCenter.z + 3.5
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, pushAOE,
                            e -> e != this && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs"))));

                        for (LivingEntity target : targets) {
                            if (target == mainTarget) {
                                this.doHurtTarget(target);
                            } else {
                                float halfDmg = (float) (this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.5F);
                                target.hurt(this.damageSources().mobAttack(this), halfDmg);
                            }
                            Vec3 knockbackDir = target.position().subtract(this.position()).normalize();
                            target.setDeltaMovement(target.getDeltaMovement().add(knockbackDir.x * 1.8D, 0.45D, knockbackDir.z * 1.8D));
                            target.hurtMarked = true;
                        }
                        triggerScreenShake(16.0D, 0.12D);
                    }
                } else if (state == 7) {
                    if (elapsedTicks >= 30 && elapsedTicks < 50) {
                        Vec3 look = this.getLookAngle();
                        double bellyX = this.getX() + look.x * 1.2;
                        double bellyY = this.getY() + 1.6;
                        double bellyZ = this.getZ() + look.z * 1.2;

                        if (this.level() instanceof ServerLevel serverLevel) {
                            if (elapsedTicks % 2 == 0) {
                                for (int i = 0; i < 2; i++) {
                                    double offsetX = (this.random.nextDouble() - 0.5) * 1.0;
                                    double offsetY = (this.random.nextDouble() - 0.5) * 0.8;
                                    double offsetZ = (this.random.nextDouble() - 0.5) * 1.0;

                                    boolean isWither = this.random.nextFloat() < 0.3F;
                                    float r = isWither ? 0.05F : 0.15F;
                                    float g = isWither ? 0.05F : 0.85F;
                                    float b = isWither ? 0.05F : 0.15F;

                                    serverLevel.sendParticles(
                                        ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, r, g, b),
                                        bellyX + offsetX, bellyY + offsetY, bellyZ + offsetZ,
                                        1, 0.05, 0.05, 0.05, 0.02
                                    );
                                }
                            }

                            ParticleType<?> pukeType = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:nurglepuke"));
                            if (pukeType instanceof SimpleParticleType simplePuke) {
                                for (int i = 0; i < 8; i++) {
                                    double px = bellyX + (this.random.nextDouble() - 0.5) * 0.8;
                                    double py = bellyY + (this.random.nextDouble() - 0.5) * 0.5;
                                    double pz = bellyZ + (this.random.nextDouble() - 0.5) * 0.8;

                                    double forwardSpeed = 0.4 + this.random.nextDouble() * 0.4;
                                    double spreadSide = (this.random.nextDouble() - 0.5) * 0.6;

                                    double vx = look.x * forwardSpeed + look.z * spreadSide;
                                    double vy = 0.25D + this.random.nextDouble() * 0.3D;
                                    double vz = look.z * forwardSpeed - look.x * spreadSide;

                                    serverLevel.sendParticles(
                                        simplePuke,
                                        px, py, pz,
                                        0,
                                        vx, vy, vz,
                                        1.0D
                                    );
                                }
                            }
                        }

                        if ((elapsedTicks - 30) % 3 != 2) {
                            EntityType<?> maggotType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:rotmaggot"));
                            if (maggotType != null) {
                                Entity maggot = maggotType.create(this.level());
                                if (maggot != null) {
                                    double maggotX = bellyX + (this.random.nextDouble() - 0.5) * 1.5;
                                    double maggotY = bellyY + (this.random.nextDouble() - 0.5) * 0.5;
                                    double maggotZ = bellyZ + (this.random.nextDouble() - 0.5) * 1.5;

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

            if (this.getTarget() != null && this.tickCount % 10 == 0) {
                destroyBlocksInAABB();
            }
        } else {
            int state = this.entityData.get(ANIMATION_STATE);

            this.animationState0.animateWhen(state == 0, this.tickCount);
            this.animationState1.animateWhen(state == 2, this.tickCount);
            this.animationState2.animateWhen(state == 3, this.tickCount);
            this.animationState3.animateWhen(state == 4, this.tickCount);
            this.animationState4.animateWhen(state == 6, this.tickCount);
            this.animationState5.animateWhen(state == 7, this.tickCount);
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
    public void die(DamageSource source) {
        super.die(source);

        if (!this.level().isClientSide()) {
            EntityType<?> lordType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:maggotlord"));
            if (lordType != null) {
                Entity maggotLord = lordType.create(this.level());
                if (maggotLord != null) {
                    maggotLord.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                    this.level().addFreshEntity(maggotLord);
                }
            }

            BlockPos safePos = findSafeVillagerSpawnPos();
            if (safePos != null) {
                Villager villager = EntityType.VILLAGER.create(this.level());
                if (villager != null) {
                    villager.moveTo(safePos.getX() + 0.5, safePos.getY(), safePos.getZ() + 0.5, this.random.nextFloat() * 360.0F, 0.0F);
                    villager.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 12000, 2));
                    this.level().addFreshEntity(villager);
                }
            }
        }
    }

    private BlockPos findSafeVillagerSpawnPos() {
        Level world = this.level();
        BlockPos origin = this.blockPosition();

        for (int i = 0; i < 30; i++) {
            double angle = this.random.nextDouble() * Math.PI * 2;
            double distance = 32.0 + this.random.nextDouble() * 16.0;

            int x = Mth.floor(origin.getX() + Math.cos(angle) * distance);
            int z = Mth.floor(origin.getZ() + Math.sin(angle) * distance);

            for (int dy = 16; dy >= -16; dy--) {
                BlockPos checkPos = new BlockPos(x, origin.getY() + dy, z);
                BlockPos groundPos = checkPos.below();

                BlockState groundState = world.getBlockState(groundPos);
                BlockState posState = world.getBlockState(checkPos);
                BlockState aboveState = world.getBlockState(checkPos.above());

                if (groundState.isSolid() && groundState.getFluidState().isEmpty()
                        && !posState.isSolid() && posState.getFluidState().isEmpty()
                        && !aboveState.isSolid() && aboveState.getFluidState().isEmpty()) {
                    return checkPos;
                }
            }
        }
        return null;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:tamur_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:tamur_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:tamur_dead"));
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level();

        NurgelmobsgethurtProcedure.execute(world, x, y, z);

        if (damagesource.getDirectEntity() instanceof ThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
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
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 600);
        builder = builder.add(Attributes.ARMOR, 10);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 45);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.STEP_HEIGHT, 2);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1);
        return builder;
    }
}