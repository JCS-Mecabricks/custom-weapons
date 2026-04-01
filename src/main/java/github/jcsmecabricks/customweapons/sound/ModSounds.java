package github.jcsmecabricks.customweapons.sound;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent ELEPHANT_AMBIENT = registerSoundEvent("elephant_ambient");
    public static final SoundEvent ELEPHANT_HURT = registerSoundEvent("elephant_hurt");


    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(BuiltInRegistries.SOUND_EVENT, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name),
                SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name)));
    }

    public static void registerSounds() {
    }
}
