package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothEntityModel;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SilkMothEntityRenderer extends MobRenderer<SilkMothEntity, SilkMothEntityModel> {
    private static final ResourceLocation TEXTURE = Verdance.id("textures/entity/silk_moth/silk_moth.png");

    public SilkMothEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SilkMothEntityModel(context.bakeLayer(SilkMothEntityModel.LAYER)), 0.5f);
        this.addLayer(new SilkMothEyesFeatureRenderer(this));
    }

    @Override
    public ResourceLocation getTexture(SilkMothEntity entity) {
        return TEXTURE;
    }
}
