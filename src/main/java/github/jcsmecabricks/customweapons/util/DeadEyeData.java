package github.jcsmecabricks.customweapons.util;


import github.jcsmecabricks.customweapons.networking.packet.DeadEyeSyncDataS2CPacket;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class DeadEyeData {
    public static void setDeadEye(IEntityDataSaver player, int value) {
        NbtCompound nbt = player.getPersistentData();
        int clamped = Math.min(Math.max(value, 0), 10);
        nbt.putInt("deadEye", clamped);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            syncDeadEye(clamped, serverPlayer);
        }
    }

    public static void setDeadEyeActive(IEntityDataSaver player, boolean active) {
        NbtCompound nbt = player.getPersistentData();
        nbt.putBoolean("deadEyeActive", active);
    }
    public static boolean isDeadEyeActive(IEntityDataSaver player) {
        return player.getPersistentData().getBoolean("deadEyeActive").orElse(false);
    }

    public static int addDeadEye(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int deadEye = nbt.getInt("deadEye").orElse(0);

        deadEye = Math.min(deadEye + amount, 10);

        nbt.putInt("deadEye", deadEye);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            syncDeadEye(deadEye, serverPlayer);
        }

        return deadEye;
    }


    public static int removeDeadEye(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int deadEye = nbt.getInt("deadEye").orElse(0);
        if(deadEye - amount < 0) {
            deadEye = 0;
        } else {
            deadEye -= amount;
        }

        syncDeadEye(deadEye, (ServerPlayerEntity) player);
        nbt.putInt("deadEye", deadEye);
        return deadEye;
    }

    public static void syncDeadEye(int deadEye, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(deadEye);
        ServerPlayNetworking.send(player, new DeadEyeSyncDataS2CPacket(deadEye));
    }

}
