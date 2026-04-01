package github.jcsmecabricks.customweapons.mixin;

import github.jcsmecabricks.customweapons.event.KeyInputHandler;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    protected LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "aiStep", at = @At("HEAD"))
    private void onTickMovement(CallbackInfo ci) {
        if (KeyInputHandler.isSlowTickActive()) {
            LivingEntity self = (LivingEntity)(Object)this;
            if (!(self instanceof Player)) {
                // Slow down movement speed and velocity
                this.setDeltaMovement(this.getDeltaMovement().multiply(0.5, 0.5, 0.5));

                // Optionally slow down jump movement or other motion here
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTickEnd(CallbackInfo ci) {
        if (KeyInputHandler.isSlowTickActive()) {
            LivingEntity self = (LivingEntity)(Object)this;
            if (!(self instanceof Player)) {

            }
        }
    }
}

