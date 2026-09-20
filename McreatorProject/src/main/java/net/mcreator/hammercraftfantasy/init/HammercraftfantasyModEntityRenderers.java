/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

import net.mcreator.hammercraftfantasy.client.renderer.*;

@EventBusSubscriber(Dist.CLIENT)
public class HammercraftfantasyModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(HammercraftfantasyModEntities.SKARBRAND.get(), SkarbrandRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.BLOODLETTER.get(), BloodletterRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.UNKNOW_BEAUTY.get(), UnknowBeautyRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.DEMONEZ.get(), DemonezRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.HERALDOF_KHORNE.get(), HeraldofKhorneRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.NURGLING.get(), NurglingRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.PINK_HORROR.get(), PinkHorrorRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.BLUE_HORROR.get(), BlueHorrorRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.PINK_HORROR_ARROW.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.FLESHHOUNDS.get(), FleshhoundsRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.KCHAOSWARRIOR.get(), KchaoswarriorRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.KCULTIST.get(), KcultistRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.ROTFLY.get(), RotflyRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.PLAGUEBEARER.get(), PlaguebearerRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.GREATUNCLEANONE.get(), GreatuncleanoneRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.ROTMAGGOT.get(), RotmaggotRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.PLAGUEZOMBIE.get(), PlaguezombieRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.NAGOXO.get(), NagoxoRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.CHAOSSPAWN.get(), ChaosspawnRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.FORSAKEN.get(), ForsakenRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.FIENDOFSLAANESH.get(), FiendofslaaneshRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.DEAMONETTE.get(), DeamonetteRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.STEEDOFSLAANESH.get(), SteedofslaaneshRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.TAMURKHAN.get(), TamurkhanRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.VALKIA.get(), ValkiaRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.VALKIASSPEAR.get(), ValkiasspearRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.SIGVALD.get(), SigvaldRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.SILVERGUARD.get(), SilverguardRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.SCREAMER.get(), ScreamerRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.FLAMER.get(), FlamerRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.TZAANGOR.get(), TzaangorRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.ERIC.get(), EricRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.ERICSPAWN.get(), EricspawnRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.MAGGOTLORD.get(), MaggotlordRenderer::new);
		event.registerEntityRenderer(HammercraftfantasyModEntities.TZAANGORHALBERD.get(), TzaangorhalberdRenderer::new);
	}
}