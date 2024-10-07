package com.teamabode.verdance.common.entity.silkworm.sensor;

import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;
import java.util.Optional;
import java.util.Set;

public class SilkwormSpecificSensor extends Sensor<SilkwormEntity> {

    @Override
    protected void sense(ServerWorld world, SilkwormEntity entity) {
        Brain<SilkwormEntity> brain = entity.getBrain();
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
