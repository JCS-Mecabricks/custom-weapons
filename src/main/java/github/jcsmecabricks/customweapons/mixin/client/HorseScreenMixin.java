package github.jcsmecabricks.customweapons.mixin.client;

import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HorseScreen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AbstractHorseEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(HorseScreen.class)
public abstract class HorseScreenMixin {

    @Inject(method = "drawBackground", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void injectBlanketSlot(DrawContext context, float delta, int mouseX, int mouseY, CallbackInfo ci, int i, int j) {
        HorseScreenAccessor accessor = (HorseScreenAccessor) this;
        AbstractHorseEntity entity = accessor.getEntity();

        if (entity instanceof ElephantEntity elephant && elephant.canUseSlot(EquipmentSlot.CHEST)) {
            accessor.callDrawSlot(context, i + 7, j + 35); // Adjust position as needed
        }
    }
}
