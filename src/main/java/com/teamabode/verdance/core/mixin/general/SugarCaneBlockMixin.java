package com.teamabode.verdance.core.mixin.general;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin implements BonemealableBlock {
    SugarCaneBlock sugarCaneBlock = SugarCaneBlock.class.cast(this);

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, boolean isClient) {
        return level.getBlockState(pos.above()).isAir() || this.getCaneHeight(level, pos) < 3;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return randomSource.nextFloat() < 0.75F;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos blockPos, BlockState blockState) {
        for (int i = 1; i < 3; i++) {
            if (level.getBlockState(blockPos.above(i)).getMaterial().isReplaceable()) {
                level.setBlock(blockPos.above(i), sugarCaneBlock.defaultBlockState(), 2);
                continue;
            }
            break;
        }
    }

    private int getCaneHeight(BlockGetter level, BlockPos blockPos) {
        int height;
        for (height = 0; height < 5 && level.getBlockState(blockPos.above(height)).is(sugarCaneBlock); height++) {}
        return height;
    }
}
