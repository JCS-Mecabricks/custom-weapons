package github.jcsmecabricks.customweapons.event;

import com.mojang.blaze3d.platform.InputConstants;
import github.jcsmecabricks.customweapons.networking.ModMessages;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeC2SPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyMapping.Category CUSTOM_WEAPONS_KEY_CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath("key.category.custom-weapons", "main"));
    public static final String KEY_DEAD_EYE = "key.custom-weapons.dead_eye";

    public static KeyMapping deadEyeKey;

    private static boolean slowTickActive = false;

    public static boolean isSlowTickActive() {
        return slowTickActive;
    }

    public static void setSlowTickActive(boolean active) {
        slowTickActive = active;
    }

    public static void register() {
        deadEyeKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_DEAD_EYE,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                CUSTOM_WEAPONS_KEY_CATEGORY
        ));
    }

    public static void registerKeyInputs() {
        register(); // register the keybinding first

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (deadEyeKey.consumeClick()) {
                ModMessages.send(new DeadEyeC2SPacket());
            }
        });
    }

}
