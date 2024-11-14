package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ChickenEntity.class)
public class ChickenEntityMixin {
    @Mutable
    @Shadow
    @Final
    private static Ingredient BREEDING_INGREDIENT;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void verdance$appendBreedingIngredient(CallbackInfo ci) {
        ArrayList<ItemStack> items = new ArrayList<>(List.of(BREEDING_INGREDIENT.getMatchingStacks()));
        items.add(new ItemStack(VerdanceItems.CANTALOUPE_SEEDS));
        BREEDING_INGREDIENT = Ingredient.ofStacks(items.toArray(ItemStack[]::new));
    }
}
