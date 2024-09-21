package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.common.util.SilkUtils;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import java.util.Optional;
import net.minecraft.entity.ai.brain.BlockPosLookTarget;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.WalkTarget;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class AerialStrollTask extends ImprovedSingleTickTask<SilkMoth> {

    public void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryModuleState.VALUE_PRESENT);
    }

    public void run(ServerWorld level, SilkMoth entity, long gameTime) {
        Optional<BlockPos> pos = SilkUtils.calculateStrollTarget(entity);
        pos.ifPresent(blockPos -> entity.getBrain().remember(MemoryModuleType.WALK_TARGET, new WalkTarget(new BlockPosLookTarget(blockPos), 1.0f, 0)));
    }
}
