package github.jcsmecabricks.customweapons.util;

import com.mojang.serialization.Codec;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

import static github.jcsmecabricks.customweapons.CustomWeapons.MOD_ID;

public class DeadEyeState extends SavedData {

    private final HashMap<UUID, Integer> deadEyeMap = new HashMap<>();
    private final HashMap<UUID, Boolean> activeMap = new HashMap<>();

    public static DeadEyeState INSTANCE;

    // --- Data accessors ---
    public static int getPoints(Player player) {
        ensureLoaded(player);
        return INSTANCE.deadEyeMap.getOrDefault(player.getUUID(), 0);
    }

    public static void setPoints(Player player, int value) {
        ensureLoaded(player);
        INSTANCE.deadEyeMap.put(player.getUUID(), value);
        INSTANCE.setDirty();
    }

    public static boolean isActive(Player player) {
        ensureLoaded(player);
        return INSTANCE.activeMap.getOrDefault(player.getUUID(), false);
    }

    public static void setActive(Player player, boolean active) {
        ensureLoaded(player);
        INSTANCE.activeMap.put(player.getUUID(), active);
        INSTANCE.setDirty();
    }

    public CompoundTag writeNbt(CompoundTag nbt, HolderLookup.Provider lookup) {
        CompoundTag pointsNbt = new CompoundTag();
        for (var entry : deadEyeMap.entrySet()) {
            pointsNbt.putInt(entry.getKey().toString(), entry.getValue());
        }
        nbt.put("DeadEyePoints", pointsNbt);

        CompoundTag activeNbt = new CompoundTag();
        for (var entry : activeMap.entrySet()) {
            activeNbt.putBoolean(entry.getKey().toString(), entry.getValue());
        }
        nbt.put("DeadEyeActive", activeNbt);
        return nbt;
    }

    public static DeadEyeState fromNbt(CompoundTag nbt, HolderLookup.Provider lookup) {
        DeadEyeState state = new DeadEyeState();

        CompoundTag points = nbt.getCompound("DeadEyePoints").get();
        for (String key : points.keySet()) {
            state.deadEyeMap.put(UUID.fromString(key), points.getInt(key).orElse(0));
        }

        CompoundTag active = nbt.getCompound("DeadEyeActive").get();
        for (String key : active.keySet()) {
            state.activeMap.put(UUID.fromString(key), active.getBoolean(key).orElse(false));
        }

        return state;
    }

    public static final SavedDataType<DeadEyeState> TYPE =
            new SavedDataType<>(
                    Identifier.fromNamespaceAndPath(MOD_ID, "_dead_eye"),
                    DeadEyeState::new,
                    null,
                    DataFixTypes.PLAYER
            );


    public static DeadEyeState get(ServerLevel world) {
        return world.getDataStorage().computeIfAbsent(TYPE);
    }

    private static void ensureLoaded(Player player) {
        if (INSTANCE == null && player.level() instanceof ServerLevel serverWorld) {
            INSTANCE = get(serverWorld);
        }
    }
}
