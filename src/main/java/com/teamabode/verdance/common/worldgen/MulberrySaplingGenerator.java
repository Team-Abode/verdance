package com.teamabode.verdance.common.worldgen;

import com.teamabode.verdance.core.registry.VerdanceConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class MulberrySaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean flowered) {
        return flowered ? VerdanceConfiguredFeatures.MULBERRY_WITH_SILK_COCOON : VerdanceConfiguredFeatures.MULBERRY;
    }
}
