// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modeldemonsteel_armor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "demonsteel_armor"), "main");
	private final ModelPart armor;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_shoe;
	private final ModelPart right_shoe;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart left_arm;
	private final ModelPart right_arm;

	public Modeldemonsteel_armor(ModelPart root) {
		this.armor = root.getChild("armor");
		this.head = this.armor.getChild("head");
		this.body = this.armor.getChild("body");
		this.left_shoe = this.armor.getChild("left_shoe");
		this.right_shoe = this.armor.getChild("right_shoe");
		this.left_leg = this.armor.getChild("left_leg");
		this.right_leg = this.armor.getChild("right_leg");
		this.left_arm = this.armor.getChild("left_arm");
		this.right_arm = this.armor.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition armor = partdefinition.addOrReplaceChild("armor", CubeListBuilder.create(),
				PartPose.offset(0.0F, 23.3F, 0.0F));

		PartDefinition head = armor.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -7.7F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.75F)).texOffs(0, 16)
						.addBox(-4.0F, -7.7F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)),
				PartPose.offset(0.0F, -24.3F, 0.0F));

		PartDefinition body = armor.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F,
				0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition left_shoe = armor.addOrReplaceChild("left_shoe", CubeListBuilder.create().texOffs(24, 32)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
				PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition right_shoe = armor.addOrReplaceChild("right_shoe", CubeListBuilder.create().texOffs(40, 32)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
				PartPose.offset(-1.9F, -12.0F, -0.1F));

		PartDefinition left_leg = armor.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
				PartPose.offset(1.9F, -12.0F, 0.0F));

		PartDefinition right_leg = armor.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 0)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
				PartPose.offset(-1.9F, -12.0F, -0.1F));

		PartDefinition left_arm = armor.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 0)
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
				PartPose.offset(5.0F, -22.0F, 0.0F));

		PartDefinition right_arm = armor.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 16)
				.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)),
				PartPose.offset(-5.0F, -22.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		armor.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}