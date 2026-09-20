package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.TzaangorEntity;
import net.mcreator.hammercraftfantasy.client.model.Modeltzaangor;

public class TzaangorRenderer extends MobRenderer<TzaangorEntity, Modeltzaangor<TzaangorEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/tzaangor_skin.png");

	public TzaangorRenderer(EntityRendererProvider.Context context) {
		super(context, new Modeltzaangor<TzaangorEntity>(context.bakeLayer(Modeltzaangor.LAYER_LOCATION)), 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(TzaangorEntity entity) {
		return entityTexture;
	}
}