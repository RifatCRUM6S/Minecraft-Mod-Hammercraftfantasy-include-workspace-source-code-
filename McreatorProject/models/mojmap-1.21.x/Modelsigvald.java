// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelsigvald<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "sigvald"), "main");
	private final ModelPart all;
	private final ModelPart body;
	private final ModelPart cloak;
	private final ModelPart balbag;
	private final ModelPart head;
	private final ModelPart hair;
	private final ModelPart right_arm;
	private final ModelPart ra_down;
	private final ModelPart shield;
	private final ModelPart left_arm;
	private final ModelPart la_down;
	private final ModelPart sword;
	private final ModelPart legs;
	private final ModelPart right_leg;
	private final ModelPart rl_down;
	private final ModelPart left_leg;
	private final ModelPart rl_down2;

	public Modelsigvald(ModelPart root) {
		this.all = root.getChild("all");
		this.body = this.all.getChild("body");
		this.cloak = this.body.getChild("cloak");
		this.balbag = this.body.getChild("balbag");
		this.head = this.body.getChild("head");
		this.hair = this.head.getChild("hair");
		this.right_arm = this.body.getChild("right_arm");
		this.ra_down = this.right_arm.getChild("ra_down");
		this.shield = this.ra_down.getChild("shield");
		this.left_arm = this.body.getChild("left_arm");
		this.la_down = this.left_arm.getChild("la_down");
		this.sword = this.la_down.getChild("sword");
		this.legs = this.all.getChild("legs");
		this.right_leg = this.legs.getChild("right_leg");
		this.rl_down = this.right_leg.getChild("rl_down");
		this.left_leg = this.legs.getChild("left_leg");
		this.rl_down2 = this.left_leg.getChild("rl_down2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(),
				PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition body = all.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(74, 81)
						.addBox(-4.0F, -8.0F, -2.5F, 8.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(72, 11)
						.addBox(-4.5F, -8.0F, -3.0F, 9.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(72, 0)
						.addBox(-5.0F, -13.0F, -3.0F, 10.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(26, 56)
						.addBox(-5.5F, -14.0F, -3.5F, 11.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cloak = body.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(26, 70).addBox(-6.0F,
				-0.5F, 0.5F, 12.0F, 20.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.5F, 3.0F));

		PartDefinition balbag = body.addOrReplaceChild("balbag",
				CubeListBuilder.create().texOffs(86, 72).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 9.0F, 0.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -1.0F, -3.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(62, 56).addBox(-4.0F,
				-8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -8.5F,
				-4.5F, 9.0F, 20.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(74, 33)
						.addBox(-7.0F, -2.5F, -3.5F, 8.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(104, 0)
						.addBox(-4.0F, 1.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(52, 72)
						.addBox(-8.0F, 0.5F, -4.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-6.0F, -14.0F, 0.0F));

		PartDefinition ra_down = right_arm.addOrReplaceChild("ra_down",
				CubeListBuilder.create().texOffs(94, 93)
						.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(22, 91)
						.addBox(-2.5F, -2.0F, -4.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-2.0F, 8.5F, 2.0F));

		PartDefinition shield = ra_down.addOrReplaceChild("shield",
				CubeListBuilder.create().texOffs(42, 28)
						.addBox(-6.0F, 9.5F, -7.0F, 2.0F, 14.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(38, 0)
						.addBox(-5.0F, 10.5F, -8.0F, 1.0F, 12.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(0, 52)
						.addBox(-5.0F, 8.5F, -6.0F, 1.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, -8.5F, -2.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(74, 43)
						.addBox(-1.0F, -2.5F, -3.5F, 8.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(104, 33)
						.addBox(0.0F, 1.5F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(74, 24)
						.addBox(-1.0F, 0.5F, -4.0F, 9.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(6.0F, -14.0F, 0.0F));

		PartDefinition la_down = left_arm.addOrReplaceChild("la_down",
				CubeListBuilder.create().texOffs(0, 97)
						.addBox(-2.0F, 0.0F, -4.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(74, 93)
						.addBox(-2.5F, -2.0F, -4.5F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(2.0F, 8.5F, 2.0F));

		PartDefinition sword = la_down.addOrReplaceChild("sword",
				CubeListBuilder.create().texOffs(104, 44)
						.addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 30)
						.addBox(0.0F, -0.5F, -25.5F, 0.0F, 1.0F, 21.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 8.0F, -2.0F));

		PartDefinition cube_r1 = sword
				.addOrReplaceChild("cube_r1",
						CubeListBuilder.create().texOffs(22, 80).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 2.5F, -1.5F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = sword
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(42, 91).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 4.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r3 = sword
				.addOrReplaceChild("cube_r3",
						CubeListBuilder.create().texOffs(22, 86).addBox(-0.5F, -1.5F, 0.0F, 1.0F, 4.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, -4.5F, 0.5672F, 0.0F, 0.0F));

		PartDefinition legs = all.addOrReplaceChild("legs", CubeListBuilder.create(),
				PartPose.offset(-2.0F, -1.0F, 0.5F));

		PartDefinition right_leg = legs.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(102, 11)
						.addBox(-2.5F, 0.0F, -2.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(94, 65)
						.addBox(-3.0F, 0.0F, -3.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rl_down = right_leg.addOrReplaceChild("rl_down",
				CubeListBuilder.create().texOffs(58, 98)
						.addBox(-2.5F, 0.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(52, 81)
						.addBox(-3.0F, -3.0F, -1.5F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 7.0F, -2.5F));

		PartDefinition left_leg = legs.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(100, 77)
						.addBox(-1.5F, 0.0F, -2.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(94, 53)
						.addBox(-2.0F, 0.0F, -3.0F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offset(4.0F, 0.0F, 0.0F));

		PartDefinition rl_down2 = left_leg.addOrReplaceChild("rl_down2",
				CubeListBuilder.create().texOffs(42, 98)
						.addBox(-1.5F, 0.0F, 0.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 80)
						.addBox(-2.0F, -3.0F, -1.5F, 5.0F, 11.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 7.0F, -2.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
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