package net.mcreator.hammercraftfantasy.potion;

import net.neoforged.neoforge.common.EffectCures;
import net.neoforged.neoforge.common.EffectCure;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import java.util.Set;

public class DazzleMobEffect extends MobEffect {
    public DazzleMobEffect() {
        super(MobEffectCategory.HARMFUL, 0xFFFFFF); 
    }

    @Override
    public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
        cures.add(EffectCures.MILK);
        cures.add(EffectCures.PROTECTED_BY_TOTEM);
        cures.add(EffectCures.HONEY);
    }
}