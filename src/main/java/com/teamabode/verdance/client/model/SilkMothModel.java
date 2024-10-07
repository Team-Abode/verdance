package com.teamabode.verdance.client.model;

import com.teamabode.sketch.client.model.SketchAnimatableModel;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.VerdanceAnimations;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.math.MathHelper;

public class SilkMothModel extends SketchAnimatableModel<SilkMothEntity> {
	public static final EntityModelLayer LAYER = new EntityModelLayer(Verdance.id("silk_moth"), "main");

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

	public static TexturedModelData createBodyLayer() {
		ModelData mesh = new ModelData();
		ModelPartData root = mesh.getRoot();

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 9).cuboid(-5.0F, -4.0F, -5.0F, 9.0F, 9.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 25).cuboid(-4.0F, -3.0F, 2.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 19.0F, -1.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -3.0F, -3.0F, 7.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -5.0F));

		ModelPartData rightAntenna = head.addChild("right_antenna", ModelPartBuilder.create().uv(20, -7).cuboid(0.0F, -7.0F, -7.0F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -3.0F, -2.0F));
		ModelPartData leftAntenna = head.addChild("left_antenna", ModelPartBuilder.create().uv(20, -7).cuboid(0.0F, -7.0F, -7.0F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -3.0F, -2.0F));

		ModelPartData leftLegFront = body.addChild("left_leg_front", ModelPartBuilder.create().uv(48, 0).cuboid(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 4.0F, -3.0F));
		ModelPartData leftLegMid = body.addChild("left_leg_mid", ModelPartBuilder.create().uv(48, 0).cuboid(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 4.0F, 1.0F));
		ModelPartData leftLegBack = body.addChild("left_leg_back", ModelPartBuilder.create().uv(48, 0).cuboid(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 4.0F, 5.0F));

		ModelPartData rightLegFront = body.addChild("right_leg_front", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-5.0F, 4.0F, -3.0F));
		ModelPartData rightLegMid = body.addChild("right_leg_mid", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-5.0F, 4.0F, 1.0F));
		ModelPartData rightLegBack = body.addChild("right_leg_back", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-4.0F, 4.0F, 5.0F));

		ModelPartData leftWing = body.addChild("left_wing", ModelPartBuilder.create().uv(14, 7).mirrored().cuboid(0.0F, 0.0F, -9.5F, 12.0F, 0.0F, 18.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, -2.0F, -1.0F));
		ModelPartData rightWing = body.addChild("right_wing", ModelPartBuilder.create().uv(14, 7).cuboid(-12.0F, 0.0F, -9.5F, 12.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -2.0F, -1.0F));

		return TexturedModelData.of(mesh, 64, 64);
	}

	private static float expDecay(float a, float b, float decay, float dt) {
		return b + (a - b) * (float)Math.exp(-decay * dt);
	}

	public void setupBones(SilkMothEntity entity, float deltaTicks) {
		this.body.pivotY = 17.75f;

		this.rightAntenna.roll = -22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.leftAntenna.roll = 22.5f * MathHelper.RADIANS_PER_DEGREE;

		this.rightLegFront.yaw = -22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.rightLegFront.roll = -22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.rightLegMid.roll = -22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.rightLegBack.yaw = 22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.rightLegBack.roll = -22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegFront.yaw = 22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegFront.roll = 22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegMid.roll = 22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegBack.yaw = -22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegBack.roll = 22.5f * MathHelper.RADIANS_PER_DEGREE;
		this.rightWing.roll = 45.0f * MathHelper.RADIANS_PER_DEGREE;
		this.leftWing.roll = -45.0f * MathHelper.RADIANS_PER_DEGREE;
	}

	@Override
	public void setAngles(SilkMothEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		float deltaTicks = animationProgress - entity.lastAnimationProgress;
		entity.lastAnimationProgress = animationProgress;

		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setupBones(entity, deltaTicks);
		this.animate(entity.idleAnimationState, VerdanceAnimations.SILK_MOTH_IDLE, animationProgress);

		if (entity.isOnGround() && !entity.isInAir()) {
			this.animateWalk(VerdanceAnimations.SILK_MOTH_WALK, limbAngle, limbDistance, 2.0f, 2.5f);
		}
		this.animate(entity.flyAnimationState, VerdanceAnimations.SILK_MOTH_FLY,  animationProgress);
		this.animateBones(entity, animationProgress, deltaTicks, headYaw, headPitch);
	}

	private void animateBones(SilkMothEntity entity, float animationProgress, float deltaTicks, float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		float targetXRot =  MathHelper.clamp(entity.bodyPitch * 45.0f, -45.0f, 45.0f) * MathHelper.RADIANS_PER_DEGREE;
		float soarProgress = entity.getSoarProgress(animationProgress - entity.age);

		entity.lastBodyPitch = expDecay(entity.lastBodyPitch, targetXRot, 8f, deltaTicks / 20f);

		this.body.pitch = entity.lastBodyPitch;
		this.head.yaw = headYaw * MathHelper.RADIANS_PER_DEGREE;
		this.head.pitch = (headPitch * MathHelper.RADIANS_PER_DEGREE) - entity.lastBodyPitch;

		this.leftLegFront.roll -= (soarProgress * 15.0f) * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegFront.yaw -= (soarProgress * 30.0f) * MathHelper.RADIANS_PER_DEGREE;

		this.leftLegMid.roll -= (soarProgress * 15.0f) * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegMid.yaw -= (soarProgress * 30.0f) * MathHelper.RADIANS_PER_DEGREE;

		this.leftLegBack.roll -= (soarProgress * 15.0f) * MathHelper.RADIANS_PER_DEGREE;
		this.leftLegBack.yaw -= (soarProgress * 30.0f) * MathHelper.RADIANS_PER_DEGREE;

		this.rightLegFront.roll += (soarProgress * 15.0f) * MathHelper.RADIANS_PER_DEGREE;
		this.rightLegFront.yaw += (soarProgress * 30.0f) * MathHelper.RADIANS_PER_DEGREE;

		this.rightLegMid.roll += (soarProgress * 15.0f) * MathHelper.RADIANS_PER_DEGREE;
		this.rightLegMid.yaw += (soarProgress * 30.0f) * MathHelper.RADIANS_PER_DEGREE;

		this.rightLegBack.roll += (soarProgress * 15.0f) * MathHelper.RADIANS_PER_DEGREE;
		this.rightLegBack.yaw += (soarProgress * 30.0f) * MathHelper.RADIANS_PER_DEGREE;
	}

	public ModelPart getPart() {
		return this.root;
	}
}