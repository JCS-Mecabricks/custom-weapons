package github.jcsmecabricks.customweapons.advancement.criterion;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModCriteria {
    public static ShotCompoundBowCriterion SHOT_COMPOUND_BOW;

    public static void loadCriteria() {
        SHOT_COMPOUND_BOW = Registry.register(
                BuiltInRegistries.TRIGGER_TYPES,
                CustomWeapons.id("shot_compound_bow"),
                new ShotCompoundBowCriterion()
        );
    }
}
