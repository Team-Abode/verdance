package com.teamabode.verdance.core.registry;

import com.teamabode.sketch.common.entity.boat.SketchBoatType;
import com.teamabode.sketch.core.registry.SketchBuiltInRegistries;
import com.teamabode.verdance.Verdance;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class VerdanceBoatTypes {
    public static final SketchBoatType MULBERRY = register("mulberry");

    private static SketchBoatType register(String name) {
        return Registry.register(SketchBuiltInRegistries.BOAT_TYPE, new ResourceLocation(Verdance.MOD_ID, name), new SketchBoatType(name, Holder.direct(VerdanceItems.MULBERRY_BOAT), Holder.direct(VerdanceItems.MULBERRY_CHEST_BOAT)));
    }

    public static void register() {

    }
}
