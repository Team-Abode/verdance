package com.teamabode.verdance.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.MulberryTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VerdanceTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> REGISTRY = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, Verdance.MOD_ID);

    public static final Supplier<TrunkPlacerType<MulberryTrunkPlacer>> MULBERRY_TRUNK_PLACER = register("mulberry_trunk_placer", MulberryTrunkPlacer.CODEC);

    private static <P extends TrunkPlacer> Supplier<TrunkPlacerType<P>> register(String name, MapCodec<P> codec) {
        return REGISTRY.register(name, () -> new TrunkPlacerType<>(codec));
    }

    public static void register() {

    }
}
