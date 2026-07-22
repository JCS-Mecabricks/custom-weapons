package github.jcsmecabricks.customweapons.init;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockInit {
    public static final Block SILVER_ORE = registerBlock("silver_ore", new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "silver_ore")))
            .strength(3.0F, 3.0F)
            .requiresCorrectToolForDrops()));
    public static final Block DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "deepslate_silver_ore")))
            .strength(4.5F, 3.0F)
            .requiresCorrectToolForDrops()));

    public static ResourceKey<Block> getRK(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block).get();
    }

    public static final Block BLOCK_OF_SILVER = registerBlock("block_of_silver", new Block(BlockBehaviour.Properties.of()
            .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "block_of_silver")))
            .strength(7.0F, 4.0F)
            .requiresCorrectToolForDrops()));

    public static <T extends Block> T register(String name, T block) {
        CustomWeapons.LOGGER.info("Registering block and item for: {}", name);

        Identifier blockId = Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name);
        Registry.register(BuiltInRegistries.BLOCK, blockId, block);
        return block;
    }


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name),
                new BlockItem(block, new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name))).useBlockDescriptionPrefix()));
    }
    public static void load() {}
}
