package com.teamabode.verdance.common.block.entity;

import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/*
    It takes a Silk Moth 2400 ticks to emerge from a Silk Cocoon.
    The Silk Cocoon will begin to wobble and occasionally drop string at 1800 ticks.
 */
public class SilkCocoonBlockEntity extends BlockEntity {
    private int ticks = 0;

    public SilkCocoonBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(VerdanceBlockEntityTypes.SILK_COCOON, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SilkCocoonBlockEntity cocoon) {
        RandomSource random = level.getRandom();
        int ticks = cocoon.getTicks();

        if (ticks >= 2400) {
            // TODO: Spawn final String and Silk Moth
            level.destroyBlock(pos, false);
        }
        else if (ticks > 1800 && random.nextInt(25) == 0) {
            // TODO: Spawn String and play a unique sound
            // plans have also changed, make it wobble in here!
        }
        cocoon.setTicks(ticks + 1);
    }

    @Override
    protected void saveAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        compound.putInt("ticks", this.getTicks());
    }

    @Override
    protected void loadAdditional(CompoundTag compound, HolderLookup.Provider provider) {
        this.setTicks(compound.getInt("ticks"));
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.setChanged();
    }

    public int getTicks() {
        return this.ticks;
    }
}
