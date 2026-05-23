package com.teamabode.verdance.core.misc;

import com.google.common.base.Suppliers;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Items;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;

public class VerdanceBoatTypes {
    public static final EnumProxy<Boat.Type> MULBERRY_BOAT_PROXY = new EnumProxy<>(
            Boat.Type.class,
            VerdanceBlocks.MULBERRY_PLANKS,
            "verdance:mulberry",
            VerdanceItems.MULBERRY_BOAT,
            VerdanceItems.MULBERRY_CHEST_BOAT,
            Suppliers.memoize(() -> Items.STICK),
            false
    );
}
