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
public class Modeldeceit_and_scheme<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modeldeceit_and_scheme"), "main");
	public final ModelPart head;
	public final ModelPart mask;
	public final ModelPart hat;
	public final ModelPart right_horn;
	public final ModelPart left_horn;
	public final ModelPart body;
	public final ModelPart right_arm;
	public final ModelPart left_arm;
	public final ModelPart right_leg;
	public final ModelPart right_leggings;
	public final ModelPart right_boots;
	public final ModelPart left_leg;
	public final ModelPart left_leggings;
	public final ModelPart left_boots;

	public Modeldeceit_and_scheme(ModelPart root) {
		this.head = root.getChild("head");
		this.mask = this.head.getChild("mask");
		this.hat = this.head.getChild("hat");
		this.right_horn = this.head.getChild("right_horn");
		this.left_horn = this.head.getChild("left_horn");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
		this.right_boots = this.right_leg.getChild("right_boots");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.left_boots = this.left_leg.getChild("left_boots");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(32, 12).addBox(-4.0F, -9.0F, -5.0F, 8.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 77).addBox(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 60)
						.addBox(4.0F, -9.0F, -5.0F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(60, 39).addBox(-5.0F, -9.0F, -5.0F, 1.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(62, 78)
						.addBox(-4.0F, -8.0F, -5.0F, 8.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 77).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, -1.0F, -0.2182F, 0.0F, 0.0F));
		PartDefinition mask = head.addOrReplaceChild("mask",
				CubeListBuilder.create().texOffs(18, 80).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(86, 54).addBox(-3.0F, -3.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 86)
						.addBox(1.0F, -3.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 86).addBox(1.0F, -1.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 85)
						.addBox(1.0F, 1.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(42, 85).addBox(-3.0F, 1.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(78, 85)
						.addBox(-3.0F, -1.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(26, 85).addBox(-3.0F, 3.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 85)
						.addBox(1.0F, 3.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -5.0F, -1.0F));
		PartDefinition cube_r2 = mask.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(70, 84).addBox(0.0F, -5.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 2.0F, -4.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition cube_r3 = mask.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(74, 84).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 2.0F, -4.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition hat = head.addOrReplaceChild("hat",
				CubeListBuilder.create().texOffs(60, 85).addBox(-4.0F, -1.0F, 3.0F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(70, 38).addBox(-15.0F, -2.0F, 3.0F, 12.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-15.0F, -2.0F, 3.0F, 12.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(58, 85).addBox(-15.0F, -1.0F, 3.0F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 26)
						.addBox(-3.0F, -2.0F, 3.0F, 0.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(70, 20).addBox(-15.0F, -2.0F, 15.0F, 12.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(24, 26)
						.addBox(-15.0F, -2.0F, 3.0F, 0.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(9.0F, -8.0F, -9.0F));
		PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create().texOffs(26, 80).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -9.0F, -3.0F, -0.383F, 0.2753F, -0.4844F));
		PartDefinition cube_r4 = right_horn.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(60, 56).addBox(-1.0F, -3.0F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -7.0F, 1.0F, -0.5873F, -0.402F, -0.5314F));
		PartDefinition cube_r5 = right_horn.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(80, 39).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -0.5873F, -0.402F, -0.5314F));
		PartDefinition cube_r6 = right_horn.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(34, 80).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, -0.6981F, 0.0F, 0.0F));
		PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create().texOffs(80, 44).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -9.0F, -3.0F, -0.383F, -0.2753F, 0.4844F));
		PartDefinition cube_r7 = left_horn.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(86, 57).addBox(0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -7.0F, 1.0F, -0.5873F, 0.402F, 0.5314F));
		PartDefinition cube_r8 = left_horn.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(62, 84).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -0.5873F, 0.402F, 0.5314F));
		PartDefinition cube_r9 = left_horn.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(80, 49).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, -0.6981F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 12).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(48, 0).addBox(-4.0F, 6.0F, -3.0F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(42, 73).addBox(-5.0F, -2.0F, 3.0F, 10.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.0F, 2.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(78, 78).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.8889F, -0.9338F, -0.7805F));
		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(48, 39).addBox(0.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, -3.0F, 0.8889F, 0.9338F, 0.7805F));
		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 47).addBox(-6.0F, -4.0F, -4.0F, 7.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(42, 60)
				.addBox(-4.0F, 1.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(70, 29).addBox(-3.0F, 8.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition left_arm = partdefinition.addOrReplaceChild(
				"left_arm", CubeListBuilder.create().texOffs(0, 47).mirror().addBox(-1.0F, -4.0F, -4.0F, 7.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(42, 60).mirror()
						.addBox(-1.0F, 1.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(70, 29).mirror().addBox(-1.0F, 8.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));
		PartDefinition right_leggings = right_leg.addOrReplaceChild("right_leggings",
				CubeListBuilder.create().texOffs(48, 22).addBox(-2.9F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(32, 22).addBox(-2.0F, 3.0F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition right_boots = right_leg.addOrReplaceChild("right_boots", CubeListBuilder.create().texOffs(66, 11).addBox(-2.9F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));
		PartDefinition left_leggings = left_leg.addOrReplaceChild("left_leggings", CubeListBuilder.create().texOffs(48, 22).mirror().addBox(-2.1F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(32, 22).mirror()
				.addBox(-1.0F, 3.0F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition left_boots = left_leg.addOrReplaceChild("left_boots", CubeListBuilder.create().texOffs(66, 11).mirror().addBox(-2.1F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_leggings.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_boots.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.right_boots.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.left_leggings.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}