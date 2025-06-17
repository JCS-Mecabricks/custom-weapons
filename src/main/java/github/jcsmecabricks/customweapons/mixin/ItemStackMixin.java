package github.jcsmecabricks.customweapons.mixin;

import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(
            method = "finishUsing(Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;",
            at = @At("HEAD")
    )
    private void onFinishUsing(World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient() && user instanceof ServerPlayerEntity player) {
            ItemStack stack = (ItemStack) (Object) this;

            int deadEyePoints = 0;

            if (stack.isOf(Items.ENCHANTED_GOLDEN_APPLE)) {
                deadEyePoints = 5;
            } else if (stack.isOf(Items.GOLDEN_APPLE)) {
                deadEyePoints = 2;
            } else if (stack.isOf(Items.GOLDEN_CARROT)) {
                deadEyePoints = 3;
            }

            if (deadEyePoints > 0) {
                DeadEyeData.addDeadEye((IEntityDataSaver) player, deadEyePoints);
            }
        }
    }
}