package com.teamabode.verdance.datagen.server;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.VerdanceTrimPatterns;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.family.BlockFamily.Variant;
import net.minecraft.data.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VerdanceRecipeProvider extends FabricRecipeProvider {

    public VerdanceRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new VerdanceRecipeGenerator(registries, exporter);
    }

    public static class VerdanceRecipeGenerator extends RecipeGenerator {

        public VerdanceRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            super(registries, exporter);
        }

        @Override
        public void generate() {
            VerdanceBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipes)
                    .forEach(family -> this.generateFamily(family, FeatureSet.of(FeatureFlags.VANILLA)));

            // Mulberry
            this.offerBarkBlockRecipe(VerdanceBlocks.MULBERRY_WOOD, VerdanceBlocks.MULBERRY_LOG);
            this.offerBarkBlockRecipe(VerdanceBlocks.STRIPPED_MULBERRY_WOOD, VerdanceBlocks.STRIPPED_MULBERRY_LOG);

            this.offerPlanksRecipe2(VerdanceBlocks.MULBERRY_PLANKS, VerdanceItemTags.MULBERRY_LOGS, 4);

            this.offerBoatRecipe(VerdanceItems.MULBERRY_BOAT, VerdanceBlocks.MULBERRY_PLANKS);
            this.offerChestBoatRecipe(VerdanceItems.MULBERRY_CHEST_BOAT, VerdanceItems.MULBERRY_BOAT);

            this.offerHangingSignRecipe(VerdanceItems.MULBERRY_HANGING_SIGN, VerdanceBlocks.STRIPPED_MULBERRY_LOG);

            // Cantaloupe
            this.offer2x2CompactingRecipe(RecipeCategory.BUILDING_BLOCKS, VerdanceBlocks.CANTALOUPE, VerdanceItems.CANTALOUPE_SLICE);
            this.createShapeless(RecipeCategory.MISC, VerdanceItems.CANTALOUPE_SEEDS)
                    .input(VerdanceItems.CANTALOUPE_SLICE)
                    .criterion("has_cantaloupe_slice", this.conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                    .offerTo(this.exporter);

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(VerdanceItems.CANTALOUPE_SLICE), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE, 0.25f, 200)
                    .criterion("has_cantaloupe_slice", conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                    .offerTo(exporter);

            CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItems(VerdanceItems.CANTALOUPE_SLICE), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE, 0.25f, 100)
                    .criterion("has_cantaloupe_slice", conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                    .offerTo(exporter, this.createKey("grilled_cantaloupe_slice_from_smoking"));

            CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItems(VerdanceItems.CANTALOUPE_SLICE), RecipeCategory.FOOD, VerdanceItems.GRILLED_CANTALOUPE_SLICE, 0.25f, 600)
                    .criterion("has_cantaloupe_slice", conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                    .offerTo(exporter, this.createKey("grilled_cantaloupe_slice_from_campfire_cooking"));

            this.createShapeless(RecipeCategory.FOOD, VerdanceItems.CANTALOUPE_JUICE)
                    .input(VerdanceItems.CANTALOUPE_SLICE, 4)
                    .input(Items.SUGAR)
                    .input(Items.GLASS_BOTTLE)
                    .criterion("has_cantaloupe_slice", this.conditionsFromItem(VerdanceItems.CANTALOUPE_SLICE))
                    .criterion("has_sugar", this.conditionsFromItem(Items.GLASS_BOTTLE))
                    .criterion("has_glass_bottle", this.conditionsFromItem(Items.GLASS_BOTTLE))
                    .offerTo(this.exporter);

            // Archaeology Treasures
            this.offerCompactingRecipe(RecipeCategory.MISC, VerdanceItems.MUSIC_DISC_RANGE, VerdanceItems.DISC_FRAGMENT_RANGE);

            this.offerSmithingTemplateCopyingRecipe(VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE, VerdanceBlocks.WHITE_STUCCO);

            this.offerSmithingTrimRecipe(
                    VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE,
                    VerdanceTrimPatterns.HERITAGE,
                    this.createKey(RecipeGenerator.getItemPath(VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE) + "_smithing_trim")
            );

            // Stucco
            this.offerStuccoRecipes(VerdanceBlockFamilies.WHITE_STUCCO, ConventionalItemTags.WHITE_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.LIGHT_GRAY_STUCCO, ConventionalItemTags.LIGHT_GRAY_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.GRAY_STUCCO, ConventionalItemTags.GRAY_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.BLACK_STUCCO, ConventionalItemTags.BLACK_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.BROWN_STUCCO, ConventionalItemTags.BROWN_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.RED_STUCCO, ConventionalItemTags.RED_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.ORANGE_STUCCO, ConventionalItemTags.ORANGE_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.YELLOW_STUCCO, ConventionalItemTags.YELLOW_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.LIME_STUCCO, ConventionalItemTags.LIME_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.GREEN_STUCCO, ConventionalItemTags.GREEN_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.CYAN_STUCCO, ConventionalItemTags.CYAN_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.LIGHT_BLUE_STUCCO, ConventionalItemTags.LIGHT_BLUE_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.BLUE_STUCCO, ConventionalItemTags.BLUE_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.PURPLE_STUCCO, ConventionalItemTags.PURPLE_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.MAGENTA_STUCCO, ConventionalItemTags.MAGENTA_DYES);
            this.offerStuccoRecipes(VerdanceBlockFamilies.PINK_STUCCO, ConventionalItemTags.PINK_DYES);

            // Cushion
            this.offerCushionRecipe(VerdanceBlocks.WHITE_CUSHION, Blocks.WHITE_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.LIGHT_GRAY_CUSHION, Blocks.LIGHT_GRAY_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.GRAY_CUSHION, Blocks.GRAY_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.BLACK_CUSHION, Blocks.BLACK_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.BROWN_CUSHION, Blocks.BROWN_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.RED_CUSHION, Blocks.RED_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.ORANGE_CUSHION, Blocks.ORANGE_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.YELLOW_CUSHION, Blocks.YELLOW_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.LIME_CUSHION, Blocks.LIME_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.GREEN_CUSHION, Blocks.GREEN_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.CYAN_CUSHION, Blocks.CYAN_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.LIGHT_BLUE_CUSHION, Blocks.LIGHT_BLUE_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.BLUE_CUSHION, Blocks.BLUE_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.PURPLE_CUSHION, Blocks.PURPLE_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.MAGENTA_CUSHION, Blocks.MAGENTA_WOOL);
            this.offerCushionRecipe(VerdanceBlocks.PINK_CUSHION, Blocks.PINK_WOOL);

            List<TagKey<Item>> dyes = List.of(ConventionalItemTags.BLACK_DYES, ConventionalItemTags.BLUE_DYES, ConventionalItemTags.BROWN_DYES, ConventionalItemTags.CYAN_DYES, ConventionalItemTags.GRAY_DYES, ConventionalItemTags.GREEN_DYES, ConventionalItemTags.LIGHT_BLUE_DYES, ConventionalItemTags.LIGHT_GRAY_DYES, ConventionalItemTags.LIME_DYES, ConventionalItemTags.MAGENTA_DYES, ConventionalItemTags.ORANGE_DYES, ConventionalItemTags.PINK_DYES, ConventionalItemTags.PURPLE_DYES, ConventionalItemTags.RED_DYES, ConventionalItemTags.YELLOW_DYES, ConventionalItemTags.WHITE_DYES);
            List<ItemConvertible> cushions = List.of(VerdanceBlocks.BLACK_CUSHION, VerdanceBlocks.BLUE_CUSHION, VerdanceBlocks.BROWN_CUSHION, VerdanceBlocks.CYAN_CUSHION, VerdanceBlocks.GRAY_CUSHION, VerdanceBlocks.GREEN_CUSHION, VerdanceBlocks.LIGHT_BLUE_CUSHION, VerdanceBlocks.LIGHT_GRAY_CUSHION, VerdanceBlocks.LIME_CUSHION, VerdanceBlocks.MAGENTA_CUSHION, VerdanceBlocks.ORANGE_CUSHION, VerdanceBlocks.PINK_CUSHION, VerdanceBlocks.PURPLE_CUSHION, VerdanceBlocks.RED_CUSHION, VerdanceBlocks.YELLOW_CUSHION, VerdanceBlocks.WHITE_CUSHION);
            this.offerCustomDyeableRecipes(RecipeCategory.BUILDING_BLOCKS, dyes, cushions, "cushion");
            
            this.offerDyeFromFlowerRecipe(Items.PURPLE_DYE, VerdanceBlocks.VIOLET, 1);
            this.offerDyeFromFlowerRecipe(Items.MAGENTA_DYE, Blocks.SPORE_BLOSSOM, 2);
            this.offerDyeFromFlowerRecipe(Items.YELLOW_DYE, VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH, 1);
            this.offerDyeFromFlowerRecipe(Items.PINK_DYE, VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH, 1);
        }

        private void offerCustomStonecuttingRecipe(RecipeCategory category, ItemConvertible result, ItemConvertible material) {
            this.offerCustomStonecuttingRecipe(category, result, material, 1);
        }

        private void offerCustomStonecuttingRecipe(RecipeCategory category, ItemConvertible result, ItemConvertible material, int resultCount) {
            var name = RecipeGenerator.convertBetween(result, material) + "_stonecutting";

            StonecuttingRecipeJsonBuilder.createStonecutting(Ingredient.ofItems(material), category, result, resultCount)
                    .criterion(RecipeGenerator.hasItem(material), this.conditionsFromItem(material))
                    .offerTo(exporter, this.createKey(name));
        }

        private void offerStuccoRecipes(BlockFamily family, TagKey<Item> dyes) {
            this.createShapeless(RecipeCategory.BUILDING_BLOCKS, family.getBaseBlock(), 8)
                    .input(dyes)
                    .input(Items.CLAY)
                    .input(Items.CLAY)
                    .input(Items.CLAY)
                    .input(Items.CLAY)
                    .input(ItemTags.SAND)
                    .input(ItemTags.SAND)
                    .input(ItemTags.SAND)
                    .input(ItemTags.SAND)
                    .group("stucco")
                    .criterion("has_clay", this.conditionsFromItem(Items.CLAY))
                    .criterion("has_sand", this.conditionsFromTag(ItemTags.SAND))
                    .offerTo(this.exporter);

            this.offerCustomStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, family.getVariant(Variant.STAIRS), family.getBaseBlock());
            this.offerCustomStonecuttingRecipe(RecipeCategory.BUILDING_BLOCKS, family.getVariant(Variant.SLAB), family.getBaseBlock(), 2);
            this.offerCustomStonecuttingRecipe(RecipeCategory.MISC, family.getVariant(Variant.WALL), family.getBaseBlock());
        }

        private void offerCushionRecipe(ItemConvertible cushion, ItemConvertible wool) {
            this.createShaped(RecipeCategory.DECORATIONS, cushion, 2)
                    .criterion(RecipeGenerator.hasItem(wool), this.conditionsFromItem(wool))
                    .input('W', wool)
                    .input('#', ItemTags.PLANKS)
                    .pattern("WW")
                    .pattern("##")
                    .group("cushion")
                    .offerTo(this.exporter);
        }

        private void offerCustomDyeableRecipes(RecipeCategory category, List<TagKey<Item>> dyeTags, List<ItemConvertible> dyeables, String group) {
            for (int i = 0; i < dyeTags.size(); ++i) {
                TagKey<Item> dyeTag = dyeTags.get(i);
                final ItemConvertible item = dyeables.get(i);

                var trueDyeables = dyeables.stream().filter(dyeable -> !dyeable.equals(item));

                this.createShapeless(category, item)
                    .input(dyeTag)
                    .input(Ingredient.ofItems(trueDyeables))
                    .group(group)
                    .criterion("has_needed_dye", this.conditionsFromTag(dyeTag))
                    .offerTo(this.exporter, this.createKey("dye_" + RecipeGenerator.getItemPath(item)));
            }
        }
        
        private void offerDyeFromFlowerRecipe(Item dyeItem, Block flowerBlock, int count) {
            this.createShapeless(RecipeCategory.MISC, dyeItem, count)
                    .input(flowerBlock)
                    .criterion(RecipeGenerator.hasItem(flowerBlock), this.conditionsFromItem(flowerBlock))
                    .group(RecipeGenerator.getItemPath(dyeItem))
                    .offerTo(exporter, this.createKey(RecipeGenerator.convertBetween(dyeItem, flowerBlock)));
        }

        private RegistryKey<Recipe<?>> createKey(String name) {
            return RegistryKey.of(RegistryKeys.RECIPE, Verdance.id(name));
        };
    }

    @Override
    public String getName() {
        return "Recipe Provider";
    }
}
