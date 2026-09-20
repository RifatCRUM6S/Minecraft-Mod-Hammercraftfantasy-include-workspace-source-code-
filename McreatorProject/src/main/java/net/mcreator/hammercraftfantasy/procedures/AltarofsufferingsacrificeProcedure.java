package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;
import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModBlocks;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class AltarofsufferingsacrificeProcedure {

    // 延迟仪式任务队列
    private static final List<AltarRitualTask> ritualTasks = new ArrayList<>();

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() != null) {
            execute(event, event.getEntity().level(), event.getEntity(), event.getSource());
        }
    }

    public static void execute() {
        execute(null, null, null, null);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, DamageSource damageSource) {
        if (entity == null || world == null || !(world instanceof ServerLevel _level))
            return;

        // 校验伤害来源：必须由玩家击杀
        Entity attacker = damageSource != null ? damageSource.getEntity() : null;
        if (!(attacker instanceof Player player)) {
            return;
        }

        // 校验武器：玩家主手必须持有仪式匕首
        if (player.getMainHandItem().getItem() != HammercraftfantasyModItems.RITUALDAGGER.get()) {
            return;
        }

        // 防重锁判断：如果已经处理过，直接跳过
        if (entity.getPersistentData().getBoolean("sacrificed_by_dagger")) {
            return;
        }

        // 判断死者是否为驯服的宠物
        boolean isPet = false;
        if (entity instanceof TamableAnimal tamable && tamable.isTame()) {
            isPet = true;
        } else if (entity instanceof AbstractHorse horse && horse.isTamed()) {
            isPet = true;
        } else if (entity instanceof OwnableEntity ownable && ownable.getOwnerUUID() != null) {
            isPet = true;
        } else if (entity.getPersistentData().contains("Owner") || entity.getPersistentData().contains("OwnerUUID")) {
            isPet = true;
        }

        // 如果是宠物，执行痛苦祭坛献祭逻辑
        if (isPet) {
            entity.getPersistentData().putBoolean("sacrificed_by_dagger", true);
            BlockPos petPos = entity.blockPosition();
            int searchRadius = 5;

            for (int sx = -searchRadius; sx <= searchRadius; sx++) {
                for (int sy = -searchRadius; sy <= searchRadius; sy++) {
                    for (int sz = -searchRadius; sz <= searchRadius; sz++) {
                        BlockPos altarPos = petPos.offset(sx, sy, sz);
                        BlockState state = _level.getBlockState(altarPos);

                        if (state.getBlock() == HammercraftfantasyModBlocks.ALTAROFSUFFERING.get()) {
                            BlockEntity be = _level.getBlockEntity(altarPos);

                            int stateCharge = 0;
                            if (state.getBlock().getStateDefinition().getProperty("chargelevel") instanceof IntegerProperty _integerProp) {
                                stateCharge = state.getValue(_integerProp);
                            }

                            double nbtCharge = 0;
                            if (be != null) {
                                nbtCharge = Math.max(be.getPersistentData().getDouble("chargelevel"), be.getPersistentData().getDouble("charge_count"));
                            }

                            // 判断祭坛充能等级是否满 4 层
                            if (stateCharge >= 4 || nbtCharge >= 4) {
                                // 1. 重置 NBT 与 BlockState 充能
                                if (be != null) {
                                    be.getPersistentData().putDouble("chargelevel", 0);
                                    be.getPersistentData().putDouble("charge_count", 0);
                                }
                                if (state.getBlock().getStateDefinition().getProperty("chargelevel") instanceof IntegerProperty _integerProp) {
                                    _level.setBlock(altarPos, state.setValue(_integerProp, 0), 3);
                                }

                                // 2. 仪式启动音效与爆发粒子
                                _level.playSound(null, altarPos, SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 1.0F, 1.0F);
                                _level.sendParticles(ParticleTypes.EXPLOSION, altarPos.getX() + 0.5, altarPos.getY() + 1.2, altarPos.getZ() + 0.5, 3, 0.2, 0.2, 0.2, 0.1);

                                // 3. 提示玩家
                                player.sendSystemMessage(Component.literal("§cThe Altar of suffering has received the sacrifice! The ritual commences.."));

                                // 4. 添加 100 刻 (5秒) 延迟任务
                                ritualTasks.add(new AltarRitualTask(_level, altarPos, 100));
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 服务端 Tick 监听：负责持续 5 秒的粒子特效与物品吐出
     */
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (ritualTasks.isEmpty()) return;

        List<AltarRitualTask> toRemove = new ArrayList<>();
        for (AltarRitualTask task : ritualTasks) {
            task.tick--;

            // 祭坛上方持续产生粒子特效（5秒全过程）
            task.level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, task.x, task.y, task.z, 12, 0.3, 0.5, 0.3, 0.03);
            task.level.sendParticles(ParticleTypes.WITCH, task.x, task.y, task.z, 8, 0.4, 0.4, 0.4, 0.05);
            task.level.sendParticles(ParticleTypes.SMOKE, task.x, task.y, task.z, 5, 0.2, 0.3, 0.2, 0.02);

            // 倒计时结束 (5秒到达)
            if (task.tick <= 0) {
                _levelPlayFinishEffects(task.level, task.pos);

                ItemEntity keyEntity = new ItemEntity(task.level, task.x, task.y + 0.2, task.z, new ItemStack(HammercraftfantasyModItems.KEYOFPALACE.get()));
                // 给予向上的抛出速度
                keyEntity.setDeltaMovement(0, 0.25, 0);
                keyEntity.setPickUpDelay(10);
                task.level.addFreshEntity(keyEntity);

                toRemove.add(task);
            }
        }
        ritualTasks.removeAll(toRemove);
    }

    private static void _levelPlayFinishEffects(ServerLevel level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.5F);
        level.sendParticles(ParticleTypes.FLASH, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 1, 0, 0, 0, 0);
    }

    /**
     * 祭坛延迟仪式任务结构类
     */
    private static class AltarRitualTask {
        ServerLevel level;
        BlockPos pos;
        double x, y, z;
        int tick;

        AltarRitualTask(ServerLevel level, BlockPos pos, int tick) {
            this.level = level;
            this.pos = pos;
            this.x = pos.getX() + 0.5;
            this.y = pos.getY() + 1.2; // 粒子中心点
            this.z = pos.getZ() + 0.5;
            this.tick = tick;
        }
    }
}