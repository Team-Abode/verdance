package com.teamabode.verdance.core.registry;

import com.teamabode.scribe.common.entity.boat.ScribeBoatType;
import com.teamabode.scribe.core.registry.ScribeBuiltInRegistries;
import com.teamabode.verdance.Verdance;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class VerdanceBoatType {
    public static final ScribeBoatType MULBERRY = register("mulberry");

    private static ScribeBoatType register(String name) {
        return Registry.register(ScribeBuiltInRegistries.BOAT_TYPE, new ResourceLocation(Verdance.MOD_ID, name), new ScribeBoatType(name));
    }

    public static void register() {

    }
}
