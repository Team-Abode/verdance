package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.mutable.MutableLong;

public class SearchForSapling {
    private static final MutableLong LAST_EXECUTION = new MutableLong(0L);

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.absent(MemoryModuleType.WALK_TARGET),
                instance.absent(VerdanceMemories.IS_POLLINATING),
                instance.registered(MemoryModuleType.LOOK_TARGET)
        ).apply(instance, (walkTargetMemory, isPollinatingMemory, lookTargetMemory) -> SearchForSapling::tryStart));
    }

    private static boolean tryStart(ServerLevel level, SilkMoth entity, long gameTime) {
        if (gameTime < LAST_EXECUTION.getValue()) {
            LAST_EXECUTION.setValue(gameTime + 40L);
            return true;
        }
        MutableBlockPos mutablePos = new MutableBlockPos();

        for (BlockPos scanPos : BlockPos.withinManhattan(entity.blockPosition(), 15, 8, 15)) {
            BlockState state = level.getBlockState(mutablePos.set(scanPos));
            if (SilkMothAi.CANNOT_POLLINATE.test(state)) continue;

            BlockPosTracker tracker = new BlockPosTracker(mutablePos);
            entity.getBrain().setMemory(VerdanceMemories.IS_POLLINATING, Unit.INSTANCE);
            entity.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, tracker);
            entity.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(tracker, 1.0f, 0));
            return true;
        }
        return false;
    }
}
