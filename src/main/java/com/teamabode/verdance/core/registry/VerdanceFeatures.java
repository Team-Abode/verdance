package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.DecayPatchConfiguration;
import com.teamabode.verdance.common.worldgen.DecayPatchFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class VerdanceFeatures {
    public static final Feature<DecayPatchConfiguration> DECAY_PATCH = register("decay_patch", new DecayPatchFeature());

    private static <FC extends FeatureConfig> Feature<FC> register(String id, Feature<FC> feature) {
        return Registry.register(Registries.FEATURE, Verdance.id(id), feature);
    }

    public static void register() {}
}
