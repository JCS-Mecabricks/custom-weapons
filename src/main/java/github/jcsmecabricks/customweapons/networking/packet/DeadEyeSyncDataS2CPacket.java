package github.jcsmecabricks.customweapons.networking.packet;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record DeadEyeSyncDataS2CPacket(int deadEyeValue, boolean active) implements CustomPacketPayload {
    public static final Type<DeadEyeSyncDataS2CPacket> ID =
            new Type<>(Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "dead_eye_sync"));

    public static final StreamCodec<FriendlyByteBuf, DeadEyeSyncDataS2CPacket> CODEC =
            StreamCodec.ofMember(DeadEyeSyncDataS2CPacket::write, DeadEyeSyncDataS2CPacket::read);

    public static DeadEyeSyncDataS2CPacket read(FriendlyByteBuf buf) {
        int value = buf.readInt();
        boolean active = buf.readBoolean();
        return new DeadEyeSyncDataS2CPacket(value, active);
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeInt(deadEyeValue);
        buf.writeBoolean(active);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
