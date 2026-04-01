package github.jcsmecabricks.customweapons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class ElephantRenderer extends MobRenderer<ElephantEntity, ElephantRenderState, ElephantModel> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/elephant.png");
    public ElephantRenderer(EntityRendererProvider.Context context) {
        super(context, new ElephantModel(context.bakeLayer(ElephantModel.ELEPHANT)), 1.5F);
        this.addLayer(new ElephantArmorFeatureRenderer(this, context.getModelSet(), context.getEquipmentRenderer()));
    }

    @Override
    public ElephantRenderState createRenderState() {
        return new ElephantRenderState();
    }

    @Override
    public void extractRenderState(ElephantEntity livingEntity, ElephantRenderState livingEntityRenderState, float f) {
        super.extractRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idlingAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.isSaddled = livingEntity.isSaddled();
        livingEntityRenderState.hasArmorOn = livingEntity.hasArmorOn();
        livingEntityRenderState.swag = livingEntity.getSwag();
        livingEntityRenderState.hasChest = livingEntity.hasChest();
        livingEntityRenderState.getBodyArmor = livingEntity.getBodyArmorItem().copy();
    }

    @Override
    public Identifier getTextureLocation(ElephantRenderState state) {
        if(state.isSaddled) {
            return Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/elephant_saddled.png");
        } else {
            return Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "textures/entity/elephant/elephant.png");
        }
    }

    @Override
    protected void scale(ElephantRenderState state, PoseStack matrices) {
        float scale = state.isBaby ? 0.5F : 1.0F;
        matrices.scale(scale, scale, scale);
    }
}
