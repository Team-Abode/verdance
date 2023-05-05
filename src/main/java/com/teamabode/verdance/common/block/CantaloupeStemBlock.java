package com.teamabode.verdance.common.block;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class CantaloupeStemBlock extends BushBlock implements BonemealableBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_7;
    private static final VoxelShape[] SHAPE_BY_AGE = {
            Block.box(7.0, 0.0, 7.0, 9.0, 2.0, 9.0),
            Block.box(7.0, 0.0, 7.0, 9.0, 4.0, 9.0),
            Block.box(7.0, 0.0, 7.0, 9.0, 6.0, 9.0),
            Block.box(7.0, 0.0, 7.0, 9.0, 8.0, 9.0),
            Block.box(7.0, 0.0, 7.0, 9.0, 10.0, 9.0),
            Block.box(7.0, 0.0, 7.0, 9.0, 12.0, 9.0),
            Block.box(7.0, 0.0, 7.0, 9.0, 14.0, 9.0),
            Block.box(7.0, 0.0, 7.0, 9.0, 16.0, 9.0)
    };

    public CantaloupeStemBlock(Properties properties) {
        super(properties);
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        int age = blockState.getValue(AGE);
        if (serverLevel.getRawBrightness(blockPos, 0) < 9) return;
        if (randomSource.nextInt((age + 1) * 2) != 0) return;

        if (age < 7) {
            blockState = blockState.setValue(AGE, blockState.getValue(AGE) + 1);
            serverLevel.setBlockAndUpdate(blockPos, blockState);
        }
        else {
            growCantaloupe(serverLevel, blockPos, randomSource);
        }
    }

    public static void growCantaloupe(ServerLevel level, BlockPos blockPos, RandomSource random) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos grownPos = blockPos.relative(direction);
        if (isValidPlacement(level, grownPos)) {
            level.playSound(null, blockPos, VerdanceSounds.BLOCK_CANTALOUPE_GROW, SoundSource.BLOCKS,  1.0F, 1.0F);
            level.setBlockAndUpdate(grownPos, VerdanceBlocks.CANTALOUPE.defaultBlockState().setValue(CantaloupeBlock.FACING, direction.getOpposite()));
            level.setBlockAndUpdate(blockPos, VerdanceBlocks.ATTACHED_CANTALOUPE_STEM.defaultBlockState().setValue(AttachedCantaloupeStemBlock.PROPERTY_BY_DIRECTION.get(direction), true));
        }
    }

    public static boolean isValidPlacement(ServerLevel serverLevel, BlockPos blockPos) {
        return serverLevel.getBlockState(blockPos).isAir() && (serverLevel.getBlockState(blockPos.below()).is(BlockTags.DIRT) || serverLevel.getBlockState(blockPos.below()).is(BlockTags.SAND) || serverLevel.getBlockState(blockPos.below()).is(Blocks.FARMLAND));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(Blocks.FARMLAND);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE_BY_AGE[blockState.getValue(AGE)];
    }

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return VerdanceItems.CANTALOUPE_SEEDS.getDefaultInstance();
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState blockState, boolean isClientSide) {
        return blockState.getValue(AGE) != 7;
    }
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        int i = Math.min(7, blockState.getValue(AGE) + Mth.nextInt(serverLevel.random, 1, 2));
        BlockState finalState = blockState.setValue(AGE, i);
        serverLevel.setBlock(blockPos, finalState, 2);
        if (i == 7) {
            finalState.randomTick(serverLevel, blockPos, serverLevel.random);
        }
    }
}
