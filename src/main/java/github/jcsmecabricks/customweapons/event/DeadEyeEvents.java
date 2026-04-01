package github.jcsmecabricks.customweapons.event;

import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityLevelChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class DeadEyeEvents {
    public static void register() {
        // --- Preserve persistent data when player respawns ---
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            CompoundTag oldData = ((IEntityDataSaver) oldPlayer).getPersistentData();
            ((IEntityDataSaver) newPlayer).getPersistentData().merge(oldData);
        });

        // --- Sync Dead Eye after respawn ---
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            syncToClient(newPlayer);
        });

        ServerEntityLevelChangeEvents.AFTER_PLAYER_CHANGE_LEVEL.register(
                (player, origin, destination) -> {
                    syncToClient(player);
                }
        );


        // --- Sync Dead Eye when player joins ---
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            syncToClient(handler.player);
        });
    }

    private static void syncToClient(ServerPlayer player) {
        IEntityDataSaver dataPlayer = (IEntityDataSaver) player;
        int deadEye = dataPlayer.getPersistentData().getIntOr("dead_eye", 0);
        boolean isActive = DeadEyeData.isDeadEyeActive(dataPlayer);

        DeadEyeData.syncDeadEye(deadEye, isActive, player);
    }
}
