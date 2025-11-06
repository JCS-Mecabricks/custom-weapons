package github.jcsmecabricks.customweapons.util;

import github.jcsmecabricks.customweapons.networking.packet.DeadEyeSyncDataS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class DeadEyeData {
    public static void setDeadEye(IEntityDataSaver player, int value) {
        NbtCompound nbt = player.getPersistentData();
        int clamped = Math.min(Math.max(value, 0), 10);
        nbt.putInt("dead_eye", clamped);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            syncDeadEye(clamped, serverPlayer);
        }
    }

    public static void setDeadEyeActive(IEntityDataSaver player, boolean active) {
        player.getPersistentData().putBoolean("dead_eye_active", active);
    }

    public static boolean isDeadEyeActive(IEntityDataSaver player) {
        return player.getPersistentData().getBoolean("dead_eye_active").orElse(false);
    }

    public static int addDeadEye(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int deadEye = nbt.getInt("dead_eye").orElse(0);

        deadEye = Math.min(deadEye + amount, 10);
        nbt.putInt("dead_eye", deadEye);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            syncDeadEye(deadEye, serverPlayer);
        }

        return deadEye;
    }

    public static int removeDeadEye(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int deadEye = nbt.getInt("dead_eye").orElse(0);

        deadEye = Math.max(deadEye - amount, 0);
        nbt.putInt("dead_eye", deadEye);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            syncDeadEye(deadEye, serverPlayer);
        }

        return deadEye;
    }

    public static void syncDeadEye(int deadEye, ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new DeadEyeSyncDataS2CPacket(deadEye));
    }
}
