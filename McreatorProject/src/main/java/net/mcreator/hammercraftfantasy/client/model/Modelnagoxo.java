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
public class Modelnagoxo<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modelnagoxo"), "main");
	public final ModelPart body;
	public final ModelPart mouth;
	public final ModelPart m_up;
	public final ModelPart m_down;
	public final ModelPart right_arm;
	public final ModelPart left_arm;
	public final ModelPart tail;
	public final ModelPart head;
	public final ModelPart hair;
	public final ModelPart hair1;
	public final ModelPart hair2;
	public final ModelPart hair3;
	public final ModelPart hair4;
	public final ModelPart hair5;
	public final ModelPart hair6;
	public final ModelPart hair7;
	public final ModelPart hair8;
	public final ModelPart hair9;
	public final ModelPart hair10;
	public final ModelPart hair11;
	public final ModelPart hair12;
	public final ModelPart hair13;
	public final ModelPart hair14;

	public Modelnagoxo(ModelPart root) {
		this.body = root.getChild("body");
		this.mouth = this.body.getChild("mouth");
		this.m_up = this.mouth.getChild("m_up");
		this.m_down = this.mouth.getChild("m_down");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.tail = root.getChild("tail");
		this.head = root.getChild("head");
		this.hair = this.head.getChild("hair");
		this.hair1 = this.hair.getChild("hair1");
		this.hair2 = this.hair.getChild("hair2");
		this.hair3 = this.hair.getChild("hair3");
		this.hair4 = this.hair.getChild("hair4");
		this.hair5 = this.hair.getChild("hair5");
		this.hair6 = this.hair.getChild("hair6");
		this.hair7 = this.hair.getChild("hair7");
		this.hair8 = this.hair.getChild("hair8");
		this.hair9 = this.hair.getChild("hair9");
		this.hair10 = this.hair.getChild("hair10");
		this.hair11 = this.hair.getChild("hair11");
		this.hair12 = this.hair.getChild("hair12");
		this.hair13 = this.hair.getChild("hair13");
		this.hair14 = this.hair.getChild("hair14");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -24.0F, -10.0F, 20.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(72, 26).addBox(-12.0F, -6.0F, -11.0F, 24.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition m_up = mouth.addOrReplaceChild("m_up",
				CubeListBuilder.create().texOffs(0, 54).addBox(-12.0F, -4.0F, -12.0F, 24.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(82, 23).addBox(-11.5F, -1.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(110, 16)
						.addBox(-12.5F, -1.5F, -11.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(102, 16).addBox(-12.5F, -2.5F, -9.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(102, 20)
						.addBox(-12.5F, -2.0F, -6.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(110, 19).addBox(-12.5F, -1.5F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(82, 19)
						.addBox(-9.5F, -2.0F, -12.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(86, 23).addBox(-6.0F, -1.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(82, 16)
						.addBox(-4.0F, -1.0F, -12.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(96, 104).addBox(-1.0F, -1.0F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(104, 96)
						.addBox(3.0F, -1.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(86, 19).addBox(6.0F, -1.0F, -12.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(104, 99)
						.addBox(7.5F, -1.0F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(100, 104).addBox(10.0F, -1.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(98, 16)
						.addBox(11.5F, -1.5F, -10.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(98, 20).addBox(11.5F, -2.0F, -8.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(108, 108)
						.addBox(11.5F, -1.5F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition m_down = mouth.addOrReplaceChild("m_down",
				CubeListBuilder.create().texOffs(0, 70).addBox(-12.0F, 0.0F, -12.0F, 24.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(104, 102).addBox(10.5F, -1.0F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(108, 102)
						.addBox(11.5F, -1.0F, -11.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(104, 108).addBox(11.5F, -0.5F, -7.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(108, 105)
						.addBox(11.5F, -1.0F, -5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(76, 20).addBox(11.5F, -1.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(90, 16)
						.addBox(8.0F, -1.5F, -12.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(104, 105).addBox(4.5F, -0.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(106, 16)
						.addBox(2.0F, -1.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(90, 20).addBox(0.5F, -1.0F, -12.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(94, 16)
						.addBox(-2.0F, -2.0F, -12.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(106, 19).addBox(-5.0F, -0.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(106, 22)
						.addBox(-7.0F, -1.0F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(96, 107).addBox(-8.5F, -1.5F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(100, 107)
						.addBox(-10.5F, -1.0F, -12.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(108, 96).addBox(-12.5F, -1.0F, -10.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(76, 16)
						.addBox(-12.5F, -0.5F, -8.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(108, 99).addBox(-12.5F, -1.0F, -5.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(94, 20)
						.addBox(-12.5F, -1.5F, -2.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(72, 83).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.5F, -25.0F, -1.0F, 0.0F, 0.0F, 0.3491F));
		PartDefinition cube_r1 = right_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 86).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 10.5F, 0.0F, -0.4363F, 0.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 86).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(10.5F, -25.0F, -1.0F, 0.0F, 0.0F, -0.3491F));
		PartDefinition cube_r2 = left_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 86).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 10.5F, 0.0F, -0.4363F, 0.0F, 0.0F));
		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 26).addBox(-12.0F, 0.0F, 1.0F, 24.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(72, 43)
				.addBox(-10.0F, 4.0F, 13.0F, 20.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(76, 0).addBox(-6.0F, 8.0F, 21.0F, 12.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, -1.0F));
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(72, 63).addBox(-7.0F, -7.5F, -8.0F, 14.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, -7.0F, -0.1309F, 0.0F, 0.0F));
		PartDefinition hair1 = hair.addOrReplaceChild("hair1", CubeListBuilder.create().texOffs(0, 103).addBox(-1.0F, -14.0F, 0.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition hair2 = hair.addOrReplaceChild("hair2", CubeListBuilder.create().texOffs(64, 103).addBox(-1.0F, -11.0F, 0.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, 0.5F, 0.0F, 0.0F, 0.0436F));
		PartDefinition hair3 = hair.addOrReplaceChild("hair3", CubeListBuilder.create().texOffs(104, 83).addBox(-1.0F, -11.0F, 0.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -3.0F, -0.5F, -0.0436F, 0.0F, -0.0436F));
		PartDefinition hair4 = hair.addOrReplaceChild("hair4", CubeListBuilder.create().texOffs(80, 100).addBox(-1.0F, -15.0F, 0.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.5F, -3.0F, 0.5F, -0.0436F, 0.0019F, 0.0436F));
		PartDefinition hair5 = hair.addOrReplaceChild("hair5", CubeListBuilder.create().texOffs(88, 100).addBox(-1.0F, -15.0F, 0.0F, 2.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -3.0F, -0.5F, 0.2182F, 0.0F, 0.0F));
		PartDefinition hair6 = hair.addOrReplaceChild("hair6", CubeListBuilder.create().texOffs(24, 103).addBox(-1.0F, -13.0F, 0.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -3.0F, 3.5F, -0.0873F, 0.0F, 0.0F));
		PartDefinition hair7 = hair.addOrReplaceChild("hair7", CubeListBuilder.create().texOffs(40, 103).addBox(-1.0F, -12.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, 8.0F, 0.0F, 0.0F, -0.0436F));
		PartDefinition hair8 = hair.addOrReplaceChild("hair8", CubeListBuilder.create().texOffs(96, 83).addBox(-1.0F, -19.0F, 0.0F, 2.0F, 19.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -3.0F, 6.0F, -0.0436F, 0.0019F, 0.0436F));
		PartDefinition hair9 = hair.addOrReplaceChild("hair9", CubeListBuilder.create().texOffs(8, 103).addBox(-1.0F, -14.0F, 0.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -3.0F, 3.5F, -0.1304F, 0.0114F, 0.0865F));
		PartDefinition hair10 = hair.addOrReplaceChild("hair10", CubeListBuilder.create().texOffs(16, 103).addBox(-1.0F, -14.0F, 0.0F, 2.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.5F, -3.0F, 5.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition hair11 = hair.addOrReplaceChild("hair11", CubeListBuilder.create().texOffs(48, 103).addBox(-1.0F, -12.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.2182F));
		PartDefinition hair12 = hair.addOrReplaceChild("hair12", CubeListBuilder.create().texOffs(72, 100).addBox(-1.0F, -17.0F, 0.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 1.0F, 6.5F, 0.0F, 0.0F, -0.3054F));
		PartDefinition hair13 = hair.addOrReplaceChild("hair13", CubeListBuilder.create().texOffs(32, 103).addBox(-1.0F, -13.0F, 0.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -1.0F, 1.0F, 0.0F, 0.0F, 0.3054F));
		PartDefinition hair14 = hair.addOrReplaceChild("hair14", CubeListBuilder.create().texOffs(56, 103).addBox(-1.0F, -12.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 1.0F, 6.5F, 0.0F, 0.0F, 0.2182F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}