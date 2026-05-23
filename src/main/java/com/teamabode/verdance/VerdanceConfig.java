package com.teamabode.verdance;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class VerdanceConfig {
    private static final Pair<VerdanceConfig, ModConfigSpec> PAIR = new ModConfigSpec.Builder().configure(VerdanceConfig::new);
    public static final VerdanceConfig INSTANCE = PAIR.getLeft();
    public static final ModConfigSpec SPEC = PAIR.getRight();

    public final ModConfigSpec.BooleanValue canBonemealSugarCane;
    public final ModConfigSpec.BooleanValue canBonemealSporeBlossom;
    public final ModConfigSpec.DoubleValue mulberryForestProportion;
    public final ModConfigSpec.DoubleValue shrublandsProportion;

    private VerdanceConfig(ModConfigSpec.Builder builder) {
        this.canBonemealSugarCane = builder.define(
                "can_bonemeal_sugar_cane",
                true
        );
        this.canBonemealSporeBlossom = builder.define(
                "can_bonemeal_spore_blossom",
                true
        );
        this.mulberryForestProportion = builder.defineInRange(
                "mulberry_forest_proportion",
                0.25d,
                0.0d,
                1.0d

        );
        this.shrublandsProportion = builder.defineInRange(
                "shrublands_proportion",
                0.35d,
                0.0d,
                1.0d
        );
    }

    static {
        Pair<VerdanceConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(VerdanceConfig::new);
    }
}
