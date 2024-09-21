package com.teamabode.verdance.common.worldgen;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.verdance.core.registry.VerdanceTrunkPlacerTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class MulberryTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<MulberryTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            MulberryTrunkPlacer.fillTrunkPlacerFields(instance)
            .apply(instance, MulberryTrunkPlacer::new)
    );

    public MulberryTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    public List<FoliagePlacer.TreeNode> generate(TestableWorld level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, int freeTreeHeight, BlockPos pos, TreeFeatureConfig config) {
        MulberryTrunkPlacer.setToDirt(level, blockSetter, random, pos.down(), config);
        BlockPos.Mutable mutableBlockPos = pos.mutableCopy();
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        ArrayList<FoliagePlacer.TreeNode> list = new ArrayList<>();
        BlockPos branchPos = null;
        for (int i = 1; i <= freeTreeHeight; ++i) {
            if (i == freeTreeHeight / 3) {
                list.add(new FoliagePlacer.TreeNode(mutableBlockPos.offset(direction.getOpposite()).up(), 0, false));
            }
            if (i == freeTreeHeight / 3 + 1 && random.nextFloat() < 0.85F) {
                mutableBlockPos.move(direction);
            }
            if (i == freeTreeHeight * 2 / 3 + 1 && random.nextFloat() < 0.85F) {
                mutableBlockPos.move(direction.getOpposite());
            }
            if (i == Math.ceil(freeTreeHeight / 2.0)) {
                branchPos = mutableBlockPos.toImmutable();
            }
            if (TreeFeature.canReplace(level, mutableBlockPos)) {
                this.getAndSetState(level, blockSetter, random, mutableBlockPos, config);
            }
            if (i == freeTreeHeight) {
                list.add(new FoliagePlacer.TreeNode(mutableBlockPos.up(), 1, false));
            }
            mutableBlockPos.move(Direction.UP);
        }
        if (branchPos != null) {
            list.add(generateBranch(level, blockSetter, random, branchPos, config, direction));
        }
        return list;
    }

    private FoliagePlacer.TreeNode generateBranch(TestableWorld level, BiConsumer<BlockPos, BlockState> blockSetter, Random random, BlockPos pos, TreeFeatureConfig config, Direction direction) {
        Function<BlockState, BlockState> function = blockState -> (BlockState) blockState.withIfExists(PillarBlock.AXIS, direction.getAxis());
        int i = random.nextBoolean() ? 2 : 1;
        Mutable mutablePos = pos.mutableCopy();
        for (int j = 0; j < i; ++j) {
            mutablePos.move(direction);
            this.getAndSetState(level, blockSetter, random, mutablePos, config, function);
        }
        mutablePos.move(Direction.UP).move(direction);
        this.getAndSetState(level, blockSetter, random, mutablePos, config);
        return new FoliagePlacer.TreeNode(mutablePos.up(), 0, false);
    }

    protected TrunkPlacerType<?> getType() {
        return VerdanceTrunkPlacerTypes.MULBERRY_TRUNK_PLACER;
    }
}
