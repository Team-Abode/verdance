package com.teamabode.verdance.core.event;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = Verdance.MOD_ID)
public class CreativeModeTabEvents {

    @SubscribeEvent
    private static void onBuildTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            insertAfter(
                    event,
                    Items.CHERRY_BUTTON,
                    VerdanceBlocks.MULBERRY_LOG.get(),
                    VerdanceBlocks.MULBERRY_WOOD.get(),
                    VerdanceBlocks.STRIPPED_MULBERRY_LOG.get(),
                    VerdanceBlocks.STRIPPED_MULBERRY_WOOD.get(),
                    VerdanceBlocks.MULBERRY_PLANKS.get(),
                    VerdanceBlocks.MULBERRY_STAIRS.get(),
                    VerdanceBlocks.MULBERRY_SLAB.get(),
                    VerdanceBlocks.MULBERRY_FENCE.get(),
                    VerdanceBlocks.MULBERRY_FENCE_GATE.get(),
                    VerdanceBlocks.MULBERRY_DOOR.get(),
                    VerdanceBlocks.MULBERRY_TRAPDOOR.get(),
                    VerdanceBlocks.MULBERRY_PRESSURE_PLATE.get(),
                    VerdanceBlocks.MULBERRY_BUTTON.get()
            );
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            insertAfter(
                    event,
                    Items.CHERRY_CHEST_BOAT,
                    VerdanceItems.MULBERRY_BOAT.get(),
                    VerdanceItems.MULBERRY_CHEST_BOAT.get()
            );
            insertBefore(
                    event,
                    Items.MUSIC_DISC_5,
                    VerdanceItems.MUSIC_DISC_RANGE.get()
            );
        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            insertAfter(
                    event,
                    Items.CHERRY_HANGING_SIGN,
                    VerdanceItems.MULBERRY_SIGN.get(),
                    VerdanceItems.MULBERRY_HANGING_SIGN.get()
            );
            insertAfter(
                    event,
                    Items.PINK_BED,
                    VerdanceBlocks.WHITE_CUSHION.get(),
                    VerdanceBlocks.LIGHT_GRAY_CUSHION.get(),
                    VerdanceBlocks.GRAY_CUSHION.get(),
                    VerdanceBlocks.BLACK_CUSHION.get(),
                    VerdanceBlocks.BROWN_CUSHION.get(),
                    VerdanceBlocks.RED_CUSHION.get(),
                    VerdanceBlocks.ORANGE_CUSHION.get(),
                    VerdanceBlocks.YELLOW_CUSHION.get(),
                    VerdanceBlocks.LIME_CUSHION.get(),
                    VerdanceBlocks.GREEN_CUSHION.get(),
                    VerdanceBlocks.CYAN_CUSHION.get(),
                    VerdanceBlocks.LIGHT_BLUE_CUSHION.get(),
                    VerdanceBlocks.BLUE_CUSHION.get(),
                    VerdanceBlocks.PURPLE_CUSHION.get(),
                    VerdanceBlocks.MAGENTA_CUSHION.get(),
                    VerdanceBlocks.PINK_CUSHION.get()
            );
        }
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            insertAfter(event, Items.GLOW_BERRIES, VerdanceItems.MULBERRY.get());
            insertAfter(event, Items.MELON_SLICE, VerdanceItems.CANTALOUPE_SLICE.get(), VerdanceItems.GRILLED_CANTALOUPE_SLICE.get());
            insertAfter(event, Items.HONEY_BOTTLE, VerdanceItems.CANTALOUPE_JUICE.get());
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            insertAfter(event, Items.CHERRY_LOG, VerdanceBlocks.MULBERRY_LOG.get());
            insertAfter(event, Items.MELON, VerdanceBlocks.CANTALOUPE.get());
            insertAfter(event, Items.MELON_SEEDS, VerdanceItems.CANTALOUPE_SEEDS.get());
            insertAfter(event, Items.CHERRY_LEAVES, VerdanceBlocks.MULBERRY_LEAVES.get(), VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get());
            insertAfter(event, Items.CHERRY_SAPLING, VerdanceItems.MULBERRY.get());
            insertAfter(event, Items.FROGSPAWN, VerdanceBlocks.SILKWORM_EGGS.get());
            insertAfter(event, Items.CORNFLOWER, VerdanceBlocks.VIOLET.get());

            insertBefore(event, Items.DEAD_BUSH, VerdanceBlocks.SHRUB.get(), VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get(), VerdanceBlocks.PINK_FLOWERING_SHRUB.get());
        }
        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
            insertAfter(
                    event,
                    Items.PINK_TERRACOTTA,
                    VerdanceBlocks.WHITE_STUCCO.get(),
                    VerdanceBlocks.WHITE_STUCCO_STAIRS.get(),
                    VerdanceBlocks.WHITE_STUCCO_SLAB.get(),
                    VerdanceBlocks.WHITE_STUCCO_WALL.get(),
                    VerdanceBlocks.LIGHT_GRAY_STUCCO.get(),
                    VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS.get(),
                    VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB.get(),
                    VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL.get(),
                    VerdanceBlocks.GRAY_STUCCO.get(),
                    VerdanceBlocks.GRAY_STUCCO_STAIRS.get(),
                    VerdanceBlocks.GRAY_STUCCO_SLAB.get(),
                    VerdanceBlocks.GRAY_STUCCO_WALL.get(),
                    VerdanceBlocks.BLACK_STUCCO.get(),
                    VerdanceBlocks.BLACK_STUCCO_STAIRS.get(),
                    VerdanceBlocks.BLACK_STUCCO_SLAB.get(),
                    VerdanceBlocks.BLACK_STUCCO_WALL.get(),
                    VerdanceBlocks.BROWN_STUCCO.get(),
                    VerdanceBlocks.BROWN_STUCCO_STAIRS.get(),
                    VerdanceBlocks.BROWN_STUCCO_SLAB.get(),
                    VerdanceBlocks.BROWN_STUCCO_WALL.get(),
                    VerdanceBlocks.RED_STUCCO.get(),
                    VerdanceBlocks.RED_STUCCO_STAIRS.get(),
                    VerdanceBlocks.RED_STUCCO_SLAB.get(),
                    VerdanceBlocks.RED_STUCCO_WALL.get(),
                    VerdanceBlocks.ORANGE_STUCCO.get(),
                    VerdanceBlocks.ORANGE_STUCCO_STAIRS.get(),
                    VerdanceBlocks.ORANGE_STUCCO_SLAB.get(),
                    VerdanceBlocks.ORANGE_STUCCO_WALL.get(),
                    VerdanceBlocks.YELLOW_STUCCO.get(),
                    VerdanceBlocks.YELLOW_STUCCO_STAIRS.get(),
                    VerdanceBlocks.YELLOW_STUCCO_SLAB.get(),
                    VerdanceBlocks.YELLOW_STUCCO_WALL.get(),
                    VerdanceBlocks.LIME_STUCCO.get(),
                    VerdanceBlocks.LIME_STUCCO_STAIRS.get(),
                    VerdanceBlocks.LIME_STUCCO_SLAB.get(),
                    VerdanceBlocks.LIME_STUCCO_WALL.get(),
                    VerdanceBlocks.GREEN_STUCCO.get(),
                    VerdanceBlocks.GREEN_STUCCO_STAIRS.get(),
                    VerdanceBlocks.GREEN_STUCCO_SLAB.get(),
                    VerdanceBlocks.GREEN_STUCCO_WALL.get(),
                    VerdanceBlocks.CYAN_STUCCO.get(),
                    VerdanceBlocks.CYAN_STUCCO_STAIRS.get(),
                    VerdanceBlocks.CYAN_STUCCO_SLAB.get(),
                    VerdanceBlocks.CYAN_STUCCO_WALL.get(),
                    VerdanceBlocks.LIGHT_BLUE_STUCCO.get(),
                    VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS.get(),
                    VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB.get(),
                    VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL.get(),
                    VerdanceBlocks.BLUE_STUCCO.get(),
                    VerdanceBlocks.BLUE_STUCCO_STAIRS.get(),
                    VerdanceBlocks.BLUE_STUCCO_SLAB.get(),
                    VerdanceBlocks.BLUE_STUCCO_WALL.get(),
                    VerdanceBlocks.PURPLE_STUCCO.get(),
                    VerdanceBlocks.PURPLE_STUCCO_STAIRS.get(),
                    VerdanceBlocks.PURPLE_STUCCO_SLAB.get(),
                    VerdanceBlocks.PURPLE_STUCCO_WALL.get(),
                    VerdanceBlocks.MAGENTA_STUCCO.get(),
                    VerdanceBlocks.MAGENTA_STUCCO_STAIRS.get(),
                    VerdanceBlocks.MAGENTA_STUCCO_SLAB.get(),
                    VerdanceBlocks.MAGENTA_STUCCO_WALL.get(),
                    VerdanceBlocks.PINK_STUCCO.get(),
                    VerdanceBlocks.PINK_STUCCO_STAIRS.get(),
                    VerdanceBlocks.PINK_STUCCO_SLAB.get(),
                    VerdanceBlocks.PINK_STUCCO_WALL.get()
            );
            insertAfter(
                    event,
                    Items.PINK_BED,
                    VerdanceBlocks.WHITE_CUSHION.get(),
                    VerdanceBlocks.LIGHT_GRAY_CUSHION.get(),
                    VerdanceBlocks.GRAY_CUSHION.get(),
                    VerdanceBlocks.BLACK_CUSHION.get(),
                    VerdanceBlocks.BROWN_CUSHION.get(),
                    VerdanceBlocks.RED_CUSHION.get(),
                    VerdanceBlocks.ORANGE_CUSHION.get(),
                    VerdanceBlocks.YELLOW_CUSHION.get(),
                    VerdanceBlocks.LIME_CUSHION.get(),
                    VerdanceBlocks.GREEN_CUSHION.get(),
                    VerdanceBlocks.CYAN_CUSHION.get(),
                    VerdanceBlocks.LIGHT_BLUE_CUSHION.get(),
                    VerdanceBlocks.BLUE_CUSHION.get(),
                    VerdanceBlocks.PURPLE_CUSHION.get(),
                    VerdanceBlocks.MAGENTA_CUSHION.get(),
                    VerdanceBlocks.PINK_CUSHION.get()
            );
        }
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            insertAfter(event, Items.CAULDRON, VerdanceBlocks.WHITE_CUSHION.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            insertAfter(event, Items.DISC_FRAGMENT_5, VerdanceItems.DISC_FRAGMENT_RANGE.get());
            insertBefore(event, Items.ANGLER_POTTERY_SHERD, VerdanceItems.ABODE_POTTERY_SHERD.get());
            insertAfter(event, Items.FRIEND_POTTERY_SHERD, VerdanceItems.FRILLS_POTTERY_SHERD.get());
            insertBefore(event, Items.PLENTY_POTTERY_SHERD, VerdanceItems.PITCH_POTTERY_SHERD.get());
            insertBefore(event, Items.PRIZE_POTTERY_SHERD, VerdanceItems.PRICKLE_POTTERY_SHERD.get());
            insertAfter(event, Items.SNORT_POTTERY_SHERD, VerdanceItems.SPIRIT_POTTERY_SHERD.get(), VerdanceItems.TRAP_POTTERY_SHERD.get());
            insertAfter(event, Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        }
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            insertBefore(
                    event,
                    Items.SILVERFISH_SPAWN_EGG,
                    VerdanceItems.SILK_MOTH_SPAWN_EGG.get(),
                    VerdanceItems.SILKWORM_SPAWN_EGG.get()
            );
        }
    }

    public static void insertAfter(BuildCreativeModeTabContentsEvent event, Item existing, ItemLike... newItems) {
        for (int i = 0; i < newItems.length; i++) {
            event.insertAfter(
                    i > 0 ? newItems[i - 1].asItem().getDefaultInstance() : existing.getDefaultInstance(),
                    newItems[i].asItem().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
    }

    public static void insertBefore(BuildCreativeModeTabContentsEvent event, Item existing, ItemLike... newItems) {
        for (int i = 0; i < newItems.length; i++) {
            event.insertAfter(
                    i > 0 ? newItems[i - 1].asItem().getDefaultInstance() : existing.getDefaultInstance(),
                    newItems[i].asItem().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
            );
        }
    }
}
