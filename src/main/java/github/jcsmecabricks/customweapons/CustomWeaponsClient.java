package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.client.DeadEyeHudOverlay;
import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.entity.client.*;
import github.jcsmecabricks.customweapons.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class CustomWeaponsClient implements ClientModInitializer {

    public CustomWeaponsClient() {
    }

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HatchetProjectileModel.HATCHET, HatchetProjectileModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT, ElephantModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT_ARMOR, ElephantModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.HATCHET, HatchetProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.ELEPHANT, ElephantRenderer::new);

        KeyInputHandler.registerKeyInputs();

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.of(CustomWeapons.MOD_ID, "dead_eye"),
                new DeadEyeHudOverlay()
        );
    }
}
