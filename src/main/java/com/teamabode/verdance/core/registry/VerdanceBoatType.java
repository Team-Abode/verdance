package com.teamabode.verdance.core.registry;

import com.teamabode.scribe.common.entity.ScribeBoatType;
import com.teamabode.scribe.core.registry.ScribeBuiltInRegistries;
import com.teamabode.verdance.Verdance;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class VerdanceBoatType {

    public static final ScribeBoatType MULBERRY = register("mulberry", VerdanceItems.MULBERRY_BOAT, VerdanceItems.MULBERRY_CHEST_BOAT);

    private static ScribeBoatType register(String name, Item boatItem, Item chestBoatItem) {
        return Registry.register(ScribeBuiltInRegistries.BOAT_TYPE, new ResourceLocation(Verdance.MOD_ID, name), new ScribeBoatType(name, boatItem, chestBoatItem));
    }

    public static void register() {

    }
}
