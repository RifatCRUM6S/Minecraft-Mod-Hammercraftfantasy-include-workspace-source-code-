// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelvalkia<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "valkia"), "main");
	private final ModelPart all;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart r_horn;
	private final ModelPart l_horn;
	private final ModelPart right_wing;
	private final ModelPart rw1;
	private final ModelPart rw2;
	private final ModelPart left_wing;
	private final ModelPart lw1;
	private final ModelPart lw2;
	private final ModelPart right_arm;
	private final ModelPart ra_down;
	private final ModelPart shield;
	private final ModelPart left_arm;
	private final ModelPart la_down;
	private final ModelPart spear;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public Modelvalkia(ModelPart root) {
		this.all = root.getChild("all");
		this.body = this.all.getChild("body");
		this.head = this.body.getChild("head");
		this.r_horn = this.head.getChild("r_horn");
		this.l_horn = this.head.getChild("l_horn");
		this.right_wing = this.body.getChild("right_wing");
		this.rw1 = this.right_wing.getChild("rw1");
		this.rw2 = this.rw1.getChild("rw2");
		this.left_wing = this.body.getChild("left_wing");
		this.lw1 = this.left_wing.getChild("lw1");
		this.lw2 = this.lw1.getChild("lw2");
		this.right_arm = this.body.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.shield = this.ra_down.getChild("shield");
		this.left_arm = this.body.getChild("left_arm");
		this.la_down = this.left_arm.getChild("la_down");
		this.spear = this.la_down.getChild("spear");
		this.right_leg = this.all.getChild("right_leg");
		this.left_leg = this.all.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(),
				PartPose.offset(0.0F, -3.0F, 5.0F));

		PartDefinition body = all.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(132, 63)
						.addBox(-4.0F, -6.5F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(86, 121)
						.addBox(-5.0F, -13.5F, -4.0F, 10.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 12.0F, -3.5F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(72, 147).addBox(-3.0F, -1.0F, -8.0F, 6.0F, 1.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, -3.5F, 0.0F, 0.0F, -1.5708F, 1.0908F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(44, 145).addBox(-3.0F, -1.0F, -8.0F, 6.0F, 1.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, -3.5F, 0.0F, 0.0F, 1.5708F, -1.0908F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(150, 141).addBox(0.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.5F, -5.0F, 0.1745F, -0.3491F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(72, 145).addBox(-5.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -12.5F, -5.0F, 0.1745F, 0.3491F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(106, 158).addBox(-2.0F, -4.0F, 3.0F, 4.0F, 8.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -7.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(126, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -13.5F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r6 = head.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(86, 136).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 4.0F, 7.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -8.0F, -4.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition r_horn = head.addOrReplaceChild("r_horn",
				CubeListBuilder.create().texOffs(60, 154).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 6.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -7.0F, -3.5F, 0.5167F, 0.0934F, -0.1476F));

		PartDefinition cube_r7 = r_horn.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(116, 136).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.5F, 3.5F, -0.9145F, -0.0692F, -0.4459F));

		PartDefinition cube_r8 = r_horn.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(158, 94).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -6.0F, 0.5F, -0.5672F, 0.0F, 0.0F));

		PartDefinition l_horn = head.addOrReplaceChild("l_horn",
				CubeListBuilder.create().texOffs(72, 156).addBox(-1.5F, -6.0F, 0.0F, 3.0F, 6.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, -7.0F, -3.5F, 0.5167F, -0.0934F, 0.1476F));

		PartDefinition cube_r9 = l_horn.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(38, 157).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.5F, 3.5F, -0.9145F, 0.0692F, 0.4459F));

		PartDefinition cube_r10 = l_horn.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(98, 158).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -6.0F, 0.5F, -0.5672F, 0.0F, 0.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing",
				CubeListBuilder.create().texOffs(126, 16)
						.addBox(-15.0F, 0.5F, 0.5F, 15.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(150, 137)
						.addBox(-15.0F, -0.5F, 0.0F, 15.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.0F, -13.0F, 4.0F));

		PartDefinition rw1 = right_wing.addOrReplaceChild("rw1",
				CubeListBuilder.create().texOffs(0, 63)
						.addBox(-33.0F, 0.0F, 0.0F, 33.0F, 23.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(50, 113)
						.addBox(-33.0F, -1.0F, -0.5F, 33.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-15.0F, 0.5F, 0.5F));

		PartDefinition cube_r11 = rw1.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(44, 140).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-32.5F, -1.5F, -0.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition rw2 = rw1.addOrReplaceChild("rw2",
				CubeListBuilder.create().texOffs(86, 117)
						.addBox(-27.0F, 0.0F, 0.0F, 27.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 86)
						.addBox(-27.0F, 1.0F, 0.5F, 27.0F, 27.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-33.0F, -1.0F, -0.5F));

		PartDefinition cube_r12 = rw2.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(108, 86).addBox(-25.0F, 0.0F, 0.0F, 25.0F, 27.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-26.5F, 1.0F, 0.5F, 0.0F, 0.0F, -0.829F));

		PartDefinition cube_r13 = rw2.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(118, 115).addBox(-26.0F, 0.0F, 0.0F, 26.0F, 1.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-27.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.829F));

		PartDefinition cube_r14 = rw2.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(142, 117).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-26.5F, -0.5F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing",
				CubeListBuilder.create().texOffs(126, 32)
						.addBox(0.0F, 0.5F, 0.5F, 15.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(150, 139)
						.addBox(0.0F, -0.5F, 0.0F, 15.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, -13.0F, 4.0F));

		PartDefinition lw1 = left_wing.addOrReplaceChild("lw1",
				CubeListBuilder.create().texOffs(66, 63)
						.addBox(0.0F, 0.0F, 0.0F, 33.0F, 23.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(50, 115)
						.addBox(0.0F, -1.0F, -0.5F, 33.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(15.0F, 0.5F, 0.5F));

		PartDefinition cube_r15 = lw1.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(160, 73).addBox(0.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(32.5F, -1.5F, -0.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition lw2 = lw1.addOrReplaceChild("lw2",
				CubeListBuilder.create().texOffs(118, 113)
						.addBox(0.0F, 0.0F, 0.0F, 27.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(54, 86)
						.addBox(0.0F, 1.0F, 0.5F, 27.0F, 27.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offset(33.0F, -1.0F, -0.5F));

		PartDefinition cube_r16 = lw2
				.addOrReplaceChild("cube_r16",
						CubeListBuilder.create().texOffs(0, 113).addBox(0.0F, 0.0F, 0.0F, 25.0F, 27.0F, 0.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(26.5F, 1.0F, 0.5F, 0.0F, 0.0F, 0.829F));

		PartDefinition cube_r17 = lw2
				.addOrReplaceChild("cube_r17",
						CubeListBuilder.create().texOffs(86, 119).addBox(0.0F, 0.0F, 0.0F, 26.0F, 1.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(27.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition cube_r18 = lw2.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(160, 77).addBox(0.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(26.5F, -0.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(156, 16).addBox(-4.0F, 1.0F, -2.0F, 4.0F, 6.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.5F, -13.0F, -0.5F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r19 = right_arm.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(146, 117).addBox(0.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.5F, -3.0F, 0.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r20 = right_arm.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(158, 108).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -1.5F, 0.0F, 0.0F, 0.0F, -0.829F));

		PartDefinition cube_r21 = right_arm
				.addOrReplaceChild("cube_r21",
						CubeListBuilder.create().texOffs(150, 117).addBox(-6.0F, -4.0F, -3.0F, 7.0F, 4.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down",
				CubeListBuilder.create().texOffs(44, 154).addBox(-2.0F, 0.5F, -2.0F, 4.0F, 7.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, 6.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r22 = ra_down
				.addOrReplaceChild("cube_r22",
						CubeListBuilder.create().texOffs(158, 0).addBox(-5.0F, 0.0F, 0.0F, 5.0F, 3.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(2.5F, -0.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition shield = ra_down.addOrReplaceChild("shield",
				CubeListBuilder.create().texOffs(50, 117)
						.addBox(-1.5F, -7.0F, -10.0F, 1.0F, 11.0F, 17.0F, new CubeDeformation(0.0F)).texOffs(122, 121)
						.addBox(-2.5F, -6.0F, -8.0F, 1.0F, 9.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(136, 152)
						.addBox(-2.5F, 3.0F, -6.0F, 1.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 140)
						.addBox(-1.5F, 4.0F, -7.0F, 1.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(16, 157)
						.addBox(-1.5F, 8.0F, -4.0F, 1.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(126, 48)
						.addBox(-1.5F, -9.0F, -8.0F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 6.5F, 0.0F, 0.1745F, 0.0F, -0.1745F));

		PartDefinition cube_r23 = shield.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(158, 10).addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -4.0F, -5.0F, 0.0F, -0.1745F, 0.1745F));

		PartDefinition cube_r24 = shield.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(154, 59).addBox(-5.0F, -1.0F, -1.0F, 5.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.0F, -4.0F, 2.0F, 0.0F, 0.1745F, 0.1745F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(156, 26).addBox(0.0F, 1.0F, -2.0F, 4.0F, 6.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.5F, -13.0F, -0.5F, 0.0F, 0.0F, -0.2182F));

		PartDefinition cube_r25 = left_arm
				.addOrReplaceChild("cube_r25",
						CubeListBuilder.create().texOffs(160, 69).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 3.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(6.5F, -3.0F, 0.5F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r26 = left_arm
				.addOrReplaceChild("cube_r26",
						CubeListBuilder.create().texOffs(156, 158).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(4.0F, -1.5F, 0.0F, 0.0F, 0.0F, 0.829F));

		PartDefinition cube_r27 = left_arm
				.addOrReplaceChild("cube_r27",
						CubeListBuilder.create().texOffs(150, 127).addBox(-1.0F, -4.0F, -3.0F, 7.0F, 4.0F, 6.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition la_down = left_arm
				.addOrReplaceChild("la_down",
						CubeListBuilder.create().texOffs(154, 48).addBox(-2.0F, 0.5F, -2.0F, 4.0F, 7.0F, 4.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(2.0F, 6.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r28 = la_down.addOrReplaceChild("cube_r28",
				CubeListBuilder.create().texOffs(158, 5).addBox(0.0F, 0.0F, 0.0F, 5.0F, 3.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.5F, -0.5F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition spear = la_down.addOrReplaceChild("spear",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-1.0F, 0.0F, -38.0F, 1.0F, 1.0F, 62.0F, new CubeDeformation(0.0F)).texOffs(156, 36)
						.addBox(-1.0F, -1.0F, -32.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(160, 63)
						.addBox(-1.0F, -2.0F, -24.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(158, 102)
						.addBox(-1.0F, -0.5F, -36.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 5.5F, 0.0F));

		PartDefinition right_leg = all.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(84, 156).addBox(-1.5F, 13.0F, -6.0F, 4.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.5F, 11.0F, -1.5F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r29 = right_leg.addOrReplaceChild(
				"cube_r29", CubeListBuilder.create().texOffs(132, 76).addBox(-3.0F, -1.6756F, -7.2627F, 6.0F, 1.0F,
						8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -1.5F, -3.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r30 = right_leg.addOrReplaceChild("cube_r30",
				CubeListBuilder.create().texOffs(24, 140).addBox(-1.0F, -12.0F, -1.0F, 5.0F, 12.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, 5.5F, -10.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r31 = right_leg.addOrReplaceChild("cube_r31",
				CubeListBuilder.create().texOffs(100, 147).addBox(-1.0F, -7.0F, 0.0F, 4.0F, 7.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 11.0F, -1.5F, 1.0036F, 0.0F, 0.0F));

		PartDefinition cube_r32 = right_leg.addOrReplaceChild("cube_r32",
				CubeListBuilder.create().texOffs(28, 157).addBox(-1.0F, -7.0F, 0.0F, 3.0F, 7.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 13.5F, -6.0F, -0.5672F, 0.0F, 0.0F));

		PartDefinition left_leg = all.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(156, 152).addBox(-2.5F, 13.0F, -6.0F, 4.0F, 3.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(3.5F, 11.0F, -1.5F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r33 = left_leg.addOrReplaceChild("cube_r33",
				CubeListBuilder.create().texOffs(136, 143).addBox(-3.0F, -1.6756F, -7.2627F, 6.0F, 1.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -1.5F, -3.0F, 0.829F, 0.0F, 0.0F));

		PartDefinition cube_r34 = left_leg.addOrReplaceChild("cube_r34",
				CubeListBuilder.create().texOffs(116, 143).addBox(-4.0F, -12.0F, -1.0F, 5.0F, 12.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 5.5F, -10.0F, -0.8727F, 0.0F, 0.0F));

		PartDefinition cube_r35 = left_leg.addOrReplaceChild("cube_r35",
				CubeListBuilder.create().texOffs(0, 155).addBox(-3.0F, -7.0F, 0.0F, 4.0F, 7.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 11.0F, -1.5F, 1.0036F, 0.0F, 0.0F));

		PartDefinition cube_r36 = left_leg.addOrReplaceChild("cube_r36",
				CubeListBuilder.create().texOffs(158, 85).addBox(-2.0F, -7.0F, 0.0F, 3.0F, 7.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 13.5F, -6.0F, -0.5672F, 0.0F, 0.0F));

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