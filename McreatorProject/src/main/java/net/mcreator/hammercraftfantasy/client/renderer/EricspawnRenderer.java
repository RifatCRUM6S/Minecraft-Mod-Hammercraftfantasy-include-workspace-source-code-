package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.EricspawnEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.eric_spawnAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modeleric_spawn;

public class EricspawnRenderer extends MobRenderer<EricspawnEntity, Modeleric_spawn<EricspawnEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/eric_spawn_skin.png");

    public EricspawnRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modeleric_spawn.LAYER_LOCATION)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(EricspawnEntity entity) {
        return entityTexture;
    }

    private static final class AnimatedModel extends Modeleric_spawn<EricspawnEntity> {
        private final ModelPart root;
        private final HierarchicalModel<EricspawnEntity> animator = new HierarchicalModel<EricspawnEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(EricspawnEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);
                this.animate(entity.animationState0, eric_spawnAnimation.twisting, ageInTicks, 1f);
                this.animate(entity.animationState1, eric_spawnAnimation.breathing, ageInTicks, 1f);
                this.animateWalk(eric_spawnAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
                
                // 仅在 AnimationState 触发时播放对应攻击动作
                this.animate(entity.animationState3, eric_spawnAnimation.attack_1, ageInTicks, 1f);
                this.animate(entity.animationState4, eric_spawnAnimation.attack_2, ageInTicks, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(EricspawnEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}