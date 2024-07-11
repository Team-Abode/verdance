package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.key.VerdanceConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class VerdanceTreeGrowers {

    public static final TreeGrower MULBERRY = new TreeGrower("mulberry", Optional.empty(), Optional.of(VerdanceConfiguredFeatures.MULBERRY), Optional.empty());
}
