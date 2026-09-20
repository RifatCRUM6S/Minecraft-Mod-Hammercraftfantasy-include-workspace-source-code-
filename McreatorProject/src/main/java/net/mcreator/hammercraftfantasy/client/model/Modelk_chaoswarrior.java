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
public class Modelk_chaoswarrior<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modelk_chaoswarrior"), "main");
	public final ModelPart all;
	public final ModelPart right_leg;
	public final ModelPart r_downleg;
	public final ModelPart r_upleg;
	public final ModelPart left_leg;
	public final ModelPart l_upleg;
	public final ModelPart l_downleg;
	public final ModelPart body;
	public final ModelPart yakka;
	public final ModelPart balbag;
	public final ModelPart f_pax;
	public final ModelPart b_pax;
	public final ModelPart head;
	public final ModelPart bax;
	public final ModelPart right_horn;
	public final ModelPart left_horn;
	public final ModelPart left_arm;
	public final ModelPart axe2;
	public final ModelPart right_arm;
	public final ModelPart axe;

	public Modelk_chaoswarrior(ModelPart root) {
		this.all = root.getChild("all");
		this.right_leg = this.all.getChild("right_leg");
		this.r_downleg = this.right_leg.getChild("r_downleg");
		this.r_upleg = this.right_leg.getChild("r_upleg");
		this.left_leg = this.all.getChild("left_leg");
		this.l_upleg = this.left_leg.getChild("l_upleg");
		this.l_downleg = this.left_leg.getChild("l_downleg");
		this.body = this.all.getChild("body");
		this.yakka = this.body.getChild("yakka");
		this.balbag = this.body.getChild("balbag");
		this.f_pax = this.balbag.getChild("f_pax");
		this.b_pax = this.balbag.getChild("b_pax");
		this.head = this.body.getChild("head");
		this.bax = this.head.getChild("bax");
		this.right_horn = this.head.getChild("right_horn");
		this.left_horn = this.head.getChild("left_horn");
		this.left_arm = this.body.getChild("left_arm");
		this.axe2 = this.left_arm.getChild("axe2");
		this.right_arm = this.body.getChild("right_arm");
		this.axe = this.right_arm.getChild("axe");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(-5.0F, -2.0F, 1.0F));
		PartDefinition right_leg = all.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0057F, 0.1308F, 0.044F));
		PartDefinition r_downleg = right_leg.addOrReplaceChild("r_downleg", CubeListBuilder.create().texOffs(64, 112).addBox(-3.0F, 12.0F, -1.0F, 9.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, -5.0F));
		PartDefinition cube_r1 = r_downleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 107).addBox(-3.0F, -1.0495F, -6.133F, 8.0F, 13.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.0872F, 0.0038F, -0.0435F));
		PartDefinition r_upleg = right_leg.addOrReplaceChild("r_upleg", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -1.0F));
		PartDefinition cube_r2 = r_upleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(106, 22).addBox(-3.0F, -16.0F, -5.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, -0.1309F, 0.0F, 0.0F));
		PartDefinition left_leg = all.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offsetAndRotation(10.0F, 0.0F, 0.0F, 0.0057F, -0.1308F, -0.044F));
		PartDefinition l_upleg = left_leg.addOrReplaceChild("l_upleg", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -1.0F));
		PartDefinition cube_r3 = l_upleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(106, 45).addBox(-5.0F, -16.0F, -5.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 16.0F, 0.0F, -0.1309F, 0.0F, 0.0F));
		PartDefinition l_downleg = left_leg.addOrReplaceChild("l_downleg", CubeListBuilder.create().texOffs(112, 80).addBox(-6.0F, 12.0F, -1.0F, 9.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, -5.0F));
		PartDefinition cube_r4 = l_downleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(108, 0).addBox(-5.0F, -1.0495F, -6.133F, 8.0F, 13.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.0872F, -0.0038F, 0.0435F));
		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 29).addBox(-8.0F, -9.0086F, -7.1305F, 16.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -3.0F, 2.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -12.0F, -7.0F, 20.0F, 14.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.0086F, -2.1305F, 0.2182F, 0.0F, 0.0F));
		PartDefinition yakka = body.addOrReplaceChild("yakka", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0428F, -4.6526F));
		PartDefinition cube_r6 = yakka.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(150, 76).addBox(-1.1397F, 0.4982F, -0.055F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, -9.0F, 0.9895F, 0.9854F, 0.9033F));
		PartDefinition cube_r7 = yakka.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(24, 148).addBox(0.1397F, 0.4982F, -0.055F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, -9.0F, 0.9895F, -0.9854F, -0.9033F));
		PartDefinition balbag = body.addOrReplaceChild("balbag", CubeListBuilder.create(), PartPose.offset(0.0F, 0.9914F, -0.1305F));
		PartDefinition cube_r8 = balbag.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 48).addBox(-8.0F, -6.0F, -6.0F, 16.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, -2.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r9 = balbag.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(140, 0).addBox(-5.0F, -6.0F, -7.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(9.0F, -2.0F, -6.0F, 0.0868F, -0.087F, -0.0113F));
		PartDefinition cube_r10 = balbag.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(140, 92).addBox(-5.0F, -6.0F, -7.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -2.0F, -6.0F, 0.0863F, 0.0876F, -0.0076F));
		PartDefinition cube_r11 = balbag.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(58, 145).addBox(-5.0F, -6.0F, -7.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.0F, -2.0F, 3.0F, 3.0502F, 0.3471F, 3.0952F));
		PartDefinition cube_r12 = balbag.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(138, 55).addBox(-5.0F, -6.0F, -7.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -2.0F, -6.0F, 0.089F, 0.2615F, 0.0079F));
		PartDefinition f_pax = balbag.addOrReplaceChild("f_pax", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -10.0F));
		PartDefinition cube_r13 = f_pax.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 136).addBox(-7.0F, -2.0F, -1.0F, 14.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.3054F, 0.0F, 0.0F));
		PartDefinition cube_r14 = f_pax.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(140, 104).addBox(-5.0F, -2.0F, -1.0F, 10.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.0F, -2.0F, -0.3054F, 0.0F, 0.0F));
		PartDefinition b_pax = balbag.addOrReplaceChild("b_pax", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 4.0F));
		PartDefinition cube_r15 = b_pax.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(138, 29).addBox(-7.0F, -0.0926F, -0.3986F, 14.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 146).addBox(-5.0F, 7.1849F, -0.2011F, 10.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -26.0342F, -6.5221F, 0.1309F, 0.0F, 0.0F));
		PartDefinition bax = head.addOrReplaceChild("bax",
				CubeListBuilder.create().texOffs(88, 68).addBox(-1.0F, -9.0F, 0.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(40, 75).addBox(3.0F, -5.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(116, 145)
						.addBox(-1.0F, -7.0F, 10.0F, 10.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(64, 124).addBox(9.0F, -7.0F, -1.0F, 2.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(112, 92)
						.addBox(-3.0F, -7.0F, -1.0F, 2.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(88, 80).addBox(-1.0F, -5.0F, 0.0F, 10.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, 1.1216F, -4.8895F));
		PartDefinition cube_r16 = bax.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(56, 157).addBox(0.0F, -1.0F, -5.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 0.0F, 1.1345F, 0.0F));
		PartDefinition cube_r17 = bax.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(70, 22).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -5.0F, 3.0F, -0.2182F, 0.0F, 0.0F));
		PartDefinition cube_r18 = bax.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(110, 157).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1345F, 0.0F));
		PartDefinition right_horn = head.addOrReplaceChild("right_horn",
				CubeListBuilder.create().texOffs(44, 148).addBox(-5.0F, -7.0F, -1.0F, 4.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(140, 12).addBox(-4.0F, 5.0F, -1.0F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-8.0F, -11.8784F, 0.1105F));
		PartDefinition cube_r19 = right_horn.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(82, 150).addBox(-3.0F, -9.0F, -1.0F, 5.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.6109F));
		PartDefinition left_horn = head.addOrReplaceChild("left_horn",
				CubeListBuilder.create().texOffs(0, 156).addBox(1.0F, -7.0F, -1.0F, 4.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(154, 12).addBox(-1.0F, 5.0F, -1.0F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(8.0F, -11.8784F, 0.1105F));
		PartDefinition cube_r20 = left_horn.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(96, 150).addBox(-2.0F, -9.0F, -1.0F, 5.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -6.0F, 0.0F, 0.0F, 0.0F, -0.6109F));
		PartDefinition left_arm = body.addOrReplaceChild(
				"left_arm", CubeListBuilder.create().texOffs(138, 39).addBox(-3.3518F, -2.1082F, -9.8113F, 15.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(138, 47)
						.addBox(-3.3518F, -2.1082F, 6.1887F, 15.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 95).addBox(-2.3518F, -3.1082F, -4.8113F, 8.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.0F, -15.7817F, -2.6697F, 0.0F, 0.0F, -0.1745F));
		PartDefinition cube_r21 = left_arm.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(90, 130).addBox(-8.0F, -1.0F, -8.0F, 7.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.6482F, 15.8918F, 3.1887F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r22 = left_arm.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(56, 41).addBox(-1.0F, -9.0F, -10.0F, 15.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.3518F, -1.1082F, 4.1887F, 0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r23 = left_arm.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(54, 29).addBox(-1.0F, -9.0F, -10.0F, 15.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.3518F, 4.8918F, 3.1887F, -0.5672F, 0.0F, 0.0F));
		PartDefinition axe2 = left_arm.addOrReplaceChild("axe2",
				CubeListBuilder.create().texOffs(40, 87).addBox(-1.4837F, 0.4319F, -1.6584F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(102, 114).addBox(-1.4837F, -3.5681F, -26.6584F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.3062F, 21.8315F, -7.8417F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r24 = axe2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(140, 145).addBox(-1.0F, -9.0F, -6.0F, 2.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4837F, 7.4319F, -18.6584F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r25 = axe2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(70, 0).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 5.0F, 17.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4837F, 10.4319F, -23.6584F, 0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r26 = axe2.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(134, 114).addBox(-1.0F, -2.0F, -13.0F, 2.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4837F, 2.4319F, -1.6584F, -0.3054F, 0.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild(
				"right_arm", CubeListBuilder.create().texOffs(128, 68).addBox(-11.3062F, -2.1685F, -9.8416F, 15.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(138, 21)
						.addBox(-11.3062F, -2.1685F, 6.1584F, 15.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 87).addBox(-5.3062F, -3.1685F, -4.8416F, 8.0F, 17.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.0F, -15.7817F, -2.6697F, 0.0F, 0.0F, 0.1745F));
		PartDefinition cube_r27 = right_arm.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(32, 128).addBox(1.0F, -1.0F, -8.0F, 7.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.3062F, 15.8315F, 3.1584F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r28 = right_arm.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(0, 63).addBox(-14.0F, -9.0F, -10.0F, 15.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.6938F, -1.1685F, 4.1584F, 0.6109F, 0.0F, 0.0F));
		PartDefinition cube_r29 = right_arm.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(56, 53).addBox(-14.0F, -9.0F, -10.0F, 15.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.6938F, 4.8315F, 3.1584F, -0.5672F, 0.0F, 0.0F));
		PartDefinition axe = right_arm.addOrReplaceChild("axe",
				CubeListBuilder.create().texOffs(0, 75).addBox(-0.1743F, -1.5063F, -1.2183F, 2.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(0, 120).addBox(-0.1743F, -5.5063F, -26.2183F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.3062F, 21.8315F, -7.8416F, -0.4363F, 0.0F, 0.0F));
		PartDefinition cube_r30 = axe.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(146, 129).addBox(-1.0F, -9.0F, -6.0F, 2.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.8257F, 5.4937F, -18.2183F, -0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r31 = axe.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(50, 65).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 5.0F, 17.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.8257F, 8.4937F, -23.2183F, 0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r32 = axe.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(116, 130).addBox(-1.0F, -2.0F, -13.0F, 2.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.8257F, 0.4937F, -1.2183F, -0.3054F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}