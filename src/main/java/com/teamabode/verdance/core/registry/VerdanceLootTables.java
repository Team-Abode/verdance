package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class VerdanceLootTables {

    public static final ResourceKey<LootTable> ARCHAEOLOGY_TOWN_RUINS_COMMON = createKey("archaeology/town_ruins_common");
    public static final ResourceKey<LootTable> ARCHAEOLOGY_TOWN_RUINS_TREASURE = createKey("archaeology/town_ruins_treasure");

    private static ResourceKey<LootTable> createKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Verdance.id(name));
    }
}
