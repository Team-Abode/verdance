package com.teamabode.verdance.datagen.client.model;

import net.minecraft.block.Block;
import net.minecraft.client.data.TextureKey;
import net.minecraft.client.data.TextureMap;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class VerdanceTextureMaps {

    public static TextureMap cushionTextureMappings(Block block) {
        return (new TextureMap()).put(TextureKey.SIDE, getCushionSideTexture(block)).put(TextureKey.TOP, getCushionTexture(block));
    }

    public static Identifier getCushionTexture(Block cushion) {
        Identifier resourceLocation = Registries.BLOCK.getId(cushion);
        return resourceLocation.withPrefixedPath("block/");
    }

    public static Identifier getCushionSideTexture(Block cushion) {
        Identifier resourceLocation = Registries.BLOCK.getId(cushion).withPath(path -> path + "_side");
        return resourceLocation.withPrefixedPath("block/");
    }
}
