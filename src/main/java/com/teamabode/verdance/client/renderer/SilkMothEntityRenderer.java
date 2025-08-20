package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothEntityModel;
import com.teamabode.verdance.client.state.SilkMothEntityRenderState;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SilkMothEntityRenderer extends MobEntityRenderer<SilkMothEntity, SilkMothEntityRenderState, SilkMothEntityModel> {
    private static final Identifier TEXTURE = Verdance.id("textures/entity/silk_moth/silk_moth.png");

    public SilkMothEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SilkMothEntityModel(context.getPart(SilkMothEntityModel.LAYER)), 0.5f);
        this.addFeature(new SilkMothEyesFeatureRenderer(this));
    }

    @Override
    public SilkMothEntityRenderState createRenderState() {
        return new SilkMothEntityRenderState();
    }

    @Override
    public void updateRenderState(SilkMothEntity entity, SilkMothEntityRenderState state, float tickProgress) {
        super.updateRenderState(entity, state, tickProgress);

        state.flying = entity.isFlying();
        state.soarProgress = entity.getSoaringAnimationProgress(tickProgress);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.flyAnimationState.copyFrom(entity.flyAnimationState);
    }

    @Override
    public Identifier getTexture(SilkMothEntityRenderState state) {
        return TEXTURE;
    }
}
