package com.teamabode.verdance;

import com.teamabode.sketch.core.api.config.BooleanProperty;
import com.teamabode.sketch.core.api.config.Config;
import com.teamabode.sketch.core.api.config.FloatProperty;

public class VerdanceConfig extends Config {
    public static final VerdanceConfig INSTANCE = new VerdanceConfig();

    public final BooleanProperty canBonemealSugarCane;
    public final BooleanProperty canBonemealSporeBlossom;
    public final FloatProperty mulberryForestProportion;

    public VerdanceConfig() {
        super("verdance");

        this.canBonemealSugarCane = new BooleanProperty("can_bonemeal_sugar_cane", true);
        this.canBonemealSporeBlossom = new BooleanProperty("can_bonemeal_spore_blossom", true);
        this.mulberryForestProportion = new FloatProperty("mulberry_forest", 0.25f);

        this.defineCategory("general", this.canBonemealSugarCane, this.canBonemealSporeBlossom);
        this.defineCategory("biome_proportions", this.mulberryForestProportion);
    }
}
