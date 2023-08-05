package com.teamabode.verdance.core.integration.general;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class CompatBlockItem extends BlockItem {
    private final boolean isEnabled;

    public CompatBlockItem(Block block, boolean isEnabled) {
        super(block, new Properties());
        this.isEnabled = isEnabled;
    }

    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return this.isEnabled;
    }
}
