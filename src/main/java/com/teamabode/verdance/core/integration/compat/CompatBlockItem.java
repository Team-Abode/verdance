package com.teamabode.verdance.core.integration.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class CompatBlockItem extends BlockItem {
    private final String mod;

    public CompatBlockItem(String mod, Block block, Properties properties) {
        super(block, properties);
        this.mod = mod;
    }

    @Override
    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        return FabricLoader.getInstance().isModLoaded(mod);
    }
}
