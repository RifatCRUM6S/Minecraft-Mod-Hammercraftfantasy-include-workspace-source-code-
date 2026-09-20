package net.mcreator.hammercraftfantasy.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.hammercraftfantasy.entity.PinkHorrorEntity;
import net.mcreator.hammercraftfantasy.client.model.Modelpink_horror;

public class PinkHorrorRenderer extends MobRenderer<PinkHorrorEntity, Modelpink_horror<PinkHorrorEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/pink_horror.png");

    public PinkHorrorRenderer(EntityRendererProvider.Context context) {
        // 如果想让阴影也按比例缩小，可以把最后一个参数（阴影大小）从 0.5f 改为 0.3f
        super(context, new Modelpink_horror<PinkHorrorEntity>(context.bakeLayer(Modelpink_horror.LAYER_LOCATION)), 0.3f);
    }

    @Override
    protected void setupRotations(PinkHorrorEntity entity, PoseStack poseStack, float bob, float yRot, float partialTicks, float scale) {
        super.setupRotations(entity, poseStack, bob, yRot, partialTicks, scale);
        // 🌟 将视觉模型三轴缩放到原来的 0.6 倍
        poseStack.scale(0.6f, 0.6f, 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(PinkHorrorEntity entity) {
        return entityTexture;
    }
}