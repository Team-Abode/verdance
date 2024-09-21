package com.teamabode.verdance.datagen.client.model;

import com.teamabode.verdance.Verdance;
import java.util.Optional;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;

public class VerdanceModels {

    public static final Model CUSHION = createCushion(TextureKey.SIDE, TextureKey.TOP);


    public static Model createCushion(TextureKey... textureSlots) {
        return new Model(Optional.of(Verdance.id("block/template_cushion")), Optional.empty(), textureSlots);
    }
}
