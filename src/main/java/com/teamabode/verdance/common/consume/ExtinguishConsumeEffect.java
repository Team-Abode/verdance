package com.teamabode.verdance.common.consume;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.core.registry.VerdanceConsumeEffectTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public record ExtinguishConsumeEffect() implements ConsumeEffect {
    public static final MapCodec<ExtinguishConsumeEffect> CODEC = MapCodec.unit(new ExtinguishConsumeEffect());
    public static final PacketCodec<RegistryByteBuf, ExtinguishConsumeEffect> PACKET_CODEC = PacketCodec.unit(new ExtinguishConsumeEffect());

    @Override
    public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
        int fireTicks = user.getFireTicks();

        if (fireTicks > 0) {
            user.extinguish();
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.25f, 1.0f);
            ReduceFireTimeConsumeEffect.spawnCoolingParticles((ServerWorld) world, user);
        }
        return fireTicks > 0;
    }

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return VerdanceConsumeEffectTypes.EXTINGUISH;
    }
}
