package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

import java.util.List;

public class HatchetProjectileRenderer extends EntityRenderer<HatchetProjectileEntity, HatchetProjectileRenderState> {
    private static final Identifier TEXTURE = Identifier.of(CustomWeapons.MOD_ID, "textures/entity/hatchet/hatchet.png");
    protected HatchetProjectileModel model;
    public HatchetProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        model = new HatchetProjectileModel(context.getPart(HatchetProjectileModel.HATCHET));
    }

    @Override
    public void render(HatchetProjectileRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        matrices.push();

        if (!state.isGrounded) {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.pitch));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.getRenderingRotation() * 5f));
            matrices.translate(0, -1.0f, 0);
        } else {
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.yaw));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(state.pitch));
            matrices.translate(0, -0.6f, 0);
        }

        List<RenderLayer> list = ItemRenderer.getGlintRenderLayers(this.model.getLayer(TEXTURE), false, false);
        for(int i = 0; i < list.size(); ++i) {
            queue.getBatchingQueue(i).submitModel(this.model,
                    state, matrices,
                    list.get(i), state.light,
                    OverlayTexture.DEFAULT_UV, -1,
                    (Sprite)null, state.outlineColor,
                    null);
        }

        matrices.pop();
        super.render(state, matrices, queue, cameraState);
    }


    @Override
    public void updateRenderState(HatchetProjectileEntity hatchetProjectile, HatchetProjectileRenderState hatchetProjectileRenderState, float f) {
        super.updateRenderState(hatchetProjectile, hatchetProjectileRenderState, f);
        hatchetProjectileRenderState.yaw = hatchetProjectile.getLerpedYaw(f);
        hatchetProjectileRenderState.pitch = hatchetProjectile.getLerpedPitch(f);
        hatchetProjectileRenderState.isGrounded = hatchetProjectile.isGrounded();
        hatchetProjectileRenderState.getRenderingRotation = hatchetProjectile.getRenderingRotation();
        hatchetProjectileRenderState.groundedPitch = hatchetProjectile.groundedOffset.getX();
        hatchetProjectileRenderState.groundedYaw = hatchetProjectile.groundedOffset.getY();
    }

    @Override
    public HatchetProjectileRenderState createRenderState() {
        return new HatchetProjectileRenderState();
    }
}
