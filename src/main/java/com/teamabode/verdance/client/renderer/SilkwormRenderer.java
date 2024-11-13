package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkwormEntityModel;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SilkwormRenderer extends MobEntityRenderer<SilkwormEntity, SilkwormEntityModel> {
    public static final Identifier TEXTURE = Verdance.id("textures/entity/silkworm.png");

    public SilkwormRenderer(EntityRendererFactory.Context context) {
        super(context, new SilkwormEntityModel(context.getPart(SilkwormEntityModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public Identifier getTexture(SilkwormEntity entity) {
        return TEXTURE;
    }
}
