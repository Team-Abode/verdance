package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.common.block.entity.SilkCocoonBlockEntity;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class SilkCocoonBlock extends BlockWithEntity {
    public static final MapCodec<SilkCocoonBlock> CODEC = createCodec(SilkCocoonBlock::new);
    public static final Map<Direction, VoxelShape> SHAPE_BY_DIR = Map.of(
            Direction.NORTH, Block.createCuboidShape(3.0d, 0.0d, 0.0d, 13.0d, 12.0d, 10.0d),
            Direction.EAST, Block.createCuboidShape(6.0d, 0.0d, 3.0d, 16.0d, 12.0d, 13.0d),
            Direction.SOUTH, Block.createCuboidShape(3.0d,0.0d, 6.0d, 13.0d, 12.0d, 16.0d),
            Direction.WEST, Block.createCuboidShape(0.0d, 0.0d, 3.0d, 10.0d, 12.0d, 13.0d)
    );
    public static final EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;

    public SilkCocoonBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<SilkCocoonBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView blockGetter, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);
        return SHAPE_BY_DIR.get(facing);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        BlockState state = this.getDefaultState();
        Direction[] directions = context.getPlacementDirections();

        for (var dir : directions) {
            if (dir.getAxis().isHorizontal()) {
                state = state.with(FACING, dir);
                if (state.canPlaceAt(context.getWorld(), context.getBlockPos())) {
                    return state;
                }
            }
        }
        return null;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        return direction == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView level, BlockPos pos) {
        Direction dir = state.get(FACING);
        BlockState relativeState = level.getBlockState(pos.offset(dir));

        return relativeState.isSideSolidFullSquare(level, pos, dir.getOpposite());
    }

    @Override
    protected BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SilkCocoonBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return validateTicker(blockEntityType, VerdanceBlockEntityTypes.SILK_COCOON, SilkCocoonBlockEntity::tick);
    }
}
