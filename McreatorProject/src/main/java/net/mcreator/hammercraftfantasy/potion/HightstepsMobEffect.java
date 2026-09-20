package net.mcreator.hammercraftfantasy.potion;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.hammercraftfantasy.procedures.HightstepsZhuangTaiXiaoGuoKaiShiYingYongShiProcedure;

public class HightstepsMobEffect extends MobEffect {
	public HightstepsMobEffect() {
		super(MobEffectCategory.NEUTRAL, -6180174);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		HightstepsZhuangTaiXiaoGuoKaiShiYingYongShiProcedure.execute(entity);
	}
}