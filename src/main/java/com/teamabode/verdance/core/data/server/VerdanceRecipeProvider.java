package com.teamabode.verdance.core.data.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.misc.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class VerdanceRecipeProvider extends FabricRecipeProvider {

    public VerdanceRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    private static void rangeDisc(RecipeOutput exporter) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VerdanceItems.MUSIC_DISC_RANGE).requires(VerdanceItems.DISC_FRAGMENT_RANGE, 4).unlockedBy("has_fragment", has(VerdanceItems.DISC_FRAGMENT_RANGE)).save(exporter);
    }

    private static void stucco(RecipeOutput output, BlockFamily family, Item dye) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), 8).requires(dye).requires(Items.CLAY, 4).requires(Ingredient.of(ItemTags.SAND), 4).group("stucco").unlockedBy(getHasName(Items.CLAY), has(Items.CLAY)).unlockedBy("has_sand", has(ItemTags.SAND)).save(output);

        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.STAIRS), family.getBaseBlock());
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.SLAB), family.getBaseBlock(), 2);
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.WALL), family.getBaseBlock());
    }

    private static void cantaloupe(RecipeOutput exporter) {
        twoByTwoPacker(exporter, RecipeCategory.BUILDING_BLOCKS, VerdanceBlocks.CANTALOUPE, VerdanceItems.CANTALOUPE_SLICE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VerdanceItems.CANTALOUPE_SEEDS)
                .requires(VerdanceItems.CANTALOUPE_SLICE)
                .unlockedBy(getHasName(VerdanceItems.CANTALOUPE_SLICE), has(VerdanceItems.CANTALOUPE_SLICE))
                .save(exporter);

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(VerdanceItems.CANTALOUPE_SLICE),
                        RecipeCategory.FOOD,
                        VerdanceItems.GRILLED_CANTALOUPE_SLICE,
                        0.25f,
                        200)
                .unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE))
                .save(exporter, Verdance.id("grilled_cantaloupe"));
        SimpleCookingRecipeBuilder.smoking(
                        Ingredient.of(VerdanceItems.CANTALOUPE_SLICE),
                        RecipeCategory.FOOD,
                        VerdanceItems.GRILLED_CANTALOUPE_SLICE,
                        0.25f,
                        100)
                .unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE))
                .save(exporter, Verdance.id("grilled_cantaloupe_slice_from_smoking"));
        SimpleCookingRecipeBuilder.campfireCooking(
                        Ingredient.of(VerdanceItems.CANTALOUPE_SLICE),
                        RecipeCategory.FOOD,
                        VerdanceItems.GRILLED_CANTALOUPE_SLICE,
                        0.25f,
                        600)
                .unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE))
                .save(exporter, Verdance.id("grilled_cantaloupe_slice_from_campfire_cooking"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VerdanceItems.CANTALOUPE_JUICE)
                .requires(VerdanceItems.CANTALOUPE_SLICE, 4)
                .requires(Items.SUGAR)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(exporter, Verdance.id("cantaloupe_juice"));
    }

    public static void stonecutterResultFromBase(RecipeOutput exporter, RecipeCategory category, ItemLike result, ItemLike material) {
        stonecutterResultFromBase(exporter, category, result, material, 1);
    }

    public static void stonecutterResultFromBase(RecipeOutput exporter, RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount).unlockedBy(RecipeProvider.getHasName(material), RecipeProvider.has(material)).save(exporter, new ResourceLocation(Verdance.MOD_ID, getConversionRecipeName(result, material) + "_stonecutting"));
    }

    private static void farmersDelightCompat(VerdanceRecipeProvider provider, RecipeOutput exporter) {
        RecipeOutput compatExporter = provider.withConditions(exporter, ResourceConditions.allModsLoaded("farmersdelight"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VerdanceItems.MULBERRY, 9)
                .requires(VerdanceBlocks.MULBERRY_CRATE)
                .unlockedBy("has_mulberry_crate", has(VerdanceBlocks.MULBERRY_CRATE))
                .save(compatExporter, Verdance.id("mulberry"));
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, VerdanceBlocks.MULBERRY_CRATE)
                .define('#', VerdanceItems.MULBERRY)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_mulberry", has(VerdanceItems.MULBERRY))
                .save(compatExporter, Verdance.id("mulberry_crate"));
    }

    public void buildRecipes(RecipeOutput exporter) {
        VerdanceBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach(family -> RecipeProvider.generateRecipes(exporter, family, FeatureFlagSet.of(FeatureFlags.VANILLA)));
        woodFromLogs(exporter, VerdanceBlocks.MULBERRY_WOOD, VerdanceBlocks.MULBERRY_LOG);
        woodFromLogs(exporter, VerdanceBlocks.STRIPPED_MULBERRY_WOOD, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        planksFromLog(exporter, VerdanceBlocks.MULBERRY_PLANKS, VerdanceItemTags.MULBERRY_LOGS, 4);

        cantaloupe(exporter);
        hangingSign(exporter, VerdanceItems.MULBERRY_HANGING_SIGN, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        rangeDisc(exporter);
        stucco(exporter, VerdanceBlockFamilies.WHITE_STUCCO, Items.WHITE_DYE);
        stucco(exporter, VerdanceBlockFamilies.LIGHT_GRAY_STUCCO, Items.LIGHT_GRAY_DYE);
        stucco(exporter, VerdanceBlockFamilies.GRAY_STUCCO, Items.GRAY_DYE);
        stucco(exporter, VerdanceBlockFamilies.BLACK_STUCCO, Items.BLACK_DYE);
        stucco(exporter, VerdanceBlockFamilies.BROWN_STUCCO, Items.BROWN_DYE);
        stucco(exporter, VerdanceBlockFamilies.RED_STUCCO, Items.RED_DYE);
        stucco(exporter, VerdanceBlockFamilies.ORANGE_STUCCO, Items.ORANGE_DYE);
        stucco(exporter, VerdanceBlockFamilies.YELLOW_STUCCO, Items.YELLOW_DYE);
        stucco(exporter, VerdanceBlockFamilies.LIME_STUCCO, Items.LIME_DYE);
        stucco(exporter, VerdanceBlockFamilies.GREEN_STUCCO, Items.GREEN_DYE);
        stucco(exporter, VerdanceBlockFamilies.CYAN_STUCCO, Items.CYAN_DYE);
        stucco(exporter, VerdanceBlockFamilies.LIGHT_BLUE_STUCCO, Items.LIGHT_BLUE_DYE);
        stucco(exporter, VerdanceBlockFamilies.BLUE_STUCCO, Items.BLUE_DYE);
        stucco(exporter, VerdanceBlockFamilies.PURPLE_STUCCO, Items.PURPLE_DYE);
        stucco(exporter, VerdanceBlockFamilies.MAGENTA_STUCCO, Items.MAGENTA_DYE);
        stucco(exporter, VerdanceBlockFamilies.PINK_STUCCO, Items.PINK_DYE);
        farmersDelightCompat(this, exporter);
    }
}
