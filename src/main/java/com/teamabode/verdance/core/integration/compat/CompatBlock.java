package com.teamabode.verdance.core.integration.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.block.Block;

public class CompatBlock extends Block {
    private final String mod;

    public CompatBlock(String mod, Properties properties) {
        super(properties);
        this.mod = mod;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return FabricLoader.getInstance().isModLoaded(this.mod);
    }
}
