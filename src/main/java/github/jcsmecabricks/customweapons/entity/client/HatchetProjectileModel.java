package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class HatchetProjectileModel extends EntityModel<HatchetProjectileRenderState> {
    public static final EntityModelLayer HATCHET = new EntityModelLayer(Identifier.of(CustomWeapons.MOD_ID, "hatchet"), "main");
    private final ModelPart hatchet;
    public HatchetProjectileModel(ModelPart root) {
        super(root);
        this.hatchet = root.getChild("hatchet");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData hatchet = modelPartData.addChild("hatchet", ModelPartBuilder.create().uv(4, 8).cuboid(-0.5F, -16.0F, -5.0F, 1.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(10, 11).cuboid(-0.5F, -13.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 8).cuboid(-0.5F, -16.0F, 2.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F))
                .uv(4, 0).cuboid(0.0F, -16.0F, -3.0F, 0.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData cube_r1 = hatchet.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(6.5F, -9.0F, -7.5F, 1.0F, 18.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-9.9F, -9.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }



    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color, float red, float green, float blue, float alpha) {
        hatchet.render(matrices, vertexConsumer, light, overlay, color);
    }
}