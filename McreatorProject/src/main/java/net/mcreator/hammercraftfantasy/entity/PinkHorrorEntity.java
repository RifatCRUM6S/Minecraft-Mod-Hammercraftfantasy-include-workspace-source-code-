package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;

import java.util.Comparator;

public class PinkHorrorEntity extends Monster implements RangedAttackMob {

    public PinkHorrorEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        xpReward = 15;
        setNoAi(false);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        // 1. 动态索敌 Goal：防抽陀螺锁敌 + 奸奇 (Tzeentch) 阵营过滤
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>(
                this, LivingEntity.class, 10, true, false,
                target -> isTargetValid(target)
        ) {
            private int lockTicks = 0;
            private int rescanTicks = 0;

            @Override
            public boolean canUse() {
                LivingEntity lastHurtBy = this.mob.getLastHurtByMob();
                if (lastHurtBy != null && lastHurtBy.isAlive() && lockTicks <= 0 && isTargetValid(lastHurtBy)) {
                    this.target = lastHurtBy;
                    this.lockTicks = 60; // 受到攻击强行锁敌 3 秒
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
                if (this.rescanTicks >= 40) { // 每 2 秒重扫描一次
                    this.rescanTicks = 0;
                    if (this.lockTicks > 0) return;

                    LivingEntity currentTarget = this.mob.getTarget();
                    if (currentTarget == null || !currentTarget.isAlive()) return;

                    double currentDistSqr = this.mob.distanceToSqr(currentTarget);
                    LivingEntity closest = this.mob.level().getEntitiesOfClass(
                        LivingEntity.class,
                        this.mob.getBoundingBox().inflate(24.0),
                        e -> isTargetValid(e)
                    ).stream().min(Comparator.comparingDouble(e -> this.mob.distanceToSqr(e))).orElse(null);

                    if (closest != null && closest != currentTarget && this.mob.distanceToSqr(closest) < currentDistSqr - 9.0) {
                        this.mob.setTarget(closest);
                        this.target = closest;
                        this.lockTicks = 40;
                    }
                }
            }
        });

        // 2. 远程攻击 Goal：射击间隔已延长至 2.5 倍 (50 Ticks = 2.5秒)
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 50, 20.0F));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
    }

    /**
     * 目标有效性校验（过滤 奸奇/Tzeentch 阵营生物）
     */
    private boolean isTargetValid(LivingEntity e) {
        return e != null
            && e.isAlive()
            && !e.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs")));
    }

    /**
     * 执行远程攻击：播放烈焰人发射音效
     */
    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        double d0 = target.getX() - this.getX();
        double d1 = target.getY(0.5D) - this.getY(0.5D);
        double d2 = target.getZ() - this.getZ();

        // 修复 1.21.1 小火球的参数传递
        SmallFireball projectile = new SmallFireball(this.level(), this, new Vec3(d0, d1, d2).normalize());
        projectile.setPos(this.getX(), this.getY(0.5D) + 0.5D, this.getZ());
        this.level().addFreshEntity(projectile);

        // 使用烈焰人发射火球的音效
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.BLAZE_SHOOT, SoundSource.HOSTILE, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    public static void init(RegisterSpawnPlacementsEvent event) {
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
        builder = builder.add(Attributes.MAX_HEALTH, 40);
        builder = builder.add(Attributes.ARMOR, 4);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 6);
        builder = builder.add(Attributes.FOLLOW_RANGE, 24);
        return builder;
    }
}