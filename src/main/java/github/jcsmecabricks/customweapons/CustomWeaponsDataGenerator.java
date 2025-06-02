package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.data.generator.CustomWeaponsEnchantmentGenerator;
import github.jcsmecabricks.customweapons.data.generator.CustomWeaponsWorldGenerator;
import github.jcsmecabricks.customweapons.data.provider.*;
import github.jcsmecabricks.customweapons.worldgen.ConfiguredFeatureInit;
import github.jcsmecabricks.customweapons.worldgen.PlacedFeatureInit;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class CustomWeaponsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(CustomWeaponsBlockLootTableProvider::new);
		pack.addProvider(CustomWeaponsBlockTagProvider::new);
		pack.addProvider(CustomWeaponsItemTagProvider::new);
		pack.addProvider(CustomWeaponsWorldGenerator::new);
		pack.addProvider(CustomWeaponsRecipeProvider::new);
		pack.addProvider(CustomWeaponsEnchantmentGenerator::new);
		pack.addProvider(CustomWeaponsModelProvider::new);
	}

	private static String hasTag(TagKey<Item> tag) {
		return "has_" + tag.id().toString();
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureInit::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatureInit::bootstrap);
	}
}
