package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import javax.annotation.Nullable;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class PinkHorrorArrowEntity extends AbstractArrow implements ItemSupplier {
    public static final ItemStack PROJECTILE_ITEM = new ItemStack(Blocks.BUBBLE_CORAL_FAN);
    private int knockback = 0;

    public PinkHorrorArrowEntity(EntityType<? extends PinkHorrorArrowEntity> type, Level world) {
        super(type, world);
        this.setSoundEvent(SoundEvents.FIRECHARGE_USE); // 统一将命中/落地音效设为火球
    }

    public PinkHorrorArrowEntity(EntityType<? extends PinkHorrorArrowEntity> type, double x, double y, double z, Level world, @Nullable ItemStack firedFromWeapon) {
        super(type, x, y, z, world, PROJECTILE_ITEM, firedFromWeapon);
        this.setSoundEvent(SoundEvents.FIRECHARGE_USE);
        if (firedFromWeapon != null)
            setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
    }

    public PinkHorrorArrowEntity(EntityType<? extends PinkHorrorArrowEntity> type, LivingEntity entity, Level world, @Nullable ItemStack firedFromWeapon) {
        super(type, entity, world, PROJECTILE_ITEM, firedFromWeapon);
        this.setSoundEvent(SoundEvents.FIRECHARGE_USE);
        if (firedFromWeapon != null)
            setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack getItem() {
        return PROJECTILE_ITEM;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Blocks.BUBBLE_CORAL_FAN);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    public void setKnockback(int knockback) {
        this.knockback = knockback;
    }

    @Override
    protected void doKnockback(LivingEntity livingEntity, DamageSource damageSource) {
        if (knockback > 0.0) {
            double d1 = Math.max(0.0, 1.0 - livingEntity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE));
            Vec3 vec3 = this.getDeltaMovement().multiply(1.0, 0.0, 1.0).normalize().scale(knockback * 0.6 * d1);
            if (vec3.lengthSqr() > 0.0) {
                livingEntity.push(vec3.x, 0.1, vec3.z);
            }
        } else {
            super.doKnockback(livingEntity, damageSource);
        }
    }

    // 点燃玩家逻辑
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (entityHitResult.getEntity() instanceof ServerPlayer player) {
            if (player.gameMode.getGameModeForPlayer() == GameType.SURVIVAL || player.gameMode.getGameModeForPlayer() == GameType.ADVENTURE) {
                player.setRemainingFireTicks(100);
            }
        }
    }

    @Override
    public void tick() {
        // 封死原版星星暴击粒子
        this.setCritArrow(false);
        
        super.tick();
        
        if (this.level().isClientSide()) {
            Vec3 motion = this.getDeltaMovement();
            
            // 从内建注册表抓取粒子类型
            ParticleType<?> baseType = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "pink_horror_fire"));
            
            if (baseType instanceof SimpleParticleType particleType) {
                // 5段高密度线性插值
                for (int i = 0; i < 5; ++i) {
                    double progress = (double) i / 5.0D;
                    
                    double px = this.getX() - motion.x * progress;
                    double py = this.getY() - motion.y * progress;
                    double pz = this.getZ() - motion.z * progress;
                    
                    double vx = (this.random.nextFloat() - 0.5F) * 0.08D;
                    double vy = (this.random.nextFloat() - 0.5F) * 0.08D;
                    double vz = (this.random.nextFloat() - 0.5F) * 0.08D;
                    
                    this.level().addParticle(
                        particleType, 
                        px, py + 0.15D, pz, 
                        vx, vy, vz
                    );
                }
            }
        }

        if (this.inGround)
            this.discard();
    }

    public static PinkHorrorArrowEntity shoot(Level world, LivingEntity entity, RandomSource source) {
        return shoot(world, entity, source, 0.6f, 12, 1);
    }

    public static PinkHorrorArrowEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
        return shoot(world, entity, source, pullingPower * 0.6f, 12, 1);
    }

    public static PinkHorrorArrowEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        PinkHorrorArrowEntity entityarrow = new PinkHorrorArrowEntity(HammercraftfantasyModEntities.PINK_HORROR_ARROW.get(), entity, world, null);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        return entityarrow;
    }

    public static PinkHorrorArrowEntity shoot(LivingEntity entity, LivingEntity target) {
        PinkHorrorArrowEntity entityarrow = new PinkHorrorArrowEntity(HammercraftfantasyModEntities.PINK_HORROR_ARROW.get(), entity, entity.level(), null);
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 0.6f * 2, 2.5F);
        
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(12);
        entityarrow.setKnockback(0);
        entity.level().addFreshEntity(entityarrow);
        return entityarrow;
    }
}