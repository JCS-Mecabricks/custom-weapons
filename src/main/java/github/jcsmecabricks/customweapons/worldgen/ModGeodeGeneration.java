package github.jcsmecabricks.customweapons.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModGeodeGeneration {
    public static void loadGeodes() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS, PlacedFeatureInit.SILVER_GEODE_KEY);
    }
}
