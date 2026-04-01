package github.jcsmecabricks.customweapons.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import github.jcsmecabricks.customweapons.tags.ModTags;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class PaxelItem extends MiningToolItem {
    protected static final Map<Block, BlockState> PATH_STATES = Maps.newHashMap(new ImmutableMap.Builder<Block, BlockState>().put(Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.DIRT, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.PODZOL, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.MYCELIUM, Blocks.DIRT_PATH.defaultBlockState()).put(Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState()).build());
    protected static final Map<Block, Block> STRIPPED_BLOCKS = new ImmutableMap.Builder<Block, Block>().put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD).put(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM).put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE).put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM).put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE).put(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD).put(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG).put(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK).build();

    public static EquipmentSlot getSlotForHand(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
    }

    public PaxelItem(ToolMaterial material, Item.Properties settings) {
        super(material, ModTags.Blocks.PAXEL_MINEABLE, 5.0F, -2.0F, settings, 0); // Example values
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, Holder<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.is(Enchantments.MENDING)
                || enchantment.is(Enchantments.EFFICIENCY)
                ||enchantment.is(Enchantments.SILK_TOUCH)
                ||enchantment.is(Enchantments.LOOTING)
                ||enchantment.is(Enchantments.FORTUNE)
                ||enchantment.is(Enchantments.UNBREAKING)
                || super.canBeEnchantedWith(stack, enchantment, context);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = world.getBlockState(blockPos);
        Player playerEntity = context.getPlayer();

        if (shouldCancelStripAttempt(context)) {
            return InteractionResult.PASS;
        }
        Optional<BlockState> optional = this.tryStrip(world, blockPos, playerEntity, world.getBlockState(blockPos));
        if (optional.isEmpty()) {
            if (context.getClickedFace() != Direction.DOWN) {
                BlockState blockState2 = PATH_STATES.get(blockState.getBlock());
                BlockState blockState3 = null;
                if (blockState2 != null && world.getBlockState(blockPos.above()).isAir()) {
                    world.playSound(playerEntity, blockPos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0f, 1.0f);
                    blockState3 = blockState2;
                } else if (blockState.getBlock() instanceof CampfireBlock && blockState.getValue(CampfireBlock.LIT).booleanValue()) {
                    if (!world.isClientSide()) {
                        world.levelEvent(null, LevelEvent.SOUND_EXTINGUISH_FIRE, blockPos, 0);
                    }
                    CampfireBlock.dowse(context.getPlayer(), world, blockPos, blockState);
                    blockState3 = (BlockState)blockState.setValue(CampfireBlock.LIT, false);
                }
                if (blockState3 != null) {
                    if (!world.isClientSide()) {
                        world.setBlock(blockPos, blockState3, Block.UPDATE_ALL_IMMEDIATE);
                        world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(playerEntity, blockState3));
                        if (playerEntity != null) {
                            context.getItemInHand().hurtAndBreak(1, playerEntity, getSlotForHand(context.getHand()));
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.PASS;
            }
            return InteractionResult.PASS;
        }
        ItemStack itemStack = context.getItemInHand();
        if (playerEntity instanceof ServerPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)playerEntity, blockPos, itemStack);
        }
        world.setBlock(blockPos, optional.get(), Block.UPDATE_ALL_IMMEDIATE);
        world.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(playerEntity, optional.get()));
        if (playerEntity != null) {
            itemStack.hurtAndBreak(1, playerEntity, getSlotForHand(context.getHand()));
        }

        return InteractionResult.SUCCESS;
    }

    private static boolean shouldCancelStripAttempt(UseOnContext context) {
        Player playerEntity = context.getPlayer();
        return context.getHand().equals((Object) InteractionHand.MAIN_HAND) && playerEntity.getOffhandItem().is(Items.SHIELD) && !playerEntity.isSecondaryUseActive();
    }

    private Optional<BlockState> tryStrip(Level world, BlockPos pos, @Nullable Player player, BlockState state) {
        Optional<BlockState> optional = this.getStrippedState(state);
        if (optional.isPresent()) {
            world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f);
            return optional;
        }
        Optional<BlockState> optional2 = WeatheringCopper.getPrevious(state);
        if (optional2.isPresent()) {
            world.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f);
            world.levelEvent(player, LevelEvent.PARTICLES_SCRAPE, pos, 0);
            return optional2;
        }
        Optional<BlockState> optional3 = Optional.ofNullable((Block) HoneycombItem.WAX_OFF_BY_BLOCK.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
        if (optional3.isPresent()) {
            world.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f);
            world.levelEvent(player, LevelEvent.PARTICLES_WAX_OFF, pos, 0);
            return optional3;
        }
        return Optional.empty();
    }

    private Optional<BlockState> getStrippedState(BlockState state) {
        return Optional.ofNullable(STRIPPED_BLOCKS.get(state.getBlock())).map(block -> (BlockState)block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)));
    }
}