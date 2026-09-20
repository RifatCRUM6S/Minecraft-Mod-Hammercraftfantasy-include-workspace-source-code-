package net.mcreator.hammercraftfantasy.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.NurglingEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.Nurgling1Animation;
import net.mcreator.hammercraftfantasy.client.model.ModelNurgling1;

public class NurglingRenderer extends MobRenderer<NurglingEntity, ModelNurgling1<NurglingEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/nurgling_skin_1.png");

    public NurglingRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(ModelNurgling1.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(NurglingEntity entity) {
        return entityTexture;
    }

    // 控制变身过程中的体型动态膨胀（1倍 -> 6倍）
    @Override
    protected void scale(NurglingEntity entity, PoseStack poseStack, float partialTick) {
        if (entity.isEvolving()) {
            int timer = entity.getEvolveTimer();
            if (timer >= 0) {
                // 计算 0.0 -> 1.0 的变身进度
                float progress = 1.0f - ((float) timer - partialTick) / 160.0f;
                progress = Mth.clamp(progress, 0.0f, 1.0f);

                // 末期加速膨胀，最大达到 6.0 倍尺寸 (1.0 + 1.0 * 5.0)
                float scaleFactor = 1.0f + (progress * progress * 5.0f);
                
                poseStack.scale(scaleFactor, scaleFactor, scaleFactor);
            }
        } else {
            super.scale(entity, poseStack, partialTick);
        }
    }

    private static final class AnimatedModel extends ModelNurgling1<NurglingEntity> {
        private final ModelPart root;
        private final HierarchicalModel animator = new HierarchicalModel<NurglingEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(NurglingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);
                
                // 播放遮脸变身动画
                this.animate(entity.animationState1, Nurgling1Animation.turn_to_guo, ageInTicks, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(NurglingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            if (entity.isEvolving()) {
                this.root.getAllParts().forEach(ModelPart::resetPose);
                animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            } else {
                animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            }
        }
    }
}