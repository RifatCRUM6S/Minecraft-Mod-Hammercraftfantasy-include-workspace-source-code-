/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.hammercraftfantasy.procedures.HightstepsZhuangTaiXiaoGuoJieShuShiProcedure;
import net.mcreator.hammercraftfantasy.potion.HightstepsMobEffect;
import net.mcreator.hammercraftfantasy.potion.DazzleMobEffect;
import net.mcreator.hammercraftfantasy.HammercraftfantasyMod;

@EventBusSubscriber
public class HammercraftfantasyModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, HammercraftfantasyMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> DAZZLE = REGISTRY.register("dazzle", () -> new DazzleMobEffect());
	public static final DeferredHolder<MobEffect, MobEffect> HIGHTSTEPS = REGISTRY.register("hightsteps", () -> new HightstepsMobEffect());

	@SubscribeEvent
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	private static void expireEffects(Entity entity, MobEffectInstance effectInstance) {
		if (effectInstance.getEffect().is(HIGHTSTEPS)) {
			HightstepsZhuangTaiXiaoGuoJieShuShiProcedure.execute(entity);
		}
	}
}