package github.jcsmecabricks.customweapons.event;

import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.NbtCompound;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public class DeadEyeEvents {
    public static void register() {
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            NbtCompound oldData = ((IEntityDataSaver) oldPlayer).getPersistentData();
            ((IEntityDataSaver) newPlayer).getPersistentData().copyFrom(oldData);
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            int deadEye = ((IEntityDataSaver) newPlayer).getPersistentData().getInt("deadEye").orElse(0);
            DeadEyeData.syncDeadEye(deadEye, newPlayer);
        });
        
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            int deadEye = ((IEntityDataSaver) handler.player).getPersistentData().getInt("deadEye").orElse(0);
            DeadEyeData.syncDeadEye(deadEye, handler.player);
        });
    }
}
