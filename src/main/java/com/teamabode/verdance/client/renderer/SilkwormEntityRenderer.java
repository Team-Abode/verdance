package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkwormEntityModel;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SilkwormEntityRenderer extends MobRenderer<SilkwormEntity, SilkwormEntityModel> {
    public static final ResourceLocation TEXTURE = Verdance.id("textures/entity/silkworm.png");

    public SilkwormEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SilkwormEntityModel(context.bakeLayer(SilkwormEntityModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTexture(SilkwormEntity entity) {
        return TEXTURE;
    }
}
