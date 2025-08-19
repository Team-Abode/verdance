package com.teamabode.verdance.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.consume.ExtinguishConsumeEffect;
import com.teamabode.verdance.common.consume.ReduceFireTimeConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VerdanceConsumeEffectTypes {
    public static final ConsumeEffect.Type<ExtinguishConsumeEffect> EXTINGUISH = register("extinguish", ExtinguishConsumeEffect.CODEC, ExtinguishConsumeEffect.PACKET_CODEC);
    public static final ConsumeEffect.Type<ReduceFireTimeConsumeEffect> REDUCE_FIRE_TIME = register("reduce_fire_time", ReduceFireTimeConsumeEffect.CODEC, ReduceFireTimeConsumeEffect.PACKET_CODEC);

    public static void register() {}

    private static <T extends ConsumeEffect> ConsumeEffect.Type<T> register(String name, MapCodec<T> codec, PacketCodec<RegistryByteBuf, T> networkCodec) {
        return Registry.register(Registries.CONSUME_EFFECT_TYPE, Verdance.id(name), new ConsumeEffect.Type<>(codec, networkCodec));
    }
}
