package github.jcsmecabricks.customweapons.init;

import com.google.common.collect.Maps;
import github.jcsmecabricks.customweapons.tags.ModTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import java.util.EnumMap;
import java.util.Map;

public interface ArmorMaterialInit {
     ArmorMaterial SILVER = new ArmorMaterial(
            30, createDefenseMap(2,
            6,
            6,
            2,
            9),
            18, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F,
            ModTags.Items.SILVER, EquipmentModelInit.SILVER
    );

    private static Map<ArmorType, Integer> createDefenseMap(int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense, int bodyDefense) {
        return Maps.newEnumMap(
                Map.of(
                        ArmorType.BOOTS,
                        bootsDefense,
                        ArmorType.LEGGINGS,
                        leggingsDefense,
                        ArmorType.CHESTPLATE,
                        chestplateDefense,
                        ArmorType.HELMET,
                        helmetDefense,
                        ArmorType.BODY,
                        bodyDefense
                )
        );
    }
}
