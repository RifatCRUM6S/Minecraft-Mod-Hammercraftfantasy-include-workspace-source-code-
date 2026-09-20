package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.PlaguebearerEntity;
import net.mcreator.hammercraftfantasy.client.model.ModelPlaguebearer;

public class PlaguebearerRenderer extends MobRenderer<PlaguebearerEntity, ModelPlaguebearer<PlaguebearerEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/plaguebearer_skin1.png");

	public PlaguebearerRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelPlaguebearer<PlaguebearerEntity>(context.bakeLayer(ModelPlaguebearer.LAYER_LOCATION)), 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(PlaguebearerEntity entity) {
		return entityTexture;
	}
}