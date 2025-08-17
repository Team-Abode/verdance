package com.teamabode.verdance.core.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class VerdanceBlockTags {
    public static final TagKey<Block> MULBERRY_LOGS = create("mulberry_logs");
    public static final TagKey<Block> SILK_MOTHS_SPAWNABLE_ON = create("silk_moths_spawnable_on");
    public static final TagKey<Block> REPLACEABLE_BY_SUGAR_CANE = create("replaceable_by_sugar_cane");
    public static final TagKey<Block> SHRUBS = create("shrubs");
    public static final TagKey<Block> FLOWERING_SHRUBS = create("flowering_shrubs");
    public static final TagKey<Block> SHRUB_MAY_PLACE_ON = create("shrub_may_place_on");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Verdance.id(name));
    }
}
