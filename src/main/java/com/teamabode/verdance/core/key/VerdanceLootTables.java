package com.teamabode.verdance.core.key;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public class VerdanceLootTables {

    public static final ResourceKey<LootTable> GHOST_TOWN_ARCHAEOLOGY_COMMON = create("archaeology/ghost_town_common");
    public static final ResourceKey<LootTable> GHOST_TOWN_ARCHAEOLOGY_RARE = create("archaeology/ghost_town_rare");
    public static final ResourceKey<LootTable> GHOST_TOWN_ARCHAEOLOGY_FARM = create("archaeology/ghost_town_farm");

    public static ResourceKey<LootTable> create(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Verdance.id(name));
    }
}
