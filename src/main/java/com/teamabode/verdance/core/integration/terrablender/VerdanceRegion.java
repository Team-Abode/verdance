package com.teamabode.verdance.core.integration.terrablender;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.biome.VerdanceBiomeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class VerdanceRegion extends Region {
    private final VerdanceBiomeBuilder biomeBuilder;

    public VerdanceRegion(VerdanceBiomeBuilder biomeBuilder) {
        super(biomeBuilder.getId(), RegionType.OVERWORLD, Verdance.CONFIG.getGroup("biomes").getIntProperty(biomeBuilder.getId().getPath() + "_weight"));
        this.biomeBuilder = biomeBuilder;
    }

    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        biomeBuilder.addBiomes(mapper);
    }
}
