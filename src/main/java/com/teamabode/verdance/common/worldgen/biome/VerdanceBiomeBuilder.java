package com.teamabode.verdance.common.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.function.Consumer;

public abstract class VerdanceBiomeBuilder {
    protected static final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0f, 1.0f);

    public VerdanceBiomeBuilder() {

    }

    public abstract void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper);

    public abstract ResourceLocation getId();
}
