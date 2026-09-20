// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelt_champions_armor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "t_champions_armor"), "main");
	private final ModelPart head;
	private final ModelPart right_horn;
	private final ModelPart left_horn;
	private final ModelPart body;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	private final ModelPart right_boots;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart left_boots;
	private final ModelPart left_arm;
	private final ModelPart right_arm;

	public Modelt_champions_armor(ModelPart root) {
		this.head = root.getChild("head");
		this.right_horn = this.head.getChild("right_horn");
		this.left_horn = this.head.getChild("left_horn");
		this.body = root.getChild("body");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
		this.right_boots = this.right_leg.getChild("right_boots");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.left_boots = this.left_leg.getChild("left_boots");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(34, 36)
						.addBox(-4.0F, -10.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(66, 65)
						.addBox(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 54)
						.addBox(4.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(22, 64)
						.addBox(-6.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(50, 18)
						.addBox(-4.0F, -6.0F, -5.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(-6.6F, -11.0F, -5.6F, 13.0F, 11.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(90, 0).addBox(-5.0F, -4.0F, 0.0F, 5.0F, 8.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, -6.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(0, 85).addBox(0.0F, -4.0F, 0.0F, 5.0F, 8.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, -6.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition right_horn = head.addOrReplaceChild("right_horn",
				CubeListBuilder.create().texOffs(24, 37).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -10.0F, -3.0F, 0.0281F, 0.2759F, -0.2576F));

		PartDefinition cube_r3 = right_horn.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(24, 93).addBox(-1.0F, -3.0F, 1.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -7.0F, 1.0F, -0.5873F, -0.402F, -0.5314F));

		PartDefinition cube_r4 = right_horn.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(14, 71).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -0.5873F, -0.402F, -0.5314F));

		PartDefinition cube_r5 = right_horn.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(24, 42).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition left_horn = head.addOrReplaceChild("left_horn",
				CubeListBuilder.create().texOffs(14, 76).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -10.0F, -3.0F, 0.0281F, -0.2759F, 0.2576F));

		PartDefinition cube_r6 = left_horn.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(28, 93).addBox(0.0F, -3.0F, 1.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -7.0F, 1.0F, -0.5873F, 0.402F, 0.5314F));

		PartDefinition cube_r7 = left_horn.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(70, 87).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -0.5873F, 0.402F, 0.5314F));

		PartDefinition cube_r8 = left_horn.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(56, 82).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -3.0F, -1.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(24, 47)
						.addBox(-6.0F, 0.0F, -4.8F, 12.0F, 17.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 23)
						.addBox(-5.0F, 0.0F, -3.0F, 10.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(34, 23)
						.addBox(-4.4F, 5.0F, -3.6F, 9.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r9 = body
				.addOrReplaceChild("cube_r9",
						CubeListBuilder.create().texOffs(70, 51).addBox(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(4.5F, 9.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r10 = body
				.addOrReplaceChild("cube_r10",
						CubeListBuilder.create().texOffs(0, 71).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-4.5F, 9.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(90, 88).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.8889F, -0.9338F, -0.7805F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(90, 81).addBox(0.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, -3.0F, 0.8889F, 0.9338F, 0.7805F));

		PartDefinition cube_r13 = body
				.addOrReplaceChild("cube_r13",
						CubeListBuilder.create().texOffs(90, 30).addBox(-5.0F, -3.0F, 0.0F, 5.0F, 7.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 3.0F, -4.8F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(90, 9).addBox(0.0F, -3.0F, 0.0F, 5.0F, 7.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, -4.8F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r15 = body
				.addOrReplaceChild("cube_r15",
						CubeListBuilder.create().texOffs(0, 37).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 17.0F, 0.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leggings = right_leg.addOrReplaceChild("right_leggings",
				CubeListBuilder.create().texOffs(44, 65)
						.addBox(-2.9F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(90, 38)
						.addBox(-2.0F, 3.0F, -3.3F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r16 = right_leggings.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(86, 63).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.1F, -1.0F, -3.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition right_boots = right_leg.addOrReplaceChild("right_boots", CubeListBuilder.create().texOffs(68, 35)
				.addBox(-2.9F, 10.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_leggings = left_leg.addOrReplaceChild("left_leggings",
				CubeListBuilder.create().texOffs(66, 18)
						.addBox(-2.1F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(38, 92)
						.addBox(-1.0F, 3.0F, -3.3F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r17 = left_leggings.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(86, 69).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, -1.0F, -3.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition left_boots = left_leg.addOrReplaceChild("left_boots", CubeListBuilder.create().texOffs(70, 43)
				.addBox(-2.1F, 10.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(38, 82)
						.addBox(-1.0F, -2.0F, 3.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(74, 92)
						.addBox(6.0F, -2.7F, -5.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 93)
						.addBox(6.0F, -2.7F, 4.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(84, 51)
						.addBox(-1.0F, -2.0F, -4.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(48, 47)
						.addBox(-1.0F, -2.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(26, 81)
						.addBox(0.0F, -1.6F, -4.9F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(66, 75)
						.addBox(0.0F, -1.6F, 5.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r18 = left_arm.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(54, 87).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -3.2984F, 2.569F, 0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r19 = left_arm.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(38, 86).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.2F, 0.6F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r20 = left_arm.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(92, 42).addBox(3.0F, 0.0F, -2.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.5F, 1.2F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r21 = left_arm.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(72, 6).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.0F, 0.5F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r22 = left_arm.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(46, 92).addBox(3.0F, 0.0F, 1.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.5F, -1.2F, 0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r23 = left_arm.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(72, 0).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.0F, -0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(84, 55)
						.addBox(-7.0F, -2.0F, 3.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 93)
						.addBox(-7.0F, -2.7F, -5.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 93)
						.addBox(-7.0F, -2.7F, 4.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(84, 59)
						.addBox(-7.0F, -2.0F, -4.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(50, 0)
						.addBox(-4.0F, -2.0F, -3.0F, 5.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(78, 81)
						.addBox(-6.0F, -1.6F, -4.9F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(14, 81)
						.addBox(-6.0F, -1.6F, 5.0F, 6.0F, 12.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r24 = right_arm.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(88, 24).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -3.2984F, 2.569F, 0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r25 = right_arm.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(88, 18).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.2F, 0.6F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r26 = right_arm.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(70, 92).addBox(-4.0F, 0.0F, -2.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.5F, 1.2F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r27 = right_arm.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(78, 75).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.0F, 0.5F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r28 = right_arm.addOrReplaceChild("cube_r28",
				CubeListBuilder.create().texOffs(50, 92).addBox(-4.0F, 0.0F, 1.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.5F, -1.2F, 0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r29 = right_arm.addOrReplaceChild("cube_r29",
				CubeListBuilder.create().texOffs(72, 12).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.0F, -0.5F, 0.9599F, 0.0F, 0.0F));

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
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}