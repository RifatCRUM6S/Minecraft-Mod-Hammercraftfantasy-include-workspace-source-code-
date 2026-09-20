package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.SteedofslaaneshEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.steed_of_slaaneshAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelsteed_of_slaanesh;

import com.mojang.blaze3d.vertex.PoseStack;

public class SteedofslaaneshRenderer extends MobRenderer<SteedofslaaneshEntity, Modelsteed_of_slaanesh<SteedofslaaneshEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/steed_skin.png");

	public SteedofslaaneshRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelsteed_of_slaanesh.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(SteedofslaaneshEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
	}

	@Override
	public ResourceLocation getTextureLocation(SteedofslaaneshEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelsteed_of_slaanesh<SteedofslaaneshEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<SteedofslaaneshEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(SteedofslaaneshEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, steed_of_slaaneshAnimation.breathing, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(SteedofslaaneshEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}