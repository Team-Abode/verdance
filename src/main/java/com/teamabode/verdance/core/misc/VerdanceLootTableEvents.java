package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class VerdanceLootTableEvents {
    private static final LootPoolEntry CANTALOUPE_SEEDS = ItemEntry.builder(VerdanceItems.CANTALOUPE_SEEDS)
            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 4.0f)))
            .weight(5)
            .build();

    public static void register() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.ABANDONED_MINESHAFT_CHEST)) {
                tableBuilder.modifyPools(builder -> builder.with(CANTALOUPE_SEEDS));
            }
            if (id.equals(LootTables.SIMPLE_DUNGEON_CHEST)) {
                tableBuilder.modifyPools(builder -> builder.with(CANTALOUPE_SEEDS));
            }
            if (id.equals(LootTables.WOODLAND_MANSION_CHEST)) {
                tableBuilder.modifyPools(builder -> builder.with(CANTALOUPE_SEEDS));
            }
        });
    }
}
