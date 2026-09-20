package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.DeamonetteEntity;
import net.mcreator.hammercraftfantasy.client.model.Modeldeamonette;

public class DeamonetteRenderer extends MobRenderer<DeamonetteEntity, Modeldeamonette<DeamonetteEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/deamonette.png");

	public DeamonetteRenderer(EntityRendererProvider.Context context) {
		super(context, new Modeldeamonette<DeamonetteEntity>(context.bakeLayer(Modeldeamonette.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(DeamonetteEntity entity) {
		return entityTexture;
	}
}