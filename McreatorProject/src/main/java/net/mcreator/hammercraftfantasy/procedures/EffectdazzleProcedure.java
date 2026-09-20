package net.mcreator.hammercraftfantasy.procedures;

import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModMobEffects;

import javax.annotation.Nullable;

@EventBusSubscriber(value = Dist.CLIENT)
public class EffectdazzleProcedure {
    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        execute(event);
    }

    public static void execute() {
        execute(null);
    }

    private static void execute(@Nullable Event event) {
        if (event instanceof RenderGuiEvent.Post guiEvent) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;

            if (player != null && player.hasEffect(HammercraftfantasyModMobEffects.DAZZLE.getDelegate())) {
                MobEffectInstance effect = player.getEffect(HammercraftfantasyModMobEffects.DAZZLE.getDelegate());
                if (effect == null) return;

                int duration = effect.getDuration();
                // 1.21.1 适配：从 DeltaTracker 中提取 partialTick (float)
                float partialTick = guiEvent.getPartialTick().getGameTimeDeltaPartialTick(true);
                float ticks = mc.level.getGameTime() + partialTick;

                // 类似原版黑暗效果的呼吸脉动感 (0.0 ~ 1.0)
                float pulse = (Mth.sin(ticks * 0.15F) + 1.0F) * 0.5F;
                
                // Alpha 透明度在 85% ~ 98% 之间起伏
                float alpha = 0.55F + pulse * 0.33F;

                // 效果结束前最后 1 秒 (20 ticks) 平滑淡出
                if (duration < 20) {
                    alpha *= (duration / 20.0F);
                }

                int colorAlpha = (int) (alpha * 255.0F) << 24;
                int whiteColor = colorAlpha | 0xFFFFFF; // 纯白色 + 动态 Alpha

                GuiGraphics guiGraphics = guiEvent.getGuiGraphics();
                int width = mc.getWindow().getGuiScaledWidth();
                int height = mc.getWindow().getGuiScaledHeight();

                // 绘制覆盖全屏的纯白目眩 Overlay
                guiGraphics.fill(0, 0, width, height, whiteColor);
            }
        }
    }
}