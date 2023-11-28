package com.teamabode.verdance.core.integration.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;

public class CompatItem extends Item {
    private final String mod;

    public CompatItem(String mod, Properties properties) {
        super(properties);
        this.mod = mod;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return FabricLoader.getInstance().isModLoaded(this.mod);
    }
}
