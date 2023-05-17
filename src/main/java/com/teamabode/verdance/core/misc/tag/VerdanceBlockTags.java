package com.teamabode.verdance.core.misc.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class VerdanceBlockTags {
    public static final TagKey<Block> MULBERRY_LOGS = create("mulberry_logs");
    public static final TagKey<Block> SHRUB_MAY_PLACE_ON = create("shrub_may_place_on");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name));
    }
}
