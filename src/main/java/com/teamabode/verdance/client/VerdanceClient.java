package com.teamabode.verdance.client;

import com.teamabode.verdance.client.model.SilkMothEntityModel;
import com.teamabode.verdance.client.model.SilkwormEntityModel;
import com.teamabode.verdance.client.renderer.SilkCocoonBlockEntityRenderer;
import com.teamabode.verdance.client.renderer.SilkMothEntityRenderer;
import com.teamabode.verdance.client.renderer.SilkwormEntityRenderer;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.world.biome.FoliageColors;

public class VerdanceClient implements ClientModInitializer {

    public void onInitializeClient() {
        setRenderTypes();
        addColorProviders();
        registerModelLayers();
        registerRenderers();
    }

    private static void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(SilkMothEntityModel.LAYER, SilkMothEntityModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SilkwormEntityModel.LAYER_LOCATION, SilkwormEntityModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(SilkCocoonBlockEntityRenderer.LAYER_LOCATION, SilkCocoonBlockEntityRenderer::createBodyLayer);
    }

    private static void registerRenderers() {
        EntityRendererRegistry.register(VerdanceEntityTypes.SILK_MOTH, SilkMothEntityRenderer::new);
        EntityRendererRegistry.register(VerdanceEntityTypes.SILKWORM, SilkwormEntityRenderer::new);
        EntityRendererRegistry.register(VerdanceEntityTypes.CUSHION, EmptyEntityRenderer::new);
        BlockEntityRendererFactories.register(VerdanceBlockEntityTypes.SILK_COCOON, SilkCocoonBlockEntityRenderer::new);
    }

    private static void setRenderTypes() {
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.POTTED_MULBERRY_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.CANTALOUPE_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.MULBERRY_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.SILKWORM_EGGS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.SILK_COCOON, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.VIOLET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.POTTED_VIOLET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.DESERT_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.POTTED_DESERT_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.POTTED_YELLOW_FLOWERING_DESERT_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(VerdanceBlocks.POTTED_PINK_FLOWERING_DESERT_BUSH, RenderLayer.getCutout());
    }

    private static void addColorProviders() {
        ColorProviderRegistry.BLOCK.register((state, tintGetter, pos, i) -> tintGetter != null && pos != null ? BiomeColors.getFoliageColor(tintGetter, pos) : FoliageColors.DEFAULT, VerdanceBlocks.MULBERRY_LEAVES, VerdanceBlocks.FLOWERING_MULBERRY_LEAVES);
    }
}
