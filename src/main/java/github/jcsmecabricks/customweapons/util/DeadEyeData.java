package github.jcsmecabricks.customweapons.util;

import github.jcsmecabricks.customweapons.networking.packet.DeadEyeSyncDataS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class DeadEyeData {
    public static void setDeadEye(IEntityDataSaver player, int value) {
        CompoundTag nbt = player.getPersistentData();
        int clamped = Math.min(Math.max(value, 0), 10);
        nbt.putInt("dead_eye", clamped);

        if (player instanceof ServerPlayer serverPlayer) {
            syncDeadEye(clamped, isDeadEyeActive(player), serverPlayer);
        }
    }

    public static void setDeadEyeActive(IEntityDataSaver player, boolean active) {
        player.getPersistentData().putBoolean("dead_eye_active", active);

        if (player instanceof ServerPlayer serverPlayer) {
            int current = player.getPersistentData().getIntOr("dead_eye", 0);
            syncDeadEye(current, active, serverPlayer);
        }
    }

    public static boolean isDeadEyeActive(IEntityDataSaver player) {
        return player.getPersistentData().getBooleanOr("dead_eye_active", false);
    }

    public static int addDeadEye(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int deadEye = nbt.getIntOr("dead_eye", 0);

        deadEye = Math.min(deadEye + amount, 10);
        nbt.putInt("dead_eye", deadEye);

        if (player instanceof ServerPlayer serverPlayer) {
            syncDeadEye(deadEye, isDeadEyeActive(player), serverPlayer);
        }

        return deadEye;
    }

    public static int removeDeadEye(IEntityDataSaver player, int amount) {
        CompoundTag nbt = player.getPersistentData();
        int deadEye = nbt.getIntOr("dead_eye", 0);

        deadEye = Math.max(deadEye - amount, 0);
        nbt.putInt("dead_eye", deadEye);

        if (player instanceof ServerPlayer serverPlayer) {
            syncDeadEye(deadEye, isDeadEyeActive(player), serverPlayer);
        }

        return deadEye;
    }

    public static void syncDeadEye(int deadEye, boolean active, ServerPlayer player) {
        ServerPlayNetworking.send(player, new DeadEyeSyncDataS2CPacket(deadEye, active));
    }
}
