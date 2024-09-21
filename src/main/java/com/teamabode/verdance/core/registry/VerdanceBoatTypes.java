package com.teamabode.verdance.core.registry;

import com.teamabode.sketch.common.entity.boat.SketchBoatType;
import com.teamabode.sketch.core.registry.SketchRegistries;
import com.teamabode.verdance.Verdance;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

public class VerdanceBoatTypes {
    public static final RegistryKey<SketchBoatType> MULBERRY = createKey("mulberry");

    public static void register(Registerable<SketchBoatType> context) {
        context.register(MULBERRY, new SketchBoatType(
                Verdance.id("mulberry"),
                Registries.ITEM.getEntry(VerdanceItems.MULBERRY_BOAT),
                Registries.ITEM.getEntry(VerdanceItems.MULBERRY_CHEST_BOAT)
        ));
    }

    private static RegistryKey<SketchBoatType> createKey(String name) {
        return RegistryKey.of(SketchRegistries.BOAT_TYPE, Verdance.id(name));
    }
}
