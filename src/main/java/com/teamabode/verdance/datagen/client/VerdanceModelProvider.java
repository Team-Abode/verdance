package com.teamabode.verdance.datagen.client;

import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.datagen.client.util.VerdanceModelTemplates;
import com.teamabode.verdance.datagen.client.util.VerdanceTextureMapping;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class VerdanceModelProvider extends FabricModelProvider {

    public VerdanceModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void createPottedMulberry(BlockModelGenerators generator, Block mulberryBlock, Block pottedMulberryBlock, BlockModelGenerators.TintState tintState) {
        TextureMapping textureMapping = TextureMapping.plant(mulberryBlock);
        ResourceLocation resourceLocation = tintState.getCrossPot().create(pottedMulberryBlock, textureMapping, generator.modelOutput);
        generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(pottedMulberryBlock, resourceLocation));
    }

    public void generateBlockStateModels(BlockModelGenerators generator) {
        VerdanceBlockFamilies.getAllFamilies().forEach(family -> generator.family(family.getBaseBlock()).generateFor(family));
        generator.woodProvider(VerdanceBlocks.MULBERRY_LOG).logWithHorizontal(VerdanceBlocks.MULBERRY_LOG).wood(VerdanceBlocks.MULBERRY_WOOD);
        generator.woodProvider(VerdanceBlocks.STRIPPED_MULBERRY_LOG).logWithHorizontal(VerdanceBlocks.STRIPPED_MULBERRY_LOG).wood(VerdanceBlocks.STRIPPED_MULBERRY_WOOD);
        generator.createTrivialBlock(VerdanceBlocks.MULBERRY_LEAVES, TexturedModel.LEAVES);
        generator.createCrossBlock(VerdanceBlocks.MULBERRY_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        generator.createPlant(VerdanceBlocks.VIOLET, VerdanceBlocks.POTTED_VIOLET, BlockModelGenerators.TintState.NOT_TINTED);
        this.createPottedMulberry(generator, VerdanceBlocks.MULBERRY_SAPLING, VerdanceBlocks.POTTED_MULBERRY_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);
        generator.createHangingSign(VerdanceBlocks.STRIPPED_MULBERRY_LOG, VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN);
        generator.createTrivialBlock(VerdanceBlocks.CANTALOUPE, TexturedModel.COLUMN);
        generator.createStems(VerdanceBlocks.CANTALOUPE_STEM, VerdanceBlocks.ATTACHED_CANTALOUPE_STEM);
        generator.createTrivialBlock(VerdanceBlocks.MULBERRY_CRATE, TexturedModel.CUBE_TOP_BOTTOM);


        this.createCushion(VerdanceBlocks.WHITE_CUSHION, generator);
        this.createCushion(VerdanceBlocks.LIGHT_GRAY_CUSHION, generator);
        this.createCushion(VerdanceBlocks.GRAY_CUSHION, generator);
        this.createCushion(VerdanceBlocks.BLACK_CUSHION, generator);
        this.createCushion(VerdanceBlocks.BROWN_CUSHION, generator);
        this.createCushion(VerdanceBlocks.RED_CUSHION, generator);
        this.createCushion(VerdanceBlocks.ORANGE_CUSHION, generator);
        this.createCushion(VerdanceBlocks.YELLOW_CUSHION, generator);
        this.createCushion(VerdanceBlocks.LIME_CUSHION, generator);
        this.createCushion(VerdanceBlocks.GREEN_CUSHION, generator);
        this.createCushion(VerdanceBlocks.CYAN_CUSHION, generator);
        this.createCushion(VerdanceBlocks.LIGHT_BLUE_CUSHION, generator);
        this.createCushion(VerdanceBlocks.BLUE_CUSHION, generator);
        this.createCushion(VerdanceBlocks.PURPLE_CUSHION, generator);
        this.createCushion(VerdanceBlocks.MAGENTA_CUSHION, generator);
        this.createCushion(VerdanceBlocks.PINK_CUSHION, generator);
    }

    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(VerdanceItems.MULBERRY, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(VerdanceItems.CANTALOUPE_SLICE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.GRILLED_CANTALOUPE_SLICE, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.CANTALOUPE_JUICE, ModelTemplates.FLAT_ITEM);

        generator.generateFlatItem(VerdanceItems.MULBERRY_BOAT, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(VerdanceItems.MULBERRY_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
    }

    public final void createCushion(Block block, BlockModelGenerators blockModelGenerators) {
        TextureMapping textureMapping = VerdanceTextureMapping.cushionTextureMappings(block);
        ResourceLocation resourceLocation = VerdanceModelTemplates.CUSHION.create(block, textureMapping, blockModelGenerators.modelOutput);
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, resourceLocation));
    }
}
