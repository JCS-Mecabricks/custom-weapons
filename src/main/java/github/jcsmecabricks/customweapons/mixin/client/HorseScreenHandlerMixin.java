package github.jcsmecabricks.customweapons.mixin.client;

import github.jcsmecabricks.customweapons.custom.ElephantArmorItem;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import github.jcsmecabricks.customweapons.slot.ModArmorSlot;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.HorseInventoryMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseInventoryMenu.class)
public abstract class HorseScreenHandlerMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addElephantBodySlot(int syncId, Inventory playerInventory, Container inventory, AbstractHorse entity, int slotColumnCount, CallbackInfo ci) {
        if (!(entity instanceof ElephantEntity elephant)) return;

        Identifier identifier = Identifier.withDefaultNamespace("container/slot/horse_armor");
        Container armorInventory = elephant.createEquipmentSlotContainer(EquipmentSlot.BODY);

        ScreenHandlerInvoker self = (ScreenHandlerInvoker) this;

        self.callAddSlot(new ModArmorSlot(armorInventory, elephant, EquipmentSlot.BODY, 0, 8, 36, identifier) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof ElephantArmorItem;
            }

            @Override
            public boolean isActive() {
                return true;
            }
        });
    }
}
