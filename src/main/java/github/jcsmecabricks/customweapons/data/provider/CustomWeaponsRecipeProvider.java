package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.BlockInit;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomWeaponsRecipeProvider extends FabricRecipeProvider {
    public CustomWeaponsRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.COMBAT, ItemInit.SICKLE)
                        .define('I', Items.IRON_INGOT)
                        .define('S', Items.STICK)
                        .pattern(" II")
                        .pattern("  I")
                        .pattern(" S ")
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.SCYTHE)
                        .define('E', ItemInit.SILVER)
                        .define('S', Items.STICK)
                        .pattern("EEE")
                        .pattern("S  ")
                        .pattern("S  ")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.SILVER_HAMMER)
                        .define('E', BlockInit.BLOCK_OF_SILVER)
                        .define('S', Items.STICK)
                        .pattern("EEE")
                        .pattern(" S ")
                        .pattern(" S ")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.SILVER_PAXEL)
                        .define('E', Items.IRON_AXE)
                        .define('J', Items.IRON_PICKAXE)
                        .define('A', Items.IRON_SHOVEL)
                        .define('K', BlockInit.BLOCK_OF_SILVER)
                        .pattern(" E ")
                        .pattern("AKJ")
                        .unlockedBy(getHasName(BlockInit.BLOCK_OF_SILVER), has(BlockInit.BLOCK_OF_SILVER))
                        .unlockedBy(getHasName(Items.IRON_SHOVEL), has(Items.IRON_SHOVEL))
                        .unlockedBy(getHasName(Items.IRON_AXE), has(Items.IRON_AXE))
                        .unlockedBy(getHasName(Items.IRON_PICKAXE), has(Items.IRON_PICKAXE))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ItemInit.SILVER_CHESTPLATE)
                        .define('S', ItemInit.SILVER)
                        .pattern("S S")
                        .pattern("SSS")
                        .pattern("SSS")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ItemInit.SILVER_HELMET)
                        .define('S', ItemInit.SILVER)
                        .pattern("SSS")
                        .pattern("S S")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ItemInit.SILVER_LEGGINGS)
                        .define('S', ItemInit.SILVER)
                        .pattern("SSS")
                        .pattern("S S")
                        .pattern("S S")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ItemInit.SILVER_BOOTS)
                        .define('S', ItemInit.SILVER)
                        .pattern("S S")
                        .pattern("S S")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.HATCHET, 3)
                        .define('S', ItemInit.SILVER)
                        .define('T', Items.STICK)
                        .pattern("SSS")
                        .pattern("ST ")
                        .pattern(" T ")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.DIAMOND_ELEPHANT_ARMOR)
                        .define('T', Items.DIAMOND)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.GOLD_ELEPHANT_ARMOR)
                        .define('T', Items.GOLD_INGOT)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.IRON_ELEPHANT_ARMOR)
                        .define('T', Items.IRON_INGOT)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.NETHERITE_ELEPHANT_ARMOR)
                        .define('T', Items.NETHERITE_INGOT)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ItemInit.SILVER_ELEPHANT_ARMOR)
                        .define('T', ItemInit.SILVER)
                        .pattern("TTT")
                        .pattern("TTT")
                        .pattern("T T")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.COMPOUND_BOW)
                        .define('B', Items.BOW)
                        .define('E', ItemInit.SILVER)
                        .define('S', Items.STRING)
                        .pattern(" S ")
                        .pattern("EBE")
                        .pattern(" S ")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .unlockedBy(getHasName(Items.BOW), has(Items.BOW))
                        .save(output);

                shaped(RecipeCategory.COMBAT, ItemInit.SILVER_SPEAR)
                        .define('L', ItemInit.SILVER)
                        .define('S', Items.STICK)
                        .pattern("  L")
                        .pattern(" S ")
                        .pattern("S  ")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, BlockInit.BLOCK_OF_SILVER)
                        .define('S', ItemInit.SILVER)
                        .pattern("SSS")
                        .pattern("SSS")
                        .pattern("SSS")
                        .unlockedBy(getHasName(ItemInit.SILVER), has(ItemInit.SILVER))
                        .save(output);

                shaped(RecipeCategory.TOOLS, ItemInit.SILVER_DETECTOR)
                        .define('S', Items.IRON_INGOT)
                        .define('E', Items.STICK)
                        .pattern(" E ")
                        .pattern(" E ")
                        .pattern("SSS")
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .save(output);

                shapeless(RecipeCategory.TOOLS, ItemInit.SILVER, 9)
                        .requires(BlockInit.BLOCK_OF_SILVER)
                        .unlockedBy(getHasName(BlockInit.BLOCK_OF_SILVER), has(BlockInit.BLOCK_OF_SILVER))
                        .save(output);

                shapeless(RecipeCategory.COMBAT, ItemInit.SICKLES)
                        .requires(ItemInit.SICKLE, 2)
                        .unlockedBy(getHasName(ItemInit.SICKLE), has(ItemInit.SICKLE))
                        .save(output);

                List<ItemLike> customweaponsOres = List.of(BlockInit.SILVER_ORE, BlockInit.DEEPSLATE_SILVER_ORE);

                oreBlasting(customweaponsOres,
                        RecipeCategory.TOOLS,
                        CookingBookCategory.MISC,
                        ItemInit.SILVER,
                        0.3f,
                        100,
                        "customweapons");

                oreSmelting(customweaponsOres,
                        RecipeCategory.TOOLS,
                        CookingBookCategory.MISC,
                        ItemInit.SILVER,
                        0.3f,
                        200,
                        "customweapons");

            }
            private static String hasTag(TagKey<Item> tag) {
                return "has_" + tag.location().toString();
            }
        };
    }

    @Override
    public String getName() {
        return "Custom Weapons Recipes";
    }
}