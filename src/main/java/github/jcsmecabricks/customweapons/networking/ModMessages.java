package github.jcsmecabricks.customweapons.networking;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeC2SPacket;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeSyncDataS2CPacket;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static void registerC2SPackets() {
        PayloadTypeRegistry.playC2S().register(DeadEyeC2SPacket.ID, DeadEyeC2SPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(DeadEyeC2SPacket.ID, DeadEyeC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        PayloadTypeRegistry.playS2C().register(
                DeadEyeSyncDataS2CPacket.ID,
                DeadEyeSyncDataS2CPacket.CODEC
        );

        ClientPlayNetworking.registerGlobalReceiver(
                DeadEyeSyncDataS2CPacket.ID,
                (payload, context) -> {
                    context.client().execute(() -> {
                        ((IEntityDataSaver) context.player()).getPersistentData().putInt("dead_eye", payload.deadEyeValue());
                    });
                }
        );
    }
}
