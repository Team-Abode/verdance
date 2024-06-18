package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.feature.BoulderConfiguration;
import com.teamabode.verdance.common.worldgen.feature.BoulderFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class VerdanceFeatures {

    public static final Feature<BoulderConfiguration> BOULDER = register("boulder", new BoulderFeature());

    private static <T extends Feature<? extends FeatureConfiguration>> T register(String name, T feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Verdance.id(name), feature);
    }

    public static void register() {

    }
}
