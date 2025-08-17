package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.registry.VerdanceConfiguredFeatures;
import java.util.Optional;
import net.minecraft.world.level.block.grower.TreeGrower;

public class VerdanceSaplingGenerators {

    public static final TreeGrower MULBERRY = new TreeGrower("mulberry", Optional.empty(), Optional.of(VerdanceConfiguredFeatures.MULBERRY), Optional.of(VerdanceConfiguredFeatures.MULBERRY_WITH_SILK_COCOON));
}
