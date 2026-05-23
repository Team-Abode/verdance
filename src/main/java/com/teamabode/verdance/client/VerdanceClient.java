package com.teamabode.verdance.client;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.client.model.SilkMothModel;
import com.teamabode.verdance.client.model.SilkwormModel;
import com.teamabode.verdance.client.renderer.SilkCocoonBlockEntityRenderer;
import com.teamabode.verdance.client.renderer.SilkMothRenderer;
import com.teamabode.verdance.client.renderer.SilkwormRenderer;
import com.teamabode.verdance.core.misc.VerdanceWoodTypes;
import com.teamabode.verdance.core.registry.VerdanceBlockEntityTypes;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = Verdance.MOD_ID, value = Dist.CLIENT)
public class VerdanceClient {

    @SubscribeEvent
    private static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SilkMothModel.LAYER_LOCATION, SilkMothModel::createBodyLayer);
        event.registerLayerDefinition(SilkwormModel.LAYER_LOCATION, SilkwormModel::createBodyLayer);
        event.registerLayerDefinition(SilkCocoonBlockEntityRenderer.LAYER_LOCATION, SilkCocoonBlockEntityRenderer::createLayer);
    }

    @SubscribeEvent
    private static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(VerdanceEntityTypes.SILK_MOTH.get(), SilkMothRenderer::new);
        event.registerEntityRenderer(VerdanceEntityTypes.SILKWORM.get(), SilkwormRenderer::new);
        event.registerEntityRenderer(VerdanceEntityTypes.CUSHION.get(), NoopRenderer::new);
        event.registerBlockEntityRenderer(VerdanceBlockEntityTypes.SILK_COCOON.get(), SilkCocoonBlockEntityRenderer::new);
    }

    @SubscribeEvent
    private static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.MULBERRY_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.MULBERRY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.POTTED_MULBERRY_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.CANTALOUPE_STEM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.MULBERRY_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.MULBERRY_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.SILKWORM_EGGS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.SILK_COCOON.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.VIOLET.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.POTTED_VIOLET.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.SHRUB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.POTTED_SHRUB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.POTTED_YELLOW_FLOWERING_SHRUB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.PINK_FLOWERING_SHRUB.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(VerdanceBlocks.POTTED_PINK_FLOWERING_SHRUB.get(), RenderType.cutout());

        event.enqueueWork(() -> {
            Sheets.addWoodType(VerdanceWoodTypes.MULBERRY);
        });
    }

    @SubscribeEvent
    private static void onRegisterBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register(
                (state, tintGetter, pos, i) ->
                        tintGetter != null && pos != null ? BiomeColors.getAverageFoliageColor(tintGetter, pos) : FoliageColor.getDefaultColor(),
                VerdanceBlocks.MULBERRY_LEAVES.get(),
                VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get()
        );
    }

    @SubscribeEvent
    private static void onRegisterItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, tintIndex) -> {
                    BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();

                    return event.getBlockColors().getColor(state, null, null, tintIndex);
                },
                VerdanceBlocks.MULBERRY_LEAVES.get(),
                VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get()
        );
    }
}
