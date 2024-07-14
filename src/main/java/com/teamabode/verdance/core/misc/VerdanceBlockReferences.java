package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class VerdanceBlockReferences {

    public static final ResourceKey<Block> CANTALOUPE = createKey("cantaloupe");
    public static final ResourceKey<Block> CANTALOUPE_STEM = createKey("cantaloupe_stem");
    public static final ResourceKey<Block> ATTACHED_CANTALOUPE_STEM = createKey("attached_cantaloupe_stem");

    private static ResourceKey<Block> createKey(String name) {
        return ResourceKey.create(Registries.BLOCK, Verdance.id(name));
    }
}
