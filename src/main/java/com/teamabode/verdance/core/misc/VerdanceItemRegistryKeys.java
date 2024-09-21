package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.Verdance;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class VerdanceItemRegistryKeys {

    public static final RegistryKey<Item> CANTALOUPE_SEEDS = createKey("cantaloupe_seeds");

    private static RegistryKey<Item> createKey(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Verdance.id(name));
    }
}
