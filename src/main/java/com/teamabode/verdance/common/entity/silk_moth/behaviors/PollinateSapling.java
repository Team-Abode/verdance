package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
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

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.STAGE;

public class PollinateSapling {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.present(MemoryModuleType.WALK_TARGET),
                instance.absent(VerdanceMemories.POLLINATE_COOLDOWN),
                instance.registered(VerdanceMemories.SAPLINGS_POLLINATED)
        ).apply(instance, (walkTarget, cooldown, saplingsPollinated) -> PollinateSapling::tryStart));
    }

    private static boolean tryStart(ServerLevel level, SilkMoth entity, long gameTime) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            BlockPos pos = entity.blockPosition().relative(dir);
            BlockState state = level.getBlockState(pos);

            if (SilkMothAi.CAN_POLLINATE.test(state)) {
                level.levelEvent(1505, pos, 0);
                level.setBlockAndUpdate(pos, state.setValue(STAGE, 1));
                updateMemory(entity.getBrain());
                return true;
            }
        }
        return false;
    }

    private static void updateMemory(Brain<SilkMoth> brain) {
        Optional<Integer> saplingsPollinated = brain.getMemory(VerdanceMemories.SAPLINGS_POLLINATED);

        if (saplingsPollinated.isEmpty()) {
            brain.setMemory(VerdanceMemories.SAPLINGS_POLLINATED, 1);
            return;
        }
        if (saplingsPollinated.get() < 20) {
            brain.setMemory(VerdanceMemories.SAPLINGS_POLLINATED, saplingsPollinated.get() + 1);
        }
        if (saplingsPollinated.get() >= 20) {
            brain.setMemory(VerdanceMemories.POLLINATE_COOLDOWN, 6000);
        }
    }
}
