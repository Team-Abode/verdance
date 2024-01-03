package com.teamabode.verdance.core.integration.farmersdelight;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.nhoryzon.mc.farmersdelight.registry.ItemsRegistry;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

public class VerdanceFD {

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(FarmersDelightMod.ITEM_GROUP).register(entries -> {
            entries.addAfter(ItemsRegistry.MELON_JUICE.get(), VerdanceItems.CANTALOUPE_JUICE);
            entries.addAfter(ItemsRegistry.ONION_CRATE.get(), VerdanceBlocks.MULBERRY_CRATE);
        });
    }
}
