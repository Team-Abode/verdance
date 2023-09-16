package com.teamabode.verdance.client.renderer;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkwormModel;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SilkwormRenderer extends MobRenderer<Silkworm, SilkwormModel> {

    public SilkwormRenderer(EntityRendererProvider.Context context) {
        super(context, new SilkwormModel(context.bakeLayer(SilkwormModel.LAYER_LOCATION)), 0.25f);
    }

    public ResourceLocation getTextureLocation(Silkworm entity) {
        return Verdance.id("textures/entity/silkworm.png");
    }
}
