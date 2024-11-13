package com.teamabode.verdance.common.util;

import com.teamabode.verdance.common.block.SilkCocoonBlock;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

// Utils for the Silk Moth and Silkworm
public class SilkUtils {

    public static Optional<BlockPos> calculateLandingTarget(SilkMothEntity entity) {
        Vec3d pos = FuzzyTargeting.find(entity, 6, 3);
        if (pos == null) {
            return Optional.empty();
        }
        return Optional.of(BlockPos.ofFloored(pos));
    }

    public static Optional<BlockPos> calculateStrollTarget(SilkMothEntity entity) {
        Vec3d view = entity.getRotationVec(0.0f);
        Vec3d pos = AboveGroundTargeting.find(entity, 10, 7, view.getX(), view.getZ(), 90.0f * MathHelper.RADIANS_PER_DEGREE, 3, 1);

        if (pos == null) {
            return Optional.empty();
        }
        return Optional.of(BlockPos.ofFloored(pos));
    }

    public static void transformIntoCocoon(ServerWorld level, SilkwormEntity entity, BlockPos pos, Direction direction) {
        BlockState state = VerdanceBlocks.SILK_COCOON.getDefaultState().with(SilkCocoonBlock.FACING, direction);
        level.setBlockState(pos, state);
        entity.discard();
    }

    public static Optional<BlockPos> getTargetPos(ServerWorld level, BlockPos origin) {
        return BlockPos.findClosest(origin, 10, 3, pos -> {
            BlockState state = level.getBlockState(pos);
            if (!state.isIn(BlockTags.LOGS_THAT_BURN)) return false;

            for (Direction dir : Direction.Type.HORIZONTAL) {
                BlockState dirState = level.getBlockState(pos.offset(dir));
                if (dirState.isIn(BlockTags.REPLACEABLE)) {
                    return true;
                }
            }
            return false;
        });
    }
}
