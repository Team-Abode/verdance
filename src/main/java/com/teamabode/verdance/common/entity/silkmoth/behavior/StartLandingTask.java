package com.teamabode.verdance.common.entity.silkmoth.behavior;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.common.util.ImprovedOneShot;
import com.teamabode.verdance.common.util.SilkUtils;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.Map;
import java.util.Optional;

// TODO: Not working properly.
public class StartLandingTask extends ImprovedOneShot<SilkMoth> {

    @Override
    public void requires(Map<MemoryModuleType<?>, MemoryStatus> requirements) {
        requirements.put(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT);
        requirements.put(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED);
        requirements.put(VerdanceMemoryModuleTypes.IS_FLYING, MemoryStatus.VALUE_PRESENT);
        requirements.put(VerdanceMemoryModuleTypes.WANTS_TO_LAND, MemoryStatus.VALUE_ABSENT);
    }

    @Override
    public void run(ServerLevel level, SilkMoth entity, long gameTime) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<BlockPos> landingTarget = SilkUtils.calculateLandingTarget(entity);

        if (landingTarget.isPresent()) {
            BehaviorUtils.setWalkAndLookTargetMemories(entity, landingTarget.get(), 1.0f, 0);
            brain.setMemory(VerdanceMemoryModuleTypes.WANTS_TO_LAND, Unit.INSTANCE);
        }
        Verdance.LOGGER.info("Started LandOnGround");
    }
}
