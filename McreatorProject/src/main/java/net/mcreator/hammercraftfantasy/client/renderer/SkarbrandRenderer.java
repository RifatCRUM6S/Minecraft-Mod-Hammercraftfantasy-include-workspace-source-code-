package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.SkarbrandEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.skarbrandAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelskarbrand;

public class SkarbrandRenderer extends MobRenderer<SkarbrandEntity, Modelskarbrand<SkarbrandEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/skarbrand_skin.png");

    public SkarbrandRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modelskarbrand.LAYER_LOCATION)), 3f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkarbrandEntity entity) {
        return entityTexture;
    }

    private static final class AnimatedModel extends Modelskarbrand<SkarbrandEntity> {
        private final ModelPart root;
        private final HierarchicalModel<SkarbrandEntity> animator = new HierarchicalModel<SkarbrandEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(SkarbrandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);

                switch (entity.getAttackState()) {
                    case SkarbrandEntity.STATE_ATTACK_LIGHT -> 
                        this.animate(entity.attackLightAnimationState, skarbrandAnimation.attack_2, ageInTicks, 1f);
                    case SkarbrandEntity.STATE_ATTACK_SWEEP -> 
                        this.animate(entity.attackSweepAnimationState, skarbrandAnimation.attack_1, ageInTicks, 1f);
                    case SkarbrandEntity.STATE_ATTACK_HEAVY -> 
                        this.animate(entity.attackHeavyAnimationState, skarbrandAnimation.attack_4, ageInTicks, 1f);
                    case SkarbrandEntity.STATE_ATTACK_SLAM -> 
                        this.animate(entity.attackSlamAnimationState, skarbrandAnimation.attack_3, ageInTicks, 1f);
                    case SkarbrandEntity.STATE_ATTACK_BERSERK -> 
                        this.animate(entity.attackBerserkAnimationState, skarbrandAnimation.attack_range, ageInTicks, 1f);
                    default -> {
                        this.animate(entity.idleAnimationState, skarbrandAnimation.breathing, ageInTicks, 1f);
                        this.animateWalk(skarbrandAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
                    }
                }
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(SkarbrandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}