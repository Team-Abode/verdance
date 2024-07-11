package com.teamabode.verdance.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public record CocoonPos(ResourceKey<Level> dimension, BlockPos pos, Direction direction) {

    public static CocoonPos of(ResourceKey<Level> dimension, BlockPos pos, Direction direction) {
        return new CocoonPos(dimension, pos, direction);
    }
}
