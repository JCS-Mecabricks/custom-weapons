package github.jcsmecabricks.customweapons.mixin.client;

import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractMountInventoryScreen;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AbstractMountInventoryScreen.class)
public abstract class MountScreenMixin {

    // Shadow the field, not a method
    @Shadow
    protected LivingEntity mount;

    @Shadow
    protected abstract void extractSlot(final GuiGraphicsExtractor context, final int x, final int y);

    @Inject(
            method = "extractBackground",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void addElephantBlanketSlot(
            final GuiGraphicsExtractor context,
            final int mouseX,
            final int mouseY,
            final float partialTicks,
            CallbackInfo ci,
            int i, int j // captured left/top positions from extractBackground
    ) {
        // Only add the slot if the mount is an Elephant and chest slot is usable
        if (!(mount instanceof ElephantEntity elephant)) return;
        if (!elephant.canUseSlot(EquipmentSlot.CHEST)) return;

        // Offset from original armor slot
        this.extractSlot(context, i + 7, j + 35);
    }
}