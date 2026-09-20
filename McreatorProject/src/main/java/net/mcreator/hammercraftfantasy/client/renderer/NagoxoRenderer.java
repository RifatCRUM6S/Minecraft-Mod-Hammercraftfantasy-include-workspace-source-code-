package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.NagoxoEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.nagoxoAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelnagoxo;

public class NagoxoRenderer extends MobRenderer<NagoxoEntity, Modelnagoxo<NagoxoEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/nagoxo_skin.png");

	public NagoxoRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelnagoxo.LAYER_LOCATION)), 1.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(NagoxoEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelnagoxo<NagoxoEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<NagoxoEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(NagoxoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, nagoxoAnimation.breathing, ageInTicks, 1f);
				this.animateWalk(nagoxoAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState2, nagoxoAnimation.attack, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(NagoxoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}