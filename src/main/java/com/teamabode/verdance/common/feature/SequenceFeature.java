package com.teamabode.verdance.common.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class SequenceFeature extends Feature<SequenceFeatureConfiguration> {

    public SequenceFeature() {
        super(SequenceFeatureConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<SequenceFeatureConfiguration> featurePlaceContext) {
        SequenceFeatureConfiguration config = featurePlaceContext.config();
        WorldGenLevel level = featurePlaceContext.level();
        ChunkGenerator chunkGen = featurePlaceContext.chunkGenerator();
        RandomSource random = featurePlaceContext.random();
        BlockPos origin = featurePlaceContext.origin();

        int successes = 0;
        for (Holder<PlacedFeature> feature : config.placedFeatures()) {
            if (feature.value().place(level, chunkGen, random, origin)) successes++;
        }
        return successes > 0;
    }
}