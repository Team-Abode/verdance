package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedOneShot;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Map;

// TODO: Target a surface properly and land on it.
public class LandOnGround extends ImprovedOneShot<SilkMoth> {

    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryStatus.VALUE_PRESENT);
    }

    public boolean canRun(ServerLevel level, SilkMoth entity, long gameTime) {
        return entity.onGround() && entity.getRandom().nextInt(3) == 0;
    }

    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        entity.getNavigation().stop();
        entity.getBrain().eraseMemory(VerdanceMemoryModuleTypes.IS_FLYING);
        entity.setFlying(false);
    }
}
