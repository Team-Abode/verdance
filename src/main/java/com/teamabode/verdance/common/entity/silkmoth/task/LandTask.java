package com.teamabode.verdance.common.entity.silkmoth.task;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class LandTask extends ImprovedSingleTickTask<SilkMoth> {
    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_LAND.get(), MemoryStatus.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING.get(), MemoryStatus.VALUE_PRESENT);
    }

    @Override
    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        if (entity.onGround()) {
            entity.land();
        }
    }
}
