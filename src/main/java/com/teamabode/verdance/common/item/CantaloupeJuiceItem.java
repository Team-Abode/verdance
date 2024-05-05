package com.teamabode.verdance.common.item;

import com.teamabode.verdance.core.integration.compat.CompatItem;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CantaloupeJuiceItem extends CompatItem {

    public CantaloupeJuiceItem(Properties properties) {
        super("farmersdelight", properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        if (user instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, stack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }
        if (!level.isClientSide()) {
            level.playSound(null, user.blockPosition(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 0.25f, 1.0f);
            this.addCoolingParticles((ServerLevel) level, user);
            user.extinguishFire();
        }
        if (user instanceof Player player && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
        if (user instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack itemStack = new ItemStack(Items.GLASS_BOTTLE);
            if (!player.getInventory().add(itemStack)) {
                player.drop(itemStack, false);
            }
        }
        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        return ItemUtils.startUsingInstantly(level, player, usedHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(itemStack, tooltipContext, components, flag);
        components.add(Component.translatable("verdance.tooltip.cantaloupe_juice").withStyle(ChatFormatting.BLUE));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 40;
    }

    private void addCoolingParticles(ServerLevel level, LivingEntity user) {
        AABB box = user.getBoundingBox();
        Vec3 center = box.getCenter();

        level.sendParticles(ParticleTypes.SNOWFLAKE, center.x, center.y, center.z, 15, 0.5f, box.getYsize() / 2, 0.5f, 0.0d);
    }
}
