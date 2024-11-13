package com.teamabode.verdance.common.item;

import com.teamabode.verdance.core.registry.VerdanceCriteria;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class CantaloupeJuiceItem extends Item {

    public CantaloupeJuiceItem(Item.Settings properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);

        if (user instanceof ServerPlayerEntity serverPlayer) {
            Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            if (serverPlayer.isOnFire()) {
                VerdanceCriteria.EXTINGUISHED_WITH_CANTALOUPE_JUICE.trigger(serverPlayer);
            }
            serverPlayer.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if (!world.isClient() && user.isOnFire()) {
            world.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.25f, 1.0f);
            CantaloupeSliceItem.addCoolingParticles((ServerWorld) world, user);
            user.extinguish();
        }
        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
            if (!player.getInventory().insertStack(itemStack)) {
                player.dropItem(itemStack, false);
            }
        }
        return stack;
    }

    @Override
    public TypedActionResult<ItemStack> use(World level, PlayerEntity player, Hand usedHand) {
        return ItemUsage.consumeHeldItem(level, player, usedHand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 40;
    }
}
