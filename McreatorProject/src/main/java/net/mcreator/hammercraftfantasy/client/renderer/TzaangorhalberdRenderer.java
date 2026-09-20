package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.TzaangorhalberdEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.tzaangor_halberdAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modeltzaangor_halberd;

public class TzaangorhalberdRenderer extends MobRenderer<TzaangorhalberdEntity, Modeltzaangor_halberd<TzaangorhalberdEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/tzaangor_halberd.png");

	public TzaangorhalberdRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modeltzaangor_halberd.LAYER_LOCATION)), 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(TzaangorhalberdEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modeltzaangor_halberd<TzaangorhalberdEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<TzaangorhalberdEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(TzaangorhalberdEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, tzaangor_halberdAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(TzaangorhalberdEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}