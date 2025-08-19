package com.teamabode.verdance.core.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class VerdanceBlockTags {
    public static final TagKey<Block> MULBERRY_LOGS = create("mulberry_logs");
    public static final TagKey<Block> SILK_MOTHS_SPAWNABLE_ON = create("silk_moths_spawnable_on");
    public static final TagKey<Block> REPLACEABLE_BY_SUGAR_CANE = create("replaceable_by_sugar_cane");
    public static final TagKey<Block> DESERT_BUSHES = create("desert_bushes");
    public static final TagKey<Block> FLOWERING_DESERT_BUSHES = create("flowering_desert_bushes");
    public static final TagKey<Block> SHRUB_MAY_PLACE_ON = create("shrub_may_place_on");

    private static TagKey<Block> create(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Verdance.id(name));
    }
}
