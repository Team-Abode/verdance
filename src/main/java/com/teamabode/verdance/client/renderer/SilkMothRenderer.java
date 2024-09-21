package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothModel;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SilkMothRenderer extends MobEntityRenderer<SilkMoth, SilkMothModel> {
    private static final Identifier TEXTURE = Verdance.id("textures/entity/silk_moth/silk_moth.png");

    public SilkMothRenderer(EntityRendererFactory.Context context) {
        super(context, new SilkMothModel(context.getPart(SilkMothModel.LAYER)), 0.5f);
        this.addFeature(new SilkMothEyesLayer(this));
    }

    @Override
    public Identifier getTexture(SilkMoth entity) {
        return TEXTURE;
    }
}
