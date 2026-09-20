package net.mcreator.hammercraftfantasy.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementHolder;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModEntities;
import net.mcreator.hammercraftfantasy.entity.SkarbrandEntity;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class Ritualdagger_whitchturnspawnProcedure {

    // 存储斯卡布兰德召唤倒计时任务队列
    private static final List<SummonDelayTask> delayTasks = new ArrayList<>();

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
        if (entity == null || !(world instanceof ServerLevel _level))
            return;

        ResourceKey<Level> currentDimension = _level.dimension();

        // ==========================================
        // 功能 1：主世界献祭女巫召唤混沌卵并解锁成就
        // ==========================================
        if (currentDimension == Level.OVERWORLD) {
            if (entity instanceof Witch witch) {
                int activeEffectCount = witch.getActiveEffects().size();
                if (activeEffectCount >= 5) {
                    witch.discard();

                    Entity entityToSpawn = HammercraftfantasyModEntities.CHAOSSPAWN.get().spawn(_level, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
                    if (entityToSpawn != null) {
                        entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
                    }

                    // 播放基础生物音效
                    try {
                        _level.playSound(null, BlockPos.containing(x, y, z),
                            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "espawn_breath")),
                            SoundSource.HOSTILE, 1.0f, 1.0f);
                    } catch (Exception ignored) {}

                    // 解锁成就：twistedblessing
                    Entity playerEntity = sourceentity != null ? sourceentity : _level.getNearestPlayer(x, y, z, 10, false);
                    if (playerEntity instanceof ServerPlayer _player) {
                        AdvancementHolder _adv = _player.server.getAdvancements().get(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "twistedblessing"));
                        if (_adv != null) {
                            AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
                            if (!_ap.isDone()) {
                                for (String criterion : _ap.getRemainingCriteria()) {
                                    _player.getAdvancements().award(_adv, criterion);
                                }
                            }
                        }
                    }
                }
            }
        }
        // ==========================================
        // 功能 2：混沌魔域献祭大师级村民召唤斯卡布兰德
        // ==========================================
        else if (currentDimension == ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "oceanofsouls"))) {
            if (entity instanceof Villager villager) {
                // 1. 检查村民等级：等级 >= 5 即为大师级村民
                if (villager.getVillagerData().getLevel() >= 5) {
                    BlockPos targetPos = villager.blockPosition();

                    // 2. 祭坛检测：统计周围半径 8 格区域内的头颅方块总数是否 >= 64
                    if (countSkullsInRadius(_level, targetPos, 8) >= 64) {
                        
                        // 3. 对村民造成 500 点真实/魔法伤害杀死村民，而不是直接 discard()
                        villager.hurt(_level.damageSources().magic(), 500.0f);

                        // 4. 添加 120 刻 (6秒) 延迟召唤任务
                        delayTasks.add(new SummonDelayTask(_level, targetPos, 120));
                    }
                }
            }
        }
    }

    // 兼容重载方法
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        execute(world, x, y, z, entity, null);
    }

    /**
     * 检查以 pos 为中心、指定半径（radius）的立方体区域内所有的头颅（SkullBlock）数量
     */
    private static int countSkullsInRadius(Level level, BlockPos center, int radius) {
        int skullCount = 0;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    mutablePos.set(center.getX() + x, center.getY() + y, center.getZ() + z);
                    BlockState state = level.getBlockState(mutablePos);
                    
                    // 匹配任意继承自 SkullBlock 的头颅/颅骨方块
                    if (state.getBlock() instanceof SkullBlock) {
                        skullCount++;
                    }
                }
            }
        }
        return skullCount;
    }

    /**
     * 服务端 Tick 事件监听：负责处理斯卡布兰德召唤倒计时、粒子特效、落雷与最终生成
     */
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        if (delayTasks.isEmpty()) return;

        List<SummonDelayTask> toRemove = new ArrayList<>();
        for (SummonDelayTask task : delayTasks) {
            task.tick--;

            // 混合粒子特效：灵魂火 + 普通火焰
            task.level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, task.x, task.y, task.z, 10, 1.2, 1.0, 1.2, 0.05);
            task.level.sendParticles(ParticleTypes.FLAME, task.x, task.y, task.z, 10, 1.2, 1.0, 1.2, 0.05);

            // 剩余 5 tick 时引发闪电
            if (task.tick == 5) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(task.level);
                if (lightning != null) {
                    lightning.moveTo(task.x, task.y, task.z);
                    task.level.addFreshEntity(lightning);
                }
            }

            // 倒计时结束：召唤斯卡布兰德
            if (task.tick <= 0) {
                SkarbrandEntity boss = new SkarbrandEntity(HammercraftfantasyModEntities.SKARBRAND.get(), task.level);
                boss.moveTo(task.x, task.y, task.z, 0, 0);

                task.level.addFreshEntity(boss);
                toRemove.add(task);
            }
        }
        delayTasks.removeAll(toRemove);
    }

    /**
     * 斯卡布兰德延迟召唤任务结构类
     */
    private static class SummonDelayTask {
        ServerLevel level;
        double x, y, z;
        int tick;

        SummonDelayTask(ServerLevel level, BlockPos pos, int tick) {
            this.level = level;
            this.x = pos.getX() + 0.5;
            this.y = pos.getY() + 0.5;
            this.z = pos.getZ() + 0.5;
            this.tick = tick;
        }
    }
}