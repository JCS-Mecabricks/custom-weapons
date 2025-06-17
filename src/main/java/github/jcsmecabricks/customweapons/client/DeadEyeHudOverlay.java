package github.jcsmecabricks.customweapons.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;

public class DeadEyeHudOverlay implements HudElement {
    public static final Identifier FILLED_DEAD_EYE =
            Identifier.of(CustomWeapons.MOD_ID, "textures/dead_eye/filled_dead_eye.png");
    public static final Identifier EMPTY_DEAD_EYE =
            Identifier.of(CustomWeapons.MOD_ID, "textures/dead_eye/empty_dead_eye.png");

    @Override
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || client.interactionManager == null) return;

        GameMode mode = client.interactionManager.getCurrentGameMode();
        if (mode != GameMode.SURVIVAL && mode != GameMode.ADVENTURE) return;

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        int x = width / 2 + 91 - 90; // align with hunger bar
        int y = height - 49;         // base y position above hunger

        // 🔼 Raise bar if riding an entity with > 20 health (like Minecraft does)
        if (client.player.hasVehicle() && client.player.getVehicle() instanceof net.minecraft.entity.LivingEntity mount) {
            float mountHealth = mount.getHealth();
            float mountMaxHealth = mount.getMaxHealth();
            int hearts = (int) Math.ceil(mountMaxHealth / 2.0);
            if (hearts > 10) {
                int rows = (int) Math.ceil(hearts / 10.0);
                y -= (rows - 1) * 10; // each extra row pushes HUD up 10px
            }
        }

        int current = ((IEntityDataSaver) client.player).getPersistentData().getInt("dead_eye").orElse(0);

        for (int i = 0; i < 10; i++) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, EMPTY_DEAD_EYE,
                    x + (i * 9), y, 0, 0, 12, 12, 12, 12);
        }

        for (int i = 0; i < current && i < 10; i++) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, FILLED_DEAD_EYE,
                    x + (i * 9), y, 0, 0, 12, 12, 12, 12);
        }
    }
}
