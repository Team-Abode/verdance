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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class GrowSapling {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.present(VerdanceMemories.IS_POLLINATING)
        ).apply(instance, (unitMemory) -> GrowSapling::tryStart));
    }

    private static boolean tryStart(ServerLevel level, SilkMoth entity, long gameTime) {
        Brain<SilkMoth> brain = entity.getBrain();
        brain.eraseMemory(VerdanceMemories.IS_POLLINATING);
        for (Direction dir : Direction.values()) {
            BlockPos pos = entity.blockPosition().relative(dir);
            BlockState state = level.getBlockState(pos);
            if (SilkMothAi.CANNOT_POLLINATE.test(state)) continue;

            level.levelEvent(1505, pos, 0);
            level.setBlock(pos, state.setValue(BlockStateProperties.STAGE, 1), 3);
            return true;
        }
        return false;
    }
}
