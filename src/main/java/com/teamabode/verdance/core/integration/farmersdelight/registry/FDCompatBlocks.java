package com.teamabode.verdance.core.integration.farmersdelight.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class FDCompatBlocks {
    // TODO: To prevent issues when trying to update MC versions, we could recreate our own cabinet
    public static final Block MULBERRY_CABINET = register(
            "mulberry_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.BARREL))
    );
    public static final Block MULBERRY_CRATE = register(
            "mulberry_crate",
            AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)
    );

    public static void register() {
        ModBlockEntityTypes.CABINET.get().addSupportedBlock(MULBERRY_CABINET);
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
