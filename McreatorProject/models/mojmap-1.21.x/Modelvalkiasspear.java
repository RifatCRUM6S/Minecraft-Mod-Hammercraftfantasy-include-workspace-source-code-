// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelvalkiasspear<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "valkiasspear"), "main");
	private final ModelPart spear;

	public Modelvalkiasspear(ModelPart root) {
		this.spear = root.getChild("spear");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition spear = partdefinition.addOrReplaceChild("spear",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-0.5F, -0.5F, -31.0F, 1.0F, 1.0F, 62.0F, new CubeDeformation(0.0F)).texOffs(0, 63)
						.addBox(-0.5F, -1.5F, -25.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).texOffs(26, 63)
						.addBox(-0.5F, -2.5F, -17.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 63)
						.addBox(-0.5F, -1.0F, -29.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		spear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}