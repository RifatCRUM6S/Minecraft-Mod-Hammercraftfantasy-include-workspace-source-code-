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
public class Modelflamers<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modelflamers"), "main");
	public final ModelPart all;
	public final ModelPart body;
	public final ModelPart tail;
	public final ModelPart tail2;
	public final ModelPart tail3;
	public final ModelPart head;
	public final ModelPart right_arm;
	public final ModelPart ra_down;
	public final ModelPart left_arm;
	public final ModelPart la_down;

	public Modelflamers(ModelPart root) {
		this.all = root.getChild("all");
		this.body = this.all.getChild("body");
		this.tail = this.body.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.head = this.body.getChild("head");
		this.right_arm = this.all.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.left_arm = this.all.getChild("left_arm");
		this.la_down = this.left_arm.getChild("la_down");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, -6.5F, 1.0F));
		PartDefinition body = all.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 38).addBox(-4.5F, -9.0F, -5.0F, 9.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(36, 19).addBox(-4.0F, -2.0F, -3.0F, 8.0F, 17.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 19).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.5F, 0.0F));
		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 0.0F, -5.0F, 12.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 42).addBox(-4.0F, -5.5F, -4.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(44, 0)
				.addBox(0.0F, -6.5F, -3.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(0, 55).addBox(-1.5F, -7.5F, -0.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -2.0F));
		PartDefinition right_arm = all.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(52, 56).addBox(-2.0F, -1.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, -8.0F, -2.5F));
		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down", CubeListBuilder.create().texOffs(20, 56).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 7.0F, 1.5F));
		PartDefinition cube_r1 = ra_down.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(58, 10).addBox(-3.0F, 0.0F, 0.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 7.0F, -1.5F, 0.3491F, 0.0F, 0.0F));
		PartDefinition cube_r2 = ra_down.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(44, 10).addBox(-3.0F, 0.0F, -2.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 7.0F, -1.5F, -0.3491F, 0.0F, 0.0F));
		PartDefinition left_arm = all.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(62, 42).addBox(-1.0F, -1.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -8.0F, -2.5F));
		PartDefinition la_down = left_arm.addOrReplaceChild("la_down", CubeListBuilder.create().texOffs(36, 56).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 7.0F, 1.5F));
		PartDefinition cube_r3 = la_down.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(64, 16).addBox(-2.0F, 0.0F, 0.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 7.0F, -1.5F, 0.3491F, 0.0F, 0.0F));
		PartDefinition cube_r4 = la_down.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(64, 0).addBox(-2.0F, 0.0F, -2.0F, 5.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 7.0F, -1.5F, -0.3491F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}
}