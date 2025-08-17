package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningEntityMixin extends Entity {
    @Shadow protected abstract BlockPos getStrikePosition();

    public LightningEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LightningBolt;powerLightningRod()V", shift = At.Shift.AFTER))
    private void verdance$tick(CallbackInfo ci) {
        for (BlockPos pos : BlockPos.withinManhattan(this.getStrikePosition(), 4, 2, 4)) {
            BlockState state = level().getBlockState(pos);

            if (state.is(VerdanceBlockTags.SHRUBS)) {
                level().setBlockAndUpdate(pos, Blocks.DEAD_BUSH.defaultBlockState());
            }
        }
    }
}
