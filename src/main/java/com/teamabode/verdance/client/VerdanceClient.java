package com.teamabode.verdance.client;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class VerdanceClient implements ClientModInitializer {

    public void onInitializeClient() {
        setRenderTypes();
        addColorProviders();
    }

    private static void setRenderTypes() {
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_LEAVES, RenderType.cutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.SHRUB, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.CANTALOUPE_STEM, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_TRAPDOOR, RenderType.cutout());
    }

    private static void addColorProviders() {
        ColorProviderRegistry.BLOCK.register((state, tintGetter, pos, i) -> tintGetter != null && pos != null ? BiomeColors.getAverageFoliageColor(tintGetter, pos) : FoliageColor.getDefaultColor(), VerdanceBlocks.MULBERRY_LEAVES, VerdanceBlocks.SHRUB);

        ColorProviderRegistry.ITEM.register((stack, i) -> {
            BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();

            return Minecraft.getInstance().getBlockColors().getColor(state, null, null, i);
        }, VerdanceBlocks.MULBERRY_LEAVES, VerdanceBlocks.SHRUB);
    }
}
