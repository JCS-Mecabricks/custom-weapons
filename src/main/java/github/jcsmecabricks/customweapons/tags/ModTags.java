package github.jcsmecabricks.customweapons.tags;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> SILVER = createTag("silver");
        public static final TagKey<Item> PAXEL_MINEABLE = createTag("paxel_mineable");
        public static final TagKey<Item> SHIELDS = createTag("shields");


        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> PAXEL_MINEABLE = createTag("paxel_mineable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, name));
        }
    }
}
