package com.teamabode.verdance.common.entity.silk_moth;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.STAGE;

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
            VerdanceMemories.OCCASIONAL_FLIGHT_COOLDOWN,
            VerdanceMemories.POLLINATE_COOLDOWN,
            MemoryModuleType.IS_PREGNANT,
            VerdanceMemories.SAPLINGS_POLLINATED
    );
    public static final ImmutableList<SensorType<? extends Sensor<? super SilkMoth>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            VerdanceSensors.SILK_MOTH_TEMPTATIONS
    );

    private static final RunOne<SilkMoth> MOVEMENT_OPTIONS = new RunOne<>(ImmutableMap.of(
            MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT
    ), List.of(
            Pair.of(RandomStroll.stroll(1.0f), 1),
            Pair.of(OccasionalTakeOff.create(), 1),
            Pair.of(new DoNothing(40, 80), 1)
    ));

    public static final Predicate<BlockState> CAN_POLLINATE = state -> state.is(BlockTags.SAPLINGS) && state.hasProperty(STAGE) && state.getValue(STAGE) < 1;

    public static Brain.Provider<SilkMoth> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

    protected static Brain<?> makeBrain(Brain<SilkMoth> brain) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        initPollinateActivity(brain);
        initLayEggsActivity(brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(
                new Swim(0.8f),
                new AnimalPanic(2.5f),
                new LookAtTargetSink(45, 90),
                new MoveToTargetSink(),
                TakeOff.create(),
                new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS),
                new CountDownCooldownTicks(VerdanceMemories.OCCASIONAL_FLIGHT_COOLDOWN),
                new CountDownPollinateCooldown()
        ));
    }

    private static void initIdleActivity(Brain<SilkMoth> brain) {
        brain.addActivity(Activity.IDLE, ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(20, 40))),
                Pair.of(1, new AnimalMakeLove(VerdanceEntities.SILK_MOTH, 1.0f)),
                Pair.of(2, new FollowTemptation(livingEntity -> 1.5f)),
                Pair.of(3, MOVEMENT_OPTIONS)
        ));
    }

    private static void initPollinateActivity(Brain<SilkMoth> brain) {
        brain.addActivityWithConditions(VerdanceActivities.POLLINATE, ImmutableList.of(
                Pair.of(1, new AnimalMakeLove(VerdanceEntities.SILK_MOTH, 1.0f)),
                Pair.of(2, new FollowTemptation(livingEntity -> 1.5f)),
                Pair.of(3, PollinateSapling.create()),
                Pair.of(4, new SearchForSapling()),
                Pair.of(5, MOVEMENT_OPTIONS)
        ), ImmutableSet.of(Pair.of(VerdanceMemories.POLLINATE_COOLDOWN, MemoryStatus.VALUE_ABSENT)));
    }

    private static void initLayEggsActivity(Brain<SilkMoth> brain) {
        brain.addActivityWithConditions(VerdanceActivities.LAY_EGGS, ImmutableList.of(
                Pair.of(0, SetEntityLookTargetSometimes.create(EntityType.PLAYER, 6.0f, UniformInt.of(10, 20))),
                Pair.of(1, SearchForLeaves.create()),
                Pair.of(2, MOVEMENT_OPTIONS),
                Pair.of(3, TryLayEggs.create())
        ), ImmutableSet.of(Pair.of(MemoryModuleType.IS_PREGNANT, MemoryStatus.VALUE_PRESENT)));
    }

    public static Ingredient getTemptations() {
        return Ingredient.of(ItemTags.FLOWERS);
    }

    public static void updateActivity(SilkMoth silkMoth) {
        silkMoth.getBrain().setActiveActivityToFirstValid(ImmutableList.of(VerdanceActivities.LAY_EGGS, VerdanceActivities.POLLINATE, Activity.IDLE));
    }
}
