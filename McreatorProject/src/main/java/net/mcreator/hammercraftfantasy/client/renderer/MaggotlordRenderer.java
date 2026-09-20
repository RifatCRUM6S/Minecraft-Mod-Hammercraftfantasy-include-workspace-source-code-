package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.MaggotlordEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.maggotlordAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelmaggotlord;

public class MaggotlordRenderer extends MobRenderer<MaggotlordEntity, Modelmaggotlord<MaggotlordEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/maggotlord_skin.png");

	public MaggotlordRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelmaggotlord.LAYER_LOCATION)), 1f);
	}

	@Override
	public ResourceLocation getTextureLocation(MaggotlordEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelmaggotlord<MaggotlordEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<MaggotlordEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(MaggotlordEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, maggotlordAnimation.breathing, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(MaggotlordEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}