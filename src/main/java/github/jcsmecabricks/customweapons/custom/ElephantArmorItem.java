package github.jcsmecabricks.customweapons.custom;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;

public class ElephantArmorItem extends Item implements CustomWeaponsArmorMaterial<ArmorMaterial>{
    private final ArmorMaterial material;
    public ElephantArmorItem(ArmorMaterial material, Settings settings) {
        super(settings.armor(material, EquipmentType.BODY));
        this.material = material;
    }

    @Override
    public ArmorMaterial getMaterial() {
        return material;
    }
}
