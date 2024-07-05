package com.teamabode.verdance.common.block.entity;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/*
    It takes a Silk Moth 2400 ticks to emerge from a Silk Cocoon.
    The Silk Cocoon will begin to wobble and occasionally drop string at 1800 ticks.
 */
public class SilkCocoonBlockEntity extends BlockEntity {
    private int ticks = 0;
    public int wobbleTicks = 0;
    public boolean wobbling = false;

    public SilkCocoonBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(VerdanceBlockEntityTypes.SILK_COCOON, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SilkCocoonBlockEntity cocoon) {
        int ticks = cocoon.getTicks();

        // Handles wobbling
        if (cocoon.wobbling) {
            cocoon.wobbleTicks++;
        }
        if (cocoon.wobbleTicks >= 10) {
            cocoon.wobbling = false;
            cocoon.wobbleTicks = 0;
        }

        if (ticks >= 2400) {
            SilkMoth silkMoth = new SilkMoth(VerdanceEntityTypes.SILK_MOTH, level);
            silkMoth.setPos(pos.getCenter());
            silkMoth.setFlying(true);
            silkMoth.getBrain().setMemory(VerdanceMemoryModuleTypes.IS_FLYING, Unit.INSTANCE);

            level.addFreshEntity(silkMoth);
            level.destroyBlock(pos, false);
        }
        else if (ticks % 200 == 0) {
            cocoon.wobble();
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

    public void wobble() {
        if (this.wobbling) {
            this.wobbleTicks = 0;
        }
        else {
            this.wobbling = true;
        }
        // TODO: Spawn String and play a unique sound
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.setChanged();
    }

    public int getTicks() {
        return this.ticks;
    }
}
