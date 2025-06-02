package github.jcsmecabricks.customweapons.custom;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;

public class CustomWeaponsArmorItem extends Item implements CustomWeaponsArmorMaterial<ArmorMaterial> {
    private final ArmorMaterial material;

    public CustomWeaponsArmorItem(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(settings.armor(material, type));
        this.material = material;
    }

    @Override
    public ArmorMaterial getMaterial() {
        return material;
    }
}
