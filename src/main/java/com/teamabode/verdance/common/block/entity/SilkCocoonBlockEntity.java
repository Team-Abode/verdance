package com.teamabode.verdance.common.block.entity;

import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SilkCocoonBlockEntity extends BlockEntity {
    private int wobbleTicks;

    public SilkCocoonBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(VerdanceBlockEntityTypes.SILK_COCOON, blockPos, blockState);
        this.wobbleTicks = 0;
    }

    public int getWobbleTicks() {
        return this.wobbleTicks;
    }

    public void setWobbleTicks(int ticks) {
        this.wobbleTicks = ticks;
    }

    public void decrementWobbleTicks() {
        this.wobbleTicks = Math.max(0, wobbleTicks - 1);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SilkCocoonBlockEntity cocoon) {
        if (cocoon.getWobbleTicks() > 0) {
            cocoon.decrementWobbleTicks();
        }
    }
}
