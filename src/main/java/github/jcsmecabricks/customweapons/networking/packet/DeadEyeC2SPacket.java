package github.jcsmecabricks.customweapons.networking.packet;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

public record DeadEyeC2SPacket() implements CustomPacketPayload {
    public static final Type<DeadEyeC2SPacket> ID = new Type<>(Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "dead_eye"));
    public static final StreamCodec<FriendlyByteBuf, DeadEyeC2SPacket> CODEC = StreamCodec.unit(new DeadEyeC2SPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static void receive(DeadEyeC2SPacket packet, ServerPlayNetworking.Context context) {
        ServerPlayer player = context.player();
        IEntityDataSaver dataPlayer = (IEntityDataSaver) player;

        int points = dataPlayer.getPersistentData().getIntOr("dead_eye", 0);
        boolean isActive = DeadEyeData.isDeadEyeActive(dataPlayer);

        // Case 1: Out of energy
        if (points <= 0) {
            DeadEyeData.setDeadEyeActive(dataPlayer, false);
            DeadEyeData.setDeadEye(dataPlayer, 0);
            return;
        }

        // Case 2: Manual toggle (player pressed the key)
        DeadEyeData.setDeadEyeActive(dataPlayer, !isActive);

        // Sync both the value and the active flag
        DeadEyeData.syncDeadEye(points, !isActive, player);
    }
}
