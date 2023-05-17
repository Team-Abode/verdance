package com.teamabode.verdance.common.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.verdance.core.misc.worldgen.VerdanceTrunkPlacerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MulberryTrunkPlacer extends TrunkPlacer {
    public static final Codec<MulberryTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> MulberryTrunkPlacer.trunkPlacerParts(instance).apply(instance, MulberryTrunkPlacer::new));

    public MulberryTrunkPlacer(int i, int j, int k) {
        super(i, j, k);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return VerdanceTrunkPlacerType.MULBERRY_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        MulberryTrunkPlacer.setDirtAt(level, blockSetter, random, pos.below(), config);
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        ArrayList<FoliagePlacer.FoliageAttachment> list = new ArrayList<>();
        BlockPos pos2 = null;
        for (int i = 1; i <= freeTreeHeight; ++i) {
            if (i == freeTreeHeight / 3) {
                list.add(new FoliagePlacer.FoliageAttachment(mutableBlockPos.relative(direction.getOpposite()).above(), 0, false));
            }
            if (i == freeTreeHeight / 3 + 1 && random.nextFloat() < 0.85F) {
                mutableBlockPos.move(direction);
            }
            if (i == freeTreeHeight * 2 / 3 + 1 && random.nextFloat() < 0.85F) {
                mutableBlockPos.move(direction.getOpposite());
            }
            if (i == Math.ceil(freeTreeHeight / 2.0)) {
                pos2 = mutableBlockPos.immutable();
            }
            if (TreeFeature.validTreePos(level, mutableBlockPos)) {
                this.placeLog(level, blockSetter, random, mutableBlockPos, config);
            }
            if (i == freeTreeHeight) {
                list.add(new FoliagePlacer.FoliageAttachment(mutableBlockPos.above(), 1, false));
            }
            mutableBlockPos.move(Direction.UP);
        }
        list.add(generateBranch(level, blockSetter, random, pos2, config, direction));
        return list;
    }

    private FoliagePlacer.FoliageAttachment generateBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config, Direction direction) {
        Function<BlockState, BlockState> function = blockState -> (BlockState) blockState.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
        int i = random.nextBoolean() ? 2 : 1;
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
        for (int j = 0; j < i; ++j) {
            mutableBlockPos.move(direction);
            this.placeLog(level, blockSetter, random, mutableBlockPos, config, function);
        }
        mutableBlockPos.move(Direction.UP).move(direction);
        this.placeLog(level, blockSetter, random, mutableBlockPos, config);
        return new FoliagePlacer.FoliageAttachment(mutableBlockPos.above(), 0, false);
    }
}
