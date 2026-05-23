package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
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
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VerdanceRecipeProvider extends RecipeProvider {

    public VerdanceRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    private static void stucco(RecipeOutput output, BlockFamily family, TagKey<Item> dye) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), 8).requires(dye).requires(Items.CLAY, 4).requires(Ingredient.of(ItemTags.SAND), 4).group("stucco").unlockedBy(getHasName(Items.CLAY), has(Items.CLAY)).unlockedBy("has_sand", has(ItemTags.SAND)).save(output);

        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.STAIRS), family.getBaseBlock());
        stonecutterResultFromBase(output, RecipeCategory.BUILDING_BLOCKS, family.get(Variant.SLAB), family.getBaseBlock(), 2);
        stonecutterResultFromBase(output, RecipeCategory.MISC, family.get(Variant.WALL), family.getBaseBlock());
    }

    private static void cantaloupe(RecipeOutput exporter) {
        twoByTwoPacker(exporter, RecipeCategory.BUILDING_BLOCKS, VerdanceBlocks.CANTALOUPE.get(), VerdanceItems.CANTALOUPE_SLICE.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, VerdanceItems.CANTALOUPE_SEEDS.get()).requires(VerdanceItems.CANTALOUPE_SLICE.get()).unlockedBy(getHasName(VerdanceItems.CANTALOUPE_SLICE.get()), has(VerdanceItems.CANTALOUPE_SLICE.get())).save(exporter);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(VerdanceItems.CANTALOUPE_SLICE.get()), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE.get(), 0.25f, 200).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE.get())).save(exporter);
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(VerdanceItems.CANTALOUPE_SLICE.get()), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE.get(), 0.25f, 100).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE.get())).save(exporter, Verdance.id("grilled_cantaloupe_slice_from_smoking"));
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(VerdanceItems.CANTALOUPE_SLICE.get()), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE.get(), 0.25f, 600).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE.get())).save(exporter, Verdance.id("grilled_cantaloupe_slice_from_campfire_cooking"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, VerdanceItems.CANTALOUPE_JUICE.get()).requires(VerdanceItems.CANTALOUPE_SLICE.get(), 4).requires(Items.SUGAR).requires(Items.GLASS_BOTTLE).unlockedBy("has_cantaloupe_slice", has(VerdanceItems.CANTALOUPE_SLICE.get())).unlockedBy("has_sugar", has(Items.SUGAR)).unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE)).save(exporter);
        threeByThreePacker(exporter, RecipeCategory.MISC, VerdanceItems.MUSIC_DISC_RANGE.get(), VerdanceItems.DISC_FRAGMENT_RANGE.get());

        copySmithingTemplate(exporter, VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), VerdanceBlocks.WHITE_STUCCO.get());
        trimSmithing(exporter, VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE.get(), Verdance.id(getItemName(VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE.get()) + "_smithing_trim"));
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
        woodFromLogs(exporter, VerdanceBlocks.MULBERRY_WOOD.get(), VerdanceBlocks.MULBERRY_LOG.get());
        woodFromLogs(exporter, VerdanceBlocks.STRIPPED_MULBERRY_WOOD.get(), VerdanceBlocks.STRIPPED_MULBERRY_LOG.get());
        planksFromLog(exporter, VerdanceBlocks.MULBERRY_PLANKS.get(), VerdanceItemTags.MULBERRY_LOGS, 4);

        woodenBoat(exporter, VerdanceItems.MULBERRY_BOAT.get(), VerdanceBlocks.MULBERRY_PLANKS.get());
        chestBoat(exporter, VerdanceItems.MULBERRY_CHEST_BOAT.get(), VerdanceItems.MULBERRY_BOAT.get());

        cantaloupe(exporter);
        hangingSign(exporter, VerdanceItems.MULBERRY_HANGING_SIGN.get(), VerdanceBlocks.STRIPPED_MULBERRY_LOG.get());
        stucco(exporter, VerdanceBlockFamilies.WHITE_STUCCO, Tags.Items.DYES_WHITE);
        stucco(exporter, VerdanceBlockFamilies.LIGHT_GRAY_STUCCO, Tags.Items.DYES_LIGHT_GRAY);
        stucco(exporter, VerdanceBlockFamilies.GRAY_STUCCO, Tags.Items.DYES_GRAY);
        stucco(exporter, VerdanceBlockFamilies.BLACK_STUCCO, Tags.Items.DYES_BLACK);
        stucco(exporter, VerdanceBlockFamilies.BROWN_STUCCO, Tags.Items.DYES_BROWN);
        stucco(exporter, VerdanceBlockFamilies.RED_STUCCO, Tags.Items.DYES_RED);
        stucco(exporter, VerdanceBlockFamilies.ORANGE_STUCCO, Tags.Items.DYES_ORANGE);
        stucco(exporter, VerdanceBlockFamilies.YELLOW_STUCCO, Tags.Items.DYES_YELLOW);
        stucco(exporter, VerdanceBlockFamilies.LIME_STUCCO, Tags.Items.DYES_LIME);
        stucco(exporter, VerdanceBlockFamilies.GREEN_STUCCO, Tags.Items.DYES_GREEN);
        stucco(exporter, VerdanceBlockFamilies.CYAN_STUCCO, Tags.Items.DYES_CYAN);
        stucco(exporter, VerdanceBlockFamilies.LIGHT_BLUE_STUCCO, Tags.Items.DYES_LIGHT_BLUE);
        stucco(exporter, VerdanceBlockFamilies.BLUE_STUCCO, Tags.Items.DYES_BLUE);
        stucco(exporter, VerdanceBlockFamilies.PURPLE_STUCCO, Tags.Items.DYES_PURPLE);
        stucco(exporter, VerdanceBlockFamilies.MAGENTA_STUCCO, Tags.Items.DYES_MAGENTA);
        stucco(exporter, VerdanceBlockFamilies.PINK_STUCCO, Tags.Items.DYES_PINK);

        cushion(exporter, VerdanceBlocks.WHITE_CUSHION.get(), Blocks.WHITE_WOOL);
        cushion(exporter, VerdanceBlocks.LIGHT_GRAY_CUSHION.get(), Blocks.LIGHT_GRAY_WOOL);
        cushion(exporter, VerdanceBlocks.GRAY_CUSHION.get(), Blocks.GRAY_WOOL);
        cushion(exporter, VerdanceBlocks.BLACK_CUSHION.get(), Blocks.BLACK_WOOL);
        cushion(exporter, VerdanceBlocks.BROWN_CUSHION.get(), Blocks.BROWN_WOOL);
        cushion(exporter, VerdanceBlocks.RED_CUSHION.get(), Blocks.RED_WOOL);
        cushion(exporter, VerdanceBlocks.ORANGE_CUSHION.get(), Blocks.ORANGE_WOOL);
        cushion(exporter, VerdanceBlocks.YELLOW_CUSHION.get(), Blocks.YELLOW_WOOL);
        cushion(exporter, VerdanceBlocks.LIME_CUSHION.get(), Blocks.LIME_WOOL);
        cushion(exporter, VerdanceBlocks.GREEN_CUSHION.get(), Blocks.GREEN_WOOL);
        cushion(exporter, VerdanceBlocks.CYAN_CUSHION.get(), Blocks.CYAN_WOOL);
        cushion(exporter, VerdanceBlocks.LIGHT_BLUE_CUSHION.get(), Blocks.LIGHT_BLUE_WOOL);
        cushion(exporter, VerdanceBlocks.BLUE_CUSHION.get(), Blocks.BLUE_WOOL);
        cushion(exporter, VerdanceBlocks.PURPLE_CUSHION.get(), Blocks.PURPLE_WOOL);
        cushion(exporter, VerdanceBlocks.MAGENTA_CUSHION.get(), Blocks.MAGENTA_WOOL);
        cushion(exporter, VerdanceBlocks.PINK_CUSHION.get(), Blocks.PINK_WOOL);

        List<TagKey<Item>> dyes = List.of(
                Tags.Items.DYES_BLACK,
                Tags.Items.DYES_BLUE,
                Tags.Items.DYES_BROWN,
                Tags.Items.DYES_CYAN,
                Tags.Items.DYES_GRAY,
                Tags.Items.DYES_GREEN,
                Tags.Items.DYES_LIGHT_BLUE,
                Tags.Items.DYES_LIGHT_GRAY,
                Tags.Items.DYES_LIME,
                Tags.Items.DYES_MAGENTA,
                Tags.Items.DYES_ORANGE,
                Tags.Items.DYES_PINK,
                Tags.Items.DYES_PURPLE,
                Tags.Items.DYES_RED,
                Tags.Items.DYES_YELLOW,
                Tags.Items.DYES_WHITE
        );
        List<ItemLike> cushions = List.of(
                VerdanceBlocks.BLACK_CUSHION.get(),
                VerdanceBlocks.BLUE_CUSHION.get(),
                VerdanceBlocks.BROWN_CUSHION.get(),
                VerdanceBlocks.CYAN_CUSHION.get(),
                VerdanceBlocks.GRAY_CUSHION.get(),
                VerdanceBlocks.GREEN_CUSHION.get(),
                VerdanceBlocks.LIGHT_BLUE_CUSHION.get(),
                VerdanceBlocks.LIGHT_GRAY_CUSHION.get(),
                VerdanceBlocks.LIME_CUSHION.get(),
                VerdanceBlocks.MAGENTA_CUSHION.get(),
                VerdanceBlocks.ORANGE_CUSHION.get(),
                VerdanceBlocks.PINK_CUSHION.get(),
                VerdanceBlocks.PURPLE_CUSHION.get(),
                VerdanceBlocks.RED_CUSHION.get(),
                VerdanceBlocks.YELLOW_CUSHION.get(),
                VerdanceBlocks.WHITE_CUSHION.get()
        );
        offerDyeableRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, dyes, cushions, "cushion");
        dyeFromFlower(exporter, Items.PURPLE_DYE, VerdanceBlocks.VIOLET.get(), 1);
        dyeFromFlower(exporter, Items.MAGENTA_DYE, Blocks.SPORE_BLOSSOM, 2);
        dyeFromFlower(exporter, Items.YELLOW_DYE, VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get(), 1);
        dyeFromFlower(exporter, Items.PINK_DYE, VerdanceBlocks.PINK_FLOWERING_SHRUB.get(), 1);
    }
}
