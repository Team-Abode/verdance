package com.teamabode.verdance.common.block;

import com.teamabode.verdance.core.misc.worldgen.VerdanceConfiguredFeatures;
import com.teamabode.verdance.core.misc.tag.VerdanceBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ShrubBlock extends BushBlock implements BonemealableBlock {
    private static final VoxelShape SHAPE = Shapes.or(Block.box(7.0D, 0.0D, 7.0D, 9.0D, 4.0D, 9.0D), Block.box(3.0D, 3.0D, 3.0D, 13.0D, 13.0D, 13.0D));

    public ShrubBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return blockState.is(VerdanceBlockTags.SHRUB_MAY_PLACE_ON);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState blockState, boolean bl) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return level.random.nextBoolean();
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.registryAccess()
                .registry(Registries.CONFIGURED_FEATURE)
                .flatMap(registry -> registry.getHolder(VerdanceConfiguredFeatures.SHRUB_PATCH_BONEMEAL))
                .ifPresent(reference -> reference.value().place(level, level.getChunkSource().getGenerator(), random, pos)
        );
    }
}
