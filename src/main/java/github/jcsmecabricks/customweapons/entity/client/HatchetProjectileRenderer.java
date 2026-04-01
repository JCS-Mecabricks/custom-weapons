package github.jcsmecabricks.customweapons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import java.util.List;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;

public class HatchetProjectileRenderer extends EntityRenderer<HatchetProjectileEntity, HatchetProjectileRenderState> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/hatchet/hatchet.png");
    protected HatchetProjectileModel model;
    public HatchetProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new HatchetProjectileModel(context.bakeLayer(HatchetProjectileModel.HATCHET));
    }

    @Override
    public void submit(HatchetProjectileRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
        matrices.pushPose();

        if (!state.isGrounded) {
            matrices.mulPose(Axis.YP.rotationDegrees(state.yaw));
            matrices.mulPose(Axis.XP.rotationDegrees(state.pitch));
            matrices.mulPose(Axis.XP.rotationDegrees(state.getRenderingRotation() * 5f));
            matrices.translate(0, -1.0f, 0);
        } else {
            matrices.mulPose(Axis.YP.rotationDegrees(state.yaw));
            matrices.mulPose(Axis.XP.rotationDegrees(state.pitch));
            matrices.translate(0, -0.6f, 0);
        }

        RenderType renderType = this.model.renderType(TEXTURE);

        queue.submitModel(
                this.model,
                state,
                matrices,
                renderType,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                -1,
                null,
                state.outlineColor,
                null
        );

        matrices.popPose();
        super.submit(state, matrices, queue, cameraState);
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

    @Override
    public HatchetProjectileRenderState createRenderState() {
        return new HatchetProjectileRenderState();
    }
}
