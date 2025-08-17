package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShrubBlock extends BushBlock implements BonemealableBlock {
    public static final MapCodec<ShrubBlock> CODEC = simpleCodec(ShrubBlock::new);
    public static final VoxelShape SHAPE = Block.box(0.0d, 0.0d, 0.0d, 16.0d, 14.0d, 16.0d);

    public ShrubBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(VerdanceBlockTags.SHRUB_MAY_PLACE_ON);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        Optional<Block> finalBlock = BuiltInRegistries.BLOCK.getRandomElementOf(VerdanceBlockTags.FLOWERING_SHRUBS, random).map(Holder::value);
        finalBlock.ifPresent(block -> level.setBlock(pos, block.defaultBlockState(), 2));
    }

    @Override
    protected MapCodec<? extends ShrubBlock> codec() {
        return CODEC;
    }
}
