package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.HeraldofKhorneEntity;
import net.mcreator.hammercraftfantasy.client.model.ModelBloodletter1;

public class HeraldofKhorneRenderer extends MobRenderer<HeraldofKhorneEntity, ModelBloodletter1<HeraldofKhorneEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/herald_of_khorne.png");

	public HeraldofKhorneRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBloodletter1<HeraldofKhorneEntity>(context.bakeLayer(ModelBloodletter1.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(HeraldofKhorneEntity entity) {
		return entityTexture;
	}
}