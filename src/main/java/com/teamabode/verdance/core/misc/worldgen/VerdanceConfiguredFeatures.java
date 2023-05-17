package com.teamabode.verdance.core.misc.worldgen;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class VerdanceConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SHRUB_PATCH_BONEMEAL = create("shrub_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MULBERRY = create("mulberry");

    private static ResourceKey<ConfiguredFeature<?, ?>> create(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Verdance.MOD_ID, name));
    }

}
