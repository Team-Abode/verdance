package com.teamabode.verdance.common.block;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.common.entity.CushionEntity;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CushionBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0);
    public static final BooleanProperty OCCUPIED = Properties.OCCUPIED;

    public CushionBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.stateManager.getDefaultState().with(OCCUPIED, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(OCCUPIED);
    }

    @Override
    public boolean hasComparatorOutput(BlockState blockState) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World level, BlockPos pos) {
        return state.get(OCCUPIED) ? 15 : 0;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.CONSUME;
        }
        if (!player.isSneaking()) {
            if (state.get(OCCUPIED)) {
                return ActionResult.FAIL;
            }
            world.setBlockState(pos, state.with(OCCUPIED, true));
            CushionEntity cushion = new CushionEntity(VerdanceEntityTypes.CUSHION, world);
            cushion.setPosition(pos.getX() + 0.5D, pos.getY() + 0.4D, pos.getZ() + 0.5D);

            if (world.spawnEntity(cushion)) {
                player.startRiding(cushion);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public void onStateReplaced(BlockState blockState, World level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        List<CushionEntity> entities = level.getNonSpectatingEntities(CushionEntity.class, new Box(blockPos));
        for (CushionEntity cushionEntity : entities) {
            cushionEntity.remove(Entity.RemovalReason.DISCARDED);
        }
        super.onStateReplaced(blockState, level, blockPos, blockState2, bl);
    }

    public void onLandedUpon(World level, BlockState blockState, BlockPos blockPos, Entity entity, float f) {
        super.onLandedUpon(level, blockState, blockPos, entity, f * 0.5F);
    }

    @Override
    public void onEntityLand(BlockView blockGetter, Entity entity) {
        if (entity.bypassesLandingEffects()) {
            super.onEntityLand(blockGetter, entity);
        }
        else {
            this.bounce(entity);
        }
    }

    private void bounce(Entity entity) {
        Vec3d vec3 = entity.getVelocity();
        if (vec3.y < 0.0d) {
            double multiplier = entity instanceof LivingEntity ? 1.0d: 0.8d;
            entity.setVelocity(vec3.x, -vec3.y * 0.8d * multiplier, vec3.z);
        }
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockView world, @NotNull BlockPos pos, @NotNull ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(@NotNull BlockState state, @NotNull BlockView world, @NotNull BlockPos pos, @NotNull ShapeContext context) {
        return SHAPE;
    }
}
