package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.google.common.collect.ImmutableMap;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

public class SearchForSapling extends Behavior<SilkMoth> {

    public SearchForSapling() {
        super(ImmutableMap.of(
                MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED
        ));
    }

    protected void start(ServerLevel level, SilkMoth entity, long gameTime) {
        BlockPos pos = this.targetPosition(level, entity.blockPosition(), entity.getRandom());

        Verdance.LOGGER.info("Target: " + pos);

        if (pos != null) {
            entity.takeOff();
            BehaviorUtils.setWalkAndLookTargetMemories(entity, pos, 1.25f, 1);
        }
    }

    private BlockPos targetPosition(ServerLevel level, BlockPos origin, RandomSource random) {
        ArrayList<BlockPos> targets = new ArrayList<>();

        for (int x = -8; x <= 8; x++) {
            for (int y = 0; y <= 6; y++) {
                for (int z = -8; z <= 8; z++) {
                    BlockPos pos = origin.offset(x, y, z);
                    BlockState state = level.getBlockState(pos);
                    if (SilkMothAi.CAN_POLLINATE.test(state)) {
                        targets.add(pos);
                    }
                }
            }
        }
        if (targets.isEmpty()) return null;
        return targets.get(random.nextInt(targets.size()));
    }
}
