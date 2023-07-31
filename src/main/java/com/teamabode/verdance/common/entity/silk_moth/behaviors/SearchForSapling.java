package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.google.common.collect.ImmutableMap;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.level.block.state.BlockState;

public class SearchForSapling extends Behavior<SilkMoth> {

    public SearchForSapling() {
        super(ImmutableMap.of(
                MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED
        ));
    }

    protected void start(ServerLevel level, SilkMoth entity, long gameTime) {
        Brain<SilkMoth> brain = entity.getBrain();
        BlockPos pos = this.targetPosition(level, entity.blockPosition());

        if (pos != null) {
            BlockPosTracker tracker = new BlockPosTracker(pos);
            brain.setMemory(MemoryModuleType.LOOK_TARGET, tracker);
            brain.setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(tracker, 1.25f, 0));
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
