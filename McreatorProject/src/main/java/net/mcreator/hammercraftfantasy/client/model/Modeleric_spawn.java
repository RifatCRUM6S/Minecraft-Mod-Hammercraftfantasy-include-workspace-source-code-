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
public class Modeleric_spawn<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modeleric_spawn"), "main");
	public final ModelPart all;
	public final ModelPart right_leg1;
	public final ModelPart right_leg2;
	public final ModelPart left_leg1;
	public final ModelPart f2;
	public final ModelPart f1;
	public final ModelPart left_leg2;
	public final ModelPart body;
	public final ModelPart b_tomur_1;
	public final ModelPart b_tomur_2;
	public final ModelPart b_tomur_3;
	public final ModelPart b_tomur_4;
	public final ModelPart mouth1;
	public final ModelPart m_up;
	public final ModelPart m_down;
	public final ModelPart mouth2;
	public final ModelPart m_up2;
	public final ModelPart m_down2;
	public final ModelPart mouth3;
	public final ModelPart m_up3;
	public final ModelPart m_down3;
	public final ModelPart b_tomur_5;
	public final ModelPart tomur_arm_1;
	public final ModelPart la_up;
	public final ModelPart la_down;
	public final ModelPart tomur_arm_2;
	public final ModelPart la_up2;
	public final ModelPart la_down2;

	public Modeleric_spawn(ModelPart root) {
		this.all = root.getChild("all");
		this.right_leg1 = this.all.getChild("right_leg1");
		this.right_leg2 = this.all.getChild("right_leg2");
		this.left_leg1 = this.all.getChild("left_leg1");
		this.f2 = this.left_leg1.getChild("f2");
		this.f1 = this.left_leg1.getChild("f1");
		this.left_leg2 = this.all.getChild("left_leg2");
		this.body = this.all.getChild("body");
		this.b_tomur_1 = this.body.getChild("b_tomur_1");
		this.b_tomur_2 = this.body.getChild("b_tomur_2");
		this.b_tomur_3 = this.body.getChild("b_tomur_3");
		this.b_tomur_4 = this.body.getChild("b_tomur_4");
		this.mouth1 = this.body.getChild("mouth1");
		this.m_up = this.mouth1.getChild("m_up");
		this.m_down = this.mouth1.getChild("m_down");
		this.mouth2 = this.body.getChild("mouth2");
		this.m_up2 = this.mouth2.getChild("m_up2");
		this.m_down2 = this.mouth2.getChild("m_down2");
		this.mouth3 = this.body.getChild("mouth3");
		this.m_up3 = this.mouth3.getChild("m_up3");
		this.m_down3 = this.mouth3.getChild("m_down3");
		this.b_tomur_5 = this.body.getChild("b_tomur_5");
		this.tomur_arm_1 = this.body.getChild("tomur_arm_1");
		this.la_up = this.tomur_arm_1.getChild("la_up");
		this.la_down = this.tomur_arm_1.getChild("la_down");
		this.tomur_arm_2 = this.body.getChild("tomur_arm_2");
		this.la_up2 = this.tomur_arm_2.getChild("la_up2");
		this.la_down2 = this.tomur_arm_2.getChild("la_down2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));
		PartDefinition right_leg1 = all.addOrReplaceChild("right_leg1", CubeListBuilder.create(), PartPose.offsetAndRotation(-9.5F, 4.0F, -8.5F, 0.2986F, 0.0651F, -0.2084F));
		PartDefinition cube_r1 = right_leg1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(238, 192).addBox(-6.0F, -19.0F, -4.0F, 8.0F, 13.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.4771F, 17.1733F, -4.1085F, -0.1731F, 0.0227F, 0.1289F));
		PartDefinition cube_r2 = right_leg1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(102, 124).addBox(-7.0F, -17.0F, -4.0F, 4.0F, 13.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-19.4771F, 30.1733F, -23.1085F, -0.9238F, 0.3426F, 0.9836F));
		PartDefinition cube_r3 = right_leg1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(196, 242).addBox(-7.0F, -17.0F, -4.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.9771F, 28.1733F, -15.6085F, -0.7022F, 0.1395F, 0.8134F));
		PartDefinition cube_r4 = right_leg1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(136, 238).addBox(-7.0F, -17.0F, -4.0F, 9.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.4771F, 26.1733F, -8.1085F, -0.2618F, 0.0F, 0.2618F));
		PartDefinition right_leg2 = all.addOrReplaceChild("right_leg2", CubeListBuilder.create().texOffs(64, 202).addBox(-8.5F, 15.5F, -8.0F, 12.0F, 17.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0F, 3.5F, 10.5F, 0.0F, 1.1345F, 0.0F));
		PartDefinition cube_r5 = right_leg2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 206).addBox(-9.0F, -19.0F, -7.0F, 11.0F, 19.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 18.5F, -0.5F, -0.1731F, 0.0227F, 0.1289F));
		PartDefinition left_leg1 = all.addOrReplaceChild("left_leg1", CubeListBuilder.create().texOffs(190, 185).addBox(-3.5F, 16.0F, -8.0F, 12.0F, 17.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(9.5F, 3.0F, -12.0F));
		PartDefinition cube_r6 = left_leg1.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(44, 206).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, 30.5F, 3.0F, 1.0845F, 0.1546F, 1.4896F));
		PartDefinition cube_r7 = left_leg1.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(112, 202).addBox(-2.0F, -19.0F, -7.0F, 11.0F, 19.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 19.0F, -0.5F, -0.1731F, -0.0227F, -0.1289F));
		PartDefinition f2 = left_leg1.addOrReplaceChild("f2", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.5F, 29.0F, -11.5F, -1.9199F, 0.4363F, -1.5708F));
		PartDefinition cube_r8 = f2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(156, 202).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -2.5F, 0.0F, 0.0F, 0.0F, -0.1745F));
		PartDefinition cube_r9 = f2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(240, 48).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition f1 = left_leg1.addOrReplaceChild("f1", CubeListBuilder.create(), PartPose.offsetAndRotation(7.5F, 31.5F, -8.0F, -1.0908F, 0.2182F, -1.5708F));
		PartDefinition cube_r10 = f1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(226, 48).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 2.5F, 0.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition cube_r11 = f1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(196, 45).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
		PartDefinition left_leg2 = all.addOrReplaceChild("left_leg2", CubeListBuilder.create(), PartPose.offsetAndRotation(13.5F, 2.5F, 17.0F, 0.0F, -0.5672F, 0.0F));
		PartDefinition cube_r12 = left_leg2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(172, 238).addBox(-2.1794F, -0.7015F, -1.1528F, 6.0F, 25.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.2541F, 1.5F, -1.3027F, 0.0521F, 0.2146F, -1.5607F));
		PartDefinition cube_r13 = left_leg2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(242, 152).addBox(0.0F, -37.0F, 1.0F, 4.0F, 37.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.2459F, 33.5F, -0.8027F, 0.0F, 0.0F, 0.3927F));
		PartDefinition body = all.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(120, 61).addBox(-16.0F, -7.0F, -14.0F, 32.0F, 10.0F, 33.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-11.0F, -34.5F, -9.0F, 37.0F, 28.0F, 33.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 3.5F, 0.0F));
		PartDefinition b_tomur_1 = body.addOrReplaceChild("b_tomur_1", CubeListBuilder.create(), PartPose.offset(5.0F, -37.0F, -19.0F));
		PartDefinition cube_r14 = b_tomur_1.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(140, 0).addBox(-18.0F, -20.0F, -2.0F, 24.0F, 24.0F, 21.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 13.5F, -6.0F, 0.1309F, 0.0F, 0.1745F));
		PartDefinition b_tomur_2 = body.addOrReplaceChild("b_tomur_2", CubeListBuilder.create(), PartPose.offset(29.0F, -37.0F, 17.5F));
		PartDefinition cube_r15 = b_tomur_2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 124).addBox(-24.0F, -24.0F, -28.0F, 25.0F, 24.0F, 26.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.0F, 7.0F, 15.0F, 0.1745F, 0.0F, -0.1745F));
		PartDefinition b_tomur_3 = body.addOrReplaceChild("b_tomur_3", CubeListBuilder.create(), PartPose.offset(-6.0F, -36.0F, -12.0F));
		PartDefinition cube_r16 = b_tomur_3.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(190, 158).addBox(-1.0F, -14.0F, -12.0F, 13.0F, 14.0F, 13.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.0F, 8.0F, -10.5F, 0.3054F, 0.0F, -0.3491F));
		PartDefinition cube_r17 = b_tomur_3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(0, 61).addBox(-29.0F, -33.0F, -32.0F, 30.0F, 33.0F, 30.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 29.0F, 43.5F, -0.1309F, 0.0F, -0.1745F));
		PartDefinition b_tomur_4 = body.addOrReplaceChild("b_tomur_4", CubeListBuilder.create(), PartPose.offset(-13.0F, -30.0F, 1.5F));
		PartDefinition cube_r18 = b_tomur_4.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(102, 158).addBox(-15.0F, -22.0F, -1.0F, 22.0F, 22.0F, 22.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.0F, 24.0F, -4.5F, 0.9893F, 0.3492F, 0.5891F));
		PartDefinition cube_r19 = b_tomur_4.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(120, 104).addBox(-1.0F, -27.0F, -6.0F, 27.0F, 27.0F, 27.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.5F, 2.0F, 9.5F, 0.1309F, 0.0F, -0.2182F));
		PartDefinition mouth1 = body.addOrReplaceChild("mouth1", CubeListBuilder.create(), PartPose.offsetAndRotation(-7.5F, -14.5F, -10.5F, 1.7453F, 0.0F, 0.0F));
		PartDefinition m_up = mouth1.addOrReplaceChild("m_up",
				CubeListBuilder.create().texOffs(156, 214).addBox(-8.4327F, -16.7288F, -4.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(102, 155).addBox(-7.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(112, 151).addBox(-5.0F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(102, 151).addBox(-2.5F, -17.5F, -5.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(200, 55)
						.addBox(1.0F, -17.5F, -6.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(108, 155).addBox(3.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(208, 55)
						.addBox(6.5F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.5F, 4.5F, -0.1309F, 0.0F, 0.0F));
		PartDefinition m_down = mouth1.addOrReplaceChild("m_down",
				CubeListBuilder.create().texOffs(228, 104).addBox(-8.4327F, -16.2288F, -2.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(114, 155).addBox(6.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(58, 206)
						.addBox(3.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(216, 55).addBox(1.5F, -17.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(58, 209)
						.addBox(-0.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(58, 212).addBox(-4.0F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(44, 227)
						.addBox(-6.0F, -17.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.0F, -4.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition mouth2 = body.addOrReplaceChild("mouth2", CubeListBuilder.create(), PartPose.offsetAndRotation(21.0F, -14.5F, 6.0F, 0.3409F, 0.0902F, 1.9198F));
		PartDefinition m_up2 = mouth2.addOrReplaceChild("m_up2",
				CubeListBuilder.create().texOffs(228, 128).addBox(-8.4327F, -16.7288F, -4.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(58, 215).addBox(-7.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(52, 227).addBox(-5.0F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(180, 55).addBox(-2.5F, -17.5F, -5.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(228, 152)
						.addBox(1.0F, -17.5F, -6.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(58, 218).addBox(3.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(136, 232)
						.addBox(6.5F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.5F, 4.5F, -0.1309F, 0.0F, 0.0F));
		PartDefinition m_down2 = mouth2.addOrReplaceChild("m_down2",
				CubeListBuilder.create().texOffs(230, 0).addBox(-8.4327F, -16.2288F, -2.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(58, 221).addBox(6.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(58, 224)
						.addBox(3.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(144, 232).addBox(1.5F, -17.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(224, 58)
						.addBox(-0.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(230, 58).addBox(-4.0F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(36, 236)
						.addBox(-6.0F, -17.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.0F, -4.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition mouth3 = body.addOrReplaceChild("mouth3", CubeListBuilder.create(), PartPose.offsetAndRotation(6.5F, -20.5F, 21.5F, -1.746F, 0.0859F, 3.1264F));
		PartDefinition m_up3 = mouth3.addOrReplaceChild("m_up3",
				CubeListBuilder.create().texOffs(230, 24).addBox(-8.4327F, -16.7288F, -4.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(236, 58).addBox(-7.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(196, 238).addBox(-5.0F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(190, 55).addBox(-2.5F, -17.5F, -5.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(36, 240)
						.addBox(1.0F, -17.5F, -6.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(236, 152).addBox(3.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(220, 242)
						.addBox(6.5F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.5F, 4.5F, -0.1309F, 0.0F, 0.0F));
		PartDefinition m_down3 = mouth3.addOrReplaceChild("m_down3",
				CubeListBuilder.create().texOffs(44, 231).addBox(-8.4327F, -16.2288F, -2.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(236, 155).addBox(6.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(242, 58)
						.addBox(3.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(228, 242).addBox(1.5F, -17.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(36, 244)
						.addBox(-0.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(244, 242).addBox(-4.0F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(236, 242)
						.addBox(-6.0F, -17.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.0F, -4.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition b_tomur_5 = body.addOrReplaceChild("b_tomur_5", CubeListBuilder.create(), PartPose.offset(-17.5F, -26.0F, -15.0F));
		PartDefinition cube_r20 = b_tomur_5.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(0, 174).addBox(-15.0F, -16.0F, -5.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.5F, 5.5F, -3.0F, 0.1309F, 0.0F, 0.0F));
		PartDefinition tomur_arm_1 = body.addOrReplaceChild("tomur_arm_1", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -30.5F, -3.0F, 0.0F, 0.0F, 2.4435F));
		PartDefinition la_up = tomur_arm_1.addOrReplaceChild("la_up", CubeListBuilder.create().texOffs(64, 174).addBox(17.0F, -48.5F, -1.5F, 9.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-21.2498F, 49.1812F, -3.0F));
		PartDefinition cube_r21 = la_up.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(140, 45).addBox(-1.5F, -3.5F, -3.5F, 3.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(27.5F, -40.5F, 3.0F, 0.3054F, 0.0F, 0.0F));
		PartDefinition la_down = tomur_arm_1.addOrReplaceChild("la_down", CubeListBuilder.create().texOffs(204, 214).addBox(-5.5F, -0.5F, -5.5F, 11.0F, 17.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.2502F, 16.6812F, 0.5F, -0.1314F, -0.0865F, 0.0114F));
		PartDefinition cube_r22 = la_down.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(44, 217).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(212, 45).addBox(-3.0F, 0.0F, 5.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 21.0F, -3.5F, 0.0F, 0.0F, 0.0873F));
		PartDefinition cube_r23 = la_down.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(180, 45).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(102, 141).addBox(-2.0F, -3.0F, 5.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 18.5F, -3.5F, 0.0F, 0.0F, -0.1745F));
		PartDefinition cube_r24 = la_down.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(172, 202).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 18.0F, -2.0F, 0.0F, 0.0F, 0.1745F));
		PartDefinition tomur_arm_2 = body.addOrReplaceChild("tomur_arm_2", CubeListBuilder.create(), PartPose.offsetAndRotation(17.0F, -19.5F, 20.5F, 2.1635F, 0.5236F, 0.333F));
		PartDefinition la_up2 = tomur_arm_2.addOrReplaceChild("la_up2", CubeListBuilder.create().texOffs(0, 236).addBox(17.0F, -48.5F, -1.5F, 9.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-19.0F, 46.5F, -3.0F));
		PartDefinition cube_r25 = la_up2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(160, 45).addBox(-1.5F, -3.5F, -3.5F, 3.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(27.5F, -40.5F, 3.0F, 0.3054F, 0.0F, 0.0F));
		PartDefinition la_down2 = tomur_arm_2.addOrReplaceChild("la_down2", CubeListBuilder.create().texOffs(92, 232).addBox(-5.5F, -0.5F, -5.5F, 11.0F, 17.0F, 11.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 14.0F, 0.5F, -0.0984F, 0.1228F, 1.4832F));
		PartDefinition cube_r26 = la_down2.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(240, 48).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(226, 48).addBox(-3.0F, 0.0F, 5.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 21.0F, -3.5F, 0.0F, 0.0F, 0.0873F));
		PartDefinition cube_r27 = la_down2.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(156, 202).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(196, 45).addBox(-2.0F, -3.0F, 5.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 18.5F, -3.5F, 0.0F, 0.0F, -0.1745F));
		PartDefinition cube_r28 = la_down2.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(44, 206).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 18.0F, -2.0F, 0.0F, 0.0F, 0.1745F));
		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}