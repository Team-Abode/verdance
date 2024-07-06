package com.teamabode.verdance.core.misc.key;

import com.teamabode.verdance.Verdance;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class VerdanceBiomes {
    public static final ResourceKey<Biome> MULBERRY_FOREST = create("mulberry_forest");
    public static final ResourceKey<Biome> SHRUBLANDS = create("shrublands");

    private static ResourceKey<Biome> create(String name) {
        return ResourceKey.create(Registries.BIOME, Verdance.id(name));
    }
}
