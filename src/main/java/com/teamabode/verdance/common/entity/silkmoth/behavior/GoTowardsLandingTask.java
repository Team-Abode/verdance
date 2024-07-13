package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedOneShot;
import com.teamabode.verdance.common.util.SilkUtils;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Map;
import java.util.Optional;

public class GoTowardsLandingTask extends ImprovedOneShot<SilkMoth> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryStatus.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_LAND, MemoryStatus.VALUE_PRESENT);
        requirements.put(MemoryModuleType.IS_PREGNANT, MemoryStatus.VALUE_ABSENT);
        requirements.put(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT);
    }

    @Override
    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        Optional<BlockPos> landingTarget = SilkUtils.calculateLandingTarget(entity);

        if (landingTarget.isPresent()) {
            BehaviorUtils.setWalkAndLookTargetMemories(entity, landingTarget.get(), 1.0f, 0);
            return;
        }
        Optional<BlockPos> strollTarget = SilkUtils.calculateStrollTarget(entity);
        strollTarget.ifPresent(pos -> BehaviorUtils.setWalkAndLookTargetMemories(entity, strollTarget.get(), 1.0f, 0));
    }
}
