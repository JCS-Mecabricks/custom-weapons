package github.jcsmecabricks.customweapons.event;

import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class PlayerTickHandler implements ServerTickEvents.StartTick {

    private final Map<ServerPlayerEntity, Integer> tickCounters = new HashMap<>();

    @Override
    public void onStartTick(MinecraftServer server) {
        // Clean up disconnected players
        tickCounters.keySet().removeIf(p -> !server.getPlayerManager().getPlayerList().contains(p));

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            IEntityDataSaver dataPlayer = (IEntityDataSaver) player;

            int deadEye = dataPlayer.getPersistentData().getInt("dead_eye", 0);
            boolean isActive = DeadEyeData.isDeadEyeActive(dataPlayer);

            // --- Stop immediately if empty ---
            if (deadEye <= 0) {
                if (isActive) {
                    DeadEyeData.setDeadEyeActive(dataPlayer, false);
                    DeadEyeData.syncDeadEye(0, false, player); // now syncs both value + active flag
                }
                tickCounters.put(player, 0);
                continue;
            }

            // --- Skip draining if inactive ---
            if (!isActive) {
                tickCounters.put(player, 0);
                continue;
            }

            int ticks = tickCounters.getOrDefault(player, 0) + 1;

            if (ticks >= 30) { // drain every 1.5 seconds at 20tps
                int remaining = DeadEyeData.removeDeadEye(dataPlayer, 1);
                tickCounters.put(player, 0);

                // --- Deplete check ---
                if (remaining <= 0) {
                    DeadEyeData.setDeadEyeActive(dataPlayer, false);
                    DeadEyeData.syncDeadEye(0, false, player);
                } else {
                    DeadEyeData.syncDeadEye(remaining, true, player);
                }
            } else {
                tickCounters.put(player, ticks);
            }
        }
    }
}