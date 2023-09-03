package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.CountDownCooldownTicks;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

public class CountDownPollinateCooldown extends CountDownCooldownTicks {
    public CountDownPollinateCooldown() {
        super(VerdanceMemories.POLLINATE_COOLDOWN);
    }

    protected boolean checkExtraStartConditions(ServerLevel level, LivingEntity owner) {
        return owner.getBrain().checkMemory(VerdanceMemories.SAPLINGS_POLLINATED, MemoryStatus.REGISTERED);
    }

    protected void stop(ServerLevel level, LivingEntity entity, long gameTime) {
        entity.getBrain().eraseMemory(VerdanceMemories.SAPLINGS_POLLINATED);
        super.stop(level, entity, gameTime);
    }
}
