package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {


    @Inject(
            method = "supports",
            at = @At("HEAD"),
            cancellable = true
    )
    private void verdance$supports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        BlockEntityType<?> $this = BlockEntityType.class.cast(this);
        if (state.isOf(VerdanceBlocks.MULBERRY_SIGN) || state.isOf(VerdanceBlocks.MULBERRY_WALL_SIGN)) {
            cir.setReturnValue($this == BlockEntityType.SIGN);
        }
        if (state.isOf(VerdanceBlocks.MULBERRY_HANGING_SIGN) || state.isOf(VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN)) {
            cir.setReturnValue($this == BlockEntityType.HANGING_SIGN);
        }
    }
}
