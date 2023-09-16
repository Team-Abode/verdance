package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Map;

public class LandOnGround extends ImprovedOneShot<SilkMoth> {

    public void requires(Map<MemoryModuleType<?>, MemoryStatus> map) {
        map.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        map.put(VerdanceMemories.IS_FLYING, MemoryStatus.VALUE_PRESENT);
    }

    public boolean canRun(ServerLevel level, SilkMoth entity, long gameTime) {
        return entity.onGround();
    }

    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        entity.getNavigation().stop();
        entity.getBrain().eraseMemory(VerdanceMemories.IS_FLYING);
        entity.setFlying(false);
    }
}
