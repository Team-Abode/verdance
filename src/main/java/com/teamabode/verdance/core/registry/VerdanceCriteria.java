package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedCriterion;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VerdanceCriteria {
    public static final SilkwormEggsDestroyedCriterion SILKWORM_EGGS_DESTROYED = Criteria.register(new SilkwormEggsDestroyedCriterion());
    public static final TickCriterion EXTINGUISHED_WITH_CANTALOUPE_JUICE = Criteria.register(new TickCriterion(Verdance.id("extinguished_with_cantaloupe_juice")));

    public static void init() {}
}
