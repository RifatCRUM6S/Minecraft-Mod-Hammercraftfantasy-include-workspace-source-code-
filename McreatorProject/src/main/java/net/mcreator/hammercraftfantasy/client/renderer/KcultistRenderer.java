package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.KcultistEntity;
import net.mcreator.hammercraftfantasy.client.model.Modelk_cultist;

public class KcultistRenderer extends MobRenderer<KcultistEntity, Modelk_cultist<KcultistEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/k_cultist.png");

	public KcultistRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelk_cultist<KcultistEntity>(context.bakeLayer(Modelk_cultist.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(KcultistEntity entity) {
		return entityTexture;
	}
}