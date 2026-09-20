package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;

import java.util.EnumSet;
import java.util.List;

public class KchaoswarriorEntity extends Monster {
    public static final EntityDataAccessor<Integer> ANIM_STATE = SynchedEntityData.defineId(KchaoswarriorEntity.class, EntityDataSerializers.INT);

    public final AnimationState animationState0 = new AnimationState(); // breathing
    public final AnimationState animationState2 = new AnimationState(); // attack_1
    public final AnimationState animationState3 = new AnimationState(); // attack_2
    public final AnimationState animationState4 = new AnimationState(); // attack_3

    private int lastAnimState = 0;
    private int currentAttackType = 0;

    public KchaoswarriorEntity(EntityType<KchaoswarriorEntity> type, Level world) {
        super(type, world);
        xpReward = 50;
        setNoAi(false);
        setPersistenceRequired();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANIM_STATE, 0);
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
        this.targetSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<>(
                this, LivingEntity.class, 24, true, false,
                target -> target != null && target.isAlive() 
                && !target.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs")))
        ));
        
        this.goalSelector.addGoal(1, new KchaosAttackGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:k_warrior_1"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.mace.smash_ground_heavy"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:k_warrior_2"));
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        if (damagesource.is(DamageTypes.IN_FIRE))
            return false;
        if (damagesource.is(DamageTypes.LIGHTNING_BOLT))
            return false;
        return super.hurt(damagesource, amount);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.animationState0.startIfStopped(this.tickCount);

            int state = getAnimState();
            if (state != this.lastAnimState) {
                this.animationState2.stop();
                this.animationState3.stop();
                this.animationState4.stop();

                if (state == 1) this.animationState2.start(this.tickCount);
                if (state == 2) this.animationState3.start(this.tickCount);
                if (state == 3) this.animationState4.start(this.tickCount);

                this.lastAnimState = state;
            }
        }
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
        builder = builder.add(Attributes.MAX_HEALTH, 50);
        builder = builder.add(Attributes.ARMOR, 28);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 20);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.STEP_HEIGHT, 1.5);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1);
        return builder;
    }

    private static class KchaosAttackGoal extends Goal {
        private final KchaoswarriorEntity mob;
        private final double speed;
        private int attackTicks = 0;
        private int totalTicks = 0;
        private int jumpCooldown = 0;
        private LivingEntity target;
        private boolean hasDealtDamage = false;
        private boolean hasLeaped = false;

        public KchaosAttackGoal(KchaoswarriorEntity mob, double speed) {
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
            this.attackTicks = 0;
            this.hasDealtDamage = false;
            this.hasLeaped = false;
        }

        @Override
        public void stop() {
            this.target = null;
            this.mob.currentAttackType = 0;
            this.mob.setAnimState(0);
            this.attackTicks = 0;
        }

        @Override
        public void tick() {
            if (this.jumpCooldown > 0) {
                this.jumpCooldown--;
            }

            this.target = this.mob.getTarget();
            if (this.target == null || !this.target.isAlive()) return;

            double dist = this.mob.distanceTo(this.target);

            if (this.mob.currentAttackType == 0) {
                if (dist <= 5.0D) {
                    chooseAndStartAttack(dist);
                } else {
                    this.mob.getNavigation().moveTo(this.target, this.speed);
                }
            } else {
                this.attackTicks++;

                // attack_1: 跳劈
                if (this.mob.currentAttackType == 1) {
                    this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

                    if (this.attackTicks < 7) {
                        this.mob.getNavigation().stop();
                    }
                    else if (this.attackTicks == 7 && !this.hasLeaped) {
                        this.hasLeaped = true;
                        Vec3 targetObliquePos = new Vec3(this.target.getX(), this.target.getY() + this.target.getEyeHeight() + 1.2D, this.target.getZ());
                        Vec3 mobPos = this.mob.position();
                        Vec3 jumpVector = targetObliquePos.subtract(mobPos).normalize().scale(0.85D);
                        this.mob.setDeltaMovement(jumpVector);
                        this.mob.hasImpulse = true;
                    }
                    else if (this.attackTicks == 17 && !this.hasDealtDamage) {
                        this.hasDealtDamage = true;
                        doAreaDamage(2.0D, 1.5F);
                    }

                    if (this.attackTicks >= 17) {
                        this.mob.getNavigation().stop();
                    }
                }
                // attack_2: 横劈 1 (横扫之刃范围攻击 + 特效)
                else if (this.mob.currentAttackType == 2) {
                    this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
                    this.mob.getNavigation().moveTo(this.target, this.speed);

                    if (this.attackTicks == 7 && !this.hasDealtDamage) {
                        this.hasDealtDamage = true;
                        doSweepAreaAttack(3.5D, 120.0D);
                    }
                }
                // attack_3: 横劈 2 (横扫之刃范围攻击 + 特效)
                else if (this.mob.currentAttackType == 3) {
                    this.mob.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
                    this.mob.getNavigation().moveTo(this.target, this.speed);

                    if (this.attackTicks == 9 && !this.hasDealtDamage) {
                        this.hasDealtDamage = true;
                        doSweepAreaAttack(3.5D, 120.0D);
                    }
                }

                if (this.attackTicks >= this.totalTicks) {
                    this.mob.currentAttackType = 0;
                    this.mob.setAnimState(0);
                    this.attackTicks = 0;
                    this.hasDealtDamage = false;
                    this.hasLeaped = false;
                }
            }
        }

        private void chooseAndStartAttack(double dist) {
            this.attackTicks = 0;
            this.hasDealtDamage = false;
            this.hasLeaped = false;

            if (dist > 2.5D && dist <= 5.0D) {
                if (this.jumpCooldown <= 0) {
                    startJumpAttack();
                    return;
                } else {
                    this.mob.getNavigation().moveTo(this.target, this.speed);
                    return;
                }
            }

            float roll = this.mob.random.nextFloat() * 100.0F;

            if (roll < 30.0F && this.jumpCooldown <= 0) {
                startJumpAttack();
            } else if (roll < 65.0F) {
                this.mob.currentAttackType = 2;
                this.mob.setAnimState(2);
                this.totalTicks = 16;
            } else {
                this.mob.currentAttackType = 3;
                this.mob.setAnimState(3);
                this.totalTicks = 18;
            }
        }

        private void startJumpAttack() {
            this.mob.currentAttackType = 1;
            this.mob.setAnimState(1);
            this.totalTicks = 28;
            this.jumpCooldown = 200;
        }

        private void doSweepAreaAttack(double radius, double arcAngleDeg) {
            // 播放横扫音效
            this.mob.level().playSound(null, this.mob.getX(), this.mob.getY(), this.mob.getZ(),
                    SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.HOSTILE, 1.0F, 1.0F);

            // 生成横扫粒子特效 (在前前方 1.5 格，胸口高度)
            if (this.mob.level() instanceof ServerLevel serverLevel) {
                double rad = Math.toRadians(this.mob.getYRot());
                double fx = this.mob.getX() - Mth.sin((float) rad) * 1.5D;
                double fy = this.mob.getY() + this.mob.getEyeHeight() * 0.75D;
                double fz = this.mob.getZ() + Mth.cos((float) rad) * 1.5D;

                serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, fx, fy, fz, 1, 0, 0, 0, 0);
            }

            float damage = (float) this.mob.getAttributeValue(Attributes.ATTACK_DAMAGE);
            Vec3 lookVec = this.mob.getLookAngle();

            // 搜索周围的敌对目标
            List<LivingEntity> targets = this.mob.level().getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(radius),
                    e -> e != this.mob && e.isAlive() && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs")))
            );

            for (LivingEntity e : targets) {
                Vec3 toTarget = e.position().subtract(this.mob.position()).normalize();
                double dot = lookVec.x * toTarget.x + lookVec.z * toTarget.z; // 忽略 Y 轴仅判断水平夹角
                double angle = Math.toDegrees(Math.acos(Mth.clamp(dot, -1.0D, 1.0D)));

                // 判断是否在前方扇形角度内
                if (angle <= arcAngleDeg / 2.0D) {
                    e.hurt(this.mob.damageSources().mobAttack(this.mob), damage);
                    // 附带横扫小击退
                    e.knockback(0.4F, Mth.sin(this.mob.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(this.mob.getYRot() * ((float) Math.PI / 180F)));
                }
            }
        }

        private void doAreaDamage(double radius, float multiplier) {
            this.mob.level().playSound(null, this.mob.getX(), this.mob.getY(), this.mob.getZ(),
                    SoundEvents.PLAYER_ATTACK_KNOCKBACK, SoundSource.HOSTILE, 1.2F, 0.8F);

            float damage = (float) this.mob.getAttributeValue(Attributes.ATTACK_DAMAGE) * multiplier;
            List<LivingEntity> targets = this.mob.level().getEntitiesOfClass(LivingEntity.class, this.mob.getBoundingBox().inflate(radius),
                    e -> e != this.mob && e.isAlive() && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs")))
            );

            for (LivingEntity e : targets) {
                e.hurt(this.mob.damageSources().mobAttack(this.mob), damage);
            }
        }
    }
}