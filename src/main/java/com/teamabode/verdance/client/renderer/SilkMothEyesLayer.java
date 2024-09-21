package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothModel;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;

public class SilkMothEyesLayer extends EyesFeatureRenderer<SilkMoth, SilkMothModel> {
    private static final RenderLayer EYES = RenderLayer.getEyes(Verdance.id("textures/entity/silk_moth/eyes.png"));

    public SilkMothEyesLayer(FeatureRendererContext<SilkMoth, SilkMothModel> renderLayerParent) {
        super(renderLayerParent);
    }

    public RenderLayer getEyesTexture() {
        return EYES;
    }
}
