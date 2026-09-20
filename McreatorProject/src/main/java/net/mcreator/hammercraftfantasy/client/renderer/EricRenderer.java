package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.EricEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.ericAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modeleric;

public class EricRenderer extends MobRenderer<EricEntity, Modeleric<EricEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/eric_skin.png");

    public EricRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modeleric.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(EricEntity entity) {
        return entityTexture;
    }

    private static final class AnimatedModel extends Modeleric<EricEntity> {
        private final ModelPart root;

        private final HierarchicalModel<EricEntity> animator = new HierarchicalModel<EricEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(EricEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);

                // 基础悬浮与呼吸动画始终播放
                this.animate(entity.animationState0, ericAnimation.flying, ageInTicks, 1f);
                this.animate(entity.animationState1, ericAnimation.breathing, ageInTicks, 1f);

                // 施法动画：根据 SpellAnimState 条件播放
                int spellState = entity.getSpellAnimState();
                this.animate(entity.animationState2, ericAnimation.spell_1, ageInTicks, spellState == 1 ? 1f : 0f);
                this.animate(entity.animationState3, ericAnimation.spell_2, ageInTicks, spellState == 2 ? 1f : 0f);

                // 死亡阶段动画
                this.animate(entity.animationState4, ericAnimation.dying, ageInTicks, entity.isDyingState() ? 1f : 0f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(EricEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}