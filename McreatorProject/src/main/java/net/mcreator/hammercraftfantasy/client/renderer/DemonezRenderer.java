package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.DemonezEntity;
import net.mcreator.hammercraftfantasy.client.model.ModelDemonez2;

public class DemonezRenderer extends MobRenderer<DemonezEntity, ModelDemonez2<DemonezEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/demonez_skin.png");

	public DemonezRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelDemonez2<DemonezEntity>(context.bakeLayer(ModelDemonez2.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(DemonezEntity entity) {
		return entityTexture;
	}
}