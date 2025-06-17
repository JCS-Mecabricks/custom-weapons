package github.jcsmecabricks.customweapons.event;

import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;

public class PlayerTickHandler implements ServerTickEvents.StartTick {
    private final Map<ServerPlayerEntity, Integer> tickCounters = new HashMap<>();

    @Override
    public void onStartTick(MinecraftServer server) {
        // Clean up counters for disconnected players
        tickCounters.keySet().removeIf(player -> !server.getPlayerManager().getPlayerList().contains(player));

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            IEntityDataSaver dataPlayer = (IEntityDataSaver) player;

            if (DeadEyeData.isDeadEyeActive(dataPlayer)) {
                int ticks = tickCounters.getOrDefault(player, 0) + 1;

                if (ticks >= 30) {
                    int remaining = DeadEyeData.removeDeadEye(dataPlayer, 1);
                    ticks = 0;

                    if (remaining <= 0) {
                        DeadEyeData.setDeadEyeActive(dataPlayer, false);
                    }
                }

                tickCounters.put(player, ticks);
            } else {
                tickCounters.put(player, 0);
            }
        }
    }

}
