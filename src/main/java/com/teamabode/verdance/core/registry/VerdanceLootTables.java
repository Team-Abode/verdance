package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class VerdanceLootTables {

    public static final RegistryKey<LootTable> ARCHAEOLOGY_TOWN_RUINS_COMMON = createKey("archaeology/town_ruins_common");
    public static final RegistryKey<LootTable> ARCHAEOLOGY_TOWN_RUINS_TREASURE = createKey("archaeology/town_ruins_treasure");

    private static RegistryKey<LootTable> createKey(String name) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Verdance.id(name));
    }
}
