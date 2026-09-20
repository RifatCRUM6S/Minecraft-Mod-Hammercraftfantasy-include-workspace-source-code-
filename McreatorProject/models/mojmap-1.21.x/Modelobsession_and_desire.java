// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelobsession_and_desire<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "obsession_and_desire"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	private final ModelPart right_boots;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart left_boots;
	private final ModelPart right_arm;

	public Modelobsession_and_desire(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
		this.right_boots = this.right_leg.getChild("right_boots");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.left_boots = this.left_leg.getChild("left_boots");
		this.right_arm = root.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 30)
						.addBox(-4.0F, -10.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(70, 68)
						.addBox(-3.0F, -3.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 56)
						.addBox(1.0F, -3.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(26, 68)
						.addBox(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 25)
						.addBox(4.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 41)
						.addBox(-6.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(60, 21)
						.addBox(-4.0F, -6.0F, -5.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 59)
						.addBox(-2.0F, -11.0F, -5.0F, 4.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 13)
						.addBox(-1.0F, -15.0F, -6.0F, 2.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(46, 68)
						.addBox(-2.0F, -11.0F, 4.0F, 4.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(60, 68)
						.addBox(-1.0F, -13.0F, 7.0F, 2.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(74, 72).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -3.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(70, 72).addBox(0.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -3.0F, -5.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(66, 51).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-5.0F, 0.0F, -4.0F, 10.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(56, 25)
						.addBox(-4.0F, 10.0F, -4.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(12, 69).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.8889F, -0.9338F, -0.7805F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(0, 69).addBox(0.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, -3.0F, 0.8889F, 0.9338F, 0.7805F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(50, 59)
						.addBox(-1.0F, 8.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(30, 13)
						.addBox(-1.0F, -3.0F, -4.0F, 7.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(60, 12)
						.addBox(-1.0F, 3.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leggings = right_leg.addOrReplaceChild("right_leggings",
				CubeListBuilder.create().texOffs(22, 42)
						.addBox(-2.9F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(72, 60)
						.addBox(-2.0F, 3.0F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_boots = right_leg.addOrReplaceChild("right_boots", CubeListBuilder.create().texOffs(56, 33)
				.addBox(-2.9F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_leggings = left_leg.addOrReplaceChild("left_leggings",
				CubeListBuilder.create().texOffs(44, 42)
						.addBox(-2.1F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(72, 64)
						.addBox(-1.0F, 3.0F, -4.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_boots = left_leg.addOrReplaceChild("left_boots", CubeListBuilder.create().texOffs(26, 59)
				.addBox(-2.1F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(64, 0)
						.addBox(-4.0F, 8.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(34, 0)
						.addBox(-6.0F, -3.0F, -4.0F, 7.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(66, 42)
						.addBox(-4.0F, 3.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_boots.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.right_arm.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.right_leggings.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.right_boots.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.left_leggings.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
	}
}