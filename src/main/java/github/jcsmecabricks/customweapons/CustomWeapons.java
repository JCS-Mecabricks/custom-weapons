package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.advancement.criterion.ModCriteria;
import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import github.jcsmecabricks.customweapons.event.DeadEyeEvents;
import github.jcsmecabricks.customweapons.event.PlayerTickHandler;
import github.jcsmecabricks.customweapons.init.*;
import github.jcsmecabricks.customweapons.networking.ModMessages;
import github.jcsmecabricks.customweapons.sound.ModSounds;
import github.jcsmecabricks.customweapons.worldgen.ModEntitySpawns;
import github.jcsmecabricks.customweapons.worldgen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.block.Blocks;
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

		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());

		FabricDefaultAttributeRegistry.register(ModEntities.ELEPHANT, ElephantEntity.createElephantAttributes());

		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if(entity instanceof LivingEntity livingEntity && !world.isClientSide()) {
				if(player.getMainHandItem().getItem() == ItemInit.SCYTHE) {
					player.sendSystemMessage(Component.literal("The Grim Reaper just hit someone with a SCYTHE! OH NO!"));
					livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 500, 2));
					livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 500, 2));
				}

				return InteractionResult.PASS;
			}

			return InteractionResult.PASS;
		});

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(entries -> {
			entries.insertAfter(Items.MACE, ItemInit.SILVER_HAMMER);
			entries.insertAfter(ItemInit.SILVER_HAMMER, ItemInit.SICKLE);
			entries.insertAfter(ItemInit.SICKLE, ItemInit.SICKLES);
			entries.insertAfter(ItemInit.SICKLES, ItemInit.SILVER_SPEAR);
		});
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(entries -> {
            entries.insertAfter(Items.IRON_BOOTS, ItemInit.SILVER_HELMET);
            entries.insertAfter(ItemInit.SILVER_HELMET, ItemInit.SILVER_CHESTPLATE);
            entries.insertAfter(ItemInit.SILVER_CHESTPLATE, ItemInit.SILVER_LEGGINGS);
            entries.insertAfter(ItemInit.SILVER_LEGGINGS, ItemInit.SILVER_BOOTS);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
			entries.insertAfter(Items.IRON_INGOT, ItemInit.SILVER);
		});
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
			entries.insertAfter(Items.REDSTONE_ORE, BlockInit.SILVER_ORE);
			entries.insertAfter(BlockInit.SILVER_ORE, BlockInit.DEEPSLATE_SILVER_ORE);
			entries.insertAfter(BlockInit.DEEPSLATE_SILVER_ORE, Blocks.REDSTONE_BLOCK);
		});

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
			entries.insertAfter(Items.REDSTONE_ORE, BlockInit.SILVER_ORE);
			entries.insertAfter(BlockInit.SILVER_ORE, BlockInit.DEEPSLATE_SILVER_ORE);
			entries.insertAfter(BlockInit.DEEPSLATE_SILVER_ORE, Blocks.REDSTONE_BLOCK);
		});


	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}