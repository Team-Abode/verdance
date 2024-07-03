package com.teamabode.verdance.common.block;

import com.teamabode.verdance.common.entity.CushionEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CushionBlock extends Block {

    private static final VoxelShape CUSHION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0);

    public CushionBlock(Properties properties) {
        super(properties);
    }


    //When you sit on Cushion, it gives a redstone signal of 15
    protected int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        List<CushionEntity> aBunchOfCushions = level.getEntitiesOfClass(CushionEntity.class, new AABB(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockPos.getX(), blockPos.getY(), blockPos.getZ()));
        if (aBunchOfCushions instanceof CushionEntity) {
            return 15;
        } else {
            return 0;
        }
    }

    @Override
    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return CUSHION_SHAPE;
    }

    @Override
    public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return CUSHION_SHAPE;
    }
}
