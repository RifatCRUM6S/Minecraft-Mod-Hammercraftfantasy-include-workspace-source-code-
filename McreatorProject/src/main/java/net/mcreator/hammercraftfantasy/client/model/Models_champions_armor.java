package net.mcreator.hammercraftfantasy.client.model;

import net.minecraft.world.entity.Entity;
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

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Models_champions_armor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "models_champions_armor"), "main");
	public final ModelPart head;
	public final ModelPart body;
	public final ModelPart right_leg;
	public final ModelPart right_leggings;
	public final ModelPart right_boots;
	public final ModelPart left_leg;
	public final ModelPart left_leggings;
	public final ModelPart left_boots;
	public final ModelPart left_arm;
	public final ModelPart right_arm;

	public Models_champions_armor(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
		this.right_boots = this.right_leg.getChild("right_boots");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.left_boots = this.left_leg.getChild("left_boots");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 34).addBox(-4.0F, -10.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(66, 23).addBox(-0.5F, -6.2F, -6.2F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(82, 14)
						.addBox(-3.0F, -3.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 41).addBox(1.0F, -3.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 69)
						.addBox(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 26).addBox(4.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(34, 43)
						.addBox(-6.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(80, 63).addBox(-4.0F, -6.0F, -5.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(56, 41)
						.addBox(-2.0F, -11.0F, -5.0F, 4.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(64, 69).addBox(-2.0F, -11.0F, 4.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 60).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -11.0F, 6.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -6.0F, -8.0F, 2.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -11.0F, 0.0F, -0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(38, 83).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -3.0F, -5.0F, 0.0F, -1.5708F, 0.0F));
		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(34, 83).addBox(0.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.0F, -5.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(66, 18).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, -1.0F, -0.2182F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 21).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(34, 18).addBox(-4.5F, 10.0F, -4.0F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 69).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 10.0F, 0.0F, 0.0F, 0.0F, 0.1745F));
		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(36, 69).addBox(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 10.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(82, 7).addBox(0.0F, -3.0F, 0.0F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -5.3F, 0.0F, -0.1745F, 0.0F));
		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(82, 0).addBox(-5.0F, -3.0F, 0.0F, 5.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -5.3F, 0.0F, 0.1745F, 0.0F));
		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(22, 52).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.8889F, -0.9338F, -0.7805F));
		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(22, 45).addBox(0.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, -3.0F, 0.8889F, 0.9338F, 0.7805F));
		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 0).addBox(-7.0F, 0.0F, 0.0F, 14.0F, 18.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, 4.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-2.0F, 12.0F, 0.0F));
		PartDefinition right_leggings = right_leg.addOrReplaceChild("right_leggings",
				CubeListBuilder.create().texOffs(0, 45).addBox(-2.9F, 1.0F, -3.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(82, 45).addBox(-2.0F, 3.0F, -3.3F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r13 = right_leggings.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(78, 75).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.1F, -1.0F, -3.5F, -0.2182F, 0.0F, 0.0F));
		PartDefinition right_boots = right_leg.addOrReplaceChild("right_boots", CubeListBuilder.create().texOffs(56, 51).addBox(-2.9F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(2.0F, 12.0F, 0.0F));
		PartDefinition left_leggings = left_leg.addOrReplaceChild("left_leggings",
				CubeListBuilder.create().texOffs(56, 26).addBox(-2.1F, 1.0F, -3.0F, 5.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(64, 82).addBox(-1.0F, 3.0F, -3.3F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r14 = left_leggings.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 79).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, -1.0F, -3.5F, -0.2182F, 0.0F, 0.0F));
		PartDefinition left_boots = left_leg.addOrReplaceChild("left_boots", CubeListBuilder.create().texOffs(0, 60).addBox(-2.1F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(16, 80).addBox(-1.0F, -2.0F, 3.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(42, 83).addBox(6.0F, -2.7F, -5.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(46, 83)
						.addBox(6.0F, -2.7F, 4.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(80, 51).addBox(-1.0F, -2.0F, -4.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(60, 0)
						.addBox(-1.0F, 3.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(60, 9).addBox(-1.0F, 8.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition cube_r15 = left_arm.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(72, 82).addBox(3.0F, 0.0F, -2.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.5F, 1.2F, -0.9599F, 0.0F, 0.0F));
		PartDefinition cube_r16 = left_arm.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(78, 29).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.0F, 0.5F, -0.9599F, 0.0F, 0.0F));
		PartDefinition cube_r17 = left_arm.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(20, 69).addBox(3.0F, 0.0F, 1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.5F, -1.2F, 0.9599F, 0.0F, 0.0F));
		PartDefinition cube_r18 = left_arm.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(78, 23).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.0F, -0.5F, 0.9599F, 0.0F, 0.0F));
		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(80, 55).addBox(-7.0F, -2.0F, 3.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(50, 83).addBox(-7.0F, -2.7F, -5.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(54, 83)
						.addBox(-7.0F, -2.7F, 4.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(80, 59).addBox(-7.0F, -2.0F, -4.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(36, 60)
						.addBox(-4.0F, 3.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(58, 60).addBox(-4.0F, 8.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition cube_r19 = right_arm.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(80, 82).addBox(-4.0F, 0.0F, -2.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.5F, 1.2F, -0.9599F, 0.0F, 0.0F));
		PartDefinition cube_r20 = right_arm.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(78, 69).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.0F, 0.5F, -0.9599F, 0.0F, 0.0F));
		PartDefinition cube_r21 = right_arm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(76, 82).addBox(-4.0F, 0.0F, 1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.5F, -1.2F, 0.9599F, 0.0F, 0.0F));
		PartDefinition cube_r22 = right_arm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(78, 35).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.0F, -0.5F, 0.9599F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}