package com.teamabode.verdance.common.util;

import com.google.common.collect.Maps;
import java.util.Map;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.SingleTickTask;
import net.minecraft.server.world.ServerWorld;

public abstract class ImprovedSingleTickTask<E extends LivingEntity> extends SingleTickTask<E> {
    private final Map<MemoryModuleType<?>, MemoryModuleState> requiredMemories = Maps.newHashMap();

    public abstract void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements);

    public final boolean checkRequirements(Brain<?> brain) {
        this.requires(requiredMemories);

        for (var entry : this.requiredMemories.entrySet()) {
            MemoryModuleType<?> memoryModule = entry.getKey();
            MemoryModuleState memoryStatus = this.requiredMemories.get(memoryModule);

            if (!brain.isMemoryInState(memoryModule, memoryStatus)) {
                return false;
            }
        }
        return true;
    }

    public boolean canRun(ServerWorld level, E entity, long gameTime) {
        return true;
    }

    public final boolean trigger(ServerWorld level, E entity, long gameTime) {
        if (this.checkRequirements(entity.getBrain()) && this.canRun(level, entity, gameTime)) {
            this.run(level, entity, gameTime);
            return true;
        }
        return false;
    }

    public abstract void run(ServerWorld level, E entity, long gameTime);
}
