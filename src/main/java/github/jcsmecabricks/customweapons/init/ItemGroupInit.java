package github.jcsmecabricks.customweapons.init;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Optional;

public class ItemGroupInit {
    public static final Text CUSTOMWEAPONS_TITLE = Text.translatable("itemGroup." + CustomWeapons.MOD_ID + ".customweapons_group");
    public static final ItemGroup CUSTOMWEAPONS_GROUP = register("customweapons_group", FabricItemGroup.builder()
            .displayName(CUSTOMWEAPONS_TITLE)
            .icon(ItemInit.SICKLE::getDefaultStack)
            .entries((displayContext, entries) -> Registries.ITEM.getIds()
                    .stream()
                    .filter(key -> key.getNamespace().equals(CustomWeapons.MOD_ID))
                    .map(Registries.ITEM::getOptionalValue)
                    .map(Optional::orElseThrow)
                    .forEach(entries::add))
            .build());
    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, CustomWeapons.id(name), itemGroup);
    }
    public static void load() {

    }
}
