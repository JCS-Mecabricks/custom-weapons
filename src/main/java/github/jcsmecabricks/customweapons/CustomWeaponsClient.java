package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.client.DeadEyeHudOverlay;
import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.entity.client.*;
import github.jcsmecabricks.customweapons.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.util.Identifier;

public class CustomWeaponsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(HatchetProjectileModel.HATCHET, HatchetProjectileModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.HATCHET, HatchetProjectileRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT, ElephantModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ELEPHANT, ElephantRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT_ARMOR, ElephantModel::getTexturedModelData);
        KeyInputHandler.registerKeyInputs();
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
                Identifier.of(CustomWeapons.MOD_ID, "dead_eye"), new DeadEyeHudOverlay());

    }
}
