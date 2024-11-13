package com.teamabode.verdance.common.block;

import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceCriteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class SilkWormEggsBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.5, 16.0);

    public SilkWormEggsBlock(Settings properties) {
        super(properties);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView level, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onBlockAdded(BlockState state, World level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.getBlockState(pos.down()).isIn(BlockTags.LEAVES)) {
            if (!level.isClient()) {
                level.syncWorldEvent(2012, pos, 15);
            }
            level.scheduleBlockTick(pos, this, this.getHatchDelay(level.getRandom()));
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView level, BlockPos pos) {
        BlockState supportState = level.getBlockState(pos.down());
        return Block.isFaceFullSquare(supportState.getCollisionShape(level, pos.down()), Direction.UP);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess level, BlockPos pos, BlockPos neighborPos) {
        return !this.canPlaceAt(state, level, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld level, BlockPos pos, Random random) {
        hatch(level, pos, random);
    }

    @Override
    public void afterBreak(World level, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(level, player, pos, state, blockEntity, stack);

        if (!level.isClient()) {
            VerdanceCriteria.SILKWORM_EGGS_DESTROYED.trigger((ServerPlayerEntity) player, stack);
        }
    }

    public int getHatchDelay(Random random) {
        return random.nextBetweenExclusive(4800, 6000);
    }

    private static void hatch(ServerWorld level, BlockPos pos, Random random) {
        int count = random.nextInt(2) + 2;

        for (int i = 0; i < count; i++) {
            SilkwormEntity silkworm = VerdanceEntityTypes.SILKWORM.create(level);
            if (silkworm == null) continue;

            silkworm.setPosition(pos.toCenterPos().addRandom(random, 0.25f));
            silkworm.setPersistent();
            level.spawnEntity(silkworm);
        }
        level.breakBlock(pos, false);
    }
}
