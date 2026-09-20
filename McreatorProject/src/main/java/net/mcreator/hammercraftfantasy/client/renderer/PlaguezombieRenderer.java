package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.PlaguezombieEntity;
import net.mcreator.hammercraftfantasy.client.model.Modelplaguezombie;

public class PlaguezombieRenderer extends MobRenderer<PlaguezombieEntity, Modelplaguezombie<PlaguezombieEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/plaguezombie.png");

	public PlaguezombieRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelplaguezombie<PlaguezombieEntity>(context.bakeLayer(Modelplaguezombie.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(PlaguezombieEntity entity) {
		return entityTexture;
	}
}