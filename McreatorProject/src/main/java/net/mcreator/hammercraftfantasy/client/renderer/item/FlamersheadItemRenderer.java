package net.mcreator.hammercraftfantasy.client.renderer.item;

import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.Minecraft;

import net.mcreator.hammercraftfantasy.init.HammercraftfantasyModItems;
import net.mcreator.hammercraftfantasy.client.model.Modelflamers_head;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

@OnlyIn(Dist.CLIENT)
public class FlamersheadItemRenderer extends BlockEntityWithoutLevelRenderer {
	private final EntityModelSet entityModelSet;
	private final ItemStack transformSource;
	private final Map<Integer, EntityModel<?>> models = new HashMap<>();
	private final long start;
	private final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.parse("hammercraftfantasy:textures/item/flamers_head.png");

	public FlamersheadItemRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, EntityModelSet entityModelSet) {
		super(blockEntityRenderDispatcher, entityModelSet);
		this.entityModelSet = entityModelSet;
		this.transformSource = new ItemStack(HammercraftfantasyModItems.FLAMERSHEAD.get());
		this.start = System.currentTimeMillis();
		this.models.put(0, new Modelflamers_head(this.entityModelSet.bakeLayer(Modelflamers_head.LAYER_LOCATION)));
	}

	@Override
	public void renderByItem(ItemStack itemstack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
		EntityModel<?> model = this.models.get(0);
		ResourceLocation texture = DEFAULT_TEXTURE;
		if (model == null)
			return;
		poseStack.pushPose();
		Minecraft.getInstance().getItemRenderer().getModel(this.transformSource, null, null, 0).applyTransform(displayContext, poseStack, isLeftHand(displayContext));
		poseStack.translate(0.5, isInventory(displayContext) ? 1.5 : 2, 0.5);
		poseStack.scale(1, -1, displayContext == ItemDisplayContext.GUI ? -1 : 1);
		VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(bufferSource, model.renderType(texture), false, itemstack.hasFoil());
		model.setupAnim(null, 0, 0, (System.currentTimeMillis() - start) / 50.0f, 0, 0);
		model.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay);
		poseStack.popPose();
	}

	private static boolean isLeftHand(ItemDisplayContext type) {
		return type == ItemDisplayContext.FIRST_PERSON_LEFT_HAND || type == ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
	}

	private static boolean isInventory(ItemDisplayContext type) {
		return type == ItemDisplayContext.GUI || type == ItemDisplayContext.FIXED;
	}
}