package com.teamabode.verdance.datagen.client.util;

import com.teamabode.verdance.Verdance;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;

import java.util.Optional;

public class VerdanceModelTemplates {

    public static final ModelTemplate CUSHION = createCushion(TextureSlot.SIDE, TextureSlot.TOP);


    public static ModelTemplate createCushion(TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(Verdance.id("block/template_cushion")), Optional.empty(), textureSlots);
    }
}
