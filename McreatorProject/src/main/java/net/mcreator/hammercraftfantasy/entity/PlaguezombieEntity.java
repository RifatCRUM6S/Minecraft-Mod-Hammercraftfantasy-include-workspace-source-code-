package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.hammercraftfantasy.procedures.NurgelmobsgethurtProcedure;
import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

public class PlaguezombieEntity extends Monster {
	public PlaguezombieEntity(EntityType<PlaguezombieEntity> type, Level world) {
		super(type, world);
		xpReward = 3;
		setNoAi(false);
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
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
			@Override
			protected boolean canPerformAttack(LivingEntity entity) {
				return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(4, new FloatGoal(this));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.drowned.ambient"));
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.drowned.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.drowned.death"));
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

	public static void init(RegisterSpawnPlacementsEvent event) {
		event.register(HammercraftfantasyModEntities.PLAGUEZOMBIE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
				RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.16);
		builder = builder.add(Attributes.MAX_HEALTH, 30);
		builder = builder.add(Attributes.ARMOR, 2);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 10);
		builder = builder.add(Attributes.FOLLOW_RANGE, 16);
		builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
		return builder;
	}
}