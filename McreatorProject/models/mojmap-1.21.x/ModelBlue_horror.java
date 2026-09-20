// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelBlue_horror<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "blue_horror"), "main");
	private final ModelPart all;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart body;
	private final ModelPart uphead;
	private final ModelPart right_arm;
	private final ModelPart right_arm2;
	private final ModelPart blade;
	private final ModelPart left_arm;
	private final ModelPart blade2;

	public ModelBlue_horror(ModelPart root) {
		this.all = root.getChild("all");
		this.right_leg = this.all.getChild("right_leg");
		this.left_leg = this.all.getChild("left_leg");
		this.body = this.all.getChild("body");
		this.uphead = this.body.getChild("uphead");
		this.right_arm = this.body.getChild("right_arm");
		this.right_arm2 = this.body.getChild("right_arm2");
		this.blade = this.right_arm2.getChild("blade");
		this.left_arm = this.body.getChild("left_arm");
		this.blade2 = this.left_arm.getChild("blade2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(),
				PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition right_leg = all.addOrReplaceChild("right_leg",
				CubeListBuilder.create().texOffs(56, 52).addBox(-2.0F, 12.5F, 2.0F, 4.0F, 2.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.0F, -14.5F, -4.0F, 0.0F, 0.1745F, 0.0F));

		PartDefinition cube_r1 = right_leg.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(36, 25).addBox(-2.0F, -1.0F, 2.0F, 5.0F, 5.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, -1.5F, -1.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r2 = right_leg
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(28, 39).addBox(-1.5F, -3.0F, -9.0F, 3.0F, 3.0F, 7.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 7.0F, 7.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r3 = right_leg.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(60, 10).addBox(-1.0F, -5.0F, -2.0F, 3.0F, 5.0F, 2.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 12.5F, 4.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition left_leg = all.addOrReplaceChild("left_leg",
				CubeListBuilder.create().texOffs(56, 57).addBox(-2.0F, 12.5F, 2.0F, 4.0F, 2.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.0F, -14.5F, -4.0F, 0.0F, -0.1745F, 0.0F));

		PartDefinition cube_r4 = left_leg.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(0, 38).addBox(-3.0F, -1.0F, 2.0F, 5.0F, 5.0F, 9.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, -1.5F, -1.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r5 = left_leg
				.addOrReplaceChild("cube_r5",
						CubeListBuilder.create().texOffs(44, 0).addBox(-1.5F, -3.0F, -9.0F, 3.0F, 3.0F, 7.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 7.0F, 7.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r6 = left_leg
				.addOrReplaceChild("cube_r6",
						CubeListBuilder.create().texOffs(60, 17).addBox(-2.0F, -5.0F, -2.0F, 3.0F, 5.0F, 2.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.5F, 12.5F, 4.0F, 0.8727F, 0.0F, 0.0F));

		PartDefinition body = all.addOrReplaceChild("body",
				CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -12.0F, -1.5F, 12.0F, 15.0F, 10.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -17.0F, -6.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition uphead = body.addOrReplaceChild("uphead", CubeListBuilder.create(),
				PartPose.offset(0.0F, -14.0F, 9.5F));

		PartDefinition cube_r7 = uphead
				.addOrReplaceChild("cube_r7",
						CubeListBuilder.create().texOffs(0, 25).addBox(-5.0F, -5.0F, -10.0F, 10.0F, 5.0F, 8.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 2.0F, 0.5F, 0.2182F, 0.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm",
				CubeListBuilder.create().texOffs(44, 10).addBox(-2.5F, 0.0F, -1.5F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.5F, -9.5F, 4.0F, 0.0832F, 0.0262F, -0.9588F));

		PartDefinition cube_r8 = right_arm.addOrReplaceChild("cube_r8",
				CubeListBuilder.create().texOffs(0, 52).addBox(-2.5F, -0.5F, -1.5F, 3.0F, 11.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.5F, 8.0F, 0.0F, 1.0906F, 0.0426F, -0.0094F));

		PartDefinition right_arm2 = body.addOrReplaceChild("right_arm2",
				CubeListBuilder.create().texOffs(48, 39).addBox(-3.0645F, -1.864F, -1.0452F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(6.0F, -4.0F, 4.0F, -1.9145F, -1.232F, 0.5564F));

		PartDefinition cube_r9 = right_arm2.addOrReplaceChild("cube_r9",
				CubeListBuilder.create().texOffs(12, 52).addBox(-2.5F, -0.5F, -1.5F, 3.0F, 11.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.0645F, 6.136F, 0.4548F, 1.0036F, 0.5375F, -0.3026F));

		PartDefinition blade = right_arm2.addOrReplaceChild("blade",
				CubeListBuilder.create().texOffs(24, 62)
						.addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 38)
						.addBox(3.0F, 0.0F, 0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 24)
						.addBox(6.0F, 0.0F, -1.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(50, 24)
						.addBox(9.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(56, 62)
						.addBox(2.0F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(64, 0)
						.addBox(5.0F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(64, 3)
						.addBox(8.0F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(4.9355F, 9.636F, 6.9548F, -0.5727F, 0.5113F, -0.3242F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm",
				CubeListBuilder.create().texOffs(28, 49).addBox(-1.5F, 0.0F, -1.5F, 4.0F, 9.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.0F, -8.0F, 1.5F, 0.2054F, 0.0741F, 0.6185F));

		PartDefinition cube_r10 = left_arm.addOrReplaceChild("cube_r10",
				CubeListBuilder.create().texOffs(44, 52).addBox(-0.5F, -0.5F, -1.5F, 3.0F, 11.0F, 3.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 8.0F, 0.0F, 0.8288F, -0.0426F, 0.0094F));

		PartDefinition blade2 = left_arm.addOrReplaceChild("blade2",
				CubeListBuilder.create().texOffs(34, 62)
						.addBox(-2.0F, -0.5F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(44, 23)
						.addBox(2.0F, 0.0F, -0.5F, 7.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 13.5F, 7.5F, 0.0269F, -0.754F, -1.5973F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.left_leg.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
		this.right_leg.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
		this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.right_arm2.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
	}
}