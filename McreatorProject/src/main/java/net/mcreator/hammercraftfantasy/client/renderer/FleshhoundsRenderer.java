package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.FleshhoundsEntity;
import net.mcreator.hammercraftfantasy.client.model.Modelflesh_hounds;

public class FleshhoundsRenderer extends MobRenderer<FleshhoundsEntity, Modelflesh_hounds<FleshhoundsEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/flesh_hounds_1.png");

	public FleshhoundsRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelflesh_hounds<FleshhoundsEntity>(context.bakeLayer(Modelflesh_hounds.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(FleshhoundsEntity entity) {
		return entityTexture;
	}
}