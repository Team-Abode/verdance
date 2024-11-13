package com.teamabode.verdance;


import com.teamabode.scribe.core.api.config.Config;
import com.teamabode.scribe.core.api.config.ConfigBuilder;

public class VerdanceConfig {
    public static final Config INSTANCE = new ConfigBuilder(Verdance.MOD_ID)
            .addGroup("general", group -> {
                group.addBooleanProperty("can_bonemeal_sugar_cane", true);
                group.addBooleanProperty("can_bonemeal_spore_blossom", true);
                return group;
            })
            .addGroup("biome_proportions", group -> {
                group.addFloatProperty("mulberry_forest", 0.25f);
                group.addFloatProperty("shrublands", 0.35f);
                return group;
            })
            .build();

    public static void load() {}
}