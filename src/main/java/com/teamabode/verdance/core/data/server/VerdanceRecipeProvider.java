package com.teamabode.verdance.core.data.server;

import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import java.util.function.Consumer;

public class VerdanceRecipeProvider extends FabricRecipeProvider {

    public VerdanceRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        rangeDisc(exporter);
    }

    private static void rangeDisc(Consumer<FinishedRecipe> exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VerdanceItems.MUSIC_DISC_RANGE)
                .requires(VerdanceItems.DISC_FRAGMENT_RANGE, 4)
                .unlockedBy("has_fragment", has(VerdanceItems.DISC_FRAGMENT_RANGE))
                .save(exporter);
    }
}
