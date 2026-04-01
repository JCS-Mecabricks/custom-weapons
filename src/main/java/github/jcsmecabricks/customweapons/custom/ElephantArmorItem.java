package github.jcsmecabricks.customweapons.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

public class ElephantArmorItem extends Item implements CustomWeaponsArmorMaterial<ArmorMaterial>{
    private final ArmorMaterial material;
    public ElephantArmorItem(ArmorMaterial material, Properties settings) {
        super(settings.humanoidArmor(material, ArmorType.BODY));
        this.material = material;
    }

    @Override
    public ArmorMaterial getMaterial() {
        return material;
    }
}
