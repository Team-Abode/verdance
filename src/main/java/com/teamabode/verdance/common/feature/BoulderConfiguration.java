package com.teamabode.verdance.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record BoulderConfiguration(IntProvider height, IntProvider radius, float richChance, BlockState baseState, BlockState richState) implements FeatureConfiguration {
    public static final Codec<BoulderConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            IntProvider.codec(1, 16).fieldOf("height").forGetter(BoulderConfiguration::height),
            IntProvider.codec(1, 8).fieldOf("radius").forGetter(BoulderConfiguration::radius),
            Codec.floatRange(0.0F, 1.0F).fieldOf("rich_chance").forGetter(BoulderConfiguration::richChance),
            BlockState.CODEC.fieldOf("base_state").forGetter(BoulderConfiguration::baseState),
            BlockState.CODEC.fieldOf("rich_state").forGetter(BoulderConfiguration::richState)
    ).apply(instance, BoulderConfiguration::new));

    public BoulderConfiguration(IntProvider height, IntProvider radius, float richChance, BlockState baseState, BlockState richState) {
        this.height = height;
        this.radius = radius;
        this.richChance = richChance;
        this.baseState = baseState;
        this.richState = richState;
    }
}
