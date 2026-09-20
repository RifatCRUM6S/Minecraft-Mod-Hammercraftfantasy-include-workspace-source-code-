// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelRage_and_fury<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "rage_and_fury"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	private final ModelPart right_boots;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart left_boots;

	public ModelRage_and_fury(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
		this.right_boots = this.right_leg.getChild("right_boots");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.left_boots = this.left_leg.getChild("left_boots");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 15)
						.addBox(-4.0F, -10.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(54, 20)
						.addBox(-1.0F, -6.0F, -6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 15)
						.addBox(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 39)
						.addBox(4.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(22, 39)
						.addBox(-6.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(42, 64)
						.addBox(-10.0F, -17.0F, -1.0F, 3.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 73)
						.addBox(-9.0F, -9.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(66, 44)
						.addBox(7.0F, -17.0F, -1.0F, 3.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(52, 74)
						.addBox(6.0F, -9.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(36, 12)
						.addBox(-4.0F, -6.0F, -5.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(66, 34).addBox(-2.0F, -7.0F, -1.0F, 5.0F, 8.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(7.0F, -16.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(52, 64).addBox(-3.0F, -7.0F, -1.0F, 5.0F, 8.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-7.0F, -16.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(66, 66).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 3.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -3.0F, -5.0F, 0.0F, -1.1345F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(66, 58).addBox(0.0F, -1.0F, -5.0F, 1.0F, 3.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -3.0F, -5.0F, 0.0F, 1.1345F, 0.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(64, 29).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-5.0F, 0.0F, -4.0F, 10.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(36, 0)
						.addBox(-4.0F, 5.0F, -3.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(12, 73).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.8889F, -0.9338F, -0.7805F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(0, 73).addBox(0.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, -3.0F, 0.8889F, 0.9338F, 0.7805F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(0, 26)
						.addBox(-7.0F, -4.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(54, 12)
						.addBox(-4.0F, 1.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(64, 20)
						.addBox(-3.0F, 8.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(22, 64)
						.addBox(-1.0F, 8.0F, -3.0F, 4.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(32, 26)
						.addBox(-1.0F, -4.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(22, 56)
						.addBox(-1.0F, 1.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leggings = right_leg.addOrReplaceChild("right_leggings",
				CubeListBuilder.create().texOffs(44, 39)
						.addBox(-2.9F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(34, 73)
						.addBox(-2.0F, 2.0F, -4.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_boots = right_leg.addOrReplaceChild("right_boots", CubeListBuilder.create().texOffs(44, 56)
				.addBox(-2.9F, 10.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_leggings = left_leg.addOrReplaceChild("left_leggings",
				CubeListBuilder.create().texOffs(0, 56)
						.addBox(-2.1F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(62, 74)
						.addBox(-1.0F, 2.0F, -4.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_boots = left_leg.addOrReplaceChild("left_boots", CubeListBuilder.create().texOffs(64, 0)
				.addBox(-2.1F, 10.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_boots.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.right_leggings.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.right_boots.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.left_leggings.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}