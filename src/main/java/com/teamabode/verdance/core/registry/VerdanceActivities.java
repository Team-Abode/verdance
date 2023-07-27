package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.schedule.Activity;

public class VerdanceActivities {
    public static final Activity SLEEP = register("sleep");
    public static final Activity LAY_EGGS = register("lay_eggs");

    public static Activity register(String name) {
        return Registry.register(BuiltInRegistries.ACTIVITY, Verdance.id(name), new Activity(name));
    }

    public static void register() {

    }
}
