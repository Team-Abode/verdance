package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothEntityModel;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class SilkMothEyesFeatureRenderer extends EyesLayer<SilkMothEntity, SilkMothEntityModel> {
    private static final RenderType EYES = RenderType.eyes(Verdance.id("textures/entity/silk_moth/eyes.png"));

    public SilkMothEyesFeatureRenderer(RenderLayerParent<SilkMothEntity, SilkMothEntityModel> renderLayerParent) {
        super(renderLayerParent);
    }

    public RenderType renderType() {
        return EYES;
    }
}
