package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;
import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SChampionSummonAltar_summondProcedure {

    private static final ScheduledExecutorService SCHEDULER = Executors.newSingleThreadScheduledExecutor();

    public static void execute(LevelAccessor world, double x, double y, double z) {
        execute((Map<String, Object>) null, world, x, y, z, null);
    }

    public static void execute(Map<String, Object> dependencies) {
        if (dependencies == null) return;
        LevelAccessor world = (LevelAccessor) dependencies.get("world");
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        Entity entity = dependencies.get("entity") instanceof Entity _ent ? _ent : null;
        execute(dependencies, world, x, y, z, entity);
    }

    private static void execute(Map<String, Object> dependencies, LevelAccessor world, double x, double y, double z, Entity entity) {
        if (!(world instanceof ServerLevel _level))
            return;

        if (entity == null) {
            entity = _level.getNearestPlayer(x + 0.5, y + 0.5, z + 0.5, 8.0, false);
        }
        if (!(entity instanceof Player player)) return;

        ItemStack mainHand = player.getMainHandItem();
        if (!mainHand.is(HammercraftfantasyModItems.KEYOFPALACE.get())) {
            return;
        }

        BlockPos pos = BlockPos.containing(x, y, z);
        BlockState state = _level.getBlockState(pos);

        if (state.getBlock().getStateDefinition().getProperty("s_chsumal") instanceof IntegerProperty _integerProp) {
            if (state.getValue(_integerProp) == 1) {
                player.sendSystemMessage(Component.literal("§cThis altar's power has been exhausted!"));
                return;
            }
        }

        double searchRadius = 192.0;
        AABB searchBox = new AABB(x - searchRadius, y - 64, z - searchRadius, x + searchRadius, y + 64, z + searchRadius);

        var sigvaldType = HammercraftfantasyModEntities.SIGVALD.get();
        if (sigvaldType != null) {
            boolean hasSigvald = !_level.getEntitiesOfClass(LivingEntity.class, searchBox, e -> e.getType() == sigvaldType && e.isAlive()).isEmpty();
            if (hasSigvald) {
                player.sendSystemMessage(Component.literal("§cSigvald already exists in this area!"));
                return;
            }
        }

        if (!player.getAbilities().instabuild) {
            mainHand.shrink(1);
        }

        if (state.getBlock().getStateDefinition().getProperty("s_chsumal") instanceof IntegerProperty _integerProp) {
            _level.setBlock(pos, state.setValue(_integerProp, 1), 3);
        }

        double cx = pos.getX() + 0.5;
        double cy = pos.getY() + 1.2;
        double cz = pos.getZ() + 0.5;

        _level.playSound(null, pos, SoundEvents.RESPAWN_ANCHOR_CHARGE, SoundSource.BLOCKS, 1.0F, 1.0F);

        for (int i = 0; i < 25; i++) {
            final int step = i;
            SCHEDULER.schedule(() -> {
                _level.getServer().execute(() -> {
                    double radius = (step / 25.0) * 1.5;
                    _level.sendParticles(ParticleTypes.CHERRY_LEAVES, cx, cy, cz, 6, radius, 0.3, radius, 0.02);
                });
            }, i * 200L, TimeUnit.MILLISECONDS);
        }

        SCHEDULER.schedule(() -> {
            _level.getServer().execute(() -> {
                _level.sendParticles(ParticleTypes.CHERRY_LEAVES, cx, cy, cz, 40, 0.8, 0.8, 0.8, 0.05);

                // 1. 召唤 Sigvald
                if (sigvaldType != null) {
                    Entity sigvald = sigvaldType.create(_level);
                    if (sigvald != null) {
                        sigvald.moveTo(cx, pos.getY() + 1.0, cz, _level.getRandom().nextFloat() * 360F, 0.0F);
                        _level.addFreshEntity(sigvald);
                    }
                }

                // 2. 召唤 4 个 Silverguard 守护者（环绕祭坛四周分布）
                var guardType = HammercraftfantasyModEntities.SILVERGUARD.get();
                if (guardType != null) {
                    double[][] offsets = {{1.5, 0}, {-1.5, 0}, {0, 1.5}, {0, -1.5}};
                    for (double[] offset : offsets) {
                        Entity guard = guardType.create(_level);
                        if (guard != null) {
                            guard.moveTo(cx + offset[0], pos.getY() + 1.0, cz + offset[1], _level.getRandom().nextFloat() * 360F, 0.0F);
                            _level.addFreshEntity(guard);
                        }
                    }
                }

                player.sendSystemMessage(Component.literal("§dSigvald and his Silverguards have been awakened!"));

                // 启动针对 Sigvald 的单目标后台检测循环
                startBossCheckLoop(_level, pos, searchBox, sigvaldType);
            });
        }, 5000L, TimeUnit.MILLISECONDS);
    }

    private static void startBossCheckLoop(ServerLevel level, BlockPos pos, AABB searchBox, EntityType<?> sigvaldType) {
        final ScheduledFuture<?>[] futureHolder = new ScheduledFuture<?>[1];

        futureHolder[0] = SCHEDULER.scheduleAtFixedRate(() -> {
            level.getServer().execute(() -> {
                // 1. 检测 192 格内是否有活着的 Sigvald
                boolean hasSigvald = !level.getEntitiesOfClass(LivingEntity.class, searchBox, e -> e.getType() == sigvaldType && e.isAlive()).isEmpty();

                // 2. 若 Sigvald 死亡或不存在，将祭坛重置为 0 并终止检测线程
                if (!hasSigvald) {
                    BlockState currentState = level.getBlockState(pos);
                    if (currentState.getBlock().getStateDefinition().getProperty("s_chsumal") instanceof IntegerProperty _integerProp) {
                        level.setBlock(pos, currentState.setValue(_integerProp, 0), 3);
                    }
                    if (futureHolder[0] != null) {
                        futureHolder[0].cancel(false);
                    }
                }
            });
        }, 8, 5, TimeUnit.SECONDS);
    }
}