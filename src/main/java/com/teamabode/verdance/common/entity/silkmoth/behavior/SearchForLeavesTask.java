package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import java.util.Map;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction;

public class SearchForLeavesTask extends ImprovedSingleTickTask<SilkMothEntity> {
    private long lastExecution = 0L;

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT);
        requirements.put(MemoryModuleType.IS_PANICKING, MemoryModuleState.REGISTERED);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED);
    }

    @Override
    public void run(ServerWorld level, SilkMothEntity entity, long gameTime) {
        if (gameTime > this.lastExecution) {
            this.lastExecution = gameTime + 40L;
            return;
        }
        BlockPos entityPos = entity.getBlockPos();
        Mutable mutablePos = new Mutable();

        for (BlockPos scanPos : BlockPos.iterateOutwards(entityPos, 15, 15, 15)) {
            boolean excludeCurrentPos = entityPos.getX() != scanPos.getX() || entityPos.getX() != scanPos.getZ();
            boolean foundLeaves = level.getBlockState(mutablePos.set(scanPos)).isIn(BlockTags.LEAVES);
            boolean isValidSpace = level.getBlockState(mutablePos.set(scanPos, Direction.UP)).isAir();

            if (excludeCurrentPos && foundLeaves && isValidSpace) {
                LookTargetUtil.walkTowards(entity, mutablePos, 1.0f, 0);
                return;
            }
        }
        this.lastExecution = gameTime + 40L;
    }
}
