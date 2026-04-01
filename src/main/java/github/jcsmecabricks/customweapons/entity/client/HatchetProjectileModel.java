package github.jcsmecabricks.customweapons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.custom.HatchetProjectileEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

public class HatchetProjectileModel extends EntityModel<HatchetProjectileRenderState> {
    public static final ModelLayerLocation HATCHET = new ModelLayerLocation(Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "hatchet"), "main");
    private final ModelPart hatchet;
    public HatchetProjectileModel(ModelPart root) {
        super(root);
        this.hatchet = root.getChild("hatchet");
    }
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition hatchet = modelPartData.addOrReplaceChild("hatchet", CubeListBuilder.create().texOffs(4, 8).addBox(-0.5F, -16.0F, -5.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(10, 11).addBox(-0.5F, -13.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 8).addBox(-0.5F, -16.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(4, 0).addBox(0.0F, -16.0F, -3.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition cube_r1 = hatchet.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(6.5F, -9.0F, -7.5F, 1.0F, 18.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.9F, -9.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }



    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void render(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color, float red, float green, float blue, float alpha) {
        hatchet.render(matrices, vertexConsumer, light, overlay, color);
    }
}