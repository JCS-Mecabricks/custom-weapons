package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.ItemInit;
import github.jcsmecabricks.customweapons.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import java.util.concurrent.CompletableFuture;

public class CustomWeaponsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public CustomWeaponsItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(ItemTags.SWORDS)
                .add(ItemInit.SCYTHE)
                .add(ItemInit.SILVER_HAMMER)
                .add(ItemInit.SICKLES);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ItemInit.SILVER_SPEAR);

        valueLookupBuilder(ModTags.Items.PAXEL_MINEABLE)
                .forceAddTag(ItemTags.PICKAXES)
                .forceAddTag(ItemTags.SHOVELS)
                .forceAddTag(ItemTags.AXES);

        valueLookupBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ItemInit.COMPOUND_BOW);

        valueLookupBuilder(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ItemInit.COMPOUND_BOW);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ItemInit.SILVER);

        valueLookupBuilder(ItemTags.ARMOR_ENCHANTABLE)
                .add(ItemInit.SILVER_HELMET)
                .add(ItemInit.SILVER_CHESTPLATE)
                .add(ItemInit.SILVER_LEGGINGS)
                .add(ItemInit.SILVER_BOOTS);

        valueLookupBuilder(ModTags.Items.SILVER)
                .add(ItemInit.SILVER);

        valueLookupBuilder(ModTags.Items.SHIELDS)
                .add(ItemInit.SILVER);

    }
}
