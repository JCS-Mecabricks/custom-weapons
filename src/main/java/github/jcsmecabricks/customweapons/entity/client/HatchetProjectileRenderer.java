package github.jcsmecabricks.customweapons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

public class HatchetProjectileRenderer extends EntityRenderer<HatchetProjectileEntity, HatchetProjectileRenderState> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/hatchet/hatchet.png");
    protected HatchetProjectileModel model;
    public HatchetProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new HatchetProjectileModel(context.bakeLayer(HatchetProjectileModel.HATCHET));
    }

    @Override
    public void submit(HatchetProjectileRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector,
                       CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
        poseStack.pushPose();

        if (!state.isGrounded) {
            poseStack.rotate(Axis.YP.rotationDegrees(state.yaw));
            poseStack.rotate(Axis.XP.rotationDegrees(state.spinRotation));
            poseStack.translate(0, -1.0f, 0);
        } else {
            poseStack.rotate(Axis.YP.rotationDegrees(state.groundedOffset.y));
            poseStack.rotate(Axis.XP.rotationDegrees(state.groundedOffset.x));
            poseStack.translate(0, -1.0f, 0);
        }

        submitNodeCollector.order(1).submitModel(this.model, state, poseStack, RenderTypes.entitySolid(getTextureLocation()),
                state.lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor);
        poseStack.popPose();
    }

    @Override
    public void extractRenderState(HatchetProjectileEntity hatchetProjectile, HatchetProjectileRenderState hatchetProjectileRenderState, float f) {
        super.extractRenderState(hatchetProjectile, hatchetProjectileRenderState, f);
        hatchetProjectileRenderState.yaw = hatchetProjectile.getYRot(f);
        hatchetProjectileRenderState.pitch = hatchetProjectile.getXRot(f);
        hatchetProjectileRenderState.isGrounded = hatchetProjectile.isGrounded();
        hatchetProjectileRenderState.getRenderingRotation = hatchetProjectile.getRenderingRotation();
        hatchetProjectileRenderState.groundedPitch = hatchetProjectile.groundedOffset.u();
        hatchetProjectileRenderState.groundedYaw = hatchetProjectile.groundedOffset.v();
    }

    public Identifier getTextureLocation() {
        return Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/hatchet/hatchet.png");
    }

    @Override
    public HatchetProjectileRenderState createRenderState() {
        return new HatchetProjectileRenderState();
    }
}
