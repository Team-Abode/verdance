package com.teamabode.verdance.datagen.client.util;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;

public class VerdanceTextureMapping {

    public static TextureMapping cushionTextureMappings(Block block) {
        return (new TextureMapping()).put(TextureSlot.SIDE, getCushionSideTexture(block)).put(TextureSlot.TOP, getCushionTexture(block));
    }

    public static ResourceLocation getCushionTexture(Block cushion) {
        ResourceLocation resourceLocation = BuiltInRegistries.BLOCK.getKey(cushion);
        return resourceLocation.withPrefix("block/");
    }

    public static ResourceLocation getCushionSideTexture(Block cushion) {
        ResourceLocation resourceLocation = ResourceLocation.parse(BuiltInRegistries.BLOCK.getKey(cushion) + "_side");
        return resourceLocation.withPrefix("block/");
    }
}
