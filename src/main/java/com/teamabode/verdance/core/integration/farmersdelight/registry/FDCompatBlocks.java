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

public class FDCompatBlocks {
    public static final Block MULBERRY_CABINET = register(
            "mulberry_cabinet",
            new CompatCabinetBlock(AbstractBlock.Settings.copy(Blocks.BARREL))
    );
    public static final Block MULBERRY_CRATE = register(
            "mulberry_crate",
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
    );

    public static void register() {

    }

    // Registry Utils
    private static Block register(String name, AbstractBlock.Settings properties) {
        return register(name, new Block(properties));
    }

    private static Block register(String name, Block block) {
        var registry = Registry.register(Registries.BLOCK, Verdance.id(name), block);
        Registry.register(Registries.ITEM, Verdance.id(name), new BlockItem(registry, new Item.Settings()));
        return registry;
    }
}
