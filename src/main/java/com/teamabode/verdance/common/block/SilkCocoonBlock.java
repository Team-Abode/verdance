package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.common.block.entity.SilkCocoonBlockEntity;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SilkCocoonBlock extends BaseEntityBlock {
    public static final MapCodec<SilkCocoonBlock> CODEC = simpleCodec(SilkCocoonBlock::new);
    public static final Map<Direction, VoxelShape> SHAPE_BY_DIR = Map.of(
            Direction.NORTH, Block.box(3.0d, 0.0d, 0.0d, 13.0d, 14.0d, 10.0d),
            Direction.EAST, Block.box(6.0d, 0.0d, 3.0d, 16.0d, 14.0d, 13.0d),
            Direction.SOUTH, Block.box(3.0d,0.0d, 6.0d, 13.0d, 14.0d, 16.0d),
            Direction.WEST, Block.box(0.0d, 0.0d, 3.0d, 10.0d, 14.0d, 13.0d)
    );
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public SilkCocoonBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected MapCodec<SilkCocoonBlock> codec() {
        return CODEC;
    }

    // TODO: Remove this when finished, this is purely for debugging.
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        SilkCocoonBlockEntity blockEntity = (SilkCocoonBlockEntity) level.getBlockEntity(pos);

        if (blockEntity != null) {
            blockEntity.setWobbleTicks(20);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return SHAPE_BY_DIR.get(facing);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState();
        Direction[] directions = context.getNearestLookingDirections();

        for (var dir : directions) {
            if (dir.getAxis().isHorizontal()) {
                state = state.setValue(FACING, dir);
                if (state.canSurvive(context.getLevel(), context.getClickedPos())) {
                    return state;
                }
            }
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return direction == state.getValue(FACING) && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction dir = state.getValue(FACING);
        BlockState relativeState = level.getBlockState(pos.relative(dir));

        return relativeState.isFaceSturdy(level, pos, dir.getOpposite());
    }

    @Override
    protected RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new SilkCocoonBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, VerdanceBlockEntityTypes.SILK_COCOON, SilkCocoonBlockEntity::tick);
    }
}
