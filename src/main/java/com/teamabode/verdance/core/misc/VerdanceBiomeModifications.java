package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.registry.VerdancePlacedFeatures;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.GenerationStep;

public class VerdanceBiomeModifications {

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(VerdanceBiomeTags.HAS_CANTALOUPE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                VerdancePlacedFeatures.PATCH_CANTALOUPE
        );
        BiomeModifications.create(VerdancePlacedFeatures.FLOWER_VIOLET.location()).add(
                ModificationPhase.REPLACEMENTS,
                BiomeSelectors.tag(VerdanceBiomeTags.HAS_VIOLET),
                VerdanceBiomeModifications::replaceDefaultWithViolet
        );
    }

    private static void replaceDefaultWithViolet(BiomeSelectionContext selectedBiome, BiomeModificationContext modifiedBiome) {
        BiomeModificationContext.GenerationSettingsContext generationSettings = modifiedBiome.getGenerationSettings();

        if (selectedBiome.hasPlacedFeature(VegetationPlacements.FLOWER_DEFAULT)) {
            generationSettings.removeFeature(VegetationPlacements.FLOWER_DEFAULT);
        }
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VerdancePlacedFeatures.FLOWER_VIOLET);
    }
}
