package com.teamabode.verdance.core.registry;

import com.mojang.serialization.Codec;
import com.teamabode.verdance.Verdance;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;
import java.util.Optional;

public class VerdanceMemoryModuleTypes {
    public static final MemoryModuleType<Unit> IS_FLYING = register("is_flying");
    public static final MemoryModuleType<Unit> WANTS_TO_COCOON = register("wants_to_cocoon");
    public static final MemoryModuleType<Unit> WANTS_TO_LAND = register("wants_to_land", Codec.unit(Unit.INSTANCE));
    public static final MemoryModuleType<Long> LANDING_TIME = register("landing_time", Codec.LONG);

    public static <U> MemoryModuleType<U> register(String name) {
        return Registry.register(Registries.MEMORY_MODULE_TYPE, Verdance.id(name), new MemoryModuleType<>(Optional.empty()));
    }

    public static <U> MemoryModuleType<U> register(String name, Codec<U> codec) {
        return Registry.register(Registries.MEMORY_MODULE_TYPE, Verdance.id(name), new MemoryModuleType<>(Optional.of(codec)));
    }

    public static void register() {

    }
}
