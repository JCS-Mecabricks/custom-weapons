package github.jcsmecabricks.customweapons.networking.packet;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DeadEyeSyncDataS2CPacket(int deadEyeValue) implements CustomPayload {
    public static final Id<DeadEyeSyncDataS2CPacket> ID =
            new Id<>(Identifier.of(CustomWeapons.MOD_ID, "dead_eye"));

    public static final PacketCodec<PacketByteBuf, DeadEyeSyncDataS2CPacket> CODEC =
            PacketCodec.of(DeadEyeSyncDataS2CPacket::write, DeadEyeSyncDataS2CPacket::read);

    public static DeadEyeSyncDataS2CPacket read(PacketByteBuf buf) {
        return new DeadEyeSyncDataS2CPacket(buf.readInt());
    }

    public void write(PacketByteBuf buf) {
        buf.writeInt(deadEyeValue);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}

