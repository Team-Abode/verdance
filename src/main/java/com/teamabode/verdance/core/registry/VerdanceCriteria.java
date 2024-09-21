package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedCriterion;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VerdanceCriteria {

    public static final SilkwormEggsDestroyedCriterion SILKWORM_EGGS_DESTROYED = register("silkworm_eggs_destroyed", new SilkwormEggsDestroyedCriterion());
    public static final TickCriterion EXTINGUISHED_WITH_CANTALOUPE_JUICE = register("extinguished_with_cantaloupe_juice", new TickCriterion());

    private static <T extends Criterion<?>> T register(String name, T trigger) {
        return Registry.register(Registries.CRITERION, Verdance.id(name), trigger);
    }

    public static void init() {}
}
