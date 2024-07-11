package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.google.common.collect.ImmutableMap;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class TakeOff extends Behavior<SilkMoth> {

    public TakeOff() {
        super(ImmutableMap.of(
                MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.VALUE_PRESENT,
                VerdanceMemoryModuleTypes.IS_FLYING, MemoryStatus.VALUE_ABSENT
        ));
    }

    protected boolean checkExtraStartConditions(ServerLevel level, SilkMoth entity) {
        return entity.onGround();
    }

    protected void start(ServerLevel level, SilkMoth entity, long gameTime) {
        entity.setFlying(true);
        entity.getBrain().setMemory(VerdanceMemoryModuleTypes.IS_FLYING, Unit.INSTANCE);
    }
}
