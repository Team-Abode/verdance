package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.common.util.ImprovedSingleTickTask;
import com.teamabode.verdance.common.util.SilkUtils;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import java.util.Map;
import java.util.Optional;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.LookTargetUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class GoTowardsLandingTask extends ImprovedSingleTickTask<SilkMothEntity> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryModuleState> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryModuleState.VALUE_ABSENT);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryModuleState.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_LAND, MemoryModuleState.VALUE_PRESENT);
        requirements.put(MemoryModuleType.IS_PREGNANT, MemoryModuleState.VALUE_ABSENT);
        requirements.put(MemoryModuleType.IS_PANICKING, MemoryModuleState.VALUE_ABSENT);
    }

    @Override
    public void run(ServerWorld level, SilkMothEntity entity, long gameTime) {
        Optional<BlockPos> landingTarget = SilkUtils.calculateLandingTarget(entity);

        if (landingTarget.isPresent()) {
            LookTargetUtil.walkTowards(entity, landingTarget.get(), 1.0f, 0);
            return;
        }
        Optional<BlockPos> strollTarget = SilkUtils.calculateStrollTarget(entity);
        strollTarget.ifPresent(pos -> LookTargetUtil.walkTowards(entity, strollTarget.get(), 1.0f, 0));
    }
}
