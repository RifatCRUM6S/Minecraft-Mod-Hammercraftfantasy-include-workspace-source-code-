package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.GreatuncleanoneEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.great_unclean_oneAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelgreat_unclean_one;

public class GreatuncleanoneRenderer extends MobRenderer<GreatuncleanoneEntity, Modelgreat_unclean_one<GreatuncleanoneEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/great_unclean_one_skin.png");

	public GreatuncleanoneRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelgreat_unclean_one.LAYER_LOCATION)), 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(GreatuncleanoneEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelgreat_unclean_one<GreatuncleanoneEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GreatuncleanoneEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GreatuncleanoneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, great_unclean_oneAnimation.constant, ageInTicks, 1f);
				this.animateWalk(great_unclean_oneAnimation.moving, limbSwing, limbSwingAmount, 1f, 2f);
				this.animate(entity.animationState2, great_unclean_oneAnimation.attack_1, ageInTicks, 1f);
				this.animate(entity.animationState3, great_unclean_oneAnimation.attack_2, ageInTicks, 1f);
				this.animate(entity.animationState4, great_unclean_oneAnimation.attack_range, ageInTicks, 1f);
				this.animate(entity.animationState5, great_unclean_oneAnimation.born, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GreatuncleanoneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}