package com.teamabode.verdance.core.registry;

import com.mojang.serialization.Codec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.feature.MulberryTrunkPlacer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class VerdanceTrunkPlacerTypes {

    public static final TrunkPlacerType<MulberryTrunkPlacer> MULBERRY_TRUNK_PLACER = register("mulberry_trunk_placer", MulberryTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Verdance.id(name), new TrunkPlacerType<>(codec));
    }

    public static void register() {

    }
}
