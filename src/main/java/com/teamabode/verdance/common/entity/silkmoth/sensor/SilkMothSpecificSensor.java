package com.teamabode.verdance.common.entity.silkmoth.sensor;

import com.google.common.collect.ImmutableSet;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;

import java.util.Optional;
import java.util.Set;

public class SilkMothSpecificSensor extends Sensor<SilkMoth> {

    protected void doTick(ServerLevel level, SilkMoth entity) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<Unit> isFlying = Optional.empty();

        if (entity.isFlying()) {
            isFlying = Optional.of(Unit.INSTANCE);
        }
        brain.setMemory(VerdanceMemoryModuleTypes.IS_FLYING, isFlying);
    }

    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.of(
                VerdanceMemoryModuleTypes.IS_FLYING
        );
    }
}
