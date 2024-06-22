package com.teamabode.verdance.core.registry;

import com.teamabode.sketch.common.entity.boat.SketchBoatType;
import com.teamabode.sketch.core.registry.SketchRegistries;
import com.teamabode.verdance.Verdance;
import net.minecraft.resources.ResourceKey;

public class VerdanceBoatTypes {
    public static final ResourceKey<SketchBoatType> MULBERRY = register("mulberry");

    private static ResourceKey<SketchBoatType> register(String name) {
        return ResourceKey.create(SketchRegistries.BOAT_TYPE, Verdance.id(name));
    }

    public static void register() {

    }
}
