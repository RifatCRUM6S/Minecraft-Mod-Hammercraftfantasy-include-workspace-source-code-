package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.EventHooks;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.EnumSet;
import java.util.List;

public class DemonthrallEntity extends TamableAnimal {

    private int lifeTicks = 0;

    public DemonthrallEntity(EntityType<DemonthrallEntity> type, Level world) {
        super(type, world);
        xpReward = 20;
        setNoAi(false);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // --- 行为 Goals ---
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.6, true) {
            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() && this.mob.distanceToSqr(entity) <= 9.0D && this.mob.getSensing().hasLineOfSight(entity);
            }
        });
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.2, (float) 8, (float) 2));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        // --- 目标选择 Goals (优先级 1：最高优先级的预警保镖机制) ---
        this.targetSelector.addGoal(1, new ProtectOwnerFromAggroGoal(this)); // 仇恨预警保镖（主动预警）
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));        // 主人打谁它打谁
        this.targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));      // 主人挨打后反击
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));           // 自己受击后反击
    }

    /**
     * 自定义保镖 AI：主动检测正在锁定/仇恨主人的生物，并在其造成伤害前发起袭击
     */
    public static class ProtectOwnerFromAggroGoal extends TargetGoal {
        private final TamableAnimal tameMob;
        private LivingEntity targetCandidate;

        public ProtectOwnerFromAggroGoal(TamableAnimal tameMob) {
            super(tameMob, false);
            this.tameMob = tameMob;
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            if (!this.tameMob.isTame()) {
                return false;
            }

            LivingEntity owner = this.tameMob.getOwner();
            if (owner == null || !owner.isAlive()) {
                return false;
            }

            // 搜索主人周围 16 格范围内的所有 Mob
            double followRange = this.tameMob.getAttributeValue(Attributes.FOLLOW_RANGE);
            List<Mob> nearbyMobs = this.tameMob.level().getEntitiesOfClass(
                Mob.class, 
                owner.getBoundingBox().inflate(followRange, 8.0D, followRange)
            );

            for (Mob mob : nearbyMobs) {
                if (mob == this.tameMob) continue;
                
                // 核心判断：如果该生物存活、未与恶魔仆役同阵营，且当前的目标就是主人
                if (mob.isAlive() && !mob.isAlliedTo(this.tameMob) && mob.getTarget() == owner) {
                    this.targetCandidate = mob;
                    return true;
                }
            }

            return false;
        }

        @Override
        public void start() {
            this.mob.setTarget(this.targetCandidate);
            super.start();
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success) {
            this.heal(6.0F);
        }
        return success;
    }

    @Override
    public void awardKillScore(Entity killedEntity, int score, DamageSource damageSource) {
        super.awardKillScore(killedEntity, score, damageSource);
        this.setHealth(this.getMaxHealth());
    }

    @Override
    public void tick() {
        super.tick();

        this.lifeTicks++;

        if (this.lifeTicks >= 4800 && this.lifeTicks < 6000) {
            if (this.level().isClientSide() && this.random.nextFloat() < 0.3F) {
                double px = this.getX() + (this.random.nextDouble() - 0.5D) * 1.2D;
                double py = this.getY() + this.random.nextDouble() * 1.8D;
                double pz = this.getZ() + (this.random.nextDouble() - 0.5D) * 1.2D;

                this.level().addParticle(
                    ParticleTypes.ENCHANT,
                    px, py, pz,
                    (this.random.nextDouble() - 0.5D) * 0.5D,
                    this.random.nextDouble() * 0.5D,
                    (this.random.nextDouble() - 0.5D) * 0.5D
                );
            }
        }

        if (!this.level().isClientSide() && this.lifeTicks >= 6000) {
            if (this.isAlive()) {
                this.kill();
            }
        }
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:bloodletter_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:bloodletter_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:bloodletter_dead"));
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
    public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
        ItemStack itemstack = sourceentity.getItemInHand(hand);
        InteractionResult retval = InteractionResult.sidedSuccess(this.level().isClientSide());
        Item item = itemstack.getItem();
        if (itemstack.getItem() instanceof SpawnEggItem) {
            retval = super.mobInteract(sourceentity, hand);
        } else if (this.level().isClientSide()) {
            retval = (this.isTame() && this.isOwnedBy(sourceentity) || this.isFood(itemstack)) ? InteractionResult.sidedSuccess(this.level().isClientSide()) : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isOwnedBy(sourceentity)) {
                    if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.usePlayerItem(sourceentity, hand, itemstack);
                        FoodProperties foodproperties = itemstack.getFoodProperties(this);
                        float nutrition = foodproperties != null ? (float) foodproperties.nutrition() : 1;
                        this.heal(nutrition);
                        retval = InteractionResult.sidedSuccess(this.level().isClientSide());
                    } else if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.usePlayerItem(sourceentity, hand, itemstack);
                        this.heal(4);
                        retval = InteractionResult.sidedSuccess(this.level().isClientSide());
                    } else {
                        retval = super.mobInteract(sourceentity, hand);
                    }
                }
            } else if (this.isFood(itemstack)) {
                this.usePlayerItem(sourceentity, hand, itemstack);
                if (this.random.nextInt(3) == 0 && !EventHooks.onAnimalTame(this, sourceentity)) {
                    this.tame(sourceentity);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
                this.setPersistenceRequired();
                retval = InteractionResult.sidedSuccess(this.level().isClientSide());
            } else {
                retval = super.mobInteract(sourceentity, hand);
                if (retval == InteractionResult.SUCCESS || retval == InteractionResult.CONSUME)
                    this.setPersistenceRequired();
            }
        }
        return retval;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
        DemonthrallEntity retval = HammercraftfantasyModEntities.DEMONTHRALL.get().create(serverWorld);
        retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null);
        return retval;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 35);
        builder = builder.add(Attributes.ARMOR, 6);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 20);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 1);
        return builder;
    }
}