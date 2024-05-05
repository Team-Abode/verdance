package com.teamabode.verdance;

import com.teamabode.sketch.core.api.config.BooleanProperty;
import com.teamabode.sketch.core.api.config.Config;
import com.teamabode.sketch.core.api.config.IntProperty;

public class VerdanceConfig extends Config {
    public static final VerdanceConfig INSTANCE = new VerdanceConfig();

    public final BooleanProperty canBonemealSugarCane;
    public final IntProperty mulberryForestWeight;
    public final IntProperty shrublandsWeight;

    public VerdanceConfig() {
        super("verdance");

        this.canBonemealSugarCane = new BooleanProperty("can_bonemeal_sugar_cane", true);
        this.mulberryForestWeight = new IntProperty("mulberry_forest_weight", 4);
        this.shrublandsWeight = new IntProperty("shrublands_weight", 15);

        this.defineCategory("general", this.canBonemealSugarCane);
        this.defineCategory("biomes", this.mulberryForestWeight, this.shrublandsWeight);
    }
}
