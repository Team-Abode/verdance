package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class VerdanceTriggerTypes {

    public static final SilkwormEggsDestroyedTrigger SILKWORM_EGGS_DESTROYED = register("silkworm_eggs_destroyed", new SilkwormEggsDestroyedTrigger());
    public static final PlayerTrigger EXTINGUISHED_WITH_CANTALOUPE_JUICE = register("extinguished_with_cantaloupe_juice", new PlayerTrigger());

    private static <T extends CriterionTrigger<?>> T register(String name, T trigger) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, Verdance.id(name), trigger);
    }

    public static void init() {}
}
