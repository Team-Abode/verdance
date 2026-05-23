package com.teamabode.verdance.datagen.client;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.misc.VerdanceBlockFamilies;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.BlockFamily.Variant;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;

public class VerdanceBlockStateProvider extends BlockStateProvider {

    public VerdanceBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Verdance.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.mulberryWood();
        this.stucco();
        this.cushion();
        this.cantaloupe();
        this.cantaloupeStem();
        this.attachedCantaloupeStem();

        this.createCross(VerdanceBlocks.VIOLET.get());
        this.createFlowerPotCross(VerdanceBlocks.POTTED_VIOLET.get(), VerdanceBlocks.VIOLET.get());

        this.createCross(VerdanceBlocks.SHRUB.get());
        this.createFlowerPotCross(VerdanceBlocks.POTTED_SHRUB.get(), VerdanceBlocks.SHRUB.get());

        this.createCross(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get());
        this.createFlowerPotCross(VerdanceBlocks.POTTED_YELLOW_FLOWERING_SHRUB.get(), VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get());

        this.createCross(VerdanceBlocks.PINK_FLOWERING_SHRUB.get());
        this.createFlowerPotCross(VerdanceBlocks.POTTED_PINK_FLOWERING_SHRUB.get(), VerdanceBlocks.PINK_FLOWERING_SHRUB.get());
    }

    private void mulberryWood() {
        ResourceLocation planksTexture = this.blockTexture(VerdanceBlocks.MULBERRY_PLANKS.get());

        this.createLog(VerdanceBlocks.MULBERRY_LOG.get());
        this.createLog(VerdanceBlocks.STRIPPED_MULBERRY_LOG.get());
        this.createWood(
                VerdanceBlocks.MULBERRY_WOOD.get(),
                this.blockTexture(VerdanceBlocks.MULBERRY_LOG.get())
        );
        this.createWood(
                VerdanceBlocks.STRIPPED_MULBERRY_WOOD.get(),
                this.blockTexture(VerdanceBlocks.STRIPPED_MULBERRY_LOG.get())
        );
        this.simpleBlockWithItem(VerdanceBlocks.MULBERRY_PLANKS.get(), cubeAll(VerdanceBlocks.MULBERRY_PLANKS.get()));
        this.createStairsWithItem(VerdanceBlocks.MULBERRY_STAIRS.get(), planksTexture);
        this.createSlabWithItem(VerdanceBlocks.MULBERRY_SLAB.get(), planksTexture);
        this.createFenceWithItem(VerdanceBlocks.MULBERRY_FENCE.get(), planksTexture);
        this.createFenceGatesWithItem(VerdanceBlocks.MULBERRY_FENCE_GATE.get(), planksTexture);
        this.doorBlock(
                (DoorBlock) VerdanceBlocks.MULBERRY_DOOR.get(),
                Verdance.id("block/mulberry_door_bottom"),
                Verdance.id("block/mulberry_door_top")
        );
        this.createTrapdoorWithItem(VerdanceBlocks.MULBERRY_TRAPDOOR.get());
        this.createButtonWithItem(VerdanceBlocks.MULBERRY_BUTTON.get(), planksTexture);
        this.createPressurePlateWithItem(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.get(), planksTexture);
        this.signBlock(
                (StandingSignBlock) VerdanceBlocks.MULBERRY_SIGN.get(),
                (WallSignBlock) VerdanceBlocks.MULBERRY_WALL_SIGN.get(),
                planksTexture
        );
        this.hangingSignBlock(
                (CeilingHangingSignBlock) VerdanceBlocks.MULBERRY_HANGING_SIGN.get(),
                (WallHangingSignBlock) VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN.get(),
                planksTexture
        );
        this.createLeavesWithItem(VerdanceBlocks.MULBERRY_LEAVES.get());
        this.createCross(VerdanceBlocks.MULBERRY_SAPLING.get());
        this.createFlowerPotCross(VerdanceBlocks.POTTED_MULBERRY_SAPLING.get(), VerdanceBlocks.MULBERRY_SAPLING.get());
    }

    private void stucco() {
        var families = VerdanceBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel);

        families.forEach(family -> {
            var baseTexture = this.blockTexture(family.getBaseBlock());

            this.simpleBlockWithItem(
                    family.getBaseBlock(),
                    cubeAll(family.getBaseBlock())
            );
            this.createStairsWithItem(
                    family.get(Variant.STAIRS),
                    baseTexture
            );
            this.createSlabWithItem(
                    family.get(Variant.SLAB),
                    baseTexture
            );
            this.createWallWithItem(
                    family.get(Variant.WALL),
                    baseTexture
            );
        });
    }

    private void cantaloupe() {
        var texture = this.blockTexture(VerdanceBlocks.CANTALOUPE.get());

        var model = models()
                .withExistingParent(
                        this.getResourceLocation(VerdanceBlocks.CANTALOUPE.get()).getPath(),
                        ResourceLocation.withDefaultNamespace("block/cube_column")
                )
                .texture("end", texture.withPath(path -> path + "_top"))
                .texture("side", texture.withPath(path -> path + "_side"));

        this.simpleBlock(
                VerdanceBlocks.CANTALOUPE.get(),
                model
        );
        this.simpleBlockItem(
                VerdanceBlocks.CANTALOUPE.get(),
                model
        );
    }

    private void cantaloupeStem() {
        var texture = this.blockTexture(VerdanceBlocks.CANTALOUPE_STEM.get());

        this.getVariantBuilder(VerdanceBlocks.CANTALOUPE_STEM.get()).forAllStates(state -> {
            int age = state.getValue(StemBlock.AGE);

            var model = models()
                    .withExistingParent(
                            this.getResourceLocation(VerdanceBlocks.CANTALOUPE_STEM.get()).getPath() + "_stage" + age,
                            ResourceLocation.withDefaultNamespace("block/stem_growth" + age)
                    )
                    .texture("stem", texture);

            return ConfiguredModel.builder().modelFile(model).build();
        });
    }

    private void attachedCantaloupeStem() {
        var stemTexture = this.blockTexture(VerdanceBlocks.CANTALOUPE_STEM.get());
        var upperStemTexture = this.blockTexture(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM.get());

        var model = models()
                .withExistingParent(
                        this.getResourceLocation(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM.get()).getPath(),
                        ResourceLocation.withDefaultNamespace("block/stem_fruit")
                )
                .texture("stem", stemTexture)
                .texture("upperstem", upperStemTexture);

        this.getVariantBuilder(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM.get()).forAllStates(state -> {
            Direction direction = state.getValue(AttachedStemBlock.FACING);
            int yRot = (int) direction.toYRot() - 90;

            if (direction == Direction.SOUTH) {
                yRot = 270;
            }

            return ConfiguredModel.builder()
                    .modelFile(model)
                    .rotationY(yRot)
                    .build();
        });
    }

    private void cushion() {
        List<Block> cushions = List.of(
                VerdanceBlocks.BLACK_CUSHION.get(),
                VerdanceBlocks.BLUE_CUSHION.get(),
                VerdanceBlocks.BROWN_CUSHION.get(),
                VerdanceBlocks.CYAN_CUSHION.get(),
                VerdanceBlocks.GRAY_CUSHION.get(),
                VerdanceBlocks.GREEN_CUSHION.get(),
                VerdanceBlocks.LIGHT_BLUE_CUSHION.get(),
                VerdanceBlocks.LIGHT_GRAY_CUSHION.get(),
                VerdanceBlocks.LIME_CUSHION.get(),
                VerdanceBlocks.MAGENTA_CUSHION.get(),
                VerdanceBlocks.ORANGE_CUSHION.get(),
                VerdanceBlocks.PINK_CUSHION.get(),
                VerdanceBlocks.PURPLE_CUSHION.get(),
                VerdanceBlocks.RED_CUSHION.get(),
                VerdanceBlocks.YELLOW_CUSHION.get(),
                VerdanceBlocks.WHITE_CUSHION.get()
        );
        for (Block cushion : cushions) {
            this.createCushionWithItem(cushion);
        }
    }

    private void createLog(Block block) {
        this.logBlock((RotatedPillarBlock) block);
        this.simpleBlockItem(block, models().withExistingParent(
                this.getResourceLocation(block).getPath(),
                ResourceLocation.withDefaultNamespace("block/cube_column")
        ));
    }

    private void createWood(Block block, ResourceLocation texture) {
        this.axisBlock((RotatedPillarBlock) block, texture, texture);
        this.simpleBlockItem(block, models().withExistingParent(
                this.getResourceLocation(block).getPath(),
                ResourceLocation.withDefaultNamespace("block/cube_column")
        ));
    }

    private void createStairsWithItem(Block block, ResourceLocation texture) {
        this.stairsBlock((StairBlock) block, texture);
        this.simpleBlockItem(block, models().stairs(
                this.getResourceLocation(block).getPath(),
                texture,
                texture,
                texture
        ));
    }

    private void createSlabWithItem(Block block, ResourceLocation texture) {
        this.slabBlock((SlabBlock) block, texture, texture);
        this.simpleBlockItem(block, models().slab(
                this.getResourceLocation(block).getPath(),
                texture,
                texture,
                texture
        ));
    }

    private void createFenceWithItem(Block block, ResourceLocation texture) {
        this.fenceBlock((FenceBlock) block, texture);
        this.simpleBlockItem(block, models().fenceInventory(
                this.getResourceLocation(block).getPath(),
                texture
        ));
    }

    private void createFenceGatesWithItem(Block block, ResourceLocation texture) {
        this.fenceGateBlock((FenceGateBlock) block, texture);
        this.simpleBlockItem(block, models().fenceGate(
                this.getResourceLocation(block).getPath(),
                texture
        ));
    }

    private void createTrapdoorWithItem(Block block) {
        var texture = this.blockTexture(block);

        this.trapdoorBlock(
                (TrapDoorBlock) block,
                texture,
                true
        );
        this.simpleBlockItem(block, models().trapdoorOrientableBottom(
                this.getResourceLocation(block).getPath(),
                texture
        ));
    }

    private void createButtonWithItem(Block block, ResourceLocation texture) {
        this.buttonBlock((ButtonBlock) block, texture);
        this.simpleBlockItem(block, models().buttonInventory(
                this.getResourceLocation(block).getPath(),
                texture
        ));
    }

    private void createPressurePlateWithItem(Block block, ResourceLocation texture) {
        this.pressurePlateBlock((PressurePlateBlock) block, texture);
        this.simpleBlockItem(block, models().pressurePlate(
                this.getResourceLocation(block).getPath(),
                texture
        ));
    }

    private void createLeavesWithItem(Block block) {
        var model = this.models().leaves(
                this.getResourceLocation(block).getPath(),
                this.blockTexture(block)
        );
        this.simpleBlockWithItem(block, model);
    }

    private void createWallWithItem(Block block, ResourceLocation texture) {
        this.wallBlock((WallBlock) block, texture);
        this.simpleBlockItem(block, models().wallInventory(
                this.getResourceLocation(block).getPath(),
                texture
        ));
    }

    private void createCross(Block block) {
        var texture = this.blockTexture(block);

        this.simpleBlock(block, models()
                .withExistingParent(
                        this.getResourceLocation(block).getPath(),
                        ResourceLocation.withDefaultNamespace("block/cross")
                )
                .texture("cross", texture)
        );
    }

    private void createFlowerPotCross(Block block, Block flowerBlock) {
        simpleBlock(block, ConfiguredModel
                .builder()
                .modelFile(
                        models()
                                .withExistingParent(
                                    this.getResourceLocation(block).getPath(),
                                    ResourceLocation.withDefaultNamespace("block/flower_pot_cross")
                                )
                                .texture(
                                        "plant",
                                        this.blockTexture(flowerBlock)
                                )
                )
                .build());
    }

    private void createCushionWithItem(Block block) {
        var texture = this.blockTexture(block);
        var model = models()
                .withExistingParent(this.getResourceLocation(block).getPath(), Verdance.id("template_cushion"))
                .texture("top", texture)
                .texture("side", texture.withPath(path -> path + "_side"));

        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }

    private ResourceLocation getResourceLocation(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
