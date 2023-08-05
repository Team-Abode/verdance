package com.teamabode.verdance.core.integration.general;

import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;

public class CompatItem extends Item {
    public CompatItem() {
        super(new Properties());
    }

    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return false;
    }
}
