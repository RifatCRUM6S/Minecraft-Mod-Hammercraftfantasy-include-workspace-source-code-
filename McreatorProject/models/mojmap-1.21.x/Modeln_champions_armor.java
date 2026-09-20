// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modeln_champions_armor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "n_champions_armor"), "main");
	private final ModelPart head;
	private final ModelPart right_horn;
	private final ModelPart left_horn;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	private final ModelPart right_boots;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart left_boots;

	public Modeln_champions_armor(ModelPart root) {
		this.head = root.getChild("head");
		this.right_horn = this.head.getChild("right_horn");
		this.left_horn = this.head.getChild("left_horn");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
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
				CubeListBuilder.create().texOffs(36, 0)
						.addBox(-4.0F, -10.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(44, 63)
						.addBox(-1.0F, -5.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(88, 17)
						.addBox(-3.0F, -6.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(88, 79)
						.addBox(1.0F, -6.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(22, 50)
						.addBox(-3.0F, -3.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(88, 13)
						.addBox(1.0F, -3.0F, -6.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 68)
						.addBox(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 38)
						.addBox(4.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 50)
						.addBox(-6.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(12, 85)
						.addBox(-4.0F, -6.0F, -5.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(12, 89).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -3.0F, -5.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(72, 83).addBox(0.0F, -3.0F, -1.0F, 1.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -3.0F, -5.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(76, 67).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create(),
				PartPose.offset(4.0F, -9.0F, 0.0F));

		PartDefinition cube_r4 = right_horn
				.addOrReplaceChild("cube_r4",
						CubeListBuilder.create().texOffs(44, 55).addBox(-2.0F, -6.0F, -3.0F, 2.0F, 6.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.0F, -9.5F, 1.0F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r5 = right_horn.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(48, 79).addBox(-2.0F, -7.0F, -3.0F, 3.0F, 7.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, -4.0F, 0.5F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r6 = right_horn
				.addOrReplaceChild("cube_r6",
						CubeListBuilder.create().texOffs(76, 47).addBox(-2.0F, -6.0F, -3.0F, 4.0F, 6.0F, 4.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create(),
				PartPose.offset(-4.0F, -9.0F, 0.0F));

		PartDefinition cube_r7 = left_horn.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(40, 86).addBox(0.0F, -6.0F, -3.0F, 2.0F, 6.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -9.5F, 1.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r8 = left_horn.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(76, 79).addBox(-1.0F, -7.0F, -3.0F, 3.0F, 7.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -4.0F, 0.5F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r9 = left_horn
				.addOrReplaceChild("cube_r9",
						CubeListBuilder.create().texOffs(76, 57).addBox(-2.0F, -6.0F, -3.0F, 4.0F, 6.0F, 4.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 38)
						.addBox(-4.0F, 0.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-5.0F, 4.0F, -3.5F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(76, 33).addBox(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.5F, 10.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(62, 69).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 10.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(0, 84).addBox(0.0F, -3.0F, 0.0F, 5.0F, 8.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 7.0F, -5.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(88, 0).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.8889F, -0.9338F, -0.7805F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(28, 86).addBox(0.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, -3.0F, 0.8889F, 0.9338F, 0.7805F));

		PartDefinition cube_r15 = body
				.addOrReplaceChild("cube_r15",
						CubeListBuilder.create().texOffs(60, 83).addBox(-5.0F, -3.0F, 0.0F, 5.0F, 8.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 7.0F, -5.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(76, 72)
						.addBox(-1.0F, 8.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(38, 11)
						.addBox(-1.0F, -4.0F, -4.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(44, 69)
						.addBox(5.0F, -3.0F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 16)
						.addBox(-1.0F, 0.0F, -5.0F, 9.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(22, 55)
						.addBox(-1.0F, 1.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(0, 77)
						.addBox(-3.0F, 8.0F, -2.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(38, 23)
						.addBox(-5.0F, -4.0F, -4.0F, 6.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(70, 0)
						.addBox(-7.0F, -3.0F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 27)
						.addBox(-8.0F, 0.0F, -5.0F, 9.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(66, 11)
						.addBox(-4.0F, 1.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r16 = right_arm.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(24, 89).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.4F, -2.0F, -1.4F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r17 = right_arm.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(20, 89).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.6F, -3.0F, 1.7F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r18 = right_arm.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(16, 89).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.3F, -7.0F, 0.5F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r19 = right_arm.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(88, 7).addBox(-2.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leggings = right_leg.addOrReplaceChild("right_leggings",
				CubeListBuilder.create().texOffs(54, 35)
						.addBox(-2.9F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(88, 83)
						.addBox(-2.0F, 3.0F, -3.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r20 = right_leggings.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(16, 78).addBox(-4.0F, -1.0F, 0.0F, 8.0F, 7.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.1F, 0.0F, -3.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition right_boots = right_leg.addOrReplaceChild("right_boots", CubeListBuilder.create().texOffs(66, 24)
				.addBox(-2.9F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_leggings = left_leg.addOrReplaceChild("left_leggings",
				CubeListBuilder.create().texOffs(54, 52)
						.addBox(-2.1F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(88, 87)
						.addBox(-1.0F, 3.0F, -3.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r21 = left_leggings.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(32, 79).addBox(-4.0F, -1.0F, 0.0F, 8.0F, 7.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, 0.0F, -3.5F, -0.1745F, 0.0F, 0.0F));

		PartDefinition left_boots = left_leg.addOrReplaceChild("left_boots", CubeListBuilder.create().texOffs(0, 68)
				.addBox(-2.1F, 10.0F, -4.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}