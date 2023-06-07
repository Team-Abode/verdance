package com.teamabode.verdance.common.entity.silkmoth.ai;

import com.teamabode.verdance.common.entity.silkmoth.SilkMoth;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;

public class SilkMothWanderGoal extends Goal {
    private final SilkMoth moth;

    public SilkMothWanderGoal(SilkMoth moth) {
        this.moth = moth;
    }

    public boolean canUse() {
        return moth.getNavigation().isDone() && moth.getRandom().nextFloat() < 0.1f;
    }

    public boolean canContinueToUse() {
        return moth.getNavigation().isInProgress();
    }

    public void start() {
        Vec3 navPos = this.getPosition();
        PathNavigation navigation = moth.getNavigation();

        if (navPos != null) {
            navigation.moveTo(navigation.createPath(BlockPos.containing(navPos), 1), 1.0f);
        }
    }

    public Vec3 getPosition() {
        Vec3 view = moth.getViewVector(0.0f);

        return HoverRandomPos.getPos(moth, 8, 8, view.x, view.z, (float) Math.toRadians(90.0f), 3, 1);
    }
}
