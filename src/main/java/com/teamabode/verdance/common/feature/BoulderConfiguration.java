package com.teamabode.verdance.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record BoulderConfiguration(BlockState baseState, BlockState oreState, float oreChance, IntProvider height) implements FeatureConfiguration {

    public static final Codec<BoulderConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockState.CODEC.fieldOf("base_state").forGetter(BoulderConfiguration::baseState),
            BlockState.CODEC.fieldOf("ore_state").forGetter(BoulderConfiguration::oreState),
            Codec.floatRange(0, 1).fieldOf("ore_chance").forGetter(BoulderConfiguration::oreChance),
            IntProvider.codec(1, 16).fieldOf("height").forGetter(BoulderConfiguration::height)
    ).apply(instance, BoulderConfiguration::new));

    public BoulderConfiguration(BlockState baseState, BlockState oreState, float oreChance, IntProvider height) {
        this.baseState = baseState;
        this.oreState = oreState;
        this.oreChance = oreChance;
        this.height = height;
    }
}
