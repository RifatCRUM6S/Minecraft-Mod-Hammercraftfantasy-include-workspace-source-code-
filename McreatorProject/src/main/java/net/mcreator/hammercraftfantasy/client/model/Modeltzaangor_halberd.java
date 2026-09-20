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

// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modeltzaangor_halberd<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modeltzaangor_halberd"), "main");
	public final ModelPart all;
	public final ModelPart right_leg;
	public final ModelPart left_leg;
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart horn1;
	public final ModelPart horn2;
	public final ModelPart mouth;
	public final ModelPart right_arm;
	public final ModelPart left_arm;
	public final ModelPart halberd;

	public Modeltzaangor_halberd(ModelPart root) {
		this.all = root.getChild("all");
		this.right_leg = this.all.getChild("right_leg");
		this.left_leg = this.all.getChild("left_leg");
		this.body = this.all.getChild("body");
		this.head = this.body.getChild("head");
		this.horn1 = this.head.getChild("horn1");
		this.horn2 = this.head.getChild("horn2");
		this.mouth = this.head.getChild("mouth");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.halberd = this.left_arm.getChild("halberd");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition right_leg = all.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(78, 89).addBox(-2.0F, 12.5F, 2.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -14.5F, -4.0F, 0.0F, 0.1745F, 0.0F));
		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 52).addBox(-2.0F, -1.0F, -1.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -4.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(72, 45).addBox(-1.5F, -3.0F, -9.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 4.0F, 7.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(92, 80).addBox(-1.0F, -6.0F, -2.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 12.5F, 4.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition left_leg = all.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(92, 75).addBox(-3.0F, 12.5F, 2.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -14.5F, -4.0F, 0.0F, -0.1745F, 0.0F));
		PartDefinition cube_r4 = left_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(38, 69).addBox(-3.0F, -1.0F, -1.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -4.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r5 = left_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(58, 93).addBox(-3.0F, -6.0F, -2.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 12.5F, 4.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r6 = left_leg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(74, 18).addBox(-1.5F, -3.0F, -9.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 4.0F, 7.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -18.5F, -6.0F));
		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(4, 0).addBox(-7.0F, -9.0F, -5.5F, 14.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 4.5F, -0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(4, 18).addBox(-6.0F, -9.0F, -4.5F, 12.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, -0.2618F, 0.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9.0F, 10.5F, -0.3491F, 0.0F, 0.0F));
		PartDefinition cube_r9 = head.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(58, 89).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.3781F, -11.8917F, -1.2654F, 0.0F, 0.0F));
		PartDefinition cube_r10 = head.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(84, 13).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.6219F, -10.3917F, -1.2654F, 0.0F, 0.0F));
		PartDefinition cube_r11 = head.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(84, 9).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.6219F, -8.3917F, -1.2654F, 0.0F, 0.0F));
		PartDefinition cube_r12 = head.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(42, 18).addBox(-5.0F, -6.0F, -2.0F, 10.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.6219F, -5.8917F, -0.6981F, 0.0F, 0.0F));
		PartDefinition horn1 = head.addOrReplaceChild("horn1", CubeListBuilder.create().texOffs(92, 57).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -5.6219F, -1.8917F, 1.736F, 1.0458F, 1.3791F));
		PartDefinition cube_r13 = horn1.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(86, 94).addBox(-1.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -10.0F, 0.5F, 0.0F, 0.0F, 0.4363F));
		PartDefinition cube_r14 = horn1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(70, 93).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.0F, 0.0F, -0.48F));
		PartDefinition horn2 = head.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(92, 66).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -5.6219F, -1.8917F, 1.736F, -1.0458F, -1.3791F));
		PartDefinition cube_r15 = horn2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(78, 94).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.0F, 0.0F, 0.48F));
		PartDefinition cube_r16 = horn2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(94, 88).addBox(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -10.0F, 0.5F, 0.0F, 0.0F, -0.4363F));
		PartDefinition mouth = head.addOrReplaceChild("mouth",
				CubeListBuilder.create().texOffs(22, 86).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(72, 33).addBox(-2.0F, -8.0F, -13.0F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.3825F, -5.8216F, -2.618F, 0.0F, 3.1416F));
		PartDefinition cube_r17 = mouth.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(4, 52).addBox(0.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -4.0F, 1.0F, -0.1745F, 0.1745F, 0.0F));
		PartDefinition cube_r18 = mouth.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(50, 0).addBox(0.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -7.0F, 1.0F, 0.0F, 0.1745F, 0.0F));
		PartDefinition cube_r19 = mouth.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(38, 34).addBox(-2.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -4.0F, 1.0F, -0.1745F, -0.1745F, 0.0F));
		PartDefinition cube_r20 = mouth.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(4, 34).addBox(-2.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -7.0F, 1.0F, 0.0F, -0.1745F, 0.0F));
		PartDefinition cube_r21 = mouth.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(84, 0).addBox(-2.0F, 0.5F, -14.5F, 4.0F, 0.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.5F, 0.5F, -0.1745F, 0.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(72, 57).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.5F, -11.5F, 7.0F, 0.641F, -0.3619F, 0.4385F));
		PartDefinition cube_r22 = right_arm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(22, 70).addBox(-2.5F, -0.5F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 9.5F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(72, 73).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, -12.5F, 7.0F, -0.4083F, 0.3171F, 0.0415F));
		PartDefinition cube_r23 = left_arm.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(42, 86).addBox(-1.5F, -0.5F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 9.5F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition halberd = left_arm.addOrReplaceChild("halberd",
				CubeListBuilder.create().texOffs(0, 0).addBox(0.8908F, -54.4376F, -0.2919F, 1.0F, 66.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 70).addBox(1.3908F, -52.4376F, -5.2919F, 0.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 18.0F, 2.0F, -0.7377F, 0.1961F, 0.1041F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}