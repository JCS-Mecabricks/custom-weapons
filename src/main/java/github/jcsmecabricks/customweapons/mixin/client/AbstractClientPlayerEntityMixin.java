package github.jcsmecabricks.customweapons.mixin.client;

import com.mojang.authlib.GameProfile;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerEntityMixin extends Player {
    public AbstractClientPlayerEntityMixin(Level world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, gameProfile);
    }

    @Inject(method = "getFieldOfViewModifier", at = @At(value = "RETURN"), cancellable = true)
    private void getFovMultiplierMixin(CallbackInfoReturnable<Float> info) {
        ItemStack itemStack = this.getUseItem();
        if (this.isUsingItem() && itemStack.is(ItemInit.COMPOUND_BOW)) {
            int useTime = this.getTicksUsingItem();
            float g = (float) useTime / 20.0f; // Normalize use time to 0.0 - 1.0
            g = Math.min(g, 1.0f); // Clamp g to 1.0
            float zoomFactor = 1.0f - g * 0.15f; // Calculate zoom factor
            float newFov = info.getReturnValue() * zoomFactor; // Adjust FOV based on zoom factor
            info.setReturnValue(Mth.lerp(Minecraft.getInstance().options.fovEffectScale().get().floatValue(), 1.0f, newFov));
        }
    }
}
