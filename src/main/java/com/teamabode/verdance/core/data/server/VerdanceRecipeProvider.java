package com.teamabode.verdance.core.data.server;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.misc.VerdanceFamilies;
import com.teamabode.verdance.core.registry.misc.VerdanceItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class VerdanceRecipeProvider extends FabricRecipeProvider {

    public VerdanceRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    private static void rangeDisc(Consumer<FinishedRecipe> exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VerdanceItems.MUSIC_DISC_RANGE).requires(VerdanceItems.DISC_FRAGMENT_RANGE, 4).unlockedBy("has_fragment", has(VerdanceItems.DISC_FRAGMENT_RANGE)).save(exporter);
    }

    private static void stucco(Consumer<FinishedRecipe> exporter, BlockFamily family, Item dye) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), 6).requires(Items.CLAY, 2).requires(Items.SAND, 2).requires(dye).unlockedBy(getHasName(Items.CLAY), has(Items.CLAY)).unlockedBy(getHasName(Items.SAND), has(Items.SAND)).unlockedBy(getHasName(dye), has(dye)).save(exporter);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.STAIRS), family.getBaseBlock());
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.SLAB), family.getBaseBlock(), 2);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.WALL), family.getBaseBlock());
    }

    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        VerdanceFamilies.getAllFamilies().filter(family -> family.shouldGenerateRecipe(FeatureFlags.DEFAULT_FLAGS)).forEach(family -> RecipeProvider.generateRecipes(exporter, family));
        woodFromLogs(exporter, VerdanceBlocks.MULBERRY_WOOD, VerdanceBlocks.MULBERRY_LOG);
        woodFromLogs(exporter, VerdanceBlocks.STRIPPED_MULBERRY_WOOD, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        planksFromLog(exporter, VerdanceBlocks.MULBERRY_PLANKS, VerdanceItemTags.MULBERRY_LOGS, 4);
        hangingSign(exporter, VerdanceItems.MULBERRY_HANGING_SIGN, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        rangeDisc(exporter);
        stucco(exporter, VerdanceFamilies.WHITE_STUCCO, Items.WHITE_DYE);
        stucco(exporter, VerdanceFamilies.LIGHT_GRAY_STUCCO, Items.LIGHT_GRAY_DYE);
        stucco(exporter, VerdanceFamilies.GRAY_STUCCO, Items.GRAY_DYE);
        stucco(exporter, VerdanceFamilies.BLACK_STUCCO, Items.BLACK_DYE);
    }
}
