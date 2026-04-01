package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.BlockInit;
import github.jcsmecabricks.customweapons.list.TagList;
import github.jcsmecabricks.customweapons.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import java.util.concurrent.CompletableFuture;

public class CustomWeaponsBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public CustomWeaponsBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
               .add(BlockInit.DEEPSLATE_SILVER_ORE)
               .add(BlockInit.BLOCK_OF_SILVER)
               .add(BlockInit.SILVER_ORE);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
               .add(BlockInit.DEEPSLATE_SILVER_ORE)
               .add(BlockInit.BLOCK_OF_SILVER)
               .add(BlockInit.SILVER_ORE);

        valueLookupBuilder(TagList.Blocks.CUSTOMWEAPONS_TAG)
               .add(BlockInit.DEEPSLATE_SILVER_ORE)
               .add(BlockInit.BLOCK_OF_SILVER)
               .add(BlockInit.SILVER_ORE);

        valueLookupBuilder(TagList.Blocks.INCORRECT_FOR_CUSTOMWEAPONS_TOOL);

        valueLookupBuilder(ModTags.Blocks.PAXEL_MINEABLE)
               .forceAddTag(BlockTags.MINEABLE_WITH_PICKAXE)
               .forceAddTag(BlockTags.MINEABLE_WITH_AXE)
               .forceAddTag(BlockTags.MINEABLE_WITH_SHOVEL);

    }
}
