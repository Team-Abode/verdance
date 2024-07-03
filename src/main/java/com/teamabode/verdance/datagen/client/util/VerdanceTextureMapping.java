package com.teamabode.verdance.datagen.client.util;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class VerdanceTextureMapping {

    public static TextureMapping cushionTextureMappings(DyeColor dyeColor) {
        return (new TextureMapping()).put(TextureSlot.SIDE, getCushionSideTexture(dyeColor)).put(TextureSlot.TOP, getCushionTexture(dyeColor));
    }

    public static ResourceLocation getCushionTexture(DyeColor colours) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(VerdanceBlocks.getCushion(colours));
        return resourceLocation.withPrefix("block/");
    }

    public static ResourceLocation getCushionSideTexture(DyeColor colours) {
        ResourceLocation resourceLocation = ResourceLocation.parse(BuiltInRegistries.BLOCK.getKey(VerdanceBlocks.getCushion(colours)) + "_side");
        return resourceLocation.withPrefix("block/");
    }
}
