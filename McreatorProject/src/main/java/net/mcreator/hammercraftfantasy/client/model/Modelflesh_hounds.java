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
public class Modelflesh_hounds<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modelflesh_hounds"), "main");
	public final ModelPart left_arm;
	public final ModelPart left_leg;
	public final ModelPart right_arm;
	public final ModelPart right_leg;
	public final ModelPart Body;
	public final ModelPart tail;
	public final ModelPart head;
	public final ModelPart head_up;
	public final ModelPart head_down;
	public final ModelPart right_fin;
	public final ModelPart left_fin;

	public Modelflesh_hounds(ModelPart root) {
		this.left_arm = root.getChild("left_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_arm = root.getChild("right_arm");
		this.right_leg = root.getChild("right_leg");
		this.Body = root.getChild("Body");
		this.tail = this.Body.getChild("tail");
		this.head = root.getChild("head");
		this.head_up = this.head.getChild("head_up");
		this.head_down = this.head.getChild("head_down");
		this.right_fin = this.head.getChild("right_fin");
		this.left_fin = this.head.getChild("left_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(96, 70).addBox(-2.0F, 13.0F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 9.0F, -8.0F));
		PartDefinition cube_r1 = left_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(52, 27).addBox(-2.0F, -4.0F, -11.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.0F, 3.0F, -0.9163F, 0.0F, 0.0F));
		PartDefinition cube_r2 = left_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(92, 96).addBox(-2.0F, -7.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 13.0F, 0.0F, -0.5672F, 0.0F, 0.0F));
		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(30, 101).addBox(-2.0F, 13.0F, -6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 9.0F, 14.0F));
		PartDefinition cube_r3 = left_leg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(60, 94).addBox(-2.0F, -11.0F, 0.0F, 5.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 6.0F, -7.0F, -0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r4 = left_leg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(96, 0).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r5 = left_leg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 101).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 13.0F, -4.0F, -0.9163F, 0.0F, 0.0F));
		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(96, 70).mirror().addBox(-2.0F, 13.0F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-6.0F, 9.0F, -8.0F));
		PartDefinition cube_r6 = right_arm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(52, 27).mirror().addBox(-3.0F, -4.0F, -11.0F, 5.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 8.0F, 3.0F, -0.9163F, 0.0F, 0.0F));
		PartDefinition cube_r7 = right_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(92, 96).mirror().addBox(-2.0F, -7.0F, -1.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 13.0F, 0.0F, -0.5672F, 0.0F, 0.0F));
		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(30, 101).mirror().addBox(-2.0F, 13.0F, -6.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-5.0F, 9.0F, 14.0F));
		PartDefinition cube_r8 = right_leg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(60, 94).mirror().addBox(-3.0F, -11.0F, 0.0F, 5.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 6.0F, -7.0F, -0.7418F, 0.0F, 0.0F));
		PartDefinition cube_r9 = right_leg.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(96, 0).mirror().addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, -0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r10 = right_leg.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 101).mirror().addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 13.0F, -4.0F, -0.9163F, 0.0F, 0.0F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -7.7798F, -10.9467F, 16.0F, 11.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(0, 27).addBox(-7.0F, -5.7798F, 4.0533F, 14.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 7.0F, -1.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r11 = Body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(102, 106).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.7798F, 9.0533F, -0.48F, 0.0F, 0.0F));
		PartDefinition cube_r12 = Body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(46, 101).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.7798F, 0.0533F, -0.48F, 0.0F, 0.0F));
		PartDefinition cube_r13 = Body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(106, 96).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -6.7798F, -8.9467F, -0.48F, 0.0F, 0.0F));
		PartDefinition tail = Body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(64, 0).addBox(-3.0F, -5.0F, -1.0F, 6.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.2202F, 16.0533F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r14 = tail.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(30, 81).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.0F, 10.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -12.0F));
		PartDefinition head_up = head.addOrReplaceChild("head_up",
				CubeListBuilder.create().texOffs(96, 60).addBox(-2.0F, -3.0F, -9.0F, 4.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(86, 27).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, -2.0F));
		PartDefinition cube_r15 = head_up.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(94, 106).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, -7.0F, -0.1309F, 0.0F, 0.0F));
		PartDefinition Rup_r1 = head_up.addOrReplaceChild("Rup_r1", CubeListBuilder.create().texOffs(70, 43).addBox(-1.0F, -3.0F, 3.0F, 3.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -11.0F, 0.1309F, -0.3054F, 0.0F));
		PartDefinition Rdwn_r1 = head_up.addOrReplaceChild("Rdwn_r1", CubeListBuilder.create().texOffs(36, 64).addBox(-1.0F, -3.0F, 3.0F, 3.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -11.0F, 0.0F, -0.3054F, 0.0F));
		PartDefinition cube_r16 = head_up.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -10.0F, 0.1745F, 0.0F, 0.0F));
		PartDefinition Lup_r1 = head_up.addOrReplaceChild("Lup_r1", CubeListBuilder.create().texOffs(0, 67).addBox(-2.0F, -3.0F, 3.0F, 3.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -11.0F, 0.1309F, 0.3054F, 0.0F));
		PartDefinition Ldwn_r1 = head_up.addOrReplaceChild("Ldwn_r1", CubeListBuilder.create().texOffs(66, 64).addBox(-2.0F, -3.0F, 3.0F, 3.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -11.0F, 0.0F, 0.3054F, 0.0F));
		PartDefinition head_down = head.addOrReplaceChild("head_down",
				CubeListBuilder.create().texOffs(64, 17).addBox(-2.0F, -0.2421F, -9.6264F, 4.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(78, 106).addBox(-2.0F, -0.2421F, -10.6264F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition cube_r17 = head_down.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(60, 81).addBox(-2.0F, -4.0F, 3.0F, 4.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.7579F, -12.6264F, 0.0F, -0.1309F, 0.0F));
		PartDefinition cube_r18 = head_down.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 84).addBox(-2.0F, -4.0F, 3.0F, 4.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.7579F, -12.6264F, 0.0F, 0.1309F, 0.0F));
		PartDefinition right_fin = head.addOrReplaceChild("right_fin",
				CubeListBuilder.create().texOffs(70, 60).addBox(-13.8042F, -1.0F, -0.6308F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(96, 77).addBox(-12.8042F, -2.0F, 0.3692F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 0.0F, -5.0F, 0.0F, 0.6545F, 0.0F));
		PartDefinition cube_r19 = right_fin.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(86, 37).addBox(-9.0F, -1.0F, 0.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8042F, 2.0F, -0.6308F, 0.0F, 0.0F, -0.3927F));
		PartDefinition cube_r20 = right_fin.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(86, 35).addBox(-9.0F, -1.0F, 0.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8042F, -2.0F, -0.6308F, 0.0F, 0.0F, 0.3927F));
		PartDefinition cube_r21 = right_fin.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(20, 97).addBox(-9.0F, -2.0F, 1.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8042F, 0.0F, -0.6308F, 0.0F, 0.0F, -0.3054F));
		PartDefinition cube_r22 = right_fin.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 97).addBox(-9.0F, -3.0F, 1.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.8042F, 0.0F, -0.6308F, 0.0F, 0.0F, 0.3054F));
		PartDefinition left_fin = head.addOrReplaceChild("left_fin",
				CubeListBuilder.create().texOffs(70, 62).addBox(1.8042F, -1.0F, -0.6308F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(40, 97).addBox(2.8042F, -2.0F, 0.3692F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 0.0F, -5.0F, 0.0F, -0.6545F, 0.0F));
		PartDefinition cube_r23 = left_fin.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(86, 41).addBox(-2.0F, -1.0F, 0.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.8042F, 2.0F, -0.6308F, 0.0F, 0.0F, 0.3927F));
		PartDefinition cube_r24 = left_fin.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(86, 39).addBox(-2.0F, -1.0F, 0.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.8042F, -2.0F, -0.6308F, 0.0F, 0.0F, -0.3927F));
		PartDefinition cube_r25 = left_fin.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(100, 47).addBox(-1.0F, -2.0F, 1.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.8042F, 0.0F, -0.6308F, 0.0F, 0.0F, 0.3054F));
		PartDefinition cube_r26 = left_fin.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(100, 43).addBox(-1.0F, -3.0F, 1.0F, 10.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.8042F, 0.0F, -0.6308F, 0.0F, 0.0F, -0.3054F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}