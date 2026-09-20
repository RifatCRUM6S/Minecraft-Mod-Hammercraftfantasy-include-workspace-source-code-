package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModMobEffects;

import java.util.EnumSet;
import java.util.List;

public class SilverguardEntity extends Monster {
    private LivingEntity leaderSigvald = null;

    public SilverguardEntity(EntityType<SilverguardEntity> type, Level world) {
        super(type, world);
        xpReward = 20;
        setNoAi(false);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // 目标选择 (Target Goals)
        // 1. 共享 Sigvald 的仇恨目标 (最优先)
        this.targetSelector.addGoal(1, new FollowSigvaldTargetGoal(this));
        // 2. 自卫反击
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        // 3. 自动索敌 (排除色孽系友军)
        this.targetSelector.addGoal(3, new net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal<>(
                this, LivingEntity.class, 24, true, false,
                target -> target != null && target.isAlive() 
                && !target.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:slaanesh_mobs")))
        ));

        // 行为 AI (Goal Selector)
        this.goalSelector.addGoal(1, new FloatGoal(this));
        // 近战攻击 AI
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false) {
            @Override
            protected boolean canPerformAttack(LivingEntity entity) {
                return this.isTimeToAttack() && this.mob.distanceToSqr(entity) < (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth()) && this.mob.getSensing().hasLineOfSight(entity);
            }
        });
        // 护卫寻路 AI (非战斗状态下贴身跟随 Sigvald)
        this.goalSelector.addGoal(3, new FollowSigvaldGoal(this, 1.2D, 3.0F, 12.0F));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        // 自动在附近检索并绑定 Sigvald
        if (!this.level().isClientSide() && (this.leaderSigvald == null || !this.leaderSigvald.isAlive())) {
            findNearestSigvald();
        }
    }

    /**
     * 检索周围 32 格内的 Sigvald 实体
     */
    private void findNearestSigvald() {
        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(32.0D), entity -> 
            entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:sigvald"))) 
            || entity.getType().equals(BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse("hammercraftfantasy:sigvald")))
        );
        if (!list.isEmpty()) {
            this.leaderSigvald = list.get(0);
        }
    }

    /**
     * 3. 近战攻击成功时：给予目标 5 秒 (100 ticks) 的虚弱 I 效果
     */
    @Override
    public boolean doHurtTarget(Entity target) {
        boolean success = super.doHurtTarget(target);
        if (success && target instanceof LivingEntity livingTarget) {
            // 虚弱 I 效果 (Amplifier 0 = Level 1)
            livingTarget.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
        }
        return success;
    }

    /**
     * 2. 受到攻击时：20% 概率给攻击者施加 5 秒 (100 ticks) 的 Dazzle 效果
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean hurtResult = super.hurt(source, amount);
        if (hurtResult && !this.level().isClientSide()) {
            Entity attacker = source.getEntity();
            if (attacker instanceof LivingEntity livingAttacker && this.random.nextFloat() < 0.20F) {
                livingAttacker.addEffect(new MobEffectInstance(HammercraftfantasyModMobEffects.DAZZLE.getDelegate(), 100, 0));
            }
        }
        return hurtResult;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:silverguard_breath"));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:k_cultist_2"));
    }

    @Override
    public SoundEvent getDeathSound() {
        // 修改为自定义死亡音效 k_cultist_2
        return BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("hammercraftfantasy:k_cultist_2"));
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 40);
        builder = builder.add(Attributes.ARMOR, 18);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 12);
        builder = builder.add(Attributes.FOLLOW_RANGE, 32);
        builder = builder.add(Attributes.STEP_HEIGHT, 0.6);
        return builder;
    }

    /**
     * AI Goal: 共享 Sigvald 的攻击目标（保镖协同仇恨）
     */
    private static class FollowSigvaldTargetGoal extends Goal {
        private final SilverguardEntity guard;

        public FollowSigvaldTargetGoal(SilverguardEntity guard) {
            this.guard = guard;
            this.setFlags(EnumSet.of(Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            if (this.guard.leaderSigvald != null && this.guard.leaderSigvald.isAlive()) {
                if (this.guard.leaderSigvald instanceof Mob mobLeader) {
                    LivingEntity sigvaldTarget = mobLeader.getTarget();
                    return sigvaldTarget != null && sigvaldTarget.isAlive() && sigvaldTarget != this.guard;
                }
            }
            return false;
        }

        @Override
        public void start() {
            if (this.guard.leaderSigvald instanceof Mob mobLeader) {
                this.guard.setTarget(mobLeader.getTarget());
            }
            super.start();
        }
    }

    /**
     * AI Goal: 自动跟随 Sigvald 移动（保镖贴身护卫）
     */
    private static class FollowSigvaldGoal extends Goal {
        private final SilverguardEntity guard;
        private final double speedModifier;
        private final float minDist;
        private final float maxDist;

        public FollowSigvaldGoal(SilverguardEntity guard, double speedModifier, float minDist, float maxDist) {
            this.guard = guard;
            this.speedModifier = speedModifier;
            this.minDist = minDist;
            this.maxDist = maxDist;
            this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            LivingEntity leader = this.guard.leaderSigvald;
            if (leader == null || !leader.isAlive()) {
                return false;
            }
            // 只有在没有战斗目标，或者与 Sigvald 距离拉开时才触发跟随
            return this.guard.getTarget() == null && this.guard.distanceToSqr(leader) > (double) (this.minDist * this.minDist);
        }

        @Override
        public boolean canContinueToUse() {
            LivingEntity leader = this.guard.leaderSigvald;
            if (leader == null || !leader.isAlive() || this.guard.getTarget() != null) {
                return false;
            }
            return this.guard.distanceToSqr(leader) > (double) (this.minDist * this.minDist);
        }

        @Override
        public void tick() {
            LivingEntity leader = this.guard.leaderSigvald;
            if (leader == null) return;

            this.guard.getLookControl().setLookAt(leader, 10.0F, (float) this.guard.getMaxHeadXRot());
            if (this.guard.distanceToSqr(leader) > (double) (this.maxDist * this.maxDist)) {
                // 距离过远直接加速追赶
                this.guard.getNavigation().moveTo(leader, this.speedModifier * 1.3D);
            } else {
                this.guard.getNavigation().moveTo(leader, this.speedModifier);
            }
        }
    }
}