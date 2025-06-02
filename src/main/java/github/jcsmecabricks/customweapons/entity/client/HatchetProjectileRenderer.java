package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.TridentEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class HatchetProjectileRenderer extends EntityRenderer<HatchetProjectileEntity, HatchetProjectileRenderState> {
    private static final Identifier TEXTURE = Identifier.of(CustomWeapons.MOD_ID, "textures/entity/hatchet/hatchet.png");
    protected HatchetProjectileModel model;
    public HatchetProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        model = new HatchetProjectileModel(context.getPart(HatchetProjectileModel.HATCHET));
    }

    @Override
    public void render(HatchetProjectileRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
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

        VertexConsumer vertexConsumer = ItemRenderer.getItemGlintConsumer(vertexConsumers, model.getLayer(TEXTURE), false, false);
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
        super.render(state, matrices, vertexConsumers, light);
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
