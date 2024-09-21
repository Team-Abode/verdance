package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkwormModel;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SilkwormRenderer extends MobEntityRenderer<Silkworm, SilkwormModel> {
    public static final Identifier TEXTURE = Verdance.id("textures/entity/silkworm.png");

    public SilkwormRenderer(EntityRendererFactory.Context context) {
        super(context, new SilkwormModel(context.getPart(SilkwormModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public Identifier getTexture(Silkworm entity) {
        return TEXTURE;
    }
}
