package github.jcsmecabricks.customweapons.custom;

import github.jcsmecabricks.customweapons.init.BlockInit;
import java.util.Objects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SilverDetectorItem extends Item {
    public SilverDetectorItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        if(!context.getLevel().isClientSide()) {
            BlockPos positionClicked = context.getClickedPos();
            Player player = context.getPlayer();
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState state = context.getLevel().getBlockState(positionClicked.below(i));

                if(isSilverOreBlock(state)) {
                    outputSilverOreCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }
            }
            if(!foundBlock) {
                player.sendSystemMessage(Component.literal("No Silver Found!"));
            }
            context.getItemInHand().hurtAndBreak(1, ((ServerLevel) world), ((ServerPlayer) context.getPlayer()),
                    item -> Objects.requireNonNull(context.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
        }
        return InteractionResult.SUCCESS;
    }

    private void outputSilverOreCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + new ItemStack(block).getHoverName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isSilverOreBlock(BlockState state) {
        return state.is(BlockInit.SILVER_ORE) || state.is(BlockInit.DEEPSLATE_SILVER_ORE);
    }
}
