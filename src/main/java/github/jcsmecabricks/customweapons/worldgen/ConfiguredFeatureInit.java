package github.jcsmecabricks.customweapons.worldgen;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ConfiguredFeatureInit {
    public static final RegistryKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE_KEY = registerKey("overworld_silver_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SILVER_GEODE_KEY = registerKey("silver_geode_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneOreReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldSilverTargets = List.of(
                OreFeatureConfig.createTarget(stoneOreReplaceables, BlockInit.SILVER_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateOreReplaceables, BlockInit.DEEPSLATE_SILVER_ORE.getDefaultState()));

        register(context, OVERWORLD_SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverTargets, 4));
        register(context, SILVER_GEODE_KEY, Feature.GEODE, new GeodeFeatureConfig(
                new GeodeLayerConfig(
                        BlockStateProvider.of(Blocks.AIR),
                        BlockStateProvider.of(BlockInit.DEEPSLATE_SILVER_ORE),
                        BlockStateProvider.of(BlockInit.SILVER_ORE),
                        BlockStateProvider.of(Blocks.DIRT),
                        BlockStateProvider.of(Blocks.DEEPSLATE),
                        List.of(BlockInit.SILVER_ORE.getDefaultState()),
                        BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS
                ),
                new GeodeLayerThicknessConfig(
                        1.5D, 1.0D, 2.0D, 3.0D
                ),
                new GeodeCrackConfig(
                        0.2D, 1.0D, 0
                ),
                0.4D, 0.1D,
                true, UniformIntProvider.create(3, 6),
                UniformIntProvider.create(2, 5), UniformIntProvider.create(1, 2),
                -16, 16,
                0.05D, 1
        ));

    }
    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, CustomWeapons.id(name));
    }
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key,
                                                                                   F feature,
                                                                                   FC featureConfig) {
        context.register(key, new ConfiguredFeature<>(feature, featureConfig));
    }
}
