// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelchaos_spawn<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "chaos_spawn"), "main");
	private final ModelPart all;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart body;
	private final ModelPart b_tomur_1;
	private final ModelPart b_tomur_2;
	private final ModelPart b_tomur_3;
	private final ModelPart b_tomur_4;
	private final ModelPart mouth;
	private final ModelPart m_up;
	private final ModelPart m_down;
	private final ModelPart limbs_1;
	private final ModelPart l1_up;
	private final ModelPart l1_down;
	private final ModelPart limbs_2;
	private final ModelPart limbs_3;
	private final ModelPart limbs_4;
	private final ModelPart l4_up;
	private final ModelPart l4_down;
	private final ModelPart limbs_5;
	private final ModelPart head;
	private final ModelPart right_arm;
	private final ModelPart ra_up;
	private final ModelPart ra_mid;
	private final ModelPart ra_down;
	private final ModelPart left_arm;
	private final ModelPart la_up;
	private final ModelPart la_down;

	public Modelchaos_spawn(ModelPart root) {
		this.all = root.getChild("all");
		this.right_leg = this.all.getChild("right_leg");
		this.left_leg = this.all.getChild("left_leg");
		this.body = this.all.getChild("body");
		this.b_tomur_1 = this.body.getChild("b_tomur_1");
		this.b_tomur_2 = this.body.getChild("b_tomur_2");
		this.b_tomur_3 = this.body.getChild("b_tomur_3");
		this.b_tomur_4 = this.body.getChild("b_tomur_4");
		this.mouth = this.body.getChild("mouth");
		this.m_up = this.mouth.getChild("m_up");
		this.m_down = this.mouth.getChild("m_down");
		this.limbs_1 = this.body.getChild("limbs_1");
		this.l1_up = this.limbs_1.getChild("l1_up");
		this.l1_down = this.limbs_1.getChild("l1_down");
		this.limbs_2 = this.body.getChild("limbs_2");
		this.limbs_3 = this.body.getChild("limbs_3");
		this.limbs_4 = this.body.getChild("limbs_4");
		this.l4_up = this.limbs_4.getChild("l4_up");
		this.l4_down = this.limbs_4.getChild("l4_down");
		this.limbs_5 = this.body.getChild("limbs_5");
		this.head = this.body.getChild("head");
		this.right_arm = this.body.getChild("right_arm");
		this.ra_up = this.right_arm.getChild("ra_up");
		this.ra_mid = this.ra_up.getChild("ra_mid");
		this.ra_down = this.ra_mid.getChild("ra_down");
		this.left_arm = this.body.getChild("left_arm");
		this.la_up = this.left_arm.getChild("la_up");
		this.la_down = this.left_arm.getChild("la_down");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(),
				PartPose.offset(-5.5F, 24.0F, -1.0F));

		PartDefinition right_leg = all.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(166, 34)
				.addBox(-4.5F, 9.0F, -10.0F, 9.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.5F, -20.0F, 6.0F));

		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(38, 177).addBox(-6.0F, -12.0F, -4.0F, 8.0F, 12.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 11.0F, -5.5F, -0.1731F, 0.0227F, 0.1289F));

		PartDefinition left_leg = all.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(56, 120).addBox(-1.9507F, 16.0F, -6.867F, 3.0F, 4.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.5F, -20.0F, 6.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r2 = left_leg.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(70, 189).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.4507F, 17.0F, -4.867F, -0.48F, 0.0F, 0.0F));

		PartDefinition cube_r3 = left_leg.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(0, 170).addBox(-2.0F, -4.0F, -3.0F, 5.0F, 4.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.9507F, 8.0F, -5.367F, 0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r4 = left_leg.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(70, 177).addBox(-1.0F, -4.0F, -3.0F, 3.0F, 4.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.9507F, 10.5F, -4.867F, -0.1745F, 0.0F, 0.0F));

		PartDefinition body = all.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 93)
						.addBox(-11.0F, -9.5F, -6.0F, 22.0F, 10.0F, 17.0F, new CubeDeformation(0.0F)).texOffs(0, 50)
						.addBox(-11.0F, -29.0F, -9.0F, 28.0F, 20.0F, 23.0F, new CubeDeformation(0.0F)),
				PartPose.offset(5.5F, -20.0F, 1.0F));

		PartDefinition b_tomur_1 = body.addOrReplaceChild("b_tomur_1", CubeListBuilder.create(),
				PartPose.offset(11.5F, -20.0F, 14.5F));

		PartDefinition cube_r5 = b_tomur_1.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(102, 0).addBox(-15.0F, -17.0F, -2.0F, 17.0F, 17.0F, 17.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 11.5F, -6.0F, 0.1309F, 0.0F, 0.1745F));

		PartDefinition b_tomur_2 = body.addOrReplaceChild("b_tomur_2", CubeListBuilder.create(),
				PartPose.offset(-8.0F, -23.5F, -1.5F));

		PartDefinition cube_r6 = b_tomur_2.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -24.0F, -28.0F, 25.0F, 24.0F, 26.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.0F, 7.0F, 15.0F, 0.1745F, 0.0F, -0.1745F));

		PartDefinition b_tomur_3 = body.addOrReplaceChild("b_tomur_3", CubeListBuilder.create(),
				PartPose.offset(3.0F, -21.5F, -2.5F));

		PartDefinition cube_r7 = b_tomur_3.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(112, 127).addBox(-1.0F, -14.0F, -12.0F, 13.0F, 14.0F, 13.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.5F, 8.0F, -1.0F, 0.3054F, 0.0F, -0.3491F));

		PartDefinition cube_r8 = b_tomur_3.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(78, 93).addBox(-15.0F, -24.0F, -20.0F, 16.0F, 16.0F, 18.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 7.0F, 25.5F, -0.1309F, 0.0F, -0.1745F));

		PartDefinition b_tomur_4 = body.addOrReplaceChild("b_tomur_4", CubeListBuilder.create(),
				PartPose.offset(0.0F, -23.5F, 13.0F));

		PartDefinition cube_r9 = b_tomur_4.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(102, 58).addBox(-15.0F, -14.0F, -1.0F, 14.0F, 14.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(14.5F, -3.0F, -16.0F, -0.1828F, 0.3006F, -0.0547F));

		PartDefinition cube_r10 = b_tomur_4.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(0, 120).addBox(-1.0F, -14.0F, -1.0F, 14.0F, 14.0F, 14.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-15.5F, 16.0F, -2.0F, 0.1309F, 0.0F, -0.2182F));

		PartDefinition mouth = body.addOrReplaceChild("mouth", CubeListBuilder.create(),
				PartPose.offsetAndRotation(10.0F, -29.5F, -6.0F, 0.1731F, -0.0227F, 0.1289F));

		PartDefinition m_up = mouth.addOrReplaceChild("m_up",
				CubeListBuilder.create().texOffs(112, 154)
						.addBox(-8.4327F, -16.7288F, -4.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
						.texOffs(68, 124).addBox(-7.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(126, 86).addBox(-5.0F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
						.texOffs(68, 120).addBox(-2.5F, -17.5F, -5.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
						.texOffs(134, 86).addBox(1.0F, -17.5F, -6.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
						.texOffs(126, 90).addBox(3.0F, -17.5F, -5.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(146, 120).addBox(6.5F, -17.5F, -5.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.5F, 4.5F, -0.1309F, 0.0F, 0.0F));

		PartDefinition m_down = mouth.addOrReplaceChild("m_down",
				CubeListBuilder.create().texOffs(160, 154)
						.addBox(-8.4327F, -16.2288F, -2.4699F, 17.0F, 17.0F, 7.0F, new CubeDeformation(0.0F))
						.texOffs(132, 90).addBox(6.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(138, 90).addBox(3.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(154, 120).addBox(1.5F, -17.0F, 2.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
						.texOffs(146, 124).addBox(-0.5F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(50, 148).addBox(-4.0F, -17.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(166, 54).addBox(-6.0F, -17.0F, 3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.0F, -4.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition limbs_1 = body.addOrReplaceChild("limbs_1", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-8.0F, -32.5F, 6.0F, 0.1745F, 0.0F, -0.48F));

		PartDefinition l1_up = limbs_1.addOrReplaceChild("l1_up", CubeListBuilder.create().texOffs(100, 155).addBox(
				-1.0F, -14.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 0.0F));

		PartDefinition l1_down = limbs_1.addOrReplaceChild("l1_down", CubeListBuilder.create(),
				PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition cube_r11 = l1_down
				.addOrReplaceChild("cube_r11",
						CubeListBuilder.create().texOffs(128, 178).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 16.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0908F));

		PartDefinition limbs_2 = body.addOrReplaceChild("limbs_2",
				CubeListBuilder.create().texOffs(136, 178).addBox(-1.0F, -12.5F, -1.0F, 2.0F, 16.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.0F, -29.5F, 9.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r12 = limbs_2.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(144, 178).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 16.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition limbs_3 = body.addOrReplaceChild("limbs_3",
				CubeListBuilder.create().texOffs(152, 178).addBox(-1.0F, -12.5F, -1.0F, 2.0F, 16.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-9.5F, -16.0F, -2.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r13 = limbs_3.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(160, 178).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 16.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0F, 0.0F, 0.5236F));

		PartDefinition limbs_4 = body.addOrReplaceChild("limbs_4", CubeListBuilder.create(),
				PartPose.offsetAndRotation(14.5F, -12.0F, -8.5F, 1.789F, -0.3927F, 1.2217F));

		PartDefinition l4_up = limbs_4.addOrReplaceChild("l4_up", CubeListBuilder.create().texOffs(168, 178).addBox(
				-1.0F, -14.0F, -1.0F, 2.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 0.0F));

		PartDefinition l4_down = limbs_4.addOrReplaceChild("l4_down", CubeListBuilder.create(),
				PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition cube_r14 = l4_down
				.addOrReplaceChild("cube_r14",
						CubeListBuilder.create().texOffs(176, 178).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 16.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.8727F));

		PartDefinition limbs_5 = body.addOrReplaceChild("limbs_5",
				CubeListBuilder.create().texOffs(184, 178).addBox(-1.0F, -12.5F, -1.0F, 2.0F, 16.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, -31.5F, 14.5F, -1.5708F, 0.7418F, -1.5708F));

		PartDefinition cube_r15 = limbs_5.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(0, 188).addBox(-1.0F, -15.5F, -1.0F, 2.0F, 16.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(),
				PartPose.offset(0.0F, -27.5F, -11.5F));

		PartDefinition cube_r16 = head
				.addOrReplaceChild("cube_r16",
						CubeListBuilder.create().texOffs(56, 127).addBox(-11.0F, -12.0F, -5.0F, 12.0F, 12.0F, 16.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(5.5F, 8.5F, -5.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-10.0F, -30.5F, 4.5F, -0.9061F, 0.2708F, -0.7203F));

		PartDefinition ra_up = right_arm.addOrReplaceChild("ra_up", CubeListBuilder.create().texOffs(102, 34)
				.addBox(-14.5F, -5.5F, -6.0F, 20.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.5F, 0.5F, 0.5F));

		PartDefinition cube_r17 = ra_up.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(146, 86).addBox(-12.0F, -12.0F, -1.0F, 13.0F, 12.0F, 12.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-12.0F, 6.5F, -5.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition ra_mid = ra_up.addOrReplaceChild("ra_mid", CubeListBuilder.create(),
				PartPose.offset(-27.0F, -1.5F, 0.5F));

		PartDefinition cube_r18 = ra_mid.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(0, 148).addBox(-12.691F, 0.2382F, -6.0F, 13.0F, 10.0F, 12.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, 8.5F, -0.5F, 0.0F, 0.0F, -1.3963F));

		PartDefinition cube_r19 = ra_mid.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(50, 155).addBox(-12.691F, 0.2382F, -6.0F, 13.0F, 10.0F, 12.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5F, -0.5F, 0.0F, 0.0F, -0.829F));

		PartDefinition ra_down = ra_mid.addOrReplaceChild("ra_down", CubeListBuilder.create(),
				PartPose.offset(-5.5F, 20.0F, 0.0F));

		PartDefinition cube_r20 = ra_down.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(102, 86).addBox(-7.691F, 3.2382F, -2.0F, 8.0F, 3.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 20.5F, -0.5F, 0.0F, 0.0F, -2.618F));

		PartDefinition cube_r21 = ra_down.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(170, 19).addBox(-7.691F, 2.2382F, -3.0F, 8.0F, 5.0F, 6.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 15.5F, -0.5F, 0.0F, 0.0F, -2.3126F));

		PartDefinition cube_r22 = ra_down.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(164, 137).addBox(-7.691F, 1.2382F, -4.0F, 8.0F, 7.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 9.0F, -0.5F, 0.0F, 0.0F, -1.9199F));

		PartDefinition cube_r23 = ra_down.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(170, 0).addBox(-7.691F, 0.2382F, -5.0F, 8.0F, 9.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 1.0F, -0.5F, 0.0F, 0.0F, -1.7017F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(),
				PartPose.offsetAndRotation(19.0F, -26.5F, 3.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition la_up = left_arm.addOrReplaceChild("la_up", CubeListBuilder.create().texOffs(164, 110)
				.addBox(17.0F, -48.5F, -1.5F, 9.0F, 18.0F, 9.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-19.0F, 46.5F, -3.0F));

		PartDefinition cube_r24 = la_up.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(92, 177).addBox(-1.5F, -3.5F, -3.5F, 3.0F, 7.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(27.5F, -40.5F, 3.0F, 0.3054F, 0.0F, 0.0F));

		PartDefinition la_down = left_arm.addOrReplaceChild("la_down",
				CubeListBuilder.create().texOffs(158, 58).addBox(-5.5F, -0.5F, -5.5F, 11.0F, 17.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.5F, 14.0F, 0.5F, -0.1314F, -0.0865F, 0.0114F));

		PartDefinition cube_r25 = la_down.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(112, 188)
						.addBox(-3.0F, 0.0F, -2.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(22, 188)
						.addBox(-3.0F, 0.0F, 5.0F, 3.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, 21.0F, -3.5F, 0.0F, 0.0F, 0.0873F));

		PartDefinition cube_r26 = la_down.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(112, 178)
						.addBox(-2.0F, -3.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(146, 110)
						.addBox(-2.0F, -3.0F, 5.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 18.5F, -3.5F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r27 = la_down.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(8, 188).addBox(-1.0F, -2.0F, -2.0F, 3.0F, 7.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.5F, 18.0F, -2.0F, 0.0F, 0.0F, 0.1745F));

		return LayerDefinition.create(meshdefinition, 256, 256);
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