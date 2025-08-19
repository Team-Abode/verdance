package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkwormEntityModel;
import com.teamabode.verdance.client.state.SilkwormEntityRenderState;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SilkwormEntityRenderer extends MobEntityRenderer<SilkwormEntity, SilkwormEntityRenderState, SilkwormEntityModel> {
    public static final Identifier TEXTURE = Verdance.id("textures/entity/silkworm.png");

    public SilkwormEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SilkwormEntityModel(context.getPart(SilkwormEntityModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public SilkwormEntityRenderState createRenderState() {
        return new SilkwormEntityRenderState();
    }

    @Override
    public Identifier getTexture(SilkwormEntityRenderState state) {
        return TEXTURE;
    }
}
