package github.jcsmecabricks.customweapons.networking.packet;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
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
        NbtCompound nbt = ((IEntityDataSaver) player).getPersistentData();

        boolean isActive = nbt.getBoolean("deadEyeActive").orElse(false);
        nbt.putBoolean("deadEyeActive", !isActive);

        int deadEyePoints = nbt.getInt("deadEye").orElse(10);
        DeadEyeData.syncDeadEye(deadEyePoints, player);
    }

}
