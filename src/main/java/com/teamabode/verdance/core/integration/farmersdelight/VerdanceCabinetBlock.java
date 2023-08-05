package com.teamabode.verdance.core.integration.farmersdelight;

import com.nhoryzon.mc.farmersdelight.block.CabinetBlock;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.NotNull;

public class VerdanceCabinetBlock extends CabinetBlock {
    public VerdanceCabinetBlock() {
        super();
    }

    @Override
    public boolean isEnabled(@NotNull FeatureFlagSet enabledFeatures) {
        return FabricLoader.getInstance().isModLoaded("farmersdelight");
    }
}
