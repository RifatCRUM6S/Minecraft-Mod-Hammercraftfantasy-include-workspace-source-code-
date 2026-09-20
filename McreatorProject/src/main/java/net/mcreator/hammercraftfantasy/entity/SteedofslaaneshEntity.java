package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.EventHooks;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

public class SteedofslaaneshEntity extends TamableAnimal {
    public final AnimationState animationState0 = new AnimationState();

    public SteedofslaaneshEntity(EntityType<SteedofslaaneshEntity> type, Level world) {
        super(type, world);
        xpReward = 10;
        setNoAi(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<net.minecraft.world.entity.LivingEntity>(this, net.minecraft.world.entity.LivingEntity.class, 10, true, false, target -> target != null
                && target.isAlive() && !target.getType().is(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, net.minecraft.resources.ResourceLocation.parse("hammercraftfantasy:slaanesh_mobs")))) {
            private int lockTicks = 0;
            private int rescanTicks = 0;

            @Override
            public boolean canUse() {
                net.minecraft.world.entity.LivingEntity lastHurtBy = this.mob.getLastHurtByMob();
                if (lastHurtBy != null && lastHurtBy.isAlive() && lockTicks <= 0 && isTargetValid(lastHurtBy)) {
                    this.target = lastHurtBy;
                    this.lockTicks = 60; // 强行锁敌 3 秒防抽陀螺
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

            private boolean isTargetValid(net.minecraft.world.entity.LivingEntity e) {
                return e != null && e.isAlive() && !e.getType().is(net.minecraft.tags.TagKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, net.minecraft.resources.ResourceLocation.parse("hammercraftfantasy:slaanesh_mobs")));
            }
        });
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true) {
            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
            }
        });
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:steed_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:steed_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:steed_dead"));
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        if (damagesource.is(DamageTypes.FALL))
            return false;
        return super.hurt(damagesource, amount);
    }

    @Override
    public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
        ItemStack itemstack = sourceentity.getItemInHand(hand);

        // 1. 刷怪蛋直接走原版逻辑
        if (itemstack.getItem() instanceof SpawnEggItem) {
            return super.mobInteract(sourceentity, hand);
        }

        // 2. 拦截副手触发：彻底解决一次吃 2 个钻石问题
        if (hand == InteractionHand.OFF_HAND) {
            return InteractionResult.PASS;
        }

        // 3. 客户端直接返回 Success 播放手部动画，不跑任何数值/状态逻辑
        if (this.level().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // ==========================================
        //             1. 未驯服状态逻辑
        // ==========================================
        if (!this.isTame()) {
            // 条件：主手持钻石 + 按下 Shift 潜行
            if (itemstack.is(Items.DIAMOND) && sourceentity.isShiftKeyDown()) {

                // 生存模式精准扣除 1 个钻石
                if (!sourceentity.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                // 15% 概率驯服
                if (this.random.nextFloat() < 0.15F) {
                    this.tame(sourceentity);
                    
                    // 清空仇恨防打主人
                    this.setTarget(null);
                    this.setLastHurtByMob(null);

                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.HEART, this.getX(), this.getY() + 1.0, this.getZ(), 7, 0.3, 0.5, 0.3, 0.02);
                    }
                } else {
                    if (this.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY() + 1.0, this.getZ(), 5, 0.2, 0.3, 0.2, 0.02);
                    }
                }
            }

            // 关键：未驯服状态下，绝对不允许上马！
            sourceentity.stopRiding();
            return InteractionResult.SUCCESS;
        }

        // ==========================================
        //             2. 已驯服状态逻辑
        // ==========================================
        if (this.isOwnedBy(sourceentity)) {
            // 如果玩家已经在马背上，允许 Shift 键正常触发原版下马
            if (sourceentity.isPassenger() && sourceentity.getVehicle() == this) {
                return InteractionResult.PASS;
            }

            // 潜行右键：切换 坐下 / 站立
            if (sourceentity.isShiftKeyDown()) {
                this.setOrderedToSit(!this.isOrderedToSit());
                return InteractionResult.SUCCESS;
            } 
            // 普通右键：上马骑乘
            else {
                this.setOrderedToSit(false);
                sourceentity.startRiding(this);
                return InteractionResult.SUCCESS;
            }
        }

        return super.mobInteract(sourceentity, hand);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            this.animationState0.animateWhen(true, this.tickCount);
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
        SteedofslaaneshEntity retval = HammercraftfantasyModEntities.STEEDOFSLAANESH.get().create(serverWorld);
        if (retval != null) {
            retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null);
        }
        return retval;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public void travel(Vec3 dir) {
        Entity entity = this.getPassengers().isEmpty() ? null : (Entity) this.getPassengers().get(0);
        if (this.isVehicle()) {
            this.setYRot(entity.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(entity.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = entity.getYRot();
            this.yHeadRot = entity.getYRot();
            if (entity instanceof LivingEntity passenger) {
                this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float forward = passenger.zza;
                float strafe = passenger.xxa;
                super.travel(new Vec3(strafe, 0, forward));
            }
            double d1 = this.getX() - this.xo;
            double d0 = this.getZ() - this.zo;
            float f1 = (float) Math.sqrt(d1 * d1 + d0 * d0) * 4;
            if (f1 > 1.0F)
                f1 = 1.0F;
            this.walkAnimation.setSpeed(this.walkAnimation.speed() + (f1 - this.walkAnimation.speed()) * 0.4F);
            this.walkAnimation.position(this.walkAnimation.position() + this.walkAnimation.speed());
            this.calculateEntityAnimation(true);
            return;
        }
        super.travel(dir);
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
        event.register(HammercraftfantasyModEntities.STEEDOFSLAANESH.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.42);
        builder = builder.add(Attributes.MAX_HEALTH, 35);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 12);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 2);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
        return builder;
    }
}