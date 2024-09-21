package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class VerdanceBiomes {
    public static final RegistryKey<Biome> MULBERRY_FOREST = create("mulberry_forest");
    public static final RegistryKey<Biome> SHRUBLANDS = create("shrublands");

    public static void register(Registerable<Biome> context) {
        RegistryEntryLookup<PlacedFeature> placedFeatures = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers = context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER);

        context.register(MULBERRY_FOREST, mulberryForest(placedFeatures, configuredCarvers));
        context.register(SHRUBLANDS, shrublands(placedFeatures, configuredCarvers));
    }

    private static Biome mulberryForest(RegistryEntryLookup<PlacedFeature> placedFeatures, RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers) {
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        BiomeEffects.Builder specialEffects = new BiomeEffects.Builder();

        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 2, 6));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 3, 2, 3));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 1, 4, 4));
        spawnSettings.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(VerdanceEntityTypes.SILK_MOTH, 10, 1, 3));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        // Global Overworld generation
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        // Other common Overworld features
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);

        // Mountain Biome Features
        DefaultBiomeFeatures.addEmeraldOre(generationSettings);
        DefaultBiomeFeatures.addInfestedStone(generationSettings);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VerdancePlacedFeatures.FLOWER_MULBERRY_FOREST);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VerdancePlacedFeatures.MULBERRY_CHECKED);

        specialEffects.skyColor(8103167);
        specialEffects.fogColor(12638463);
        specialEffects.waterColor(937679);
        specialEffects.waterFogColor(329011);
        specialEffects.grassColor(10141259);
        specialEffects.foliageColor(8893999);
        specialEffects.moodSound(BiomeMoodSound.CAVE);
        specialEffects.music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_MEADOW));

        return new Biome.Builder()
                .generationSettings(generationSettings.build())
                .spawnSettings(spawnSettings.build())
                .effects(specialEffects.build())
                .downfall(0.8f)
                .temperature(0.6f)
                .precipitation(true)
                .temperatureModifier(Biome.TemperatureModifier.NONE)
                .build();
    }

    private static Biome shrublands(RegistryEntryLookup<PlacedFeature> placedFeatures, RegistryEntryLookup<ConfiguredCarver<?>> configuredCarvers) {
        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(placedFeatures, configuredCarvers);
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        BiomeEffects.Builder specialEffects = new BiomeEffects.Builder();

        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.ARMADILLO, 6, 1, 2));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        // Global Overworld generation
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        // Other common Overworld features
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);

        // Shrublands features
        DefaultBiomeFeatures.addDesertDeadBushes(generationSettings);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VerdancePlacedFeatures.SHRUBLANDS_BUSH);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VerdancePlacedFeatures.PATCH_SHRUB);

        specialEffects.foliageColor(12640602);
        specialEffects.grassColor(12640602);
        specialEffects.skyColor(7254527);
        specialEffects.fogColor(12638463);
        specialEffects.waterColor(4159204);
        specialEffects.waterFogColor(329011);
        specialEffects.moodSound(BiomeMoodSound.CAVE);
        specialEffects.music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DESERT));

        return new Biome.Builder()
                .generationSettings(generationSettings.build())
                .spawnSettings(spawnSettings.build())
                .effects(specialEffects.build())
                .downfall(0.0f)
                .temperature(2.0f)
                .precipitation(false)
                .temperatureModifier(Biome.TemperatureModifier.NONE)
                .build();
    }

    private static RegistryKey<Biome> create(String name) {
        return RegistryKey.of(RegistryKeys.BIOME, Verdance.id(name));
    }
}
