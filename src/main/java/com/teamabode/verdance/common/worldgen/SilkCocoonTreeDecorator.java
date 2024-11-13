package com.teamabode.verdance.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.verdance.common.block.SilkCocoonBlock;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceTreeDecoratorTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SilkCocoonTreeDecorator extends TreeDecorator {
    public static final Codec<SilkCocoonTreeDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.floatRange(0.0f, 1.0f).fieldOf("probability").forGetter(SilkCocoonTreeDecorator::getProbability)
    ).apply(instance, SilkCocoonTreeDecorator::new));

    private final float probability;

    public SilkCocoonTreeDecorator(float probability) {
        this.probability = probability;
    }

    @Override
    public void generate(Generator generator) {
        TestableWorld world = generator.getWorld();
        Random random = generator.getRandom();

        if (random.nextFloat() >= this.getProbability()) return;

        List<BlockPos> logPositions = generator.getLogPositions();

        List<BlockPos> validPositions = logPositions.stream()
                .flatMap(pos -> Direction.Type.HORIZONTAL.stream().map(pos::offset))
                .filter(generator::isAir)
                .collect(Collectors.toList());
        Collections.shuffle(validPositions);
        Optional<BlockPos> targetPos = validPositions.stream().findFirst();

        if (targetPos.isEmpty()) return;
        BlockPos pos = targetPos.get();

        for (Direction dir : Direction.Type.HORIZONTAL) {
            if (world.testBlockState(pos.offset(dir), state -> state.isIn(BlockTags.LOGS))) {
                generator.replace(pos, VerdanceBlocks.SILK_COCOON.getDefaultState().with(SilkCocoonBlock.FACING, dir));
                break;
            }
        }
    }

    public float getProbability() {
        return this.probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return VerdanceTreeDecoratorTypes.SILK_COCOON;
    }
}
