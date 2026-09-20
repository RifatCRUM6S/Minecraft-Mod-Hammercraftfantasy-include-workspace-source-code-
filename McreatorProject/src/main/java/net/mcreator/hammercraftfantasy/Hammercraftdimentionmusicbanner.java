package net.mcreator.hammercraftfantasy;

import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.client.event.SelectMusicEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

@EventBusSubscriber(modid = "hammercraftfantasy", value = Dist.CLIENT)
public class Hammercraftdimentionmusicbanner {

    // 需要禁用原版音乐的维度命名空间与 Path 列表
    private static final Set<ResourceLocation> BANNED_DIMENSIONS = Set.of(
            ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "oceanofsouls"),
            ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "championsthrone"),
            ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "championsgarden"),
            ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "championslibrary"),
            ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "championspalace")
    );

    public Hammercraftdimentionmusicbanner() {
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        new Hammercraftdimentionmusicbanner();
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
    }

    // --- 核心维度拦截逻辑 ---

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onSelectMusic(SelectMusicEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        // 获取玩家当前所在维度的 ResourceLocation 标识
        ResourceLocation currentDimension = mc.level.dimension().location();

        // 如果处于指定的 5 个维度之一，强制取消原版音乐选择
        if (BANNED_DIMENSIONS.contains(currentDimension)) {
            event.setMusic(null);
        }
    }

    @EventBusSubscriber
    private static class HammercraftdimentionmusicbannerForgeBusEvents {
        @SubscribeEvent
        public static void serverLoad(ServerStartingEvent event) {
        }
    }
}