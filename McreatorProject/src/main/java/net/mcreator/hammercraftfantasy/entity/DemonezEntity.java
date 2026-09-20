package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

public class DemonezEntity extends Monster {
    private int noHurtTicks = 0;

    public DemonezEntity(EntityType<DemonezEntity> type, Level world) {
        super(type, world);
        xpReward = 10;
        setNoAi(false);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        
        // 动态索敌 Goal：支持报复攻击 + 动态寻找最近目标 + 防抽陀螺锁敌
        this.targetSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<LivingEntity>(
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

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 2, true) {
            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
            }
        });
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    private static boolean isTargetValid(LivingEntity e) {
        return e != null
            && e.isAlive()
            && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:slaanesh_mobs")));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (!this.level().isClientSide()) {
            this.noHurtTicks = 0;
        }
        return super.hurt(source, amount);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        
        if (!this.level().isClientSide() && this.isAlive()) {
            this.noHurtTicks++;
            
            if (this.noHurtTicks % 20 == 0 && this.noHurtTicks >= 600) {
                if (this.level() instanceof ServerLevel serverLevel) {
                    var ubType = HammercraftfantasyModEntities.UNKNOW_BEAUTY.get();
                    if (ubType != null) {
                        net.minecraft.world.entity.Mob ub = (net.minecraft.world.entity.Mob) ubType.create(serverLevel);
                        if (ub != null) {
                            ub.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                            ub.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(ub.blockPosition()), MobSpawnType.CONVERSION, null);
                            
                            ub.setHealth(Math.min(this.getHealth(), ub.getMaxHealth()));
                            
                            if (this.getTarget() != null) {
                                ub.setTarget(this.getTarget());
                            }
                            
                            serverLevel.addFreshEntity(ub);
                            this.discard();
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:deamon_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:deamon_dead"));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:deamon_breath"));
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 12);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.STEP_HEIGHT, 1);
        return builder;
    }
}