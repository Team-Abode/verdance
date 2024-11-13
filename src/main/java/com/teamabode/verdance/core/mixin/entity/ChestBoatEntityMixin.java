package com.teamabode.verdance.core.mixin.entity;

import com.teamabode.verdance.core.registry.VerdanceBoatTypes;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChestBoatEntity.class)
public abstract class ChestBoatEntityMixin extends BoatEntity {

    public ChestBoatEntityMixin(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

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
