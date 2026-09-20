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
public class Modeltamurkhan<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modeltamurkhan"), "main");
	public final ModelPart all;
	public final ModelPart body;
	public final ModelPart yopka;
	public final ModelPart right_arm;
	public final ModelPart ra_down;
	public final ModelPart left_arm;
	public final ModelPart la_down;
	public final ModelPart axe;
	public final ModelPart head;
	public final ModelPart right_leg;
	public final ModelPart rl_down;
	public final ModelPart left_leg;
	public final ModelPart rl_down2;

	public Modeltamurkhan(ModelPart root) {
		this.all = root.getChild("all");
		this.body = this.all.getChild("body");
		this.yopka = this.body.getChild("yopka");
		this.right_arm = this.body.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.left_arm = this.body.getChild("left_arm");
		this.la_down = this.left_arm.getChild("la_down");
		this.axe = this.la_down.getChild("axe");
		this.head = this.body.getChild("head");
		this.right_leg = this.all.getChild("right_leg");
		this.rl_down = this.right_leg.getChild("rl_down");
		this.left_leg = this.all.getChild("left_leg");
		this.rl_down2 = this.left_leg.getChild("rl_down2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));
		PartDefinition body = all.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 68).addBox(-13.0F, -13.5F, -10.5F, 26.0F, 13.0F, 20.0F, new CubeDeformation(0.0F)).texOffs(128, 116).addBox(-9.0F, -15.5F, -12.5F, 18.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(128, 135)
						.addBox(-13.0F, -5.5F, -11.5F, 26.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(136, 0).addBox(-13.0F, -5.5F, 9.5F, 26.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 101)
						.addBox(13.0F, -5.5F, -10.5F, 1.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)).texOffs(86, 116).addBox(-14.0F, -5.5F, -10.5F, 1.0F, 4.0F, 20.0F, new CubeDeformation(0.0F)).texOffs(92, 68)
						.addBox(-12.0F, -2.0F, -9.5F, 24.0F, 3.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(92, 89).addBox(-11.0F, -23.5F, -7.5F, 22.0F, 11.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(168, 93).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -3.5F, -12.5F, -0.2657F, 0.1685F, -0.0456F));
		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(30, 151).addBox(0.0F, -2.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -4.5F, -17.0F, -0.9702F, -0.4134F, 0.5814F));
		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(168, 99).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -3.5F, -12.5F, -0.2657F, -0.1685F, 0.0456F));
		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(30, 146).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -8.5F, -12.5F, -0.2618F, 0.0F, 0.0F));
		PartDefinition yopka = body.addOrReplaceChild("yopka", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -5.0F));
		PartDefinition cube_r5 = yopka.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(136, 5).addBox(-12.0F, 0.0F, 1.0F, 24.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.5F, -1.5F, -0.2618F, 0.0F, 0.0F));
		PartDefinition right_arm = body
				.addOrReplaceChild(
						"right_arm", CubeListBuilder.create().texOffs(144, 140).addBox(-7.5981F, -1.7321F, -4.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 123)
								.addBox(-10.0981F, -3.7321F, -6.0F, 8.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(136, 62).addBox(-16.0981F, 0.2679F, -1.5F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-10.5F, -22.0F, -0.5F, 0.0F, 0.0F, 0.5236F));
		PartDefinition cube_r6 = right_arm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(52, 168).addBox(-6.0F, -1.5F, -1.5F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-15.5981F, 2.2679F, 0.5F, 0.0F, 0.0F, 0.2618F));
		PartDefinition cube_r7 = right_arm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(168, 109).addBox(-4.0F, -1.5F, -0.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.0981F, 1.2679F, 3.0F, 0.0F, 0.0F, 0.0873F));
		PartDefinition cube_r8 = right_arm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(168, 105).addBox(-4.0F, -1.5F, -0.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.0981F, 1.2679F, -4.0F, 0.0F, 0.0F, 0.1309F));
		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down", CubeListBuilder.create(), PartPose.offset(-3.5981F, 10.2679F, 0.0F));
		PartDefinition cube_r9 = ra_down.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(80, 140).addBox(-4.0F, 0.0F, -8.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, 4.0F, -0.4363F, 0.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 145).addBox(-0.4019F, -1.7321F, -4.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(10.5F, -22.0F, -0.5F, 0.0F, 0.0F, -0.5236F));
		PartDefinition cube_r10 = left_arm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(168, 113).addBox(0.0F, -1.5F, -0.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.5981F, 5.2679F, -1.5F, 0.0076F, -0.043F, -0.262F));
		PartDefinition cube_r11 = left_arm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(154, 62).addBox(0.0F, -1.5F, -0.5F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.5981F, 1.7679F, 1.0F, 0.0F, 0.0F, -0.2182F));
		PartDefinition cube_r12 = left_arm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(168, 89).addBox(0.0F, -1.5F, -0.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.5981F, 1.2679F, -2.5F, 0.0F, 0.0F, -0.3054F));
		PartDefinition la_down = left_arm.addOrReplaceChild("la_down", CubeListBuilder.create(), PartPose.offset(3.5981F, 10.2679F, 0.0F));
		PartDefinition cube_r13 = la_down.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(112, 140).addBox(-4.0F, 0.0F, -8.0F, 8.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, 4.0F, -0.4363F, 0.0F, 0.0F));
		PartDefinition axe = la_down.addOrReplaceChild("axe",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.9015F, -41.0772F, 2.0F, 2.0F, 66.0F, new CubeDeformation(0.0F)).texOffs(0, 166).addBox(-0.5F, 1.0985F, -39.0772F, 1.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(80, 130)
						.addBox(-0.5F, -4.4015F, -33.0772F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(86, 101).addBox(-0.5F, -5.4015F, -38.5772F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 11.5F, -5.0F, -0.3927F, 0.0F, 0.0F));
		PartDefinition cube_r14 = axe.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(18, 166).addBox(-1.0F, 3.0F, 0.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 9.5985F, -29.5772F, 0.9599F, 0.0F, 0.0F));
		PartDefinition cube_r15 = axe.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 146).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 12.0985F, -39.0772F, 0.3054F, 0.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 101).addBox(-5.5F, -10.0F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(40, 125).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -24.0F, -1.0F));
		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(80, 125).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -9.5F, -2.5F, 0.2956F, 0.0779F, -0.2502F));
		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(86, 108).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.5F, -12.0F, -1.5F, 0.845F, 0.2327F, 0.5221F));
		PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(72, 155).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -9.0F, -2.0F, 0.0F, 0.5672F, -0.8727F));
		PartDefinition head_r4 = head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(72, 145).addBox(-0.5F, -8.0F, -0.5F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -16.0F, -3.5F, 0.0F, 0.0F, -0.1745F));
		PartDefinition head_r5 = head.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(40, 168).addBox(-1.5F, -8.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, -9.0F, -3.0F, 0.0F, 0.0F, 0.2618F));
		PartDefinition right_leg = all.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-9.5F, -0.5F, 2.0F));
		PartDefinition cube_r16 = right_leg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(136, 15).addBox(-4.0F, -10.0F, -1.0F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.0F, -7.5F, -0.3927F, 0.0F, 0.0F));
		PartDefinition rl_down = right_leg.addOrReplaceChild("rl_down", CubeListBuilder.create().texOffs(136, 51).addBox(-4.0F, 8.5F, -5.0F, 10.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -2.0F));
		PartDefinition cube_r17 = rl_down.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(72, 163).addBox(-3.5F, -10.0F, -7.0F, 9.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.5F, 4.0F, 0.3054F, 0.0F, 0.0F));
		PartDefinition left_leg = all.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(9.5F, -0.5F, 2.0F));
		PartDefinition cube_r18 = left_leg.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(136, 33).addBox(-6.0F, -10.0F, -1.0F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.0F, -7.5F, -0.3927F, 0.0F, 0.0F));
		PartDefinition rl_down2 = left_leg.addOrReplaceChild("rl_down2", CubeListBuilder.create().texOffs(136, 163).addBox(-6.0F, 8.5F, -5.0F, 10.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -2.0F));
		PartDefinition cube_r19 = rl_down2.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(104, 163).addBox(-5.5F, -10.0F, -7.0F, 9.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.5F, 4.0F, 0.3054F, 0.0F, 0.0F));
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