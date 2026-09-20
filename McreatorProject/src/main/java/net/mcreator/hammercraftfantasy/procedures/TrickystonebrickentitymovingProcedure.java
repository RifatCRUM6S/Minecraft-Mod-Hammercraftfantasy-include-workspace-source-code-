package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.TagKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;

import java.util.Map;

public class TrickystonebrickentitymovingProcedure {

    private static final TagKey<net.minecraft.world.entity.EntityType<?>> TZEENTCH_MOBS = 
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("hammercraftfantasy:tzeentch_mobs"));

    public static void execute(Map<String, Object> dependencies) {
        if (dependencies == null) return;
        LevelAccessor world = (LevelAccessor) dependencies.get("world");
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        Entity entity = (Entity) dependencies.get("entity");
        execute(world, x, y, z, entity);
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null || !(world instanceof ServerLevel _level))
            return;

        if (entity instanceof LivingEntity _livingEntity && !_livingEntity.isAlive())
            return;

        if (entity.getType().is(TZEENTCH_MOBS))
            return;

        // 获取游戏系统当前 Tick 时间戳做冷却判断
        long currentTime = _level.getGameTime();
        long lastTeleportTime = entity.getPersistentData().getLong("last_trickystone_tp");

        // 冷却 80 Ticks (4秒)
        if (currentTime - lastTeleportTime < 80) {
            return;
        }

        RandomSource random = _level.getRandom();
        BlockPos currentPos = entity.blockPosition();

        for (int attempt = 0; attempt < 8; attempt++) {
            int offsetA = (random.nextBoolean() ? 1 : -1) * (32 + random.nextInt(7));
            int offsetB = random.nextInt(77) - 38;

            int dx = random.nextBoolean() ? offsetA : offsetB;
            int dz = (dx == offsetA) ? offsetB : offsetA;

            int targetX = currentPos.getX() + dx;
            int targetZ = currentPos.getZ() + dz;

            if (!_level.hasChunkAt(new BlockPos(targetX, 0, targetZ)))
                continue;

            for (int dy = -8; dy <= 8; dy++) {
                int targetY = currentPos.getY() + dy;
                BlockPos floorPos = new BlockPos(targetX, targetY, targetZ);
                BlockPos standPos1 = floorPos.above();
                BlockPos standPos2 = standPos1.above();

                BlockState floorState = _level.getBlockState(floorPos);

                if (floorState.isRedstoneConductor(_level, floorPos) 
                        && isSafeToStandIn(_level, standPos1) 
                        && isSafeToStandIn(_level, standPos2)) {

                    double destX = targetX + 0.5;
                    double destY = targetY + 1.0;
                    double destZ = targetZ + 0.5;

                    // 原地音效与服务端粒子
                    _level.playSound(null, x, y, z, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1.0F, 1.0F);
                    _level.sendParticles(ParticleTypes.PORTAL, x, y + 1.0, z, 30, 0.2, 0.5, 0.2, 0.1);

                    // 传送实体
                    if (entity instanceof Player _player) {
                        _player.teleportTo(destX, destY, destZ);
                    } else {
                        entity.teleportTo(destX, destY, destZ);
                    }

                    // 目标点音效与服务端粒子
                    _level.playSound(null, destX, destY, destZ, SoundEvents.ENDERMAN_TELEPORT, SoundSource.BLOCKS, 1.0F, 1.0F);
                    _level.sendParticles(ParticleTypes.PORTAL, destX, destY + 1.0, destZ, 30, 0.2, 0.5, 0.2, 0.1);

                    // 若被传送实体为玩家，授予成就 "oops"
                    if (entity instanceof ServerPlayer _serverPlayer) {
                        AdvancementHolder advancement = _level.getServer().getAdvancements()
                            .get(ResourceLocation.parse("hammercraftfantasy:oops"));
                        if (advancement != null) {
                            AdvancementProgress progress = _serverPlayer.getAdvancements().getOrStartProgress(advancement);
                            if (!progress.isDone()) {
                                for (String criterion : progress.getRemainingCriteria()) {
                                    _serverPlayer.getAdvancements().award(advancement, criterion);
                                }
                            }
                        }
                    }

                    // 写入传送时的 GameTime 戳
                    entity.getPersistentData().putLong("last_trickystone_tp", currentTime);
                    return;
                }
            }
        }
    }

    private static boolean isSafeToStandIn(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return level.isEmptyBlock(pos) || (!state.isSuffocating(level, pos) && state.getCollisionShape(level, pos).isEmpty());
    }
}