package github.jcsmecabricks.customweapons.advancement.criterion;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModCriteria {
    public static ShotCompoundBowCriterion SHOT_COMPOUND_BOW;

    public static void loadCriteria() {
        SHOT_COMPOUND_BOW = Registry.register(
                Registries.CRITERION,
                CustomWeapons.id("shot_compound_bow"),
                new ShotCompoundBowCriterion()
        );
    }
}
