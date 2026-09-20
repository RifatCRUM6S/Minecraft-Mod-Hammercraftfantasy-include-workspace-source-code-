package net.mcreator.hammercraftfantasy.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.BlueHorrorEntity;
import net.mcreator.hammercraftfantasy.client.model.ModelBlue_horror;

public class BlueHorrorRenderer extends MobRenderer<BlueHorrorEntity, ModelBlue_horror<BlueHorrorEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/blue_horror_skin.png");

    public BlueHorrorRenderer(EntityRendererProvider.Context context) {
        // 阴影大小同步按比例缩减至 0.3f
        super(context, new ModelBlue_horror<BlueHorrorEntity>(context.bakeLayer(ModelBlue_horror.LAYER_LOCATION)), 0.3f);
    }

    @Override
    protected void setupRotations(BlueHorrorEntity entity, PoseStack poseStack, float bob, float yRot, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, bob, yRot, partialTicks, scale);
        // 🌟 将视觉模型缩放到原来的 0.6 倍
        poseStack.scale(0.6f, 0.6f, 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(BlueHorrorEntity entity) {
        return entityTexture;
    }
}