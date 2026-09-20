package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.List;

public class ChaosspawnEntity extends Monster {
    public final AnimationState animationState0 = new AnimationState(); // limbstwisting
    public final AnimationState animationState1 = new AnimationState(); // breathing
    public final AnimationState animationState3 = new AnimationState(); // attack_1 (State 1)
    public final AnimationState animationState4 = new AnimationState(); // attack_2 (State 2)
    public final AnimationState animationState5 = new AnimationState(); // attack_3 (State 3)
    public final AnimationState animationState6 = new AnimationState(); // birth

    private static final EntityDataAccessor<Integer> ANIMATION_STATE = 
        SynchedEntityData.defineId(ChaosspawnEntity.class, EntityDataSerializers.INT);

    private int attackAnimationTimer = 0;   
    private int currentAttackMaxTicks = 0;  
    private float lockedYRot = 0.0F;        

    public ChaosspawnEntity(EntityType<ChaosspawnEntity> type, Level world) {
        super(type, world);
        xpReward = 30;
        setNoAi(false);
    }

    public boolean isAttacking() {
        return this.attackAnimationTimer > 0;
    }

    private double getAttackReachSqr(LivingEntity enemy) {
        double attackReach = (this.getBbWidth() + enemy.getBbWidth()) / 2.0 + 2.0;
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

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 24, true, false, 
            target -> target != null && target.isAlive() 
                && !target.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:shadowchaos_mobs")))));

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            protected void checkAndPerformAttack(LivingEntity enemy) {
                if (this.canPerformAttack(enemy) && !ChaosspawnEntity.this.isAttacking()) {
                    this.resetAttackCooldown();
                    ((ChaosspawnEntity) this.mob).startDelayedAttack();
                }
            }

            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() 
                    && this.mob.distanceToSqr(entity) <= ((ChaosspawnEntity) this.mob).getAttackReachSqr(entity) 
                    && this.mob.getSensing().hasLineOfSight(entity);
            }
        });

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0) {
            @Override
            public boolean canUse() {
                return !ChaosspawnEntity.this.isAttacking() && super.canUse();
            }
        });

        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
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

        double rand = this.random.nextDouble();
        if (rand < 0.30) {
            this.currentAttackMaxTicks = 30; 
            this.attackAnimationTimer = 30;
            this.entityData.set(ANIMATION_STATE, 1);
        } else if (rand < 0.60) {
            this.currentAttackMaxTicks = 30; 
            this.attackAnimationTimer = 30;
            this.entityData.set(ANIMATION_STATE, 2);
        } else {
            this.currentAttackMaxTicks = 30; 
            this.attackAnimationTimer = 30;
            this.entityData.set(ANIMATION_STATE, 3);
        }

        this.getNavigation().stop();
        this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
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

                if (state == 1) {
                    if (elapsedTicks == 25) {
                        Vec3 look = this.getLookAngle();
                        Vec3 sweepCenter = this.position().add(look.scale(1.5));
                        AABB sweepAOE = new AABB(
                            sweepCenter.x - 2.0, sweepCenter.y - 1.0, sweepCenter.z - 2.0,
                            sweepCenter.x + 2.0, sweepCenter.y + 2.0, sweepCenter.z + 2.0
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, sweepAOE,
                            e -> e != this && e.isAlive() 
                                && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:shadowchaos_mobs"))));

                        for (LivingEntity target : targets) {
                            this.doHurtTarget(target);
                            Vec3 knockback = target.position().subtract(this.position()).normalize();
                            target.setDeltaMovement(target.getDeltaMovement().add(knockback.x * 0.5D, 0.1D, knockback.z * 0.5D));
                            target.hurtMarked = true;
                        }
                    }
                } 
                else if (state == 2) {
                    if (elapsedTicks == 17) {
                        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:havy_foot_steps")),
                            SoundSource.HOSTILE, 1.0F, 1.0F);
                    }

                    if (elapsedTicks == 25) {
                        Vec3 look = this.getLookAngle();
                        Vec3 center = this.position().add(look.scale(1.5));
                        AABB area = new AABB(
                            center.x - 1.5, center.y - 1.0, center.z - 1.5,
                            center.x + 1.5, center.y + 2.5, center.z + 1.5
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, area,
                            e -> e != this && e.isAlive() 
                                && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:shadowchaos_mobs"))));

                        for (LivingEntity target : targets) {
                            this.doHurtTarget(target);
                        }
                    }
                } 
                else if (state == 3) {
                    if (elapsedTicks == 5 || elapsedTicks == 15 || elapsedTicks == 25) {
                        Vec3 look = this.getLookAngle();
                        Vec3 sweepCenter = this.position().add(look.scale(1.2));
                        AABB rapidSweepAOE = new AABB(
                            sweepCenter.x - 1.8, sweepCenter.y - 1.0, sweepCenter.z - 1.8,
                            sweepCenter.x + 1.8, sweepCenter.y + 2.0, sweepCenter.z + 1.8
                        );

                        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, rapidSweepAOE,
                            e -> e != this && e.isAlive() 
                                && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:shadowchaos_mobs"))));

                        for (LivingEntity target : targets) {
                            this.doHurtTarget(target);
                        }
                    }
                }

                this.attackAnimationTimer--;
                if (this.attackAnimationTimer == 0) {
                    this.entityData.set(ANIMATION_STATE, 0); 
                }
            }

            if (this.horizontalCollision && this.level().getGameRules().getBoolean(net.minecraft.world.level.GameRules.RULE_MOBGRIEFING)) {
                boolean brokeAny = false;
                AABB aabb = this.getBoundingBox().inflate(0.2);
                for (BlockPos blockpos : BlockPos.betweenClosed(
                        Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ),
                        Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                    BlockState blockstate = this.level().getBlockState(blockpos);
                    if (!blockstate.isAir() && blockstate.getDestroySpeed(this.level(), blockpos) >= 0 && blockstate.getDestroySpeed(this.level(), blockpos) < 3.0F) {
                        brokeAny = this.level().destroyBlock(blockpos, true, this) || brokeAny;
                    }
                }
                if (brokeAny) {
                    this.level().levelEvent(null, 1022, this.blockPosition(), 0);
                }
            }
        } else {
            int state = this.entityData.get(ANIMATION_STATE);

            this.animationState0.animateWhen(true, this.tickCount);
            this.animationState1.animateWhen(true, this.tickCount);

            this.animationState3.animateWhen(state == 1, this.tickCount);
            this.animationState4.animateWhen(state == 2, this.tickCount);
            this.animationState5.animateWhen(state == 3, this.tickCount);
        }
    }

    @Override
    public void die(DamageSource damageSource) {
        super.die(damageSource);
        if (!this.level().isClientSide() && this.level().dimension() == Level.OVERWORLD) {
            this.spawnAtLocation(new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse("hammercraftfantasy:demon_essence"))));
        }
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:chaosspawn_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:chaosspawn_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:chaosspawn_dead"));
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
        event.register(HammercraftfantasyModEntities.CHAOSSPAWN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 200);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 18);
        builder = builder.add(Attributes.FOLLOW_RANGE, 16);
        builder = builder.add(Attributes.STEP_HEIGHT, 1.6);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        return builder;
    }
}