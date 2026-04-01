package github.jcsmecabricks.customweapons.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModOreGeneration {
    public static void load() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                PlacedFeatureInit.OVERWORLD_SILVER_ORE_KEY
        );
    }
}
