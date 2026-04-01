package github.jcsmecabricks.customweapons.mixin;

import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(
            method = "finishUsingItem(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;)Lnet/minecraft/world/item/ItemStack;",
            at = @At("HEAD")
    )
    private void onFinishUsing(Level world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClientSide() && user instanceof ServerPlayer player) {
            ItemStack stack = (ItemStack) (Object) this;

            int deadEyePoints = 0;

            if (stack.is(Items.ENCHANTED_GOLDEN_APPLE)) {
                deadEyePoints = 5;
            } else if (stack.is(Items.GOLDEN_APPLE)) {
                deadEyePoints = 2;
            } else if (stack.is(Items.GOLDEN_CARROT)) {
                deadEyePoints = 3;
            }

            if (deadEyePoints > 0) {
                DeadEyeData.addDeadEye((IEntityDataSaver) player, deadEyePoints);
            }
        }
    }
}