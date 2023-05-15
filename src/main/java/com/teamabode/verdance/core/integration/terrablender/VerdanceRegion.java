package com.teamabode.verdance.core.integration.terrablender;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.misc.VerdanceBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class VerdanceRegion extends Region {
    public VerdanceRegion() {
        super(new ResourceLocation(Verdance.MOD_ID, "default"), RegionType.OVERWORLD, 3);
    }

    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(Biomes.DESERT, VerdanceBiomes.SHRUBLANDS);
            builder.replaceBiome(Biomes.MEADOW, VerdanceBiomes.MULBERRY_FOREST);
        });
    }
}
