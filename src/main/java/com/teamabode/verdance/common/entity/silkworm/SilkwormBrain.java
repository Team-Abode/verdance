package com.teamabode.verdance.common.entity.silkworm;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.common.entity.silkworm.task.SearchForCocoonTask;
import com.teamabode.verdance.common.entity.silkworm.task.TurnIntoCocoonTask;
import com.teamabode.verdance.core.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceActivities;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleTypes;
import com.teamabode.verdance.core.registry.VerdanceSensorTypes;
import java.util.List;
import java.util.Set;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.AnimalPanic;
import net.minecraft.world.entity.ai.behavior.CountDownCooldownTicks;
import net.minecraft.world.entity.ai.behavior.DoNothing;
import net.minecraft.world.entity.ai.behavior.FollowTemptation;
import net.minecraft.world.entity.ai.behavior.LookAtTargetSink;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.RandomStroll;
import net.minecraft.world.entity.ai.behavior.RunOne;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

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
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
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
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(1.0f),
                new AnimalPanic<>(1.5f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleActivities(Brain<SilkwormEntity> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new FollowTemptation(livingEntity -> 1.0f)),
                Pair.of(1, createStrollingBehaviors())
        ));
    }

    private static void addCocoonActivities(Brain<SilkwormEntity> brain) {
        brain.addActivityWithConditions(VerdanceActivities.COCOON, ImmutableList.of(
                Pair.of(1, new RunOne<>(ImmutableList.of(
                        Pair.of(new SearchForCocoonTask(), 3),
                        Pair.of(RandomStroll.stroll(1.0f), 2),
                        Pair.of(new DoNothing(30, 60), 1)
                ))),
                Pair.of(2, new TurnIntoCocoonTask())
        ), Set.of(Pair.of(VerdanceMemoryModuleTypes.WANTS_TO_COCOON, MemoryStatus.VALUE_PRESENT)));
    }

    private static RunOne<SilkwormEntity> createStrollingBehaviors() {
        return new RunOne<>(ImmutableList.of(
                Pair.of(RandomStroll.stroll(1.0f), 3),
                Pair.of(new DoNothing(30, 60), 1)
        ));
    }

    public static void updateActivity(SilkwormEntity silkworm) {
        silkworm.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                VerdanceActivities.COCOON,
                Activity.IDLE
        ));
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(VerdanceItemTags.SILKWORM_FOOD);
    }
}
