package com.teamabode.verdance.core.registry;

import com.mojang.serialization.Codec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.MulberryTrunkPlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class VerdanceTrunkPlacerTypes {

    public static final TrunkPlacerType<MulberryTrunkPlacer> MULBERRY_TRUNK_PLACER = register("mulberry_trunk_placer", MulberryTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String name, Codec<P> codec) {
        return Registry.register(Registries.TRUNK_PLACER_TYPE, Verdance.id(name), new TrunkPlacerType<>(codec));
    }

    public static void register() {

    }
}
