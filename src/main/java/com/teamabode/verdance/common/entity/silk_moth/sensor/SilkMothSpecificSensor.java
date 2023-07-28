package com.teamabode.verdance.common.entity.silk_moth.sensor;

import com.google.common.collect.ImmutableSet;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
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
                VerdanceMemories.POLLINATE_TARGET,
                VerdanceMemories.SAPLINGS_POLLINATED
        );
    }

    protected void doTick(ServerLevel level, SilkMoth entity) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<GlobalPos> saplingPos = entity.getBrain().getMemory(VerdanceMemories.POLLINATE_TARGET);
        Optional<Unit> isPollinating = Optional.empty();

        if (saplingPos.isPresent()) {
            BlockPos entityPos = entity.blockPosition();
            double distance = entityPos.distManhattan(saplingPos.get().pos());

            if (entity.level().dimension() == saplingPos.get().dimension() && distance < 2.0) {
                isPollinating = Optional.of(Unit.INSTANCE);
            }
        }
        brain.setMemory(VerdanceMemories.IS_POLLINATING, isPollinating);
    }
}
