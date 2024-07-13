package com.teamabode.verdance.common.util;

import com.teamabode.verdance.common.block.SilkCocoonBlock;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

// Utils for the Silk Moth and Silkworm
public class SilkUtils {

    public static Optional<BlockPos> calculateLandingTarget(SilkMoth entity) {
        Vec3 pos = LandRandomPos.getPos(entity, 6, 3);
        if (pos == null) {
            return Optional.empty();
        }
        return Optional.of(BlockPos.containing(pos));
    }

    public static Optional<BlockPos> calculateStrollTarget(SilkMoth entity) {
        Vec3 view = entity.getViewVector(0.0f);
        Vec3 pos = HoverRandomPos.getPos(entity, 10, 7, view.x(), view.z(), 90.0f * Mth.DEG_TO_RAD, 3, 1);

        if (pos == null) {
            return Optional.empty();
        }
        return Optional.of(BlockPos.containing(pos));
    }

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
