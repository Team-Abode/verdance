package com.teamabode.verdance.client.model;

import com.teamabode.sketch.client.model.SketchAnimatableModel;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.VerdanceAnimations;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SilkMothModel extends SketchAnimatableModel<SilkMoth> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(Verdance.id("silk_moth"), "main");

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart head;

	private final ModelPart rightAntenna;
	private final ModelPart leftAntenna;

	private final ModelPart leftLegFront;
	private final ModelPart leftLegMid;
	private final ModelPart leftLegBack;

	private final ModelPart rightLegFront;
	private final ModelPart rightLegMid;
	private final ModelPart rightLegBack;

	private final ModelPart leftWing;
	private final ModelPart rightWing;

	public SilkMothModel(ModelPart root) {
		this.root = root;
		this.body = this.root.getChild("body");
		this.head = body.getChild("head");

		this.leftAntenna = head.getChild("left_antenna");
		this.rightAntenna = head.getChild("right_antenna");

		this.leftLegFront = body.getChild("left_leg_front");
		this.leftLegMid = body.getChild("left_leg_mid");
		this.leftLegBack = body.getChild("left_leg_back");

		this.rightLegFront = body.getChild("right_leg_front");
		this.rightLegMid = body.getChild("right_leg_mid");
		this.rightLegBack = body.getChild("right_leg_back");

		this.rightWing = body.getChild("right_wing");
		this.leftWing = body.getChild("left_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-5.0F, -4.0F, -5.0F, 9.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-4.0F, -3.0F, 2.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 19.0F, -1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -3.0F, -3.0F, 7.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -5.0F));

		PartDefinition rightAntenna = head.addOrReplaceChild("right_antenna", CubeListBuilder.create().texOffs(20, -7).addBox(0.0F, -7.0F, -7.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -3.0F, -2.0F));
		PartDefinition leftAntenna = head.addOrReplaceChild("left_antenna", CubeListBuilder.create().texOffs(20, -7).addBox(0.0F, -7.0F, -7.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, -2.0F));

		PartDefinition leftLegFront = body.addOrReplaceChild("left_leg_front", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 4.0F, -3.0F));
		PartDefinition leftLegMid = body.addOrReplaceChild("left_leg_mid", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 4.0F, 1.0F));
		PartDefinition leftLegBack = body.addOrReplaceChild("left_leg_back", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 4.0F, 5.0F));

		PartDefinition rightLegFront = body.addOrReplaceChild("right_leg_front", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 4.0F, -3.0F));
		PartDefinition rightLegMid = body.addOrReplaceChild("right_leg_mid", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 4.0F, 1.0F));
		PartDefinition rightLegBack = body.addOrReplaceChild("right_leg_back", CubeListBuilder.create().texOffs(48, 0).mirror().addBox(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 4.0F, 5.0F));

		PartDefinition leftWing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(14, 7).mirror().addBox(0.0F, 0.0F, -9.5F, 12.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -2.0F, -1.0F));
		PartDefinition rightWing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(14, 7).addBox(-12.0F, 0.0F, -9.5F, 12.0F, 0.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -2.0F, -1.0F));

		return LayerDefinition.create(mesh, 64, 64);
	}

	public void setupAnim(SilkMoth entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.setupBones();
		this.animate(entity.idleAnimationState, VerdanceAnimations.SILK_MOTH_IDLE, ageInTicks);
		
		if (entity.onGround() && !entity.isFlying()) {
			this.animateWalk(VerdanceAnimations.SILK_MOTH_WALK, limbSwing, limbSwingAmount, 2.0f, 2.5f);
		}
		this.animate(entity.flyAnimationState, VerdanceAnimations.SILK_MOTH_FLY, ageInTicks);
		this.applyHeadRotation(netHeadYaw, headPitch);
	}

	public void setupBones() {
		this.body.y = 17.75f;

		this.rightAntenna.zRot = (float) Math.toRadians(-22.5f);
		this.leftAntenna.zRot = (float) Math.toRadians(22.5f);

		this.rightLegFront.yRot = (float) Math.toRadians(-22.5f);
		this.rightLegFront.zRot = (float) Math.toRadians(-22.5f);
		this.rightLegMid.zRot = (float) Math.toRadians(-22.5f);
		this.rightLegBack.yRot = (float) Math.toRadians(22.5f);
		this.rightLegBack.zRot = (float) Math.toRadians(-22.5f);
		this.leftLegFront.yRot = (float) Math.toRadians(22.5f);
		this.leftLegFront.zRot = (float) Math.toRadians(22.5f);
		this.leftLegMid.zRot = (float) Math.toRadians(22.5f);
		this.leftLegBack.yRot = (float) Math.toRadians(-22.5f);
		this.leftLegBack.zRot = (float) Math.toRadians(22.5f);
		this.rightWing.zRot = (float) Math.toRadians(45.0f);
		this.leftWing.zRot = (float) Math.toRadians(-45.0f);
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.head.yRot = netHeadYaw * Mth.DEG_TO_RAD;
		this.head.xRot = headPitch * Mth.DEG_TO_RAD;
	}

	public ModelPart root() {
		return this.root;
	}
}