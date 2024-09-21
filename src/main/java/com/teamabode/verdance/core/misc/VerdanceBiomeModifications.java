package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.registry.VerdancePlacedFeatures;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class VerdanceBiomeModifications {

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.tag(VerdanceBiomeTags.HAS_CANTALOUPE),
                GenerationStep.Feature.VEGETAL_DECORATION,
                VerdancePlacedFeatures.PATCH_CANTALOUPE
        );
        BiomeModifications.create(VerdancePlacedFeatures.FLOWER_VIOLET.getValue()).add(
                ModificationPhase.REPLACEMENTS,
                BiomeSelectors.tag(VerdanceBiomeTags.HAS_VIOLET),
                VerdanceBiomeModifications::replaceDefaultWithViolet
        );
    }

    private static void replaceDefaultWithViolet(BiomeSelectionContext selectedBiome, BiomeModificationContext modifiedBiome) {
        BiomeModificationContext.GenerationSettingsContext generationSettings = modifiedBiome.getGenerationSettings();

        if (selectedBiome.hasPlacedFeature(VegetationPlacedFeatures.FLOWER_DEFAULT)) {
            generationSettings.removeFeature(VegetationPlacedFeatures.FLOWER_DEFAULT);
        }
        generationSettings.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, VerdancePlacedFeatures.FLOWER_VIOLET);
    }
}
