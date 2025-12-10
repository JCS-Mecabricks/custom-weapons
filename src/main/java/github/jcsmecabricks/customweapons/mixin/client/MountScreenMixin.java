package github.jcsmecabricks.customweapons.mixin.client;

import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.MountScreen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MountScreen.class)
public abstract class MountScreenMixin {

    @Shadow protected LivingEntity mount;

    @Shadow protected abstract void drawSlot(DrawContext context, int x, int y);

    @Inject(
            method = "drawBackground",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void addElephantBlanketSlot(
            DrawContext context, float delta, int mouseX, int mouseY,
            CallbackInfo ci,
            int i, int j // ← CAPTURED LOCALS
    ) {
        if (!(mount instanceof ElephantEntity elephant)) return;
        if (!elephant.canUseSlot(EquipmentSlot.CHEST)) return;

        // i = left, j = top
        this.drawSlot(context, i + 7, j + 35);
    }
}
