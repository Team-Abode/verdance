package com.teamabode.verdance.core.integration.farmersdelight;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.nhoryzon.mc.farmersdelight.block.CabinetBlock;
import com.nhoryzon.mc.farmersdelight.registry.BlocksRegistry;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceGroupEvents;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.level.block.Block;

import static com.teamabode.verdance.core.registry.VerdanceBlocks.MULBERRY_CABINET;

public class VerdanceFD {

    public static Block createMulberryCabinet() {
        return new CabinetBlock();
    }

    public static void register() {
        Verdance.LOGGER.info("Registering Farmer's Delight compatibility!");
        VerdanceGroupEvents.appendItemsBefore(FarmersDelightMod.ITEM_GROUP, BlocksRegistry.CRIMSON_CABINET.get(), MULBERRY_CABINET);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_CABINET, 5, 20);
    }
}
