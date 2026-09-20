package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.ParticleType;

import java.util.List;

public class FlamersheadOnUseTickProcedure {

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null || !(entity instanceof LivingEntity _entity))
            return;

        int totalDuration = itemstack.getUseDuration(_entity);
        int remainingTicks = _entity.getUseItemRemainingTicks();

        if (remainingTicks <= 0 || totalDuration <= 0)
            return;

        int ticksUsed = totalDuration - remainingTicks;
        int warmupTicks = Math.max(3, Math.min(10, (int) (totalDuration * 0.125)));

        if (_entity instanceof Player _player && !_player.getAbilities().instabuild) {
            itemstack.hurtAndBreak(1, _entity, EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty()) {
                _entity.stopUsingItem();
                return;
            }
        }

        if (ticksUsed < warmupTicks) {
            if (world.isClientSide()) {
                spawnAuraParticles(world, _entity);
            }
            return;
        }

        if (world.isClientSide()) {
            spawnFlameParticles(world, _entity);
        } else if (world instanceof Level _level) {
            // 从前摇结束开始，每 5 Ticks 播放一次火焰弹音效
            if (ticksUsed % 5 == 0) {
                float pitch = 0.8F + _level.getRandom().nextFloat() * 0.4F;
                _level.playSound(null, _entity.getX(), _entity.getY(), _entity.getZ(), 
                        SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.0F, pitch);
            }

            // 服务端：每 4 Ticks 触发一次伤害（单次伤害提升 2 点，改为 10.0F）
            if (ticksUsed % 4 == 0) {
                performFlameAreaDamage(_level, _entity, 12.0D, 10.0F); 
            }
        }
    }

    private static void spawnAuraParticles(LevelAccessor world, LivingEntity entity) {
        ParticleType<?> customType = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:pinkfalme"));
        for (int i = 0; i < 3; i++) {
            double px = entity.getX() + (world.getRandom().nextDouble() - 0.5) * 1.5;
            double py = entity.getY() + world.getRandom().nextDouble() * entity.getBbHeight();
            double pz = entity.getZ() + (world.getRandom().nextDouble() - 0.5) * 1.5;

            double vx = (world.getRandom().nextDouble() - 0.5) * 0.05;
            double vy = 0.04 + world.getRandom().nextDouble() * 0.06;
            double vz = (world.getRandom().nextDouble() - 0.5) * 0.05;

            if (i % 2 == 0 && customType instanceof SimpleParticleType pinkFlameType) {
                world.addParticle(pinkFlameType, px, py, pz, vx, vy, vz);
            } else {
                world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, px, py, pz, vx, vy, vz);
            }
        }
    }

    private static void spawnFlameParticles(LevelAccessor world, LivingEntity entity) {
        Vec3 look = entity.getLookAngle();
        double startX = entity.getX() + look.x * 0.8;
        double startY = entity.getEyeY() - 0.2 + look.y * 0.8;
        double startZ = entity.getZ() + look.z * 0.8;

        ParticleType<?> customType = BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.parse("hammercraftfantasy:pinkfalme"));

        for (int i = 0; i < 8; i++) {
            double speedScale = 0.6 + world.getRandom().nextDouble() * 0.4;
            double vx = look.x * speedScale + (world.getRandom().nextDouble() - 0.5) * 0.12;
            double vy = look.y * speedScale + (world.getRandom().nextDouble() - 0.5) * 0.12;
            double vz = look.z * speedScale + (world.getRandom().nextDouble() - 0.5) * 0.18;

            if (i % 2 == 0 && customType instanceof SimpleParticleType pinkFlameType) {
                world.addParticle(pinkFlameType, startX, startY, startZ, vx, vy, vz);
            } else {
                world.addParticle(ParticleTypes.SOUL_FIRE_FLAME, startX, startY, startZ, vx, vy, vz);
            }
        }
    }

    private static void performFlameAreaDamage(Level level, LivingEntity attacker, double range, float damagePerHit) {
        Vec3 lookVec = attacker.getLookAngle();
        Vec3 eyePos = attacker.getEyePosition();

        AABB searchBox = attacker.getBoundingBox().inflate(range);
        List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, searchBox, e -> 
            e != attacker && e.isAlive()
        );

        for (LivingEntity target : targets) {
            Vec3 toTarget = target.position().add(0, target.getBbHeight() / 2.0, 0).subtract(eyePos);
            if (toTarget.length() <= range) {
                if (lookVec.dot(toTarget.normalize()) > 0.6D) {
                    DamageSource ds = attacker instanceof Player p ? level.damageSources().playerAttack(p) : level.damageSources().mobAttack(attacker);
                    Vec3 oldDelta = target.getDeltaMovement();

                    if (target.hurt(ds, damagePerHit)) {
                        target.setRemainingFireTicks(60);
                        target.setDeltaMovement(oldDelta.x * 0.3, target.getDeltaMovement().y, oldDelta.z * 0.3);
                    }
                }
            }
        }
    }
}