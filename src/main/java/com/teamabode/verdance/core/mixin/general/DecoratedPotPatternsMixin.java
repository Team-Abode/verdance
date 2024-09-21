package com.teamabode.verdance.core.mixin.general;


import com.teamabode.verdance.core.registry.VerdanceDecoratedPotPatterns;
import net.minecraft.block.DecoratedPotPattern;
import net.minecraft.block.DecoratedPotPatterns;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotPatterns.class)
public class DecoratedPotPatternsMixin {

    @Inject(method = "fromSherd", at = @At("HEAD"), cancellable = true)
    private static void verdance$fromSherd(Item item, CallbackInfoReturnable<RegistryKey<DecoratedPotPattern>> cir) {
        if (VerdanceDecoratedPotPatterns.SHERD_TO_PATTERN.containsKey(item)) {
            cir.setReturnValue(VerdanceDecoratedPotPatterns.SHERD_TO_PATTERN.get(item));
        }
    }
}
