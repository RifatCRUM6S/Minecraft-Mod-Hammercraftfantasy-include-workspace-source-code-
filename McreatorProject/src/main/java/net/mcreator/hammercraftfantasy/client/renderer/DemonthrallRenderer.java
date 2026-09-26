package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.DemonthrallEntity;
import net.mcreator.hammercraftfantasy.client.model.ModelBloodletter1;

import com.mojang.blaze3d.vertex.PoseStack;

public class DemonthrallRenderer extends MobRenderer<DemonthrallEntity, ModelBloodletter1<DemonthrallEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/herald_of_khorne.png");

	public DemonthrallRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelBloodletter1<DemonthrallEntity>(context.bakeLayer(ModelBloodletter1.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(DemonthrallEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
	}

	@Override
	public ResourceLocation getTextureLocation(DemonthrallEntity entity) {
		return entityTexture;
	}
}