package github.jcsmecabricks.customweapons.worldgen;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.init.BlockInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import java.util.List;

public class ConfiguredFeatureInit {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE_KEY = registerKey("overworld_silver_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_GEODE_KEY = registerKey("silver_geode_ore");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        var blocks = context.lookup(Registries.BLOCK);
        RuleTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> overworldSilverTargets = List.of(
                OreConfiguration.target(stoneOreReplaceables, BlockInit.SILVER_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateOreReplaceables, BlockInit.DEEPSLATE_SILVER_ORE.defaultBlockState()));

        register(context, OVERWORLD_SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSilverTargets, 4));
        register(context, SILVER_GEODE_KEY, Feature.GEODE, new GeodeConfiguration(
                new GeodeBlockSettings(
                        BlockStateProvider.simple(Blocks.AIR),
                        BlockStateProvider.simple(BlockInit.DEEPSLATE_SILVER_ORE),
                        BlockStateProvider.simple(BlockInit.SILVER_ORE),
                        BlockStateProvider.simple(Blocks.DIRT),
                        BlockStateProvider.simple(Blocks.DEEPSLATE),
                        List.of(BlockInit.SILVER_ORE.defaultBlockState()),
                        blocks.getOrThrow(BlockTags.FEATURES_CANNOT_REPLACE),
                        blocks.getOrThrow(BlockTags.GEODE_INVALID_BLOCKS)
                ),
                new GeodeLayerSettings(
                        1.5D, 1.0D, 2.0D, 3.0D
                ),
                new GeodeCrackSettings(
                        0.2D, 1.0D, 0
                ),
                0.4D, 0.1D,
                true, UniformInt.of(3, 6),
                UniformInt.of(2, 5), UniformInt.of(1, 2),
                -16, 16,
                0.05D, 1
        ));

    }
    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, CustomWeapons.id(name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                   ResourceKey<ConfiguredFeature<?, ?>> key,
                                                                                   F feature,
                                                                                   FC featureConfig) {
        context.register(key, new ConfiguredFeature<>(feature, featureConfig));
    }
}
