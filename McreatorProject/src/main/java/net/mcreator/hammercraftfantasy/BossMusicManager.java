package net.mcreator.hammercraftfantasy;

import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.SelectMusicEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = "hammercraftfantasy", value = Dist.CLIENT)
public class BossMusicManager {

    private static final TagKey<EntityType<?>> BOSS_TAG = TagKey.create(
            Registries.ENTITY_TYPE, 
            ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "boss_with_music")
    );

    private static final Map<ResourceLocation, ResourceLocation> BOSS_MUSIC_MAP = new HashMap<>();

    static {
        register("skarbrand", "skarbrand_music_1");
        register("greatuncleanone", "darktide40kmusic_imperial_advance");
        register("tamurkhan", "tamur_music");
        register("valkia", "valkia_music");
        register("sigvald", "sigvald_music");
        register("ericspawn", "spawn_music");
        register("eric", "eric_music");
    }

    private static void register(String entityPath, String soundPath) {
        BOSS_MUSIC_MAP.put(
                ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", entityPath),
                ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", soundPath)
        );
    }

    private static AbstractSoundInstance currentSoundInstance = null;
    private static ResourceLocation currentPlayingMusicId = null;
    
    private static int scanTimer = 0;
    private static LivingEntity cachedBoss = null;

    public BossMusicManager() {
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
    }

    // --- 核心客户端音效逻辑 ---

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        
        // 1. 退出世界、死亡或场景为空时，清理音乐
        if (mc.player == null || mc.level == null || !mc.player.isAlive()) {
            resetAll();
            return;
        }

        scanTimer++;
        if (scanTimer >= 10) {
            scanTimer = 0;

            // 2. 校验缓存的 Boss 是否依然有效（维度校验 + 客户端真正存在性校验）
            if (cachedBoss != null) {
                boolean isSameLevel = cachedBoss.level() == mc.level;
                boolean isEntityValid = cachedBoss.isAlive() && !cachedBoss.isRemoved() && mc.level.getEntity(cachedBoss.getId()) == cachedBoss;
                
                if (isSameLevel && isEntityValid) {
                    // 48 格离开判定缓冲区
                    double distanceSqr = mc.player.distanceToSqr(cachedBoss);
                    if (distanceSqr > 2304.0D) { // 48 * 48 = 2304
                        cachedBoss = null;
                    }
                } else {
                    // 如果跨维度、实体死亡或被指令清理，直接清除缓存
                    cachedBoss = null;
                }
            }

            // 3. 寻找 40 格内的 Boss
            if (cachedBoss == null) {
                cachedBoss = mc.level.getEntitiesOfClass(
                        LivingEntity.class,
                        mc.player.getBoundingBox().inflate(40.0D),
                        e -> e.isAlive() && !e.isRemoved() && e.getType().is(BOSS_TAG)
                ).stream().findFirst().orElse(null);
            }
        }

        // 4. 音频状态更新
        if (cachedBoss != null && cachedBoss.isAlive() && !cachedBoss.isRemoved()) {
            ResourceLocation entityKey = BuiltInRegistries.ENTITY_TYPE.getKey(cachedBoss.getType());
            ResourceLocation musicId = BOSS_MUSIC_MAP.get(entityKey);

            if (musicId != null) {
                playBossMusic(musicId);
            } else {
                stopBossMusic();
            }
        } else {
            stopBossMusic();
        }
    }

    @OnlyIn(Dist.CLIENT)
    private static void playBossMusic(ResourceLocation musicId) {
        SoundManager soundManager = Minecraft.getInstance().getSoundManager();

        // 防重复播放
        if (currentSoundInstance != null && musicId.equals(currentPlayingMusicId) && soundManager.isActive(currentSoundInstance)) {
            return;
        }

        stopBossMusic();

        soundManager.stop(null, SoundSource.MUSIC);
        soundManager.stop(null, SoundSource.AMBIENT);

        currentSoundInstance = SimpleSoundInstance.forMusic(BuiltInRegistries.SOUND_EVENT.get(musicId));
        currentPlayingMusicId = musicId;
        soundManager.play(currentSoundInstance);
    }

    @OnlyIn(Dist.CLIENT)
    public static void stopBossMusic() {
        if (currentSoundInstance != null) {
            Minecraft.getInstance().getSoundManager().stop(currentSoundInstance);
            currentSoundInstance = null;
            currentPlayingMusicId = null;
        }
    }

    // 状态全局重置辅助函数
    @OnlyIn(Dist.CLIENT)
    private static void resetAll() {
        stopBossMusic();
        cachedBoss = null;
        scanTimer = 0;
    }

    // 监听玩家登出 / 切换维度重置状态
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onLoggingOut(ClientPlayerNetworkEvent.LoggingOut event) {
        resetAll();
    }

    // 拦截原版背景音乐
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onSelectMusic(SelectMusicEvent event) {
        if (currentSoundInstance != null && Minecraft.getInstance().getSoundManager().isActive(currentSoundInstance)) {
            event.setMusic(null);
        }
    }
}