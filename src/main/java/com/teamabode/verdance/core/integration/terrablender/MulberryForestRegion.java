package com.teamabode.verdance.core.integration.terrablender;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.worldgen.VerdanceBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class MulberryForestRegion extends Region {
    public MulberryForestRegion() {
        super(Verdance.id("mulberry_forest"), RegionType.OVERWORLD, Verdance.CONFIG.getGroup("biomes").getIntProperty("mulberry_forest_weight"));
    }

    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.MEADOW, VerdanceBiomes.MULBERRY_FOREST);
        });
    }
}
