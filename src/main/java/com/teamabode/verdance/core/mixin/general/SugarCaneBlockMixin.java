package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.misc.tag.VerdanceBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin implements BonemealableBlock {
    SugarCaneBlock sugarCaneBlock = SugarCaneBlock.class.cast(this);

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        BlockPos abovePos = pos.above();
        return level.getBlockState(abovePos).isAir() || this.getCaneHeight(level, pos) < 3;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return randomSource.nextFloat() < 0.75F;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos blockPos, BlockState blockState) {
        for (int i = 1; i < 3; i++) {
            BlockState aboveState = level.getBlockState(blockPos.above(i));
            if (aboveState.is(VerdanceBlockTags.REPLACEABLE_BY_SUGAR_CANE)) {
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
