package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.data.server.recipe.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VerdanceRecipeProvider extends FabricRecipeProvider {

    public VerdanceRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    private static void stucco(RecipeOutput output, BlockFamily family, TagKey<Item> dye) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), 8).requires(dye).requires(Items.CLAY, 4).requires(Ingredient.of(ItemTags.SAND), 4).group("stucco").unlockedBy(getHasName(Items.CLAY), has(Items.CLAY)).unlockedBy("has_sand", has(ItemTags.SAND)).save(output);

        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.STAIRS), family.getBaseBlock());
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.SLAB), family.getBaseBlock(), 2);
        stonecutterResultFromBase(output, RecipeCategory.MISC, family.get(Variant.WALL), family.getBaseBlock());
    }

    private static void cantaloupe(RecipeOutput exporter) {
        twoByTwoPacker(exporter, RecipeCategory.BUILDING_BLOCKS, VerdanceBlocks.CANTALOUPE, VerdanceItems.CANTALOUPE_SLICE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VerdanceItems.CANTALOUPE_SEEDS).requires(VerdanceItems.CANTALOUPE_SLICE).unlockedBy(getHasName(VerdanceItems.CANTALOUPE_SLICE), has(VerdanceItems.CANTALOUPE_SLICE)).save(exporter);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VerdanceItems.CANTALOUPE_SLICE), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE, 0.25f, 200).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE)).save(exporter);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(VerdanceItems.CANTALOUPE_SLICE), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE, 0.25f, 100).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE)).save(exporter, Verdance.id("grilled_cantaloupe_slice_from_smoking"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(VerdanceItems.CANTALOUPE_SLICE), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE, 0.25f, 600).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE)).save(exporter, Verdance.id("grilled_cantaloupe_slice_from_campfire_cooking"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VerdanceItems.CANTALOUPE_JUICE).requires(VerdanceItems.CANTALOUPE_SLICE, 4).requires(Items.SUGAR).requires(Items.GLASS_BOTTLE).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE)).unlockedBy("has_sugar", has(Items.SUGAR)).unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE)).save(exporter);
        threeByThreePacker(exporter, RecipeCategory.MISC, VerdanceItems.MUSIC_DISC_RANGE, VerdanceItems.DISC_FRAGMENT_RANGE);

        copySmithingTemplate(exporter, VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE, VerdanceBlocks.WHITE_STUCCO);
        trimSmithing(exporter, VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE, Verdance.id(getItemName(VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE) + "_smithing_trim"));
    }

    private static void offerDyeableRecipes(RecipeOutput exporter, RecipeCategory category, List<TagKey<Item>> dyes, List<ItemLike> dyeables, String group) {
        for (int i = 0; i < dyes.size(); ++i) {
            TagKey<Item> dye = dyes.get(i);
            final ItemLike item = dyeables.get(i);
            ShapelessRecipeBuilder.shapeless(category, item).requires(dye).requires(Ingredient.of(dyeables.stream().filter(dyeable -> !dyeable.equals(item)).map(ItemStack::new))).group(group).unlockedBy("has_needed_dye", has(dye)).save(exporter, Verdance.id("dye_" + getItemName(item)));
        }
    }

    public static void stonecutterResultFromBase(RecipeOutput exporter, RecipeCategory category, ItemLike result, ItemLike material) {
        stonecutterResultFromBase(exporter, category, result, material, 1);
    }

    public static void stonecutterResultFromBase(RecipeOutput exporter, RecipeCategory category, ItemLike result, ItemLike material, int resultCount) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(material), category, result, resultCount).unlockedBy(RecipeProvider.getHasName(material), RecipeProvider.has(material)).save(exporter, Verdance.id(getConversionRecipeName(result, material) + "_stonecutting"));
    }

    private static void cushion(RecipeOutput exporter, ItemLike cushion, ItemLike wool) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, cushion, 2).unlockedBy(getHasName(wool), has(wool)).define('W', wool).define('#', ItemTags.PLANKS).pattern("WW").pattern("##").group("cushion").save(exporter);
    }

    private static void dyeFromFlower(RecipeOutput exporter, Item dye, Block flower, int count) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, dye, count).requires(flower).unlockedBy(getHasName(flower), has(flower)).group(getItemName(dye)).save(exporter, Verdance.id(getConversionRecipeName(dye, flower)));
    }

    public void buildRecipes(RecipeOutput exporter) {
        VerdanceBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach(family -> RecipeProvider.generateRecipes(exporter, family, FeatureFlagSet.of(FeatureFlags.VANILLA)));
        woodFromLogs(exporter, VerdanceBlocks.MULBERRY_WOOD, VerdanceBlocks.MULBERRY_LOG);
        woodFromLogs(exporter, VerdanceBlocks.STRIPPED_MULBERRY_WOOD, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        planksFromLog(exporter, VerdanceBlocks.MULBERRY_PLANKS, VerdanceItemTags.MULBERRY_LOGS, 4);

        woodenBoat(exporter, VerdanceItems.MULBERRY_BOAT, VerdanceBlocks.MULBERRY_PLANKS);
        chestBoat(exporter, VerdanceItems.MULBERRY_CHEST_BOAT, VerdanceItems.MULBERRY_BOAT);

        cantaloupe(exporter);
        hangingSign(exporter, VerdanceItems.MULBERRY_HANGING_SIGN, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        stucco(exporter, VerdanceBlockFamilies.WHITE_STUCCO, ConventionalItemTags.WHITE_DYES);
        stucco(exporter, VerdanceBlockFamilies.LIGHT_GRAY_STUCCO, ConventionalItemTags.LIGHT_GRAY_DYES);
        stucco(exporter, VerdanceBlockFamilies.GRAY_STUCCO, ConventionalItemTags.GRAY_DYES);
        stucco(exporter, VerdanceBlockFamilies.BLACK_STUCCO, ConventionalItemTags.BLACK_DYES);
        stucco(exporter, VerdanceBlockFamilies.BROWN_STUCCO, ConventionalItemTags.BROWN_DYES);
        stucco(exporter, VerdanceBlockFamilies.RED_STUCCO, ConventionalItemTags.RED_DYES);
        stucco(exporter, VerdanceBlockFamilies.ORANGE_STUCCO, ConventionalItemTags.ORANGE_DYES);
        stucco(exporter, VerdanceBlockFamilies.YELLOW_STUCCO, ConventionalItemTags.YELLOW_DYES);
        stucco(exporter, VerdanceBlockFamilies.LIME_STUCCO, ConventionalItemTags.LIME_DYES);
        stucco(exporter, VerdanceBlockFamilies.GREEN_STUCCO, ConventionalItemTags.GREEN_DYES);
        stucco(exporter, VerdanceBlockFamilies.CYAN_STUCCO, ConventionalItemTags.CYAN_DYES);
        stucco(exporter, VerdanceBlockFamilies.LIGHT_BLUE_STUCCO, ConventionalItemTags.LIGHT_BLUE_DYES);
        stucco(exporter, VerdanceBlockFamilies.BLUE_STUCCO, ConventionalItemTags.BLUE_DYES);
        stucco(exporter, VerdanceBlockFamilies.PURPLE_STUCCO, ConventionalItemTags.PURPLE_DYES);
        stucco(exporter, VerdanceBlockFamilies.MAGENTA_STUCCO, ConventionalItemTags.MAGENTA_DYES);
        stucco(exporter, VerdanceBlockFamilies.PINK_STUCCO, ConventionalItemTags.PINK_DYES);

        cushion(exporter, VerdanceBlocks.WHITE_CUSHION, Blocks.WHITE_WOOL);
        cushion(exporter, VerdanceBlocks.LIGHT_GRAY_CUSHION, Blocks.LIGHT_GRAY_WOOL);
        cushion(exporter, VerdanceBlocks.GRAY_CUSHION, Blocks.GRAY_WOOL);
        cushion(exporter, VerdanceBlocks.BLACK_CUSHION, Blocks.BLACK_WOOL);
        cushion(exporter, VerdanceBlocks.BROWN_CUSHION, Blocks.BROWN_WOOL);
        cushion(exporter, VerdanceBlocks.RED_CUSHION, Blocks.RED_WOOL);
        cushion(exporter, VerdanceBlocks.ORANGE_CUSHION, Blocks.ORANGE_WOOL);
        cushion(exporter, VerdanceBlocks.YELLOW_CUSHION, Blocks.YELLOW_WOOL);
        cushion(exporter, VerdanceBlocks.LIME_CUSHION, Blocks.LIME_WOOL);
        cushion(exporter, VerdanceBlocks.GREEN_CUSHION, Blocks.GREEN_WOOL);
        cushion(exporter, VerdanceBlocks.CYAN_CUSHION, Blocks.CYAN_WOOL);
        cushion(exporter, VerdanceBlocks.LIGHT_BLUE_CUSHION, Blocks.LIGHT_BLUE_WOOL);
        cushion(exporter, VerdanceBlocks.BLUE_CUSHION, Blocks.BLUE_WOOL);
        cushion(exporter, VerdanceBlocks.PURPLE_CUSHION, Blocks.PURPLE_WOOL);
        cushion(exporter, VerdanceBlocks.MAGENTA_CUSHION, Blocks.MAGENTA_WOOL);
        cushion(exporter, VerdanceBlocks.PINK_CUSHION, Blocks.PINK_WOOL);

        List<TagKey<Item>> dyes = List.of(ConventionalItemTags.BLACK_DYES, ConventionalItemTags.BLUE_DYES, ConventionalItemTags.BROWN_DYES, ConventionalItemTags.CYAN_DYES, ConventionalItemTags.GRAY_DYES, ConventionalItemTags.GREEN_DYES, ConventionalItemTags.LIGHT_BLUE_DYES, ConventionalItemTags.LIGHT_GRAY_DYES, ConventionalItemTags.LIME_DYES, ConventionalItemTags.MAGENTA_DYES, ConventionalItemTags.ORANGE_DYES, ConventionalItemTags.PINK_DYES, ConventionalItemTags.PURPLE_DYES, ConventionalItemTags.RED_DYES, ConventionalItemTags.YELLOW_DYES, ConventionalItemTags.WHITE_DYES);
        List<ItemLike> cushions = List.of(VerdanceBlocks.BLACK_CUSHION, VerdanceBlocks.BLUE_CUSHION, VerdanceBlocks.BROWN_CUSHION, VerdanceBlocks.CYAN_CUSHION, VerdanceBlocks.GRAY_CUSHION, VerdanceBlocks.GREEN_CUSHION, VerdanceBlocks.LIGHT_BLUE_CUSHION, VerdanceBlocks.LIGHT_GRAY_CUSHION, VerdanceBlocks.LIME_CUSHION, VerdanceBlocks.MAGENTA_CUSHION, VerdanceBlocks.ORANGE_CUSHION, VerdanceBlocks.PINK_CUSHION, VerdanceBlocks.PURPLE_CUSHION, VerdanceBlocks.RED_CUSHION, VerdanceBlocks.YELLOW_CUSHION, VerdanceBlocks.WHITE_CUSHION);
        offerDyeableRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, dyes, cushions, "cushion");
        dyeFromFlower(exporter, Items.PURPLE_DYE, VerdanceBlocks.VIOLET, 1);
        dyeFromFlower(exporter, Items.MAGENTA_DYE, Blocks.SPORE_BLOSSOM, 2);
        dyeFromFlower(exporter, Items.YELLOW_DYE, VerdanceBlocks.YELLOW_FLOWERING_SHRUB, 1);
        dyeFromFlower(exporter, Items.PINK_DYE, VerdanceBlocks.PINK_FLOWERING_SHRUB, 1);
    }
}
