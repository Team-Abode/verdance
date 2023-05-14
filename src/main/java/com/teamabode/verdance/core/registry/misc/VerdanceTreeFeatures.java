package com.teamabode.verdance.core.registry.misc;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class VerdanceTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MULBERRY = createKey("mulberry");

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String key) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Verdance.MOD_ID, key));
    }

    public static void register() {

    }
}
