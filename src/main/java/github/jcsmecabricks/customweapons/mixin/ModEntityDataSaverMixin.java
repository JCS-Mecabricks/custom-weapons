package github.jcsmecabricks.customweapons.mixin;

import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {
    private CompoundTag persistentData;

    @Override
    public CompoundTag getPersistentData() {
        if (persistentData == null) {
            persistentData = new CompoundTag();
        }
        return persistentData;
    }

    @Inject(method = "saveWithoutId", at = @At("HEAD"))
    private void injectWriteMethod(ValueOutput view, CallbackInfo ci) {
        if (persistentData != null && !persistentData.isEmpty()) {
            view.store("custom-weapons.dead_eye_data", CompoundTag.CODEC, persistentData);
        }
    }

    @Inject(method = "load", at = @At("HEAD"))
    private void injectReadMethod(ValueInput view, CallbackInfo ci) {
        view.read("custom-weapons.dead_eye_data", CompoundTag.CODEC).ifPresent(nbt -> {
            this.persistentData = nbt;
        });
    }
}
