package github.jcsmecabricks.customweapons.event;

import github.jcsmecabricks.customweapons.networking.ModMessages;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeC2SPacket;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeSyncDataS2CPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String CUSTOM_WEAPONS_KEY_CATEGORY = "key.category.custom-weapons";
    public static final String KEY_DEAD_EYE = "key.custom-weapons.dead_eye";

    public static KeyBinding deadEyeKey;

    private static boolean slowTickActive = false;

    public static boolean isSlowTickActive() {
        return slowTickActive;
    }

    public static void setSlowTickActive(boolean active) {
        slowTickActive = active;
    }

    public static void register() {
        deadEyeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DEAD_EYE,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                CUSTOM_WEAPONS_KEY_CATEGORY
        ));
    }

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (deadEyeKey.wasPressed()) {
                ClientPlayNetworking.send(new DeadEyeC2SPacket());
                slowTickActive = !slowTickActive;
            }
        });

        register();
    }
}
