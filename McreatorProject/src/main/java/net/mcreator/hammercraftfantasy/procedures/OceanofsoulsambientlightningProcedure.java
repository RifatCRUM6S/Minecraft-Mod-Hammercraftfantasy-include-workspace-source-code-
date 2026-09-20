package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.List;

@EventBusSubscriber
public class OceanofsoulsambientlightningProcedure {

    // 🌟 1. 静态预缓存维度 Key，避免每次 Tick 重新 Parse 造成性能抖动
    private static final ResourceKey<Level> TARGET_DIM = ResourceKey.create(
            Registries.DIMENSION, 
            ResourceLocation.parse("hammercraftfantasy:oceanofsouls")
    );

    // 🌟 2. 改为监听 LevelTickEvent（维度 Tick），彻底剔除客户端消耗与多玩家重复计算
    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        // 确保只在服务端运行，且必须是目标维度
        if (event.getLevel() instanceof ServerLevel serverLevel && serverLevel.dimension().equals(TARGET_DIM)) {
            
            // 概率控制：例如平均每 50 Ticks (2.5秒) 判定一次落雷
            if (serverLevel.getRandom().nextFloat() < 0.02F) {
                
                // 获取当前维度内的所有在线玩家
                List<ServerPlayer> players = serverLevel.players();
                if (players.isEmpty()) {
                    return; // 没人就不生雷，节约开销
                }

                // 随机挑选一名幸运玩家作为闪电基准点
                ServerPlayer randomPlayer = players.get(serverLevel.getRandom().nextInt(players.size()));

                // 偏移计算：使用 serverLevel 原生共享的 RandomSource，避免重新对象创建
                double randomX = randomPlayer.getX() + (serverLevel.getRandom().nextDouble() * 80.0D - 40.0D);
                double randomY = 200; // 固定高度
                double randomZ = randomPlayer.getZ() + (serverLevel.getRandom().nextDouble() * 80.0D - 40.0D);

                // 召唤闪电
                EntityType.LIGHTNING_BOLT.spawn(
                        serverLevel, 
                        BlockPos.containing(randomX, randomY, randomZ), 
                        MobSpawnType.MOB_SUMMONED
                );
            }
        }
    }
}