// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelgreat_unclean_one<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "great_unclean_one"), "main");
	private final ModelPart right_leg;
	private final ModelPart rl_down;
	private final ModelPart rl_up;
	private final ModelPart left_leg;
	private final ModelPart ll_down;
	private final ModelPart ll_up;
	private final ModelPart body;
	private final ModelPart tum_up;
	private final ModelPart tum_down;
	private final ModelPart head;
	private final ModelPart h_down;
	private final ModelPart h_up;
	private final ModelPart r_horn;
	private final ModelPart l_horn;
	private final ModelPart right_arm;
	private final ModelPart ra_up;
	private final ModelPart ra_down;
	private final ModelPart left_arm;
	private final ModelPart la_up;
	private final ModelPart la_down;

	public Modelgreat_unclean_one(ModelPart root) {
		this.right_leg = root.getChild("right_leg");
		this.rl_down = this.right_leg.getChild("rl_down");
		this.rl_up = this.right_leg.getChild("rl_up");
		this.left_leg = root.getChild("left_leg");
		this.ll_down = this.left_leg.getChild("ll_down");
		this.ll_up = this.left_leg.getChild("ll_up");
		this.body = root.getChild("body");
		this.tum_up = this.body.getChild("tum_up");
		this.tum_down = this.body.getChild("tum_down");
		this.head = root.getChild("head");
		this.h_down = this.head.getChild("h_down");
		this.h_up = this.head.getChild("h_up");
		this.r_horn = this.h_up.getChild("r_horn");
		this.l_horn = this.h_up.getChild("l_horn");
		this.right_arm = root.getChild("right_arm");
		this.ra_up = this.right_arm.getChild("ra_up");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.left_arm = root.getChild("left_arm");
		this.la_up = this.left_arm.getChild("la_up");
		this.la_down = this.left_arm.getChild("la_down");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-27.0F, -11.0F, 12.0F));

		PartDefinition rl_down = right_leg.addOrReplaceChild("rl_down", CubeListBuilder.create().texOffs(284, 48)
				.addBox(-10.0F, 15.0F, -8.0F, 24.0F, 8.0F, 23.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 12.0F, -12.0F));

		PartDefinition cube_r1 = rl_down
				.addOrReplaceChild("cube_r1",
						CubeListBuilder.create().texOffs(0, 347).addBox(-9.0F, -20.0F, -9.0F, 22.0F, 24.0F, 18.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 15.0F, 5.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition rl_up = right_leg.addOrReplaceChild("rl_up", CubeListBuilder.create(),
				PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition cube_r2 = rl_up.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(284, 0).addBox(-10.0F, -22.0F, -10.0F, 24.0F, 26.0F, 22.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 17.0F, -13.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(27.0F, -11.0F, 12.0F));

		PartDefinition ll_down = left_leg.addOrReplaceChild("ll_down", CubeListBuilder.create().texOffs(0, 316)
				.addBox(-14.0F, 15.0F, -8.0F, 24.0F, 8.0F, 23.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 12.0F, -12.0F));

		PartDefinition cube_r3 = ll_down
				.addOrReplaceChild("cube_r3",
						CubeListBuilder.create().texOffs(356, 310).addBox(-13.0F, -20.0F, -9.0F, 22.0F, 24.0F, 18.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 15.0F, 5.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition ll_up = left_leg.addOrReplaceChild("ll_up", CubeListBuilder.create(),
				PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition cube_r4 = ll_up.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(112, 310).addBox(-14.0F, -22.0F, -10.0F, 24.0F, 26.0F, 22.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 17.0F, -13.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 83)
				.addBox(-38.0F, -11.4648F, -23.0085F, 79.0F, 16.0F, 49.0F, new CubeDeformation(0.0F)).texOffs(246, 148)
				.addBox(-25.0F, -40.4648F, -21.0085F, 53.0F, 11.0F, 44.0F, new CubeDeformation(0.0F)).texOffs(148, 364)
				.addBox(28.0F, -40.4648F, -9.0085F, 12.0F, 19.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(216, 364)
				.addBox(-37.0F, -40.4648F, -9.0085F, 12.0F, 19.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(0, 148)
				.addBox(-31.0F, -31.4648F, -31.0085F, 65.0F, 12.0F, 58.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -29.0F, 11.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition tum_up = body.addOrReplaceChild("tum_up", CubeListBuilder.create().texOffs(0, 0).addBox(-36.0F,
				-20.0F, -68.0F, 75.0F, 16.0F, 67.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -2.4648F, 29.9915F));

		PartDefinition cube_r5 = tum_up.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(284, 79)
						.addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(224, 283)
						.addBox(-33.0F, -2.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(316, 203)
						.addBox(-56.0F, -2.0F, 0.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(33.0F, 1.0F, -76.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r6 = tum_up.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(310, 203)
						.addBox(-1.0F, -3.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(224, 289)
						.addBox(-6.0F, -2.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(224, 302)
						.addBox(-11.0F, -2.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(322, 203)
						.addBox(-19.0F, -2.0F, 0.0F, 2.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(326, 209)
						.addBox(-30.0F, -2.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(224, 277)
						.addBox(-44.0F, -2.0F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(322, 209)
						.addBox(-54.0F, -2.0F, 0.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(224, 295)
						.addBox(-59.0F, -3.0F, 0.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(25.0F, 2.0F, -76.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r7 = tum_up.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(316, 209).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0F, 3.0F, -76.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r8 = tum_up.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(256, 83).addBox(-37.0F, -10.0F, 11.0F, 77.0F, 13.0F, 22.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -86.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition tum_down = body.addOrReplaceChild("tum_down",
				CubeListBuilder.create().texOffs(232, 218)
						.addBox(-29.0F, -3.0F, -35.0F, 64.0F, 10.0F, 45.0F, new CubeDeformation(0.0F)).texOffs(0, 218)
						.addBox(-35.0F, -13.0F, -21.0F, 73.0F, 16.0F, 43.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 12.5352F, -1.0085F));

		PartDefinition cube_r9 = tum_down.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(262, 213)
						.addBox(-2.0F, -4.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(300, 213)
						.addBox(-8.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(254, 213)
						.addBox(-22.0F, -4.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(330, 208)
						.addBox(-31.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(276, 213)
						.addBox(-48.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(328, 203)
						.addBox(-51.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(246, 213)
						.addBox(-62.0F, -4.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(35.0F, -4.0F, -47.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r10 = tum_down.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(330, 213)
						.addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(288, 213)
						.addBox(-17.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(282, 213)
						.addBox(-27.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(270, 213)
						.addBox(-45.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(306, 213)
						.addBox(-55.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(24.0F, -5.0F, -47.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r11 = tum_down.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(294, 213)
						.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(310, 210)
						.addBox(-28.0F, -6.0F, -1.0F, 2.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(19.0F, -3.0F, -47.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r12 = tum_down.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(232, 273).addBox(-34.0F, -8.0F, -1.0F, 71.0F, 11.0F, 26.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, -45.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(),
				PartPose.offset(0.0F, -69.0F, 5.0F));

		PartDefinition h_down = head.addOrReplaceChild("h_down", CubeListBuilder.create(),
				PartPose.offset(0.0F, 1.0F, -3.0F));

		PartDefinition cube_r13 = h_down
				.addOrReplaceChild("cube_r13",
						CubeListBuilder.create().texOffs(112, 277).addBox(-12.0F, -3.0F, -19.0F, 27.0F, 4.0F, 29.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition h_up = head.addOrReplaceChild("h_up", CubeListBuilder.create().texOffs(0, 277).addBox(-12.0F,
				-10.0F, -20.0F, 27.0F, 10.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition r_horn = h_up.addOrReplaceChild("r_horn",
				CubeListBuilder.create().texOffs(320, 118).addBox(-20.0F, -30.0F, 6.0F, 5.0F, 25.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -11.0F, -10.0F, 0.2559F, 0.056F, -0.4291F));

		PartDefinition cube_r14 = r_horn.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(246, 203).addBox(-26.0F, -33.0F, -1.0F, 27.0F, 5.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-33.0F, -2.0F, 7.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition cube_r15 = r_horn.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(256, 128).addBox(-4.0F, -21.0F, -1.0F, 20.0F, 4.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.0F, -7.0F, 7.0F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r16 = r_horn.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(94, 316).addBox(-3.0F, -21.0F, 0.0F, 4.0F, 21.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-19.0F, -20.0F, 7.0F, 0.5236F, 0.0F, -1.2654F));

		PartDefinition cube_r17 = r_horn.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(308, 364).addBox(-2.0F, -19.0F, -1.0F, 3.0F, 19.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-29.0F, -36.0F, 7.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r18 = r_horn.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(306, 128).addBox(-2.0F, -19.0F, -1.0F, 3.0F, 13.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-56.0F, -33.0F, 8.0F, 0.0F, 0.0F, 1.0908F));

		PartDefinition cube_r19 = r_horn.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(360, 118).addBox(-1.0F, -14.0F, -1.0F, 6.0F, 14.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.0F, 5.0F, 3.0F, -0.2181F, 0.0038F, -0.6983F));

		PartDefinition l_horn = h_up.addOrReplaceChild("l_horn",
				CubeListBuilder.create().texOffs(340, 118).addBox(15.0F, -30.0F, 6.0F, 5.0F, 25.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -9.0F, -10.0F, 0.7778F, -0.168F, 0.4496F));

		PartDefinition cube_r20 = l_horn.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(256, 118).addBox(-2.0096F, -1.2513F, -1.0F, 27.0F, 5.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(18.0F, -30.0F, 7.0F, 0.0F, -0.7854F, -0.5236F));

		PartDefinition cube_r21 = l_horn.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(256, 137).addBox(-16.0F, -21.0F, -1.0F, 20.0F, 4.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.0F, -10.0F, 6.0F, 0.0F, -0.5236F, 0.3054F));

		PartDefinition cube_r22 = l_horn.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(320, 364).addBox(-1.0F, -19.0F, -1.0F, 3.0F, 19.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(29.0F, -36.0F, 21.0F, 0.48F, 0.0F, 0.0F));

		PartDefinition cube_r23 = l_horn.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(284, 364).addBox(-5.0F, -14.0F, -1.0F, 6.0F, 14.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(10.0F, 5.0F, 3.0F, -0.2181F, -0.0038F, 0.6983F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-34.0F, -58.0F, 2.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition ra_up = right_arm.addOrReplaceChild("ra_up", CubeListBuilder.create().texOffs(204, 310)
				.addBox(-7.0F, -4.0F, -1.0F, 19.0F, 35.0F, 19.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-4.0F, -2.0F, -8.0F));

		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down",
				CubeListBuilder.create().texOffs(356, 352).addBox(-9.0F, -5.0F, -8.0F, 18.0F, 31.0F, 16.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 29.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(),
				PartPose.offsetAndRotation(34.0F, -58.0F, 2.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition la_up = left_arm.addOrReplaceChild("la_up", CubeListBuilder.create().texOffs(280, 310)
				.addBox(-12.0F, -4.0F, -1.0F, 19.0F, 35.0F, 19.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, -2.0F, -8.0F));

		PartDefinition la_down = left_arm.addOrReplaceChild("la_down",
				CubeListBuilder.create().texOffs(80, 358).addBox(-9.0F, -5.0F, -8.0F, 18.0F, 31.0F, 16.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 29.0F, 0.0F, -0.5672F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

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
}