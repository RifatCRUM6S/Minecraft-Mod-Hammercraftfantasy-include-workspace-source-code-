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
public class Modelfiend_of_slaanesh<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modelfiend_of_slaanesh"), "main");
	public final ModelPart body_1;
	public final ModelPart head;
	public final ModelPart right_horn1;
	public final ModelPart til;
	public final ModelPart right_horn2;
	public final ModelPart left_horn1;
	public final ModelPart right_arm;
	public final ModelPart ra_down;
	public final ModelPart left_arm;
	public final ModelPart ra_down2;
	public final ModelPart body_2;
	public final ModelPart tail;
	public final ModelPart t1;
	public final ModelPart t2;
	public final ModelPart t3;
	public final ModelPart right_leg_1;
	public final ModelPart right_leg_2;
	public final ModelPart left_leg_1;
	public final ModelPart left_leg_2;

	public Modelfiend_of_slaanesh(ModelPart root) {
		this.body_1 = root.getChild("body_1");
		this.head = this.body_1.getChild("head");
		this.right_horn1 = this.head.getChild("right_horn1");
		this.til = this.head.getChild("til");
		this.right_horn2 = this.head.getChild("right_horn2");
		this.left_horn1 = this.head.getChild("left_horn1");
		this.right_arm = this.body_1.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.left_arm = this.body_1.getChild("left_arm");
		this.ra_down2 = this.left_arm.getChild("ra_down2");
		this.body_2 = root.getChild("body_2");
		this.tail = this.body_2.getChild("tail");
		this.t1 = this.tail.getChild("t1");
		this.t2 = this.t1.getChild("t2");
		this.t3 = this.t2.getChild("t3");
		this.right_leg_1 = this.body_2.getChild("right_leg_1");
		this.right_leg_2 = this.body_2.getChild("right_leg_2");
		this.left_leg_1 = this.body_2.getChild("left_leg_1");
		this.left_leg_2 = this.body_2.getChild("left_leg_2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body_1 = partdefinition.addOrReplaceChild("body_1", CubeListBuilder.create().texOffs(56, 27).addBox(-8.0F, -15.0F, -8.5F, 16.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -9.5F));
		PartDefinition cube_r1 = body_1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(112, 93).addBox(-3.0F, -10.0F, -7.0F, 6.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -15.0F, -0.5F, 0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r2 = body_1.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 27).addBox(-7.0F, -9.0F, -12.0F, 14.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 4.0F, -2.5F, -1.0908F, 0.0F, 0.0F));
		PartDefinition head = body_1.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(24, 138).addBox(-3.0F, -4.2606F, 1.0701F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 121).addBox(-2.0F, -4.2606F, -8.9299F, 4.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -20.5F, -6.5F, 0.6545F, 0.0F, 0.0F));
		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(106, 25).addBox(0.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -1.2606F, 5.0701F, -0.1745F, 0.1745F, 0.0F));
		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(100, 45).addBox(0.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -4.2606F, 5.0701F, 0.0F, 0.1745F, 0.0F));
		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(108, 0).addBox(-2.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -1.2606F, 5.0701F, -0.1745F, -0.1745F, 0.0F));
		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 95).addBox(-2.0F, 0.0F, -15.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -4.2606F, 5.0701F, 0.0F, -0.1745F, 0.0F));
		PartDefinition cube_r7 = head.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(130, 73).addBox(-2.0F, -0.5F, -14.5F, 4.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.2394F, 4.5701F, -0.1745F, 0.0F, 0.0F));
		PartDefinition right_horn1 = head.addOrReplaceChild("right_horn1", CubeListBuilder.create().texOffs(38, 76).addBox(-1.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -2.7606F, 4.5701F, -1.5272F, -0.48F, 0.0F));
		PartDefinition cube_r8 = right_horn1.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(44, 138).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.5236F, 0.0F, -0.5236F));
		PartDefinition cube_r9 = right_horn1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(34, 104).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 3.5F, 5.5F, 0.0F, 0.0F, 0.1745F));
		PartDefinition cube_r10 = right_horn1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(34, 95).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.5F, 6.5F, 0.0F, 0.0F, -0.0436F));
		PartDefinition cube_r11 = right_horn1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 144).addBox(-0.5F, -8.0F, -0.5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, 0.0436F, 0.0F, -0.0436F));
		PartDefinition cube_r12 = right_horn1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(144, 132).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -12.5F, 3.5F, -1.0472F, 0.0F, -1.0472F));
		PartDefinition til = head.addOrReplaceChild("til", CubeListBuilder.create().texOffs(34, 113).addBox(-0.5F, -5.0F, -24.5F, 1.0F, 0.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.7394F, 3.5701F));
		PartDefinition right_horn2 = head.addOrReplaceChild("right_horn2", CubeListBuilder.create(), PartPose.offset(0.0F, 3.2394F, 3.5701F));
		PartDefinition cube_r13 = right_horn2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(76, 25).addBox(-1.2022F, -1.0F, 0.0261F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -3.0F, -5.0F, -0.0399F, 0.7409F, -0.0592F));
		PartDefinition cube_r14 = right_horn2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(92, 25).addBox(-0.2022F, 0.0F, 0.0261F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.0F, -4.0F, -1.0F, -0.1237F, -0.6387F, 0.3167F));
		PartDefinition cube_r15 = right_horn2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(140, 38).addBox(-0.2022F, -1.0F, 0.0261F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -3.5F, -3.5F, 0.0F, -0.3927F, 0.0F));
		PartDefinition cube_r16 = right_horn2.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(60, 25).addBox(-5.7978F, -1.0F, 0.0261F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -3.0F, -5.0F, -0.0399F, -0.7409F, 0.0592F));
		PartDefinition cube_r17 = right_horn2.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(88, 48).addBox(-4.7978F, 0.0F, 0.0261F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, -4.0F, -1.0F, -0.1237F, 0.6387F, -0.3167F));
		PartDefinition cube_r18 = right_horn2.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(140, 34).addBox(-5.7978F, -1.0F, 0.0261F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -3.5F, -3.5F, 0.0F, 0.3927F, 0.0F));
		PartDefinition left_horn1 = head.addOrReplaceChild("left_horn1", CubeListBuilder.create().texOffs(62, 113).addBox(-1.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -2.7606F, 4.5701F, -1.5272F, 0.48F, 0.0F));
		PartDefinition cube_r19 = left_horn1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(142, 111).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, -0.5236F, 0.0F, 0.5236F));
		PartDefinition cube_r20 = left_horn1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(20, 144).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.5F, 6.5F, 0.0F, 0.0F, 0.0436F));
		PartDefinition cube_r21 = left_horn1.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(8, 144).addBox(-1.5F, -8.0F, -0.5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, 0.0436F, 0.0F, 0.0436F));
		PartDefinition cube_r22 = left_horn1.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(144, 141).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -12.5F, 3.5F, -1.0472F, 0.0F, 1.0472F));
		PartDefinition cube_r23 = left_horn1.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(16, 144).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 3.5F, 5.5F, 0.0F, 0.0F, -0.1745F));
		PartDefinition right_arm = body_1.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(100, 63).addBox(-14.194F, -2.5089F, -2.5F, 14.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.5F, -15.5F, -5.5F, -0.981F, -0.0452F, -0.6095F));
		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down",
				CubeListBuilder.create().texOffs(110, 112).addBox(-23.5F, -7.5F, -2.0F, 12.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(76, 112).addBox(-11.0F, -3.5F, -3.0F, 11.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.194F, -0.0089F, 0.0F, 0.0F, 0.0F, -1.0036F));
		PartDefinition cube_r24 = ra_down.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(56, 45).addBox(-19.0F, 0.0F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(108, 18).addBox(-7.0F, 0.0F, -1.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.0F, 1.0F, -1.0F, 0.0F, 0.0F, -0.2182F));
		PartDefinition cube_r25 = ra_down.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(134, 54).addBox(-5.8413F, 0.0506F, -1.0F, 6.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-14.0F, 2.5F, -0.5F, 0.0F, 0.0F, -0.2182F));
		PartDefinition cube_r26 = ra_down.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(130, 18).addBox(-9.0F, -4.0F, -0.5F, 9.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-30.0F, -1.5F, -0.5F, 0.0F, 0.0F, -1.0908F));
		PartDefinition cube_r27 = ra_down.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(62, 124).addBox(-11.0F, -6.0F, -1.5F, 11.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-21.5F, -1.5F, 0.0F, 0.0F, 0.0F, -0.2618F));
		PartDefinition cube_r28 = ra_down.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(110, 122).addBox(-8.0F, -7.0F, -2.5F, 8.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, 2.5F, 0.0F, 0.0F, 0.0F, 0.5236F));
		PartDefinition left_arm = body_1.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 113).addBox(0.194F, -1.5089F, -1.5F, 13.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.5F, -15.5F, -5.5F, -0.981F, -0.0452F, 0.6095F));
		PartDefinition ra_down2 = left_arm.addOrReplaceChild("ra_down2",
				CubeListBuilder.create().texOffs(90, 124).addBox(11.5F, -7.5F, -1.0F, 7.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(52, 133).addBox(0.0F, -2.5F, -2.5F, 8.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(10.694F, 0.4911F, 0.5F, 0.0F, 0.0F, 1.0036F));
		PartDefinition cube_r29 = ra_down2.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(88, 45).addBox(-0.3412F, -0.1152F, -0.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(17.0F, 3.5F, 0.5F, 0.0F, 0.0F, -0.0873F));
		PartDefinition cube_r30 = ra_down2.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(138, 68).addBox(-0.1587F, 0.0506F, 0.0F, 6.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.5F, 2.0F, -0.5F, 0.0F, 0.0F, 0.2182F));
		PartDefinition cube_r31 = ra_down2.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(72, 45).addBox(0.0F, 0.0F, -1.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.2182F));
		PartDefinition cube_r32 = ra_down2.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(62, 143).addBox(0.0F, -4.0F, 0.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(20.0F, -3.5F, -0.5F, 0.0F, 0.0F, 1.0908F));
		PartDefinition cube_r33 = ra_down2.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(138, 98).addBox(0.0F, -6.0F, -0.5F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(16.5F, -1.5F, 0.0F, 0.0F, 0.0F, 0.2618F));
		PartDefinition cube_r34 = ra_down2.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(130, 83).addBox(-2.0F, -7.0F, -1.5F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.0F, 2.5F, 0.0F, 0.0F, 0.0F, -0.5236F));
		PartDefinition body_2 = partdefinition.addOrReplaceChild("body_2", CubeListBuilder.create().texOffs(52, 75).addBox(-5.0F, -3.0F, 11.0F, 10.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, -5.0F));
		PartDefinition cube_r35 = body_2.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 9.0F, 18.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition tail = body_2.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 50).addBox(-3.0F, -3.5F, -3.0F, 6.0F, 6.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 22.0F, 0.7418F, 0.0F, 0.0F));
		PartDefinition t1 = tail.addOrReplaceChild("t1", CubeListBuilder.create().texOffs(52, 50).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 5.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 17.0F, 0.6981F, 0.0F, 0.0F));
		PartDefinition t2 = t1.addOrReplaceChild("t2", CubeListBuilder.create().texOffs(60, 0).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 17.5F, 0.9163F, 0.0F, 0.0F));
		PartDefinition t3 = t2.addOrReplaceChild("t3", CubeListBuilder.create().texOffs(136, 122).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 19.5F, 0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r36 = t3.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(122, 134).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.0F, 12.0F, 2.1817F, 0.0F, 0.0F));
		PartDefinition cube_r37 = t3.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(78, 133).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 6.0F, 0.7854F, 0.0F, 0.0F));
		PartDefinition right_leg_1 = body_2.addOrReplaceChild("right_leg_1", CubeListBuilder.create().texOffs(138, 104).addBox(-3.0F, 16.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.5F, -7.0F));
		PartDefinition cube_r38 = right_leg_1.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(142, 0).addBox(-1.0F, -8.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 17.0F, 0.0F, -0.48F, 0.0F, 0.0F));
		PartDefinition cube_r39 = right_leg_1.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(38, 94).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 13.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 7.5F, -1.0F, 0.8727F, 0.0F, 0.0F));
		PartDefinition cube_r40 = right_leg_1.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(0, 132).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 10.5F, -1.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition right_leg_2 = body_2.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(138, 61).addBox(-3.0F, 17.0F, -3.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, 0.5F, 19.5F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r41 = right_leg_2.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(142, 10).addBox(-1.0F, -6.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 17.0F, -0.5F, -0.48F, 0.0F, 0.0F));
		PartDefinition cube_r42 = right_leg_2.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(94, 75).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 7.5F, -1.0F, 1.0036F, 0.0F, 0.0F));
		PartDefinition cube_r43 = right_leg_2.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(134, 43).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 10.5F, -1.5F, -0.6109F, 0.0F, 0.0F));
		PartDefinition left_leg_1 = body_2.addOrReplaceChild("left_leg_1", CubeListBuilder.create().texOffs(38, 87).addBox(-1.0F, 16.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.5F, -7.0F));
		PartDefinition cube_r44 = left_leg_1.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(140, 24).addBox(-2.0F, -8.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 17.0F, 0.0F, -0.48F, 0.0F, 0.0F));
		PartDefinition cube_r45 = left_leg_1.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(0, 76).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 13.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 7.5F, -1.0F, 0.8727F, 0.0F, 0.0F));
		PartDefinition cube_r46 = left_leg_1.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(28, 126).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 10.5F, -1.0F, -0.5236F, 0.0F, 0.0F));
		PartDefinition left_leg_2 = body_2.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(138, 91).addBox(-1.0F, 17.0F, -3.5F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 0.5F, 19.5F, 0.0F, -3.1416F, 0.0F));
		PartDefinition cube_r47 = left_leg_2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(52, 143).addBox(-2.0F, -6.0F, -1.0F, 3.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 17.0F, -0.5F, -0.48F, 0.0F, 0.0F));
		PartDefinition cube_r48 = left_leg_2.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(76, 94).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 7.5F, -1.0F, 1.0036F, 0.0F, 0.0F));
		PartDefinition cube_r49 = left_leg_2.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(100, 134).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 10.5F, -1.5F, -0.6109F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		body_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		body_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}