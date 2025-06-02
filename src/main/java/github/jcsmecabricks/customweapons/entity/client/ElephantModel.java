package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.client.animation.ElephantAnimations;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ElephantModel extends EntityModel<ElephantRenderState> {
    public static final EntityModelLayer ELEPHANT = new EntityModelLayer(Identifier.of(CustomWeapons.MOD_ID, "elephant"), "main");
    public static final EntityModelLayer ELEPHANT_ARMOR =
            new EntityModelLayer(Identifier.of(CustomWeapons.MOD_ID, "elephant_armor"), "armor");
    private final ModelPart elephant;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart head;
    private final ModelPart ears;
    private final ModelPart left;
    private final ModelPart right;
    private final ModelPart nose;
    private final ModelPart tusks;
    private final ModelPart blanket;
    private final ModelPart chests;
    private final ModelPart tier1;
    private final ModelPart tier2;
    private final ModelPart tier3;
    private final ModelPart hood;
    private final ModelPart legs;
    private final ModelPart LegFR;
    private final ModelPart LegFL;
    private final ModelPart LegBR;
    private final ModelPart LegBL;
    public ElephantModel(ModelPart root) {
        super(root);
        this.elephant = root.getChild("elephant");
        this.body = this.elephant.getChild("body");
        this.tail = this.body.getChild("tail");
        this.head = this.body.getChild("head");
        this.ears = this.head.getChild("ears");
        this.left = this.ears.getChild("left");
        this.right = this.ears.getChild("right");
        this.nose = this.head.getChild("nose");
        this.tusks = this.head.getChild("tusks");
        this.blanket = this.body.getChild("blanket");
        this.chests = this.body.getChild("chests");
        this.tier1 = this.chests.getChild("tier1");
        this.tier2 = this.chests.getChild("tier2");
        this.tier3 = this.chests.getChild("tier3");
        this.hood = this.body.getChild("hood");
        this.legs = this.elephant.getChild("legs");
        this.LegFR = this.legs.getChild("LegFR");
        this.LegFL = this.legs.getChild("LegFL");
        this.LegBR = this.legs.getChild("LegBR");
        this.LegBL = this.legs.getChild("LegBL");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData elephant = modelPartData.addChild("elephant", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 24.0F, 0.0F));

        ModelPartData body = elephant.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -57.0F, -28.0F, 28.0F, 32.0F, 47.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 4.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(96, 109).cuboid(0.0F, -46.0F, 19.3F, 2.0F, 4.0F, 0.0F, new Dilation(0.0F))
                .uv(96, 103).cuboid(-1.0F, -41.0F, 19.3F, 2.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(96, 113).cuboid(0.0F, -35.0F, 19.3F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(174, 107).cuboid(-2.0F, -33.0F, 19.3F, 5.0F, 6.0F, 0.0F, new Dilation(0.0F))
                .uv(100, 104).cuboid(1.0F, -36.0F, 19.3F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(100, 103).cuboid(0.0F, -42.0F, 19.3F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 4.0F, 0.0873F, 0.0F, 0.0F));

        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(102, 79).mirrored().cuboid(-11.0F, -11.0F, -7.0F, 22.0F, 22.0F, 14.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, -41.0F, -35.0F));

        ModelPartData ears = head.addChild("ears", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 41.0F, 35.0F));

        ModelPartData left = ears.addChild("left", ModelPartBuilder.create(), ModelTransform.origin(-11.0F, -30.0F, -33.0F));

        ModelPartData cube_r1 = left.addChild("cube_r1", ModelPartBuilder.create().uv(150, 23).mirrored().cuboid(-3.7024F, -22.0F, 1.9491F, 21.0F, 22.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 0.0F, -1.0F, 0.0F, -2.4435F, 0.0F));

        ModelPartData right = ears.addChild("right", ModelPartBuilder.create(), ModelTransform.origin(12.0F, -30.0F, -33.0F));

        ModelPartData cube_r2 = right.addChild("cube_r2", ModelPartBuilder.create().uv(150, 23).mirrored().cuboid(-3.4351F, -22.0F, -4.1734F, 21.0F, 22.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.0F, 0.0F, -1.0F, 0.0F, -0.6981F, 0.0F));

        ModelPartData nose = head.addChild("nose", ModelPartBuilder.create().uv(88, 151).mirrored().cuboid(-5.0F, -9.0F, -3.0F, 10.0F, 25.0F, 8.0F, new Dilation(0.0F)).mirrored(false)
                .uv(124, 151).mirrored().cuboid(-4.0F, 16.0F, -2.0F, 8.0F, 12.0F, 6.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(0.0F, 10.0F, -12.0F));

        ModelPartData tusks = head.addChild("tusks", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 41.0F, 35.0F));

        ModelPartData LTusk_r1 = tusks.addChild("LTusk_r1", ModelPartBuilder.create().uv(124, 169).mirrored().cuboid(-1.0F, -2.5F, -1.0F, 5.0F, 18.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(152, 151).mirrored().cuboid(16.0F, -2.5F, -1.0F, 5.0F, 18.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-10.0F, -28.0F, -41.0F, -0.0873F, 0.0F, 0.0F));

        ModelPartData LTusk2_r1 = tusks.addChild("LTusk2_r1", ModelPartBuilder.create().uv(174, 68).mirrored().cuboid(-1.0F, -1.0F, -1.5F, 5.0F, 18.0F, 5.0F, new Dilation(0.0F)).mirrored(false)
                .uv(172, 151).mirrored().cuboid(16.0F, -1.0F, -1.5F, 5.0F, 18.0F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-10.0F, -16.2F, -44.0F, -1.6581F, 0.0F, 0.0F));

        ModelPartData blanket = body.addChild("blanket", ModelPartBuilder.create().uv(0, 101).cuboid(-14.0F, -58.0F, -15.0F, 28.0F, 1.0F, 23.0F, new Dilation(0.0F))
                .uv(2, 103).cuboid(-15.0F, -58.0F, -15.0F, 1.0F, 20.0F, 23.0F, new Dilation(0.0F))
                .uv(47, 103).cuboid(14.0F, -58.0F, -15.0F, 1.0F, 20.0F, 23.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData chests = body.addChild("chests", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData tier1 = chests.addChild("tier1", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = tier1.addChild("cube_r3", ModelPartBuilder.create().uv(22, 182).cuboid(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(15.0F, -30.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r4 = tier1.addChild("cube_r4", ModelPartBuilder.create().uv(150, 68).cuboid(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-16.0F, -30.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData tier2 = chests.addChild("tier2", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r5 = tier2.addChild("cube_r5", ModelPartBuilder.create().uv(0, 182).cuboid(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(15.0F, -30.0F, 6.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r6 = tier2.addChild("cube_r6", ModelPartBuilder.create().uv(174, 96).cuboid(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-16.0F, -30.0F, 6.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData tier3 = chests.addChild("tier3", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r7 = tier3.addChild("cube_r7", ModelPartBuilder.create().uv(166, 174).cuboid(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(15.0F, -36.0F, 18.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData cube_r8 = tier3.addChild("cube_r8", ModelPartBuilder.create().uv(144, 174).cuboid(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-16.0F, -36.0F, 18.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData hood = body.addChild("hood", ModelPartBuilder.create().uv(150, 48).cuboid(-9.0F, -53.0F, -40.0F, 18.0F, 1.0F, 10.0F, new Dilation(0.0F))
                .uv(154, 50).cuboid(-8.0F, -54.0F, -39.0F, 16.0F, 1.0F, 8.0F, new Dilation(0.0F))
                .uv(158, 54).cuboid(-6.0F, -55.0F, -37.0F, 12.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData legs = elephant.addChild("legs", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 4.0F));

        ModelPartData LegFR = legs.addChild("LegFR", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData leg_r1 = LegFR.addChild("leg_r1", ModelPartBuilder.create().uv(96, 115).cuboid(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 18.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData LegFL = legs.addChild("LegFL", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData leg_r2 = LegFL.addChild("leg_r2", ModelPartBuilder.create().uv(140, 115).cuboid(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, 18.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData LegBR = legs.addChild("LegBR", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData leg_r3 = LegBR.addChild("leg_r3", ModelPartBuilder.create().uv(0, 146).cuboid(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, -18.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData LegBL = legs.addChild("LegBL", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 0.0F, 0.0F));

        ModelPartData leg_r4 = LegBL.addChild("leg_r4", ModelPartBuilder.create().uv(44, 146).cuboid(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(13.0F, 0.0F, -18.0F, 0.0F, 1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }


    @Override
    public void setAngles(ElephantRenderState elephantRenderState) {
        super.setAngles(elephantRenderState);
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.chests.visible = elephantRenderState.hasChest;
        this.setHeadAngles(elephantRenderState, elephantRenderState.relativeHeadYaw, elephantRenderState.pitch);
        this.animateWalking(ElephantAnimations.WALK, elephantRenderState.limbSwingAnimationProgress, elephantRenderState.limbSwingAmplitude, 2.0F, 2.5F);
        this.animate(elephantRenderState.idlingAnimationState, ElephantAnimations.IDLE, elephantRenderState.age, 1.0F);
    }

    private void setHeadAngles(ElephantRenderState elephantRenderState, float headYaw, float headPitch) {
        this.head.yaw = elephantRenderState.relativeHeadYaw * 0.017453292F;
        this.head.pitch = elephantRenderState.pitch * 0.017453292F;
    }

    public void copyStateTo(ElephantModel target) {
        target.elephant.copyTransform(this.elephant);
        target.body.copyTransform(this.body);
        target.tail.copyTransform(this.tail);
        target.head.copyTransform(this.head);
        target.ears.copyTransform(this.ears);
        target.left.copyTransform(this.left);
        target.right.copyTransform(this.right);
        target.nose.copyTransform(this.nose);
        target.tusks.copyTransform(this.tusks);
        target.blanket.copyTransform(this.blanket);
        target.chests.copyTransform(this.chests);
        target.tier1.copyTransform(this.tier1);
        target.tier2.copyTransform(this.tier2);
        target.tier3.copyTransform(this.tier3);
        target.hood.copyTransform(this.hood);
        target.legs.copyTransform(this.legs);
        target.LegFR.copyTransform(this.LegFR);
        target.LegFL.copyTransform(this.LegFL);
        target.LegBR.copyTransform(this.LegBR);
        target.LegBL.copyTransform(this.LegBL);
    }



    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ElephantRenderState state, float limbAngle, float limbDistance) {

    }

    public ModelPart getPart() {
        return elephant;
    }
}
