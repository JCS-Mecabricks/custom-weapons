package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.BlockInit;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomWeaponsRecipeProvider extends FabricRecipeProvider {
    public CustomWeaponsRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.COMBAT, ItemInit.SICKLE)
                        .input('I', Items.IRON_INGOT)
                        .input('S', Items.STICK)
                        .pattern(" II")
                        .pattern("  I")
                        .pattern(" S ")
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.SCYTHE)
                        .input('E', ItemInit.SILVER)
                        .input('S', Items.STICK)
                        .pattern("EEE")
                        .pattern("S  ")
                        .pattern("S  ")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.SILVER_HAMMER)
                        .input('E', BlockInit.BLOCK_OF_SILVER)
                        .input('S', Items.STICK)
                        .pattern("EEE")
                        .pattern(" S ")
                        .pattern(" S ")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.SILVER_PAXEL)
                        .input('E', Items.IRON_AXE)
                        .input('J', Items.IRON_PICKAXE)
                        .input('A', Items.IRON_SHOVEL)
                        .input('K', BlockInit.BLOCK_OF_SILVER)
                        .pattern(" E ")
                        .pattern("AKJ")
                        .criterion(hasItem(BlockInit.BLOCK_OF_SILVER), conditionsFromItem(BlockInit.BLOCK_OF_SILVER))
                        .criterion(hasItem(Items.IRON_SHOVEL), conditionsFromItem(Items.IRON_SHOVEL))
                        .criterion(hasItem(Items.IRON_AXE), conditionsFromItem(Items.IRON_AXE))
                        .criterion(hasItem(Items.IRON_PICKAXE), conditionsFromItem(Items.IRON_PICKAXE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ItemInit.SPARTAN_CHESTPLATE)
                        .input('S', ItemInit.SILVER)
                        .input('G', Items.GOLD_INGOT)
                        .pattern("S S")
                        .pattern("GGG")
                        .pattern("GGG")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ItemInit.SPARTAN_HELM)
                        .input('S', ItemInit.SILVER)
                        .input('G', Items.GOLD_INGOT)
                        .input('F', Items.FEATHER)
                        .pattern(" F ")
                        .pattern("GGG")
                        .pattern("GSG")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .criterion(hasItem(Items.FEATHER), conditionsFromItem(Items.FEATHER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ItemInit.MEDIEVAL_LEGGINGS)
                        .input('S', ItemInit.SILVER)
                        .input('I', Items.IRON_INGOT)
                        .pattern("SIS")
                        .pattern("I I")
                        .pattern("S S")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ItemInit.MEDIEVAL_BOOTS)
                        .input('S', ItemInit.SILVER)
                        .pattern("   ")
                        .pattern("S S")
                        .pattern("S S")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.HATCHET, 3)
                        .input('S', ItemInit.SILVER)
                        .input('T', Items.STICK)
                        .pattern("SSS")
                        .pattern("ST ")
                        .pattern(" T ")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.DIAMOND_ELEPHANT_ARMOR)
                        .input('T', Items.DIAMOND)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.GOLD_ELEPHANT_ARMOR)
                        .input('T', Items.GOLD_INGOT)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .criterion(hasItem(Items.GOLD_INGOT), conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.IRON_ELEPHANT_ARMOR)
                        .input('T', Items.IRON_INGOT)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.NETHERITE_ELEPHANT_ARMOR)
                        .input('T', Items.NETHERITE_INGOT)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ItemInit.SILVER_ELEPHANT_ARMOR)
                        .input('T', ItemInit.SILVER)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.COMPOUND_BOW)
                        .input('B', Items.BOW)
                        .input('E', ItemInit.SILVER)
                        .input('S', Items.STRING)
                        .pattern(" S ")
                        .pattern("EBE")
                        .pattern(" S ")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .criterion(hasItem(Items.BOW), conditionsFromItem(Items.BOW))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ItemInit.SPEAR)
                        .input('L', ItemInit.SILVER)
                        .input('S', Items.STICK)
                        .pattern("  L")
                        .pattern(" S ")
                        .pattern("S  ")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.BLOCK_OF_SILVER)
                        .input('S', ItemInit.SILVER)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern("SSS")
                        .criterion(hasItem(ItemInit.SILVER), conditionsFromItem(ItemInit.SILVER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ItemInit.SILVER_DETECTOR)
                        .input('S', Items.IRON_INGOT)
                        .input('E', Items.STICK)
                        .pattern(" E ")
                        .pattern(" E ")
                        .pattern("SSS")
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.TOOLS, ItemInit.SILVER, 9)
                        .input(BlockInit.BLOCK_OF_SILVER)
                        .criterion(hasItem(BlockInit.BLOCK_OF_SILVER), conditionsFromItem(BlockInit.BLOCK_OF_SILVER))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.COMBAT, ItemInit.SICKLES)
                        .input(ItemInit.SICKLE, 2)
                        .criterion(hasItem(ItemInit.SICKLE), conditionsFromItem(ItemInit.SICKLE))
                        .offerTo(exporter);

                List<ItemConvertible> customweaponsOres = List.of(BlockInit.SILVER_ORE, BlockInit.DEEPSLATE_SILVER_ORE);

                offerBlasting(customweaponsOres,
                        RecipeCategory.TOOLS,
                        ItemInit.SILVER,
                        0.3f,
                        100,
                        "customweapons");

                offerSmelting(customweaponsOres,
                        RecipeCategory.TOOLS,
                        ItemInit.SILVER,
                        0.3f,
                        200,
                        "customweapons");

            }
            private static String hasTag(TagKey<Item> tag) {
                return "has_" + tag.id().toString();
            }
        };
    }

    @Override
    public String getName() {
        return "Custom Weapons Recipes";
    }
}