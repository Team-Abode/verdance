package com.teamabode.verdance.common.entity.silk_moth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.common.entity.silk_moth.behaviors.*;
import com.teamabode.verdance.core.registry.VerdanceActivities;
import com.teamabode.verdance.core.registry.VerdanceEntities;
import com.teamabode.verdance.core.registry.VerdanceMemories;
import com.teamabode.verdance.core.registry.VerdanceSensors;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.List;
import java.util.function.Predicate;


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
            VerdanceMemories.FLIGHT_COOLDOWN_TICKS,
            MemoryModuleType.IS_PREGNANT,
            VerdanceMemories.IS_POLLINATING,
            VerdanceMemories.POLLINATE_TARGET
    );
    public static final ImmutableList<SensorType<? extends Sensor<? super SilkMoth>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            VerdanceSensors.SILK_MOTH_TEMPTATIONS,
            VerdanceSensors.SILK_MOTH_SPECIFIC_SENSOR
    );

    public static final Predicate<BlockState> CANNOT_POLLINATE = state -> {
        if (!state.is(BlockTags.SAPLINGS) || !state.hasProperty(BlockStateProperties.STAGE)) return true;
        return state.getValue(BlockStateProperties.STAGE) >= 1;
    };

    public static Brain.Provider<SilkMoth> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected static Brain<?> makeBrain(Brain<SilkMoth> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        initLayEggsActivity(brain);
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
                ForceTakeOff.create(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new CountDownCooldownTicks(VerdanceMemories.FLIGHT_COOLDOWN_TICKS)
        ));
    }

    private static void initIdleActivity(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(20, 40))),
                Pair.of(1, new AnimalMakeLove(VerdanceEntities.SILK_MOTH, 1.0f)),
                Pair.of(2, new FollowTemptation(livingEntity -> 1.5f)),
                Pair.of(3, new RunOne<>(List.of(
                        Pair.of(RandomStroll.stroll(1.0f), 1),
                        Pair.of(OccasionalTakeOff.create(), 1),
                        Pair.of(SetWalkTargetFromLookTarget.create(1.0f, 5), 2)
                ))),
                Pair.of(4, new PollinateSapling()),
                Pair.of(5, new SearchForSapling())
        ));
    }

    private static void initLayEggsActivity(Brain<SilkMoth> brain) {
        brain.addActivityWithConditions(VerdanceActivities.LAY_EGGS, ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(10, 20))),
                Pair.of(1, SearchForLeaves.create()),
                Pair.of(2, RandomStroll.stroll(1.0f)),
                Pair.of(3, TryLayEggs.create())
        ), ImmutableSet.of(Pair.of(MemoryModuleType.IS_PREGNANT, MemoryStatus.VALUE_PRESENT)));
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(ItemTags.FLOWERS);
    }

    public static void updateActivity(SilkMoth silkMoth) {
        silkMoth.getBrain().setActiveActivityToFirstValid(ImmutableList.of(VerdanceActivities.LAY_EGGS, Activity.IDLE));
    }
}
