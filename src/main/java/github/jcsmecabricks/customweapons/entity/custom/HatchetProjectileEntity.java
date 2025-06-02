package github.jcsmecabricks.customweapons.entity.custom;

import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class HatchetProjectileEntity extends PersistentProjectileEntity {
    private float rotation;
    public int groundedTicks = 0;
    public Vector2f groundedOffset;

    public HatchetProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        groundedOffset = new Vector2f(0f, 0f);
    }

    public HatchetProjectileEntity(World world, PlayerEntity player) {
        super(ModEntities.HATCHET, player, world, new ItemStack(ItemInit.HATCHET), null);
        groundedOffset = new Vector2f(0f, 0f);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
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
    protected void onEntityHit(EntityHitResult entityHitResult) {
        if (!this.getWorld().isClient()) {
            ServerWorld world = (ServerWorld) this.getWorld();
            Entity entity = entityHitResult.getEntity();
            entity.damage(world, this.getDamageSources().thrown(this, this.getOwner()), 7);
            this.getWorld().sendEntityStatus(this, (byte) 9);
            this.discard();
        }
    }


    @Override
    protected void onBlockHit(BlockHitResult result) {
        super.onBlockHit(result);

        if(result.getSide() == Direction.SOUTH) {
            groundedOffset = new Vector2f(215f,180f);
        }
        if(result.getSide() == Direction.NORTH) {
            groundedOffset = new Vector2f(215f, 0f);
        }
        if(result.getSide() == Direction.EAST) {
            groundedOffset = new Vector2f(215f,-90f);
        }
        if(result.getSide() == Direction.WEST) {
            groundedOffset = new Vector2f(215f,90f);
        }

        if(result.getSide() == Direction.DOWN) {
            groundedOffset = new Vector2f(115f,180f);
        }
        if(result.getSide() == Direction.UP) {
            groundedOffset = new Vector2f(285f,180f);
        }
    }
}
