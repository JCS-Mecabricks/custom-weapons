package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.BlockInit;
import github.jcsmecabricks.customweapons.init.EquipmentModelInit;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class CustomWeaponsModelProvider extends FabricModelProvider {
    public CustomWeaponsModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(BlockInit.SILVER_ORE);
        blockStateModelGenerator.createTrivialCube(BlockInit.DEEPSLATE_SILVER_ORE);
        blockStateModelGenerator.createTrivialCube(BlockInit.BLOCK_OF_SILVER);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ItemInit.SICKLE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.declareCustomModelItem(ItemInit.HATCHET);
        itemModelGenerator.generateFlatItem(ItemInit.ELEPHANT_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateTrimmableItem(ItemInit.SILVER_HELMET, EquipmentModelInit.SILVER, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerator.generateTrimmableItem(ItemInit.SILVER_CHESTPLATE, EquipmentModelInit.SILVER, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerator.generateTrimmableItem(ItemInit.SILVER_LEGGINGS, EquipmentModelInit.SILVER, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerator.generateTrimmableItem(ItemInit.SILVER_BOOTS, EquipmentModelInit.SILVER, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerator.generateFlatItem(ItemInit.IRON_ELEPHANT_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.GOLD_ELEPHANT_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.DIAMOND_ELEPHANT_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.NETHERITE_ELEPHANT_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.SILVER_ELEPHANT_ARMOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.COMPOUND_BOW, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.SICKLES, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.SILVER_DETECTOR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.SILVER_PAXEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.SILVER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateSpear(ItemInit.SILVER_SPEAR);
        itemModelGenerator.generateFlatItem(ItemInit.SILVER_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ItemInit.SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);

    }
}