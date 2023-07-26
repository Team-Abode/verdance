package com.teamabode.verdance.common.entity.silk_moth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.entity.silk_moth.behaviors.TakeOff;
import com.teamabode.verdance.core.registry.VerdanceMemoryModuleType;
import com.teamabode.verdance.core.registry.VerdanceSensorType;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;


@SuppressWarnings("deprecation")
public class SilkMothAi {
    public static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.IS_PANICKING,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.BREED_TARGET,
            MemoryModuleType.IS_TEMPTED,
            MemoryModuleType.TEMPTING_PLAYER,
            MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
            VerdanceMemoryModuleType.FLIGHT_COOLDOWN_TICKS
    );
    public static final ImmutableList<SensorType<? extends Sensor<? super SilkMoth>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            VerdanceSensorType.SILK_MOTH_TEMPTATIONS
    );

    public static Brain.Provider<SilkMoth> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected static Brain<?> makeBrain(Brain<SilkMoth> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(0.8f),
                new AnimalPanic(1.75f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new CountDownCooldownTicks(VerdanceMemoryModuleType.FLIGHT_COOLDOWN_TICKS)
        ));
    }

    private static void initIdleActivity(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(20, 40))),
                Pair.of(1, new FollowTemptation(livingEntity -> 1.5f)),
                Pair.of(2, new RunOne<>(List.of(
                        Pair.of(RandomStroll.stroll(1.0f), 1),
                        Pair.of(new DoNothing(10, 20), 1),
                        Pair.of(SetWalkTargetFromLookTarget.create(1.0f, 5), 2)
                ))),
                Pair.of(3, new TakeOff())
        ));
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(ItemTags.FLOWERS);
    }

    public static void updateActivity(SilkMoth silkMoth) {
        silkMoth.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.IDLE));
    }
}
