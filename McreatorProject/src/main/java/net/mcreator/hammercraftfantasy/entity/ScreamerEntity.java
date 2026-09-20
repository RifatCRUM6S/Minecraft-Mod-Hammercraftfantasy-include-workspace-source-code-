package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.EnumSet;
import java.util.List;

public class ScreamerEntity extends Monster {
    public final AnimationState animationState0 = new AnimationState();

    // 状态机：盘旋 / 俯冲
    enum AttackPhase {
        CIRCLE,
        SWOOP
    }

    private AttackPhase attackPhase = AttackPhase.CIRCLE;
    private BlockPos anchorPoint = BlockPos.ZERO;
    private Vec3 moveTargetPoint = Vec3.ZERO;

    public ScreamerEntity(EntityType<ScreamerEntity> type, Level world) {
        super(type, world);
        this.xpReward = 10;
        this.setNoAi(false);
        this.moveControl = new ScreamerMoveControl(this);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        return new FlyingPathNavigation(this, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new ScreamerAttackGroupGoal());
        this.goalSelector.addGoal(2, new ScreamerSweepAttackGoal());
        this.goalSelector.addGoal(3, new ScreamerCircleAroundAnchorGoal());

        // 排除拥有 hammercraftfantasy:tzeentch_mobs 标签的实体
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false,
                target -> target != null && target.isAlive() && !target.getType().is(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs")))));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:screamer_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:screamer_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:screamer_dead"));
    }

    @Override
    public boolean causeFallDamage(float l, float d, DamageSource source) {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.animationState0.animateWhen(true, this.tickCount);
        }
    }

    @Override
    public void travel(Vec3 dir) {
        if (this.isInWater()) {
            this.moveRelative(0.02F, dir);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.8));
        } else if (this.isInLava()) {
            this.moveRelative(0.02F, dir);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.5));
        } else {
            this.moveRelative((float) this.getAttributeValue(Attributes.FLYING_SPEED), dir);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.91));
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
    }

    @Override
    public void setNoGravity(boolean ignored) {
        super.setNoGravity(true);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.setNoGravity(true);
    }

    // ==========================================
    // 1. 自定义平滑飞行控制器
    // ==========================================
    class ScreamerMoveControl extends MoveControl {
        private float speed = 0.1F;

        public ScreamerMoveControl(Mob mob) {
            super(mob);
        }

        @Override
        public void tick() {
            if (ScreamerEntity.this.horizontalCollision) {
                ScreamerEntity.this.setYRot(ScreamerEntity.this.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d0 = ScreamerEntity.this.moveTargetPoint.x - ScreamerEntity.this.getX();
            double d1 = ScreamerEntity.this.moveTargetPoint.y - ScreamerEntity.this.getY();
            double d2 = ScreamerEntity.this.moveTargetPoint.z - ScreamerEntity.this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);

            if (Math.abs(d3) > 1.0E-5D) {
                double d4 = 1.0D - Math.abs(d1 * 0.7D) / d3;
                d0 *= d4;
                d2 *= d4;
                d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);

                float f = ScreamerEntity.this.getYRot();
                float f1 = (float) Mth.atan2(d2, d0);
                float f2 = Mth.wrapDegrees(ScreamerEntity.this.getYRot() + 90.0F);
                float f3 = Mth.wrapDegrees(f1 * (180.0F / (float) Math.PI));

                ScreamerEntity.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                ScreamerEntity.this.yBodyRot = ScreamerEntity.this.getYRot();

                if (Mth.degreesDifferenceAbs(f, ScreamerEntity.this.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float f4 = (float) (-(Mth.atan2(-d1, d3) * (180.0F / (float) Math.PI)));
                ScreamerEntity.this.setXRot(f4);

                float f5 = ScreamerEntity.this.getYRot() + 90.0F;
                double d6 = (double) (this.speed * Mth.cos(f5 * ((float) Math.PI / 180.0F))) * Math.abs(d0 / d5);
                double d7 = (double) (this.speed * Mth.sin(f5 * ((float) Math.PI / 180.0F))) * Math.abs(d2 / d5);
                double d8 = (double) (this.speed * Mth.sin(f4 * ((float) Math.PI / 180.0F))) * Math.abs(d1 / d5);

                Vec3 vec3 = ScreamerEntity.this.getDeltaMovement();
                ScreamerEntity.this.setDeltaMovement(vec3.add((new Vec3(d6, d8, d7)).subtract(vec3).scale(0.2D)));
            }
        }
    }

    // ==========================================
    // 2. 群体共享目标 Goal
    // ==========================================
    class ScreamerAttackGroupGoal extends Goal {
        private int nextScanTick;

        @Override
        public boolean canUse() {
            LivingEntity target = ScreamerEntity.this.getTarget();
            return target != null && target.isAlive();
        }

        @Override
        public void start() {
            this.nextScanTick = this.adjustedTickDelay(10);
        }

        @Override
        public void stop() {
            ScreamerEntity.this.anchorPoint = ScreamerEntity.this.level()
                    .getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, ScreamerEntity.this.anchorPoint)
                    .above(10 + ScreamerEntity.this.random.nextInt(20));
        }

        @Override
        public void tick() {
            if (ScreamerEntity.this.attackPhase == AttackPhase.CIRCLE) {
                --this.nextScanTick;
                if (this.nextScanTick <= 0) {
                    this.nextScanTick = this.adjustedTickDelay(60);
                    List<ScreamerEntity> list = ScreamerEntity.this.level().getEntitiesOfClass(
                            ScreamerEntity.class,
                            ScreamerEntity.this.getBoundingBox().inflate(16.0D),
                            screamer -> screamer != ScreamerEntity.this
                    );
                    for (ScreamerEntity screamer : list) {
                        screamer.setTarget(ScreamerEntity.this.getTarget());
                    }
                }
            }
        }
    }

    // ==========================================
    // 3. 高空盘旋与俯冲触发 Goal
    // ==========================================
    class ScreamerCircleAroundAnchorGoal extends Goal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        public ScreamerCircleAroundAnchorGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return ScreamerEntity.this.getTarget() == null || ScreamerEntity.this.attackPhase == AttackPhase.CIRCLE;
        }

        @Override
        public void start() {
            this.distance = 5.0F + ScreamerEntity.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + ScreamerEntity.this.random.nextFloat() * 9.0F;
            this.clockwise = ScreamerEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNextObject();
        }

        @Override
        public void tick() {
            if (ScreamerEntity.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = -4.0F + ScreamerEntity.this.random.nextFloat() * 9.0F;
            }

            if (ScreamerEntity.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (ScreamerEntity.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = ScreamerEntity.this.random.nextFloat() * 2.0F * (float) Math.PI;
                this.selectNextObject();
            }

            if (this.touchingTarget()) {
                this.selectNextObject();
            }

            if (ScreamerEntity.this.moveTargetPoint.y < ScreamerEntity.this.getY() && !ScreamerEntity.this.level().isEmptyBlock(ScreamerEntity.this.blockPosition().below(2))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNextObject();
            }

            if (ScreamerEntity.this.moveTargetPoint.y > ScreamerEntity.this.getY() && !ScreamerEntity.this.level().isEmptyBlock(ScreamerEntity.this.blockPosition().above(2))) {
                this.height = min(-1.0F, this.height);
                this.selectNextObject();
            }

            // 锁定目标后概率触发俯冲逻辑
            LivingEntity target = ScreamerEntity.this.getTarget();
            if (target != null && target.isAlive()) {
                ScreamerEntity.this.anchorPoint = target.blockPosition().above(10 + ScreamerEntity.this.random.nextInt(10));
                
                if (ScreamerEntity.this.random.nextInt(this.adjustedTickDelay(100)) == 0 && ScreamerEntity.this.distanceToSqr(target) < 4096.0D) {
                    ScreamerEntity.this.attackPhase = AttackPhase.SWOOP;
                }
            }
        }

        private float min(float a, float b) {
            return Math.min(a, b);
        }

        private void selectNextObject() {
            if (BlockPos.ZERO.equals(ScreamerEntity.this.anchorPoint)) {
                ScreamerEntity.this.anchorPoint = ScreamerEntity.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * ((float) Math.PI / 180.0F);
            ScreamerEntity.this.moveTargetPoint = Vec3.atLowerCornerOf(ScreamerEntity.this.anchorPoint).add(
                    (double) (this.distance * Mth.cos(this.angle)),
                    (double) (this.height),
                    (double) (this.distance * Mth.sin(this.angle))
            );
        }

        private boolean touchingTarget() {
            return ScreamerEntity.this.moveTargetPoint.distanceToSqr(ScreamerEntity.this.getX(), ScreamerEntity.this.getY(), ScreamerEntity.this.getZ()) < 4.0D;
        }
    }

    // ==========================================
    // 4. 俯冲冲刺与伤害 Goal
    // ==========================================
    class ScreamerSweepAttackGoal extends Goal {

        public ScreamerSweepAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return ScreamerEntity.this.getTarget() != null && ScreamerEntity.this.attackPhase == AttackPhase.SWOOP;
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity target = ScreamerEntity.this.getTarget();
            if (target == null || !target.isAlive()) {
                return false;
            } else {
                return ScreamerEntity.this.attackPhase == AttackPhase.SWOOP;
            }
        }

        @Override
        public void stop() {
            ScreamerEntity.this.setTarget(null);
            ScreamerEntity.this.attackPhase = AttackPhase.CIRCLE;
        }

        @Override
        public void tick() {
            LivingEntity target = ScreamerEntity.this.getTarget();
            if (target != null) {
                ScreamerEntity.this.moveTargetPoint = new Vec3(target.getX(), target.getY(0.5D), target.getZ());
                
                if (ScreamerEntity.this.getBoundingBox().inflate(0.2D).intersects(target.getBoundingBox())) {
                    ScreamerEntity.this.doHurtTarget(target);
                    ScreamerEntity.this.attackPhase = AttackPhase.CIRCLE;
                } else if (ScreamerEntity.this.horizontalCollision || ScreamerEntity.this.hurtTime > 0) {
                    ScreamerEntity.this.attackPhase = AttackPhase.CIRCLE;
                }
            }
        }
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
        event.register(HammercraftfantasyModEntities.SCREAMER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
        builder = builder.add(Attributes.MAX_HEALTH, 30);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 18);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        builder = builder.add(Attributes.FLYING_SPEED, 0.25);
        return builder;
    }
}