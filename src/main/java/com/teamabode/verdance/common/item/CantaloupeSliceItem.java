package com.teamabode.verdance.common.item;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.annotation.MethodsReturnNonnullByDefault;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@MethodsReturnNonnullByDefault
public class CantaloupeSliceItem extends Item {

    public CantaloupeSliceItem(net.minecraft.item.Item.Settings properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (!world.isClient()) {
            int fireTicks = user.getFireTicks();

            if (fireTicks > 0) {
                world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.25f, 1.0f);
                addCoolingParticles((ServerWorld) world, user);
                user.setFireTicks(Math.max(0, fireTicks - 60));
            }
        }
        return user.eatFood(world, stack);
    }

    public static void addCoolingParticles(ServerWorld level, LivingEntity user) {
        Box box = user.getBoundingBox();
        Vec3d center = box.getCenter();

        level.spawnParticles(ParticleTypes.SNOWFLAKE, center.x, center.y, center.z, 15, 0.5f, box.getYLength() / 2, 0.5f, 0.0d);
    }
}
