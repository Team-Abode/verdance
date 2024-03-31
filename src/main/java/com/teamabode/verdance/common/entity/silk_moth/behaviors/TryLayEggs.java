package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class TryLayEggs {

    public static BehaviorControl<SilkMoth> create() {
        return BehaviorBuilder.create(instance -> instance.group(
                instance.present(MemoryModuleType.IS_PREGNANT)
        ).apply(instance, (isPregnantMemory) -> TryLayEggs::tryStart));
    }

    private static boolean tryStart(ServerLevel level, SilkMoth entity, long gameTime) {
        BlockPos entityPos = entity.blockPosition();

        BlockPos relativePos = entityPos.below();
        BlockPos eggPos = relativePos.above();

        boolean isEmpty = level.getBlockState(eggPos).isAir();
        boolean isLeaves = level.getBlockState(relativePos).is(BlockTags.LEAVES);

        if (isEmpty && isLeaves) {
            BlockState eggState = VerdanceBlocks.SILKWORM_EGGS.defaultBlockState();

            level.setBlock(eggPos, eggState, 3);
            level.playSound(null, eggPos, eggState.getSoundType().getPlaceSound(), SoundSource.BLOCKS, 1.0f, 1.0f);
            level.gameEvent(GameEvent.BLOCK_PLACE, eggPos, GameEvent.Context.of(entity, eggState));
            entity.getBrain().eraseMemory(MemoryModuleType.IS_PREGNANT);
            return true;
        }
        return false;
    }
}
