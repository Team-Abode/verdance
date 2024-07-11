package com.teamabode.verdance.core.registry;

import com.mojang.serialization.Codec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.util.CocoonPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;

public class VerdanceMemoryModuleTypes {
    public static final MemoryModuleType<Unit> IS_FLYING = register("is_flying");
    public static final MemoryModuleType<Unit> WANTS_TO_COCOON = register("wants_to_cocoon");

    public static <U> MemoryModuleType<U> register(String name) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Verdance.id(name), new MemoryModuleType<>(Optional.empty()));
    }

    public static <U> MemoryModuleType<U> register(String name, Codec<U> codec) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Verdance.id(name), new MemoryModuleType<>(Optional.of(codec)));
    }

    public static void register() {

    }
}
