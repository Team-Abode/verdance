package com.teamabode.verdance.core.integration.general;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;

public class CompatBlock extends Block {
    public CompatBlock() {
        super(Properties.of().instabreak());
    }

    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return false;
    }
}
