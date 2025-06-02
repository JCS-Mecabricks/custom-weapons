package github.jcsmecabricks.customweapons.custom;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material,  float attackDamage, float attackSpeed, Settings settings) {
        super(material, BlockTags.SWORD_EFFICIENT, 15, -2f, settings, 0);
    }
}
