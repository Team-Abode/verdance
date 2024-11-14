package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothEntityModel;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SilkMothEntityRenderer extends MobEntityRenderer<SilkMothEntity, SilkMothEntityModel> {
    private static final Identifier TEXTURE = Verdance.id("textures/entity/silk_moth/silk_moth.png");

    public SilkMothEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SilkMothEntityModel(context.getPart(SilkMothEntityModel.LAYER)), 0.5f);
        this.addFeature(new SilkMothEyesFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(SilkMothEntity entity) {
        return TEXTURE;
    }
}
