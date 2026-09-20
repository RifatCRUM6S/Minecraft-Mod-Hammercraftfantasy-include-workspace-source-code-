// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelPlaguebearer<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "plaguebearer"), "main");
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart right_arm;
	private final ModelPart left_arm;

	public ModelPlaguebearer(ModelPart root) {
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
		this.body = root.getChild("body");
		this.head = root.getChild("head");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(32, 50)
						.addBox(-2.0F, 0.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(32, 36)
						.addBox(-2.0F, 6.0F, -3.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, 10.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(0, 52)
						.addBox(-3.0F, -1.0F, -2.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(44, 0)
						.addBox(-3.0F, 5.0F, -3.0F, 5.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 11.0F, 0.0F));

		PartDefinition cube_r1 = left_leg.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(72, 58).addBox(-1.0F, -2.0F, -2.0F, 1.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -15.0F, -1.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-6.0F, -7.0F, -5.0F, 12.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 18)
						.addBox(-5.0F, -17.0F, -4.0F, 10.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(36, 18)
						.addBox(-7.0F, -20.0F, 1.0F, 8.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(44, 14)
						.addBox(-12.0F, -15.0F, 3.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 50)
						.addBox(2.0F, -5.0F, 5.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 9.0F, 0.0F));

		PartDefinition cube_r2 = body
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(72, 62).addBox(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(6.0F, 0.0F, 1.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(72, 54).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -16.0F, 9.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r4 = body
				.addOrReplaceChild("cube_r4",
						CubeListBuilder.create().texOffs(72, 66).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-3.0F, 0.0F, -6.0F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r5 = body
				.addOrReplaceChild("cube_r5",
						CubeListBuilder.create().texOffs(68, 46).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 3.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(4.0F, -8.0F, -5.0F, 0.0F, 0.0F, -0.48F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(58, 14).addBox(-6.0F, -1.0F, 0.0F, 7.0F, 1.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-15.0F, -14.0F, 1.0F, -1.0007F, -0.3352F, 1.05F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(54, 46).addBox(-4.0F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0F, -13.0F, 4.0F, 0.0F, -0.6109F, 0.0F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(28, 52).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 7.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.0F, -30.0F, 2.0F, -0.7279F, 0.1457F, 0.0226F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(60, 71).addBox(0.0F, -7.0F, 1.0F, 1.0F, 7.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-14.0F, -26.0F, 3.0F, 0.9356F, 0.0879F, -1.3102F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(12, 63).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-9.0F, -24.0F, 2.0F, -0.0744F, -0.158F, -1.1286F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(20, 52).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-9.0F, -24.0F, 2.0F, -0.1719F, -0.0302F, -0.1719F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(52, 61).addBox(-1.0F, -7.0F, -1.0F, 3.0F, 7.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -18.0F, 2.0F, -0.1515F, -0.0869F, -0.517F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 36).addBox(
				-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -3.0F));

		PartDefinition cube_r13 = head.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(66, 16).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -3.0F, -2.0F, 0.4821F, 0.2129F, -0.3838F));

		PartDefinition cube_r14 = head.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(66, 0).addBox(0.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -14.0F, 0.0F, -0.5236F, 0.0F, 0.6981F));

		PartDefinition cube_r15 = head.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(64, 61).addBox(0.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -7.0F, 0.0F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r16 = head
				.addOrReplaceChild("cube_r16",
						CubeListBuilder.create().texOffs(0, 63).addBox(-1.0F, -7.0F, -2.0F, 3.0F, 7.0F, 3.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(2.0F, -3.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(20, 61).addBox(-3.0F, -1.0F, -3.0F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -7.0F, -1.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r17 = right_arm.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(66, 28)
						.addBox(-3.0F, 5.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(66, 23)
						.addBox(0.0F, 5.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(66, 9)
						.addBox(-1.0F, 5.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(52, 50)
						.addBox(-3.0F, -1.0F, -4.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 9.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(36, 61).addBox(-1.0F, -1.0F, -3.0F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -7.0F, -1.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r18 = left_arm.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(12, 72)
						.addBox(1.0F, 5.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(52, 71)
						.addBox(-2.0F, 5.0F, -4.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(64, 70)
						.addBox(-1.0F, 5.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(54, 35)
						.addBox(-2.0F, -1.0F, -4.0F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 9.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
	}
}