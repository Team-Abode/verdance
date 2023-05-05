package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.feature.BoulderConfiguration;
import com.teamabode.verdance.common.feature.BoulderFeature;
import com.teamabode.verdance.common.feature.SequenceFeature;
import com.teamabode.verdance.common.feature.SequenceFeatureConfiguration;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class VerdanceFeatures {

    public static final Feature<BoulderConfiguration> BOULDER = register("boulder", new BoulderFeature());
    public static final Feature<SequenceFeatureConfiguration> SEQUENCE = register("sequence", new SequenceFeature());

    private static <T extends Feature<? extends FeatureConfiguration>> T register(String name, T feature) {
        return Registry.register(BuiltInRegistries.FEATURE, new ResourceLocation(Verdance.MOD_ID, name), feature);
    }

    public static void register() {

    }
}
