package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothEntityModel;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;

public class SilkMothEyesLayer extends EyesFeatureRenderer<SilkMothEntity, SilkMothEntityModel> {
    private static final RenderLayer EYES = RenderLayer.getEyes(Verdance.id("textures/entity/silk_moth/eyes.png"));

    public SilkMothEyesLayer(FeatureRendererContext<SilkMothEntity, SilkMothEntityModel> renderLayerParent) {
        super(renderLayerParent);
    }

    public RenderLayer getEyesTexture() {
        return EYES;
    }
}
