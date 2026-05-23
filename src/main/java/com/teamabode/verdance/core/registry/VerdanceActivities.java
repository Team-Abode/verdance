package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.schedule.Activity;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VerdanceActivities {
    public static final DeferredRegister<Activity> REGISTRY = DeferredRegister.create(Registries.ACTIVITY, Verdance.MOD_ID);

    public static final Supplier<Activity> COCOON = register("cocoon");
    public static final Supplier<Activity> LAY_EGGS = register("lay_eggs");
    public static final Supplier<Activity> SLEEP = register("sleep");

    public static Supplier<Activity> register(String name) {
        return REGISTRY.register(name, () -> new Activity(name));
    }
}
