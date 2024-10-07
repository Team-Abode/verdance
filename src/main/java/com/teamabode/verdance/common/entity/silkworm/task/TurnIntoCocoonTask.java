package com.teamabode.verdance.common.entity.silkworm.task;

import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.common.util.SilkUtils;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class TurnIntoCocoonTask extends ImprovedSingleTickTask<SilkwormEntity> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements) {
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_COCOON, MemoryModuleState.VALUE_PRESENT);
    }

    @Override
    public void run(ServerWorld level, SilkwormEntity entity, long gameTime) {
        if (!entity.getBlockStateAtPos().isIn(BlockTags.REPLACEABLE)) return;

        for (Direction dir : Direction.Type.HORIZONTAL) {
            BlockPos dirPos = entity.getBlockPos().offset(dir);
            BlockState dirState = level.getBlockState(dirPos);
            if (!dirState.isIn(BlockTags.LOGS_THAT_BURN)) continue;

            SilkUtils.transformIntoCocoon(level, entity, entity.getBlockPos(), dir);
        }
    }
}
