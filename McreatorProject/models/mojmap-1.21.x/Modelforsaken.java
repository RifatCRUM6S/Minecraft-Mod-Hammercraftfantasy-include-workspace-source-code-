// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelforsaken<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "forsaken"), "main");
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart ra_down;
	private final ModelPart head;
	private final ModelPart hron1;
	private final ModelPart hron2;

	public Modelforsaken(ModelPart root) {
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.head = root.getChild("head");
		this.hron1 = this.head.getChild("hron1");
		this.hron2 = this.head.getChild("hron2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(38, 0)
				.addBox(-3.0F, 6.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.5F, 11.0F, -0.5F));

		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(52, 44).addBox(-2.0F, -7.0F, -1.0F, 4.0F, 8.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -0.1304F, 0.0114F, 0.0865F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(3.0F, 11.0F, 0.0F));

		PartDefinition cube_r2 = left_leg.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(0, 44).addBox(-3.0F, 0.0F, -1.0F, 5.0F, 13.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, -0.0436F, 0.0F, -0.0436F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-6.0F, -9.0F, -3.5F, 12.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(32, 15)
						.addBox(-5.0F, -1.0F, -2.5F, 10.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 7.0F, 0.5F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 31)
				.addBox(-3.0F, -3.0F, -4.0F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(8.0F, -0.5F, 0.0F));

		PartDefinition cube_r3 = left_arm.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(20, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1739F, -0.0151F, -0.0859F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 31)
				.addBox(-4.0F, -3.0F, -4.0F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-8.0F, -0.5F, 0.0F));

		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down",
				CubeListBuilder.create().texOffs(56, 25).addBox(-2.1F, 5.5263F, 1.6231F, 2.0F, 7.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -0.5F, -0.5F, -0.7514F, 0.2889F, 0.0437F));

		PartDefinition cube_r4 = ra_down.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(36, 44).addBox(-2.0F, -7.9772F, -2.5229F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.6F, 5.8612F, 4.1052F, 0.5637F, -0.4073F, 0.0412F));

		PartDefinition cube_r5 = ra_down.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(52, 25).addBox(0.0F, -0.0038F, -0.9128F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.6F, 12.0263F, 6.1231F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r6 = ra_down.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(44, 57).addBox(0.0F, -1.0F, -2.0F, 1.0F, 5.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.6F, 14.0263F, 1.1231F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r7 = ra_down.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(52, 56).addBox(0.0F, -2.0F, -3.0F, 1.0F, 6.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.6F, 12.0263F, 3.6231F, -0.5236F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 15).addBox(
				-4.0F, -5.0F, -5.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition hron1 = head.addOrReplaceChild("hron1",
				CubeListBuilder.create().texOffs(56, 36).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 5.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -4.0F, -3.5F, -0.1705F, -0.0376F, -0.215F));

		PartDefinition cube_r8 = hron1.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(44, 25).addBox(-1.0F, -3.5F, -1.0F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r9 = hron1.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(58, 0).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, 1.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition hron2 = head.addOrReplaceChild("hron2",
				CubeListBuilder.create().texOffs(32, 25).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -4.0F, 0.5F, 0.1699F, 0.1339F, 0.5431F));

		PartDefinition cube_r10 = hron2.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(36, 57).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.0F, 0.5F, -0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r11 = hron2
				.addOrReplaceChild("cube_r11",
						CubeListBuilder.create().texOffs(58, 5).addBox(-0.5F, -3.0F, -0.5F, 1.0F, 3.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, -5.0F, 1.0F, 0.0F, 0.0F, 0.3054F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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