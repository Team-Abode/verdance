package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.google.common.collect.ImmutableMap;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class TakeOff extends Behavior<SilkMoth> {

    public TakeOff() {
        super(ImmutableMap.of(
                MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                VerdanceMemoryModuleType.FLIGHT_COOLDOWN_TICKS, MemoryStatus.VALUE_ABSENT
        ));
    }

    protected boolean checkExtraStartConditions(ServerLevel level, SilkMoth owner) {
        return owner.onGround() && !owner.isFlying();
    }

    protected void start(ServerLevel level, SilkMoth entity, long gameTime) {
        entity.takeOff();
    }
}
