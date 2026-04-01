package github.jcsmecabricks.customweapons.init;

import github.jcsmecabricks.customweapons.CustomWeapons;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class EquipmentModelInit implements EquipmentAssets {
    public static final ResourceKey<EquipmentAsset> SILVER = createId("silver");

    public static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, CustomWeapons.id(name));
    }
}