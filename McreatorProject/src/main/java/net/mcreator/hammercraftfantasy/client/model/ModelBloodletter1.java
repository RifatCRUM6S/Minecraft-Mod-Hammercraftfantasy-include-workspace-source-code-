package net.mcreator.hammercraftfantasy.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelBloodletter1<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "model_bloodletter_1"), "main");
	public final ModelPart right_leg;
	public final ModelPart left_leg;
	public final ModelPart Body;
	public final ModelPart Head;
	public final ModelPart right_arm;
	public final ModelPart left_arm;
	public final ModelPart hellblade;

	public ModelBloodletter1(ModelPart root) {
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
		this.Body = root.getChild("Body");
		this.Head = root.getChild("Head");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.hellblade = this.left_arm.getChild("hellblade");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(54, 14).addBox(-0.8371F, 11.0F, -4.7309F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, 11.0F, 4.0F, 0.0F, 0.0873F, 0.0F));
		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 45).addBox(-1.0F, -10.0F, -2.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.1629F, 5.0F, -4.7309F, -0.9163F, 0.0F, 0.0F));
		PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(8, 45).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.1629F, 6.0F, -3.7309F, -0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(54, 6).addBox(-1.0F, -6.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.1629F, 11.0F, -2.7309F, -0.6981F, 0.0F, 0.0F));
		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(56, 48).addBox(-2.2501F, 11.0F, -5.7271F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, 11.0F, 5.0F, 0.0F, -0.0873F, 0.0F));
		PartDefinition cube_r4 = left_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(26, 45).addBox(-1.0F, -2.0F, -1.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.2501F, 6.0F, -4.7271F, -0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r5 = left_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(56, 40).addBox(-2.0F, -6.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2501F, 11.0F, -3.7271F, -0.6981F, 0.0F, 0.0F));
		PartDefinition cube_r6 = left_leg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 21).addBox(-2.0F, -10.0F, -2.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.2501F, 5.0F, -5.7271F, -0.9163F, 0.0F, 0.0F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(56, 53).addBox(-1.0F, -8.0F, -2.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(42, 58).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 3.0F, 3.0F));
		PartDefinition cube_r7 = Body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -2.0F, 10.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r8 = Body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -6.0F, -1.0F, 8.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 1.0F, 0.3491F, 0.0F, 0.0F));
		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -3.0F));
		PartDefinition cube_r9 = Head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(32, 11).addBox(-2.0F, -2.0F, 0.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -9.0F, -2.0F, -0.0948F, -0.2228F, -1.2487F));
		PartDefinition cube_r10 = Head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(54, 0).addBox(-1.0F, -3.0F, -1.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.0F, -5.0F, -3.0F, 0.0F, -0.3054F, -2.0508F));
		PartDefinition cube_r11 = Head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(8, 53).addBox(-1.0F, -3.0F, -1.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, 0.0F, -3.0F, 0.0094F, -0.0426F, -0.5238F));
		PartDefinition cube_r12 = Head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(54, 19).addBox(0.0F, -2.0F, 1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 3.0F, -4.0F, 0.0F, 0.0F, 0.48F));
		PartDefinition cube_r13 = Head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(8, 42).addBox(-4.0F, -2.0F, 1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 3.0F, -4.0F, 0.0F, 0.0F, -0.48F));
		PartDefinition cube_r14 = Head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(26, 21).addBox(-4.0F, -2.0F, 0.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -9.0F, -2.0F, -0.0948F, 0.2228F, 1.2487F));
		PartDefinition cube_r15 = Head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(26, 53).addBox(-4.0F, -3.0F, -1.0F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, -5.0F, -3.0F, 0.0F, 0.3054F, 2.0508F));
		PartDefinition cube_r16 = Head.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(50, 34).addBox(-5.0F, -3.0F, -1.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 0.0F, -3.0F, 0.0094F, 0.0426F, 0.5238F));
		PartDefinition cube_r17 = Head.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 26).addBox(-3.0F, -13.0F, -4.0F, 6.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r18 = Head.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, 0.5236F, 0.0F, 0.0F));
		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-7.0F, -1.0F, 0.0F));
		PartDefinition cube_r19 = right_arm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(26, 15).addBox(-3.0F, -3.0F, -2.0F, 11.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 9.0F, -6.0F, 0.0F, 0.2618F, 0.2182F));
		PartDefinition cube_r20 = right_arm.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(18, 32).addBox(-3.0F, -2.082F, -2.1495F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 1.0F, -1.0F, -0.5672F, 0.0F, 0.0F));
		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(8.0F, 0.0F, 1.0F));
		PartDefinition cube_r21 = left_arm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(18, 26).addBox(-8.0F, -3.0F, -2.0F, 11.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 9.0F, -2.0F, 0.0F, -1.2217F, -0.2182F));
		PartDefinition cube_r22 = left_arm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(34, 32).addBox(-1.0F, -2.082F, -2.1495F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, -0.2182F, 0.0F, 0.0F));
		PartDefinition hellblade = left_arm.addOrReplaceChild("hellblade",
				CubeListBuilder.create().texOffs(52, 58).addBox(-0.0356F, -0.4281F, -0.4898F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 42).addBox(0.9644F, -24.4281F, -0.4898F, 1.0F, 23.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(46, 21).addBox(-0.0356F, -9.4281F, -0.4898F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 42).addBox(-1.0356F, -24.4281F, -0.4898F, 1.0F, 23.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 5.0F, -9.0F, 0.1195F, 1.0406F, 0.5685F));
		PartDefinition cube_r23 = hellblade.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(8, 59).addBox(0.0F, -4.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0356F, -1.4281F, -0.4898F, 0.0F, 0.0F, 1.5708F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.Head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
}