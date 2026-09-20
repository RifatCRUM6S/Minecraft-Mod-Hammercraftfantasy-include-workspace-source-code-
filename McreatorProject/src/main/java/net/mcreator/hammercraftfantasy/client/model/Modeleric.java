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
public class Modeleric<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("hammercraftfantasy", "modeleric"), "main");
	public final ModelPart all;
	public final ModelPart frisbee;
	public final ModelPart person;
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart left_arm;
	public final ModelPart right_arm;
	public final ModelPart left_leg;
	public final ModelPart right_leg;

	public Modeleric(ModelPart root) {
		this.all = root.getChild("all");
		this.frisbee = this.all.getChild("frisbee");
		this.person = this.all.getChild("person");
		this.body = this.person.getChild("body");
		this.head = this.body.getChild("head");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.left_leg = this.person.getChild("left_leg");
		this.right_leg = this.person.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition frisbee = all.addOrReplaceChild("frisbee",
				CubeListBuilder.create().texOffs(0, 0).addBox(-12.0F, -2.0F, -12.0F, 24.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)).texOffs(66, 75).addBox(12.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 76)
						.addBox(-14.0F, -4.0F, -9.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(44, 76).addBox(12.0F, -4.0F, -9.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(74, 75)
						.addBox(-14.0F, -4.0F, 7.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 76).addBox(12.0F, -4.0F, 7.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(36, 75)
						.addBox(-1.0F, -4.0F, -14.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(52, 76).addBox(-1.0F, -4.0F, 12.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 58)
						.addBox(7.0F, -4.0F, -14.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(60, 82).addBox(7.0F, -4.0F, 12.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 33)
						.addBox(-9.0F, -4.0F, -14.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(36, 82).addBox(-9.0F, -4.0F, 12.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(80, 26)
						.addBox(-14.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition person = all.addOrReplaceChild("person", CubeListBuilder.create(), PartPose.offset(0.0F, -13.5F, 0.0F));
		PartDefinition body = person.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(26, 42).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 42).addBox(-4.0F, -12.0F, -2.5F, 8.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(32, 26).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, -12.0F, 0.0F));
		PartDefinition left_arm = body.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(64, 26).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(50, 42).addBox(-1.0F, -2.5F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, -10.0F, 0.0F));
		PartDefinition right_arm = body.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(66, 59).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(26, 58).addBox(-4.0F, -2.5F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, -10.0F, 0.0F));
		PartDefinition left_leg = person.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(70, 42).addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 59).addBox(-1.9F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.9F, 0.0F, 0.0F, 0.0437F, 0.0436F, -0.0417F));
		PartDefinition right_leg = person.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(20, 75).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(46, 59).addBox(-3.1F, 0.0F, -2.5F, 5.0F, 12.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0436F));
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