/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.hammercraftfantasy.client.particle.TzeentchseyeParticle;
import net.mcreator.hammercraftfantasy.client.particle.PinkfalmeParticle;
import net.mcreator.hammercraftfantasy.client.particle.PinkHorrorFireParticle;
import net.mcreator.hammercraftfantasy.client.particle.NurglepukeParticle;

@EventBusSubscriber(Dist.CLIENT)
public class HammercraftfantasyModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(HammercraftfantasyModParticleTypes.PINK_HORROR_FIRE.get(), PinkHorrorFireParticle::provider);
		event.registerSpriteSet(HammercraftfantasyModParticleTypes.PINKFALME.get(), PinkfalmeParticle::provider);
		event.registerSpriteSet(HammercraftfantasyModParticleTypes.TZEENTCHSEYE.get(), TzeentchseyeParticle::provider);
		event.registerSpriteSet(HammercraftfantasyModParticleTypes.NURGLEPUKE.get(), NurglepukeParticle::provider);
	}
}