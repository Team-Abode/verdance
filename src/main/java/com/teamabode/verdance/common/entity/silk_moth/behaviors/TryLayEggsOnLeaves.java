package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

public class TryLayEggsOnLeaves {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.present(MemoryModuleType.WALK_TARGET),
                instance.present(MemoryModuleType.IS_PREGNANT)
        ).apply(instance, (walkTargetMemory, isPregnantMemory) -> TryLayEggsOnLeaves::attemptStart));
    }

    private static boolean attemptStart(ServerLevel level, SilkMoth entity, long gameTime) {
        BlockPos entityPos = entity.blockPosition();

        for (Direction dir : Direction.values()) {
            BlockPos relativePos = entityPos.relative(dir);
            BlockPos eggPos = relativePos.relative(dir.getOpposite());

            boolean isEmpty = level.getBlockState(eggPos).isAir();
            boolean isLeaves = level.getBlockState(relativePos).is(BlockTags.LEAVES);

            if (isEmpty && isLeaves) {
                Verdance.LOGGER.info("Successfully laid eggs");
                BlockState eggState = Blocks.LIGHTNING_ROD.defaultBlockState().setValue(BlockStateProperties.FACING, dir.getOpposite()); // placeholder

                level.setBlock(eggPos, eggState, 3);
                level.gameEvent(GameEvent.BLOCK_PLACE, eggPos, GameEvent.Context.of(entity, eggState));
                entity.getBrain().eraseMemory(MemoryModuleType.IS_PREGNANT);
                return true;
            }
        }
        return true;
    }
}
