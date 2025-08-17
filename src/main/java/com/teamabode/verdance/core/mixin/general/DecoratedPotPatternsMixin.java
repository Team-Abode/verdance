package com.teamabode.verdance.core.mixin.general;


import com.teamabode.verdance.core.registry.VerdanceDecoratedPotPatterns;
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
        if (VerdanceDecoratedPotPatterns.SHERD_TO_PATTERN.containsKey(item)) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.SHERD_TO_PATTERN.get(item));
        }
    }
}
