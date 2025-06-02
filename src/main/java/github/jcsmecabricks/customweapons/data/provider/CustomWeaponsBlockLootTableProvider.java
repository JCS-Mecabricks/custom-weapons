package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class CustomWeaponsBlockLootTableProvider extends FabricBlockLootTableProvider {
    public CustomWeaponsBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockInit.SILVER_ORE);
        addDrop(BlockInit.DEEPSLATE_SILVER_ORE);
        addDrop(BlockInit.BLOCK_OF_SILVER);
    }
}
