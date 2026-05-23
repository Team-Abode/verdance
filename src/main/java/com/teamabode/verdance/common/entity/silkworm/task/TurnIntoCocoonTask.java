package com.teamabode.verdance.common.entity.silkworm.task;

import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.common.util.SilkUtils;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;

public class TurnIntoCocoonTask extends ImprovedSingleTickTask<Silkworm> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_COCOON.get(), MemoryStatus.VALUE_PRESENT);
    }

    @Override
    public void run(ServerLevel level, Silkworm entity, long gameTime) {
        if (!entity.getInBlockState().is(BlockTags.REPLACEABLE)) return;

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos dirPos = entity.blockPosition().relative(dir);
            BlockState dirState = level.getBlockState(dirPos);
            if (!dirState.is(BlockTags.LOGS_THAT_BURN)) continue;

            SilkUtils.transformIntoCocoon(level, entity, entity.blockPosition(), dir);
        }
    }
}
