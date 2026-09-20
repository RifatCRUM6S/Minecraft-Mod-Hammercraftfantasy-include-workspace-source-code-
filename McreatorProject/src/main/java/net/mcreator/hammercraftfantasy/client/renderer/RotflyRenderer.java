package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.RotflyEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.rotflyAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelrotfly;

public class RotflyRenderer extends MobRenderer<RotflyEntity, Modelrotfly<RotflyEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/rotfly_skin1.png");

	public RotflyRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelrotfly.LAYER_LOCATION)), 0.6f);
	}

	@Override
	public ResourceLocation getTextureLocation(RotflyEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelrotfly<RotflyEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<RotflyEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(RotflyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, rotflyAnimation.continue_1, ageInTicks, 1f);
				this.animate(entity.animationState1, rotflyAnimation.continue_2, ageInTicks, 1f);
				this.animate(entity.animationState2, rotflyAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(RotflyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}