package com.teamabode.verdance.core.key;

import com.teamabode.sketch.common.entity.boat.SketchBoatType;
import com.teamabode.sketch.core.registry.SketchRegistries;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

public class VerdanceBoatTypes {
    public static final ResourceKey<SketchBoatType> MULBERRY = createKey("mulberry");

    public static void register(BootstrapContext<SketchBoatType> context) {
        context.register(MULBERRY, new SketchBoatType(
                Verdance.id("mulberry"),
                BuiltInRegistries.ITEM.wrapAsHolder(VerdanceItems.MULBERRY_BOAT),
                BuiltInRegistries.ITEM.wrapAsHolder(VerdanceItems.MULBERRY_CHEST_BOAT)
        ));
    }

    private static ResourceKey<SketchBoatType> createKey(String name) {
        return ResourceKey.create(SketchRegistries.BOAT_TYPE, Verdance.id(name));
    }
}
