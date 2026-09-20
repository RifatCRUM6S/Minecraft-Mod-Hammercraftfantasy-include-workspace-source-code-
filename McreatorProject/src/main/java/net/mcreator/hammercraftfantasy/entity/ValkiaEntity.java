package net.mcreator.hammercraftfantasy.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ColorParticleOption;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.List;

public class ValkiaEntity extends Monster {

    // 0: GROUND, 1: LAND_TO_AIR, 2: FLYING, 3: AIR_TO_LAND
    private static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(ValkiaEntity.class, EntityDataSerializers.INT);
    // 0: none, 1: g_attack1, 2: g_attack2, 3: g_charge, 4: g_defend, 5: f_hover, 6: f_charge, 7: f_range, 8: f_hitground
    private static final EntityDataAccessor<Integer> ACTION_ID = SynchedEntityData.defineId(ValkiaEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> IS_INVULNERABLE = SynchedEntityData.defineId(ValkiaEntity.class, EntityDataSerializers.BOOLEAN);

    // 红色 Boss 血条
    private final ServerBossEvent bossEvent = (ServerBossEvent) new ServerBossEvent(
            this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS
    ).setDarkenScreen(false);

    // 客户端动画状态
    public final AnimationState animOnAir = new AnimationState();          
    public final AnimationState animOnLand = new AnimationState();         
    public final AnimationState animLandToAir = new AnimationState();      
    public final AnimationState animAirToLand = new AnimationState();      
    public final AnimationState animFlyingMoving = new AnimationState();   
    public final AnimationState animFlyingCharge = new AnimationState();   
    public final AnimationState animFlyingRangeAttack = new AnimationState();
    public final AnimationState animFlyingHitGround = new AnimationState();
    public final AnimationState animGroundAttack1 = new AnimationState();   
    public final AnimationState animGroundAttack2 = new AnimationState();   
    public final AnimationState animGroundCharge = new AnimationState();   
    public final AnimationState animGroundDefend = new AnimationState();   
    public final AnimationState animGroundMoving = new AnimationState();   

    public int actionTimer = 0;
    public int subActionTimer = 0;
    public int subActionStep = 0;

    private Vec3 diveStartPos = Vec3.ZERO;
    private Vec3 diveTargetPos = Vec3.ZERO;
    private Vec3 diveReturnPos = Vec3.ZERO;

    public ValkiaEntity(EntityType<ValkiaEntity> type, Level world) {
        super(type, world);
        xpReward = 100;
        setNoAi(false);
        setPersistenceRequired();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(STATE, 0);
        builder.define(ACTION_ID, 0);
        builder.define(IS_INVULNERABLE, false);
    }

    public int getState() {
        return this.entityData.get(STATE);
    }

    public void setState(int state) {
        this.entityData.set(STATE, state);
    }

    public int getActionId() {
        return this.entityData.get(ACTION_ID);
    }

    public void setActionId(int id) {
        if (id == 0) {
            setShieldInvulnerable(false);
        }
        this.entityData.set(ACTION_ID, id);
    }

    public boolean isShieldInvulnerable() {
        return this.entityData.get(IS_INVULNERABLE);
    }

    public void setShieldInvulnerable(boolean inv) {
        this.entityData.set(IS_INVULNERABLE, inv);
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, target ->
                target != null && target.isAlive() && !target.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:korne_mobs")))
        ));
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean hurt = super.doHurtTarget(target);
        if (hurt && target instanceof Player player) {
            disablePlayerShield(player);
        }
        return hurt;
    }

    private void disablePlayerShield(Player player) {
        if (player.isBlocking()) {
            player.getCooldowns().addCooldown(Items.SHIELD, 100);
            player.stopUsingItem();
            this.level().broadcastEntityEvent(player, (byte) 30);
        }
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.mace.smash_ground_heavy"));
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 60) {
            this.animFlyingRangeAttack.stop();
            this.animFlyingRangeAttack.start(this.tickCount);
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return super.hurt(source, amount);
        }

        if (isShieldInvulnerable()) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("minecraft:item.shield.block")),
                    SoundSource.HOSTILE, 1.0F, 1.0F);
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupClientAnimations();
            return;
        }

        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());

        if (getState() == 2 || getState() == 1) {
            this.setNoGravity(true);
        } else {
            this.setNoGravity(false);
        }

        LivingEntity target = this.getTarget();

        if (target == null || !target.isAlive()) {
            setShieldInvulnerable(false);

            if (getState() == 2 || getState() == 1) {
                setState(3);
                setActionId(0);
                actionTimer = 0;
            }

            if (getState() == 3) {
                actionTimer++;
                this.setDeltaMovement(this.getDeltaMovement().x * 0.8D, -0.25D, this.getDeltaMovement().z * 0.8D);
                if (this.onGround() || actionTimer >= 30) {
                    setState(0);
                    setActionId(0);
                    actionTimer = 0;
                    this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
                }
            } else {
                setActionId(0);
            }
            return;
        }

        if (getState() == 2) {
            this.getLookControl().setLookAt(target, 30.0F, 30.0F);
            Vec3 motion = this.getDeltaMovement();
            if (motion.horizontalDistanceSqr() > 0.005) {
                double yaw = Math.toDegrees(Math.atan2(-motion.x, motion.z));
                this.setYRot((float) yaw);
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.getYRot();
            }
        }

        switch (getState()) {
            case 0:
                handleGroundAI(target);
                break;

            case 1:
                setActionId(0);
                actionTimer++;
                this.setDeltaMovement(0, 0, 0);
                if (actionTimer >= 20) {
                    setState(2);
                    setActionId(5);
                    actionTimer = 0;
                    subActionTimer = 30 + this.random.nextInt(20);
                }
                break;

            case 2:
                handleFlyingAI(target);
                break;

            case 3:
                setActionId(0);
                actionTimer++;
                this.setDeltaMovement(this.getDeltaMovement().x * 0.8D, -0.25D, this.getDeltaMovement().z * 0.8D);
                if (this.onGround() || actionTimer >= 20) {
                    setState(0);
                    setActionId(0);
                    actionTimer = 0;
                }
                break;
        }
    }

    private void handleGroundAI(LivingEntity target) {
        int action = getActionId();

        if (action == 0) {
            this.getNavigation().moveTo(target, 1.25);
            double distSq = this.distanceToSqr(target);

            if (distSq <= 12.0) {
                selectGroundAttack();
            } else if (this.random.nextFloat() < 0.02f) {
                setState(1);
                actionTimer = 0;
            }
            return;
        }

        if (action == 1) { // g_attack1
            actionTimer++;
            this.getNavigation().moveTo(target, 1.25);
            if (actionTimer == 10) {
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                        BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("minecraft:entity.player.attack.sweep")), SoundSource.HOSTILE, 1.0f, 1.0f);
                if (this.distanceToSqr(target) <= 16.0) this.doHurtTarget(target);
            }
            if (actionTimer >= 20) postGroundAttackCheck();

        } else if (action == 2) { // g_attack2
            actionTimer++;
            this.getNavigation().moveTo(target, 1.25);
            if (actionTimer == 13) {
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                        BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("minecraft:entity.player.attack.crit")), SoundSource.HOSTILE, 1.0f, 1.0f);
                if (this.distanceToSqr(target) <= 16.0) this.doHurtTarget(target);
            }
            if (actionTimer >= 30) postGroundAttackCheck();

        } else if (action == 3) { // g_charge
            actionTimer++;
            this.getNavigation().stop();
            if (actionTimer <= 10) {
                setShieldInvulnerable(true);
                diveTargetPos = target.position();
            } else {
                setShieldInvulnerable(false);
                if (diveTargetPos != null && !diveTargetPos.equals(Vec3.ZERO)) {
                    Vec3 dir = diveTargetPos.subtract(this.position()).normalize().scale(1.2);
                    this.setDeltaMovement(dir.x, this.getDeltaMovement().y, dir.z);
                }
                if (this.getBoundingBox().inflate(0.5).intersects(target.getBoundingBox())) {
                    this.doHurtTarget(target);
                }
            }
            if (actionTimer >= 30) {
                setShieldInvulnerable(false);
                postGroundAttackCheck();
            }

        } else if (action == 4) { // g_defend (举盾爆破)
            actionTimer++;
            this.getNavigation().stop();
            setShieldInvulnerable(true);
            
            if (actionTimer == 60) {
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), 6.0F, Level.ExplosionInteraction.NONE);
                
                if (this.level() instanceof ServerLevel serverLevel) {
                    double originX = this.getX();
                    double originY = this.getY() + 1.0;
                    double originZ = this.getZ();

                    serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, originX, originY, originZ, 3, 1.0, 1.0, 1.0, 0.0);
                    serverLevel.sendParticles(ParticleTypes.EXPLOSION, originX, originY, originZ, 25, 2.5, 1.5, 2.5, 0.1);
                    serverLevel.sendParticles(ParticleTypes.FLAME, originX, originY, originZ, 60, 3.0, 1.5, 3.0, 0.25);

                    for (int i = 0; i < 80; i++) {
                        double offsetX = (this.random.nextDouble() - 0.5) * 6.0;
                        double offsetY = (this.random.nextDouble() - 0.5) * 3.0;
                        double offsetZ = (this.random.nextDouble() - 0.5) * 6.0;
                        
                        serverLevel.sendParticles(
                                ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, 0.9F, 0.05F, 0.05F),
                                originX + offsetX, originY + offsetY, originZ + offsetZ,
                                1, 0.2, 0.2, 0.2, 0.15
                        );
                    }
                }
            }
            
            if (actionTimer >= 70) {
                setShieldInvulnerable(false);
                postGroundAttackCheck();
            }
        }
    }

    private void selectGroundAttack() {
        actionTimer = 0;
        float rand = this.random.nextFloat();
        if (rand < 0.325f) {
            setActionId(1); 
        } else if (rand < 0.65f) {
            setActionId(2); 
        } else if (rand < 0.90f) {
            setActionId(3); 
        } else {
            setActionId(4); 
        }
    }

    private void postGroundAttackCheck() {
        actionTimer = 0;
        if (this.random.nextFloat() < 0.25f) {
            setState(1);
        } else {
            setActionId(0);
        }
    }

    private void handleFlyingAI(LivingEntity target) {
        int action = getActionId();

        if (action == 5) { // 盘旋
            Vec3 targetHover = target.position().add(Math.sin(this.tickCount * 0.1) * 5, 7, Math.cos(this.tickCount * 0.1) * 5);
            Vec3 moveDir = targetHover.subtract(this.position()).normalize().scale(0.44);
            this.setDeltaMovement(moveDir);

            subActionTimer--;
            if (subActionTimer <= 0) {
                int rnd = this.random.nextInt(100);
                actionTimer = 0;
                subActionStep = 0;
                if (rnd < 40) {
                    setActionId(7); 
                    playSound("hammercraftfantasy:valkia_attackvoic2");
                } else if (rnd < 80) {
                    setActionId(6); 
                    playSound("hammercraftfantasy:valkia_attackvoic1");
                } else {
                    setActionId(8); 
                    playSound("hammercraftfantasy:valkia_attackvoic3");
                }
            }
        } else if (action == 7) { // 远程投矛
            actionTimer++;

            if (actionTimer == 1 || actionTimer == 41 || actionTimer == 81) {
                this.level().broadcastEntityEvent(this, (byte) 60);
            }

            if (actionTimer == 28 || actionTimer == 68 || actionTimer == 108) {
                if (target == null || !target.isAlive()) {
                    returnToHover();
                    return;
                }
                throwSpearAt(target);
            }
            
            if (actionTimer >= 120) {
                returnToHover();
            }
        } else if (action == 6) { // 空中冲锋
            actionTimer++;
            if (subActionStep == 0) {
                diveStartPos = this.position();
                diveTargetPos = target.position().add(0, 1.8, 0); 
                Vec3 chargeDir = diveTargetPos.subtract(diveStartPos);
                if (chargeDir.lengthSqr() > 0.001) {
                    chargeDir = chargeDir.normalize();
                } else {
                    chargeDir = new Vec3(0, -1, 0);
                }
                diveReturnPos = diveTargetPos.add(chargeDir.scale(6.0)).add(0, 6.0, 0);
                subActionStep = 1;
            } else if (subActionStep == 1) { 
                if (!diveTargetPos.equals(Vec3.ZERO)) {
                    Vec3 dir = diveTargetPos.subtract(this.position());
                    if (dir.lengthSqr() > 0.001) {
                        this.setDeltaMovement(dir.normalize().scale(1.32));
                    }
                }
                if (this.getBoundingBox().inflate(1.2).intersects(target.getBoundingBox())) {
                    this.doHurtTarget(target);
                    subActionStep = 2;
                } else if (actionTimer >= 40 || this.position().distanceToSqr(diveTargetPos) < 3.0) {
                    subActionStep = 2;
                }
            } else if (subActionStep == 2) { 
                if (!diveReturnPos.equals(Vec3.ZERO)) {
                    Vec3 returnDir = diveReturnPos.subtract(this.position());
                    if (returnDir.lengthSqr() > 0.001) {
                        this.setDeltaMovement(returnDir.normalize().scale(0.96));
                    }
                }
                if (actionTimer >= 80 || (this.position().distanceToSqr(diveReturnPos) < 4.0)) {
                    returnToHover();
                }
            }
        } else if (action == 8) { // 飞行重击地面
            actionTimer++;
            if (subActionStep == 0) { 
                Vec3 topPos = target.position().add(0, 7, 0);
                Vec3 dir = topPos.subtract(this.position());
                if (dir.lengthSqr() > 0.001) {
                    this.setDeltaMovement(dir.scale(0.36));
                }
                if (this.position().distanceToSqr(topPos) < 3.0 || actionTimer > 40) {
                    subActionStep = 1;
                    actionTimer = 0;
                }
            } else if (subActionStep == 1) { 
                this.setDeltaMovement(0, actionTimer >= 20 ? -2.2 : 0, 0);
                if (this.onGround() || actionTimer >= 50) {
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("minecraft:entity.generic.explode")), SoundSource.HOSTILE, 1.0F, 1.0F);

                    DamageSource bypassSource = this.damageSources().mobAttack(this);

                    List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0));
                    for (LivingEntity e : targets) {
                        if (e == this) continue;
                        
                        if (e instanceof Player player) {
                            disablePlayerShield(player);
                        }

                        double d = this.distanceTo(e);
                        if (d <= 2.5) {
                            e.hurt(bypassSource, 100.0F);
                        } else if (d <= 6.0) {
                            e.hurt(bypassSource, 20.0F);
                        }
                        if (d <= 16.0 && e instanceof Player player) {
                            player.hurtMarked = true;
                        }
                    }
                    
                    setState(3);
                    setActionId(0);
                    actionTimer = 0;
                }
            }
        }
    }

    private void throwSpearAt(LivingEntity target) {
        if (target == null) return;

        Vec3 shooterPos = this.position().add(0, this.getEyeHeight() * 0.5, 0);
        Vec3 targetPos = target.position().add(0, target.getBbHeight() * 0.5, 0);

        double dx = targetPos.x - shooterPos.x;
        double dy = targetPos.y - shooterPos.y;
        double dz = targetPos.z - shooterPos.z;

        double horizontalDistance = Math.sqrt(dx * dx + dz * dz);

        float speed = 1.6F;
        double velocityY = dy + horizontalDistance * 0.25D;

        ValkiasspearEntity entityarrow = new ValkiasspearEntity(HammercraftfantasyModEntities.VALKIASSPEAR.get(), this, this.level(), null);
        entityarrow.setPos(shooterPos.x, shooterPos.y, shooterPos.z);
        entityarrow.shoot(dx, velocityY, dz, speed * 1.5F, 0.2F);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(20.0); // 投矛伤害已修改为 20.0
        entityarrow.setKnockback(1);
        entityarrow.setCritArrow(false);

        this.level().addFreshEntity(entityarrow);
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.trident.throw")),
                SoundSource.HOSTILE, 1.0F, 1f / (this.getRandom().nextFloat() * 0.5f + 1));
    }

    private void returnToHover() {
        setActionId(5);
        actionTimer = 0;
        subActionTimer = 60 + this.random.nextInt(40);
    }

    private void playSound(String soundPath) {
        try {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse(soundPath)), SoundSource.HOSTILE, 1.0F, 1.0F);
        } catch (Exception ignored) {}
    }

    private void setupClientAnimations() {
        int state = getState();
        int action = getActionId();

        this.animOnLand.animateWhen(state == 0 || state == 3, this.tickCount);
        this.animOnAir.animateWhen(state == 2 || state == 1, this.tickCount);

        this.animLandToAir.animateWhen(state == 1, this.tickCount);
        this.animAirToLand.animateWhen(state == 3, this.tickCount);

        this.animFlyingMoving.animateWhen(state == 2 && this.getDeltaMovement().horizontalDistanceSqr() > 0.005, this.tickCount);
        this.animGroundMoving.animateWhen(state == 0 && this.getDeltaMovement().horizontalDistanceSqr() > 0.005, this.tickCount);

        this.animGroundAttack1.animateWhen(state == 0 && action == 1, this.tickCount);
        this.animGroundAttack2.animateWhen(state == 0 && action == 2, this.tickCount);
        this.animGroundCharge.animateWhen(state == 0 && action == 3, this.tickCount);
        this.animGroundDefend.animateWhen(state == 0 && action == 4, this.tickCount);

        this.animFlyingCharge.animateWhen(state == 2 && action == 6, this.tickCount);
        this.animFlyingHitGround.animateWhen(state == 2 && action == 8, this.tickCount);

        if (state != 2 || action != 7) {
            this.animFlyingRangeAttack.stop();
        }
    }

    public static void init(RegisterSpawnPlacementsEvent event) {}

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.ARMOR, 24)
                .add(Attributes.ATTACK_DAMAGE, 24) // 近战攻击力(ATTACK_DAMAGE)已修改为 24
                .add(Attributes.FOLLOW_RANGE, 32)
                .add(Attributes.STEP_HEIGHT, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5);
    }
}