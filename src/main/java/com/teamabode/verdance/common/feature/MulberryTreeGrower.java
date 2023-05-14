package com.teamabode.verdance.common.feature;

import com.teamabode.verdance.core.registry.VerdanceConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class MulberryTreeGrower extends AbstractTreeGrower {

    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean hasFlowers) {
        return VerdanceConfiguredFeatures.MULBERRY;
    }
}
