package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.google.common.collect.ImmutableMap;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;

public class SearchForSapling extends Behavior<SilkMoth> {

    public SearchForSapling() {
        super(ImmutableMap.of(
                VerdanceMemories.POLLINATE_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT
        ));
    }

    protected void start(ServerLevel level, SilkMoth entity, long gameTime) {
        BlockPos pos = this.targetPosition(level, entity.blockPosition());

        if (pos != null) {
            GlobalPos pollinateTarget = GlobalPos.of(entity.level().dimension(), pos);
            entity.getBrain().setMemory(VerdanceMemories.POLLINATE_TARGET, pollinateTarget);
        }
    }

    private BlockPos targetPosition(ServerLevel level, BlockPos origin) {
        MutableBlockPos mutablePos = new MutableBlockPos();
        for (BlockPos pos : BlockPos.withinManhattan(origin, 20, 8, 20)) {
            BlockState state = level.getBlockState(mutablePos.set(pos));
            if (SilkMothAi.CANNOT_POLLINATE.test(state)) continue;
            return mutablePos;
        }
        return null;
    }
}
