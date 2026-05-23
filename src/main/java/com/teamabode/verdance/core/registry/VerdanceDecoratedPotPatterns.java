package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class VerdanceDecoratedPotPatterns {
    public static final DeferredRegister<DecoratedPotPattern> REGISTRY = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, Verdance.MOD_ID);

    public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> ABODE = register("abode");
    public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> FRILLS = register("frills");
    public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> PITCH = register("pitch");
    public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> PRICKLE = register("prickle");
    public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> SPIRIT = register("spirit");
    public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> TRAP = register("trap");

    /*
    public static final Map<Item, ResourceKey<DecoratedPotPattern>> SHERD_TO_PATTERN = Map.of(
            VerdanceItems.ABODE_POTTERY_SHERD.get(), ABODE.getKey(),
            VerdanceItems.FRILLS_POTTERY_SHERD.get(), FRILLS.getKey(),
            VerdanceItems.PITCH_POTTERY_SHERD.get(), PITCH.getKey(),
            VerdanceItems.PRICKLE_POTTERY_SHERD.get(), PRICKLE.getKey(),
            VerdanceItems.SPIRIT_POTTERY_SHERD.get(), SPIRIT.getKey(),
            VerdanceItems.TRAP_POTTERY_SHERD.get(), TRAP.getKey()
    );
    */

    private static DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> register(String name) {
        return REGISTRY.register(name, () -> new DecoratedPotPattern(Verdance.id(name + "_pottery_pattern")));
    }
}
