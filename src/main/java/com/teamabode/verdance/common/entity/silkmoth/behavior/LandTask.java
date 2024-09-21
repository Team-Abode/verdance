package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.server.world.ServerWorld;

public class LandTask extends ImprovedSingleTickTask<SilkMoth> {
    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements) {
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_LAND, MemoryModuleState.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryModuleState.VALUE_PRESENT);
    }

    @Override
    public void run(ServerWorld level, SilkMoth entity, long gameTime) {
        if (entity.isOnGround()) {
            entity.land();
        }
    }
}
