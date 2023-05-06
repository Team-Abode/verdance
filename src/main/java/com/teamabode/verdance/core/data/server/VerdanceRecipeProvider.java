package com.teamabode.verdance.core.data.server;

import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.misc.VerdanceFamilies;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

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

        stucco(VerdanceFamilies.WHITE_STUCCO, Items.WHITE_DYE, exporter);
    }

    private static void stucco(BlockFamily family, Item dye, Consumer<FinishedRecipe> exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), 6)
                .requires(Items.CLAY, 2)
                .requires(Items.SAND, 2)
                .requires(dye)
                .unlockedBy(getHasName(Items.CLAY), has(Items.CLAY))
                .unlockedBy(getHasName(Items.SAND), has(Items.SAND))
                .unlockedBy(getHasName(dye), has(dye))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, family.get(Variant.STAIRS), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', family.getBaseBlock())
                .unlockedBy(getHasName(family.getBaseBlock()), has(family.getBaseBlock()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, family.get(Variant.SLAB), 6)
                .pattern("SSS")
                .define('S', family.getBaseBlock())
                .unlockedBy(getHasName(family.getBaseBlock()), has(family.getBaseBlock()))
                .save(exporter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, family.get(Variant.WALL), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', family.getBaseBlock())
                .unlockedBy(getHasName(family.getBaseBlock()), has(family.getBaseBlock()))
                .save(exporter);

        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.STAIRS), family.getBaseBlock());
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.SLAB), family.getBaseBlock(), 2);
        stonecutterResultFromBase(exporter, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.WALL), family.getBaseBlock());
    }
}
