package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin implements Fertilizable {

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        SugarCaneBlock $this = SugarCaneBlock.class.cast(this);

        BlockPos abovePos = pos.up();
        return world.getBlockState(abovePos).isAir() || getCaneHeight($this, world, pos) < 3;
    }

    @Override
    public boolean canGrow(World level, Random randomSource, BlockPos blockPos, BlockState blockState) {
        return randomSource.nextFloat() < 0.75F;
    }

    @Override
    public void grow(ServerWorld level, Random random, BlockPos blockPos, BlockState blockState) {
        SugarCaneBlock $this = SugarCaneBlock.class.cast(this);

        for (int i = 1; i < 3; i++) {
            BlockState aboveState = level.getBlockState(blockPos.up(i));
            if (aboveState.isIn(VerdanceBlockTags.REPLACEABLE_BY_SUGAR_CANE)) {
                level.setBlockState(blockPos.up(i), $this.getDefaultState(), 2);
                continue;
            }
            break;
        }
    }

    @Unique
    private static int getCaneHeight(Block block, BlockView level, BlockPos blockPos) {
        int height;
        for (height = 0; height < 5 && level.getBlockState(blockPos.up(height)).isOf(block); height++) {}
        return height;
    }
}
