package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import org.apache.commons.lang3.mutable.MutableLong;

public class SearchForLeaves {
    private static final MutableLong LAST_EXECUTION = new MutableLong(0L);

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.absent(MemoryModuleType.WALK_TARGET),
                instance.registered(MemoryModuleType.LOOK_TARGET)
        ).apply(instance, (walkTargetMemory, lookTargetMemory) -> SearchForLeaves::attemptStart));
    }

    private static boolean attemptStart(ServerLevel level, SilkMoth entity, long gameTime) {
        if (gameTime < LAST_EXECUTION.getValue()) {
            LAST_EXECUTION.setValue(gameTime + 40L);
            return true;
        }
        BlockPos entityPos = entity.blockPosition();
        MutableBlockPos mutablePos = new MutableBlockPos();

        for (BlockPos scanPos : BlockPos.withinManhattan(entityPos, 10, 16, 10)) {
            boolean excludeCurrentPos = entityPos.getX() != scanPos.getX() || entityPos.getX() != scanPos.getZ();
            boolean foundLeaves = level.getBlockState(mutablePos.set(scanPos)).is(BlockTags.LEAVES);
            boolean isValidSpace = level.getBlockState(mutablePos.setWithOffset(scanPos, Direction.UP)).isAir();

            if (excludeCurrentPos && foundLeaves && isValidSpace) {
                BlockPosTracker tracker = new BlockPosTracker(mutablePos);

                entity.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, tracker);
                entity.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(tracker, 1.0f, 0));
                return true;
            }
        }
        LAST_EXECUTION.setValue(gameTime + 40L);
        return true;
    }
}
