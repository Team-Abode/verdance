package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class VerdanceLootTableEvents {
    private static final LootPoolEntryContainer CANTALOUPE_SEEDS = LootItem.lootTableItem(VerdanceItems.CANTALOUPE_SEEDS)
            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 4.0f)))
            .setWeight(5)
            .build();

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (key.equals(BuiltInLootTables.ABANDONED_MINESHAFT)) {
                tableBuilder.modifyPools(builder -> builder.with(CANTALOUPE_SEEDS));
            }
            if (key.equals(BuiltInLootTables.SIMPLE_DUNGEON)) {
                tableBuilder.modifyPools(builder -> builder.with(CANTALOUPE_SEEDS));
            }
            if (key.equals(BuiltInLootTables.WOODLAND_MANSION)) {
                tableBuilder.modifyPools(builder -> builder.with(CANTALOUPE_SEEDS));
            }
        });
    }
}
