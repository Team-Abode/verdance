package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.server.world.ServerWorld;

public class TakeOffTask extends ImprovedSingleTickTask<SilkMothEntity> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements) {
        requirements.put(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleState.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryModuleState.VALUE_ABSENT);
    }

    @Override
    public boolean canRun(ServerWorld level, SilkMothEntity entity, long gameTime) {
        return entity.isOnGround();
    }

    @Override
    public void run(ServerWorld level, SilkMothEntity entity, long gameTime) {
        entity.takeOff();
    }
}
