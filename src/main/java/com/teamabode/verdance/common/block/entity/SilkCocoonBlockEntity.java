package com.teamabode.verdance.common.block.entity;

import com.teamabode.verdance.common.entity.silkmoth.SilkMothEntity;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

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

    public static void tick(World level, BlockPos pos, BlockState state, SilkCocoonBlockEntity cocoon) {
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
            SilkMothEntity silkMoth = new SilkMothEntity(VerdanceEntityTypes.SILK_MOTH, level);
            silkMoth.setPosition(pos.toCenterPos());
            silkMoth.setHeadYaw(state.get(Properties.HORIZONTAL_FACING).asRotation());
            silkMoth.setYaw(state.get(Properties.HORIZONTAL_FACING).asRotation());
            silkMoth.takeOff();

            level.playSound(null, pos, VerdanceSoundEvents.ENTITY_SILK_MOTH_EMERGE, SoundCategory.NEUTRAL);
            level.spawnEntity(silkMoth);
            level.breakBlock(pos, true);
        }
        else if (ticks >= 4400 && ticks % 100 == 0 || ticks >= 3600 && ticks % 200 == 0) {
            cocoon.wobble(level);
        }
        cocoon.setTicks(ticks + 1);
    }

    @Override
    protected void writeNbt(NbtCompound compound, RegistryWrapper.WrapperLookup provider) {
        compound.putInt("ticks", this.getTicks());
    }

    @Override
    protected void readNbt(NbtCompound compound, RegistryWrapper.WrapperLookup provider) {
        this.setTicks(compound.getInt("ticks"));
    }

    public void wobble(World level) {
        if (this.wobbling) {
            this.wobbleTicks = 0;
        }
        else {
            this.wobbling = true;
        }
        BlockPos pos = this.getPos();
        Random random = level.getRandom();
        if (random.nextInt(2) == 0) {
            this.dropString(level, random, pos);
        }
        level.playSound(null, pos, VerdanceSoundEvents.BLOCK_SILK_COCOON_WOBBLE, SoundCategory.BLOCKS);
    }

    public void dropString(World level, Random random, BlockPos origin) {
        int count = MathHelper.nextBetween(random, 1, 2);
        ItemEntity itemEntity = new ItemEntity(
                level,
                origin.getX() + 0.5d,
                origin.getY() - 0.5d,
                origin.getZ() + 0.5d,
                new ItemStack(Items.STRING, count)
        );
        level.spawnEntity(itemEntity);
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
        this.markDirty();
    }

    public int getTicks() {
        return this.ticks;
    }
}
