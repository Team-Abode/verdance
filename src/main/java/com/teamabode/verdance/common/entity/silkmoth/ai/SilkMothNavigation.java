package com.teamabode.verdance.common.entity.silkmoth.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.level.Level;

public class SilkMothNavigation extends FlyingPathNavigation {
    public SilkMothNavigation(Mob mob, Level level) {
        super(mob, level);
    }

    public boolean isStableDestination(BlockPos pos) {
        return !this.level.getBlockState(pos.below()).isAir();
    }
}
