package com.teamabode.verdance.common.entity.silkworm;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.common.entity.silkworm.behavior.SearchForCocoonTask;
import com.teamabode.verdance.common.entity.silkworm.behavior.TurnIntoCocoonTask;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceActivities;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import com.teamabode.verdance.core.registry.VerdanceSensorTypes;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.FleeTask;
import net.minecraft.entity.ai.brain.task.LookAroundTask;
import net.minecraft.entity.ai.brain.task.MoveToTargetTask;
import net.minecraft.entity.ai.brain.task.RandomTask;
import net.minecraft.entity.ai.brain.task.StayAboveWaterTask;
import net.minecraft.entity.ai.brain.task.StrollTask;
import net.minecraft.entity.ai.brain.task.TemptTask;
import net.minecraft.entity.ai.brain.task.TemptationCooldownTask;
import net.minecraft.entity.ai.brain.task.WaitTask;
import net.minecraft.recipe.Ingredient;
import java.util.List;
import java.util.Set;

public class SilkwormBrain {
    public static final List<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.IS_PANICKING,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.MOBS,
            MemoryModuleType.VISIBLE_MOBS,
            VerdanceMemoryModuleTypes.WANTS_TO_COCOON
    );
    public static final List<SensorType<? extends Sensor<? super SilkwormEntity>>> SENSORS = ImmutableList.of(
            VerdanceSensorTypes.SILKWORM_SPECIFIC_SENSOR,
            VerdanceSensorTypes.SILKWORM_TEMPTATIONS,
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY
    );

    public static Brain<SilkwormEntity> createBrain(Brain<SilkwormEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        addCocoonActivities(brain);

        brain.setDefaultActivity(Activity.IDLE);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        return brain;
    }

    private static void addCoreActivities(Brain<SilkwormEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(
                new StayAboveWaterTask(1.0f),
                new FleeTask<>(1.5f),
                new LookAroundTask(45, 90),
                new MoveToTargetTask(),
                new TemptationCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleActivities(Brain<SilkwormEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new TemptTask(livingEntity -> 1.0f)),
                Pair.of(1, createStrollingBehaviors())
        ));
    }

    private static void addCocoonActivities(Brain<SilkwormEntity> brain) {
        brain.setTaskList(VerdanceActivities.COCOON, ImmutableList.of(
                Pair.of(1, new RandomTask<>(ImmutableList.of(
                        Pair.of(new SearchForCocoonTask(), 3),
                        Pair.of(StrollTask.create(1.0f), 2),
                        Pair.of(new WaitTask(30, 60), 1)
                ))),
                Pair.of(2, new TurnIntoCocoonTask())
        ), Set.of(Pair.of(VerdanceMemoryModuleTypes.WANTS_TO_COCOON, MemoryModuleState.VALUE_PRESENT)));
    }

    private static RandomTask<SilkwormEntity> createStrollingBehaviors() {
        return new RandomTask<>(ImmutableList.of(
                Pair.of(StrollTask.create(1.0f), 3),
                Pair.of(new WaitTask(30, 60), 1)
        ));
    }

    public static void updateActivity(SilkwormEntity silkworm) {
        silkworm.getBrain().resetPossibleActivities(ImmutableList.of(
                VerdanceActivities.COCOON,
                Activity.IDLE
        ));
    }

    public static Ingredient getTemptations() {
        return Ingredient.fromTag(VerdanceItemTags.SILKWORM_FOOD);
    }
}
