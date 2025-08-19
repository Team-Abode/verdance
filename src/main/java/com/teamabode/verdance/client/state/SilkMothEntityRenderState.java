package com.teamabode.verdance.client.state;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class SilkMothEntityRenderState extends LivingEntityRenderState {
    public float lastAge = 0.0f;
    public boolean isGrounded = true;

    public float bodyPitch = 0.0f;
    public float lastBodyPitch = 0.0f;

    public int soarTicks = 0;
    public int lastSoarTicks = 0;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();
}
