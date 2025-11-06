package github.jcsmecabricks.customweapons.networking.packet;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.util.DeadEyeData;
import github.jcsmecabricks.customweapons.util.DeadEyeState;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
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
        ServerWorld world = player.getEntityWorld();

        DeadEyeState state = DeadEyeState.get(world);

        boolean isActive = DeadEyeState.isActive(player);
        DeadEyeState.setActive(player, !isActive);

        int points = DeadEyeState.getPoints(player);
        points = Math.clamp(points, 0, 10);
        DeadEyeState.setPoints(player, points);

        DeadEyeData.syncDeadEye(points, player);
    }
}
