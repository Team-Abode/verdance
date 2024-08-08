package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedOneShot;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Map;

public class LandTask extends ImprovedOneShot<SilkMoth> {
    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_LAND, MemoryStatus.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryStatus.VALUE_PRESENT);
    }

    @Override
    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        if (entity.onGround()) {
            entity.land();
        }
    }
}
