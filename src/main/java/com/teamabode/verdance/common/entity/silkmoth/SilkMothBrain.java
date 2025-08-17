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
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.AnimalMakeLove;
import net.minecraft.world.entity.ai.behavior.AnimalPanic;
import net.minecraft.world.entity.ai.behavior.CountDownCooldownTicks;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.ai.behavior.FollowTemptation;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.ai.behavior.SetEntityLookTargetSometimes;
import net.minecraft.world.entity.ai.behavior.SetWalkTargetFromLookTarget;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

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
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
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
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(1.0f),
                new TakeOffTask(),
                new LandTask(),
                new AnimalPanic<>(1.5f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleActivities(Brain<SilkMothEntity> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new AnimalMakeLove(VerdanceEntityTypes.SILK_MOTH)),
                Pair.of(1, new FollowTemptation(livingEntity -> 1.5f)),
                Pair.of(2, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(30, 60))),
                Pair.of(2, new GoTowardsLandingTask()),
                Pair.of(4, addMovementTasks())
        ));
    }

    private static void addLayEggsActivities(Brain<SilkMothEntity> brain) {
        brain.addActivityWithConditions(VerdanceActivities.LAY_EGGS, ImmutableList.of(
                Pair.of(0, new SearchForLeavesTask()),
                Pair.of(1, LayEggsTask.create()),
                Pair.of(2, addMovementTasks())
        ), ImmutableSet.of(Pair.of(MemoryModuleType.IS_PREGNANT, MemoryStatus.VALUE_PRESENT)));
    }

    public static void updateActivity(SilkMothEntity silkMoth) {
        silkMoth.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                VerdanceActivities.LAY_EGGS,
                VerdanceActivities.SLEEP,
                Activity.IDLE
        ));
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(VerdanceItemTags.SILK_MOTH_FOOD);
    }

    private static RunOne<SilkMothEntity> addMovementTasks() {
        return new RunOne<>(ImmutableList.of(
                Pair.of(BehaviorBuilder.triggerIf(SilkMothEntity::isFlying, new AerialStrollTask()), 2),
                Pair.of(BehaviorBuilder.triggerIf(SilkMothEntity::isFlying, new GoTowardsLandingTask()), 2),
                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(SilkMothEntity::isFlying), RandomStroll.stroll(1.0f)), 2),
                Pair.of(SetWalkTargetFromLookTarget.create(1.0f, 3), 2),
                Pair.of(new DoNothing(30,  60), 1)
        ));
    }
}
