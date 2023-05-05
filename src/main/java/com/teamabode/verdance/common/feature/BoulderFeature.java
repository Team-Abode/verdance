package com.teamabode.verdance.common.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BoulderFeature extends Feature<BoulderConfiguration> {

    public BoulderFeature() {
        super(BoulderConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<BoulderConfiguration> featurePlaceContext) {
        BlockPos origin;
        WorldGenLevel level = featurePlaceContext.level();
        RandomSource random = featurePlaceContext.random();
        BoulderConfiguration config = featurePlaceContext.config();
        int height = config.height().sample(random);
        int radius = config.radius().sample(random);

        for (int i = 0; i < height; i++) {
            origin = featurePlaceContext.origin().above(i);
            for (BlockPos blockPos : BlockPos.betweenClosed(origin.offset(-radius, 0, -radius), origin.offset(radius, 0, radius))) {
                if (isWithinDistance(radius + 6, origin, blockPos)) {
                    setBlock(level, blockPos, random, config);
                }
            }
        }
        origin = featurePlaceContext.origin().above(height);
        for (BlockPos blockPos : BlockPos.betweenClosed(origin.offset(-radius, 0, -radius), origin.offset(radius, 1, radius))) {
            if (isWithinDistance(radius + 3, origin, blockPos)) {
                setBlock(level, blockPos, random, config);
            }
        }
        return true;
    }

    private static void setBlock(WorldGenLevel level, BlockPos pos, RandomSource randomSource, BoulderConfiguration config) {
        if (randomSource.nextFloat() > config.richChance()) {
            level.setBlock(pos, config.baseState(), 2);
            return;
        }
        level.setBlock(pos, config.richState(), 2);
    }

    private static boolean isWithinDistance(float distance, BlockPos center, BlockPos comparePos) {
        return center.distToLowCornerSqr(comparePos.getX(), comparePos.getY(), comparePos.getZ()) < distance;
    }
}
