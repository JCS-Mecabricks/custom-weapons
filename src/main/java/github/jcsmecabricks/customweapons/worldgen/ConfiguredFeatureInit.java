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
import net.minecraft.world.level.levelgen.feature.BlockReplacement;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.GeodeFeature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import java.util.List;

public class ConfiguredFeatureInit {
    public static final ResourceKey<Feature> OVERWORLD_SILVER_ORE_KEY = registerKey("overworld_silver_ore");
    public static final ResourceKey<Feature> SILVER_GEODE_KEY = registerKey("silver_geode_ore");

    public static void bootstrap(BootstrapContext<Feature> context) {
        var blocks = context.lookup(Registries.BLOCK);
        RuleTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<BlockReplacement> overworldSilverTargets = List.of(
                BlockReplacement.replace(stoneOreReplaceables, BlockInit.SILVER_ORE.defaultBlockState()),
                BlockReplacement.replace(deepslateOreReplaceables, BlockInit.DEEPSLATE_SILVER_ORE.defaultBlockState()));

        context.register(OVERWORLD_SILVER_ORE_KEY, new OreFeature(overworldSilverTargets, 4));
        context.register(SILVER_GEODE_KEY, new GeodeFeature(
                new GeodeBlockSettings(
                        BlockStateProvider.holderOf(Blocks.AIR),
                        BlockStateProvider.holderOf(BlockInit.DEEPSLATE_SILVER_ORE),
                        BlockStateProvider.holderOf(BlockInit.SILVER_ORE),
                        BlockStateProvider.holderOf(Blocks.DIRT),
                        BlockStateProvider.holderOf(Blocks.DEEPSLATE),
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
    private static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, CustomWeapons.id(name));
    }
}
