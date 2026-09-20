package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.common.NeoForgeMod;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.projectile.LlamaSpit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;

import net.mcreator.hammercraftfantasy.procedures.NurgelmobsgethurtProcedure;
import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

public class NurglingEntity extends Monster {
    public final AnimationState animationState1 = new AnimationState();

    private static final EntityDataAccessor<Integer> ANIMATION_STATE = 
        SynchedEntityData.defineId(NurglingEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EVOLVE_TIMER = 
        SynchedEntityData.defineId(NurglingEntity.class, EntityDataSerializers.INT);

    private BlockPos targetChestPos = null;
    private Player trackingPlayer = null;
    
    // 成功寻宝状态机
    private int reactionStage = 0;
    private int reactionTimer = 0;

    // 失败（未找到宝箱）响应计时器
    private int failSpitTimer = 0;

    public NurglingEntity(EntityType<NurglingEntity> type, Level world) {
        super(type, world);
        xpReward = 3;
        setNoAi(false);
    }

    public boolean isEvolving() {
        return this.entityData.get(ANIMATION_STATE) == 1;
    }

    public int getEvolveTimer() {
        return this.entityData.get(EVOLVE_TIMER);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ANIMATION_STATE, 0);
        builder.define(EVOLVE_TIMER, -1);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1) {
            @Override
            public boolean canUse() {
                return NurglingEntity.this.targetChestPos == null && NurglingEntity.this.failSpitTimer <= 0 && super.canUse();
            }
        });
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new FloatGoal(this));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (this.isEvolving()) {
            return InteractionResult.FAIL;
        }

        // 1. 喂食腐肉 -> 触发成就 Cutelittleone 并寻找宝箱
        if (itemstack.is(Items.ROTTEN_FLESH)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            if (!this.level().isClientSide() && this.level() instanceof ServerLevel serverLevel) {
                // 解锁成就逻辑
                if (player instanceof ServerPlayer serverPlayer) {
                    AdvancementHolder advancement = serverLevel.getServer().getAdvancements()
                        .get(ResourceLocation.parse("hammercraftfantasy:cutelittleone"));
                    if (advancement != null) {
                        AdvancementProgress progress = serverPlayer.getAdvancements().getOrStartProgress(advancement);
                        if (!progress.isDone()) {
                            for (String criterion : progress.getRemainingCriteria()) {
                                serverPlayer.getAdvancements().award(advancement, criterion);
                            }
                        }
                    }
                }

                BlockPos foundChest = findNearestLootChest(serverLevel);

                this.trackingPlayer = player;

                if (foundChest != null) {
                    serverLevel.sendParticles(ParticleTypes.HEART, this.getX(), this.getY() + 0.5D, this.getZ(), 7, 0.3D, 0.3D, 0.3D, 0.1D);
                    
                    this.targetChestPos = foundChest;
                    this.failSpitTimer = 0;
                    
                    // 进入初始动作阶段 1：看向玩家并等待 10 刻
                    this.reactionStage = 1;
                    this.reactionTimer = 10;
                } else {
                    // 未成功获取宝箱：看向玩家，等待 20 刻后再吐口水
                    this.targetChestPos = null;
                    this.reactionStage = 0;
                    this.failSpitTimer = 20;
                }
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }

        // 2. 喂食“慈父的汤” -> 变身
        ResourceLocation soupId = ResourceLocation.parse("hammercraftfantasy:grandfatherssoup");
        if (BuiltInRegistries.ITEM.getKey(itemstack.getItem()).equals(soupId)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            if (!this.level().isClientSide()) {
                ServerLevel serverLevel = (ServerLevel) this.level();
                serverLevel.sendParticles(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY() + 0.5D, this.getZ(), 15, 0.4D, 0.4D, 0.4D, 0.1D);
                
                this.entityData.set(ANIMATION_STATE, 1);
                this.entityData.set(EVOLVE_TIMER, 160);
                this.setInvulnerable(true);
                this.stopTreasureHunting();
                this.getNavigation().stop();
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void tick() {
        super.tick();

        boolean evolving = this.isEvolving();

        if (this.level().isClientSide()) {
            if (evolving) {
                this.animationState1.startIfStopped(this.tickCount);
            } else {
                this.animationState1.stop();
            }
            return;
        }

        ServerLevel serverLevel = (ServerLevel) this.level();

        // 变身状态逻辑
        if (evolving) {
            this.getNavigation().stop();
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);

            int timer = this.getEvolveTimer();
            if (timer > 0) {
                float progress = 1.0f - ((float) timer / 160.0f);
                float scaleFactor = 1.0f + (progress * progress * 5.0f);

                double radius = 0.8D * scaleFactor;
                double height = 1.2D * scaleFactor;
                int particleCount = (int) (5 * scaleFactor);

                serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK, 
                    this.getX() + (this.random.nextDouble() - 0.5D) * radius, 
                    this.getY() + this.random.nextDouble() * height, 
                    this.getZ() + (this.random.nextDouble() - 0.5D) * radius, 
                    particleCount, radius * 0.2D, height * 0.2D, radius * 0.2D, 0.05D);

                serverLevel.sendParticles(ParticleTypes.SNEEZE, 
                    this.getX() + (this.random.nextDouble() - 0.5D) * radius, 
                    this.getY() + this.random.nextDouble() * height, 
                    this.getZ() + (this.random.nextDouble() - 0.5D) * radius, 
                    (int) (3 * scaleFactor), radius * 0.1D, height * 0.1D, radius * 0.1D, 0.02D);

                this.entityData.set(EVOLVE_TIMER, timer - 1);

                if (timer - 1 == 0) {
                    double shockwaveRadius = 10.0D;

                    serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), 
                        SoundEvents.GENERIC_EXPLODE.value(), SoundSource.HOSTILE, 3.0F, 0.6F);

                    serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, 
                        this.getX(), this.getY() + 1.5D, this.getZ(), 1, 0, 0, 0, 0);
                    serverLevel.sendParticles(ParticleTypes.POOF, 
                        this.getX(), this.getY() + 1.0D, this.getZ(), 100, 2.5D, 2.5D, 2.5D, 0.25D);

                    for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(shockwaveRadius))) {
                        if (entity != this) {
                            double dx = entity.getX() - this.getX();
                            double dz = entity.getZ() - this.getZ();
                            double dist = Math.sqrt(dx * dx + dz * dz);
                            
                            if (dist > 0 && dist < shockwaveRadius) {
                                double knockbackPower = (1.0D - dist / shockwaveRadius) * 2.8D; 
                                entity.push(dx / dist * knockbackPower, 0.6D + (knockbackPower * 0.15D), dz / dist * knockbackPower);
                                entity.hurtMarked = true;
                            }
                        }
                    }

                    GreatuncleanoneEntity boss = new GreatuncleanoneEntity(
                        (EntityType<GreatuncleanoneEntity>) BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:greatuncleanone")), 
                        serverLevel
                    );
                    boss.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                    serverLevel.addFreshEntity(boss);
                    
                    this.discard();
                }
            }
            return;
        }

        // 处理未找到宝箱时的“注视20刻再吐口水”逻辑
        if (this.failSpitTimer > 0) {
            if (this.trackingPlayer != null && this.trackingPlayer.isAlive()) {
                this.getLookControl().setLookAt(this.trackingPlayer, 30.0F, 30.0F);
            }
            this.getNavigation().stop();

            this.failSpitTimer--;
            if (this.failSpitTimer == 0) {
                // 等待 20 刻结束，正式吐口水
                serverLevel.sendParticles(ParticleTypes.ANGRY_VILLAGER, this.getX(), this.getY() + 0.6D, this.getZ(), 5, 0.3D, 0.3D, 0.3D, 0.0D);
                serverLevel.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.NEUTRAL, 1.0F, 1.0F);

                if (this.trackingPlayer != null && this.trackingPlayer.isAlive()) {
                    LlamaSpit spit = EntityType.LLAMA_SPIT.create(serverLevel);
                    if (spit != null) {
                        spit.setOwner(this);
                        spit.setPos(this.getX(), this.getEyeY() - 0.1D, this.getZ());
                        double dx = this.trackingPlayer.getX() - this.getX();
                        double dy = this.trackingPlayer.getY(0.33D) - spit.getY();
                        double dz = this.trackingPlayer.getZ() - this.getZ();
                        double dist = Math.sqrt(dx * dx + dz * dz);
                        spit.shoot(dx, dy + dist * 0.2D, dz, 1.2F, 10.0F);
                        serverLevel.addFreshEntity(spit);
                    }
                    this.trackingPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40, 0));
                }
                this.trackingPlayer = null;
            }
            return;
        }

        // 成功寻宝流程逻辑
        if (this.targetChestPos != null) {
            // 阶段性动作响应状态机
            if (this.reactionStage > 0) {
                if (this.trackingPlayer != null && this.trackingPlayer.isAlive()) {
                    this.getLookControl().setLookAt(this.trackingPlayer, 30.0F, 30.0F);
                }

                switch (this.reactionStage) {
                    case 1: // 扫描到后看向玩家，等待 10 刻
                        if (--this.reactionTimer <= 0) {
                            if (this.onGround()) {
                                this.jumpFromGround();
                                this.reactionStage = 2; // 进入阶段 2：等待第 1 次跳跃落地
                            }
                        }
                        break;
                    case 2: // 检查第 1 次跳跃落地
                        if (this.onGround()) {
                            this.reactionStage = 3;
                            this.reactionTimer = 10; // 落地后等待 10 刻
                        }
                        break;
                    case 3: // 落地后等待 10 刻，然后跳第 2 下
                        if (--this.reactionTimer <= 0) {
                            if (this.onGround()) {
                                this.jumpFromGround();
                                this.reactionStage = 4; // 进入阶段 4：等待第 2 次跳跃落地
                            }
                        }
                        break;
                    case 4: // 检查第 2 次跳跃落地
                        if (this.onGround()) {
                            this.reactionStage = 5;
                            this.reactionTimer = 10; // 落地后等待 10 刻
                        }
                        break;
                    case 5: // 第 2 次落地后等待 10 刻，完成后开始行动
                        if (--this.reactionTimer <= 0) {
                            this.reactionStage = 0; // 反应动作结束
                            if (this.getAttribute(Attributes.FOLLOW_RANGE) != null) {
                                this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(256.0D);
                            }
                            this.getNavigation().moveTo(this.targetChestPos.getX(), this.targetChestPos.getY(), this.targetChestPos.getZ(), 1.25D);
                        }
                        break;
                }
                return; // 反应阶段未结束前，暂停后续的移动和指示粒子更新
            }

            // 反应动作结束后：正式出发走向宝箱
            if (this.tickCount % 6 == 0) {
                spawnSparseIndicatorParticles(serverLevel, this.blockPosition(), this.targetChestPos);
            }

            if (this.blockPosition().closerThan(this.targetChestPos, 4.0D)) {
                this.stopTreasureHunting();
            } else if (this.tickCount % 10 == 0 || this.getNavigation().isDone()) {
                this.getNavigation().moveTo(this.targetChestPos.getX(), this.targetChestPos.getY(), this.targetChestPos.getZ(), 1.25D);
            }
        }
    }

    private BlockPos findNearestLootChest(ServerLevel level) {
        ChunkPos currentChunk = new ChunkPos(this.blockPosition());
        BlockPos closestChest = null;
        double minDistanceSq = Double.MAX_VALUE;

        for (int cx = -16; cx <= 16; cx++) {
            for (int cz = -16; cz <= 16; cz++) {
                int chunkX = currentChunk.x + cx;
                int chunkZ = currentChunk.z + cz;

                if (level.hasChunk(chunkX, chunkZ)) {
                    LevelChunk chunk = level.getChunk(chunkX, chunkZ);
                    for (BlockEntity be : chunk.getBlockEntities().values()) {
                        if (be instanceof RandomizableContainerBlockEntity container && container.getLootTable() != null) {
                            double distSq = this.distanceToSqr(be.getBlockPos().getX() + 0.5D, be.getBlockPos().getY() + 0.5D, be.getBlockPos().getZ() + 0.5D);
                            if (distSq < minDistanceSq) {
                                minDistanceSq = distSq;
                                closestChest = be.getBlockPos();
                            }
                        }
                    }
                }
            }
        }
        return closestChest;
    }

    private void spawnSparseIndicatorParticles(ServerLevel level, BlockPos start, BlockPos end) {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        double dz = end.getZ() - start.getZ();
        
        for (int i = 1; i <= 3; i++) {
            double factor = i / 4.0D;
            level.sendParticles(ParticleTypes.HAPPY_VILLAGER, 
                start.getX() + 0.5D + dx * factor, 
                start.getY() + 0.5D + dy * factor, 
                start.getZ() + 0.5D + dz * factor, 
                1, 0.15D, 0.15D, 0.15D, 0.0D);
        }
    }

    private void stopTreasureHunting() {
        this.targetChestPos = null;
        this.trackingPlayer = null;
        this.reactionStage = 0;
        this.reactionTimer = 0;
        this.failSpitTimer = 0;
        if (this.getAttribute(Attributes.FOLLOW_RANGE) != null) {
            this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(24.0D);
        }
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:nurgling_laught"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:nurgling_hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:nurgling_death"));
    }

    @Override
    public boolean hurt(DamageSource damagesource, float amount) {
        if (this.isEvolving()) {
            return false;
        }

        if (this.targetChestPos != null || this.failSpitTimer > 0) {
            this.stopTreasureHunting();
        }

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Level world = this.level();

        NurgelmobsgethurtProcedure.execute(world, x, y, z);

        if (damagesource.getDirectEntity() instanceof ThrownPotion || damagesource.getDirectEntity() instanceof AreaEffectCloud || damagesource.typeHolder().is(NeoForgeMod.POISON_DAMAGE))
            return false;
        if (damagesource.is(DamageTypes.DROWN))
            return false;
        if (damagesource.is(DamageTypes.WITHER) || damagesource.is(DamageTypes.WITHER_SKULL))
            return false;
        return super.hurt(damagesource, amount);
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
        event.register(HammercraftfantasyModEntities.NURGLING.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.2);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        return builder;
    }
}