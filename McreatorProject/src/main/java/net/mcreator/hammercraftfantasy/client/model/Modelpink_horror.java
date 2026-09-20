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
public class Modelpink_horror<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modelpink_horror"), "main");
	public final ModelPart all;
	public final ModelPart right_leg;
	public final ModelPart left_leg;
	public final ModelPart body;
	public final ModelPart uphead;
	public final ModelPart mayhair;
	public final ModelPart mayhair2;
	public final ModelPart mayhair3;
	public final ModelPart mayhair4;
	public final ModelPart mayhair5;
	public final ModelPart horn1;
	public final ModelPart horn2;
	public final ModelPart right_arm;
	public final ModelPart right_arm2;
	public final ModelPart left_arm;

	public Modelpink_horror(ModelPart root) {
		this.all = root.getChild("all");
		this.right_leg = this.all.getChild("right_leg");
		this.left_leg = this.all.getChild("left_leg");
		this.body = this.all.getChild("body");
		this.uphead = this.body.getChild("uphead");
		this.mayhair = this.uphead.getChild("mayhair");
		this.mayhair2 = this.uphead.getChild("mayhair2");
		this.mayhair3 = this.uphead.getChild("mayhair3");
		this.mayhair4 = this.uphead.getChild("mayhair4");
		this.mayhair5 = this.uphead.getChild("mayhair5");
		this.horn1 = this.uphead.getChild("horn1");
		this.horn2 = this.uphead.getChild("horn2");
		this.right_arm = this.body.getChild("right_arm");
		this.right_arm2 = this.body.getChild("right_arm2");
		this.left_arm = this.body.getChild("left_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition right_leg = all.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(26, 77).addBox(-2.0F, 12.5F, 2.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -14.5F, -4.0F, 0.0F, 0.1745F, 0.0F));
		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 28).addBox(-2.0F, -1.0F, -1.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -4.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 46).addBox(-1.5F, -3.0F, -9.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 4.0F, 7.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(78, 29).addBox(-1.0F, -6.0F, -2.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 12.5F, 4.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition left_leg = all.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(78, 24).addBox(-3.0F, 12.5F, 2.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -14.5F, -4.0F, 0.0F, -0.1745F, 0.0F));
		PartDefinition cube_r4 = left_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 45).addBox(-3.0F, -1.0F, -1.0F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -4.0F, 0.0F, -0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r5 = left_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(26, 82).addBox(-3.0F, -6.0F, -2.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 12.5F, 4.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r6 = left_leg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 16).addBox(-1.5F, -3.0F, -9.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 4.0F, 7.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -14.0F, -1.5F, 14.0F, 17.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.5F, -6.0F, -0.2182F, 0.0F, 0.0F));
		PartDefinition uphead = body.addOrReplaceChild("uphead", CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 9.5F));
		PartDefinition cube_r7 = uphead.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(70, 0).addBox(-2.0F, -9.0F, -3.5F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.0F, -5.0F, 0.5672F, 0.0F, 0.0F));
		PartDefinition cube_r8 = uphead.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 28).addBox(-7.0F, -10.0F, -8.0F, 14.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition mayhair = uphead.addOrReplaceChild("mayhair", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, -6.5F, -7.5F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r9 = mayhair.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 77).addBox(-1.5F, -17.0F, -3.0F, 3.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, 1.0F, 0.6545F, 0.0F, 0.0F));
		PartDefinition cube_r10 = mayhair.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(68, 75).addBox(-2.0F, -5.0F, -3.5F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition mayhair2 = uphead.addOrReplaceChild("mayhair2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, -10.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r11 = mayhair2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(84, 57).addBox(-1.5F, -12.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, 1.0F, 0.7835F, 0.0617F, -0.0618F));
		PartDefinition cube_r12 = mayhair2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 74).addBox(-2.0F, -7.0F, -3.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition mayhair3 = uphead.addOrReplaceChild("mayhair3", CubeListBuilder.create(), PartPose.offset(-5.0F, -6.5F, -7.5F));
		PartDefinition cube_r13 = mayhair3.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(78, 37).addBox(-1.5F, -13.0F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, 1.0F, 0.8727F, 0.0F, 0.0F));
		PartDefinition cube_r14 = mayhair3.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(68, 62).addBox(-2.0F, -9.0F, -3.5F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition mayhair4 = uphead.addOrReplaceChild("mayhair4", CubeListBuilder.create(), PartPose.offsetAndRotation(5.0F, -1.5F, -5.5F, -0.3444F, -0.1781F, 0.4005F));
		PartDefinition cube_r15 = mayhair4.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(84, 64).addBox(-1.5F, -12.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, 1.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition cube_r16 = mayhair4.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(74, 13).addBox(-2.0F, -7.0F, -3.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition mayhair5 = uphead.addOrReplaceChild("mayhair5", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.5F, -4.0F, -5.5F, 0.0F, 0.0F, -0.5236F));
		PartDefinition cube_r17 = mayhair5.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(78, 45).addBox(-0.5F, -18.0F, -3.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.5F, 3.5F, 0.7849F, -0.0308F, 0.0309F));
		PartDefinition cube_r18 = mayhair5.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(66, 84).addBox(-1.0F, -4.0F, -3.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition horn1 = uphead.addOrReplaceChild("horn1", CubeListBuilder.create().texOffs(42, 77).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.5F, -4.5F, 2.3567F, 1.454F, 2.0183F));
		PartDefinition cube_r19 = horn1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(38, 82).addBox(-1.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -10.0F, 0.5F, 0.0F, 0.0F, 0.4363F));
		PartDefinition cube_r20 = horn1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(84, 71).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.0F, 0.0F, -0.48F));
		PartDefinition horn2 = uphead.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(54, 77).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.5F, -4.5F, 2.3567F, -1.454F, -2.0183F));
		PartDefinition cube_r21 = horn2.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(78, 84).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.5F, 0.0F, 0.0F, 0.0F, 0.48F));
		PartDefinition cube_r22 = horn2.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 85).addBox(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -10.0F, 0.5F, 0.0F, 0.0F, -0.4363F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 46).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.5F, -8.0F, 1.5F, 0.0F, 0.0F, -0.5672F));
		PartDefinition cube_r23 = right_arm.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(20, 62).addBox(-2.5F, -0.5F, -2.5F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 9.5F, 0.0F, 1.3524F, 0.0426F, -0.0094F));
		PartDefinition right_arm2 = body.addOrReplaceChild("right_arm2", CubeListBuilder.create().texOffs(50, 0).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.5F, -11.5F, 1.5F, -0.2175F, -0.0859F, -1.8914F));
		PartDefinition cube_r24 = right_arm2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(36, 62).addBox(-2.5F, -0.5F, -2.5F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 9.5F, 0.0F, 1.0906F, 0.0426F, -0.0094F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 58).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -8.0F, 1.5F, 0.2182F, 0.0F, 0.9599F));
		PartDefinition cube_r25 = left_arm.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(52, 62).addBox(-1.5F, -0.5F, -2.5F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 9.5F, 0.0F, 1.3524F, -0.0426F, 0.0094F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}