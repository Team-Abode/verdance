package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

public class ForceTakeOff {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.present(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE),
                instance.registered(VerdanceMemories.FLIGHT_COOLDOWN_TICKS)
        ).apply(instance, (walkTarget, flightCooldown) -> ForceTakeOff::tryStart));
    }

    private static boolean tryStart(ServerLevel level, SilkMoth entity, long gameTime) {
        if (entity.onGround() && !entity.isFlying()) {
            entity.getBrain().eraseMemory(VerdanceMemories.FLIGHT_COOLDOWN_TICKS);
            entity.takeOff();
            return true;
        }
        return false;
    }
}
