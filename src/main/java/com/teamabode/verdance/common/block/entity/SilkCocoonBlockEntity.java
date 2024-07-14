package com.teamabode.verdance.common.block.entity;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

/*
    It takes a Silk Moth 4800 ticks to emerge from a Silk Cocoon.
    The Silk Cocoon will begin to wobble and occasionally drop string at 3600 ticks.
    While wobbling, the Silk Cocoon has a 1/4th chance to drop 1-2 string, but will drop 5-6 string on break or on emerge.
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
        if (ticks >= 4800) {
            SilkMoth silkMoth = new SilkMoth(VerdanceEntityTypes.SILK_MOTH, level);
            silkMoth.setPos(pos.getCenter());
            silkMoth.setYHeadRot(state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());
            silkMoth.setYRot(state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot());
            silkMoth.takeOff();

            level.addFreshEntity(silkMoth);
            level.destroyBlock(pos, true);
        }
        else if (ticks >= 3600 && ticks % 200 == 0) {
            cocoon.wobble(level);
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

    public void wobble(Level level) {
        if (this.wobbling) {
            this.wobbleTicks = 0;
        }
        else {
            this.wobbling = true;
        }
        RandomSource random = level.getRandom();
        if (random.nextInt(3) == 0) {
            this.dropString(level, random, this.getBlockPos());
        }
        // TODO: unique sound
    }

    public void dropString(Level level, RandomSource random, BlockPos origin) {
        for (int i = 0; i < Mth.randomBetweenInclusive(random, 1, 2); i++) {
            BlockState state = level.getBlockState(origin);
            Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);


            ItemEntity itemEntity = new ItemEntity(
                    level,
                    origin.getX() + 0.5d,
                    origin.getY() - 0.5d,
                    origin.getZ() + 0.5d,
                    Items.STRING.getDefaultInstance()
            );

            level.addFreshEntity(itemEntity);
        }
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.setChanged();
    }

    public int getTicks() {
        return this.ticks;
    }
}
