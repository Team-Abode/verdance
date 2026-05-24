package com.teamabode.verdance.core.integration.farmersdelight;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

import java.util.function.Supplier;

public class FDIntegration {
    /*
    private static final ResourceKey<CreativeModeTab> TAB_FARMERS_DELIGHT = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath("farmersdelight", "farmersdelight")
    );

    public static final Supplier<Block> MULBERRY_CRATE = VerdanceBlocks.registerWithItem("mulberry_crate", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    ));
    public static final Supplier<Block> MULBERRY_CABINET = VerdanceBlocks.registerWithItem("mulberry_cabinet", () -> new CabinetBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)
    ));

    public static void register() {
        NeoForge.EVENT_BUS.addListener(FDIntegration::onBlockEntityTypeAddBlocks);
    }

    private static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(ModBlockEntityTypes.CABINET.get(), MULBERRY_CABINET.get());
    }
    */
}
