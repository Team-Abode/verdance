package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class VerdanceBiomes {
    public static final ResourceKey<Biome> MULBERRY_FOREST = create("mulberry_forest");
    public static final ResourceKey<Biome> SHRUBLANDS = create("shrublands");

    public static void register(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(MULBERRY_FOREST, mulberryForest(placedFeatures, configuredCarvers));
        context.register(SHRUBLANDS, shrublands(placedFeatures, configuredCarvers));
    }

    private static Biome mulberryForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeSpecialEffects.Builder specialEffects = new BiomeSpecialEffects.Builder();

        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 2, 6));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 3, 2, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4, 4));
        spawnSettings.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(VerdanceEntityTypes.SILK_MOTH, 10, 1, 3));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        // Global Overworld generation
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);

        // Other common Overworld features
        BiomeDefaultFeatures.addPlainGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);

        // Mountain Biome Features
        BiomeDefaultFeatures.addExtraEmeralds(generationSettings);
        BiomeDefaultFeatures.addInfestedStone(generationSettings);

        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VerdancePlacedFeatures.FLOWER_MULBERRY_FOREST);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VerdancePlacedFeatures.MULBERRY_CHECKED);

        specialEffects.skyColor(8103167);
        specialEffects.fogColor(12638463);
        specialEffects.waterColor(937679);
        specialEffects.waterFogColor(329011);
        specialEffects.grassColorOverride(10141259);
        specialEffects.foliageColorOverride(8893999);
        specialEffects.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
        specialEffects.backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW));

        return new Biome.BiomeBuilder()
                .generationSettings(generationSettings.build())
                .mobSpawnSettings(spawnSettings.build())
                .specialEffects(specialEffects.build())
                .downfall(0.8f)
                .temperature(0.6f)
                .hasPrecipitation(true)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build();
    }

    private static Biome shrublands(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeSpecialEffects.Builder specialEffects = new BiomeSpecialEffects.Builder();

        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.ARMADILLO, 6, 1, 2));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        // Global Overworld generation
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);

        // Other common Overworld features
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);

        // Shrublands features
        BiomeDefaultFeatures.addDesertVegetation(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VerdancePlacedFeatures.SHRUBLANDS_BUSH);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VerdancePlacedFeatures.PATCH_SHRUB);

        specialEffects.foliageColorOverride(12640602);
        specialEffects.grassColorOverride(12640602);
        specialEffects.skyColor(7254527);
        specialEffects.fogColor(12638463);
        specialEffects.waterColor(4159204);
        specialEffects.waterFogColor(329011);
        specialEffects.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
        specialEffects.backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT));

        return new Biome.BiomeBuilder()
                .generationSettings(generationSettings.build())
                .mobSpawnSettings(spawnSettings.build())
                .specialEffects(specialEffects.build())
                .downfall(0.0f)
                .temperature(2.0f)
                .hasPrecipitation(false)
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build();
    }

    private static ResourceKey<Biome> create(String name) {
        return ResourceKey.create(Registries.BIOME, Verdance.id(name));
    }
}
