package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedOneShot;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Map;

public class SearchForLeaves extends ImprovedOneShot<SilkMoth> {
    private long lastExecution = 0L;

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        requirements.put(MemoryModuleType.IS_PANICKING, MemoryStatus.REGISTERED);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED);
    }

    @Override
    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        if (gameTime > this.lastExecution) {
            this.lastExecution = gameTime + 40L;
            return;
        }
        BlockPos entityPos = entity.blockPosition();
        MutableBlockPos mutablePos = new MutableBlockPos();

        for (BlockPos scanPos : BlockPos.withinManhattan(entityPos, 15, 15, 15)) {
            boolean excludeCurrentPos = entityPos.getX() != scanPos.getX() || entityPos.getX() != scanPos.getZ();
            boolean foundLeaves = level.getBlockState(mutablePos.set(scanPos)).is(BlockTags.LEAVES);
            boolean isValidSpace = level.getBlockState(mutablePos.setWithOffset(scanPos, Direction.UP)).isAir();

            if (excludeCurrentPos && foundLeaves && isValidSpace) {
                BehaviorUtils.setWalkAndLookTargetMemories(entity, mutablePos, 1.0f, 0);
                return;
            }
        }
        this.lastExecution = gameTime + 40L;
    }
}
