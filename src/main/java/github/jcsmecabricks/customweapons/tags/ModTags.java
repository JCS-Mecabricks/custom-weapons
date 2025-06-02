package github.jcsmecabricks.customweapons.tags;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> SILVER = createTag("silver");
        public static final TagKey<Item> PAXEL_MINEABLE = createTag("paxel_mineable");
        public static final TagKey<Item> SHIELDS = createTag("shields");


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CustomWeapons.MOD_ID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> PAXEL_MINEABLE = createTag("paxel_mineable");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CustomWeapons.MOD_ID, name));
        }
    }
}
