package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(ParrotEntity.class)
public class ParrotEntityMixin {
    @Shadow
    @Final
    private static Set<Item> TAMING_INGREDIENTS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void verdance$appendTamingIngredient(CallbackInfo ci) {
        TAMING_INGREDIENTS.add(VerdanceItems.CANTALOUPE_SEEDS);
    }
}
