package com.teamabode.verdance.core.key;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class VerdanceBiomes {
    public static final ResourceKey<Biome> MULBERRY_FOREST = create("mulberry_forest");

    public static void register(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(MULBERRY_FOREST, mulberryForest(placedFeatures, configuredCarvers));
    }

    private static Biome mulberryForest(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> configuredCarvers) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(placedFeatures, configuredCarvers);
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeSpecialEffects.Builder specialEffects = new BiomeSpecialEffects.Builder();

        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 2, 6));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 3, 2, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4, 4));
        spawnSettings.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(VerdanceEntityTypes.SILK_MOTH, 4, 1, 2));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
        BiomeDefaultFeatures.addPlainGrass(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
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

    private static ResourceKey<Biome> create(String name) {
        return ResourceKey.create(Registries.BIOME, Verdance.id(name));
    }
}
