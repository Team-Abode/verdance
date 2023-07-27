package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.apache.commons.lang3.mutable.MutableLong;

public class TryFindLeaves {
    private static final MutableLong LAST_EXECUTION = new MutableLong(0L);

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.absent(MemoryModuleType.WALK_TARGET),
                instance.registered(MemoryModuleType.LOOK_TARGET)
        ).apply(instance, (walkTargetMemory, lookTargetMemory) -> TryFindLeaves::attemptStart));
    }

    private static boolean attemptStart(ServerLevel level, SilkMoth entity, long gameTime) {
        if (gameTime < LAST_EXECUTION.getValue()) {
            LAST_EXECUTION.setValue(gameTime + 40L);
            return true;
        }
        BlockPos entityPos = entity.blockPosition();
        MutableBlockPos mutablePos = new MutableBlockPos();

        for (BlockPos scanPos : BlockPos.withinManhattan(entityPos, 12, 12, 12)) {
            boolean excludeCurrentPos = entityPos.getX() != scanPos.getX() || entityPos.getX() != scanPos.getZ();
            boolean foundLeaves = level.getBlockState(mutablePos.set(scanPos)).is(BlockTags.LEAVES);

            if (excludeCurrentPos && foundLeaves) {
                Verdance.LOGGER.info("Successfully found leaves");
                BlockPosTracker tracker = new BlockPosTracker(mutablePos);

                entity.getBrain().setMemory(MemoryModuleType.LOOK_TARGET, tracker);
                entity.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(tracker, 1.0f, 0));
                return true;
            }
        }
        LAST_EXECUTION.setValue(gameTime + 40L);
        return true;
    }
}
