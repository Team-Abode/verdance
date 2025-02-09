package com.teamabode.verdance.core.integration.farmersdelight;

import com.teamabode.verdance.core.integration.CompatUtils;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatBlockEntityTypes;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatBlocks;
import com.teamabode.verdance.core.integration.farmersdelight.registry.FDCompatSoundEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class FDIntegration {
    private static final RegistryKey<ItemGroup> TAB_FARMERS_DELIGHT = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            new Identifier("farmersdelight", "farmersdelight")
    );

    public static void register(ModContainer container) {
        FDCompatBlocks.register();
        FDCompatBlockEntityTypes.register();
        FDCompatSoundEvents.register();

        ItemGroupEvents.modifyEntriesEvent(TAB_FARMERS_DELIGHT).register(entries -> {
            entries.addAfter(Registries.ITEM.get(new Identifier("farmersdelight", "onion_crate")), FDCompatBlocks.MULBERRY_CRATE);
            entries.addAfter(Registries.ITEM.get(new Identifier("farmersdelight", "cherry_cabinet")), FDCompatBlocks.MULBERRY_CABINET);
        });
        if (CompatUtils.isModLoaded("connectormod")) {
            CompatUtils.registerBuiltinPack("forge_farmersdelight_datapack", container);
        } else {
            CompatUtils.registerBuiltinPack("farmersdelight_datapack", container);
        }
        CompatUtils.registerBuiltinPack("farmersdelight_resourcepack", container);
    }
}
