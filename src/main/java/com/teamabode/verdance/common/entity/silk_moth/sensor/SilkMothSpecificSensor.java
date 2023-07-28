package com.teamabode.verdance.common.entity.silk_moth.sensor;

import com.google.common.collect.ImmutableSet;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.Set;

public class SilkMothSpecificSensor extends Sensor<SilkMoth> {
    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.of(
                VerdanceMemories.IS_POLLINATING,
                VerdanceMemories.POLLINATE_TARGET
        );
    }

    protected void doTick(ServerLevel level, SilkMoth entity) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<GlobalPos> pollinateTarget = brain.getMemory(VerdanceMemories.POLLINATE_TARGET);
        Optional<Unit> isPollinating = Optional.empty();

        if (pollinateTarget.isPresent()) {
            BlockState state = level.getBlockState(pollinateTarget.get().pos());
            BlockPos entityPos = entity.blockPosition();
            double distance = entityPos.distManhattan(pollinateTarget.get().pos());

            if (entity.level().dimension() == pollinateTarget.get().dimension() && distance < 3) {
                isPollinating = Optional.of(Unit.INSTANCE);
            }
            if (SilkMothAi.CANNOT_POLLINATE.test(state) || distance > 25.0 || entity.level().dimension() != pollinateTarget.get().dimension()) {
                brain.eraseMemory(VerdanceMemories.POLLINATE_TARGET);
            }
        }
        brain.setMemory(VerdanceMemories.IS_POLLINATING, isPollinating);
    }
}
