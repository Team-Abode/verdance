package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.family.BlockFamily.Variant;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.StonecuttingRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VerdanceRecipeProvider extends FabricRecipeProvider {

    public VerdanceRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    private static void stucco(RecipeExporter output, BlockFamily family, Item dye) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), 8).input(dye).input(Items.CLAY, 4).input(Ingredient.fromTag(ItemTags.SAND), 4).group("stucco").criterion(hasItem(Items.CLAY), conditionsFromItem(Items.CLAY)).criterion("has_sand", conditionsFromTag(ItemTags.SAND)).offerTo(output);

        offerStonecuttingRecipe(output, RecipeCategory.BUILDING_BLOCKS, family.getVariant(Variant.STAIRS), family.getBaseBlock());
        offerStonecuttingRecipe(output, RecipeCategory.BUILDING_BLOCKS, family.getVariant(Variant.SLAB), family.getBaseBlock(), 2);
        offerStonecuttingRecipe(output, RecipeCategory.BUILDING_BLOCKS, family.getVariant(Variant.WALL), family.getBaseBlock());
    }

    private static void cantaloupe(RecipeExporter exporter) {
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, VerdanceBlocks.CANTALOUPE, VerdanceItems.CANTALOUPE_SLICE);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, VerdanceItems.CANTALOUPE_SEEDS)
                .input(VerdanceItems.CANTALOUPE_SLICE)
                .criterion(hasItem(VerdanceItems.CANTALOUPE_SLICE), conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                .offerTo(exporter);

        CookingRecipeJsonBuilder.createSmelting(
                        Ingredient.ofItems(VerdanceItems.CANTALOUPE_SLICE),
                        RecipeCategory.FOOD,
                        VerdanceItems.GRILLED_CANTALOUPE_SLICE,
                        0.25f,
                        200)
                .criterion("has_cantaloupe_slice", conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                .offerTo(exporter, Verdance.id("grilled_cantaloupe"));
        CookingRecipeJsonBuilder.createSmoking(
                        Ingredient.ofItems(VerdanceItems.CANTALOUPE_SLICE),
                        RecipeCategory.FOOD,
                        VerdanceItems.GRILLED_CANTALOUPE_SLICE,
                        0.25f,
                        100)
                .criterion("has_cantaloupe_slice", conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                .offerTo(exporter, Verdance.id("grilled_cantaloupe_slice_from_smoking"));
        CookingRecipeJsonBuilder.createCampfireCooking(
                        Ingredient.ofItems(VerdanceItems.CANTALOUPE_SLICE),
                        RecipeCategory.FOOD,
                        VerdanceItems.GRILLED_CANTALOUPE_SLICE,
                        0.25f,
                        600)
                .criterion("has_cantaloupe_slice", conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                .offerTo(exporter, Verdance.id("grilled_cantaloupe_slice_from_campfire_cooking"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, VerdanceItems.CANTALOUPE_JUICE)
                .input(VerdanceItems.CANTALOUPE_SLICE, 4)
                .input(Items.SUGAR)
                .input(Items.GLASS_BOTTLE)
                .criterion("has_cantaloupe_slice", conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                .criterion("has_sugar", conditionsFromItem(Items.SUGAR))
                .criterion("has_glass_bottle", conditionsFromItem(Items.GLASS_BOTTLE))
                .offerTo(exporter, Verdance.id("cantaloupe_juice"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, VerdanceItems.MUSIC_DISC_RANGE)
                .input(VerdanceItems.DISC_FRAGMENT_RANGE, 9)
                .criterion("has_disc_fragment_range", conditionsFromItem(VerdanceItems.DISC_FRAGMENT_RANGE))
                .offerTo(exporter, Verdance.id("music_disc_range"));

        offerSmithingTemplateCopyingRecipe(exporter, VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE, VerdanceBlocks.WHITE_STUCCO);
        offerSmithingTrimRecipe(
                exporter,
                VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE,
                Verdance.id(getItemPath(VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE) + "_smithing_trim")
        );
    }

    public static void offerStonecuttingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible result, ItemConvertible material) {
        offerStonecuttingRecipe(exporter, category, result, material, 1);
    }

    public static void offerStonecuttingRecipe(RecipeExporter exporter, RecipeCategory category, ItemConvertible result, ItemConvertible material, int resultCount) {
        StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(material), category, result, resultCount).criterion(RecipeProvider.hasItem(material), RecipeProvider.conditionsFromItem(material)).offerTo(exporter, Verdance.id(convertBetween(result, material) + "_stonecutting"));
    }

    private static void cushion(RecipeExporter exporter, ItemConvertible cushion, ItemConvertible wool) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, cushion, 2)
                .criterion(hasItem(wool), conditionsFromItem(wool))
                .input('W', wool)
                .input('#', ItemTags.PLANKS)
                .pattern("WW")
                .pattern("##")
                .offerTo(exporter, Verdance.id(getItemPath(cushion)));
    }

    private static void dyeFromFlower(RecipeExporter exporter, Item dye, Block flower, int count) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, dye, count)
                .input(flower)
                .criterion(hasItem(flower), conditionsFromItem(flower))
                .group(getItemPath(dye))
                .offerTo(exporter, Verdance.id(convertBetween(dye, flower)));
    }

    public void generate(RecipeExporter exporter) {
        VerdanceBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipes).forEach(family -> RecipeProvider.generateFamily(exporter, family, FeatureSet.of(FeatureFlags.VANILLA)));
        offerBarkBlockRecipe(exporter, VerdanceBlocks.MULBERRY_WOOD, VerdanceBlocks.MULBERRY_LOG);
        offerBarkBlockRecipe(exporter, VerdanceBlocks.STRIPPED_MULBERRY_WOOD, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
        offerPlanksRecipe2(exporter, VerdanceBlocks.MULBERRY_PLANKS, VerdanceItemTags.MULBERRY_LOGS, 4);

        offerBoatRecipe(exporter, VerdanceItems.MULBERRY_BOAT, VerdanceBlocks.MULBERRY_PLANKS);
        offerChestBoatRecipe(exporter, VerdanceItems.MULBERRY_CHEST_BOAT, VerdanceItems.MULBERRY_BOAT);

        cantaloupe(exporter);
        offerHangingSignRecipe(exporter, VerdanceItems.MULBERRY_HANGING_SIGN, VerdanceBlocks.STRIPPED_MULBERRY_LOG);
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

        List<Item> dyes = List.of(
                Items.BLACK_DYE, Items.BLUE_DYE, Items.BROWN_DYE, Items.CYAN_DYE,
                Items.GRAY_DYE, Items.GREEN_DYE, Items.LIGHT_BLUE_DYE, Items.LIGHT_GRAY_DYE,
                Items.LIME_DYE, Items.MAGENTA_DYE, Items.ORANGE_DYE, Items.PINK_DYE,
                Items.PURPLE_DYE, Items.RED_DYE, Items.YELLOW_DYE, Items.WHITE_DYE
        );
        List<Item> cushions = List.of(
                VerdanceBlocks.BLACK_CUSHION.asItem(), VerdanceBlocks.BLUE_CUSHION.asItem(), VerdanceBlocks.BROWN_CUSHION.asItem(), VerdanceBlocks.CYAN_CUSHION.asItem(),
                VerdanceBlocks.GRAY_CUSHION.asItem(), VerdanceBlocks.GREEN_CUSHION.asItem(), VerdanceBlocks.LIGHT_BLUE_CUSHION.asItem(), VerdanceBlocks.LIGHT_GRAY_CUSHION.asItem(),
                VerdanceBlocks.LIME_CUSHION.asItem(), VerdanceBlocks.MAGENTA_CUSHION.asItem(), VerdanceBlocks.ORANGE_CUSHION.asItem(), VerdanceBlocks.PINK_CUSHION.asItem(),
                VerdanceBlocks.PURPLE_CUSHION.asItem(), VerdanceBlocks.RED_CUSHION.asItem(), VerdanceBlocks.YELLOW_CUSHION.asItem(), VerdanceBlocks.WHITE_CUSHION.asItem()
        );
        offerDyeableRecipes(exporter, dyes, cushions, "cushion");
        dyeFromFlower(exporter, Items.PURPLE_DYE, VerdanceBlocks.VIOLET, 1);
        dyeFromFlower(exporter, Items.MAGENTA_DYE, Blocks.SPORE_BLOSSOM, 2);
        dyeFromFlower(exporter, Items.YELLOW_DYE, VerdanceBlocks.YELLOW_FLOWERING_SHRUB, 1);
        dyeFromFlower(exporter, Items.PINK_DYE, VerdanceBlocks.PINK_FLOWERING_SHRUB, 1);
    }
}
