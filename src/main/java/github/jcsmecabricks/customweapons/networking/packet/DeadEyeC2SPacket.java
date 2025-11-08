package github.jcsmecabricks.customweapons.networking.packet;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public record DeadEyeC2SPacket() implements CustomPayload {
    public static final Id<DeadEyeC2SPacket> ID = new Id<>(Identifier.of(CustomWeapons.MOD_ID, "dead_eye"));
    public static final PacketCodec<PacketByteBuf, DeadEyeC2SPacket> CODEC = PacketCodec.unit(new DeadEyeC2SPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static void receive(DeadEyeC2SPacket packet, ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        IEntityDataSaver dataPlayer = (IEntityDataSaver) player;

        int points = dataPlayer.getPersistentData().getInt("dead_eye", 0);
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
