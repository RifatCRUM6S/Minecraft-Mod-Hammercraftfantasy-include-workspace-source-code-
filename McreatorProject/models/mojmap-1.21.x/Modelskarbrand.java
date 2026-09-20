// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelskarbrand<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "skarbrand"), "main");
	private final ModelPart all;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart upbody;
	private final ModelPart body;
	private final ModelPart balbag;
	private final ModelPart right_wing;
	private final ModelPart rw1;
	private final ModelPart rw2;
	private final ModelPart left_wing;
	private final ModelPart rw3;
	private final ModelPart rw4;
	private final ModelPart head;
	private final ModelPart right_horn_1;
	private final ModelPart right_horn_2;
	private final ModelPart right_horn_3;
	private final ModelPart left_horn_1;
	private final ModelPart right_arm;
	private final ModelPart ra_down;
	private final ModelPart r_axe;
	private final ModelPart left_arm;
	private final ModelPart la_down;
	private final ModelPart l_axe;

	public Modelskarbrand(ModelPart root) {
		this.all = root.getChild("all");
		this.right_leg = this.all.getChild("right_leg");
		this.left_leg = this.all.getChild("left_leg");
		this.upbody = this.all.getChild("upbody");
		this.body = this.upbody.getChild("body");
		this.balbag = this.body.getChild("balbag");
		this.right_wing = this.body.getChild("right_wing");
		this.rw1 = this.right_wing.getChild("rw1");
		this.rw2 = this.rw1.getChild("rw2");
		this.left_wing = this.body.getChild("left_wing");
		this.rw3 = this.left_wing.getChild("rw3");
		this.rw4 = this.rw3.getChild("rw4");
		this.head = this.body.getChild("head");
		this.right_horn_1 = this.head.getChild("right_horn_1");
		this.right_horn_2 = this.head.getChild("right_horn_2");
		this.right_horn_3 = this.head.getChild("right_horn_3");
		this.left_horn_1 = this.head.getChild("left_horn_1");
		this.right_arm = this.upbody.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.r_axe = this.ra_down.getChild("r_axe");
		this.left_arm = this.upbody.getChild("left_arm");
		this.la_down = this.left_arm.getChild("la_down");
		this.l_axe = this.la_down.getChild("l_axe");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(),
				PartPose.offset(-16.0F, -31.0F, 14.5F));

		PartDefinition right_leg = all.addOrReplaceChild(
				"right_leg", CubeListBuilder.create().texOffs(340, 206).addBox(-8.0F, 39.0F, -22.5F, 16.0F, 16.0F,
						16.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(250, 317).addBox(-10.0F, 0.0266F, -0.1287F, 20.0F, 38.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, -16.5F, -8.0F, -0.9743F, 0.5183F, -0.369F));

		PartDefinition cube_r2 = right_leg.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(116, 204).addBox(-10.0F, -42.0F, 0.0F, 20.0F, 42.0F, 19.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 10.5F, -35.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r3 = right_leg
				.addOrReplaceChild("cube_r3",
						CubeListBuilder.create().texOffs(302, 311).addBox(-8.0F, -28.0F, 0.0F, 16.0F, 28.0F, 13.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 34.0F, 3.0F, 1.1345F, 0.0F, 0.0F));

		PartDefinition cube_r4 = right_leg.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(212, 165).addBox(-7.0F, -25.0F, 0.0F, 14.0F, 25.0F, 13.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 39.0F, -20.5F, -0.7418F, 0.0F, 0.0F));

		PartDefinition left_leg = all.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(66, 350).addBox(-8.0F, 39.0F, -22.5F, 16.0F, 16.0F, 16.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(32.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r5 = left_leg.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(350, 153).addBox(-10.0F, 0.0266F, -0.1287F, 20.0F, 38.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.0F, -16.5F, -8.0F, -0.9743F, -0.5183F, 0.369F));

		PartDefinition cube_r6 = left_leg.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(194, 204).addBox(-10.0F, -42.0F, 0.0F, 20.0F, 42.0F, 19.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 10.5F, -35.0F, -0.9599F, 0.0F, 0.0F));

		PartDefinition cube_r7 = left_leg
				.addOrReplaceChild("cube_r7",
						CubeListBuilder.create().texOffs(312, 74).addBox(-8.0F, -28.0F, 0.0F, 16.0F, 28.0F, 13.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 34.0F, 3.0F, 1.1345F, 0.0F, 0.0F));

		PartDefinition cube_r8 = left_leg.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(350, 115).addBox(-7.0F, -25.0F, 0.0F, 14.0F, 25.0F, 13.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 39.0F, -20.5F, -0.7418F, 0.0F, 0.0F));

		PartDefinition upbody = all.addOrReplaceChild("upbody", CubeListBuilder.create(),
				PartPose.offset(16.0F, 3.0F, -4.0F));

		PartDefinition body = upbody
				.addOrReplaceChild("body",
						CubeListBuilder.create().texOffs(0, 156).addBox(-18.0F, -32.0F, -9.5F, 36.0F, 32.0F, 22.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(340, 244).addBox(-16.0F, -4.0F, 0.0F, 32.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -57.5776F, 6.4382F, -1.4399F, 0.0F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(340, 238).addBox(-16.0F, -4.0F, 0.0F, 32.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -59.5776F, 3.9382F, -1.4399F, 0.0F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(334, 0).addBox(-16.0F, -4.0F, 0.0F, 32.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -61.5776F, 1.9382F, -1.4399F, 0.0F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(326, 21).addBox(-16.0F, -4.0F, 0.0F, 32.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -63.5776F, -0.5618F, -1.4399F, 0.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(194, 80).addBox(-16.0F, -4.0F, 0.0F, 32.0F, 6.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -65.5776F, -3.0618F, -1.4399F, 0.0F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(168, 116).addBox(-16.0F, 0.0F, -15.0F, 32.0F, 24.0F, 15.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -56.0F, 6.5F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(310, 382).addBox(-7.0F, -27.0F, -10.0F, 14.0F, 19.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -24.5F, -4.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(302, 252).addBox(-22.0F, -17.0F, -10.0F, 44.0F, 18.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -24.5F, -3.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(240, 385).addBox(-2.0F, -3.5F, -4.5F, 4.0F, 8.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -14.2174F, 12.1556F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(212, 384).addBox(-3.0F, -4.5F, -4.5F, 6.0F, 9.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -24.2174F, 11.6556F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(370, 95).addBox(-4.0F, -4.5F, -4.5F, 8.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -36.7174F, 12.1556F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(356, 370).addBox(-4.0F, -3.5F, -4.5F, 8.0F, 12.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -51.2174F, 8.6556F, -0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(0, 88).addBox(-22.0F, -27.0F, -11.0F, 44.0F, 28.0F, 28.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -25.0F, -1.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition balbag = body.addOrReplaceChild("balbag",
				CubeListBuilder.create().texOffs(360, 311)
						.addBox(-9.0F, -20.0F, -2.0F, 18.0F, 19.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(262, 144)
						.addBox(-18.0F, -11.0F, -1.0F, 36.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(326, 10)
						.addBox(-18.0F, -11.0F, 22.0F, 36.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 1.0F, -9.5F));

		PartDefinition cube_r22 = balbag.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(86, 228).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 32.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -1.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r23 = balbag.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(368, 271).addBox(-18.0F, -10.0F, 0.0F, 22.0F, 10.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(19.0F, -1.0F, 18.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r24 = balbag.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(368, 61).addBox(-18.0F, -10.0F, 0.0F, 22.0F, 10.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-18.0F, -1.0F, 18.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing",
				CubeListBuilder.create().texOffs(0, 210).addBox(-47.5F, -0.5F, 0.0F, 48.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.5F, -56.5F, 6.0F, -0.839F, 0.7254F, -1.0334F));

		PartDefinition rw1 = right_wing.addOrReplaceChild("rw1",
				CubeListBuilder.create().texOffs(0, 32).addBox(-93.0F, -4.0F, -4.5F, 93.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-47.5F, 3.5F, 4.5F, 0.0F, -0.1309F, 2.3562F));

		PartDefinition cube_r25 = rw1.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(212, 0).addBox(-55.0F, -5.0F, 1.0F, 56.0F, 5.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 0.5F, -3.5F, 0.0F, 0.0F, 2.9234F));

		PartDefinition rw2 = rw1.addOrReplaceChild("rw2",
				CubeListBuilder.create().texOffs(0, 0).addBox(-98.0F, -3.5F, -4.0F, 98.0F, 8.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-93.0F, 0.0F, 0.0F, -0.023F, -0.173F, -2.5287F));

		PartDefinition cube_r26 = rw2.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(144, 100).addBox(-69.0F, -7.0F, 1.0F, 70.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-84.0F, 4.5F, -3.0F, 0.0F, 0.0F, -1.2217F));

		PartDefinition cube_r27 = rw2.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(0, 144).addBox(-77.0F, -7.0F, 0.0F, 78.0F, 6.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-93.5F, 4.5F, -3.0F, 0.0F, 0.0F, -0.829F));

		PartDefinition cube_r28 = rw2.addOrReplaceChild("cube_r28",
				CubeListBuilder.create().texOffs(0, 68).addBox(-91.0F, -5.0F, 0.0F, 92.0F, 5.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 2.5F, -1.5F, 0.0F, 0.0F, -0.3491F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing",
				CubeListBuilder.create().texOffs(212, 10).addBox(-0.5F, -0.5F, 0.0F, 48.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.5F, -56.5F, 6.0F, -0.839F, -0.7254F, 1.0334F));

		PartDefinition rw3 = left_wing.addOrReplaceChild("rw3",
				CubeListBuilder.create().texOffs(0, 50).addBox(0.0F, -4.0F, -4.5F, 93.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(47.5F, 3.5F, 4.5F, 0.0F, 0.1309F, -2.3562F));

		PartDefinition cube_r29 = rw3.addOrReplaceChild("cube_r29",
				CubeListBuilder.create().texOffs(212, 155).addBox(-1.0F, -5.0F, 1.0F, 56.0F, 5.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 0.5F, -3.5F, 0.0F, 0.0F, -2.9234F));

		PartDefinition rw4 = rw3.addOrReplaceChild("rw4",
				CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -3.5F, -4.0F, 98.0F, 8.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(93.0F, 0.0F, 0.0F, -0.023F, 0.173F, 2.5287F));

		PartDefinition cube_r30 = rw4.addOrReplaceChild("cube_r30",
				CubeListBuilder.create().texOffs(144, 108).addBox(-1.0F, -7.0F, 1.0F, 70.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(84.0F, 4.5F, -3.0F, 0.0F, 0.0F, 1.2217F));

		PartDefinition cube_r31 = rw4
				.addOrReplaceChild("cube_r31",
						CubeListBuilder.create().texOffs(144, 88).addBox(-1.0F, -7.0F, 0.0F, 78.0F, 6.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(93.5F, 4.5F, -3.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition cube_r32 = rw4
				.addOrReplaceChild("cube_r32",
						CubeListBuilder.create().texOffs(0, 78).addBox(-1.0F, -5.0F, 0.0F, 92.0F, 5.0F, 5.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.0F, 2.5F, -1.5F, 0.0F, 0.0F, 0.3491F));

		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 228)
						.addBox(-11.0F, -14.5F, -8.5F, 22.0F, 22.0F, 21.0F, new CubeDeformation(0.0F)).texOffs(262, 116)
						.addBox(-11.5F, 2.5F, -9.0F, 23.0F, 7.0F, 21.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -54.0F, -19.5F, -0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r33 = head.addOrReplaceChild("cube_r33",
				CubeListBuilder.create().texOffs(292, 352).addBox(-4.0F, 1.0F, 0.0F, 6.0F, 58.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 1.5F, -7.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition right_horn_1 = head.addOrReplaceChild("right_horn_1",
				CubeListBuilder.create().texOffs(250, 356).addBox(-5.0F, -19.0F, -4.0F, 10.0F, 19.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.5F, -13.0F, -4.0F, 0.0873F, 0.0F, -0.1309F));

		PartDefinition cube_r34 = right_horn_1.addOrReplaceChild("cube_r34",
				CubeListBuilder.create().texOffs(0, 374).addBox(-3.5F, -14.0F, 0.0F, 8.0F, 14.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -19.0F, -3.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition right_horn_2 = head.addOrReplaceChild("right_horn_2", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r35 = right_horn_2.addOrReplaceChild("cube_r35",
				CubeListBuilder.create().texOffs(64, 382).addBox(0.0F, -3.0F, -5.0F, 15.0F, 5.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(35.0F, -5.0F, -9.5F, 0.0F, 2.1118F, 0.0F));

		PartDefinition cube_r36 = right_horn_2.addOrReplaceChild("cube_r36",
				CubeListBuilder.create().texOffs(368, 47).addBox(0.0F, -4.0F, -8.0F, 15.0F, 7.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(27.0F, -5.0F, 3.5F, 0.0F, 0.8465F, 0.0F));

		PartDefinition cube_r37 = right_horn_2.addOrReplaceChild("cube_r37",
				CubeListBuilder.create().texOffs(356, 352).addBox(-2.0F, -3.0F, -6.0F, 17.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(12.0F, -7.0F, 5.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r38 = right_horn_2.addOrReplaceChild("cube_r38",
				CubeListBuilder.create().texOffs(350, 192).addBox(-15.0F, -4.0F, -8.0F, 15.0F, 7.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-27.0F, -5.0F, 3.5F, 0.0F, -0.8465F, 0.0F));

		PartDefinition cube_r39 = right_horn_2.addOrReplaceChild("cube_r39",
				CubeListBuilder.create().texOffs(0, 356).addBox(-15.0F, -3.0F, -6.0F, 17.0F, 9.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0F, -7.0F, 5.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition right_horn_3 = head.addOrReplaceChild("right_horn_3", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition cube_r40 = right_horn_3.addOrReplaceChild("cube_r40",
				CubeListBuilder.create().texOffs(272, 252).addBox(-1.0F, -2.0F, -3.0F, 11.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(10.5F, 3.5F, -1.5F, 0.0F, 0.0F, 0.3054F));

		PartDefinition cube_r41 = right_horn_3
				.addOrReplaceChild("cube_r41",
						CubeListBuilder.create().texOffs(262, 385).addBox(-1.0F, -1.0F, -2.0F, 8.0F, 3.0F, 3.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(8.0F, 7.5F, -2.5F, 0.0F, 0.0F, 1.0036F));

		PartDefinition cube_r42 = right_horn_3.addOrReplaceChild("cube_r42",
				CubeListBuilder.create().texOffs(258, 80).addBox(-10.0F, -2.0F, -3.0F, 11.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-10.5F, 3.5F, -1.5F, 0.0F, 0.0F, -0.3054F));

		PartDefinition cube_r43 = right_horn_3.addOrReplaceChild("cube_r43",
				CubeListBuilder.create().texOffs(288, 80).addBox(-7.0F, -1.0F, -2.0F, 8.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, 7.5F, -2.5F, 0.0F, 0.0F, -1.0036F));

		PartDefinition left_horn_1 = head.addOrReplaceChild("left_horn_1",
				CubeListBuilder.create().texOffs(130, 360).addBox(-5.0F, -19.0F, -4.0F, 10.0F, 19.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.5F, -13.0F, -4.0F, 0.0873F, 0.0F, 0.1309F));

		PartDefinition cube_r44 = left_horn_1.addOrReplaceChild("cube_r44",
				CubeListBuilder.create().texOffs(144, 116).addBox(-3.5F, -20.0F, 0.0F, 6.0F, 20.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -32.0F, 0.0F, -0.479F, -0.0226F, -0.0843F));

		PartDefinition cube_r45 = left_horn_1.addOrReplaceChild("cube_r45",
				CubeListBuilder.create().texOffs(32, 374).addBox(-4.5F, -14.0F, 0.0F, 8.0F, 14.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -19.0F, -3.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition right_arm = upbody.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(230, 265).addBox(-10.0F, 0.0F, -9.0F, 18.0F, 34.0F, 18.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-28.5F, -49.5F, -20.0F, 0.3973F, 0.2426F, 0.3939F));

		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down",
				CubeListBuilder.create().texOffs(0, 271)
						.addBox(-9.0F, 0.0F, -18.0F, 18.0F, 34.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(300, 28)
						.addBox(-11.0F, 1.0F, -20.0F, 12.0F, 24.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(194, 317)
						.addBox(0.5F, 1.0F, -18.5F, 9.0F, 24.0F, 19.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 34.0F, 9.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition r_axe = ra_down.addOrReplaceChild("r_axe", CubeListBuilder.create().texOffs(204, 32)
				.addBox(-2.4231F, -4.1729F, -20.3184F, 5.0F, 5.0F, 43.0F, new CubeDeformation(0.0F)).texOffs(0, 323)
				.addBox(-2.4231F, -6.6729F, -61.8184F, 5.0F, 5.0F, 28.0F, new CubeDeformation(0.0F)).texOffs(72, 306)
				.addBox(-1.9231F, -12.1729F, -60.8184F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(336, 144)
				.addBox(-0.9231F, -11.1729F, -55.3184F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(108, 243)
				.addBox(-0.9231F, -10.1729F, -46.3184F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(108, 249)
				.addBox(-1.4231F, -10.1729F, -42.8184F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(108, 228)
				.addBox(-1.4231F, -13.1729F, -50.3184F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(368, 282)
				.addBox(-1.4231F, -1.6729F, -58.8184F, 3.0F, 11.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(340, 382)
				.addBox(-1.9231F, -1.6729F, -61.8184F, 4.0F, 22.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(72, 284)
				.addBox(-1.4231F, 9.3271F, -27.8184F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.4231F, 31.1729F, -5.1816F));

		PartDefinition cube_r46 = r_axe.addOrReplaceChild("cube_r46",
				CubeListBuilder.create().texOffs(370, 72).addBox(-1.5F, -9.0F, -0.5F, 2.0F, 9.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5769F, 18.3271F, -58.3184F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r47 = r_axe.addOrReplaceChild("cube_r47",
				CubeListBuilder.create().texOffs(310, 352).addBox(-1.5F, -9.0F, 9.5F, 2.0F, 9.0F, 21.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5769F, 13.8271F, -57.8184F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r48 = r_axe.addOrReplaceChild("cube_r48",
				CubeListBuilder.create().texOffs(368, 27).addBox(-2.5F, -5.0F, -15.0F, 5.0F, 5.0F, 15.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0769F, 0.8271F, -20.3184F, -0.1745F, 0.0F, 0.0F));

		PartDefinition left_arm = upbody.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(86, 265)
						.addBox(-8.0F, 0.0F, -9.0F, 18.0F, 34.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(272, 165)
						.addBox(-2.0F, -5.0F, -11.0F, 17.0F, 19.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(170, 360)
						.addBox(9.0F, 14.0F, -8.0F, 5.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(27.5F, -49.5F, -20.0F, 0.1786F, 0.2299F, -0.3093F));

		PartDefinition cube_r49 = left_arm.addOrReplaceChild("cube_r49",
				CubeListBuilder.create().texOffs(170, 385).addBox(0.0F, -2.0F, -2.0F, 11.0F, 4.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.5F, -1.5F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition la_down = left_arm.addOrReplaceChild("la_down",
				CubeListBuilder.create().texOffs(158, 265)
						.addBox(-9.0F, 0.0F, -18.0F, 18.0F, 34.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(272, 206)
						.addBox(-1.0F, 1.0F, -20.0F, 12.0F, 24.0F, 22.0F, new CubeDeformation(0.0F)).texOffs(138, 317)
						.addBox(-9.5F, 1.0F, -18.5F, 9.0F, 24.0F, 19.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 34.0F, 9.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition l_axe = la_down.addOrReplaceChild("l_axe", CubeListBuilder.create().texOffs(116, 156)
				.addBox(-2.5F, -2.728F, -16.7957F, 5.0F, 5.0F, 43.0F, new CubeDeformation(0.0F)).texOffs(72, 317)
				.addBox(-2.5F, -5.228F, -58.2957F, 5.0F, 5.0F, 28.0F, new CubeDeformation(0.0F)).texOffs(292, 100)
				.addBox(-2.0F, -10.728F, -57.2957F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(334, 155)
				.addBox(-2.0F, -9.728F, -51.7957F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(108, 237)
				.addBox(-1.0F, -8.728F, -42.7957F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(194, 68)
				.addBox(-0.5F, -11.728F, -46.7957F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(72, 297)
				.addBox(-1.5F, -15.728F, -57.2957F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(212, 360)
				.addBox(-1.5F, -0.228F, -55.2957F, 3.0F, 11.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(104, 382)
				.addBox(-2.0F, -0.228F, -58.2957F, 4.0F, 22.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(302, 271)
				.addBox(-1.0F, 10.772F, -55.2957F, 2.0F, 9.0F, 31.0F, new CubeDeformation(0.0F)).texOffs(72, 271)
				.addBox(-1.5F, 10.772F, -24.2957F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-0.5F, 29.728F, -8.7043F));

		PartDefinition cube_r50 = l_axe.addOrReplaceChild("cube_r50",
				CubeListBuilder.create().texOffs(360, 332).addBox(-2.5F, -5.0F, -15.0F, 5.0F, 5.0F, 15.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 2.272F, -16.7957F, -0.1745F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}