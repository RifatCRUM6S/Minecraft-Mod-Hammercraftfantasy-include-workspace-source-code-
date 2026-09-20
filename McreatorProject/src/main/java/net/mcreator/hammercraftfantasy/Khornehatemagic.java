package net.mcreator.hammercraftfantasy;

import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@EventBusSubscriber(modid = "hammercraftfantasy")
public class Khornehatemagic {

    private static final Random RANDOM = new Random();
    
    // 记录玩家上一 Tick 的施法状态，防止同一次施法连续触发几十次雷劈
    private static final Map<UUID, Boolean> WAS_CASTING_MAP = new HashMap<>();

    private static final ResourceLocation KTHRONE_BIOME = ResourceLocation.parse("hammercraftfantasy:kthrone");
    private static final List<ResourceLocation> KHORNE_PUNISH_BIOMES = List.of(
            ResourceLocation.parse("hammercraftfantasy:bloodwastes"),
            ResourceLocation.parse("hammercraftfantasy:bloodhill"),
            ResourceLocation.parse("hammercraftfantasy:bloodforest1"),
            ResourceLocation.parse("hammercraftfantasy:bloodforest2")
    );

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        // 只在服务端逻辑运行
        if (event.getEntity().level().isClientSide()) return;

        if (event.getEntity() instanceof ServerPlayer player) {
            boolean isCastingNow = isPlayerCastingSpell(player);
            UUID uuid = player.getUUID();
            boolean wasCasting = WAS_CASTING_MAP.getOrDefault(uuid, false);

            // 捕捉“按下快捷键/开始施法”的瞬间（从未施法变为正在施法）
            if (isCastingNow && !wasCasting) {
                handleKhorneWrath(player);
            }

            // 更新玩家施法状态缓存
            WAS_CASTING_MAP.put(uuid, isCastingNow);
        }
    }

    /**
     * 底层检测：判断玩家是否处于铁魔法的施法状态中
     */
    private static boolean isPlayerCastingSpell(ServerPlayer player) {
        // 方法 1：通过 NBT 持久化数据检测（铁魔法会将当前施法数据写入玩家的 PersistentData/EntityData）
        CompoundTag persistentData = player.getPersistentData();
        if (persistentData.contains("irons_spellbooks")) {
            CompoundTag isData = persistentData.getCompound("irons_spellbooks");
            if (isData.contains("isCasting") && isData.getBoolean("isCasting")) {
                return true;
            }
            if (isData.contains("castDuration") && isData.getInt("castDuration") > 0) {
                return true;
            }
        }

        // 方法 2：动态反射获取 MagicData（兼容不同版本的内存状态）
        try {
            Class<?> magicDataClass = Class.forName("io.redspace.ironsspellbooks.api.magic.MagicData");
            Method getMethod = magicDataClass.getMethod("getPlayerMagicData", net.minecraft.world.entity.LivingEntity.class);
            Object magicData = getMethod.invoke(null, player);

            if (magicData != null) {
                Method isCastingMethod = magicDataClass.getMethod("isCasting");
                return (boolean) isCastingMethod.invoke(magicData);
            }
        } catch (Exception ignored) {
            // 反射失败时优雅降级，不抛出崩游戏异常
        }

        return false;
    }

    private static void handleKhorneWrath(ServerPlayer player) {
        ServerLevel level = player.serverLevel();
        Holder<Biome> currentBiomeHolder = level.getBiome(player.blockPosition());

        ResourceLocation currentBiomeLoc = currentBiomeHolder.unwrapKey()
                .map(ResourceKey::location)
                .orElse(null);

        if (currentBiomeLoc == null) return;

        // 1. 特殊群系：kthrone - 100% 触发雷劈与高额伤害
        if (currentBiomeLoc.equals(KTHRONE_BIOME)) {
            strikeLightning(level, player.position(), player);
            applyKhorneDamage(player, 500.0F);
            return;
        }

        // 2. 惩罚群系：bloodwastes, bloodhill, bloodforest1, bloodforest2
        if (KHORNE_PUNISH_BIOMES.contains(currentBiomeLoc)) {
            double roll = RANDOM.nextDouble();

            if (roll < 0.50) {
                // 50% 概率：劈在玩家附近（偏离 3~6 格）
                double offsetX = (RANDOM.nextBoolean() ? 1 : -1) * (3.0 + RANDOM.nextDouble() * 3.0);
                double offsetZ = (RANDOM.nextBoolean() ? 1 : -1) * (3.0 + RANDOM.nextDouble() * 3.0);
                Vec3 strikePos = player.position().add(offsetX, 0, offsetZ);

                strikeLightning(level, strikePos, null);
            } else if (roll < 0.75) {
                // 25% 概率：劈中玩家并造成 15 点伤害
                strikeLightning(level, player.position(), player);
                applyKhorneDamage(player, 15.0F);
            } else {
                // 25% 概率：劈中玩家并造成 500 点伤害
                strikeLightning(level, player.position(), player);
                applyKhorneDamage(player, 500.0F);
            }
        }
    }

    private static void strikeLightning(ServerLevel level, Vec3 pos, ServerPlayer targetEntity) {
        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        if (bolt != null) {
            bolt.moveTo(pos.x, pos.y, pos.z);
            if (targetEntity != null) {
                bolt.setCause(targetEntity);
            }
            bolt.setVisualOnly(false);
            level.addFreshEntity(bolt);
        }
    }

    private static void applyKhorneDamage(ServerPlayer player, float amount) {
        player.hurt(player.damageSources().generic(), amount);
    }
}