package github.jcsmecabricks.customweapons.list;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class TagList {
    public static class Blocks {
        public static final TagKey<Block> CUSTOMWEAPONS_TAG = TagKey.create(Registries.BLOCK, CustomWeapons.id("customweapons_tag"));

        public static final TagKey<Block> INCORRECT_FOR_CUSTOMWEAPONS_TOOL =
                TagKey.create(Registries.BLOCK, CustomWeapons.id("incorrect_for_iron_tool"));
        public static final TagKey<Block> INCORRECT_FOR_CUSTOMWEAPONS_TOOL2 =
                TagKey.create(Registries.BLOCK, CustomWeapons.id("incorrect_for_iron_tool2"));
    }
}
