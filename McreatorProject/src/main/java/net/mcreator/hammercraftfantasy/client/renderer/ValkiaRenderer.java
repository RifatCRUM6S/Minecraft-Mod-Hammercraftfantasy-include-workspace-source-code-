package net.mcreator.hammercraftfantasy.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import net.mcreator.hammercraftfantasy.entity.ValkiaEntity;
import net.mcreator.hammercraftfantasy.client.model.animations.valkiaAnimation;
import net.mcreator.hammercraftfantasy.client.model.Modelvalkia;

public class ValkiaRenderer extends MobRenderer<ValkiaEntity, Modelvalkia<ValkiaEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.parse("hammercraftfantasy:textures/entities/valkia_skin.png");

    public ValkiaRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modelvalkia.LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(ValkiaEntity entity) {
        return entityTexture;
    }

    private static final class AnimatedModel extends Modelvalkia<ValkiaEntity> {
        private final ModelPart root;
        private final HierarchicalModel<ValkiaEntity> animator = new HierarchicalModel<ValkiaEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(ValkiaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);

                // 基础/过渡状态
                this.animate(entity.animOnAir, valkiaAnimation.onair, ageInTicks, 1f);
                this.animate(entity.animOnLand, valkiaAnimation.onland, ageInTicks, 1f);
                this.animate(entity.animLandToAir, valkiaAnimation.landtoair, ageInTicks, 1f);
                this.animate(entity.animAirToLand, valkiaAnimation.airtoland, ageInTicks, 1f);

                // 移动动画
                this.animate(entity.animFlyingMoving, valkiaAnimation.flyingmoving, ageInTicks, 1f);
                this.animate(entity.animGroundMoving, valkiaAnimation.groundmoving, ageInTicks, 1f);

                // 飞行技能动画
                this.animate(entity.animFlyingCharge, valkiaAnimation.flyingcharge, ageInTicks, 1f);
                this.animate(entity.animFlyingRangeAttack, valkiaAnimation.flyingrangeattack, ageInTicks, 1f);
                this.animate(entity.animFlyingHitGround, valkiaAnimation.flyinghitground, ageInTicks, 1f);

                // 陆行技能动画
                this.animate(entity.animGroundAttack1, valkiaAnimation.groundattack1, ageInTicks, 1f);
                this.animate(entity.animGroundAttack2, valkiaAnimation.groundattack2, ageInTicks, 1f);
                this.animate(entity.animGroundCharge, valkiaAnimation.groundcharge, ageInTicks, 1f);
                this.animate(entity.animGroundDefend, valkiaAnimation.grounddefend, ageInTicks, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(ValkiaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}