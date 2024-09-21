package com.teamabode.verdance.core.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class VerdanceBiomeTags {

    public static final TagKey<Biome> HAS_CANTALOUPE = create("has_cantaloupe");
    public static final TagKey<Biome> HAS_GOLDEN_WOLF = create("has_golden_wolf");
    public static final TagKey<Biome> HAS_VIOLET = create("has_violet");
    public static final TagKey<Biome> HAS_TOWN_RUINS = create("has_structure/town_ruins");

    private static TagKey<Biome> create(String name) {
        return TagKey.of(RegistryKeys.BIOME, Verdance.id(name));
    }
}
