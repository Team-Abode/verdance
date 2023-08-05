package com.teamabode.verdance.core.data.client;

import com.teamabode.verdance.core.misc.datagen.VerdanceFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class VerdanceModelProvider extends FabricModelProvider {

    public VerdanceModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void createPottedMulberry(BlockModelGenerators generator, Block mulberryBlock, Block pottedMulberryBlock, BlockModelGenerators.TintState tintState) {
        TextureMapping textureMapping = TextureMapping.plant(mulberryBlock);
        ResourceLocation resourceLocation = tintState.getCrossPot().create(pottedMulberryBlock, textureMapping, generator.modelOutput);
        generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pottedMulberryBlock, resourceLocation));
    }

    private void createCabinet(BlockModelGenerators generator, Block cabinetBlock) {
        TexturedModel.Provider modelProvider = TexturedModel.ORIENTABLE_ONLY_TOP;
        ResourceLocation resourceLocation = modelProvider.create(cabinetBlock, generator.modelOutput);
        ResourceLocation resourceLocation2 = TextureMapping.getBlockTexture(cabinetBlock, "_front_open");
        ResourceLocation resourceLocation3 = modelProvider.get(cabinetBlock).updateTextures((textureMapping) -> {
            textureMapping.put(TextureSlot.FRONT, resourceLocation2);
        }).createWithSuffix(cabinetBlock, "_open", generator.modelOutput);
        generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(cabinetBlock).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.OPEN, resourceLocation3, resourceLocation)).with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }

    public void generateBlockStateModels(BlockModelGenerators generator) {
        VerdanceFamilies.getAllFamilies().forEach(family -> generator.family(family.getBaseBlock()).generateFor(family));
        generator.woodProvider(VerdanceBlocks.MULBERRY_LOG).logWithHorizontal(VerdanceBlocks.MULBERRY_LOG).wood(VerdanceBlocks.MULBERRY_WOOD);
        generator.woodProvider(VerdanceBlocks.STRIPPED_MULBERRY_LOG).logWithHorizontal(VerdanceBlocks.STRIPPED_MULBERRY_LOG).wood(VerdanceBlocks.STRIPPED_MULBERRY_WOOD);
        generator.createTrivialBlock(VerdanceBlocks.MULBERRY_LEAVES, TexturedModel.LEAVES);
        generator.createCrossBlock(VerdanceBlocks.MULBERRY_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        this.createPottedMulberry(generator, VerdanceBlocks.MULBERRY_SAPLING, VerdanceBlocks.POTTED_MULBERRY_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        generator.createHangingSign(VerdanceBlocks.STRIPPED_MULBERRY_LOG, VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN);
        createCabinet(generator, VerdanceBlocks.MULBERRY_CABINET);
    }

    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(VerdanceItems.MULBERRY, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.CANTALOUPE_SLICE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.CANTALOUPE_SEEDS, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(VerdanceItems.MULBERRY_BOAT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.MULBERRY_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(VerdanceItems.MUSIC_DISC_RANGE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.DISC_FRAGMENT_RANGE, ModelTemplates.FLAT_ITEM);
    }
}
