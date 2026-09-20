// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelsteed_of_slaanesh<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "steed_of_slaanesh"), "main");
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart til;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

	public Modelsteed_of_slaanesh(ModelPart root) {
		this.body = root.getChild("body");
		this.tail = this.body.getChild("tail");
		this.neck = this.body.getChild("neck");
		this.head = this.neck.getChild("head");
		this.til = this.head.getChild("til");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-5.0F, -13.5F, -11.5F, 10.0F, 13.0F, 20.0F, new CubeDeformation(0.0F)).texOffs(0, 33)
						.addBox(0.0F, -15.0F, -11.5F, 0.0F, 12.0F, 20.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 9.0F, 4.5F, -0.0436F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail",
				CubeListBuilder.create().texOffs(40, 33)
						.addBox(-4.0F, -8.0F, 0.0F, 8.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(62, 78)
						.addBox(0.0F, -10.0F, 0.0F, 0.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(40, 55)
						.addBox(-2.0F, -6.5F, 11.5F, 4.0F, 7.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(86, 78)
						.addBox(0.0F, -8.5F, 11.5F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(32, 75)
						.addBox(-1.0F, -5.5F, 24.0F, 2.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(0, 84)
						.addBox(-1.0F, -5.5F, 37.0F, 2.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(0, 65)
						.addBox(0.0F, -7.5F, 24.0F, 0.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -4.5F, 8.5F, 0.0436F, 0.0F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck",
				CubeListBuilder.create().texOffs(88, 0)
						.addBox(-2.0F, -3.5F, -16.5F, 4.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(80, 23)
						.addBox(-3.0F, -4.0F, -8.0F, 6.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(60, 23)
						.addBox(0.0F, -5.0F, -8.0F, 0.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -9.0F, -10.5F, 0.1309F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(),
				PartPose.offset(0.0F, -2.5F, -16.5F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(102, 63).mirror()
						.addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(102, 63).addBox(-4.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 2.0F, -0.5F, 0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2",
				CubeListBuilder.create().texOffs(62, 75).mirror()
						.addBox(-1.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(62, 75).addBox(-6.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(2.0F, 2.5F, -2.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r3 = head
				.addOrReplaceChild("cube_r3",
						CubeListBuilder.create().texOffs(80, 40).addBox(-1.5F, 0.0F, -10.0F, 3.0F, 4.0F, 11.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition til = head.addOrReplaceChild("til", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r4 = til
				.addOrReplaceChild("cube_r4",
						CubeListBuilder.create().texOffs(0, 99).addBox(-0.5F, 0.0F, -10.0F, 1.0F, 0.0F, 11.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 5.0F, -6.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(),
				PartPose.offset(-8.0F, 2.0F, 8.0F));

		PartDefinition cube_r5 = right_leg.addOrReplaceChild("cube_r5",
				CubeListBuilder.create().texOffs(24, 99).addBox(0.0F, -15.0F, -7.0F, 0.0F, 11.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.5F, -6.5F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r6 = right_leg.addOrReplaceChild("cube_r6",
				CubeListBuilder.create().texOffs(60, 0).addBox(-3.0F, -14.0F, -7.0F, 6.0F, 15.0F, 8.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 10.0F, -5.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r7 = right_leg.addOrReplaceChild("cube_r7",
				CubeListBuilder.create().texOffs(76, 23).addBox(0.0F, -5.0F, 9.0F, 0.0F, 5.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 8.5F, -5.5F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r8 = right_leg.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(28, 92).addBox(-2.0F, -5.0F, 2.0F, 4.0F, 5.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 9.0F, -7.5F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r9 = right_leg.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(54, 100).addBox(-2.0F, -10.0F, 0.0F, 3.0F, 10.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 18.0F, -5.5F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r10 = right_leg.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(54, 96).addBox(-1.0F, -2.0F, -3.5F, 1.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 22.0F, -4.0F, 0.2618F, -0.1745F, 0.0F));

		PartDefinition cube_r11 = right_leg.addOrReplaceChild("cube_r11",
				CubeListBuilder.create().texOffs(54, 92).addBox(0.0F, -2.0F, -3.5F, 1.0F, 2.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.5F, 22.0F, -4.0F, 0.2618F, 0.1745F, 0.0F));

		PartDefinition cube_r12 = right_leg.addOrReplaceChild("cube_r12",
				CubeListBuilder.create().texOffs(32, 65).addBox(-1.0F, -2.0F, -4.5F, 1.0F, 2.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 22.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r13 = right_leg.addOrReplaceChild("cube_r13",
				CubeListBuilder.create().texOffs(88, 15).addBox(-3.0F, -4.0F, 0.0F, 3.0F, 4.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 22.0F, -6.5F, -0.2182F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(),
				PartPose.offset(8.0F, 2.0F, 8.0F));

		PartDefinition cube_r14 = left_leg.addOrReplaceChild("cube_r14",
				CubeListBuilder.create().texOffs(24, 99).mirror()
						.addBox(0.0F, -15.0F, -7.0F, 0.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 8.5F, -6.5F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r15 = left_leg.addOrReplaceChild("cube_r15",
				CubeListBuilder.create().texOffs(60, 0).mirror()
						.addBox(-3.0F, -14.0F, -7.0F, 6.0F, 15.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 10.0F, -5.0F, -0.7418F, 0.0F, 0.0F));

		PartDefinition cube_r16 = left_leg.addOrReplaceChild("cube_r16",
				CubeListBuilder.create().texOffs(76, 23).mirror()
						.addBox(0.0F, -5.0F, 9.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 8.5F, -5.5F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r17 = left_leg.addOrReplaceChild("cube_r17",
				CubeListBuilder.create().texOffs(28, 92).mirror()
						.addBox(-2.0F, -5.0F, 2.0F, 4.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(0.0F, 9.0F, -7.5F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r18 = left_leg.addOrReplaceChild("cube_r18",
				CubeListBuilder.create().texOffs(54, 100).mirror()
						.addBox(-1.0F, -10.0F, 0.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 18.0F, -5.5F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r19 = left_leg.addOrReplaceChild("cube_r19",
				CubeListBuilder.create().texOffs(54, 96).mirror()
						.addBox(0.0F, -2.0F, -3.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-1.5F, 22.0F, -4.0F, 0.2618F, 0.1745F, 0.0F));

		PartDefinition cube_r20 = left_leg.addOrReplaceChild("cube_r20",
				CubeListBuilder.create().texOffs(54, 92).mirror()
						.addBox(-1.0F, -2.0F, -3.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(1.5F, 22.0F, -4.0F, 0.2618F, -0.1745F, 0.0F));

		PartDefinition cube_r21 = left_leg.addOrReplaceChild("cube_r21",
				CubeListBuilder.create().texOffs(32, 65).mirror()
						.addBox(0.0F, -2.0F, -4.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.5F, 22.0F, -4.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r22 = left_leg.addOrReplaceChild("cube_r22",
				CubeListBuilder.create().texOffs(88, 15).mirror()
						.addBox(0.0F, -4.0F, 0.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-1.5F, 22.0F, -6.5F, -0.2182F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}