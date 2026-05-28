package com.teamabode.verdance.core.integration.farmersdelight;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.event.CreativeModeTabEvents;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

public class FDIntegration {
    public static final ResourceKey<CreativeModeTab> TAB_FARMERSDELIGHT = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, FarmersDelight.MODID)
    );
    public static final Supplier<Block> MULBERRY_CRATE = VerdanceBlocks.registerWithItem("mulberry_crate", () -> new Block(
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
    ));
    public static final Supplier<Block> MULBERRY_CABINET = VerdanceBlocks.registerWithItem("mulberry_cabinet", () -> new CabinetBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)
    ));

    public static void register(IEventBus bus) {
        bus.addListener(FDIntegration::onBlockEntityTypeAddBlocks);
        bus.addListener(FDIntegration::onBuildCreativeModeTabContents);
        bus.addListener(FDIntegration::onAddPackFinders);
    }

    private static void onAddPackFinders(AddPackFindersEvent event) {
        if (Dist.CLIENT.isClient()) {
            event.addPackFinders(
                    Verdance.id("packs/fd_resourcepack"),
                    PackType.CLIENT_RESOURCES,
                    Component.literal("Verdance Compatibility - Farmer's Delight"),
                    PackSource.BUILT_IN,
                    true,
                    Pack.Position.TOP
            );
        }
        event.addPackFinders(
                Verdance.id("packs/fd_datapack"),
                PackType.SERVER_DATA,
                Component.literal("Verdance Compatibility - Farmer's Delight"),
                PackSource.BUILT_IN,
                true,
                Pack.Position.TOP
        );
    }

    private static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == TAB_FARMERSDELIGHT) {
            CreativeModeTabEvents.insertAfter(event, ModItems.CHERRY_CABINET.get(), MULBERRY_CABINET.get());
            CreativeModeTabEvents.insertAfter(event, ModItems.ONION_CRATE.get(), MULBERRY_CRATE.get());
        }
    }

    private static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(ModBlockEntityTypes.CABINET.get(), MULBERRY_CABINET.get());
    }
}
