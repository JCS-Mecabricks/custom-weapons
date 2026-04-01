package github.jcsmecabricks.customweapons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.custom.ElephantArmorItem;
import github.jcsmecabricks.customweapons.init.ItemInit;
import java.util.Map;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EquipmentLayerRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ElephantArmorFeatureRenderer extends RenderLayer<ElephantRenderState, ElephantModel> {
    private final ElephantModel model;
    private final EquipmentLayerRenderer equipmentRenderer;

    private static final Map<Item, Identifier> ARMOR_MAP = Map.of(
            ItemInit.IRON_ELEPHANT_ARMOR, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/iron_elephant.png"),
            ItemInit.GOLD_ELEPHANT_ARMOR, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/gold_elephant.png"),
            ItemInit.DIAMOND_ELEPHANT_ARMOR, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/diamond_elephant.png"),
            ItemInit.NETHERITE_ELEPHANT_ARMOR, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/netherite_elephant.png"),
            ItemInit.SILVER_ELEPHANT_ARMOR, Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/silver_elephant.png")
    );

    private static final Identifier[] DYE_LOCATION = new Identifier[]{
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/white.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/orange.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/magenta.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/light_blue.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/yellow.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/lime.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/pink.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/gray.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/light_gray.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/cyan.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/purple.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/blue.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/brown.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/green.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/red.png"),
            Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/blanket/black.png")
    };

    public ElephantArmorFeatureRenderer(RenderLayerParent<ElephantRenderState, ElephantModel> context, EntityModelSet loader, EquipmentLayerRenderer equipmentRenderer) {
        super(context);
        this.model = new ElephantModel(loader.bakeLayer(ElephantModel.ELEPHANT_ARMOR));
        this.equipmentRenderer = equipmentRenderer;
    }

    @Override
    public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, ElephantRenderState state, float limbAngle, float limbDistance) {
        if (!state.hasArmorOn) return;

        ItemStack stack = state.getBodyArmor;
        if (!(stack.getItem() instanceof ElephantArmorItem armorItem)) return;

        // Use equipmentRenderer if armor assets are registered via registry
        // Otherwise, use the legacy texture approach below
        Identifier armorTexture = ARMOR_MAP.get(armorItem);
        if (armorTexture == null) return;

        this.getParentModel().copyStateTo(this.model);
        this.model.setAngles(state);

        // Submit base armor texture
        queue.submitModel(
                this.model,
                state,
                matrices,
                RenderTypes.armorCutoutNoCull(armorTexture),
                light,
                OverlayTexture.NO_OVERLAY,
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
                    RenderTypes.armorCutoutNoCull(dyeTexture),
                    light,
                    OverlayTexture.NO_OVERLAY,
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
