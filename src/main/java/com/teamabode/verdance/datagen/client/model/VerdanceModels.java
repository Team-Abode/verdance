package com.teamabode.verdance.datagen.client.model;

import com.teamabode.verdance.Verdance;
import java.util.Optional;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;

public class VerdanceModels {

    public static final ModelTemplate CUSHION = createCushion(TextureSlot.SIDE, TextureSlot.TOP);


    public static ModelTemplate createCushion(TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(Verdance.id("block/template_cushion")), Optional.empty(), textureSlots);
    }
}
