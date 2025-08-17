package com.teamabode.verdance.common.entity.silkmoth.task;

import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class TakeOffTask extends ImprovedSingleTickTask<SilkMothEntity> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryStatus.VALUE_ABSENT);
    }

    @Override
    public boolean canRun(ServerLevel level, SilkMothEntity entity, long gameTime) {
        return entity.onGround();
    }

    @Override
    public void run(ServerLevel level, SilkMothEntity entity, long gameTime) {
        entity.takeOff();
    }
}
