package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.TamurkhanEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.tamurkhanAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modeltamurkhan;

public class TamurkhanRenderer extends MobRenderer<TamurkhanEntity, Modeltamurkhan<TamurkhanEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/tamurkhan_skin.png");

    public TamurkhanRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modeltamurkhan.LAYER_LOCATION)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(TamurkhanEntity entity) {
        return entityTexture;
    }

    private static final class AnimatedModel extends Modeltamurkhan<TamurkhanEntity> {
        private final ModelPart rootPart;
        private final HierarchicalModel<TamurkhanEntity> animator;

        public AnimatedModel(ModelPart root) {
            super(root);
            this.rootPart = root;
            this.animator = new HierarchicalModel<TamurkhanEntity>() {
                @Override
                public ModelPart root() {
                    return rootPart;
                }

                @Override
                public void setupAnim(TamurkhanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                    this.root().getAllParts().forEach(ModelPart::resetPose);

                    // 1. 行走动画：仅在实体实际发生位移（limbSwingAmount > 0）时播放
                    this.animateWalk(tamurkhanAnimation.moving, limbSwing, limbSwingAmount, 1f, 1f);

                    // 2. 攻击技能动画绑定
                    this.animate(entity.animationState1, tamurkhanAnimation.attack_1, ageInTicks, 1f);
                    this.animate(entity.animationState2, tamurkhanAnimation.attack_2, ageInTicks, 1f);
                    this.animate(entity.animationState3, tamurkhanAnimation.attack_3, ageInTicks, 1f);
                    this.animate(entity.animationState4, tamurkhanAnimation.attack_4, ageInTicks, 1f);
                    this.animate(entity.animationState5, tamurkhanAnimation.attack_range, ageInTicks, 1f);
                }
            };
        }

        @Override
        public void setupAnim(TamurkhanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}