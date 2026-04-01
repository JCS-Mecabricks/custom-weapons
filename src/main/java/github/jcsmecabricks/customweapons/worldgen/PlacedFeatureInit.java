package github.jcsmecabricks.customweapons.worldgen;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import java.util.List;

public class PlacedFeatureInit {
    public static final ResourceKey<PlacedFeature> OVERWORLD_SILVER_ORE_KEY = registerKey("overworld_silver_ore");
    public static final ResourceKey<PlacedFeature> SILVER_GEODE_KEY = registerKey("silver_geode_ore");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.lookup(Registries.CONFIGURED_FEATURE);
        HolderGetter<ConfiguredFeature<?, ?>> registryLookup = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, OVERWORLD_SILVER_ORE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ConfiguredFeatureInit.OVERWORLD_SILVER_ORE_KEY),
                Modifiers.modifiersWithCount(14,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-20), VerticalAnchor.absolute(64))));

        register(context, SILVER_GEODE_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ConfiguredFeatureInit.SILVER_GEODE_KEY),
                RarityFilter.onAverageOnceEvery(25),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(16)),
                BiomeFilter.biome()
        );

    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                                                                   Holder<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
    public static class Modifiers {
        public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
            return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
        }

        public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
            return modifiers(CountPlacement.of(count), heightModifier);
        }

        public static List<PlacementModifier> modifiersWithRarity(int chance, PlacementModifier heightModifier) {
            return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
        }
    }
}
