package net.mcreator.hammercraftfantasy.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.KchaoswarriorEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.k_chaoswarriorAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelk_chaoswarrior;

public class KchaoswarriorRenderer extends MobRenderer<KchaoswarriorEntity, Modelk_chaoswarrior<KchaoswarriorEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/k_cw_skin.png");

    public KchaoswarriorRenderer(EntityRendererProvider.Context context) {
        // 阴影大小调整为 0.6f * (5/8) = 0.375f
        super(context, new AnimatedModel(context.bakeLayer(Modelk_chaoswarrior.LAYER_LOCATION)), 0.375f);
    }

    @Override
    protected void scale(KchaoswarriorEntity entity, PoseStack poseStack, float partialTick) {
        // 5/8 = 0.625f
        poseStack.scale(0.625f, 0.625f, 0.625f);
        super.scale(entity, poseStack, partialTick);
    }

    @Override
    public ResourceLocation getTextureLocation(KchaoswarriorEntity entity) {
        return entityTexture;
    }

    private static final class AnimatedModel extends Modelk_chaoswarrior<KchaoswarriorEntity> {
        private final ModelPart root;
        private final HierarchicalModel animator = new HierarchicalModel<KchaoswarriorEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(KchaoswarriorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);
                this.animate(entity.animationState0, k_chaoswarriorAnimation.breathing, ageInTicks, 1f);
                this.animateWalk(k_chaoswarriorAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
                this.animate(entity.animationState2, k_chaoswarriorAnimation.attack_1, ageInTicks, 1f);
                this.animate(entity.animationState3, k_chaoswarriorAnimation.attack_2, ageInTicks, 1f);
                this.animate(entity.animationState4, k_chaoswarriorAnimation.attack_3, ageInTicks, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(KchaoswarriorEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}