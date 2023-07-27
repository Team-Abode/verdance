package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothModel;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;

public class SilkMothEyesLayer extends EyesLayer<SilkMoth, SilkMothModel> {
    private static final RenderType EYES = RenderType.eyes(Verdance.id("textures/entity/silk_moth/eyes.png"));

    public SilkMothEyesLayer(RenderLayerParent<SilkMoth, SilkMothModel> renderLayerParent) {
        super(renderLayerParent);
    }

    public RenderType renderType() {
        return EYES;
    }
}
