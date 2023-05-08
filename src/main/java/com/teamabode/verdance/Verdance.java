package com.teamabode.verdance;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceFeatures;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceSounds;
import com.teamabode.verdance.core.registry.misc.VerdanceBlockSetType;
import com.teamabode.verdance.core.registry.misc.VerdanceGroupEvents;
import com.teamabode.verdance.core.registry.misc.VerdanceWoodType;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Verdance implements ModInitializer {
    public static final String MOD_ID = "verdance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        VerdanceBlockSetType.register();
        VerdanceWoodType.register();
        VerdanceBlocks.register();
        VerdanceItems.register();
        VerdanceFeatures.register();
        VerdanceSounds.register();
        VerdanceGroupEvents.register();
    }
}
