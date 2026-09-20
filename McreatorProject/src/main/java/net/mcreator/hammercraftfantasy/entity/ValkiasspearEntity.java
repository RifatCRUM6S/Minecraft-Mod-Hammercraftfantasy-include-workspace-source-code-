package net.mcreator.hammercraftfantasy.entity;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;
import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import javax.annotation.Nullable;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class ValkiasspearEntity extends AbstractArrow implements ItemSupplier {
    public static final ItemStack PROJECTILE_ITEM = new ItemStack(HammercraftfantasyModItems.VALKIA_SPAWN_EGG.get());
    private int knockback = 0;
    private boolean hasHitEntity = false;

    public ValkiasspearEntity(EntityType<? extends ValkiasspearEntity> type, Level world) {
        super(type, world);
    }

    public ValkiasspearEntity(EntityType<? extends ValkiasspearEntity> type, double x, double y, double z, Level world, @Nullable ItemStack firedFromWeapon) {
        super(type, x, y, z, world, PROJECTILE_ITEM, firedFromWeapon);
        if (firedFromWeapon != null)
            setKnockback(EnchantmentHelper.getItemEnchantmentLevel(world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.KNOCKBACK), firedFromWeapon));
    }

    public ValkiasspearEntity(EntityType<? extends ValkiasspearEntity> type, LivingEntity entity, Level world, @Nullable ItemStack firedFromWeapon) {
        super(type, entity, world, PROJECTILE_ITEM, firedFromWeapon);
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
        return new ItemStack(HammercraftfantasyModItems.VALKIA_SPAWN_EGG.get());
    }

    @Override
    protected void doPostHurtEffects(LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    // 完美复刻三叉戟击中生物后弹开并掉落的物理逻辑
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide() && !hasHitEntity) {
            hasHitEntity = true;
            // 给予反向弹开的加速度，并加上轻微向上分量
            Vec3 currentMotion = this.getDeltaMovement();
            this.setDeltaMovement(currentMotion.multiply(-0.25D, -0.25D, -0.25D).add(0.0, 0.2D, 0.0));
            // 允许脱离实体并自由下落
            this.setNoPhysics(false);
            this.inGround = false;
        }
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

    @Override
    public void tick() {
        if (hasHitEntity) {
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.05D, 0.0));
            }
        }
        super.tick();
    }

    @Override
    protected float getWaterInertia() {
        return 0.99F;
    }

    public static ValkiasspearEntity shoot(Level world, LivingEntity entity, RandomSource source) {
        return shoot(world, entity, source, 1f, 24, 0);
    }

    public static ValkiasspearEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
        return shoot(world, entity, source, pullingPower * 1f, 24, 0);
    }

    public static ValkiasspearEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        ValkiasspearEntity entityarrow = new ValkiasspearEntity(HammercraftfantasyModEntities.VALKIASSPEAR.get(), entity, world, null);
        entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2.5F, 1.0F);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(false);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.trident.throw")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
        return entityarrow;
    }

    public static ValkiasspearEntity shoot(LivingEntity shooter, LivingEntity target) {
        ValkiasspearEntity entityarrow = new ValkiasspearEntity(HammercraftfantasyModEntities.VALKIASSPEAR.get(), shooter, shooter.level(), null);
        
        Vec3 shooterPos = shooter.position().add(0, shooter.getEyeHeight() * 0.5, 0);
        Vec3 targetPos = target.position().add(0, target.getBbHeight() * 0.5, 0);
        
        double dx = targetPos.x - shooterPos.x;
        double dy = targetPos.y - shooterPos.y;
        double dz = targetPos.z - shooterPos.z;
        
        double horizontalDistance = Math.hypot(dx, dz);
        double velocityY = dy + horizontalDistance * 0.22D;
        
        entityarrow.setPos(shooterPos.x, shooterPos.y, shooterPos.z);
        entityarrow.shoot(dx, velocityY, dz, 1.8F, 0.5F);
        
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(16.0);
        entityarrow.setKnockback(1);
        
        shooter.level().addFreshEntity(entityarrow);
        shooter.level().playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), 
                BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.trident.throw")), 
                SoundSource.HOSTILE, 1.0F, 1f / (shooter.getRandom().nextFloat() * 0.5f + 1));
        return entityarrow;
    }
}