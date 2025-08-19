package com.teamabode.verdance.client.model;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.animation.SilkMothEntityAnimations;
import com.teamabode.verdance.client.state.SilkMothEntityRenderState;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.math.MathHelper;

public class SilkMothEntityModel extends EntityModel<SilkMothEntityRenderState> {
	public static final EntityModelLayer LAYER = new EntityModelLayer(Verdance.id("silk_moth"), "main");

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

	private final Animation idleAnimation;
	private final Animation walkAnimation;
	private final Animation flyAnimation;

	public SilkMothEntityModel(ModelPart root) {
        super(root.getChild("root"));

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

		this.idleAnimation = SilkMothEntityAnimations.IDLE.createAnimation(root);
		this.walkAnimation = SilkMothEntityAnimations.WALK.createAnimation(root);
		this.flyAnimation = SilkMothEntityAnimations.FLY.createAnimation(root);
	}

	public static TexturedModelData createBodyLayer() {
		ModelData mesh = new ModelData();
		ModelPartData root = mesh.getRoot();

		ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 9).cuboid(-5.0F, -4.0F, -5.0F, 9.0F, 9.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 25).cuboid(-4.0F, -3.0F, 2.0F, 7.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.origin(0.5F, 19.0F, -1.0F));

		ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -3.0F, -3.0F, 7.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 1.0F, -5.0F));

		ModelPartData rightAntenna = head.addChild("right_antenna", ModelPartBuilder.create().uv(20, -7).cuboid(0.0F, -7.0F, -7.0F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.origin(-3.0F, -3.0F, -2.0F));
		ModelPartData leftAntenna = head.addChild("left_antenna", ModelPartBuilder.create().uv(20, -7).cuboid(0.0F, -7.0F, -7.0F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, -3.0F, -2.0F));

		ModelPartData leftLegFront = body.addChild("left_leg_front", ModelPartBuilder.create().uv(48, 0).cuboid(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.0F, 4.0F, -3.0F));
		ModelPartData leftLegMid = body.addChild("left_leg_mid", ModelPartBuilder.create().uv(48, 0).cuboid(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(4.0F, 4.0F, 1.0F));
		ModelPartData leftLegBack = body.addChild("left_leg_back", ModelPartBuilder.create().uv(48, 0).cuboid(0.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(3.0F, 4.0F, 5.0F));

		ModelPartData rightLegFront = body.addChild("right_leg_front", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-5.0F, 4.0F, -3.0F));
		ModelPartData rightLegMid = body.addChild("right_leg_mid", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-5.0F, 4.0F, 1.0F));
		ModelPartData rightLegBack = body.addChild("right_leg_back", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(-6.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-4.0F, 4.0F, 5.0F));

		ModelPartData leftWing = body.addChild("left_wing", ModelPartBuilder.create().uv(14, 7).mirrored().cuboid(0.0F, 0.0F, -9.5F, 12.0F, 0.0F, 18.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(4.0F, -2.0F, -1.0F));
		ModelPartData rightWing = body.addChild("right_wing", ModelPartBuilder.create().uv(14, 7).cuboid(-12.0F, 0.0F, -9.5F, 12.0F, 0.0F, 18.0F, new Dilation(0.0F)), ModelTransform.origin(-5.0F, -2.0F, -1.0F));

		return TexturedModelData.of(mesh, 64, 64);
	}

	public void setupBones() {
		this.body.originY = 17.75f;

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
	public void setAngles(SilkMothEntityRenderState state) {
		super.setAngles(state);
		this.setupBones();

		this.idleAnimation.apply(state.idleAnimationState, state.age);

		if (state.isGrounded) {
			this.walkAnimation.applyWalking(state.limbSwingAnimationProgress, state.limbSwingAmplitude, 2.0f, 2.5f);
		}
		this.flyAnimation.apply(state.flyAnimationState, state.age);
		this.animateBones(state);

		state.lastAge = state.age;
	}

	private void animateBones(SilkMothEntityRenderState state) {
		float ageDelta = state.age - state.lastAge;

		float headYaw = MathHelper.clamp(state.relativeHeadYaw, -30.0F, 30.0F);
		float headPitch = MathHelper.clamp(state.pitch, -25.0f, 45.0f);

		float targetXRot =  MathHelper.clamp(state.bodyPitch * 45.0f, -45.0f, 45.0f) * MathHelper.RADIANS_PER_DEGREE;
		float soarProgress = MathHelper.lerp(ageDelta, state.lastSoarTicks, state.soarTicks) / 5.0f;

		float lastBodyPitch = expDecay(state.lastAge, targetXRot, 8.0f, ageDelta / 20.0f);

		this.body.pitch = state.lastBodyPitch;
		this.head.yaw = headYaw * MathHelper.RADIANS_PER_DEGREE;
		this.head.pitch = (headPitch * MathHelper.RADIANS_PER_DEGREE) - lastBodyPitch;

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

	private static float expDecay(float a, float b, float decay, float dt) {
		return b + (a - b) * (float)Math.exp(-decay * dt);
	}
}