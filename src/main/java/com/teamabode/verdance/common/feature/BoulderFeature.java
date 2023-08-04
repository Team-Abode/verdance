package com.teamabode.verdance.common.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BoulderFeature extends Feature<BoulderConfiguration> {

    public BoulderFeature() {
        super(BoulderConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<BoulderConfiguration> context) {
        BoulderConfiguration config = context.config();
        RandomSource random = context.random();
        WorldGenLevel level = context.level();
        BlockPos origin = this.scanSurfacePos(level, context.origin());
        int height = config.height().sample(random);
        int width = random.nextInt(2,  5);

        float thickness = (1.0f - (float) 1 / height) * (width);
        for (int layer = 1; layer < height; layer++) {
            if (Mth.isMultipleOf(layer, 2)) {
                thickness = (1.0f - (float) layer / height) * (width);
            }
            int radius = Mth.ceil(thickness);
            for (int x = -radius; x <= radius; x++) {

                for (int z = -radius; z <= radius; z++) {
                    boolean withinRadius = (x * x + z * z) <= radius * radius;
                    BlockPos pos = origin.offset(x, layer, z);
                    BlockState state = level.getBlockState(pos);

                    if (withinRadius && canPlace(state)) {
                        this.setBlock(level, pos, this.getBlockState(config, random));
                    }
                }
            }
        }
        return true;
    }

    private static boolean canPlace(BlockState state) {
        return isDirt(state) || isStone(state) || state.is(BlockTags.SAND) || state.isAir() || state.getFluidState().is(FluidTags.WATER) || state.canBeReplaced();
    }

    private BlockState getBlockState(BoulderConfiguration config, RandomSource random) {
        if (config.oreChance() >= 1) return config.oreState();
        if (config.oreChance() <= 0) return config.baseState();

        return random.nextFloat() >= config.oreChance() ? config.baseState() : config.oreState();
    }

    private BlockPos scanSurfacePos(WorldGenLevel level, BlockPos pos) {
        MutableBlockPos mutablePos = new MutableBlockPos().set(pos);
        while (level.isEmptyBlock(mutablePos) && mutablePos.getY() > level.getMinBuildHeight() + 2) {
            mutablePos.set(mutablePos.below());
        }
        return mutablePos;
    }
}
