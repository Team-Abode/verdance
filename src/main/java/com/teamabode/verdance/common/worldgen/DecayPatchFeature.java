package com.teamabode.verdance.common.worldgen;

import com.teamabode.verdance.Verdance;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class DecayPatchFeature extends Feature<DecayPatchConfiguration> {
    public DecayPatchFeature() {
        super(DecayPatchConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<DecayPatchConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        List<DecayPatchConfiguration.Distance> distances = context.config().distances();
        DecayPatchConfiguration.Patch patch = context.config().patch();

        int successCount = 0;
        BlockPos.MutableBlockPos scanPos = new BlockPos.MutableBlockPos();
        int horizontalSpread = patch.horizontalSpread() + 1;
        int verticalSpread = patch.verticalSpread() + 1;

        for(int i = 0; i < patch.tries(); i++) {
            int randomX = random.nextInt(horizontalSpread) - random.nextInt(horizontalSpread);
            int randomY = random.nextInt(verticalSpread) - random.nextInt(verticalSpread);
            int randomZ = random.nextInt(horizontalSpread) - random.nextInt(horizontalSpread);

            scanPos.setWithOffset(origin, randomX, randomY, randomZ);
            int distance = scanPos.distManhattan(origin);

            for (DecayPatchConfiguration.Distance settings : distances) {
                if (distance >= settings.distance()) {
                    PlacedFeature feature = settings.feature().value();

                    if (feature.place(level, context.chunkGenerator(), random, scanPos)) {
                        successCount++;
                        break;
                    }
                }
            }
        }
        return successCount > 0;
    }
}
