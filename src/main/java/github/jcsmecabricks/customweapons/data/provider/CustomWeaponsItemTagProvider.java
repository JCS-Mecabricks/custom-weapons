package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.ItemInit;
import github.jcsmecabricks.customweapons.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class CustomWeaponsItemTagProvider extends FabricTagProvider<Item> {
    public CustomWeaponsItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
//        getOrCreateTagBuilder(ItemTags.SWORDS)
//                .add(ItemInit.SPEAR)
//                .add(ItemInit.SCYTHE)
//                .add(ItemInit.SILVER_HAMMER)
//                .add(ItemInit.SICKLES);

//        getOrCreateTagBuilder(ModTags.Items.PAXEL_MINEABLE)
//                .forceAddTag(ItemTags.PICKAXES)
//                .forceAddTag(ItemTags.SHOVELS)
//                .forceAddTag(ItemTags.AXES);

//        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
//                .add(ItemInit.COMPOUND_BOW);

//        getOrCreateTagBuilder(ItemTags.CROSSBOW_ENCHANTABLE)
//                .add(ItemInit.COMPOUND_BOW);

//        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
//                .add(ItemInit.SILVER);

//        getOrCreateTagBuilder(ItemTags.ARMOR_ENCHANTABLE)
//                .add(ItemInit.SPARTAN_HELM)
//                .add(ItemInit.SPARTAN_CHESTPLATE)
//                .add(ItemInit.MEDIEVAL_LEGGINGS)
//                .add(ItemInit.MEDIEVAL_BOOTS);

//        getOrCreateTagBuilder(ModTags.Items.SILVER)
//                .add(ItemInit.SILVER);

//        getOrCreateTagBuilder(ModTags.Items.SHIELDS)
//                .add(ItemInit.SILVER);

    }
}
