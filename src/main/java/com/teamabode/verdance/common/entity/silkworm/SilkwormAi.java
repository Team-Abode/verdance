package com.teamabode.verdance.common.entity.silkworm;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.schedule.Activity;

public class SilkwormAi {

    public static Brain<Silkworm> createBrain(Brain<Silkworm> brain) {


        return brain;
    }

    public static void addCoreActivities(Brain<Silkworm> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(1.0f),
                new MoveToTargetSink(),
                new LookAtTargetSink(45, 900)
        ));
    }

    public static void addIdleActivities(Brain<Silkworm> brain) {

    }
}
