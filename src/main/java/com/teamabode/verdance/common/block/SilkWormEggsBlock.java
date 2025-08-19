package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.common.entity.silkworm.SilkwormEntity;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceCriteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

public class SilkWormEggsBlock extends Block {
    public static final MapCodec<SilkWormEggsBlock> CODEC = createCodec(SilkWormEggsBlock::new);
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
    protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
        return !this.canPlaceAt(state, world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
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

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return CODEC;
    }

    public int getHatchDelay(Random random) {
        return random.nextBetweenExclusive(4800, 6000);
    }

    private static void hatch(ServerWorld level, BlockPos pos, Random random) {
        int count = random.nextInt(2) + 2;

        for (int i = 0; i < count; i++) {
            SilkwormEntity silkworm = VerdanceEntityTypes.SILKWORM.create(level, SpawnReason.BREEDING);
            if (silkworm == null) continue;

            silkworm.setPosition(pos.toCenterPos().addRandom(random, 0.25f));
            silkworm.setPersistent();
            level.spawnEntity(silkworm);
        }
        level.breakBlock(pos, false);
    }
}
