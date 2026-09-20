package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.ForsakenEntity;
import net.mcreator.hammercraftfantasy.client.model.Modelforsaken;

public class ForsakenRenderer extends MobRenderer<ForsakenEntity, Modelforsaken<ForsakenEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/forsaken_skin.png");

	public ForsakenRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelforsaken<ForsakenEntity>(context.bakeLayer(Modelforsaken.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(ForsakenEntity entity) {
		return entityTexture;
	}
}