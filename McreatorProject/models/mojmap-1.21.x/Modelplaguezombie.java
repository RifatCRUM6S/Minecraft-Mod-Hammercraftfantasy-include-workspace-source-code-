// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelplaguezombie<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "plaguezombie"), "main");
	private final ModelPart head;
	private final ModelPart head_horn_f;
	private final ModelPart head_horn_b;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart ra_horn;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public Modelplaguezombie(ModelPart root) {
		this.head = root.getChild("head");
		this.head_horn_f = this.head.getChild("head_horn_f");
		this.head_horn_b = this.head.getChild("head_horn_b");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.ra_horn = this.right_arm.getChild("ra_horn");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(
				-4.0F, -8.5F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -0.5F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -7.0F, 0.0F, -0.2618F, 0.0F, -0.4363F));

		PartDefinition head_horn_f = head.addOrReplaceChild("head_horn_f",
				CubeListBuilder.create().texOffs(44, 16).addBox(-1.5F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -8.0F, -2.5F, 0.3257F, -0.3655F, -0.7103F));

		PartDefinition cube_r2 = head_horn_f.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(16, 47).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -11.5F, 3.0F, 0.1766F, -0.4011F, -2.5667F));

		PartDefinition cube_r3 = head_horn_f.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(44, 46).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -4.5F, 0.0F, -0.4363F, 0.0F, -0.5672F));

		PartDefinition head_horn_b = head.addOrReplaceChild("head_horn_b",
				CubeListBuilder.create().texOffs(44, 23).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -8.5F, 2.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r4 = head_horn_b.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(44, 39).addBox(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.3491F, 0.0F, -0.3491F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(16, 34)
						.addBox(-3.0F, 0.5F, 3.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 41)
						.addBox(-0.5F, 5.5F, -5.0F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(28, 16)
						.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(20, 47)
						.addBox(3.0F, 1.5F, -0.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, -1.4399F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(32, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -1.4399F, 0.0F, 0.0F));

		PartDefinition ra_horn = right_arm.addOrReplaceChild("ra_horn",
				CubeListBuilder.create().texOffs(44, 29).addBox(-3.0F, -5.0F, -1.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 0.0F, 1.0F, 1.1781F, 0.0F, -0.3491F));

		PartDefinition cube_r5 = ra_horn.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(44, 34).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -4.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(28, 32)
				.addBox(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 34)
				.addBox(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}