package github.jcsmecabricks.customweapons.data.provider;

import github.jcsmecabricks.customweapons.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import java.util.concurrent.CompletableFuture;

public class CustomWeaponsBlockLootTableProvider extends FabricBlockLootSubProvider {
    public CustomWeaponsBlockLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(BlockInit.SILVER_ORE);
        dropSelf(BlockInit.DEEPSLATE_SILVER_ORE);
        dropSelf(BlockInit.BLOCK_OF_SILVER);
    }
}
