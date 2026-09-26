package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import java.util.List;

public class MaggotlordEntity extends Monster {
    public final AnimationState animationState0 = new AnimationState();
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), ServerBossEvent.BossBarColor.GREEN, ServerBossEvent.BossBarOverlay.PROGRESS);

    public MaggotlordEntity(EntityType<MaggotlordEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Villager.class, 0, false, false, 
            target -> target != null && target.isAlive()));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, false, false, 
            target -> target != null && target.isAlive() 
                && !(target instanceof Villager) 
                && !target.getType().is(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:nurgle_mobs")))));

        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));

        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, false) {
            @Override
            public void start() {
                super.start();
                if (MaggotlordEntity.this.getTarget() instanceof Villager) {
                    this.mob.setSpeed((float) (MaggotlordEntity.this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 2));
                }
            }

            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() 
                    && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth());
            }
        });

        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new FloatGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            if (this.tickCount % 5 == 0) {
                double followRange = this.getAttributeValue(Attributes.FOLLOW_RANGE);
                List<Villager> villagers = this.level().getEntitiesOfClass(
                    Villager.class, 
                    this.getBoundingBox().inflate(followRange), 
                    Entity::isAlive
                );

                if (!villagers.isEmpty()) {
                    Villager nearestVillager = villagers.get(0);
                    double minDistanceSq = this.distanceToSqr(nearestVillager);
                    for (Villager v : villagers) {
                        double distSq = this.distanceToSqr(v);
                        if (distSq < minDistanceSq) {
                            minDistanceSq = distSq;
                            nearestVillager = v;
                        }
                    }

                    if (this.getTarget() != nearestVillager) {
                        this.setTarget(nearestVillager);
                    }
                }
            }

            if (this.getTarget() != null && this.tickCount % 10 == 0) {
                destroyBlocksInAABB();
            }
        } else {
            this.animationState0.animateWhen(true, this.tickCount);
        }
    }

    private void destroyBlocksInAABB() {
        int feetY = this.blockPosition().getY();

        int minX = (int) Math.floor(this.getBoundingBox().minX - 0.5);
        int maxX = (int) Math.ceil(this.getBoundingBox().maxX + 0.5);
        int minY = feetY; 
        int maxY = (int) Math.ceil(this.getBoundingBox().maxY + 1.0);
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
    public boolean killedEntity(ServerLevel world, LivingEntity victim) {
        boolean result = super.killedEntity(world, victim);

        if (victim instanceof Villager) {
            double x = victim.getX();
            double y = victim.getY();
            double z = victim.getZ();

            world.sendParticles(ParticleTypes.EXPLOSION, x, y + 1.0, z, 1, 0.0, 0.0, 0.0, 0.0);
            world.playSound(null, x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 1.0F, 1.0F);

            EntityType<?> tamurkhanType = BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:tamurkhan"));
            if (tamurkhanType != null) {
                Entity entity = tamurkhanType.create(world);
                if (entity instanceof TamurkhanEntity tamurkhan) {
                    tamurkhan.moveTo(x, y, z, victim.getYRot(), victim.getXRot());
                    
                    float maxHealth = tamurkhan.getMaxHealth();
                    tamurkhan.setHealth(maxHealth / 3.0F);

                    tamurkhan.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 12000, 0));

                    world.addFreshEntity(tamurkhan);
                }
            }

            this.discard();
        }

        return result;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.silverfish.ambient"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.silverfish.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.silverfish.death"));
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
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.28);
        builder = builder.add(Attributes.MAX_HEALTH, 70);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 14);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        builder = builder.add(Attributes.STEP_HEIGHT, 2.0);
        return builder;
    }
}