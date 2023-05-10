package com.teamabode.verdance.core.registry.misc;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class VerdanceGroupEvents {

    public static void register() {
        appendItemsBefore(CreativeModeTabs.BUILDING_BLOCKS, Items.CRIMSON_STEM,
                VerdanceBlocks.MULBERRY_LOG,
                VerdanceBlocks.MULBERRY_WOOD,
                VerdanceBlocks.STRIPPED_MULBERRY_LOG,
                VerdanceBlocks.STRIPPED_MULBERRY_WOOD,
                VerdanceBlocks.MULBERRY_PLANKS,
                VerdanceBlocks.MULBERRY_STAIRS,
                VerdanceBlocks.MULBERRY_SLAB,
                VerdanceBlocks.MULBERRY_FENCE,
                VerdanceBlocks.MULBERRY_FENCE_GATE,
                VerdanceBlocks.MULBERRY_DOOR,
                VerdanceBlocks.MULBERRY_TRAPDOOR,
                VerdanceBlocks.MULBERRY_PRESSURE_PLATE,
                VerdanceBlocks.MULBERRY_BUTTON
        );

        appendItemsAfter(CreativeModeTabs.FOOD_AND_DRINKS, Items.MELON_SLICE, VerdanceItems.CANTALOUPE_SLICE);
        appendItemsAfter(CreativeModeTabs.NATURAL_BLOCKS, Items.MELON, VerdanceBlocks.CANTALOUPE);
        appendItemsAfter(CreativeModeTabs.NATURAL_BLOCKS, Items.MELON_SEEDS, VerdanceItems.CANTALOUPE_SEEDS);

        appendItemsAfter(CreativeModeTabs.TOOLS_AND_UTILITIES, Items.MUSIC_DISC_OTHERSIDE, VerdanceItems.MUSIC_DISC_RANGE);
        appendItemsBefore(CreativeModeTabs.INGREDIENTS, Items.DISC_FRAGMENT_5, VerdanceItems.DISC_FRAGMENT_RANGE);

        appendItemsBefore(CreativeModeTabs.NATURAL_BLOCKS, Items.AZALEA, VerdanceBlocks.SHRUB);

        appendItemsAfter(CreativeModeTabs.COLORED_BLOCKS, Items.PINK_TERRACOTTA,
                VerdanceBlocks.WHITE_STUCCO,
                VerdanceBlocks.WHITE_STUCCO_STAIRS,
                VerdanceBlocks.WHITE_STUCCO_SLAB,
                VerdanceBlocks.WHITE_STUCCO_WALL
        );
    }


    private static void appendItemsAfter(CreativeModeTab tab, ItemLike target, ItemLike... appendedItems) {
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> {
            entries.addAfter(target, appendedItems);
        });
    }

    private static void appendItemsBefore(CreativeModeTab tab, ItemLike target, ItemLike... appendedItems) {
        ItemGroupEvents.modifyEntriesEvent(tab).register(entries -> {
            entries.addBefore(target, appendedItems);
        });
    }
}
