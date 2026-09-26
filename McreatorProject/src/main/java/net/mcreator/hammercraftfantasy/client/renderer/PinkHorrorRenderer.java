package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.PinkHorrorEntity;
import net.mcreator.hammercraftfantasy.client.model.Modelpink_horror;

import com.mojang.blaze3d.vertex.PoseStack;

public class PinkHorrorRenderer extends MobRenderer<PinkHorrorEntity, Modelpink_horror<PinkHorrorEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/pink_horror.png");

	public PinkHorrorRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelpink_horror<PinkHorrorEntity>(context.bakeLayer(Modelpink_horror.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(PinkHorrorEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(0.6f, 0.6f, 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(PinkHorrorEntity entity) {
		return entityTexture;
	}
}