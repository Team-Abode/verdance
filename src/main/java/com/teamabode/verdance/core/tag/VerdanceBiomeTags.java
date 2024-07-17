package com.teamabode.verdance.core.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class VerdanceBiomeTags {

    public static final TagKey<Biome> HAS_CANTALOUPE = create("has_cantaloupe");
    public static final TagKey<Biome> HAS_GOLDEN_WOLF = create("has_golden_wolf");
    public static final TagKey<Biome> HAS_VIOLET = create("has_violet");

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, Verdance.id(name));
    }
}
