package com.teamabode.verdance.core.integration.farmersdelight;

import com.teamabode.verdance.core.integration.CompatUtils;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatBlockEntityTypes;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatBlocks;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatSoundEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;

public class FDIntegration {
    private static final RegistryKey<ItemGroup> TAB_FARMERS_DELIGHT = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of("farmersdelight:farmersdelight")
    );

    public static void register(ModContainer container) {
        FDCompatBlocks.register();
        FDCompatBlockEntityTypes.register();
        FDCompatSoundEvents.register();

        ItemGroupEvents.modifyEntriesEvent(TAB_FARMERS_DELIGHT).register(entries -> {
            entries.addAfter(ModItems.ONION_CRATE.get(), FDCompatBlocks.MULBERRY_CRATE);
            entries.addAfter(ModBlocks.CHERRY_CABINET.get(), FDCompatBlocks.MULBERRY_CABINET);
        });
        CompatUtils.registerBuiltinPack("farmersdelight_datapack", container);
        CompatUtils.registerBuiltinPack("farmersdelight_resourcepack", container);
    }
}
