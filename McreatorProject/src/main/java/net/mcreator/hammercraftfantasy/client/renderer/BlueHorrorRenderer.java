package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.BlueHorrorEntity;
import net.mcreator.hammercraftfantasy.client.model.ModelBlue_horror;

import com.mojang.blaze3d.vertex.PoseStack;

public class BlueHorrorRenderer extends MobRenderer<BlueHorrorEntity, ModelBlue_horror<BlueHorrorEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/blue_horror_skin.png");

	public BlueHorrorRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBlue_horror<BlueHorrorEntity>(context.bakeLayer(ModelBlue_horror.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(BlueHorrorEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(0.6f, 0.6f, 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(BlueHorrorEntity entity) {
		return entityTexture;
	}
}