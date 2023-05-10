package com.teamabode.verdance.common.block;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceSounds;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

@SuppressWarnings("deprecation")
public class AttachedCantaloupeStemBlock extends BushBlock {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = PipeBlock.PROPERTY_BY_DIRECTION.entrySet().stream().filter(entry -> entry.getKey().getAxis().isHorizontal()).collect(Util.toMap());
    private static final VoxelShape SHAPE = Block.box(4.0d, 0.0d, 4.0d, 12.0d, 10.0d, 12.0d);

    public AttachedCantaloupeStemBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST);
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(Blocks.FARMLAND);
    }

    public BlockState updateShape(BlockState blockState, Direction direction, BlockState neighborState, LevelAccessor levelAccessor, BlockPos blockPos, BlockPos neighborPos) {
        int fruitCount = 0;
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            if (levelAccessor.getBlockState(blockPos.relative(dir)).is(VerdanceBlocks.CANTALOUPE)) fruitCount++;
        }
        if (fruitCount == 0) {
            return VerdanceBlocks.CANTALOUPE_STEM.defaultBlockState().setValue(CantaloupeStemBlock.AGE, 7);
        }
        return direction.getAxis().getPlane() == Direction.Plane.HORIZONTAL && !neighborState.is(VerdanceBlocks.CANTALOUPE) ? blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), false) : super.updateShape(blockState, direction, neighborState, levelAccessor, blockPos, neighborPos);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockPos grownPos = blockPos.relative(direction);
        if (CantaloupeStemBlock.isValidPlacement(serverLevel, grownPos) && random.nextInt(10) == 0) {
            serverLevel.playSound(null, blockPos, VerdanceSounds.BLOCK_CANTALOUPE_GROW, SoundSource.BLOCKS,  1.0F, 1.0F);
            serverLevel.setBlockAndUpdate(grownPos, VerdanceBlocks.CANTALOUPE.defaultBlockState().setValue(CantaloupeBlock.FACING, direction.getOpposite()));
            serverLevel.setBlockAndUpdate(blockPos, blockState.setValue(PROPERTY_BY_DIRECTION.get(direction), true));
        }
    }

    public ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(VerdanceBlocks.CANTALOUPE_STEM.asItem());
    }
}
