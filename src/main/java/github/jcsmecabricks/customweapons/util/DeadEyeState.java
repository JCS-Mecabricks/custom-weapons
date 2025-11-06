package github.jcsmecabricks.customweapons.util;

import com.mojang.serialization.Codec;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateType;

import java.util.HashMap;
import java.util.UUID;

import static github.jcsmecabricks.customweapons.CustomWeapons.MOD_ID;

public class DeadEyeState extends PersistentState {

    private final HashMap<UUID, Integer> deadEyeMap = new HashMap<>();
    private final HashMap<UUID, Boolean> activeMap = new HashMap<>();

    public static DeadEyeState INSTANCE;

    // --- Data accessors ---
    public static int getPoints(PlayerEntity player) {
        ensureLoaded(player);
        return INSTANCE.deadEyeMap.getOrDefault(player.getUuid(), 0);
    }

    public static void setPoints(PlayerEntity player, int value) {
        ensureLoaded(player);
        INSTANCE.deadEyeMap.put(player.getUuid(), value);
        INSTANCE.markDirty();
    }

    public static boolean isActive(PlayerEntity player) {
        ensureLoaded(player);
        return INSTANCE.activeMap.getOrDefault(player.getUuid(), false);
    }

    public static void setActive(PlayerEntity player, boolean active) {
        ensureLoaded(player);
        INSTANCE.activeMap.put(player.getUuid(), active);
        INSTANCE.markDirty();
    }

    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        NbtCompound pointsNbt = new NbtCompound();
        for (var entry : deadEyeMap.entrySet()) {
            pointsNbt.putInt(entry.getKey().toString(), entry.getValue());
        }
        nbt.put("DeadEyePoints", pointsNbt);

        NbtCompound activeNbt = new NbtCompound();
        for (var entry : activeMap.entrySet()) {
            activeNbt.putBoolean(entry.getKey().toString(), entry.getValue());
        }
        nbt.put("DeadEyeActive", activeNbt);
        return nbt;
    }

    public static DeadEyeState fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup lookup) {
        DeadEyeState state = new DeadEyeState();

        NbtCompound points = nbt.getCompound("DeadEyePoints").get();
        for (String key : points.getKeys()) {
            state.deadEyeMap.put(UUID.fromString(key), points.getInt(key).orElse(0));
        }

        NbtCompound active = nbt.getCompound("DeadEyeActive").get();
        for (String key : active.getKeys()) {
            state.activeMap.put(UUID.fromString(key), active.getBoolean(key).orElse(false));
        }

        return state;
    }

    public static final PersistentStateType<DeadEyeState> TYPE =
            new PersistentStateType<>(
                    MOD_ID + "_dead_eye",
                    ctx -> new DeadEyeState(),
                    ctx -> Codec.unit(new DeadEyeState()),
                    DataFixTypes.PLAYER
            );

    public static DeadEyeState get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(TYPE);
    }

    private static void ensureLoaded(PlayerEntity player) {
        if (INSTANCE == null && player.getEntityWorld() instanceof ServerWorld serverWorld) {
            INSTANCE = get(serverWorld);
        }
    }
}
