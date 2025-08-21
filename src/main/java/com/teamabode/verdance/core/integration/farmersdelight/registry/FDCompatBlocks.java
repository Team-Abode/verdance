package com.teamabode.verdance.core.integration.farmersdelight.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.integration.farmersdelight.block.CompatCabinetBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Function;

public class FDCompatBlocks {
    public static final Block MULBERRY_CABINET = register(
            "mulberry_cabinet",
            CompatCabinetBlock::new,
            AbstractBlock.Settings.copy(Blocks.BARREL)
    );
    public static final Block MULBERRY_CRATE = register(
            "mulberry_crate",
            Block::new,
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
    );

    public static void register() {

    }

    // Registry Utils
    private static Block register(String name, Function<AbstractBlock.Settings, Block> block, AbstractBlock.Settings settings) {
        var key = RegistryKey.of(RegistryKeys.BLOCK, Verdance.id(name));
        var registry = Registry.register(Registries.BLOCK, key, block.apply(settings.registryKey(key)));

        var itemKey = RegistryKey.of(RegistryKeys.ITEM, Verdance.id(name));
        Registry.register(Registries.ITEM, itemKey, new BlockItem(registry, new Item.Settings().registryKey(itemKey)));

        return registry;
    }
}
