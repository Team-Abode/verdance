package com.teamabode.verdance.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public record SequenceFeatureConfiguration(List<Holder<PlacedFeature>> placedFeatures) implements FeatureConfiguration {
    public static final Codec<SequenceFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    PlacedFeature.CODEC.listOf().fieldOf("features").forGetter(SequenceFeatureConfiguration::placedFeatures))
            .apply(instance, SequenceFeatureConfiguration::new)
    );

    public SequenceFeatureConfiguration(List<Holder<PlacedFeature>> placedFeatures) {
        this.placedFeatures = placedFeatures;
    }
}