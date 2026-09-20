package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.hammercraftfantasy.procedures.NurgelmobsgethurtProcedure;
import net.mcreator.hammercraftfantasy.procedures.Entitys_trytoattack_playanimeProcedure;
import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

public class RotflyEntity extends Monster {
	public final AnimationState animationState0 = new AnimationState();
	public final AnimationState animationState1 = new AnimationState();
	public final AnimationState animationState2 = new AnimationState();

	public RotflyEntity(EntityType<RotflyEntity> type, Level world) {
		super(type, world);
		xpReward = 5;
		setNoAi(false);
		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<net.minecraft.world.entity.LivingEntity>(this, net.minecraft.world.entity.LivingEntity.class, 10, true, false, target -> target != null
				&& target.isAlive() && !target.getType().is(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, net.minecraft.resources.ResourceLocation.parse("hammercraftfantasy:nurgle_mobs")))) {
			private int lockTicks = 0;
			private int rescanTicks = 0;

			private boolean isTargetValid(net.minecraft.world.entity.LivingEntity e) {
				return e != null && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, net.minecraft.resources.ResourceLocation.parse("hammercraftfantasy:nurgle_mobs")));
			}

			@Override
			public boolean canUse() {
				net.minecraft.world.entity.LivingEntity lastHurtBy = this.mob.getLastHurtByMob();
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
					if (this.lockTicks > 0)
						return;
					net.minecraft.world.entity.LivingEntity currentTarget = this.mob.getTarget();
					if (currentTarget == null || !currentTarget.isAlive())
						return;
					double currentDistSqr = this.mob.distanceToSqr(currentTarget);
					net.minecraft.world.entity.LivingEntity closest = this.mob.level().getEntitiesOfClass(net.minecraft.world.entity.LivingEntity.class, this.mob.getBoundingBox().inflate(24.0), e -> isTargetValid(e)).stream()
							.min(java.util.Comparator.comparingDouble(e -> this.mob.distanceToSqr(e))).orElse(null);
					if (closest != null && closest != currentTarget && this.mob.distanceToSqr(closest) < currentDistSqr - 9.0) {
						this.mob.setTarget(closest);
						this.target = closest;
						this.lockTicks = 40;
					}
				}
			}
		});
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.8, false) {
			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < 4 && this.mob.getSensing().hasLineOfSight(entity);
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.8, 20) {
			@Override
			protected Vec3 getPosition() {
				RandomSource random = RotflyEntity.this.getRandom();
				double dir_x = RotflyEntity.this.getX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = RotflyEntity.this.getY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = RotflyEntity.this.getZ() + ((random.nextFloat() * 2 - 1) * 16);
				return new Vec3(dir_x, dir_y, dir_z);
			}
		});
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:rotfly_breathing"));
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:rotfly_moving")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:rotfly_hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:rotfly_death"));
	}

	@Override
	public boolean causeFallDamage(float l, float d, DamageSource source) {
		return false;
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Level world = this.level();
		Entity entity = this;
		Entity sourceentity = damagesource.getEntity();
		Entity immediatesourceentity = damagesource.getDirectEntity();

		NurgelmobsgethurtProcedure.execute(world, x, y, z);
		return super.hurt(damagesource, amount);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			this.animationState0.animateWhen(true, this.tickCount);
			this.animationState1.animateWhen(true, this.tickCount);
			this.animationState2.animateWhen(Entitys_trytoattack_playanimeProcedure.execute(this), this.tickCount);
		}
	}

	@Override
	public void travel(Vec3 dir) {
		this.travelFlying(dir);
	}

	private void travelFlying(Vec3 dir) {
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

	public void aiStep() {
		super.aiStep();
		this.setNoGravity(true);
	}

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(HammercraftfantasyModEntities.ROTFLY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.18);
		builder = builder.add(Attributes.MAX_HEALTH, 40);
		builder = builder.add(Attributes.ARMOR, 2);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 10);
		builder = builder.add(Attributes.FOLLOW_RANGE, 32);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		builder = builder.add(Attributes.FLYING_SPEED, 0.18);
		return builder;
	}
}