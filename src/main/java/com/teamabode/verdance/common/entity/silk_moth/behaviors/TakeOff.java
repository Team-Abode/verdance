package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

public class TakeOff {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.absent(MemoryModuleType.WALK_TARGET),
                instance.absent(VerdanceMemories.FLIGHT_COOLDOWN_TICKS)
        ).apply(instance, (walkTargetMemory, flightCooldownMemory) -> TakeOff::attemptStart));
    }

    private static boolean attemptStart(ServerLevel level, SilkMoth entity, long gameTime) {
        if (entity.onGround() && !entity.isFlying()) {
            entity.takeOff();
            return true;
        }
        return false;
    }
}
