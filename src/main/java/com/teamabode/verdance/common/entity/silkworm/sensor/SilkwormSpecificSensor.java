package com.teamabode.verdance.common.entity.silkworm.sensor;

import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import java.util.Optional;
import java.util.Set;

public class SilkwormSpecificSensor extends Sensor<Silkworm> {

    @Override
    protected void sense(ServerWorld world, Silkworm entity) {
        Brain<Silkworm> brain = entity.getBrain();
        Optional<Unit> wantsToCocoon = Optional.empty();

        if (entity.getAge() > 24000) {
            wantsToCocoon = Optional.of(Unit.INSTANCE);
        }
        brain.remember(VerdanceMemoryModuleTypes.WANTS_TO_COCOON, wantsToCocoon);
    }

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return Set.of(VerdanceMemoryModuleTypes.WANTS_TO_COCOON);
    }
}
