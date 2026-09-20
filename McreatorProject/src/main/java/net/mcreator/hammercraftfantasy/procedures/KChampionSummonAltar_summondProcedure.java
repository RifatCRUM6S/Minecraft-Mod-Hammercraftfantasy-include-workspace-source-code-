package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
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

public class KChampionSummonAltar_summondProcedure {

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
        if (!mainHand.is(HammercraftfantasyModItems.KEYOFTHRONE.get())) {
            return;
        }

        BlockPos pos = BlockPos.containing(x, y, z);
        BlockState state = _level.getBlockState(pos);

        if (state.getBlock().getStateDefinition().getProperty("k_chsumal") instanceof IntegerProperty _integerProp) {
            if (state.getValue(_integerProp) == 1) {
                player.sendSystemMessage(Component.literal("§cThis altar's power has been exhausted!"));
                return;
            }
        }

        double searchRadius = 192.0;
        AABB searchBox = new AABB(x - searchRadius, y - 64, z - searchRadius, x + searchRadius, y + 64, z + searchRadius);

        var valkiaType = HammercraftfantasyModEntities.VALKIA.get();
        if (valkiaType != null) {
            boolean hasValkia = !_level.getEntitiesOfClass(LivingEntity.class, searchBox, e -> e.getType() == valkiaType && e.isAlive()).isEmpty();
            if (hasValkia) {
                player.sendSystemMessage(Component.literal("§cValkia already exists in this area!"));
                return;
            }
        }

        if (!player.getAbilities().instabuild) {
            mainHand.shrink(1);
        }

        if (state.getBlock().getStateDefinition().getProperty("k_chsumal") instanceof IntegerProperty _integerProp) {
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
                    _level.sendParticles(ParticleTypes.FLAME, cx, cy, cz, 4, radius, 0.2, radius, 0.02);
                    _level.sendParticles(ParticleTypes.SMOKE, cx, cy, cz, 2, radius * 0.8, 0.3, radius * 0.8, 0.01);
                });
            }, i * 200L, TimeUnit.MILLISECONDS);
        }

        SCHEDULER.schedule(() -> {
            _level.getServer().execute(() -> {
                _level.sendParticles(ParticleTypes.LARGE_SMOKE, cx, cy, cz, 20, 0.5, 0.5, 0.5, 0.05);

                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(_level);
                if (lightning != null) {
                    lightning.moveTo(cx, pos.getY() + 1.0, cz);
                    lightning.setVisualOnly(true);
                    _level.addFreshEntity(lightning);
                }

                if (valkiaType != null) {
                    Entity valkia = valkiaType.create(_level);
                    if (valkia != null) {
                        valkia.moveTo(cx, pos.getY() + 1.0, cz, _level.getRandom().nextFloat() * 360F, 0.0F);
                        _level.addFreshEntity(valkia);
                    }
                }

                player.sendSystemMessage(Component.literal("§4Valkia has been awakened!"));

                // 🌟 核心改进：召唤成功后，直接启动后台循环检测（每 5 秒查一次 BOSS 存活）
                startBossCheckLoop(_level, pos, searchBox, valkiaType);
            });
        }, 5000L, TimeUnit.MILLISECONDS);
    }

    private static void startBossCheckLoop(ServerLevel level, BlockPos pos, AABB searchBox, EntityType<?> valkiaType) {
        final ScheduledFuture<?>[] futureHolder = new ScheduledFuture<?>[1];
        
        futureHolder[0] = SCHEDULER.scheduleAtFixedRate(() -> {
            level.getServer().execute(() -> {
                // 1. 检查范围内的活体 Valkia
                boolean hasValkia = !level.getEntitiesOfClass(LivingEntity.class, searchBox, e -> e.getType() == valkiaType && e.isAlive()).isEmpty();

                // 2. 如果 BOSS 已经死亡或不存在，重置祭坛并终结此检测线程
                if (!hasValkia) {
                    BlockState currentState = level.getBlockState(pos);
                    if (currentState.getBlock().getStateDefinition().getProperty("k_chsumal") instanceof IntegerProperty _integerProp) {
                        level.setBlock(pos, currentState.setValue(_integerProp, 0), 3);
                    }
                    if (futureHolder[0] != null) {
                        futureHolder[0].cancel(false);
                    }
                }
            });
        }, 8, 5, TimeUnit.SECONDS); // 8 秒后启动首次检查（跳过刚生成的安全期），之后每 5 秒检查一次
    }
}