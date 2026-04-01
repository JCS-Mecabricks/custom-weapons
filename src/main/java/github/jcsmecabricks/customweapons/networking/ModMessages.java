package github.jcsmecabricks.customweapons.networking;

import github.jcsmecabricks.customweapons.networking.packet.DeadEyeC2SPacket;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeSyncDataS2CPacket;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;

public class ModMessages {
    public static void registerC2SPackets() {
        PayloadTypeRegistry.serverboundPlay().register(DeadEyeC2SPacket.ID, DeadEyeC2SPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(DeadEyeC2SPacket.ID, DeadEyeC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        PayloadTypeRegistry.clientboundPlay().register(
                DeadEyeSyncDataS2CPacket.ID,
                DeadEyeSyncDataS2CPacket.CODEC
        );

        ClientPlayNetworking.registerGlobalReceiver(
                DeadEyeSyncDataS2CPacket.ID,
                (payload, context) -> {
                    context.client().execute(() -> {
                        if (context.player() instanceof IEntityDataSaver dataPlayer) {
                            dataPlayer.getPersistentData().putInt("dead_eye", payload.deadEyeValue());
                            dataPlayer.getPersistentData().putBoolean("dead_eye_active", payload.active());

                            // 🔥 ADD THIS LINE 🔥
                            github.jcsmecabricks.customweapons.event.KeyInputHandler.setSlowTickActive(payload.active());
                        }
                    });
                }
        );
    }

    public static void send(DeadEyeC2SPacket payload) {
        if (Minecraft.getInstance().getConnection() != null) {
            ClientPlayNetworking.send(payload);
        } else {
            throw new IllegalStateException("Cannot send packets when not in game!");
        }
    }

    public static void send(ServerPlayer player, DeadEyeSyncDataS2CPacket payload) {
        ServerPlayNetworking.send(player, payload);
    }
}
