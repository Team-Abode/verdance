package com.teamabode.verdance.common.entity.silk_moth.behaviors;

import com.google.common.collect.ImmutableMap;
import com.teamabode.verdance.common.entity.silk_moth.SilkMoth;
import com.teamabode.verdance.common.entity.silk_moth.SilkMothAi;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BlockPosTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Optional;

public class PollinateSapling extends Behavior<SilkMoth> {

    public PollinateSapling() {
        super(ImmutableMap.of(
                VerdanceMemories.POLLINATE_TARGET, MemoryStatus.VALUE_PRESENT,
                MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED,
                VerdanceMemories.IS_POLLINATING, MemoryStatus.REGISTERED
        ));
    }

    protected boolean canStillUse(ServerLevel level, SilkMoth entity, long gameTime) {
        return entity.getBrain().getMemory(VerdanceMemories.POLLINATE_TARGET).isPresent();
    }

    protected void start(ServerLevel level, SilkMoth entity, long gameTime) {
        Brain<SilkMoth> brain = entity.getBrain();
        if (brain.getMemory(VerdanceMemories.POLLINATE_TARGET).isEmpty()) return;
        this.moveToTarget(brain, brain.getMemory(VerdanceMemories.POLLINATE_TARGET).get().pos());
    }

    protected void tick(ServerLevel level, SilkMoth entity, long gameTime) {
        Brain<SilkMoth> brain = entity.getBrain();
        Optional<GlobalPos> pollinateTarget = brain.getMemory(VerdanceMemories.POLLINATE_TARGET);
        if (pollinateTarget.isEmpty()) return;

        if (brain.getMemory(VerdanceMemories.IS_POLLINATING).isPresent()) {
            BlockState state = level.getBlockState(pollinateTarget.get().pos());
            if (SilkMothAi.CANNOT_POLLINATE.test(state)) return;
            entity.getNavigation().stop();
            level.levelEvent(1505, pollinateTarget.get().pos(), 0);
            level.setBlock(pollinateTarget.get().pos(), state.setValue(BlockStateProperties.STAGE, 1), 3);
            brain.eraseMemory(VerdanceMemories.IS_POLLINATING);
            brain.eraseMemory(VerdanceMemories.POLLINATE_TARGET);
            return;
        }
        this.moveToTarget(brain, pollinateTarget.get().pos());
    }

    public void moveToTarget(Brain<SilkMoth> brain, BlockPos pos) {
        BlockPosTracker tracker = new BlockPosTracker(pos);
        brain.setMemory(MemoryModuleType.LOOK_TARGET, tracker);
        brain.setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(tracker, 0.75f, 0));
    }

    protected void stop(ServerLevel level, SilkMoth entity, long gameTime) {
        entity.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
        entity.getBrain().eraseMemory(MemoryModuleType.LOOK_TARGET);
    }
}
