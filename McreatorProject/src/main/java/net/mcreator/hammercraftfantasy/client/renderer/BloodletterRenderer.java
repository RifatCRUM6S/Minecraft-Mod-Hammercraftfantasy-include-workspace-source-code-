package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.BloodletterEntity;
import net.mcreator.hammercraftfantasy.client.model.ModelBloodletter1;

public class BloodletterRenderer extends MobRenderer<BloodletterEntity, ModelBloodletter1<BloodletterEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/bloodletter.png");

	public BloodletterRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBloodletter1<BloodletterEntity>(context.bakeLayer(ModelBloodletter1.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(BloodletterEntity entity) {
		return entityTexture;
	}
}