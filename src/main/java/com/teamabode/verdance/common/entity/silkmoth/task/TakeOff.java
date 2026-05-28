package com.teamabode.verdance.common.entity.silkmoth.task;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedOneShot;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class TakeOff extends ImprovedOneShot<SilkMoth> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING.get(), MemoryStatus.VALUE_ABSENT);
    }

    @Override
    public boolean canRun(ServerLevel level, SilkMoth entity, long gameTime) {
        return entity.onGround();
    }

    @Override
    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        entity.takeOff();
    }
}
