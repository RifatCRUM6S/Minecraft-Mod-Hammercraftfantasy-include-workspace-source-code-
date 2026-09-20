package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.FiendofslaaneshEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.fiend_of_slaaneshAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelfiend_of_slaanesh;

public class FiendofslaaneshRenderer extends MobRenderer<FiendofslaaneshEntity, Modelfiend_of_slaanesh<FiendofslaaneshEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/fiend_of_slaanesh_skin.png");

	public FiendofslaaneshRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelfiend_of_slaanesh.LAYER_LOCATION)), 1f);
	}

	@Override
	public ResourceLocation getTextureLocation(FiendofslaaneshEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelfiend_of_slaanesh<FiendofslaaneshEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<FiendofslaaneshEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(FiendofslaaneshEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, fiend_of_slaaneshAnimation.breathing, ageInTicks, 1f);
				this.animateWalk(fiend_of_slaaneshAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, fiend_of_slaaneshAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(FiendofslaaneshEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}