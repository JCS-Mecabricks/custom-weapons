package github.jcsmecabricks.customweapons;

import github.jcsmecabricks.customweapons.client.DeadEyeHudOverlay;
import github.jcsmecabricks.customweapons.entity.ModEntities;
import github.jcsmecabricks.customweapons.entity.client.*;
import github.jcsmecabricks.customweapons.event.KeyInputHandler;
import github.jcsmecabricks.customweapons.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

public class CustomWeaponsClient implements ClientModInitializer {

    public CustomWeaponsClient() {
    }

    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(HatchetProjectileModel.HATCHET, HatchetProjectileModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT, ElephantModel::getTexturedModelData);
        ModelLayerRegistry.registerModelLayer(ElephantModel.ELEPHANT_ARMOR, ElephantModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.HATCHET, HatchetProjectileRenderer::new);
        EntityRendererRegistry.register(ModEntities.ELEPHANT, ElephantRenderer::new);

        KeyInputHandler.registerKeyInputs();
        ModMessages.registerS2CPackets();

        HudElementRegistry.attachElementBefore(
                VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "dead_eye"),
                new DeadEyeHudOverlay()
        );
    }
}
