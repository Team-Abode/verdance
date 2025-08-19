package com.teamabode.verdance.common.consume;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamabode.verdance.core.registry.VerdanceConsumeEffectTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public record ReduceFireTimeConsumeEffect(float reductionTimeSeconds) implements ConsumeEffect {
    public static final MapCodec<ReduceFireTimeConsumeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.POSITIVE_FLOAT.fieldOf("reduction_time_seconds").forGetter(ReduceFireTimeConsumeEffect::reductionTimeSeconds)
    ).apply(instance, ReduceFireTimeConsumeEffect::new));

    public static final PacketCodec<RegistryByteBuf, ReduceFireTimeConsumeEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, ReduceFireTimeConsumeEffect::reductionTimeSeconds,
            ReduceFireTimeConsumeEffect::new
    );

    public int getReductionTimeTicks() {
        return Math.round(reductionTimeSeconds * 20);
    }

    @Override
    public boolean onConsume(World world, ItemStack stack, LivingEntity user) {
        int fireTicks = user.getFireTicks();

        if (fireTicks > 0) {
            user.setFireTicks(fireTicks - this.getReductionTimeTicks());
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.25f, 1.0f);
            spawnCoolingParticles((ServerWorld) world, user);
        }
        return fireTicks > 0;
    }

    @Override
    public Type<? extends ConsumeEffect> getType() {
        return VerdanceConsumeEffectTypes.REDUCE_FIRE_TIME;
    }

    public static void spawnCoolingParticles(ServerWorld world, LivingEntity user) {
        Box box = user.getBoundingBox();
        Vec3d center = box.getCenter();

        world.spawnParticles(ParticleTypes.SNOWFLAKE, center.x, center.y, center.z, 15, 0.5f, box.getLengthY() / 2, 0.5f, 0.0d);
    }
}
