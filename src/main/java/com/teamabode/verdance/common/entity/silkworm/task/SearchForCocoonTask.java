package com.teamabode.verdance.common.entity.silkworm.task;

import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import com.teamabode.verdance.common.util.SilkUtil;
import java.util.Map;
import java.util.Optional;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.TargetUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class SearchForCocoonTask extends ImprovedSingleTickTask<SilkwormEntity> {
    private long lastExecution = 0L; // It should only try to attempt this task around every four seconds.

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED);
    }

    @Override
    public void run(ServerWorld level, SilkwormEntity entity, long gameTime) {
        if (gameTime > this.lastExecution) {
            this.lastExecution = gameTime + 80L;
            return;
        }
        Optional<BlockPos> targetPos = SilkUtil.getTargetPos(level, entity.getBlockPos());

        if (targetPos.isPresent()) {
            TargetUtil.walkTowards(entity, targetPos.get(), 2.0f, 0);
            return;
        }
        this.lastExecution = gameTime + 80L;
    }
}
