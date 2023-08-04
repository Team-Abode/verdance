package com.teamabode.verdance.core.integration.terrablender;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.worldgen.VerdanceBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ShrublandsRegion extends Region {

    public ShrublandsRegion() {
        super(Verdance.id("shrublands"), RegionType.OVERWORLD, Verdance.CONFIG.getGroup("biomes").getIntProperty("shrublands_weight"));
    }

    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.SPARSE_JUNGLE, VerdanceBiomes.SHRUBLANDS);
            builder.replaceBiome(Biomes.SAVANNA, VerdanceBiomes.SHRUBLANDS);
        });
    }
}
