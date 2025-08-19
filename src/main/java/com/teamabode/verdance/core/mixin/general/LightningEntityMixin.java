package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin extends Entity {
    @Shadow protected abstract BlockPos getAffectedBlockPos();

    public LightningEntityMixin(EntityType<?> entityType, World level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LightningEntity;powerLightningRod()V", shift = At.Shift.AFTER))
    private void verdance$tick(CallbackInfo ci) {
        for (BlockPos pos : BlockPos.iterateOutwards(this.getAffectedBlockPos(), 4, 2, 4)) {
            BlockState state = getWorld().getBlockState(pos);

            if (state.isIn(VerdanceBlockTags.DESERT_BUSHES)) {
                getWorld().setBlockState(pos, Blocks.DEAD_BUSH.getDefaultState());
            }
        }
    }
}
