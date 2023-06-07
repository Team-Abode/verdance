package com.teamabode.verdance.core.mixin.general;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin {

    LightningBolt lightningBolt = LightningBolt.class.cast(this);
    @Shadow protected abstract BlockPos getStrikePosition();

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LightningBolt;powerLightningRod()V", shift = At.Shift.BEFORE))
    private void killShrubs(CallbackInfo ci) {
        BlockPos strikePos = this.getStrikePosition();
        Level level = lightningBolt.level();

        for (BlockPos pos : BlockPos.betweenClosed(strikePos.offset(-3, -3, -3), strikePos.offset(3, 3, 3))) {
            if (!level.canSeeSky(pos)) continue;

            if (level.getBlockState(pos).is(VerdanceBlocks.SHRUB)) {
                level.destroyBlock(pos, false);
                level.setBlockAndUpdate(pos, Blocks.DEAD_BUSH.defaultBlockState());
            }
        }
    }
}
