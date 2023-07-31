package com.teamabode.verdance.common.entity.silk_moth.sensor;

import com.google.common.collect.ImmutableSet;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;

import java.util.Optional;
import java.util.Set;

public class SilkMothSpecificSensor extends Sensor<SilkMoth> {

    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.of(
                VerdanceMemories.IS_POLLINATING,
                VerdanceMemories.SAPLINGS_POLLINATED
        );
    }

    protected void doTick(ServerLevel level, SilkMoth entity) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<Unit> isPollinating = Optional.empty();

        Optional<Integer> saplingsPollinated = brain.getMemory(VerdanceMemories.SAPLINGS_POLLINATED);

       if (saplingsPollinated.isEmpty() || saplingsPollinated.get() < 20) {
           isPollinating = Optional.of(Unit.INSTANCE);
       }

        Verdance.LOGGER.info("Time of day: " + level.dayTime());

       if (level.dayTime() >= 13000 && level.dayTime() <= 13080) {
           Verdance.LOGGER.info("Can pollinate once more!");
           brain.eraseMemory(VerdanceMemories.SAPLINGS_POLLINATED);
       }
       brain.setMemory(VerdanceMemories.IS_POLLINATING, isPollinating);
    }
}
