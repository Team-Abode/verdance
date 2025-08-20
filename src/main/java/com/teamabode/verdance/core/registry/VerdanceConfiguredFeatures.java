package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.MulberryTrunkPlacer;
import com.teamabode.verdance.common.worldgen.SilkCocoonTreeDecorator;
import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.*;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import java.util.List;

public class VerdanceConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_MULBERRY_FOREST = createKey("flower_mulberry_forest");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MULBERRY = createKey("mulberry");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MULBERRY_WITH_SILK_COCOON = createKey("mulberry_with_silk_cocoon");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_CANTALOUPE = createKey("patch_cantaloupe");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PILE_CANTALOUPE = createKey("pile_cantaloupe");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_SHRUB = createKey("patch_shrub");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SHRUBLANDS_BUSH = createKey("shrublands_bush");

    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWER_VIOLET = createKey("flower_violet");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_YELLOW_FLOWERING_SHRUB_BONEMEAL = createKey("patch_yellow_flowering_shrub_bonemeal");
    public static final RegistryKey<ConfiguredFeature<? ,?>> PATCH_PINK_FLOWERING_SHRUB_BONEMEAL = createKey("patch_pink_flowering_shrub_bonemeal");

    public static void register(Registerable<ConfiguredFeature<?, ?>> context) {
        ConfiguredFeatures.register(context, FLOWER_MULBERRY_FOREST, Feature.FLOWER, new RandomPatchFeatureConfig(
                64,
                7,
                3,
                PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(Pool.<BlockState>builder()
                                .add(Blocks.LILY_OF_THE_VALLEY.getDefaultState(), 1)
                                .add(Blocks.OXEYE_DAISY.getDefaultState(), 2).build())
                ), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.AIR)))
        ));
        ConfiguredFeatures.register(context, MULBERRY, Feature.TREE, new TreeFeatureConfig.Builder(
                SimpleBlockStateProvider.of(VerdanceBlocks.MULBERRY_LOG),
                new MulberryTrunkPlacer(7, 2, 0),
                new WeightedBlockStateProvider(
                        Pool.<BlockState>builder()
                                .add(VerdanceBlocks.MULBERRY_LEAVES.getDefaultState(), 14)
                                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.getDefaultState(), 1)
                                .build()
                ),
                new CherryFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(4), 0.33333333F, 0.25f, 0.16666667f, 0.33333334f),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build());
        ConfiguredFeatures.register(context, MULBERRY_WITH_SILK_COCOON, Feature.TREE, new TreeFeatureConfig.Builder(
                SimpleBlockStateProvider.of(VerdanceBlocks.MULBERRY_LOG),
                new MulberryTrunkPlacer(7, 2, 0),
                new WeightedBlockStateProvider(
                        Pool.<BlockState>builder()
                                .add(VerdanceBlocks.MULBERRY_LEAVES.getDefaultState(), 14)
                                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.getDefaultState(), 1)
                                .build()
                ),
                new CherryFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(4), 0.33333333F, 0.25f, 0.16666667f, 0.33333334f),
                new TwoLayersFeatureSize(1, 0, 2)
        ).decorators(List.of(new SilkCocoonTreeDecorator(0.1f))).build());

        ConfiguredFeatures.register(context, PATCH_CANTALOUPE, Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(BlockStateProvider.of(VerdanceBlocks.CANTALOUPE)),
                List.of(Blocks.GRASS_BLOCK)
        ));
        ConfiguredFeatures.register(context, PILE_CANTALOUPE, Feature.BLOCK_PILE, new BlockPileFeatureConfig(
                SimpleBlockStateProvider.of(VerdanceBlocks.CANTALOUPE)
        ));

        patchShrub(context);
        ConfiguredFeatures.register(context, SHRUBLANDS_BUSH, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new AcaciaFoliagePlacer(
                        new WeightedListIntProvider(Pool.<IntProvider>builder()
                                .add(ConstantIntProvider.create(2), 1)
                                .add(ConstantIntProvider.create(1), 4)
                                .build()),
                        ConstantIntProvider.create(0)
                ),
                new TwoLayersFeatureSize(1, 1, 2)
        ).build());

        ConfiguredFeatures.register(context, PATCH_YELLOW_FLOWERING_SHRUB_BONEMEAL, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(
                32,
                4,
                2,
                floweringShrubPlacement(VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH)
        ));
        ConfiguredFeatures.register(context, PATCH_PINK_FLOWERING_SHRUB_BONEMEAL, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(
                32,
                4,
                2,
                floweringShrubPlacement(VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH)
        ));
        ConfiguredFeatures.register(context, FLOWER_VIOLET, Feature.FLOWER, new RandomPatchFeatureConfig(
                64,
                6,
                2,
                PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockFeatureConfig(SimpleBlockStateProvider.of(VerdanceBlocks.VIOLET))
                )
        ));
    }

    public static RegistryEntry<PlacedFeature> floweringShrubPlacement(Block shrubBlock) {
        return PlacedFeatures.createEntry(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(Pool.<BlockState>builder()
                        .add(shrubBlock.getDefaultState(), 3).add(VerdanceBlocks.DESERT_BUSH.getDefaultState(), 4)
                )),
                BlockFilterPlacementModifier.of(BlockPredicate.bothOf(
                        BlockPredicate.IS_AIR,
                        BlockPredicate.matchingBlockTag(Direction.DOWN.getVector(), VerdanceBlockTags.SHRUB_MAY_PLACE_ON)
                ))
        );
    }

    private static void patchShrub(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntry<PlacedFeature> shrub = PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                SimpleBlockStateProvider.of(VerdanceBlocks.DESERT_BUSH)
        ));
        RegistryEntry<PlacedFeature> noiseBasedShrub = PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2345L, new DoublePerlinNoiseSampler.NoiseParameters(-2, List.of(1.0d)), 0.1f, List.of(
                        VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH.getDefaultState(),
                        VerdanceBlocks.DESERT_BUSH.getDefaultState(),
                        VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH.getDefaultState()
                ))
        ));
        RandomPatchFeatureConfig patch = new RandomPatchFeatureConfig(64, 5, 2, PlacedFeatures.createEntry(
                Feature.RANDOM_BOOLEAN_SELECTOR,
                new RandomBooleanFeatureConfig(shrub, noiseBasedShrub),
                BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.AIR))
        ));
        ConfiguredFeatures.register(context, PATCH_SHRUB, Feature.RANDOM_PATCH, patch);
    }

    private static RegistryKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Verdance.id(name));
    }

}
