//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package github.jcsmecabricks.customweapons.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SwordItem extends Item {
    public SwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties settings) {
        super(material.applySwordProperties(settings, attackDamage, attackSpeed));
    }

    public boolean canMine(BlockState state, Level world, BlockPos pos, Player miner) {
        return !miner.isCreative();
    }

    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}
