package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.*;
import com.teamabode.verdance.core.misc.VerdanceBlockSetTypes;
import com.teamabode.verdance.core.misc.VerdanceBlockSoundGroups;
import com.teamabode.verdance.core.misc.VerdanceSaplingGenerators;
import com.teamabode.verdance.core.misc.VerdanceWoodTypes;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.OffsetType;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;

public class VerdanceBlocks {
    public static final Block MULBERRY_LOG = register(
            "mulberry_log",
            Blocks.createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY)
    );
    public static final Block MULBERRY_WOOD = register(
            "mulberry_wood",
            Blocks.createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY)
    );
    public static final Block STRIPPED_MULBERRY_LOG = register(
            "stripped_mulberry_log",
            Blocks.createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW)
    );
    public static final Block STRIPPED_MULBERRY_WOOD = register(
            "stripped_mulberry_wood",
            Blocks.createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW)
    );
    public static final Block MULBERRY_PLANKS = register(
            "mulberry_planks",
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(2.0F, 3.0F)
                .instrument(Instrument.BASS)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
    );
    public static final Block MULBERRY_STAIRS = register(
            "mulberry_stairs",
            new StairsBlock(MULBERRY_PLANKS.getDefaultState(), Settings.copy(MULBERRY_PLANKS))
    );
    public static final Block MULBERRY_SLAB = register(
            "mulberry_slab",
            new SlabBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(2.0F, 3.0F)
                    .instrument(Instrument.BASS)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable())
    );
    public static final Block MULBERRY_FENCE = register(
            "mulberry_fence",
            new FenceBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(2.0F, 3.0F)
                    .instrument(Instrument.BASS)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable())
    );
    public static final Block MULBERRY_FENCE_GATE = register(
            "mulberry_fence_gate",
            new FenceGateBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid()
                    .strength(2.0F, 3.0F)
                    .instrument(Instrument.BASS)
                    .burnable(), VerdanceWoodTypes.MULBERRY)
    );
    public static final Block MULBERRY_DOOR = register(
            "mulberry_door",
            new DoorBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(3.0F)
                    .instrument(Instrument.BASS)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .burnable(), VerdanceBlockSetTypes.MULBERRY)
    );
    public static final Block MULBERRY_TRAPDOOR = register(
            "mulberry_trapdoor",
            new TrapdoorBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(3.0F)
                    .instrument(Instrument.BASS)
                    .allowsSpawning(Blocks::never)
                    .nonOpaque()
                    .burnable(), VerdanceBlockSetTypes.MULBERRY)
    );
    public static final Block MULBERRY_PRESSURE_PLATE = register(
            "mulberry_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid()
                    .strength(0.5f)
                    .instrument(Instrument.BASS)
                    .noCollision()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .burnable(), VerdanceBlockSetTypes.MULBERRY)
    );
    public static final Block MULBERRY_BUTTON = register(
            "mulberry_button",
            Blocks.createWoodenButtonBlock(VerdanceBlockSetTypes.MULBERRY)
    );
    public static final Block MULBERRY_SIGN = registerWithoutItem(
            "mulberry_sign",
            new SignBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid()
                    .instrument(Instrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .burnable(), VerdanceWoodTypes.MULBERRY)
    );
    public static final Block MULBERRY_WALL_SIGN = registerWithoutItem(
            "mulberry_wall_sign",
            new WallSignBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid()
                    .instrument(Instrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .dropsLike(MULBERRY_SIGN)
                    .burnable(), VerdanceWoodTypes.MULBERRY)
    );
    public static final Block MULBERRY_HANGING_SIGN = registerWithoutItem(
            "mulberry_hanging_sign",
            new HangingSignBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid()
                    .instrument(Instrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .burnable(), VerdanceWoodTypes.MULBERRY)
    );
    public static final Block MULBERRY_WALL_HANGING_SIGN = registerWithoutItem(
            "mulberry_wall_hanging_sign",
            new WallHangingSignBlock(Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid()
                    .instrument(Instrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .dropsLike(MULBERRY_HANGING_SIGN)
                    .burnable(), VerdanceWoodTypes.MULBERRY)
    );
    public static final Block MULBERRY_LEAVES = register(
            "mulberry_leaves", Blocks.createLeavesBlock(BlockSoundGroup.GRASS)
    );
    public static final Block FLOWERING_MULBERRY_LEAVES = register(
            "flowering_mulberry_leaves", Blocks.createLeavesBlock(BlockSoundGroup.GRASS)
    );
    public static final Block MULBERRY_SAPLING = registerWithoutItem(
            "mulberry_sapling",
            new SaplingBlock(VerdanceSaplingGenerators.MULBERRY, Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY))
    );
    public static final Block POTTED_MULBERRY_SAPLING = registerWithoutItem(
            "potted_mulberry_sapling",
            Blocks.createFlowerPotBlock(MULBERRY_SAPLING)
    );
    public static final Block CANTALOUPE = register(
            "cantaloupe",
            new CantaloupeBlock(Settings.create()
                    .mapColor(MapColor.LIME)
                    .strength(1.0F)
                    .sounds(BlockSoundGroup.WOOD))
    );
    public static final Block ATTACHED_CANTALOUPE_STEM = registerWithoutItem(
            "attached_cantaloupe_stem",
            new AttachedStemBlock(
                (GourdBlock) CANTALOUPE,
                () -> VerdanceItems.CANTALOUPE_SEEDS,
                Settings.create()
                        .noCollision()
                        .ticksRandomly()
                        .breakInstantly()
                        .sounds(BlockSoundGroup.STEM)
                        .pistonBehavior(PistonBehavior.DESTROY))
    );
    public static final Block CANTALOUPE_STEM = registerWithoutItem(
            "cantaloupe_stem",
            new StemBlock(
                (GourdBlock) CANTALOUPE,
                () -> VerdanceItems.CANTALOUPE_SEEDS,
                Settings.create()
                        .noCollision()
                        .ticksRandomly()
                        .breakInstantly()
                        .sounds(BlockSoundGroup.STEM)
                        .pistonBehavior(PistonBehavior.DESTROY))
    );
    public static final Block WHITE_CUSHION = register(
            "white_cushion",
            cushion(DyeColor.WHITE)
    );
    public static final Block LIGHT_GRAY_CUSHION = register(
            "light_gray_cushion",
            cushion(DyeColor.LIGHT_GRAY)
    );
    public static final Block GRAY_CUSHION = register(
            "gray_cushion",
            cushion(DyeColor.GRAY)
    );
    public static final Block BLACK_CUSHION = register(
            "black_cushion",
            cushion(DyeColor.BLACK)
    );
    public static final Block BROWN_CUSHION = register(
            "brown_cushion",
            cushion(DyeColor.BROWN)
    );
    public static final Block RED_CUSHION = register(
            "red_cushion",
            cushion(DyeColor.RED)
    );
    public static final Block ORANGE_CUSHION = register(
            "orange_cushion",
            cushion(DyeColor.ORANGE)
    );
    public static final Block YELLOW_CUSHION = register(
            "yellow_cushion",
            cushion(DyeColor.YELLOW)
    );
    public static final Block LIME_CUSHION = register(
            "lime_cushion",
            cushion(DyeColor.LIME)
    );
    public static final Block GREEN_CUSHION = register(
            "green_cushion",
            cushion(DyeColor.GREEN)
    );
    public static final Block CYAN_CUSHION = register(
            "cyan_cushion",
            cushion(DyeColor.CYAN)
    );
    public static final Block LIGHT_BLUE_CUSHION = register(
            "light_blue_cushion",
            cushion(DyeColor.LIGHT_BLUE)
    );
    public static final Block BLUE_CUSHION = register(
            "blue_cushion",
            cushion(DyeColor.BLUE)
    );
    public static final Block PURPLE_CUSHION = register(
            "purple_cushion",
            cushion(DyeColor.PURPLE)
    );
    public static final Block MAGENTA_CUSHION = register(
            "magenta_cushion",
            cushion(DyeColor.MAGENTA)
    );
    public static final Block PINK_CUSHION = register(
            "pink_cushion",
            cushion(DyeColor.PINK)
    );
    public static final Block WHITE_STUCCO = register(
            "white_stucco",
            stucco(DyeColor.WHITE)
    );
    public static final Block WHITE_STUCCO_STAIRS = register(
            "white_stucco_stairs",
            new StairsBlock(WHITE_STUCCO.getDefaultState(), Settings.copy(WHITE_STUCCO))
    );
    public static final Block WHITE_STUCCO_SLAB = register(
            "white_stucco_slab",
            new SlabBlock(Settings.copy(WHITE_STUCCO))
    );
    public static final Block WHITE_STUCCO_WALL = register(
            "white_stucco_wall",
            new WallBlock(Settings.copy(WHITE_STUCCO))
    );
    public static final Block LIGHT_GRAY_STUCCO = register(
            "light_gray_stucco",
            stucco(DyeColor.LIGHT_GRAY)
    );
    public static final Block LIGHT_GRAY_STUCCO_STAIRS = register(
            "light_gray_stucco_stairs",
            new StairsBlock(LIGHT_GRAY_STUCCO.getDefaultState(), Settings.copy(LIGHT_GRAY_STUCCO))
    );
    public static final Block LIGHT_GRAY_STUCCO_SLAB = register(
            "light_gray_stucco_slab",
            new SlabBlock(Settings.copy(LIGHT_GRAY_STUCCO))
    );
    public static final Block LIGHT_GRAY_STUCCO_WALL = register(
            "light_gray_stucco_wall",
            new WallBlock(Settings.copy(LIGHT_GRAY_STUCCO))
    );
    public static final Block GRAY_STUCCO = register(
            "gray_stucco",
            stucco(DyeColor.GRAY)
    );
    public static final Block GRAY_STUCCO_STAIRS = register(
            "gray_stucco_stairs",
            new StairsBlock(GRAY_STUCCO.getDefaultState(), Settings.copy(GRAY_STUCCO))
    );
    public static final Block GRAY_STUCCO_SLAB = register(
            "gray_stucco_slab",
            new SlabBlock(Settings.copy(GRAY_STUCCO))
    );
    public static final Block GRAY_STUCCO_WALL = register(
            "gray_stucco_wall",
            new WallBlock(Settings.copy(GRAY_STUCCO))
    );
    public static final Block BLACK_STUCCO = register(
            "black_stucco",
            stucco(DyeColor.BLACK)
    );
    public static final Block BLACK_STUCCO_STAIRS = register(
            "black_stucco_stairs",
            new StairsBlock(BLACK_STUCCO.getDefaultState(), Settings.copy(BLACK_STUCCO))
    );
    public static final Block BLACK_STUCCO_SLAB = register(
            "black_stucco_slab",
            new SlabBlock(Settings.copy(BLACK_STUCCO))
    );
    public static final Block BLACK_STUCCO_WALL = register(
            "black_stucco_wall",
            new WallBlock(Settings.copy(BLACK_STUCCO))
    );
    public static final Block BROWN_STUCCO = register(
            "brown_stucco",
            stucco(DyeColor.BROWN)
    );
    public static final Block BROWN_STUCCO_STAIRS = register(
            "brown_stucco_stairs",
            new StairsBlock(BROWN_STUCCO.getDefaultState(), Settings.copy(BROWN_STUCCO))
    );
    public static final Block BROWN_STUCCO_SLAB = register(
            "brown_stucco_slab",
            new SlabBlock(Settings.copy(BROWN_STUCCO))
    );
    public static final Block BROWN_STUCCO_WALL = register(
            "brown_stucco_wall",
            new WallBlock(Settings.copy(BROWN_STUCCO))
    );
    public static final Block RED_STUCCO = register(
            "red_stucco",
            stucco(DyeColor.RED)
    );
    public static final Block RED_STUCCO_STAIRS = register(
            "red_stucco_stairs",
            new StairsBlock(RED_STUCCO.getDefaultState(), Settings.copy(RED_STUCCO))
    );
    public static final Block RED_STUCCO_SLAB = register(
            "red_stucco_slab",
            new SlabBlock(Settings.copy(RED_STUCCO))
    );
    public static final Block RED_STUCCO_WALL = register(
            "red_stucco_wall",
            new WallBlock(Settings.copy(RED_STUCCO))
    );
    public static final Block ORANGE_STUCCO = register(
            "orange_stucco",
            stucco(DyeColor.ORANGE)
    );
    public static final Block ORANGE_STUCCO_STAIRS = register(
            "orange_stucco_stairs",
            new StairsBlock(ORANGE_STUCCO.getDefaultState(), Settings.copy(ORANGE_STUCCO))
    );
    public static final Block ORANGE_STUCCO_SLAB = register(
            "orange_stucco_slab",
            new SlabBlock(Settings.copy(ORANGE_STUCCO))
    );
    public static final Block ORANGE_STUCCO_WALL = register(
            "orange_stucco_wall",
            new WallBlock(Settings.copy(ORANGE_STUCCO))
    );
    public static final Block YELLOW_STUCCO = register(
            "yellow_stucco",
            stucco(DyeColor.YELLOW)
    );
    public static final Block YELLOW_STUCCO_STAIRS = register(
            "yellow_stucco_stairs",
            new StairsBlock(YELLOW_STUCCO.getDefaultState(), Settings.copy(YELLOW_STUCCO))
    );
    public static final Block YELLOW_STUCCO_SLAB = register(
            "yellow_stucco_slab",
            new SlabBlock(Settings.copy(YELLOW_STUCCO))
    );
    public static final Block YELLOW_STUCCO_WALL = register(
            "yellow_stucco_wall",
            new WallBlock(Settings.copy(YELLOW_STUCCO))
    );
    public static final Block LIME_STUCCO = register(
            "lime_stucco",
            stucco(DyeColor.LIME)
    );
    public static final Block LIME_STUCCO_STAIRS = register(
            "lime_stucco_stairs",
            new StairsBlock(LIME_STUCCO.getDefaultState(), Settings.copy(LIME_STUCCO))
    );
    public static final Block LIME_STUCCO_SLAB = register(
            "lime_stucco_slab",
            new SlabBlock(Settings.copy(LIME_STUCCO))
    );
    public static final Block LIME_STUCCO_WALL = register(
            "lime_stucco_wall",
            new WallBlock(Settings.copy(LIME_STUCCO))
    );
    public static final Block GREEN_STUCCO = register(
            "green_stucco",
            stucco(DyeColor.GREEN)
    );
    public static final Block GREEN_STUCCO_STAIRS = register(
            "green_stucco_stairs",
            new StairsBlock(GREEN_STUCCO.getDefaultState(), Settings.copy(GREEN_STUCCO))
    );
    public static final Block GREEN_STUCCO_SLAB = register(
            "green_stucco_slab",
            new SlabBlock(Settings.copy(GREEN_STUCCO))
    );
    public static final Block GREEN_STUCCO_WALL = register(
            "green_stucco_wall",
            new WallBlock(Settings.copy(GREEN_STUCCO))
    );
    public static final Block CYAN_STUCCO = register(
            "cyan_stucco",
            stucco(DyeColor.CYAN)
    );
    public static final Block CYAN_STUCCO_STAIRS = register(
            "cyan_stucco_stairs",
            new StairsBlock(CYAN_STUCCO.getDefaultState(), Settings.copy(CYAN_STUCCO))
    );
    public static final Block CYAN_STUCCO_SLAB = register(
            "cyan_stucco_slab",
            new SlabBlock(Settings.copy(CYAN_STUCCO))
    );
    public static final Block CYAN_STUCCO_WALL = register(
            "cyan_stucco_wall",
            new WallBlock(Settings.copy(CYAN_STUCCO))
    );
    public static final Block LIGHT_BLUE_STUCCO = register(
            "light_blue_stucco",
            stucco(DyeColor.LIGHT_BLUE)
    );
    public static final Block LIGHT_BLUE_STUCCO_STAIRS = register(
            "light_blue_stucco_stairs",
            new StairsBlock(LIGHT_BLUE_STUCCO.getDefaultState(), Settings.copy(LIGHT_BLUE_STUCCO))
    );
    public static final Block LIGHT_BLUE_STUCCO_SLAB = register(
            "light_blue_stucco_slab",
            new SlabBlock(Settings.copy(LIGHT_BLUE_STUCCO))
    );
    public static final Block LIGHT_BLUE_STUCCO_WALL = register(
            "light_blue_stucco_wall",
            new WallBlock(Settings.copy(LIGHT_BLUE_STUCCO))
    );
    public static final Block BLUE_STUCCO = register(
            "blue_stucco", stucco(DyeColor.BLUE)
    );
    public static final Block BLUE_STUCCO_STAIRS = register(
            "blue_stucco_stairs",
            new StairsBlock(BLUE_STUCCO.getDefaultState(), Settings.copy(BLUE_STUCCO))
    );
    public static final Block BLUE_STUCCO_SLAB = register(
            "blue_stucco_slab",
            new SlabBlock(Settings.copy(BLUE_STUCCO))
    );
    public static final Block BLUE_STUCCO_WALL = register(
            "blue_stucco_wall",
            new WallBlock(Settings.copy(BLUE_STUCCO))
    );
    public static final Block PURPLE_STUCCO = register(
            "purple_stucco", stucco(DyeColor.PURPLE)
    );
    public static final Block PURPLE_STUCCO_STAIRS = register(
            "purple_stucco_stairs",
            new StairsBlock(PURPLE_STUCCO.getDefaultState(), Settings.copy(PURPLE_STUCCO))
    );
    public static final Block PURPLE_STUCCO_SLAB = register(
            "purple_stucco_slab",
            new SlabBlock(Settings.copy(PURPLE_STUCCO))
    );
    public static final Block PURPLE_STUCCO_WALL = register(
            "purple_stucco_wall",
            new WallBlock(Settings.copy(PURPLE_STUCCO))
    );
    public static final Block MAGENTA_STUCCO = register(
            "magenta_stucco",
            stucco(DyeColor.MAGENTA)
    );
    public static final Block MAGENTA_STUCCO_STAIRS = register(
            "magenta_stucco_stairs",
            new StairsBlock(MAGENTA_STUCCO.getDefaultState(), Settings.copy(MAGENTA_STUCCO))
    );
    public static final Block MAGENTA_STUCCO_SLAB = register(
            "magenta_stucco_slab",
            new SlabBlock(Settings.copy(MAGENTA_STUCCO))
    );
    public static final Block MAGENTA_STUCCO_WALL = register(
            "magenta_stucco_wall",
            new WallBlock(Settings.copy(MAGENTA_STUCCO))
    );
    public static final Block PINK_STUCCO = register(
            "pink_stucco",
            stucco(DyeColor.PINK)
    );
    public static final Block PINK_STUCCO_STAIRS = register(
            "pink_stucco_stairs",
            new StairsBlock(PINK_STUCCO.getDefaultState(), Settings.copy(PINK_STUCCO))
    );
    public static final Block PINK_STUCCO_SLAB = register(
            "pink_stucco_slab",
            new SlabBlock(Settings.copy(PINK_STUCCO))
    );
    public static final Block PINK_STUCCO_WALL = register(
            "pink_stucco_wall",
            new WallBlock(Settings.copy(PINK_STUCCO))
    );
    public static final Block SILKWORM_EGGS = register(
            "silkworm_eggs",
            new SilkWormEggsBlock(Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .sounds(BlockSoundGroup.FROGSPAWN)
                    .breakInstantly().nonOpaque()
                    .noCollision()
                    .pistonBehavior(PistonBehavior.DESTROY))
    );
    public static final Block SILK_COCOON = registerWithoutItem(
            "silk_cocoon",
            new SilkCocoonBlock(Settings.create()
                    .strength(0.8F)
                    .sounds(VerdanceBlockSoundGroups.SILK_COCOON))
    );
    public static final Block VIOLET = register(
            "violet", new FlowerBlock(StatusEffects.REGENERATION, 8, Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY))
    );
    public static final Block POTTED_VIOLET = registerWithoutItem(
            "potted_violet",
            Blocks.createFlowerPotBlock(VIOLET)
    );
    public static final Block SHRUB = register(
            "shrub",
            new ShrubBlock(Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.AZALEA)
                    .burnable()
                    .offset(OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.BLOCK))
    );
    public static final Block POTTED_SHRUB = registerWithoutItem(
            "potted_shrub",
            Blocks.createFlowerPotBlock(SHRUB)
    );
    public static final Block YELLOW_FLOWERING_SHRUB = register(
            "yellow_flowering_shrub",
            new FloweringShrubBlock(
                    VerdanceConfiguredFeatures.PATCH_YELLOW_FLOWERING_SHRUB_BONEMEAL,
                    Settings.copy(SHRUB))
    );
    public static final Block POTTED_YELLOW_FLOWERING_SHRUB = registerWithoutItem(
            "potted_yellow_flowering_shrub",
            Blocks.createFlowerPotBlock(YELLOW_FLOWERING_SHRUB)
    );
    public static final Block PINK_FLOWERING_SHRUB = register(
            "pink_flowering_shrub",
            new FloweringShrubBlock(
                    VerdanceConfiguredFeatures.PATCH_PINK_FLOWERING_SHRUB_BONEMEAL,
                    Settings.copy(SHRUB))
    );
    public static final Block POTTED_PINK_FLOWERING_SHRUB = registerWithoutItem(
            "potted_pink_flowering_shrub",
            Blocks.createFlowerPotBlock(PINK_FLOWERING_SHRUB)
    );

    public static void register() {
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_MULBERRY_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_MULBERRY_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_FENCE_GATE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MULBERRY_LEAVES, 30, 60);
        StrippableBlockRegistry.register(MULBERRY_LOG, STRIPPED_MULBERRY_LOG);
        StrippableBlockRegistry.register(MULBERRY_WOOD, STRIPPED_MULBERRY_WOOD);
    }

    // Utils
    public static Block register(String name, Block block) {
        var registry = Registry.register(Registries.BLOCK, Verdance.id(name), block);
        Registry.register(Registries.ITEM, Verdance.id(name), new BlockItem(registry, new Item.Settings()));
        return registry;
    }

    private static Block register(String name, Settings properties) {
        return register(name, new Block(properties));
    }

    private static <T extends Block> T registerWithoutItem(String name, T block) {
        return Registry.register(Registries.BLOCK, Verdance.id(name), block);
    }

    private static Block stucco(DyeColor color) {
        return new Block(Settings.create()
                .mapColor(color)
                .sounds(VerdanceBlockSoundGroups.STUCCO)
                .requiresTool()
                .instrument(Instrument.BASEDRUM)
                .strength(1.5F, 5.5F)
        );
    }
    private static Block cushion(DyeColor color) {
        return new CushionBlock(Settings.create()
                .mapColor(color)
                .sounds(BlockSoundGroup.WOOD)
                .strength(0.2f)
                .noCollision()
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY)
        );
    }
}
