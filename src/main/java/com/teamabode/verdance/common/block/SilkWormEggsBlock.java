package com.teamabode.verdance.common.block;

import com.teamabode.verdance.common.entity.silkworm.Silkworm;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("deprecation")
public class SilkWormEggsBlock extends Block {
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 1.5, 16.0);

    public SilkWormEggsBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (level.getBlockState(pos.below()).is(BlockTags.LEAVES)) {
            if (!level.isClientSide()) level.levelEvent(2005, pos, 0);
            level.scheduleTick(pos, this, this.getHatchDelay(level.getRandom()));
        }
    }

    public int getHatchDelay(RandomSource random) {
        return random.nextInt(4800, 6000);
    }

    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState supportState = level.getBlockState(pos.below());
        return Block.isFaceFull(supportState.getCollisionShape(level, pos.below()), Direction.UP);
    }

    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return !this.canSurvive(state, level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        spawnBabies(level, pos, random);
    }

    private static void spawnBabies(ServerLevel level, BlockPos pos, RandomSource random) {
        int count = random.nextInt(2) + 2;

        for (int i = 0; i < count; i++) {
            Silkworm silkworm = VerdanceEntityTypes.SILKWORM.create(level);
            if (silkworm == null) continue;

            silkworm.setPos(pos.getCenter().offsetRandom(random, 0.25f));
            silkworm.setPersistenceRequired();
            level.addFreshEntity(silkworm);
        }
        level.destroyBlock(pos, false);
    }
}
