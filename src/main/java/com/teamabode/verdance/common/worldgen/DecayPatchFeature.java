package com.teamabode.verdance.common.worldgen;

import com.teamabode.verdance.Verdance;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DecayPatchFeature extends Feature<DecayPatchConfiguration> {
    public DecayPatchFeature() {
        super(DecayPatchConfiguration.CODEC);
    }

    @Override
    public boolean generate(FeatureContext<DecayPatchConfiguration> context) {
        StructureWorldAccess level = context.getWorld();
        Random random = context.getRandom();
        BlockPos origin = context.getOrigin();
        List<DecayPatchConfiguration.Distance> distances = context.getConfig().distances();
        DecayPatchConfiguration.Patch patch = context.getConfig().patch();

        int successCount = 0;
        BlockPos.Mutable scanPos = new BlockPos.Mutable();
        int horizontalSpread = patch.horizontalSpread() + 1;
        int verticalSpread = patch.verticalSpread() + 1;

        for(int i = 0; i < patch.tries(); i++) {
            int randomX = random.nextInt(horizontalSpread) - random.nextInt(horizontalSpread);
            int randomY = random.nextInt(verticalSpread) - random.nextInt(verticalSpread);
            int randomZ = random.nextInt(horizontalSpread) - random.nextInt(horizontalSpread);

            scanPos.set(origin, randomX, randomY, randomZ);
            int distance = scanPos.getManhattanDistance(origin);

            for (DecayPatchConfiguration.Distance settings : distances) {
                if (distance >= settings.distance()) {
                    PlacedFeature feature = settings.feature().value();

                    if (feature.generateUnregistered(level, context.getGenerator(), random, scanPos)) {
                        successCount++;
                        break;
                    }
                }
            }
        }
        return successCount > 0;
    }
}
