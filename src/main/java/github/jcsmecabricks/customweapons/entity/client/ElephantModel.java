package github.jcsmecabricks.customweapons.entity.client;

import github.jcsmecabricks.customweapons.CustomWeapons;
import github.jcsmecabricks.customweapons.entity.client.animation.ElephantAnimations;
import net.minecraft.client.animation.KeyframeAnimation;
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

public class ElephantModel extends EntityModel<ElephantRenderState> {
    public float originX;
    public float originY;
    public float originZ;
    public float pitch;
    public float yaw;
    public float roll;
    public float xScale = 1.0F;
    public float yScale = 1.0F;
    public float zScale = 1.0F;
    private PartPose defaultTransform;
    public static final ModelLayerLocation ELEPHANT = new ModelLayerLocation(Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "elephant"), "main");
    public static final ModelLayerLocation ELEPHANT_ARMOR =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(CustomWeapons.MOD_ID, "elephant_armor"), "armor");
    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idlingAnimation;
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
        this.walkingAnimation = ElephantAnimations.WALK.bake(root);
        this.idlingAnimation = ElephantAnimations.IDLE.bake(root);
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
    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        PartDefinition elephant = modelPartData.addOrReplaceChild("elephant", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = elephant.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -57.0F, -28.0F, 28.0F, 32.0F, 47.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(96, 109).addBox(0.0F, -46.0F, 19.3F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(96, 103).addBox(-1.0F, -41.0F, 19.3F, 2.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(96, 113).addBox(0.0F, -35.0F, 19.3F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(174, 107).addBox(-2.0F, -33.0F, 19.3F, 5.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(100, 104).addBox(1.0F, -36.0F, 19.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(100, 103).addBox(0.0F, -42.0F, 19.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0873F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(102, 79).mirror().addBox(-11.0F, -11.0F, -7.0F, 22.0F, 22.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -41.0F, -35.0F));

        PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, 41.0F, 35.0F));

        PartDefinition left = ears.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(-11.0F, -30.0F, -33.0F));

        PartDefinition cube_r1 = left.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(150, 23).mirror().addBox(-3.7024F, -22.0F, 1.9491F, 21.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.0F, 0.0F, -1.0F, 0.0F, -2.4435F, 0.0F));

        PartDefinition right = ears.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(12.0F, -30.0F, -33.0F));

        PartDefinition cube_r2 = right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(150, 23).mirror().addBox(-3.4351F, -22.0F, -4.1734F, 21.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, -1.0F, 0.0F, -0.6981F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(88, 151).mirror().addBox(-5.0F, -9.0F, -3.0F, 10.0F, 25.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(124, 151).mirror().addBox(-4.0F, 16.0F, -2.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, -12.0F));

        PartDefinition tusks = head.addOrReplaceChild("tusks", CubeListBuilder.create(), PartPose.offset(0.0F, 41.0F, 35.0F));

        PartDefinition LTusk_r1 = tusks.addOrReplaceChild("LTusk_r1", CubeListBuilder.create().texOffs(124, 169).mirror().addBox(-1.0F, -2.5F, -1.0F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(152, 151).mirror().addBox(16.0F, -2.5F, -1.0F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.0F, -28.0F, -41.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition LTusk2_r1 = tusks.addOrReplaceChild("LTusk2_r1", CubeListBuilder.create().texOffs(174, 68).mirror().addBox(-1.0F, -1.0F, -1.5F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(172, 151).mirror().addBox(16.0F, -1.0F, -1.5F, 5.0F, 18.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.0F, -16.2F, -44.0F, -1.6581F, 0.0F, 0.0F));

        PartDefinition blanket = body.addOrReplaceChild("blanket", CubeListBuilder.create().texOffs(0, 101).addBox(-14.0F, -58.0F, -15.0F, 28.0F, 1.0F, 23.0F, new CubeDeformation(0.0F))
                .texOffs(2, 103).addBox(-15.0F, -58.0F, -15.0F, 1.0F, 20.0F, 23.0F, new CubeDeformation(0.0F))
                .texOffs(47, 103).addBox(14.0F, -58.0F, -15.0F, 1.0F, 20.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition chests = body.addOrReplaceChild("chests", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition tier1 = chests.addOrReplaceChild("tier1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = tier1.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(22, 182).addBox(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, -30.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r4 = tier1.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(150, 68).addBox(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -30.0F, -8.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition tier2 = chests.addOrReplaceChild("tier2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = tier2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 182).addBox(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, -30.0F, 6.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r6 = tier2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(174, 96).addBox(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -30.0F, 6.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition tier3 = chests.addOrReplaceChild("tier3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r7 = tier3.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(166, 174).addBox(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.0F, -36.0F, 18.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition cube_r8 = tier3.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(144, 174).addBox(-7.0F, -8.0F, -2.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -36.0F, 18.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition hood = body.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(150, 48).addBox(-9.0F, -53.0F, -40.0F, 18.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(154, 50).addBox(-8.0F, -54.0F, -39.0F, 16.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(158, 54).addBox(-6.0F, -55.0F, -37.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legs = elephant.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition LegFR = legs.addOrReplaceChild("LegFR", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leg_r1 = LegFR.addOrReplaceChild("leg_r1", CubeListBuilder.create().texOffs(96, 115).addBox(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 18.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition LegFL = legs.addOrReplaceChild("LegFL", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leg_r2 = LegFL.addOrReplaceChild("leg_r2", CubeListBuilder.create().texOffs(140, 115).addBox(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 0.0F, 18.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition LegBR = legs.addOrReplaceChild("LegBR", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leg_r3 = LegBR.addOrReplaceChild("leg_r3", CubeListBuilder.create().texOffs(0, 146).addBox(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -18.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition LegBL = legs.addOrReplaceChild("LegBL", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leg_r4 = LegBL.addOrReplaceChild("leg_r4", CubeListBuilder.create().texOffs(44, 146).addBox(-1.0F, -25.0F, -10.0F, 11.0F, 25.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 0.0F, -18.0F, 0.0F, 1.5708F, 0.0F));
        return LayerDefinition.create(modelData, 256, 256);
    }


    public void setAngles(ElephantRenderState elephantRenderState) {
        super.setupAnim(elephantRenderState);
        this.getPart().getAllParts().forEach(ModelPart::resetPose);
        this.chests.visible = elephantRenderState.hasChest;
        this.setHeadAngles(elephantRenderState, elephantRenderState.yRot, elephantRenderState.xRot);
        this.walkingAnimation.applyWalk(elephantRenderState.walkAnimationPos, elephantRenderState.walkAnimationSpeed, 2.0F, 2.5F);
        this.idlingAnimation.apply(elephantRenderState.idlingAnimationState, elephantRenderState.ageInTicks, 1.0F);
    }

    private void setHeadAngles(ElephantRenderState elephantRenderState, float headYaw, float headPitch) {
        this.head.yRot = elephantRenderState.yRot * 0.017453292F;
        this.head.xRot = elephantRenderState.xRot * 0.017453292F;
    }

    public void copyTransform(ModelPart part) {
        this.xScale = part.xScale;
        this.yScale = part.yScale;
        this.zScale = part.zScale;
        this.pitch = part.xRot;
        this.yaw = part.yRot;
        this.roll = part.zRot;
        this.originX = part.x;
        this.originY = part.y;
        this.originZ = part.z;
    }

    public void copyStateTo(ElephantModel target) {
        copyTransform(this.elephant);
        copyTransform(this.body);
        copyTransform(this.tail);
        copyTransform(this.head);
        copyTransform(this.ears);
        copyTransform(this.left);
        copyTransform(this.right);
        copyTransform(this.nose);
        copyTransform(this.tusks);
        copyTransform(this.blanket);
        copyTransform(this.chests);
        copyTransform(this.tier1);
        copyTransform(this.tier2);
        copyTransform(this.tier3);
        copyTransform(this.hood);
        copyTransform(this.legs);
        copyTransform(this.LegFR);
        copyTransform(this.LegFL);
        copyTransform(this.LegBR);
        copyTransform(this.LegBL);
    }

    public ModelPart getPart() {
        return elephant;
    }
}
