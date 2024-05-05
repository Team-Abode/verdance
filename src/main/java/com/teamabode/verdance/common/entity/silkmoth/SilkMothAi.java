package com.teamabode.verdance.common.entity.silkmoth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.common.entity.silkmoth.behaviors.*;
import com.teamabode.verdance.core.registry.VerdanceActivities;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import com.teamabode.verdance.core.registry.VerdanceSensorTypes;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("deprecation")
public class SilkMothAi {

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
            VerdanceMemoryModuleTypes.WANTS_TO_SLEEP
    );

    public static final List<SensorType<? extends Sensor<? super SilkMoth>>> SENSORS = ImmutableList.of(
            VerdanceSensorTypes.SILK_MOTH_SENSOR,
            VerdanceSensorTypes.SILK_MOTH_TEMPTATIONS,
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY
    );

    public static Brain<SilkMoth> createBrain(Brain<SilkMoth> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);
        addLayEggsActivities(brain);

        brain.setDefaultActivity(Activity.IDLE);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        return brain;
    }

    private static void addCoreActivities(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(1.0f),
                new AnimalPanic<>(1.5f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new TakeOff(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleActivities(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new AnimalMakeLove(VerdanceEntityTypes.SILK_MOTH)),
                Pair.of(1, new FollowTemptation(livingEntity -> 1.5f)),
                Pair.of(2, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(30, 60))),
                Pair.of(3, addIdleBehaviors())
        ));
    }

    private static void addLayEggsActivities(Brain<SilkMoth> brain) {
        brain.addActivityWithConditions(VerdanceActivities.LAY_EGGS, ImmutableList.of(
                Pair.of(0, SearchForLeaves.create()),
                Pair.of(1, TryLayEggs.create()),
                Pair.of(2, addIdleBehaviors())
        ), ImmutableSet.of(Pair.of(MemoryModuleType.IS_PREGNANT, MemoryStatus.VALUE_PRESENT)));
    }

    private static void addSleepActivities(Brain<SilkMoth> brain) {
        brain.addActivityWithConditions(VerdanceActivities.SLEEP, ImmutableList.of(
                Pair.of(1, addIdleBehaviors())
        ), ImmutableSet.of(Pair.of(VerdanceMemoryModuleTypes.WANTS_TO_SLEEP, MemoryStatus.VALUE_PRESENT)));
    }

    public static void updateActivity(SilkMoth silkMoth) {
        silkMoth.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                VerdanceActivities.LAY_EGGS,
                VerdanceActivities.SLEEP,
                Activity.IDLE
        ));
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(ItemTags.FLOWERS);
    }

    private static RunOne<SilkMoth> addIdleBehaviors() {
        return new RunOne<>(ImmutableList.of(
                Pair.of(BehaviorBuilder.triggerIf(SilkMoth::isFlying, new AerialStroll()), 2),
                Pair.of(BehaviorBuilder.triggerIf(SilkMoth::isFlying, new LandOnGround()), 3),
                Pair.of(BehaviorBuilder.triggerIf(Predicate.not(SilkMoth::isFlying), RandomStroll.stroll(1.0f)), 2),
                Pair.of(SetWalkTargetFromLookTarget.create(1.0f, 3), 2),
                Pair.of(new DoNothing(30,  60), 1)
        ));
    }
}
