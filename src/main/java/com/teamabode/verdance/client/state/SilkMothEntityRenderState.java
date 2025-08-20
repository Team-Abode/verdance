package com.teamabode.verdance.client.state;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class SilkMothEntityRenderState extends LivingEntityRenderState {
    public boolean flying = true;

    public float soarProgress = 0.0f;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState flyAnimationState = new AnimationState();
}
