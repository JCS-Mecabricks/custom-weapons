package github.jcsmecabricks.customweapons.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.GameType;

public class DeadEyeHudOverlay implements HudElement {
    public static final Identifier FILLED_DEAD_EYE =
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/dead_eye/filled_dead_eye.png");
    public static final Identifier EMPTY_DEAD_EYE =
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/dead_eye/empty_dead_eye.png");

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null || client.gameMode == null) return;

        GameType mode = client.gameMode.getPlayerMode();
        if (mode != GameType.SURVIVAL && mode != GameType.ADVENTURE) return;

        int width = client.getWindow().getGuiScaledWidth();
        int height = client.getWindow().getGuiScaledHeight();
        int x = width / 2 + 91 - 90; // align with hunger bar
        int y = height - 49;         // base y position above hunger

        if (client.player.isPassenger() && client.player.getVehicle() instanceof net.minecraft.world.entity.LivingEntity mount) {
            float mountHealth = mount.getHealth();
            float mountMaxHealth = mount.getMaxHealth();
            int hearts = (int) Math.ceil(mountMaxHealth / 2.0);
            if (hearts > 10) {
                int rows = (int) Math.ceil(hearts / 10.0);
                y -= (rows - 1) * 10; // each extra row pushes HUD up 10px
            }
        }

        if (client.player.getAirSupply() < client.player.getMaxAirSupply() || client.player.isUnderWater()) {
            y -= 10; // move up one row (same offset used by hearts/armor)
        }

        int current = ((IEntityDataSaver) client.player).getPersistentData().getInt("dead_eye").orElse(0);

        for (int i = 0; i < 10; i++) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, EMPTY_DEAD_EYE,
                    x + (i * 9), y, 0, 0, 12, 12, 12, 12);
        }

        for (int i = 0; i < current && i < 10; i++) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, FILLED_DEAD_EYE,
                    x + (i * 9), y, 0, 0, 12, 12, 12, 12);
        }
    }
}
