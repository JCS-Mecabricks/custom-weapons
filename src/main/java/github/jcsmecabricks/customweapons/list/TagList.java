package github.jcsmecabricks.customweapons.list;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class TagList {
    public static class Blocks {
        public static final TagKey<Block> CUSTOMWEAPONS_TAG = TagKey.of(RegistryKeys.BLOCK, CustomWeapons.id("customweapons_tag"));

        public static final TagKey<Block> INCORRECT_FOR_CUSTOMWEAPONS_TOOL =
                TagKey.of(RegistryKeys.BLOCK, CustomWeapons.id("incorrect_for_iron_tool"));
        public static final TagKey<Block> INCORRECT_FOR_CUSTOMWEAPONS_TOOL2 =
                TagKey.of(RegistryKeys.BLOCK, CustomWeapons.id("incorrect_for_iron_tool2"));
    }
}
