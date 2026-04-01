package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.data.generator.CustomWeaponsEnchantmentGenerator;
import github.jcsmecabricks.customweapons.data.generator.CustomWeaponsWorldGenerator;
import github.jcsmecabricks.customweapons.data.provider.*;
import github.jcsmecabricks.customweapons.worldgen.ConfiguredFeatureInit;
import github.jcsmecabricks.customweapons.worldgen.PlacedFeatureInit;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

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
		return "has_" + tag.location().toString();
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureInit::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, PlacedFeatureInit::bootstrap);
	}
}
