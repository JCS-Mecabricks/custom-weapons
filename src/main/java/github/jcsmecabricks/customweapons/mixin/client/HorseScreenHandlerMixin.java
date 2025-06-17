package github.jcsmecabricks.customweapons.mixin.client;

import github.jcsmecabricks.customweapons.custom.ElephantArmorItem;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import github.jcsmecabricks.customweapons.slot.ModArmorSlot;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.HorseScreenHandler;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HorseScreenHandler.class)
public abstract class HorseScreenHandlerMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addElephantBodySlot(int syncId, PlayerInventory playerInventory, Inventory inventory, AbstractHorseEntity entity, int slotColumnCount, CallbackInfo ci) {
        if (!(entity instanceof ElephantEntity elephant)) return;

        Identifier identifier = Identifier.ofVanilla("container/slot/horse_armor");
        Inventory armorInventory = elephant.createEquipmentInventory(EquipmentSlot.BODY);

        ScreenHandlerInvoker self = (ScreenHandlerInvoker) this;

        self.callAddSlot(new ModArmorSlot(armorInventory, elephant, EquipmentSlot.BODY, 0, 8, 36, identifier) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof ElephantArmorItem;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        });
    }
}
