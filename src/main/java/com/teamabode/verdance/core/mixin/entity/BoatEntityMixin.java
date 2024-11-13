package com.teamabode.verdance.core.mixin.entity;

import com.teamabode.verdance.core.registry.VerdanceBoatTypes;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin {

    @Shadow public abstract BoatEntity.Type getVariant();

    @Inject(
            method = "asItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void verdance$asItem(CallbackInfoReturnable<Item> cir) {
        if (this.getVariant() == VerdanceBoatTypes.MULBERRY) {
            cir.setReturnValue(VerdanceItems.MULBERRY_BOAT);
        }
    }
}
