package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.common.entity.CushionEntity;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CushionBlock extends Block {
    public static final MapCodec<CushionBlock> CODEC = simpleCodec(CushionBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0);
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

    public CushionBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OCCUPIED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OCCUPIED);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState blockState) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(OCCUPIED) ? 15 : 0;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }
        if (!player.isShiftKeyDown()) {
            if (blockState.getValue(OCCUPIED)) {
                return InteractionResult.FAIL;
            }
            level.setBlockAndUpdate(blockPos, blockState.setValue(OCCUPIED, true));
            CushionEntity cushion = new CushionEntity(VerdanceEntityTypes.CUSHION.get(), level);
            cushion.setPos(blockPos.getX() + 0.5D, blockPos.getY() + 0.4D, blockPos.getZ() + 0.5D);

            if (level.addFreshEntity(cushion)) {
                player.startRiding(cushion);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    protected void onRemove(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        List<CushionEntity> entities = level.getEntitiesOfClass(CushionEntity.class, new AABB(blockPos));
        for (CushionEntity cushionEntity : entities) {
            cushionEntity.remove(Entity.RemovalReason.DISCARDED);
        }
        super.onRemove(blockState, level, blockPos, blockState2, bl);
    }

    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float f) {
        super.fallOn(level, blockState, blockPos, entity, f * 0.5F);
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter blockGetter, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(blockGetter, entity);
        }
        else {
            this.bounce(entity);
        }
    }

    private void bounce(Entity entity) {
        Vec3 vec3 = entity.getDeltaMovement();
        if (vec3.y < 0.0d) {
            double multiplier = entity instanceof LivingEntity ? 1.0d: 0.8d;
            entity.setDeltaMovement(vec3.x, -vec3.y * 0.8d * multiplier, vec3.z);
        }
    }

    @Override
    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends Block> codec() {
        return CODEC;
    }
}
