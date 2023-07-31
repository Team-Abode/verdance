package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

import static com.teamabode.verdance.common.entity.silk_moth.SilkMothAi.CANNOT_POLLINATE;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.STAGE;

public class PollinateSapling {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.present(MemoryModuleType.WALK_TARGET),
                instance.present(VerdanceMemories.IS_POLLINATING),
                instance.registered(VerdanceMemories.SAPLINGS_POLLINATED)
        ).apply(instance, (walkTarget, isPollinating, saplingsPollinated) -> PollinateSapling::tryStart));
    }

    private static boolean tryStart(ServerLevel level, SilkMoth entity, long gameTime) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos pos = entity.blockPosition().relative(dir);
            BlockState state = level.getBlockState(pos);

            if (CANNOT_POLLINATE.test(state)) continue;

            level.levelEvent(1505, pos, 0);
            level.setBlockAndUpdate(pos, state.setValue(STAGE, 1));
            updateMemory(entity.getBrain());
            return true;
        }
        return false;
    }

    private static void updateMemory(Brain<SilkMoth> brain) {
        Optional<Integer> saplingsPollianted = brain.getMemory(VerdanceMemories.SAPLINGS_POLLINATED);

        if (saplingsPollianted.isEmpty()) {
            brain.setMemory(VerdanceMemories.SAPLINGS_POLLINATED, 1);
            return;
        }
        if (saplingsPollianted.get() >= 20) {
            brain.eraseMemory(VerdanceMemories.IS_POLLINATING);
        }
        brain.setMemory(VerdanceMemories.SAPLINGS_POLLINATED, saplingsPollianted.get() + 1);
    }
}
