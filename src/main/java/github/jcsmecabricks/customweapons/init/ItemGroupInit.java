package github.jcsmecabricks.customweapons.init;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import java.util.Optional;

public class ItemGroupInit {
    public static final Component CUSTOMWEAPONS_TITLE = Component.translatable("itemGroup." + CustomWeapons.MOD_ID + ".customweapons_group");
    public static final CreativeModeTab CUSTOMWEAPONS_GROUP = register("customweapons_group", FabricCreativeModeTab.builder()
            .title(CUSTOMWEAPONS_TITLE)
            .icon(ItemInit.SICKLE::getDefaultInstance)
            .displayItems((displayContext, entries) -> BuiltInRegistries.ITEM.keySet()
                    .stream()
                    .filter(key -> key.getNamespace().equals(CustomWeapons.MOD_ID))
                    .map(BuiltInRegistries.ITEM::getOptional)
                    .map(Optional::orElseThrow)
                    .forEach(entries::accept))
            .build());
    public static <T extends CreativeModeTab> T register(String name, T itemGroup) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CustomWeapons.id(name), itemGroup);
    }
    public static void load() {

    }
}
