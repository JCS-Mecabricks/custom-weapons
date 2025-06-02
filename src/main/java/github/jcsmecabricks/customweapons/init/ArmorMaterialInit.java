package github.jcsmecabricks.customweapons.init;

import com.google.common.collect.Maps;
import github.jcsmecabricks.customweapons.tags.ModTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.Map;

public interface ArmorMaterialInit {
     ArmorMaterial SILVER = new ArmorMaterial(
            30, createDefenseMap(2,
            4,
            6,
            2,
            9),
            15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F,
            ModTags.Items.SILVER, EquipmentModelInit.SILVER
    );

    private static Map<EquipmentType, Integer> createDefenseMap(int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense, int bodyDefense) {
        return Maps.newEnumMap(
                Map.of(
                        EquipmentType.BOOTS,
                        bootsDefense,
                        EquipmentType.LEGGINGS,
                        leggingsDefense,
                        EquipmentType.CHESTPLATE,
                        chestplateDefense,
                        EquipmentType.HELMET,
                        helmetDefense,
                        EquipmentType.BODY,
                        bodyDefense
                )
        );
    }
}
