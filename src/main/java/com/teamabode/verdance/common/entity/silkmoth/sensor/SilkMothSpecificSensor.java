package com.teamabode.verdance.common.entity.silkmoth.sensor;

import com.google.common.collect.ImmutableSet;
import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Unit;

import java.util.Optional;
import java.util.Set;

public class SilkMothSpecificSensor extends Sensor<SilkMothEntity> {

    @Override
    protected void sense(ServerWorld world, SilkMothEntity entity) {
        Brain<SilkMothEntity> brain = entity.getBrain();
        Optional<Unit> isFlying = Optional.empty();
        Optional<Unit> wantsToLand = Optional.empty();

        if (entity.isInAir()) {
            isFlying = Optional.of(Unit.INSTANCE);
        }
        Optional<Long> landingTime = brain.getOptionalRegisteredMemory(VerdanceMemoryModuleTypes.LANDING_TIME);

        if (landingTime.isPresent() && world.getTime() > landingTime.get()) {
            wantsToLand = Optional.of(Unit.INSTANCE);
        }
        brain.remember(VerdanceMemoryModuleTypes.IS_FLYING, isFlying);
        brain.remember(VerdanceMemoryModuleTypes.WANTS_TO_LAND, wantsToLand);
    }

    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return ImmutableSet.of(
                VerdanceMemoryModuleTypes.IS_FLYING,
                VerdanceMemoryModuleTypes.WANTS_TO_LAND,
                VerdanceMemoryModuleTypes.LANDING_TIME
        );
    }
}
