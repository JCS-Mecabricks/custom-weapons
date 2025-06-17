package github.jcsmecabricks.customweapons.mixin;

import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null) {
            persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Inject(method = "writeData", at = @At("HEAD"))
    private void injectWriteMethod(WriteView view, CallbackInfo ci) {
        if (persistentData != null && !persistentData.isEmpty()) {
            view.put("custom-weapons.dead_eye_data", NbtCompound.CODEC, persistentData);
        }
    }

    @Inject(method = "readData", at = @At("HEAD"))
    private void injectReadMethod(ReadView view, CallbackInfo ci) {
        view.read("custom-weapons.dead_eye_data", NbtCompound.CODEC).ifPresent(nbt -> {
            this.persistentData = nbt;
        });
    }
}
