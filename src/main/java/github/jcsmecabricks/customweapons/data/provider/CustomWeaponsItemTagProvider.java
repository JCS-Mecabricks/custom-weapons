package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.ItemInit;
import github.jcsmecabricks.customweapons.tags.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class CustomWeaponsItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public CustomWeaponsItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        tag(ItemTags.SWORDS)
                .add(ItemInit.getRK(ItemInit.SCYTHE))
                .add(ItemInit.getRK(ItemInit.SILVER_HAMMER))
                .add(ItemInit.getRK(ItemInit.SICKLES));

        tag(ItemTags.SWORDS)
                .add(ItemInit.getRK(ItemInit.SILVER_SPEAR));

        tag(ItemTags.AXES).add(ItemInit.getRK(Items.IRON_AXE)).add(ItemInit.getRK(ItemInit.SILVER_PAXEL));
        tag(ItemTags.AXES).add(ItemInit.getRK(Items.IRON_SHOVEL)).add(ItemInit.getRK(ItemInit.SILVER_PAXEL));
        tag(ItemTags.AXES).add(ItemInit.getRK(Items.IRON_PICKAXE)).add(ItemInit.getRK(ItemInit.SILVER_PAXEL));

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(ItemInit.getRK(ItemInit.COMPOUND_BOW));

        tag(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ItemInit.getRK(ItemInit.COMPOUND_BOW));

        tag(ItemTags.TRIM_MATERIALS)
                .add(ItemInit.getRK(ItemInit.SILVER));

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ItemInit.getRK(ItemInit.SILVER_HELMET))
                .add(ItemInit.getRK(ItemInit.SILVER_CHESTPLATE))
                .add(ItemInit.getRK(ItemInit.SILVER_LEGGINGS))
                .add(ItemInit.getRK(ItemInit.SILVER_BOOTS));

        tag(ModTags.Items.SILVER)
                .add(ItemInit.getRK(ItemInit.SILVER));

        tag(ModTags.Items.SHIELDS)
                .add(ItemInit.getRK(ItemInit.SILVER));

    }
}
