package com.teamabode.verdance.core.misc.key;

import com.teamabode.sketch.common.entity.boat.SketchBoatType;
import com.teamabode.sketch.core.registry.SketchRegistries;
import com.teamabode.verdance.Verdance;
import net.minecraft.resources.ResourceKey;

public class VerdanceBoatTypes {
    public static final ResourceKey<SketchBoatType> MULBERRY = createKey("mulberry");

    private static ResourceKey<SketchBoatType> createKey(String name) {
        return ResourceKey.create(SketchRegistries.BOAT_TYPE, Verdance.id(name));
    }
}
