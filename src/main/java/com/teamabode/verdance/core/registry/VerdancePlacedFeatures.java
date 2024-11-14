package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;
import net.minecraft.world.gen.placementmodifier.NoiseBasedCountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.NoiseThresholdCountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceWaterDepthFilterPlacementModifier;

import java.util.List;

public class VerdancePlacedFeatures {
    public static final RegistryKey<PlacedFeature> FLOWER_MULBERRY_FOREST = createKey("flower_mulberry_forest");
    public static final RegistryKey<PlacedFeature> MULBERRY = createKey("mulberry");
    public static final RegistryKey<PlacedFeature> MULBERRY_CHECKED = createKey("mulberry_checked");
    public static final RegistryKey<PlacedFeature> PATCH_CANTALOUPE = createKey("patch_cantaloupe");
    public static final RegistryKey<PlacedFeature> PILE_CANTALOUPE = createKey("pile_cantaloupe");
    public static final RegistryKey<PlacedFeature> PATCH_SHRUB = createKey("patch_shrub");
    public static final RegistryKey<PlacedFeature> SHRUBLANDS_BUSH = createKey("shrublands_bush");
    public static final RegistryKey<PlacedFeature> FLOWER_VIOLET = createKey("flower_violet");

    public static void register(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Mulberry Forest
        PlacedFeatures.register(context, FLOWER_MULBERRY_FOREST, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.FLOWER_MULBERRY_FOREST), List.of(
                NoiseThresholdCountPlacementModifier.of(-0.25d, 1, 5),
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.MOTION_BLOCKING),
                BiomePlacementModifier.of()
        ));
        PlacedFeatures.register(context, MULBERRY, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.MULBERRY), List.of(
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(
                        VerdanceBlocks.MULBERRY_SAPLING.getDefaultState(),
                        Vec3i.ZERO
                ))
        ));
        PlacedFeatures.register(context, MULBERRY_CHECKED, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.MULBERRY), List.of(
                NoiseBasedCountPlacementModifier.of(25, 10.0d, 0.5d),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR),
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(
                        VerdanceBlocks.MULBERRY_SAPLING.getDefaultState(),
                        Vec3i.ZERO
                )),
                BiomePlacementModifier.of()
        ));

        // Cantaloupe
        PlacedFeatures.register(context, PATCH_CANTALOUPE, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.PATCH_CANTALOUPE), List.of(
                RarityFilterPlacementModifier.of(400),
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.MOTION_BLOCKING),
                BiomePlacementModifier.of()
        ));
        PlacedFeatures.register(context, PILE_CANTALOUPE, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.PILE_CANTALOUPE));

        // Shrublands
        PlacedFeatures.register(context, PATCH_SHRUB, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.PATCH_SHRUB), List.of(
                NoiseBasedCountPlacementModifier.of(12, 1.0d, 0.5d),
                SquarePlacementModifier.of(),
                HeightmapPlacementModifier.of(Heightmap.Type.MOTION_BLOCKING),
                BiomePlacementModifier.of()
        ));
        PlacedFeatures.register(context, SHRUBLANDS_BUSH, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.SHRUBLANDS_BUSH), List.of(
                CountPlacementModifier.of(UniformIntProvider.create(4, 5)),
                SquarePlacementModifier.of(),
                SurfaceWaterDepthFilterPlacementModifier.of(0),
                HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR),
                BiomePlacementModifier.of(),
                BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), Vec3i.ZERO))
        ));
        PlacedFeatures.register(context, FLOWER_VIOLET, configuredFeatures.getOrThrow(VerdanceConfiguredFeatures.FLOWER_VIOLET), List.of(
                RarityFilterPlacementModifier.of(8),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        ));
    }

    private static RegistryKey<PlacedFeature> createKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Verdance.id(name));
    }
}
