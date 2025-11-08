package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.advancement.criterion.ModCriteria;
import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import github.jcsmecabricks.customweapons.event.DeadEyeEvents;
import github.jcsmecabricks.customweapons.event.PlayerTickHandler;
import github.jcsmecabricks.customweapons.init.*;
import github.jcsmecabricks.customweapons.networking.ModMessages;
import github.jcsmecabricks.customweapons.networking.packet.DeadEyeSyncDataS2CPacket;
import github.jcsmecabricks.customweapons.sound.ModSounds;
import github.jcsmecabricks.customweapons.worldgen.ModEntitySpawns;
import github.jcsmecabricks.customweapons.worldgen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomWeapons implements ModInitializer {
	public static final String MOD_ID = "custom-weapons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading...");
		ItemInit.load();
		ModWorldGeneration.generateWorldGeneration();
		BlockInit.load();
		DeadEyeEvents.register();
		ModSounds.registerSounds();
        ModCriteria.loadCriteria();

        ModMessages.registerC2SPackets();
		ModEntitySpawns.addSpawns();
		ModEntities.registerModEntities();
		ItemGroupInit.load();
		EnchantmentInit.load();
		registerCustomTrades();

		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());

		FabricDefaultAttributeRegistry.register(ModEntities.ELEPHANT, ElephantEntity.createElephantAttributes());

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if(entity instanceof LivingEntity livingEntity && !world.isClient()) {
				if(player.getMainHandStack().getItem() == ItemInit.SCYTHE) {
					player.sendMessage(Text.literal("The Grim Reaper just hit someone with a SCYTHE! OH NO!"), false);
					livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 2));
					livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 4));
				}

				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(Items.MACE, ItemInit.SILVER_HAMMER);
			entries.addAfter(ItemInit.SILVER_HAMMER, ItemInit.SICKLE);
			entries.addAfter(ItemInit.SICKLE, ItemInit.SICKLES);
			entries.addAfter(ItemInit.SICKLES, ItemInit.SPEAR);
		});
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.IRON_BOOTS, ItemInit.SILVER_HELMET);
            entries.addAfter(ItemInit.SILVER_HELMET, ItemInit.SILVER_CHESTPLATE);
            entries.addAfter(ItemInit.SILVER_CHESTPLATE, ItemInit.SILVER_LEGGINGS);
            entries.addAfter(ItemInit.SILVER_LEGGINGS, ItemInit.SILVER_BOOTS);
        });
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.IRON_INGOT, ItemInit.SILVER);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			entries.addAfter(Items.REDSTONE_ORE, BlockInit.SILVER_ORE);
			entries.addAfter(BlockInit.SILVER_ORE, BlockInit.DEEPSLATE_SILVER_ORE);
			entries.addAfter(BlockInit.DEEPSLATE_SILVER_ORE, Blocks.REDSTONE_BLOCK);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			entries.addAfter(Items.REDSTONE_ORE, BlockInit.SILVER_ORE);
			entries.addAfter(BlockInit.SILVER_ORE, BlockInit.DEEPSLATE_SILVER_ORE);
			entries.addAfter(BlockInit.DEEPSLATE_SILVER_ORE, Blocks.REDSTONE_BLOCK);
		});


	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}

	private static void registerCustomTrades() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.TOOLSMITH, 1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 1),
					new ItemStack(ItemInit.SILVER, 1), 20, 3, 0.04f
			));
		});
	}
}