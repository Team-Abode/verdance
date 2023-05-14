package com.teamabode.verdance;

import com.teamabode.scribe.core.api.config.Config;
import com.teamabode.scribe.core.api.config.ConfigBuilder;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceFeatures;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceSounds;
import com.teamabode.verdance.core.registry.misc.*;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Verdance implements ModInitializer {
    public static final String MOD_ID = "verdance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Config CONFIG = new ConfigBuilder(MOD_ID)
            .addBooleanProperty("bonemealable_sugar_cane", true)
            .build();

    public void onInitialize() {
        VerdanceBlocks.register();
        VerdanceItems.register();
        VerdanceFeatures.register();
        VerdanceSounds.register();
        VerdanceGroupEvents.register();
        VerdanceBlockSetType.register();
        VerdanceWoodType.register();
        VerdanceTrunkPlacerType.register();
    }
}
