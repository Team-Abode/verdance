package com.teamabode.verdance.common.entity.silkmoth.sensor;

import com.google.common.collect.ImmutableSet;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.level.LightLayer;

import java.util.Optional;
import java.util.Set;

public class SilkMothSpecificSensor extends Sensor<SilkMoth> {

    @Override
    protected void doTick(ServerLevel level, SilkMoth entity) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<Unit> isFlying = Optional.empty();
        Optional<Unit> wantsToLand = Optional.empty();
        Optional<BlockPos> nearestLightSource = Optional.empty();

        if (entity.isFlying()) {
            isFlying = Optional.of(Unit.INSTANCE);
        }
        Optional<Long> landingTime = brain.getMemory(VerdanceMemoryModuleTypes.LANDING_TIME.get());

        if (this.canSearchForLightSource(level, entity.blockPosition())) {
            nearestLightSource = BlockPos.findClosestMatch(
                    entity.blockPosition(),
                    12,
                    10,
                    pos -> level.getBlockState(pos).getLightEmission(level, pos) > 0
            );
        }

        if (landingTime.isPresent() && level.getGameTime() > landingTime.get()) {
            wantsToLand = Optional.of(Unit.INSTANCE);
        }

        brain.setMemory(VerdanceMemoryModuleTypes.IS_FLYING.get(), isFlying);
        brain.setMemory(VerdanceMemoryModuleTypes.WANTS_TO_LAND.get(), wantsToLand);
        brain.setMemory(VerdanceMemoryModuleTypes.NEAREST_LIGHT_SOURCE.get(), nearestLightSource);
    }

    private boolean canSearchForLightSource(ServerLevel level, BlockPos pos) {
        if (level.getBrightness(LightLayer.SKY, pos) == 0) {
            return true;
        }
        return level.isNight() && level.getBrightness(LightLayer.BLOCK, pos) == 0;
    }

    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.of(
                VerdanceMemoryModuleTypes.IS_FLYING.get(),
                VerdanceMemoryModuleTypes.WANTS_TO_LAND.get(),
                VerdanceMemoryModuleTypes.LANDING_TIME.get(),
                VerdanceMemoryModuleTypes.NEAREST_LIGHT_SOURCE.get()
        );
    }
}
