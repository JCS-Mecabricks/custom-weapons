package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.custom.ElephantArmorItem;
import github.jcsmecabricks.customweapons.init.ItemInit;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
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
    private Map<Item, Identifier> ARMOR_MAP = Map.of(
            ItemInit.IRON_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/iron_elephant.png"),
            ItemInit.GOLD_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/gold_elephant.png"),
            ItemInit.DIAMOND_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/diamond_elephant.png"),
            ItemInit.NETHERITE_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/netherite_elephant.png"),
            ItemInit.SILVER_ELEPHANT_ARMOR, Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/armor/silver_elephant.png")
    );

    public ElephantArmorFeatureRenderer(FeatureRendererContext<ElephantRenderState, ElephantModel> context, LoadedEntityModels loader) {
        super(context);
        model = new ElephantModel(loader.getModelPart(ElephantModel.ELEPHANT_ARMOR));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
                       ElephantRenderState state, float limbAngle, float limbDistance) {

        if (state.hasArmorOn) {
            ItemStack itemStack = state.getBodyArmor;
            if (itemStack.getItem() instanceof ElephantArmorItem armorItem) {
                (this.getContextModel()).copyStateTo(this.model);
                this.model.setAngles(state);
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(ARMOR_MAP.get(itemStack.getItem())));
                this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
                this.renderDyed(matrices, vertexConsumers, light, state, armorItem);
            }
        }
    }

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

    private void renderDyed(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ElephantRenderState state, ElephantArmorItem armorItem) {
        DyeColor dyecolor = state.swag;
        Identifier identifier;
        if (dyecolor != null) {
            identifier = DYE_LOCATION[dyecolor.ordinal()];
        } else {
            identifier = ARMOR_MAP.get(armorItem);
        }

        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(identifier)), light, OverlayTexture.DEFAULT_UV);
    }
}