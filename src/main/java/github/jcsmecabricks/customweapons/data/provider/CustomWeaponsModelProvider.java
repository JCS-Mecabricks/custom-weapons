package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.BlockInit;
import github.jcsmecabricks.customweapons.init.EquipmentModelInit;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class CustomWeaponsModelProvider extends FabricModelProvider {
    public CustomWeaponsModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.DEEPSLATE_SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(BlockInit.BLOCK_OF_SILVER);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ItemInit.SICKLE, Models.GENERATED);
        itemModelGenerator.register(ItemInit.HATCHET);
        itemModelGenerator.register(ItemInit.ELEPHANT_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.registerArmor(ItemInit.SPARTAN_HELM, EquipmentModelInit.SILVER, ItemModelGenerator.HELMET_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ItemInit.SPARTAN_CHESTPLATE, EquipmentModelInit.SILVER, ItemModelGenerator.CHESTPLATE_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ItemInit.MEDIEVAL_LEGGINGS, EquipmentModelInit.SILVER, ItemModelGenerator.LEGGINGS_TRIM_ID_PREFIX, false);
        itemModelGenerator.registerArmor(ItemInit.MEDIEVAL_BOOTS, EquipmentModelInit.SILVER, ItemModelGenerator.BOOTS_TRIM_ID_PREFIX, false);
        itemModelGenerator.register(ItemInit.IRON_ELEPHANT_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ItemInit.GOLD_ELEPHANT_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ItemInit.DIAMOND_ELEPHANT_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ItemInit.NETHERITE_ELEPHANT_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ItemInit.SILVER_ELEPHANT_ARMOR, Models.GENERATED);
        itemModelGenerator.register(ItemInit.COMPOUND_BOW, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.SICKLES, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.SILVER_DETECTOR, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.SILVER_PAXEL, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.SILVER, Models.GENERATED);
        itemModelGenerator.register(ItemInit.SPEAR, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.SILVER_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ItemInit.SCYTHE, Models.HANDHELD);

    }
}