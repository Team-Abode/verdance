package com.teamabode.verdance.core.registry.misc;

import com.google.common.collect.Maps;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class VerdanceFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

    public static final BlockFamily WHITE_STUCCO = create(VerdanceBlocks.WHITE_STUCCO).stairs(VerdanceBlocks.WHITE_STUCCO_STAIRS).slab(VerdanceBlocks.WHITE_STUCCO_SLAB).wall(VerdanceBlocks.WHITE_STUCCO_WALL).getFamily();

    private static BlockFamily.Builder create(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = MAP.put(baseBlock, builder.getFamily());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
