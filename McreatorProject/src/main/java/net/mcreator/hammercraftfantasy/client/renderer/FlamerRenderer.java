package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.FlamerEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.flamersAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelflamers;

public class FlamerRenderer extends MobRenderer<FlamerEntity, Modelflamers<FlamerEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/flamers_skin.png");

	public FlamerRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelflamers.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(FlamerEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelflamers<FlamerEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<FlamerEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(FlamerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, flamersAnimation.breathing, ageInTicks, 1f);
				this.animateWalk(flamersAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, flamersAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(FlamerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}