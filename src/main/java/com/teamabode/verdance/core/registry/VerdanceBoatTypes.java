package com.teamabode.verdance.core.registry;

import com.teamabode.scribe.common.entity.boat.ScribeBoatType;
import com.teamabode.scribe.core.registry.ScribeBuiltInRegistries;
import com.teamabode.verdance.Verdance;
import net.minecraft.registry.Registry;

public class VerdanceBoatTypes {
    public static final ScribeBoatType MULBERRY = register("mulberry");

    public static void register() {

    }

    private static ScribeBoatType register(String name) {
        return Registry.register(ScribeBuiltInRegistries.BOAT_TYPE, Verdance.id(name), new ScribeBoatType(name));
    }
}
