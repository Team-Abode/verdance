package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.DecayPatchConfiguration;
import com.teamabode.verdance.common.worldgen.DecayPatchFeature;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class VerdanceFeatures {
    public static final Feature<DecayPatchConfiguration> DECAY_PATCH = register("decay_patch", new DecayPatchFeature());

    private static <FC extends FeatureConfiguration> Feature<FC> register(String id, Feature<FC> feature) {
        return Registry.register(BuiltInRegistries.FEATURE, Verdance.id(id), feature);
    }

    public static void register() {}
}
