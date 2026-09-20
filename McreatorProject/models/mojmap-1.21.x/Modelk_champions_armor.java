// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelk_champions_armor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "k_champions_armor"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_leg;
	private final ModelPart right_leggings;
	private final ModelPart right_boots;
	private final ModelPart left_leg;
	private final ModelPart left_leggings;
	private final ModelPart left_boots;
	private final ModelPart right_arm;
	private final ModelPart left_arm;

	public Modelk_champions_armor(ModelPart root) {
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.right_leg = root.getChild("right_leg");
		this.right_leggings = this.right_leg.getChild("right_leggings");
		this.right_boots = this.right_leg.getChild("right_boots");
		this.left_leg = root.getChild("left_leg");
		this.left_leggings = this.left_leg.getChild("left_leggings");
		this.left_boots = this.left_leg.getChild("left_boots");
		this.right_arm = root.getChild("right_arm");
		this.left_arm = root.getChild("left_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 14)
						.addBox(-4.0F, -10.0F, -5.0F, 8.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(80, 74)
						.addBox(-1.0F, -6.0F, -6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 54)
						.addBox(-4.0F, -9.0F, 4.0F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(28, 25)
						.addBox(4.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(34, 0)
						.addBox(-6.0F, -9.0F, -5.0F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(82, 11)
						.addBox(6.5F, -11.0F, -0.9F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 41)
						.addBox(5.5F, -12.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 21)
						.addBox(-8.0F, -22.0F, -1.1F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(50, 34)
						.addBox(-6.0F, -13.0F, -1.1F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 58)
						.addBox(6.0F, -8.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(76, 8)
						.addBox(-4.0F, -6.0F, -5.0F, 8.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(50, 38)
						.addBox(2.0F, -22.0F, -1.1F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 17)
						.addBox(-7.0F, -18.0F, -1.1F, 14.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 64)
						.addBox(-8.0F, -8.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 34)
						.addBox(-8.5F, -11.0F, -0.9F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(82, 70)
						.addBox(-7.5F, -12.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(26, 73).addBox(-2.0F, -14.0F, -1.0F, 2.0F, 12.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -10.0F, 0.1F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(18, 73).addBox(0.0F, -14.0F, -1.0F, 2.0F, 12.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -10.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(78, 0).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 3.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -3.0F, -5.0F, 0.0F, -1.1345F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(34, 77).addBox(0.0F, -1.0F, -5.0F, 1.0F, 3.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -3.0F, -5.0F, 0.0F, 1.1345F, 0.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(56, 8).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-5.0F, 0.0F, -3.0F, 10.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(0, 25)
						.addBox(-4.0F, 5.0F, -3.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body
				.addOrReplaceChild("cube_r6",
						CubeListBuilder.create().texOffs(20, 59).addBox(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(4.5F, 9.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r7 = body
				.addOrReplaceChild("cube_r7",
						CubeListBuilder.create().texOffs(34, 63).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(-4.5F, 9.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(0, 80).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -2.0F, -3.0F, 0.8889F, -0.9338F, -0.7805F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(70, 79).addBox(0.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -2.0F, -3.0F, 0.8889F, 0.9338F, 0.7805F));

		PartDefinition cube_r10 = body
				.addOrReplaceChild("cube_r10",
						CubeListBuilder.create().texOffs(58, 79).addBox(-5.0F, -3.0F, 0.0F, 5.0F, 7.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 3.0F, -4.8F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(46, 79).addBox(0.0F, -3.0F, 0.0F, 5.0F, 7.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 3.0F, -4.8F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(66, 66).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 9.0F, 4.0F, -0.1752F, -0.0859F, 0.0152F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(66, 58).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 9.0F, 4.0F, -0.1752F, 0.0859F, -0.0152F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(66, 38).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 9.0F, -4.0F, 0.1314F, -0.0865F, -0.0114F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(66, 13).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 9.0F, -4.0F, 0.1314F, 0.0865F, 0.0114F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leggings = right_leg.addOrReplaceChild("right_leggings",
				CubeListBuilder.create().texOffs(0, 37)
						.addBox(-2.9F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(56, 13)
						.addBox(-2.0F, 3.0F, -3.3F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r16 = right_leggings.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(48, 73).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.1F, -1.0F, -3.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition right_boots = right_leg.addOrReplaceChild("right_boots", CubeListBuilder.create().texOffs(44, 55)
				.addBox(-2.9F, 10.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_leggings = left_leg.addOrReplaceChild("left_leggings",
				CubeListBuilder.create().texOffs(22, 42)
						.addBox(-2.1F, -1.0F, -3.0F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(34, 59)
						.addBox(-1.0F, 3.0F, -3.3F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r17 = left_leggings.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(0, 74).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.9F, -1.0F, -3.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition left_boots = left_leg.addOrReplaceChild("left_boots", CubeListBuilder.create().texOffs(56, 0)
				.addBox(-2.1F, 10.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(72, 21)
						.addBox(-7.0F, -2.0F, 3.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(84, 52)
						.addBox(-7.0F, -2.7F, -5.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(42, 85)
						.addBox(-7.0F, -2.7F, 4.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(72, 25)
						.addBox(-7.0F, -2.0F, -4.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(50, 21)
						.addBox(-4.0F, -2.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition cube_r18 = right_arm.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(34, 85).addBox(-4.0F, 0.0F, -2.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.5F, 1.2F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r19 = right_arm.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(64, 74).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.2F, 0.6F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r20 = right_arm.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(66, 52).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.0F, 0.5F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r21 = right_arm.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(84, 45).addBox(-4.0F, 0.0F, 1.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.5F, -1.2F, 0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r22 = right_arm.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(66, 46).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -4.0F, -0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(48, 69)
						.addBox(-1.0F, -2.0F, 3.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 37)
						.addBox(6.0F, -2.7F, -5.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(38, 85)
						.addBox(6.0F, -2.7F, 4.2F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 70)
						.addBox(-1.0F, -2.0F, -4.2F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 42)
						.addBox(-1.0F, -2.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition cube_r23 = left_arm.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(12, 80).addBox(3.0F, 0.0F, -2.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.5F, 1.2F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r24 = left_arm.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(72, 29).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.2F, 0.6F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r25 = left_arm.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(0, 64).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.0F, 0.5F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r26 = left_arm.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(82, 79).addBox(3.0F, 0.0F, 1.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.5F, -1.2F, 0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r27 = left_arm.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(48, 63).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -4.0F, -0.5F, 0.9599F, 0.0F, 0.0F));

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
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}