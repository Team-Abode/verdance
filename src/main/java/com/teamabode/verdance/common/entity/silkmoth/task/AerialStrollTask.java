package com.teamabode.verdance.common.entity.silkmoth.task;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.common.util.SilkUtils;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;

public class AerialStrollTask extends ImprovedSingleTickTask<SilkMoth> {

    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING.get(), MemoryStatus.VALUE_PRESENT);
    }

    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        Optional<BlockPos> pos = SilkUtils.calculateStrollTarget(entity);
        pos.ifPresent(blockPos -> entity.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(new BlockPosTracker(blockPos), 1.0f, 0)));
    }
}
