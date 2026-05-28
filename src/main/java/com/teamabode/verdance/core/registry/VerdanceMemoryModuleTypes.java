package com.teamabode.verdance.core.registry;

import com.mojang.serialization.Codec;
import com.teamabode.verdance.Verdance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class VerdanceMemoryModuleTypes {
    public static final DeferredRegister<MemoryModuleType<?>> REGISTRY = DeferredRegister.create(Registries.MEMORY_MODULE_TYPE, Verdance.MOD_ID);

    public static final Supplier<MemoryModuleType<Unit>> IS_FLYING = register("is_flying");
    public static final Supplier<MemoryModuleType<Unit>>WANTS_TO_COCOON = register("wants_to_cocoon");
    public static final Supplier<MemoryModuleType<Unit>> WANTS_TO_LAND = register("wants_to_land", Codec.unit(Unit.INSTANCE));
    public static final Supplier<MemoryModuleType<Long>> LANDING_TIME = register("landing_time", Codec.LONG);
    public static final Supplier<MemoryModuleType<BlockPos>> NEAREST_LIGHT_SOURCE = register("nearest_light_source");

    public static <U> Supplier<MemoryModuleType<U>> register(String name) {
        return REGISTRY.register(name, () -> new MemoryModuleType<>(Optional.empty()));
    }

    public static <U> Supplier<MemoryModuleType<U>> register(String name, Codec<U> codec) {
        return REGISTRY.register(name, () -> new MemoryModuleType<>(Optional.of(codec)));
    }
}
