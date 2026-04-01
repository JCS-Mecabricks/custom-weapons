package github.jcsmecabricks.customweapons.entity.custom;

import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.minecraft.client.model.geom.builders.UVPair;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class HatchetProjectileEntity extends AbstractArrow {
    private float rotation;
    public int groundedTicks = 0;
    public UVPair groundedOffset;

    public HatchetProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
        groundedOffset = new UVPair(0f, 0f);
    }

    public HatchetProjectileEntity(Level world, Player player) {
        super(ModEntities.HATCHET, player, world, new ItemStack(ItemInit.HATCHET), null);
        groundedOffset = new UVPair(0f, 0f);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ItemInit.HATCHET);
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    @Override
    public void tick() {
        super.tick();

        if (isInGround()) {
            groundedTicks = Math.min(groundedTicks + 1, 10);
        } else {
            groundedTicks = 0;
        }
    }



    public boolean isGrounded() {
        return isInGround();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        if (!this.level().isClientSide()) {
            ServerLevel world = (ServerLevel) this.level();
            Entity entity = entityHitResult.getEntity();
            entity.hurtServer(world, this.damageSources().thrown(this, this.getOwner()), 7);
            this.level().broadcastEntityEvent(this, (byte) 9);
            this.discard();
        }
    }


    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if(result.getDirection() == Direction.SOUTH) {
            groundedOffset = new UVPair(215f,180f);
        }
        if(result.getDirection() == Direction.NORTH) {
            groundedOffset = new UVPair(215f, 0f);
        }
        if(result.getDirection() == Direction.EAST) {
            groundedOffset = new UVPair(215f,-90f);
        }
        if(result.getDirection() == Direction.WEST) {
            groundedOffset = new UVPair(215f,90f);
        }

        if(result.getDirection() == Direction.DOWN) {
            groundedOffset = new UVPair(115f,180f);
        }
        if(result.getDirection() == Direction.UP) {
            groundedOffset = new UVPair(285f,180f);
        }
    }
}
