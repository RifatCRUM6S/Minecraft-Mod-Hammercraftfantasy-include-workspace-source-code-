package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.SigvaldEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.sigvaldAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelsigvald;

public class SigvaldRenderer extends MobRenderer<SigvaldEntity, Modelsigvald<SigvaldEntity>> {

    public SigvaldRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modelsigvald.LAYER_LOCATION)), 0.5f);
    }

    /**
     * 动态读取实体自身的材质（普通状态 sigvard_skin.png / 狂暴状态 sigvard_angry_skin.png）
     */
    @Override
    public ResourceLocation getTextureLocation(SigvaldEntity entity) {
        return entity.getTextureLocation();
    }

    private static final class AnimatedModel extends Modelsigvald<SigvaldEntity> {
        private final ModelPart root;
        private final HierarchicalModel<SigvaldEntity> animator = new HierarchicalModel<SigvaldEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(SigvaldEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);

                // 1. 移动动画切换：狂暴状态使用 angry_moving，正常状态使用 moving
                if (entity.isAngry()) {
                    this.animateWalk(sigvaldAnimation.angry_moving, limbSwing, limbSwingAmount, 1f, 1f);
                } else {
                    this.animateWalk(sigvaldAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);
                }

                // 2. 普通状态攻击动画 (AnimationState 1-4)
                this.animate(entity.animationState1, sigvaldAnimation.attack_1, ageInTicks, 1f);
                this.animate(entity.animationState2, sigvaldAnimation.attack_2, ageInTicks, 1f);
                this.animate(entity.animationState3, sigvaldAnimation.attack_3, ageInTicks, 1f);
                this.animate(entity.animationState4, sigvaldAnimation.attack_4, ageInTicks, 1f);

                // 3. 狂暴变身动画 (AnimationState 5) —— 修复：正确绑定变身动画
                this.animate(entity.animationState5, sigvaldAnimation.angry, ageInTicks, 1f);

                // 4. 狂暴状态攻击动画 (AnimationState 7 与 8) —— 修复：正确绑定狂暴攻击 1 和 2
                this.animate(entity.animationState7, sigvaldAnimation.angry_attack_1, ageInTicks, 1f);
                this.animate(entity.animationState8, sigvaldAnimation.angry_attack_2, ageInTicks, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(SigvaldEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}