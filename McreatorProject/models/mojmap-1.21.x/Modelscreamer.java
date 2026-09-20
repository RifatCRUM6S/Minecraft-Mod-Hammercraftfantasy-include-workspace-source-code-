// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelscreamer<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "screamer"), "main");
	private final ModelPart body;
	private final ModelPart left_wing;
	private final ModelPart right_wing;
	private final ModelPart head;
	private final ModelPart tail_1;
	private final ModelPart tail_2;
	private final ModelPart tail_3;
	private final ModelPart tail_4;

	public Modelscreamer(ModelPart root) {
		this.body = root.getChild("body");
		this.left_wing = this.body.getChild("left_wing");
		this.right_wing = this.body.getChild("right_wing");
		this.head = this.body.getChild("head");
		this.tail_1 = this.body.getChild("tail_1");
		this.tail_2 = this.tail_1.getChild("tail_2");
		this.tail_3 = this.tail_2.getChild("tail_3");
		this.tail_4 = this.tail_3.getChild("tail_4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-7.0F, -8.0F, -8.0F, 14.0F, 5.0F, 45.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, -14.5F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing",
				CubeListBuilder.create().texOffs(118, 0)
						.addBox(0.0F, -1.0F, -1.0F, 32.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)).texOffs(124, 60)
						.addBox(0.0F, -3.0F, 0.0F, 30.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -4.5F, -9.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r1 = left_wing.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(96, 177).addBox(0.0F, -1.0F, 2.5F, 1.0F, 1.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(28.5F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r2 = left_wing.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(168, 177).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(25.5F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r3 = left_wing.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(160, 177).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(23.5F, -0.5F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r4 = left_wing.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(152, 177).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, -0.5F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r5 = left_wing.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(144, 177).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -0.5F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r6 = left_wing.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(136, 177).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(11.0F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r7 = left_wing.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(24, 174).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(15.0F, -0.5F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r8 = left_wing.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(166, 148).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(20.0F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r9 = left_wing.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(32, 160).addBox(1.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(22.0F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r10 = left_wing.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(46, 172).addBox(0.0F, -1.0F, 1.5F, 1.0F, 1.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(18.5F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r11 = left_wing.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(86, 177).addBox(1.0F, -1.0F, 2.5F, 1.0F, 1.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(13.0F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r12 = left_wing
				.addOrReplaceChild("cube_r12",
						CubeListBuilder.create().texOffs(112, 144).addBox(0.0F, -1.0F, 2.5F, 2.0F, 2.0F, 4.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(9.0F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r13 = left_wing.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(132, 166).addBox(1.0F, -1.0F, 1.5F, 4.0F, 2.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(36.0F, 0.5F, 29.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r14 = left_wing.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(144, 152).addBox(0.0F, -2.0F, -1.5F, 5.0F, 3.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(34.0F, 0.5F, 22.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r15 = left_wing.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(112, 152).addBox(0.0F, -2.0F, -1.5F, 5.0F, 3.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(24.0F, 0.5F, 30.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r16 = left_wing.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(78, 138).addBox(1.0F, -1.0F, 1.5F, 4.0F, 2.0F, 13.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(26.0F, 0.5F, 37.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r17 = left_wing.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(124, 78).addBox(1.0F, -1.0F, -1.5F, 4.0F, 2.0F, 18.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(20.0F, 0.5F, 46.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r18 = left_wing.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(106, 166).addBox(0.0F, -2.0F, -1.5F, 5.0F, 3.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(18.0F, 0.5F, 39.5F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r19 = left_wing
				.addOrReplaceChild("cube_r19",
						CubeListBuilder.create().texOffs(168, 94).addBox(0.0F, -1.0F, 1.5F, 2.0F, 2.0F, 5.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(4.0F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r20 = left_wing
				.addOrReplaceChild("cube_r20",
						CubeListBuilder.create().texOffs(112, 138).addBox(0.0F, -1.0F, 1.5F, 1.0F, 1.0F, 5.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition l_r1 = left_wing.addOrReplaceChild("l_r1",
				CubeListBuilder.create().texOffs(0, 50).addBox(0.0F, -0.5F, -0.5F, 20.0F, 2.0F, 42.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(32.0F, -1.0F, -0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing",
				CubeListBuilder.create().texOffs(124, 30)
						.addBox(-32.0F, -1.0F, -1.0F, 32.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)).texOffs(124, 69)
						.addBox(-30.0F, -3.0F, 0.0F, 30.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -4.5F, -9.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r21 = right_wing.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(126, 177).addBox(-1.0F, -1.0F, 2.5F, 1.0F, 1.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-28.5F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r22 = right_wing.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(32, 179).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-25.5F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r23 = right_wing.addOrReplaceChild("cube_r23",
				CubeListBuilder.create().texOffs(78, 178).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-23.5F, -0.5F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r24 = right_wing.addOrReplaceChild("cube_r24",
				CubeListBuilder.create().texOffs(70, 178).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.0F, -0.5F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r25 = right_wing.addOrReplaceChild("cube_r25",
				CubeListBuilder.create().texOffs(62, 178).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -0.5F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r26 = right_wing.addOrReplaceChild("cube_r26",
				CubeListBuilder.create().texOffs(54, 178).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-11.0F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r27 = right_wing.addOrReplaceChild("cube_r27",
				CubeListBuilder.create().texOffs(46, 178).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-15.0F, -0.5F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r28 = right_wing.addOrReplaceChild("cube_r28",
				CubeListBuilder.create().texOffs(24, 178).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-20.0F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r29 = right_wing.addOrReplaceChild("cube_r29",
				CubeListBuilder.create().texOffs(176, 177).addBox(-2.0F, 0.0F, 3.5F, 1.0F, 1.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-22.0F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r30 = right_wing.addOrReplaceChild("cube_r30",
				CubeListBuilder.create().texOffs(176, 160).addBox(-1.0F, -1.0F, 1.5F, 1.0F, 1.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-18.5F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r31 = right_wing.addOrReplaceChild("cube_r31",
				CubeListBuilder.create().texOffs(116, 177).addBox(-2.0F, -1.0F, 2.5F, 1.0F, 1.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-13.0F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r32 = right_wing.addOrReplaceChild("cube_r32",
				CubeListBuilder.create().texOffs(176, 154).addBox(-2.0F, -1.0F, 2.5F, 2.0F, 2.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-9.0F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r33 = right_wing.addOrReplaceChild("cube_r33",
				CubeListBuilder.create().texOffs(158, 166).addBox(-5.0F, -1.0F, 1.5F, 4.0F, 2.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-36.0F, 0.5F, 29.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r34 = right_wing.addOrReplaceChild("cube_r34",
				CubeListBuilder.create().texOffs(0, 160).addBox(-5.0F, -2.0F, -1.5F, 5.0F, 3.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-34.0F, 0.5F, 22.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r35 = right_wing.addOrReplaceChild("cube_r35",
				CubeListBuilder.create().texOffs(74, 153).addBox(-5.0F, -2.0F, -1.5F, 5.0F, 3.0F, 11.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-24.0F, 0.5F, 30.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r36 = right_wing.addOrReplaceChild("cube_r36",
				CubeListBuilder.create().texOffs(40, 149).addBox(-5.0F, -1.0F, 1.5F, 4.0F, 2.0F, 13.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-26.0F, 0.5F, 37.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r37 = right_wing.addOrReplaceChild("cube_r37",
				CubeListBuilder.create().texOffs(124, 98).addBox(-5.0F, -1.0F, -1.5F, 4.0F, 2.0F, 18.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-20.0F, 0.5F, 46.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r38 = right_wing.addOrReplaceChild("cube_r38",
				CubeListBuilder.create().texOffs(166, 137).addBox(-5.0F, -2.0F, -1.5F, 5.0F, 3.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-18.0F, 0.5F, 39.5F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r39 = right_wing.addOrReplaceChild("cube_r39",
				CubeListBuilder.create().texOffs(32, 172).addBox(-2.0F, -1.0F, 1.5F, 2.0F, 2.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r40 = right_wing.addOrReplaceChild("cube_r40",
				CubeListBuilder.create().texOffs(176, 148).addBox(-1.0F, -1.0F, 1.5F, 1.0F, 1.0F, 5.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r41 = right_wing.addOrReplaceChild("cube_r41",
				CubeListBuilder.create().texOffs(0, 94).addBox(-20.0F, -0.5F, -0.5F, 20.0F, 2.0F, 42.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-32.0F, -1.0F, -0.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(32, 164)
						.addBox(-6.0F, -7.5F, -16.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 174)
						.addBox(-6.0F, -6.5F, -19.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(168, 101)
						.addBox(-5.5F, -6.0F, -24.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(168, 108)
						.addBox(-5.0F, -5.5F, -30.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(12, 174)
						.addBox(-2.5F, -7.0F, -24.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(168, 122)
						.addBox(-3.0F, -7.5F, -20.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(86, 167)
						.addBox(-2.0F, -6.5F, -33.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(106, 177)
						.addBox(1.0F, -5.5F, -30.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(168, 129)
						.addBox(0.0F, -6.5F, -20.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(168, 86)
						.addBox(0.5F, -6.0F, -26.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(168, 78)
						.addBox(4.0F, -5.5F, -33.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(168, 115)
						.addBox(3.5F, -6.5F, -28.5F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(64, 167)
						.addBox(3.0F, -7.0F, -24.0F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tail_1 = body.addOrReplaceChild("tail_1",
				CubeListBuilder.create().texOffs(0, 150)
						.addBox(-6.5F, -3.5F, 0.0F, 13.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(40, 138)
						.addBox(-5.5F, -2.5F, 5.5F, 11.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -4.0F, 37.0F));

		PartDefinition tail_2 = tail_1.addOrReplaceChild("tail_2", CubeListBuilder.create().texOffs(0, 138)
				.addBox(-5.0F, -1.5F, 0.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -0.5F, 13.5F));

		PartDefinition tail_3 = tail_2.addOrReplaceChild("tail_3", CubeListBuilder.create().texOffs(124, 137)
				.addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -0.5F, 10.5F));

		PartDefinition tail_4 = tail_3.addOrReplaceChild("tail_4", CubeListBuilder.create().texOffs(124, 118).addBox(
				-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.5F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}