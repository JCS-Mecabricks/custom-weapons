package github.jcsmecabricks.customweapons.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class CustomWeaponsArmorItem extends Item implements CustomWeaponsArmorMaterial<ArmorMaterial> {
    private final ArmorMaterial material;

    public CustomWeaponsArmorItem(ArmorMaterial material, ArmorType type, Properties settings) {
        super(settings.humanoidArmor(material, type));
        this.material = material;
    }

    @Override
    public ArmorMaterial getMaterial() {
        return material;
    }
}
