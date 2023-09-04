package com.teamabode.verdance.common.entity.silk_moth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.AnimalPanic;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.schedule.Activity;

public class TestAi {

    public static Brain<SilkMoth> createBrain(Brain<SilkMoth> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        addSleepActivities(brain);
        addLayEggsActivities(brain);

        brain.setDefaultActivity(Activity.IDLE);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        return brain;
    }

    private static void addCoreActivities(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(1.0f),
                new AnimalPanic(1.5f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink()
        ));
    }

    private static void addIdleActivities(Brain<SilkMoth> brain) {

    }

    private static void addSleepActivities(Brain<SilkMoth> brain) {

    }

    private static void addLayEggsActivities(Brain<SilkMoth> brain) {

    }
}
