package com.teamabode.verdance.common.block.entity;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

/*
    It takes a Silk Moth 4800 ticks to emerge from a Silk Cocoon.
    The Silk Cocoon will begin to wobble and occasionally drop string at 3600 ticks.
    While wobbling, the Silk Cocoon has a 33% chance to drop 1-2 string, but will drop 5-6 string on break or on emerge.
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

            level.playSound(null, pos, VerdanceSoundEvents.ENTITY_SILK_MOTH_EMERGE, SoundSource.NEUTRAL);
            level.addFreshEntity(silkMoth);
            level.destroyBlock(pos, true);
        }
        else if (ticks >= 4400 && ticks % 100 == 0 || ticks >= 3600 && ticks % 200 == 0) {
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
        BlockPos pos = this.getBlockPos();
        RandomSource random = level.getRandom();
        if (random.nextInt(2) == 0) {
            this.dropString(level, random, pos);
        }
        level.playSound(null, pos, VerdanceSoundEvents.BLOCK_SILK_COCOON_WOBBLE, SoundSource.BLOCKS);
    }

    public void dropString(Level level, RandomSource random, BlockPos origin) {
        int count = Mth.randomBetweenInclusive(random, 1, 2);
        ItemEntity itemEntity = new ItemEntity(
                level,
                origin.getX() + 0.5d,
                origin.getY() - 0.5d,
                origin.getZ() + 0.5d,
                new ItemStack(Items.STRING, count)
        );
        level.addFreshEntity(itemEntity);
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.setChanged();
    }

    public int getTicks() {
        return this.ticks;
    }
}
