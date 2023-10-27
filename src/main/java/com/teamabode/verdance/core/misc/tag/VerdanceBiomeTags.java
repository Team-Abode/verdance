package com.teamabode.verdance.core.misc.tag;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class VerdanceBiomeTags {

    public static final TagKey<Biome> HAS_CANTALOUPE = create("has_cantaloupe");

    private static TagKey<Biome> create(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(Verdance.MOD_ID, name));
    }
}
