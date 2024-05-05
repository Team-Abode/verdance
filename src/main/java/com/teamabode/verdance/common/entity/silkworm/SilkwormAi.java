package com.teamabode.verdance.common.entity.silkworm;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.core.misc.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceSensorTypes;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class SilkwormAi {

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
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES
    );

    public static final List<SensorType<? extends Sensor<? super Silkworm>>> SENSORS = ImmutableList.of(
            VerdanceSensorTypes.SILKWORM_TEMPTATIONS,
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY
    );

    public static Brain<Silkworm> createBrain(Brain<Silkworm> brain) {
        addCoreActivities(brain);
        addIdleActivities(brain);

        brain.setDefaultActivity(Activity.IDLE);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        return brain;
    }

    private static void addCoreActivities(Brain<Silkworm> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(1.0f),
                new AnimalPanic<>(1.5f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)
        ));
    }

    private static void addIdleActivities(Brain<Silkworm> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, new FollowTemptation(livingEntity -> 1.0f)),
                Pair.of(1, createIdleBehaviors())
        ));
    }

    private static RunOne<Silkworm> createIdleBehaviors() {
        return new RunOne<>(ImmutableList.of(
                Pair.of(RandomStroll.stroll(1.0f), 3),
                Pair.of(new DoNothing(30, 60), 1)
        ));
    }

    public static void updateActivity(Silkworm silkworm) {
        silkworm.getBrain().setActiveActivityToFirstValid(ImmutableList.of(
                Activity.IDLE
        ));
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(VerdanceItemTags.SILKWORM_FOOD);
    }
}
