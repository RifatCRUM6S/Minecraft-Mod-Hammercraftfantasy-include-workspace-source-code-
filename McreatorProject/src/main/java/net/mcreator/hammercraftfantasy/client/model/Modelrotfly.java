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

// Made with Blockbench 5.1.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelrotfly<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modelrotfly"), "main");
	public final ModelPart tail;
	public final ModelPart body;
	public final ModelPart left_appendages;
	public final ModelPart l1;
	public final ModelPart l2;
	public final ModelPart l3;
	public final ModelPart right_appendages;
	public final ModelPart r1;
	public final ModelPart r2;
	public final ModelPart r3;
	public final ModelPart right_wing_1;
	public final ModelPart right_wing_2;
	public final ModelPart right_wing_3;
	public final ModelPart head;
	public final ModelPart left_wing_1;
	public final ModelPart left_wing_2;
	public final ModelPart left_wing_3;

	public Modelrotfly(ModelPart root) {
		this.tail = root.getChild("tail");
		this.body = root.getChild("body");
		this.left_appendages = this.body.getChild("left_appendages");
		this.l1 = this.left_appendages.getChild("l1");
		this.l2 = this.left_appendages.getChild("l2");
		this.l3 = this.left_appendages.getChild("l3");
		this.right_appendages = this.body.getChild("right_appendages");
		this.r1 = this.right_appendages.getChild("r1");
		this.r2 = this.right_appendages.getChild("r2");
		this.r3 = this.right_appendages.getChild("r3");
		this.right_wing_1 = root.getChild("right_wing_1");
		this.right_wing_2 = root.getChild("right_wing_2");
		this.right_wing_3 = root.getChild("right_wing_3");
		this.head = root.getChild("head");
		this.left_wing_1 = root.getChild("left_wing_1");
		this.left_wing_2 = root.getChild("left_wing_2");
		this.left_wing_3 = root.getChild("left_wing_3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition tail = partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, 7.0F, -2.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -3.0F));
		PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(88, 92).addBox(0.0F, 0.0F, -1.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -1.3022F, 0.2497F, -0.7368F));
		PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 90).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 2.0F, -0.6545F, 0.0F, 0.0F));
		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 30).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 17.0F, 3.0F, -0.2618F, 0.0F, 0.0F));
		PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(24, 66).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 2.0F, 0.1745F, 0.0F, 0.0F));
		PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 3.0F, 0.0873F, 0.0F, 0.0F));
		PartDefinition cube_r6 = tail.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 17).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 14.0F, 3.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -8.0F, -2.0F, 0.2182F, 0.0F, 0.0F));
		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(48, 66).addBox(-3.0F, -7.0F, -4.0F, 6.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.5236F, 0.0F, 0.0F));
		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, 0.3491F, 0.0F, 0.0F));
		PartDefinition left_appendages = body.addOrReplaceChild("left_appendages", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -6.0F));
		PartDefinition l1 = left_appendages.addOrReplaceChild("l1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2182F, -0.3491F, -0.3491F));
		PartDefinition cube_r9 = l1.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(90, 29).addBox(3.0F, -1.0F, 1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, -5.0F, 1.5845F, 0.3475F, 1.8154F));
		PartDefinition cube_r10 = l1.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(14, 82).addBox(0.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -5.0F, 1.589F, 0.7838F, 1.8235F));
		PartDefinition cube_r11 = l1.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(88, 86).addBox(-1.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -5.0F, 1.585F, -0.4379F, 1.8047F));
		PartDefinition cube_r12 = l1.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(88, 84).addBox(-1.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5171F, 0.654F, 1.8966F));
		PartDefinition l2 = left_appendages.addOrReplaceChild("l2", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 0.0F, 1.0F, -0.2436F, -0.1298F, -0.099F));
		PartDefinition cube_r13 = l2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(42, 92).addBox(-1.0F, -7.2376F, 0.1414F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 12.0F, -3.0F, -2.6569F, 0.0762F, -0.2628F));
		PartDefinition cube_r14 = l2.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(72, 65).addBox(-1.0F, -0.2386F, -0.8149F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 4.0F, -3.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r15 = l2.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(94, 6).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, -0.7418F, 0.0F, 0.0F));
		PartDefinition l3 = left_appendages.addOrReplaceChild("l3", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 1.0F, 1.0F, 0.0195F, -0.3916F, -0.1041F));
		PartDefinition cube_r16 = l3.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(84, 92).addBox(-1.0F, -7.2376F, 0.1414F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 12.0F, -3.0F, -2.6569F, 0.0762F, -0.2628F));
		PartDefinition cube_r17 = l3.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(48, 75).addBox(-1.0F, -0.2386F, -0.8149F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 4.0F, -3.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r18 = l3.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(94, 12).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, -0.7418F, 0.0F, 0.0F));
		PartDefinition right_appendages = body.addOrReplaceChild("right_appendages", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition r1 = right_appendages.addOrReplaceChild("r1", CubeListBuilder.create(), PartPose.offsetAndRotation(-4.0F, 0.0F, -6.0F, -0.2182F, 0.3491F, 0.3491F));
		PartDefinition cube_r19 = r1.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(90, 28).addBox(-9.0F, -1.0F, 1.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, -5.0F, 1.5845F, -0.3475F, -1.8154F));
		PartDefinition cube_r20 = r1.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(54, 30).addBox(-4.0F, -1.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -5.0F, 1.589F, -0.7838F, -1.8235F));
		PartDefinition cube_r21 = r1.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(76, 28).addBox(-5.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -5.0F, 1.585F, 0.4379F, -1.8047F));
		PartDefinition cube_r22 = r1.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(40, 30).addBox(-5.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5171F, -0.654F, -1.8966F));
		PartDefinition r2 = right_appendages.addOrReplaceChild("r2", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 0.0F, -5.0F, -0.2436F, 0.1298F, 0.099F));
		PartDefinition cube_r23 = r2.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(42, 76).addBox(0.0F, -7.2376F, 0.1414F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 12.0F, -3.0F, -2.6569F, -0.0762F, 0.2628F));
		PartDefinition cube_r24 = r2.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(68, 43).addBox(0.0F, -0.2386F, -0.8149F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 4.0F, -3.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r25 = r2.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(92, 92).addBox(0.0F, -2.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 2.0F, -1.0F, -0.7418F, 0.0F, 0.0F));
		PartDefinition r3 = right_appendages.addOrReplaceChild("r3", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 1.0F, -5.0F, 0.0195F, 0.3916F, 0.1041F));
		PartDefinition cube_r26 = r3.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(38, 92).addBox(0.0F, -7.2376F, 0.1414F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 12.0F, -3.0F, -2.6569F, -0.0762F, 0.2628F));
		PartDefinition cube_r27 = r3.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(72, 54).addBox(0.0F, -0.2386F, -0.8149F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 4.0F, -3.0F, -1.5708F, 0.0F, 0.0F));
		PartDefinition cube_r28 = r3.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(94, 0).addBox(0.0F, -2.0F, -1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, -1.0F, -0.7418F, 0.0F, 0.0F));
		PartDefinition right_wing_1 = partdefinition.addOrReplaceChild("right_wing_1",
				CubeListBuilder.create().texOffs(90, 43).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(76, 24).addBox(-10.0F, -2.0F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(40, 0)
						.addBox(-37.0F, -6.0F, 0.0F, 18.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 48).addBox(-19.0F, -5.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(18, 48)
						.addBox(-44.0F, -5.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -14.0F, -4.0F, 0.2618F, 0.0F, 0.2618F));
		PartDefinition right_wing_2 = partdefinition.addOrReplaceChild("right_wing_2",
				CubeListBuilder.create().texOffs(90, 48).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 78).addBox(-11.0F, -3.0F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(40, 10)
						.addBox(-38.0F, -7.0F, 0.0F, 18.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(76, 0).addBox(-20.0F, -6.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 82)
						.addBox(-45.0F, -6.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, -11.0F, -2.0F));
		PartDefinition right_wing_3 = partdefinition.addOrReplaceChild("right_wing_3",
				CubeListBuilder.create().texOffs(8, 92).addBox(-2.0F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(14, 84).addBox(-11.0F, -3.0F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(40, 20)
						.addBox(-38.0F, -7.0F, 0.0F, 18.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(76, 8).addBox(-20.0F, -6.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(32, 84)
						.addBox(-45.0F, -6.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -9.0F, 0.0F, -0.2182F, 0.0F, -0.2618F));
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -13.0F, -10.0F, 0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r29 = head.addOrReplaceChild("cube_r29",
				CubeListBuilder.create().texOffs(92, 88).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(18, 78).addBox(-6.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, 2.3827F, -3.0761F, 0.48F, 0.0F, 0.0F));
		PartDefinition cube_r30 = head.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(0, 66).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.3827F, 1.9239F, 0.4363F, 0.0F, 0.0F));
		PartDefinition left_wing_1 = partdefinition.addOrReplaceChild("left_wing_1",
				CubeListBuilder.create().texOffs(90, 43).mirror().addBox(-2.0F, -1.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(76, 24).mirror().addBox(1.0F, -2.0F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false).texOffs(40, 0).mirror().addBox(19.0F, -6.0F, 0.0F, 18.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 48).mirror().addBox(10.0F, -5.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false).texOffs(18, 48).mirror().addBox(37.0F, -5.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(3.0F, -14.0F, -4.0F, 0.2618F, 0.0F, -0.2618F));
		PartDefinition left_wing_2 = partdefinition.addOrReplaceChild("left_wing_2",
				CubeListBuilder.create().texOffs(90, 48).mirror().addBox(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(0, 78).mirror().addBox(2.0F, -3.0F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false).texOffs(40, 10).mirror().addBox(20.0F, -7.0F, 0.0F, 18.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(76, 0).mirror().addBox(11.0F, -6.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false).texOffs(0, 82).mirror().addBox(38.0F, -6.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(2.0F, -11.0F, -2.0F));
		PartDefinition left_wing_3 = partdefinition.addOrReplaceChild("left_wing_3",
				CubeListBuilder.create().texOffs(8, 92).mirror().addBox(-1.0F, -2.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(14, 84).mirror().addBox(2.0F, -3.0F, 0.0F, 9.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false).texOffs(40, 20).mirror().addBox(20.0F, -7.0F, 0.0F, 18.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(76, 8).mirror().addBox(11.0F, -6.0F, 0.0F, 9.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
						.mirror(false).texOffs(32, 84).mirror().addBox(38.0F, -6.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(2.0F, -9.0F, 0.0F, -0.2182F, 0.0F, 0.2618F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		tail.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_wing_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_wing_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		right_wing_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_wing_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_wing_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		left_wing_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}