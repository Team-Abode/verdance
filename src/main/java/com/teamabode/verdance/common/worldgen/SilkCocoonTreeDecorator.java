package com.teamabode.verdance.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.verdance.common.block.SilkCocoonBlock;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceTreeDecoratorTypes;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class SilkCocoonTreeDecorator extends TreeDecorator {
    public static final MapCodec<SilkCocoonTreeDecorator> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(SilkCocoonTreeDecorator::getProbability)
    ).apply(instance, SilkCocoonTreeDecorator::new));

    private final float probability;

    public SilkCocoonTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    public void place(Context generator) {
        LevelSimulatedReader world = generator.level();
        RandomSource random = generator.random();

        if (random.nextFloat() >= this.getProbability()) return;

        List<BlockPos> logPositions = generator.logs();

        List<BlockPos> validPositions = logPositions.stream()
                .flatMap(pos -> Direction.Plane.HORIZONTAL.stream().map(pos::relative))
                .filter(generator::isAir)
                .collect(Collectors.toList());
        Collections.shuffle(validPositions);
        Optional<BlockPos> targetPos = validPositions.stream().findFirst();

        if (targetPos.isEmpty()) return;
        BlockPos pos = targetPos.get();

        for (Direction dir : Direction.Plane.HORIZONTAL) {
            if (world.isStateAtPosition(pos.relative(dir), state -> state.is(BlockTags.LOGS))) {
                generator.setBlock(pos, VerdanceBlocks.SILK_COCOON.defaultBlockState().setValue(SilkCocoonBlock.FACING, dir));
                break;
            }
        }
    }

    public float getProbability() {
        return this.probability;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return VerdanceTreeDecoratorTypes.SILK_COCOON;
    }
}
