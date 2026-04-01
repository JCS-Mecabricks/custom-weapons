package github.jcsmecabricks.customweapons.mixin.client;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractContainerMenu.class)
public interface ScreenHandlerInvoker {
    @Invoker("addSlot")
    Slot callAddSlot(Slot slot);
}
