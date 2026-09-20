package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.SilverguardEntity;
import net.mcreator.hammercraftfantasy.client.model.Modelsilverguard;

public class SilverguardRenderer extends MobRenderer<SilverguardEntity, Modelsilverguard<SilverguardEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/silverguard_skin.png");

	public SilverguardRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelsilverguard<SilverguardEntity>(context.bakeLayer(Modelsilverguard.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(SilverguardEntity entity) {
		return entityTexture;
	}
}