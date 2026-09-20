// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelsilverguard<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "silverguard"), "main");
	private final ModelPart body;
	private final ModelPart cloak;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart head;
	private final ModelPart hair;
	private final ModelPart right_arm;
	private final ModelPart ra_down;
	private final ModelPart shield;
	private final ModelPart left_arm;
	private final ModelPart la_down;
	private final ModelPart sword;

	public Modelsilverguard(ModelPart root) {
		this.body = root.getChild("body");
		this.cloak = this.body.getChild("cloak");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
		this.head = root.getChild("head");
		this.hair = this.head.getChild("hair");
		this.right_arm = root.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.shield = this.ra_down.getChild("shield");
		this.left_arm = root.getChild("left_arm");
		this.la_down = this.left_arm.getChild("la_down");
		this.sword = this.la_down.getChild("sword");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(68, 18)
						.addBox(-5.0F, -7.0F, -3.0F, 10.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(58, 48)
						.addBox(-5.5F, -14.0F, -3.5F, 11.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 10.5F, 0.0F));

		PartDefinition cloak = body.addOrReplaceChild("cloak", CubeListBuilder.create(),
				PartPose.offset(0.0F, -13.0F, 3.0F));

		PartDefinition cube_r1 = cloak
				.addOrReplaceChild("cube_r1",
						CubeListBuilder.create().texOffs(58, 62).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 20.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, -0.5F, 0.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(72, 83)
				.addBox(-2.0F, 0.0F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, 10.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create()
				.texOffs(52, 83).addBox(-3.0F, 0.0F, -2.5F, 5.0F, 14.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 10.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 56).addBox(
				-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair",
				CubeListBuilder.create().texOffs(34, 0)
						.addBox(-4.5F, -8.5F, -4.5F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 94)
						.addBox(-1.0F, -9.5F, -4.5F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 72)
						.addBox(-0.5F, -12.5F, -3.5F, 1.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(70, 0)
						.addBox(-7.0F, -2.5F, -3.5F, 8.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(92, 76)
						.addBox(-5.0F, 1.5F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(34, 18)
						.addBox(-8.0F, 2.0F, -4.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -2.5F, 0.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down",
				CubeListBuilder.create().texOffs(84, 62).addBox(-3.0F, 0.0F, -4.5F, 5.0F, 9.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 7.5F, 2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition shield = ra_down.addOrReplaceChild("shield",
				CubeListBuilder.create().texOffs(0, 28)
						.addBox(-6.0F, 9.5F, -7.0F, 2.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-5.0F, 10.5F, -8.0F, 1.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(32, 48)
						.addBox(-5.0F, 8.5F, -6.0F, 1.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.5F, -11.5F, -2.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(22, 76)
						.addBox(-1.0F, -2.5F, -3.5F, 8.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(92, 88)
						.addBox(0.0F, 1.5F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(68, 31)
						.addBox(-1.0F, 2.0F, -4.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -2.5F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition la_down = left_arm
				.addOrReplaceChild("la_down",
						CubeListBuilder.create().texOffs(22, 88).addBox(-2.0F, 0.0F, -4.5F, 5.0F, 9.0F, 5.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(2.0F, 7.5F, 2.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition sword = la_down.addOrReplaceChild("sword",
				CubeListBuilder.create().texOffs(94, 40)
						.addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(68, 40)
						.addBox(0.0F, -0.5F, -26.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(32, 28)
						.addBox(0.0F, -1.0F, -23.0F, 0.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(70, 12)
						.addBox(-0.5F, -2.5F, -5.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 8.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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