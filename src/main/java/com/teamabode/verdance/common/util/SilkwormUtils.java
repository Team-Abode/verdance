package com.teamabode.verdance.common.util;

import com.teamabode.verdance.common.block.SilkCocoonBlock;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class SilkwormUtils {

    public static void transformIntoCocoon(ServerLevel level, Silkworm entity, BlockPos pos, Direction direction) {
        BlockState state = VerdanceBlocks.SILK_COCOON.defaultBlockState().setValue(SilkCocoonBlock.FACING, direction);
        level.setBlockAndUpdate(pos, state);
        entity.discard();
        // TODO: Play a unique sound
    }

    public static Optional<BlockPos> getTargetPos(ServerLevel level, BlockPos origin) {
        return BlockPos.findClosestMatch(origin, 10, 3, pos -> {
            BlockState state = level.getBlockState(pos);
            if (!state.is(BlockTags.LOGS_THAT_BURN)) return false;

            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockState dirState = level.getBlockState(pos.relative(dir));
                if (dirState.is(BlockTags.REPLACEABLE)) {
                    return true;
                }
            }
            return false;
        });
    }
}
