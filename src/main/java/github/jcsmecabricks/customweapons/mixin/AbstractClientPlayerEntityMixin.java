package github.jcsmecabricks.customweapons.mixin;

import com.mojang.authlib.GameProfile;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {
    public AbstractClientPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Inject(method = "getFovMultiplier", at = @At(value = "RETURN"), cancellable = true)
    private void getFovMultiplierMixin(CallbackInfoReturnable<Float> info) {
        ItemStack itemStack = this.getActiveItem();
        if (this.isUsingItem() && itemStack.isOf(ItemInit.COMPOUND_BOW)) {
            int useTime = this.getItemUseTime();
            float g = (float) useTime / 20.0f; // Normalize use time to 0.0 - 1.0
            g = Math.min(g, 1.0f); // Clamp g to 1.0
            float zoomFactor = 1.0f - g * 0.15f; // Calculate zoom factor
            float newFov = info.getReturnValue() * zoomFactor; // Adjust FOV based on zoom factor
            info.setReturnValue(MathHelper.lerp(MinecraftClient.getInstance().options.getFovEffectScale().getValue().floatValue(), 1.0f, newFov));
        }
    }
}
