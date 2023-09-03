package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

public class OccasionalTakeOff {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.absent(MemoryModuleType.WALK_TARGET),
                instance.absent(VerdanceMemories.OCCASIONAL_FLIGHT_COOLDOWN)
        ).apply(instance, (walkTarget, flightCooldown) -> OccasionalTakeOff::tryStart));
    }

    private static boolean tryStart(ServerLevel level, SilkMoth entity, long gameTime) {
        if (!entity.isFlying() && entity.onGround()) {
            entity.takeOff();
            return true;
        }
        return false;
    }
}
