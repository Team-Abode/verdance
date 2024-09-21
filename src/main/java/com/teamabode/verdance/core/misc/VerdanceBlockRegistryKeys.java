package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.Verdance;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class VerdanceBlockRegistryKeys {

    public static final RegistryKey<Block> CANTALOUPE = createKey("cantaloupe");
    public static final RegistryKey<Block> CANTALOUPE_STEM = createKey("cantaloupe_stem");
    public static final RegistryKey<Block> ATTACHED_CANTALOUPE_STEM = createKey("attached_cantaloupe_stem");

    private static RegistryKey<Block> createKey(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Verdance.id(name));
    }
}
