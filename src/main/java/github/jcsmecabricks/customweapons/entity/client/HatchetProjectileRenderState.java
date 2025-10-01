package github.jcsmecabricks.customweapons.entity.client;

import net.minecraft.client.render.entity.state.EntityRenderState;

public class HatchetProjectileRenderState extends EntityRenderState {
    public float yaw;
    public float pitch;
    private float rotation;
    public int light;
    public boolean isGrounded;
    public float getRenderingRotation;
    public float groundedPitch;
    public float groundedYaw;
    public int groundedTicks;

    public HatchetProjectileRenderState() {
        yaw = 0f;
        pitch = 0f;
        isGrounded = false;
        getRenderingRotation = 0f;
        groundedPitch = 0f;
        groundedYaw = 0f;
        groundedTicks = 0;
        light = 0xF000F0;
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }
}
