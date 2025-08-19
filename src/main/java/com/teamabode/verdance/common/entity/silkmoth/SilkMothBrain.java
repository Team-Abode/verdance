package com.teamabode.verdance.common.entity.silkmoth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.common.entity.silkmoth.task.*;
import com.teamabode.verdance.core.registry.VerdanceActivities;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import com.teamabode.verdance.core.registry.VerdanceSensorTypes;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("deprecation")
public class SilkMothBrain {

    public static final List<MemoryModuleType<?>> MEMORY_MODULES = ImmutableList.of(
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.IS_PANICKING,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.IS_PREGNANT,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.MOBS,
            MemoryModuleType.VISIBLE_MOBS,
            VerdanceMemoryModuleTypes.IS_FLYING,
            VerdanceMemoryModuleTypes.LANDING_TIME,
            VerdanceMemoryModuleTypes.WANTS_TO_LAND
    );

    public static final List<SensorType<? extends Sensor<? super SilkMothEntity>>> SENSORS = ImmutableList.of(
            VerdanceSensorTypes.SILK_MOTH_SPECIFIC_SENSOR,
            VerdanceSensorTypes.SILK_MOTH_TEMPTATIONS,
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY
    );

    public static Brain<SilkMothEntity> createBrain(Brain<SilkMothEntity> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        addLayEggsActivities(brain);

        brain.setDefaultActivity(Activity.IDLE);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        return brain;
    }

    private static void addCoreActivities(Brain<SilkMothEntity> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(
                new StayAboveWaterTask<>(1.0f),
                new TakeOffTask(),
                new LandTask(),
                new FleeTask<>(1.5f),
                new UpdateLookControlTask(45, 90),
                new MoveToTargetTask(),
                new TickCooldownTask(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleActivities(Brain<SilkMothEntity> brain) {
        brain.setTaskList(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new BreedTask(VerdanceEntityTypes.SILK_MOTH)),
                Pair.of(1, new TemptTask(livingEntity -> 1.5f)),
                Pair.of(2, LookAtMobWithIntervalTask.follow(EntityType.PLAYER, 6.0f, UniformIntProvider.create(30, 60))),
                Pair.of(2, new GoTowardsLandingTask()),
                Pair.of(4, addMovementTasks())
        ));
    }

    private static void addLayEggsActivities(Brain<SilkMothEntity> brain) {
        brain.setTaskList(VerdanceActivities.LAY_EGGS, ImmutableList.of(
                Pair.of(0, new SearchForLeavesTask()),
                Pair.of(1, LayEggsTask.create()),
                Pair.of(2, addMovementTasks())
        ), ImmutableSet.of(Pair.of(MemoryModuleType.IS_PREGNANT, MemoryModuleState.VALUE_PRESENT)));
    }

    public static void updateActivity(SilkMothEntity silkMoth) {
        silkMoth.getBrain().resetPossibleActivities(ImmutableList.of(
                VerdanceActivities.LAY_EGGS,
                VerdanceActivities.SLEEP,
                Activity.IDLE
        ));
    }

    public static Predicate<ItemStack> getTemptItemPredicate() {
        return stack -> stack.isIn(VerdanceItemTags.SILK_MOTH_FOOD);
    }

    private static RandomTask<SilkMothEntity> addMovementTasks() {
        return new RandomTask<>(ImmutableList.of(
                Pair.of(TaskTriggerer.runIf(SilkMothEntity::isInAir, new AerialStrollTask()), 2),
                Pair.of(TaskTriggerer.runIf(SilkMothEntity::isInAir, new GoTowardsLandingTask()), 2),
                Pair.of(TaskTriggerer.runIf(Predicate.not(SilkMothEntity::isInAir), StrollTask.create(1.0f)), 2),
                Pair.of(GoToLookTargetTask.create(1.0f, 3), 2),
                Pair.of(new WaitTask(30,  60), 1)
        ));
    }
}
