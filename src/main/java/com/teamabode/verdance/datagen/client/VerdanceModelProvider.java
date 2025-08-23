package com.teamabode.verdance.datagen.client;

import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.datagen.client.model.VerdanceModels;
import com.teamabode.verdance.datagen.client.model.VerdanceTextureMaps;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class VerdanceModelProvider extends FabricModelProvider {

    public VerdanceModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void createCushionBlockModel(Block block, BlockStateModelGenerator generator) {
        TextureMap map = VerdanceTextureMaps.cushionTextureMappings(block);
        Identifier id = VerdanceModels.CUSHION.upload(block, map, generator.modelCollector);
        generator.blockStateCollector.accept(BlockStateModelGenerator.createSingletonBlockState(block, BlockStateModelGenerator.createWeightedVariant(id)));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        VerdanceBlockFamilies.getAllFamilies().forEach(family -> generator.registerCubeAllModelTexturePool(family.getBaseBlock()).family(family));

        generator.createLogTexturePool(VerdanceBlocks.MULBERRY_LOG).log(VerdanceBlocks.MULBERRY_LOG).wood(VerdanceBlocks.MULBERRY_WOOD);
        generator.createLogTexturePool(VerdanceBlocks.STRIPPED_MULBERRY_LOG).log(VerdanceBlocks.STRIPPED_MULBERRY_LOG).wood(VerdanceBlocks.STRIPPED_MULBERRY_WOOD);
        generator.registerTintedBlockAndItem(VerdanceBlocks.MULBERRY_LEAVES, TexturedModel.LEAVES, -12012264);
        generator.registerFlowerPotPlantAndItem(VerdanceBlocks.VIOLET, VerdanceBlocks.POTTED_VIOLET, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerFlowerPotPlantAndItem(VerdanceBlocks.DESERT_BUSH, VerdanceBlocks.POTTED_DESERT_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerFlowerPotPlantAndItem(VerdanceBlocks.YELLOW_FLOWERING_DESERT_BUSH, VerdanceBlocks.POTTED_YELLOW_FLOWERING_DESERT_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerFlowerPotPlantAndItem(VerdanceBlocks.PINK_FLOWERING_DESERT_BUSH, VerdanceBlocks.POTTED_PINK_FLOWERING_DESERT_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED);
        generator.registerFlowerPotPlant(VerdanceBlocks.MULBERRY_SAPLING, VerdanceBlocks.POTTED_MULBERRY_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);

        generator.registerHangingSign(VerdanceBlocks.STRIPPED_MULBERRY_LOG, VerdanceBlocks.MULBERRY_HANGING_SIGN, VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN);
        generator.registerSingleton(VerdanceBlocks.CANTALOUPE, TexturedModel.CUBE_COLUMN);
        generator.registerGourd(VerdanceBlocks.CANTALOUPE_STEM, VerdanceBlocks.ATTACHED_CANTALOUPE_STEM);

        this.createCushionBlockModel(VerdanceBlocks.WHITE_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.LIGHT_GRAY_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.GRAY_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.BLACK_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.BROWN_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.RED_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.ORANGE_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.YELLOW_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.LIME_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.GREEN_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.CYAN_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.LIGHT_BLUE_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.BLUE_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.PURPLE_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.MAGENTA_CUSHION, generator);
        this.createCushionBlockModel(VerdanceBlocks.PINK_CUSHION, generator);
    }

    @Override
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
        generator.register(VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE, Models.GENERATED);

        generator.register(VerdanceItems.MUSIC_DISC_RANGE, Models.GENERATED);
        generator.register(VerdanceItems.DISC_FRAGMENT_RANGE, Models.GENERATED);

        generator.register(VerdanceItems.SILK_MOTH_SPAWN_EGG, Models.GENERATED);
        generator.register(VerdanceItems.SILKWORM_SPAWN_EGG, Models.GENERATED);
    }
}
