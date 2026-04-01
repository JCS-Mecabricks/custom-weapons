package github.jcsmecabricks.customweapons.custom;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material,  float attackDamage, float attackSpeed, Properties settings) {
        super(material, BlockTags.SWORD_EFFICIENT, 15, -2f, settings, 0);
    }
}
