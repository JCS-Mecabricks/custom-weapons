package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.custom.ElephantArmorItem;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ElephantArmorFeatureRenderer extends FeatureRenderer<ElephantRenderState, ElephantModel> {
    private final ElephantModel model;
    private final EquipmentRenderer equipmentRenderer;

    private static final Map<Item, Identifier> ARMOR_MAP = Map.of(
            ItemInit.IRON_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/iron_elephant.png"),
            ItemInit.GOLD_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/gold_elephant.png"),
            ItemInit.DIAMOND_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/diamond_elephant.png"),
            ItemInit.NETHERITE_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/netherite_elephant.png"),
            ItemInit.SILVER_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/silver_elephant.png")
    );

    private static final Identifier[] DYE_LOCATION = new Identifier[]{
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/white.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/orange.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/magenta.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/light_blue.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/yellow.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/lime.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/pink.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/gray.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/light_gray.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/cyan.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/purple.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/blue.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/brown.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/green.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/red.png"),
            Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/black.png")
    };

    public ElephantArmorFeatureRenderer(FeatureRendererContext<ElephantRenderState, ElephantModel> context, LoadedEntityModels loader, EquipmentRenderer equipmentRenderer) {
        super(context);
        this.model = new ElephantModel(loader.getModelPart(ElephantModel.ELEPHANT_ARMOR));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, ElephantRenderState state, float limbAngle, float limbDistance) {
        if (!state.hasArmorOn) return;

        ItemStack stack = state.getBodyArmor;
        if (!(stack.getItem() instanceof ElephantArmorItem armorItem)) return;

        // Use equipmentRenderer if armor assets are registered via registry
        // Otherwise, use the legacy texture approach below
        Identifier armorTexture = ARMOR_MAP.get(armorItem);
        if (armorTexture == null) return;

        this.getContextModel().copyStateTo(this.model);
        this.model.setAngles(state);

        // Submit base armor texture
        queue.submitModel(
                this.model,
                state,
                matrices,
                RenderLayers.armorCutoutNoCull(armorTexture),
                light,
                OverlayTexture.DEFAULT_UV,
                state.outlineColor,
                null
        );

        // Blanket (dyed) layer
        Identifier dyeTexture = getBlanketTexture(state.swag);
        if (dyeTexture != null) {
            queue.submitModel(
                    this.model,
                    state,
                    matrices,
                    RenderLayers.armorCutoutNoCull(dyeTexture),
                    light,
                    OverlayTexture.DEFAULT_UV,
                    state.outlineColor,
                    null
            );
        }
    }

    private Identifier getBlanketTexture(DyeColor dyeColor) {
        if (dyeColor != null) {
            return DYE_LOCATION[dyeColor.ordinal()];
        }
        return null;
    }
}
