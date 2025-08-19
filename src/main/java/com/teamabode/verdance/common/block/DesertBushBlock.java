package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import java.util.Optional;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class DesertBushBlock extends PlantBlock implements Fertilizable {
    public static final MapCodec<DesertBushBlock> CODEC = createCodec(DesertBushBlock::new);
    public static final VoxelShape SHAPE = Block.createCuboidShape(0.0d, 0.0d, 0.0d, 16.0d, 14.0d, 16.0d);

    public DesertBushBlock(Settings properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState blockState, BlockView blockGetter, BlockPos blockPos, ShapeContext collisionContext) {
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState state, BlockView level, BlockPos pos) {
        return state.isIn(VerdanceBlockTags.SHRUB_MAY_PLACE_ON);
    }

    @Override
    public boolean isFertilizable(WorldView level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean canGrow(World level, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld level, Random random, BlockPos pos, BlockState state) {
        Optional<Block> finalBlock = Registries.BLOCK.getRandomEntry(VerdanceBlockTags.FLOWERING_DESERT_BUSHES, random).map(RegistryEntry::value);
        finalBlock.ifPresent(block -> level.setBlockState(pos, block.getDefaultState(), 2));
    }

    @Override
    protected MapCodec<? extends DesertBushBlock> getCodec() {
        return CODEC;
    }
}
