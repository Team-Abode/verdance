package com.teamabode.verdance.common.entity.silkmoth.task;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedOneShot;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Map;
import java.util.Optional;

public class GoTowardsLightSource extends ImprovedOneShot<SilkMoth> {
    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        requirements.put(VerdanceMemoryModuleTypes.NEAREST_LIGHT_SOURCE.get(), MemoryStatus.VALUE_PRESENT);
    }

    @Override
    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<BlockPos> lightSourcePos = brain.getMemory(VerdanceMemoryModuleTypes.NEAREST_LIGHT_SOURCE.get());

        lightSourcePos.ifPresent(pos -> BehaviorUtils.setWalkAndLookTargetMemories(entity, pos, 1.0f, 2));
    }
}
