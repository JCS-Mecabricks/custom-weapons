package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.ElephantEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ElephantRenderer extends MobEntityRenderer<ElephantEntity, ElephantRenderState, ElephantModel> {
    private static final Identifier TEXTURE = Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/elephant.png");
    public ElephantRenderer(EntityRendererFactory.Context context) {
        super(context, new ElephantModel(context.getPart(ElephantModel.ELEPHANT)), 1.5F);
        this.addFeature(new ElephantArmorFeatureRenderer(this, context.getEntityModels()));
    }

    @Override
    public ElephantRenderState createRenderState() {
        return new ElephantRenderState();
    }

    @Override
    public void updateRenderState(ElephantEntity livingEntity, ElephantRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idlingAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.isSaddled = livingEntity.hasSaddleEquipped();
        livingEntityRenderState.hasArmorOn = livingEntity.hasArmorOn();
        livingEntityRenderState.swag = livingEntity.getSwag();
        livingEntityRenderState.hasChest = livingEntity.hasChest();
        livingEntityRenderState.getBodyArmor = livingEntity.getBodyArmor().copy();
    }

    @Override
    protected void scale(ElephantRenderState state, MatrixStack matrices) {
        float scale = state.baby ? 0.5F : 1.0F;
        matrices.scale(scale, scale, scale);
    }


    public Identifier getTexture(ElephantRenderState state) {
        if(state.isSaddled) {
            return Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/elephant_saddled.png");
        } else {
            return Identifier.of(CustomWeapons.MOD_ID, "textures/entity/elephant/elephant.png");
        }
    }
}
