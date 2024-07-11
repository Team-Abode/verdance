package com.teamabode.verdance.core.key;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class VerdancePlacedFeatures {

    public static final ResourceKey<PlacedFeature> PATCH_CANTALOUPE = create("patch_cantaloupe");

    private static ResourceKey<PlacedFeature> create(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Verdance.id(name));
    }
}
