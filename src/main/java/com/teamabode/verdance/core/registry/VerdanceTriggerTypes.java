package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.trigger.SilkwormEggsDestroyedCriterion;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VerdanceTriggerTypes {
    public static final DeferredRegister<CriterionTrigger<?>> REGISTRY = DeferredRegister.create(Registries.TRIGGER_TYPE, Verdance.MOD_ID);

    public static final Supplier<SilkwormEggsDestroyedCriterion> SILKWORM_EGGS_DESTROYED = register("silkworm_eggs_destroyed", new SilkwormEggsDestroyedCriterion());
    public static final Supplier<PlayerTrigger> EXTINGUISHED_WITH_CANTALOUPE_JUICE = register("extinguished_with_cantaloupe_juice", new PlayerTrigger());

    private static <T extends CriterionTrigger<?>> Supplier<T> register(String name, T trigger) {
        return REGISTRY.register(name, () -> trigger);
    }
}
