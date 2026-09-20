package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.ChaosspawnEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.chaos_spawnAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelchaos_spawn;

public class ChaosspawnRenderer extends MobRenderer<ChaosspawnEntity, Modelchaos_spawn<ChaosspawnEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/chaos_spawn_skin.png");

	public ChaosspawnRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelchaos_spawn.LAYER_LOCATION)), 1f);
	}

	@Override
	public ResourceLocation getTextureLocation(ChaosspawnEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelchaos_spawn<ChaosspawnEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<ChaosspawnEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(ChaosspawnEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, chaos_spawnAnimation.limbstwisting, ageInTicks, 1f);
				this.animate(entity.animationState1, chaos_spawnAnimation.breathing, ageInTicks, 1f);
				this.animateWalk(chaos_spawnAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
				this.animate(entity.animationState3, chaos_spawnAnimation.attack_1, ageInTicks, 1f);
				this.animate(entity.animationState4, chaos_spawnAnimation.attack_2, ageInTicks, 1f);
				this.animate(entity.animationState5, chaos_spawnAnimation.attack_3, ageInTicks, 1f);
				this.animate(entity.animationState6, chaos_spawnAnimation.birth, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(ChaosspawnEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}