/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.hammercraftfantasy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.hammercraftfantasy.HammercraftfantasyMod;

public class HammercraftfantasyModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, HammercraftfantasyMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PINK_HORROR_FIRE = REGISTRY.register("pink_horror_fire", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PINKFALME = REGISTRY.register("pinkfalme", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TZEENTCHSEYE = REGISTRY.register("tzeentchseye", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> NURGLEPUKE = REGISTRY.register("nurglepuke", () -> new SimpleParticleType(false));
}