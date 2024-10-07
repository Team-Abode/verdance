package com.teamabode.verdance.common.entity.silkmoth.task;

import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.TaskTriggerer;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

public class LayEggsTask {

    public static Task<SilkMothEntity> create() {
        return TaskTriggerer.task(instance -> instance.group(
                instance.queryMemoryValue(MemoryModuleType.IS_PREGNANT)
        ).apply(instance, (isPregnantMemory) -> LayEggsTask::tryStart));
    }

    private static boolean tryStart(ServerWorld level, SilkMothEntity entity, long gameTime) {
        BlockPos entityPos = entity.getBlockPos();

        BlockPos relativePos = entityPos.down();
        BlockPos eggPos = relativePos.up();

        boolean isEmpty = level.getBlockState(eggPos).isAir();
        boolean isLeaves = level.getBlockState(relativePos).isIn(BlockTags.LEAVES);

        if (isEmpty && isLeaves) {
            BlockState eggState = VerdanceBlocks.SILKWORM_EGGS.getDefaultState();

            level.setBlockState(eggPos, eggState, 3);
            level.playSound(null, eggPos, eggState.getSoundGroup().getPlaceSound(), SoundCategory.BLOCKS, 1.0f, 1.0f);
            level.emitGameEvent(GameEvent.BLOCK_PLACE, eggPos, GameEvent.Emitter.of(entity, eggState));
            entity.getBrain().forget(MemoryModuleType.IS_PREGNANT);
            return true;
        }
        return false;
    }
}
