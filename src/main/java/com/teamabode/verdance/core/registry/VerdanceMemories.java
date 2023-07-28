package com.teamabode.verdance.core.registry;

import com.mojang.serialization.Codec;
import com.teamabode.verdance.Verdance;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;

public class VerdanceMemories {
    public static final MemoryModuleType<Integer> FLIGHT_COOLDOWN_TICKS = register("flight_cooldown_ticks", Codec.INT);
    public static final MemoryModuleType<Unit> IS_POLLINATING = register("is_pollinating", Codec.unit(Unit.INSTANCE));
    public static final MemoryModuleType<GlobalPos> POLLINATE_TARGET = register("pollinate_target", GlobalPos.CODEC);
    public static final MemoryModuleType<Integer> SAPLINGS_POLLINATED = register("saplings_pollinated", Codec.INT);

    public static <U> MemoryModuleType<U> register(String name) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Verdance.id(name), new MemoryModuleType<>(Optional.empty()));
    }

    public static <U> MemoryModuleType<U> register(String name, Codec<U> codec) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, Verdance.id(name), new MemoryModuleType<>(Optional.of(codec)));
    }

    public static void register() {

    }
}
