package net.mcreator.hammercraftfantasy.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.ScreamerEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.screamerAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelscreamer;

public class ScreamerRenderer extends MobRenderer<ScreamerEntity, Modelscreamer<ScreamerEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/screamer_skin.png");

    public ScreamerRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modelscreamer.LAYER_LOCATION)), 0.7f); 
    }

    @Override
    protected void scale(ScreamerEntity entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(0.7F, 0.7F, 0.7F);
        super.scale(entity, poseStack, partialTick);
    }

    @Override
    public ResourceLocation getTextureLocation(ScreamerEntity entity) {
        return entityTexture;
    }

    private static final class AnimatedModel extends Modelscreamer<ScreamerEntity> {
        private final ModelPart root;
        private final HierarchicalModel animator = new HierarchicalModel<ScreamerEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(ScreamerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);
                this.animate(entity.animationState0, screamerAnimation.flying, ageInTicks, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(ScreamerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}