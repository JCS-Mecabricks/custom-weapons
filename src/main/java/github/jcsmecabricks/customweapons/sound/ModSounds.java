package github.jcsmecabricks.customweapons.sound;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent ELEPHANT_AMBIENT = registerSoundEvent("elephant_ambient");
    public static final SoundEvent ELEPHANT_HURT = registerSoundEvent("elephant_hurt");


    private static SoundEvent registerSoundEvent(String name) {
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(CustomWeapons.MOD_ID, name),
                SoundEvent.of(Identifier.of(CustomWeapons.MOD_ID, name)));
    }

    public static void registerSounds() {
    }
}
