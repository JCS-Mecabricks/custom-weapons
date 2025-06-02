package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CustomWeaponsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HatchetProjectileModel.HATCHET, HatchetProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.HATCHET, HatchetProjectileRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT, ElephantModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ELEPHANT, ElephantRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT_ARMOR, ElephantModel::getTexturedModelData);
    }
}
