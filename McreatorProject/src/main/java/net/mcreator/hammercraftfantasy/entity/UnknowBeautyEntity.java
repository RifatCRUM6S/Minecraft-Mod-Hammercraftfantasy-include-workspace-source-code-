package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.EnumSet;
import java.util.List;

public class UnknowBeautyEntity extends Monster {
    private static final TagKey<EntityType<?>> SLAANESH_MOBS = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:slaanesh_mobs"));

    public UnknowBeautyEntity(EntityType<UnknowBeautyEntity> type, Level world) {
        super(type, world);
        xpReward = 10;
        setNoAi(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new UBTryToTurnGoal(this));
        this.goalSelector.addGoal(1, new FloatGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false,
                (entity) -> {
                    if (entity == null || !entity.isAlive() || entity.getType().is(SLAANESH_MOBS)) {
                        return false;
                    }
                    if (entity instanceof net.minecraft.world.entity.decoration.ArmorStand) {
                        return false;
                    }
                    if (entity instanceof Player player) {
                        return !player.isCreative() && !player.isSpectator();
                    }
                    return true;
                }
        ));

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, true) {
            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
            }
        });
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    private static class UBTryToTurnGoal extends Goal {
        private final UnknowBeautyEntity ub;
        private LivingEntity targetEntity;

        public UBTryToTurnGoal(UnknowBeautyEntity ub) {
            this.ub = ub;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            if (this.ub.tickCount % 5 != 0 || !this.ub.isAlive()) {
                return false;
            }

            if (this.ub.level() instanceof ServerLevel serverLevel) {
                AABB searchBox = new AABB(
                        this.ub.getX() - 8.0D, this.ub.getY() - 4.0D, this.ub.getZ() - 8.0D,
                        this.ub.getX() + 8.0D, this.ub.getY() + 4.0D, this.ub.getZ() + 8.0D
                );

                List<LivingEntity> entities = serverLevel.getEntitiesOfClass(LivingEntity.class, searchBox, (entity) -> {
                    if (entity == this.ub || !entity.isAlive() || entity.getType().is(UnknowBeautyEntity.SLAANESH_MOBS)) {
                        return false;
                    }
                    if (entity instanceof net.minecraft.world.entity.decoration.ArmorStand) {
                        return false;
                    }
                    if (entity instanceof Player player) {
                        return !player.isCreative() && !player.isSpectator();
                    }
                    return true;
                });

                if (!entities.isEmpty()) {
                    this.targetEntity = entities.stream()
                            .min((e1, e2) -> Double.compare(this.ub.distanceToSqr(e1), this.ub.distanceToSqr(e2)))
                            .orElse(null);
                } else {
                    this.targetEntity = null;
                }
            }

            if (this.targetEntity != null && this.targetEntity.isAlive()) {
                boolean inRange = this.ub.distanceToSqr(this.targetEntity) <= 64.0D;
                boolean hasSight = this.ub.getSensing().hasLineOfSight(this.targetEntity);
                return inRange && hasSight;
            }
            return false;
        }

        @Override
        public void start() {
            if (this.ub.level() instanceof ServerLevel serverLevel) {
                var demonezType = HammercraftfantasyModEntities.DEMONEZ.get();
                if (demonezType != null) {
                    Mob demonez = (Mob) demonezType.create(serverLevel);
                    if (demonez != null) {
                        demonez.moveTo(ub.getX(), ub.getY(), ub.getZ(), ub.getYRot(), ub.getXRot());
                        demonez.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(demonez.blockPosition()), MobSpawnType.CONVERSION, null);
                        demonez.setTarget(this.targetEntity);
                        serverLevel.addFreshEntity(demonez);
                        this.ub.discard();
                    }
                }
            }
        }
    }

    @Override
    public Vec3 getPassengerRidingPosition(Entity entity) {
        return super.getPassengerRidingPosition(entity).add(0, -0.35F, 0);
    }

    // 🌟 空闲时随机播放的呼吸音效
    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:ub_breath"));
    }

    // 🌟 受伤音效（受伤后会立刻变身，因此这也是变身瞬间的音效）
    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:ub_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.generic.death"));
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        boolean hurtSuccess = super.hurt(damagesource, amount);
        if (!hurtSuccess) return false;

        float remainHealth = this.getHealth();

        if (!this.level().isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) this.level();
            var demonezType = HammercraftfantasyModEntities.DEMONEZ.get();

            if (demonezType != null) {
                Mob demonez = (Mob) demonezType.create(serverLevel);
                if (demonez != null) {
                    demonez.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                    demonez.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(this.blockPosition()), MobSpawnType.CONVERSION, null);
                    demonez.setHealth(remainHealth);
                    this.playHurtSound(damagesource);
                    serverLevel.addFreshEntity(demonez);
                    this.discard();
                }
            }
        }
        return hurtSuccess;
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
        event.register(HammercraftfantasyModEntities.UNKNOW_BEAUTY.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (
                        world.getDifficulty() != Difficulty.PEACEFUL
                                && Monster.isDarkEnoughToSpawn(world, pos, random)
                                && world.getFluidState(pos).isEmpty()
                                && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)
                ),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.18);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 12);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        return builder;
    }
}