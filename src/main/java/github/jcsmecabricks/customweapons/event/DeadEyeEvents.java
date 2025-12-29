package github.jcsmecabricks.customweapons.event;

import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class DeadEyeEvents {
    public static void register() {
        // --- Preserve persistent data when player respawns ---
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            NbtCompound oldData = ((IEntityDataSaver) oldPlayer).getPersistentData();
            ((IEntityDataSaver) newPlayer).getPersistentData().copyFrom(oldData);
        });

        // --- Sync Dead Eye after respawn ---
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            syncToClient(newPlayer);
        });

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register(
                (player, origin, destination) -> {
                    syncToClient(player);
                }
        );


        // --- Sync Dead Eye when player joins ---
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            syncToClient(handler.player);
        });
    }

    private static void syncToClient(ServerPlayerEntity player) {
        IEntityDataSaver dataPlayer = (IEntityDataSaver) player;
        int deadEye = dataPlayer.getPersistentData().getInt("dead_eye", 0);
        boolean isActive = DeadEyeData.isDeadEyeActive(dataPlayer);

        DeadEyeData.syncDeadEye(deadEye, isActive, player);
    }
}
