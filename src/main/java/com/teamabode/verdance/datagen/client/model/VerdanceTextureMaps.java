package com.teamabode.verdance.datagen.client.model;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class VerdanceTextureMaps {

    public static TextureMapping cushionTextureMappings(Block block) {
        return (new TextureMapping()).put(TextureSlot.SIDE, getCushionSideTexture(block)).put(TextureSlot.TOP, getCushionTexture(block));
    }

    public static ResourceLocation getCushionTexture(Block cushion) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(cushion);
        return resourceLocation.withPrefix("block/");
    }

    public static ResourceLocation getCushionSideTexture(Block cushion) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(cushion).withPath(path -> path + "_side");
        return resourceLocation.withPrefix("block/");
    }
}
