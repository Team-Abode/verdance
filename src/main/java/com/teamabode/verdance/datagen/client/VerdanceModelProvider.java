package com.teamabode.verdance.datagen.client;

import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.datagen.client.model.VerdanceModels;
import com.teamabode.verdance.datagen.client.model.VerdanceTextureMaps;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.util.Identifier;

public class VerdanceModelProvider extends FabricModelProvider {

    public VerdanceModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void createPottedMulberry(BlockStateModelGenerator generator, Block mulberryBlock, Block pottedMulberryBlock, BlockStateModelGenerator.TintType tintState) {
        TextureMap textureMapping = TextureMap.plant(mulberryBlock);
        Identifier resourceLocation = tintState.getFlowerPotCrossModel().upload(pottedMulberryBlock, textureMapping, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(pottedMulberryBlock, resourceLocation));
    }

    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        VerdanceBlockFamilies.getAllFamilies().forEach(family -> generator.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));
        generator.registerLog(VerdanceBlocks.MULBERRY_LOG).log(VerdanceBlocks.MULBERRY_LOG).wood(VerdanceBlocks.MULBERRY_WOOD);
        generator.registerLog(VerdanceBlocks.STRIPPED_MULBERRY_LOG).log(VerdanceBlocks.STRIPPED_MULBERRY_LOG).wood(VerdanceBlocks.STRIPPED_MULBERRY_WOOD);
        generator.registerSingleton(VerdanceBlocks.MULBERRY_LEAVES, TexturedModel.LEAVES);
        generator.registerTintableCrossBlockState(VerdanceBlocks.MULBERRY_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerFlowerPotPlant(VerdanceBlocks.VIOLET, VerdanceBlocks.POTTED_VIOLET, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerFlowerPotPlant(VerdanceBlocks.SHRUB, VerdanceBlocks.POTTED_SHRUB, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerFlowerPotPlant(VerdanceBlocks.YELLOW_FLOWERING_SHRUB, VerdanceBlocks.POTTED_YELLOW_FLOWERING_SHRUB, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerFlowerPotPlant(VerdanceBlocks.PINK_FLOWERING_SHRUB, VerdanceBlocks.POTTED_PINK_FLOWERING_SHRUB, BlockStateModelGenerator.TintType.NOT_TINTED);

        this.createPottedMulberry(generator, VerdanceBlocks.MULBERRY_SAPLING, VerdanceBlocks.POTTED_MULBERRY_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        generator.registerHangingSign(VerdanceBlocks.STRIPPED_MULBERRY_LOG, VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN);
        generator.registerSingleton(VerdanceBlocks.CANTALOUPE, TexturedModel.CUBE_COLUMN);
        generator.registerGourd(VerdanceBlocks.CANTALOUPE_STEM, VerdanceBlocks.ATTACHED_CANTALOUPE_STEM);

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

    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(VerdanceItems.MULBERRY, Models.GENERATED);

        generator.register(VerdanceItems.CANTALOUPE_SLICE, Models.GENERATED);
        generator.register(VerdanceItems.GRILLED_CANTALOUPE_SLICE, Models.GENERATED);
        generator.register(VerdanceItems.CANTALOUPE_JUICE, Models.GENERATED);

        generator.register(VerdanceItems.MULBERRY_BOAT, Models.GENERATED);
        generator.register(VerdanceItems.MULBERRY_CHEST_BOAT, Models.GENERATED);

        generator.register(VerdanceItems.ABODE_POTTERY_SHERD, Models.GENERATED);
        generator.register(VerdanceItems.FRILLS_POTTERY_SHERD, Models.GENERATED);
        generator.register(VerdanceItems.PITCH_POTTERY_SHERD, Models.GENERATED);
        generator.register(VerdanceItems.PRICKLE_POTTERY_SHERD, Models.GENERATED);
        generator.register(VerdanceItems.SPIRIT_POTTERY_SHERD, Models.GENERATED);
        generator.register(VerdanceItems.TRAP_POTTERY_SHERD, Models.GENERATED);
        generator.register(VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);

        generator.register(VerdanceItems.MUSIC_DISC_RANGE, Models.GENERATED);
        generator.register(VerdanceItems.DISC_FRAGMENT_RANGE, Models.GENERATED);
    }

    public final void createCushion(Block block, BlockStateModelGenerator generator) {
        TextureMap textureMapping = VerdanceTextureMaps.cushionTextureMappings(block);
        Identifier resourceLocation = VerdanceModels.CUSHION.upload(block, textureMapping, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, resourceLocation));
    }
}
