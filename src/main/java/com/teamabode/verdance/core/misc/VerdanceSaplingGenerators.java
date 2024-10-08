package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.registry.VerdanceConfiguredFeatures;
import java.util.Optional;
import net.minecraft.block.SaplingGenerator;

public class VerdanceSaplingGenerators {

    public static final SaplingGenerator MULBERRY = new SaplingGenerator("mulberry", Optional.empty(), Optional.of(VerdanceConfiguredFeatures.MULBERRY), Optional.of(VerdanceConfiguredFeatures.MULBERRY_WITH_SILK_COCOON));
}
