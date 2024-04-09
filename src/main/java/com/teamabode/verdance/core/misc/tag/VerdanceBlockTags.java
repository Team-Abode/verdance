package com.teamabode.verdance.core.misc.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class VerdanceBlockTags {
    public static final TagKey<Block> MULBERRY_LOGS = create("mulberry_logs");
    public static final TagKey<Block> SHRUB_MAY_PLACE_ON = create("shrub_may_place_on");
    public static final TagKey<Block> GHOST_TOWN_DEGRADABLE = create("ghost_town_degradable");
    public static final TagKey<Block> SILK_MOTHS_SPAWNABLE_ON = create("silk_moths_spawnable_on");
    public static final TagKey<Block> REPLACEABLE_BY_SUGAR_CANE = create("replaceable_by_sugar_cane");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name));
    }
}
