package com.teamabode.verdance.core.misc.reference;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class VerdanceItemReferences {

    public static final ResourceKey<Item> CANTALOUPE_SEEDS = createKey("cantaloupe_seeds");

    private static ResourceKey<Item> createKey(String name) {
        return ResourceKey.create(Registries.ITEM, Verdance.id(name));
    }
}
