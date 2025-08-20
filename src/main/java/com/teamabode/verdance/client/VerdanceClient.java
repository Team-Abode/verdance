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
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
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
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT_MIPPED, VerdanceBlocks.MULBERRY_LEAVES);

        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT,
                VerdanceBlocks.MULBERRY_SAPLING,
                VerdanceBlocks.POTTED_MULBERRY_SAPLING,
                VerdanceBlocks.ATTACHED_CANTALOUPE_STEM,
                VerdanceBlocks.CANTALOUPE_STEM,
                VerdanceBlocks.MULBERRY_DOOR,
                VerdanceBlocks.MULBERRY_TRAPDOOR,
                VerdanceBlocks.SILKWORM_EGGS,
                VerdanceBlocks.SILK_COCOON,
                VerdanceBlocks.VIOLET,
                VerdanceBlocks.POTTED_VIOLET,
                VerdanceBlocks.DESERT_BUSH,
                VerdanceBlocks.POTTED_DESERT_BUSH,
                VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH,
                VerdanceBlocks.POTTED_YELLOW_FLOWERING_DESERT_BUSH,
                VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH,
                VerdanceBlocks.POTTED_PINK_FLOWERING_DESERT_BUSH
        );
    }

    private static void addColorProviders() {
        ColorProviderRegistry.BLOCK.register((state, tintGetter, pos, i) -> tintGetter != null && pos != null ? BiomeColors.getFoliageColor(tintGetter, pos) : FoliageColors.DEFAULT, VerdanceBlocks.MULBERRY_LEAVES, VerdanceBlocks.FLOWERING_MULBERRY_LEAVES);
    }
}
