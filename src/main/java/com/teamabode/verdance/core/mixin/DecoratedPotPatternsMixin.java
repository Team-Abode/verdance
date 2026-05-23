package com.teamabode.verdance.core.mixin;

import com.teamabode.verdance.core.registry.VerdanceDecoratedPotPatterns;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternsMixin {

    @Inject(method = "getPatternFromItem", at = @At("HEAD"), cancellable = true)
    private static void verdance$getPatternFromItem(Item item, CallbackInfoReturnable<ResourceKey<DecoratedPotPattern>> cir) {
        if (item == VerdanceItems.ABODE_POTTERY_SHERD.get()) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.ABODE.getKey());
        }
        if (item == VerdanceItems.FRILLS_POTTERY_SHERD.get()) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.FRILLS.getKey());
        }
        if (item == VerdanceItems.PITCH_POTTERY_SHERD.get()) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.PITCH.getKey());
        }
        if (item == VerdanceItems.PRICKLE_POTTERY_SHERD.get()) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.PRICKLE.getKey());
        }
        if (item == VerdanceItems.SPIRIT_POTTERY_SHERD.get()) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.SPIRIT.getKey());
        }
        if (item == VerdanceItems.TRAP_POTTERY_SHERD.get()) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.TRAP.getKey());
        }
    }
}
