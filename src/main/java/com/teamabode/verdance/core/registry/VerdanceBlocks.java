package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.*;
import com.teamabode.verdance.core.misc.VerdanceBlockSetTypes;
import com.teamabode.verdance.core.misc.VerdanceBlockSoundGroups;
import com.teamabode.verdance.core.misc.VerdanceSaplingGenerators;
import com.teamabode.verdance.core.misc.VerdanceWoodTypes;
import com.teamabode.verdance.core.misc.VerdanceBlockRegistryKeys;
import com.teamabode.verdance.core.misc.VerdanceItemRegistryKeys;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.OffsetType;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;

import java.util.function.Function;

public class VerdanceBlocks {
    public static final Block MULBERRY_LOG = register(
            "mulberry_log",
            PillarBlock::new,
            Blocks.createLogSettings(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.WOOD)
    );
    public static final Block MULBERRY_WOOD = register(
            "mulberry_wood",
            PillarBlock::new,
            Blocks.createLogSettings(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.WOOD)
    );
    public static final Block STRIPPED_MULBERRY_LOG = register(
            "stripped_mulberry_log",
            PillarBlock::new,
            Blocks.createLogSettings(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, BlockSoundGroup.WOOD)
    );
    public static final Block STRIPPED_MULBERRY_WOOD = register(
            "stripped_mulberry_wood",
            PillarBlock::new,
            Blocks.createLogSettings(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, BlockSoundGroup.WOOD)
    );
    public static final Block MULBERRY_PLANKS = register(
            "mulberry_planks",
            Block::new,
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(2.0F, 3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
    );
    public static final Block MULBERRY_STAIRS = register(
            "mulberry_stairs",
            settings -> new StairsBlock(MULBERRY_PLANKS.getDefaultState(), settings),
            Settings.copy(MULBERRY_PLANKS)
    );
    public static final Block MULBERRY_SLAB = register(
            "mulberry_slab",
            SlabBlock::new,
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(2.0F, 3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
    );
    public static final Block MULBERRY_FENCE = register(
            "mulberry_fence",
            FenceBlock::new,
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(2.0F, 3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
    );
    public static final Block MULBERRY_FENCE_GATE = register(
            "mulberry_fence_gate",
            settings -> new FenceGateBlock(VerdanceWoodTypes.MULBERRY, settings),
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .solid()
                .strength(2.0F, 3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .burnable()
    );
    public static final Block MULBERRY_DOOR = register(
            "mulberry_door",
            settings -> new DoorBlock(VerdanceBlockSetTypes.MULBERRY, settings),
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY)
                .burnable()
    );
    public static final Block MULBERRY_TRAPDOOR = register(
            "mulberry_trapdoor",
            settings -> new TrapdoorBlock(VerdanceBlockSetTypes.MULBERRY, settings),
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .allowsSpawning(Blocks::never)
                .nonOpaque()
                .burnable()
    );
    public static final Block MULBERRY_PRESSURE_PLATE = register(
            "mulberry_pressure_plate",
            settings -> new PressurePlateBlock(VerdanceBlockSetTypes.MULBERRY, settings),
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .solid()
                .strength(0.5f)
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .pistonBehavior(PistonBehavior.DESTROY)
                .burnable()
    );
    public static final Block MULBERRY_BUTTON = register(
            "mulberry_button",
            settings -> new ButtonBlock(VerdanceBlockSetTypes.MULBERRY, 30, settings),
            Blocks.createButtonSettings()
    );
    public static final Block MULBERRY_SIGN = registerWithoutItem(
            "mulberry_sign",
            settings -> new SignBlock(VerdanceWoodTypes.MULBERRY, settings),
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(1.0F)
                .burnable()
    );
    public static final Block MULBERRY_WALL_SIGN = registerWithoutItem(
            "mulberry_wall_sign",
            settings -> new WallSignBlock(VerdanceWoodTypes.MULBERRY, settings),
            Settings.create()
                .lootTable(MULBERRY_SIGN.getLootTableKey())
                .overrideTranslationKey(MULBERRY_SIGN.getTranslationKey())
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(1.0F)
                .burnable()
    );
    public static final Block MULBERRY_HANGING_SIGN = registerWithoutItem(
            "mulberry_hanging_sign",
            settings -> new HangingSignBlock(VerdanceWoodTypes.MULBERRY, settings),
            Settings.create()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(1.0F)
                .burnable()
    );
    public static final Block MULBERRY_WALL_HANGING_SIGN = registerWithoutItem(
            "mulberry_wall_hanging_sign",
            settings -> new WallHangingSignBlock(VerdanceWoodTypes.MULBERRY, settings),
            Settings.create()
                .lootTable(MULBERRY_SIGN.getLootTableKey())
                .overrideTranslationKey(MULBERRY_SIGN.getTranslationKey())
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .solid()
                .instrument(NoteBlockInstrument.BASS)
                .noCollision()
                .strength(1.0F)
                .burnable()
    );
    public static final Block MULBERRY_LEAVES = register(
            "mulberry_leaves",
            settings -> new TintedParticleLeavesBlock(0.01f, settings),
            Blocks.createLeavesSettings(BlockSoundGroup.GRASS)
    );
    public static final Block FLOWERING_MULBERRY_LEAVES = register(
            "flowering_mulberry_leaves",
            settings -> new TintedParticleLeavesBlock(0.01f, settings),
            Blocks.createLeavesSettings(BlockSoundGroup.GRASS)
    );
    public static final Block MULBERRY_SAPLING = register(
            "mulberry_sapling",
            settings -> new SaplingBlock(VerdanceSaplingGenerators.MULBERRY, settings),
            Settings.create()
                .mapColor(MapColor.DARK_GREEN)
                .noCollision()
                .ticksRandomly()
                .breakInstantly()
                .sounds(BlockSoundGroup.CROP)
                .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_MULBERRY_SAPLING = registerWithoutItem(
            "potted_mulberry_sapling",
            settings -> new FlowerPotBlock(MULBERRY_SAPLING, settings),
            Blocks.createFlowerPotSettings()
    );
    public static final Block CANTALOUPE = register(
            "cantaloupe",
            Block::new,
            Settings.create()
                .mapColor(MapColor.LIME)
                .strength(1.0F)
                .sounds(BlockSoundGroup.WOOD)
    );
    public static final Block ATTACHED_CANTALOUPE_STEM = registerWithoutItem(
            "attached_cantaloupe_stem",
            settings -> new AttachedStemBlock(
                    VerdanceBlockRegistryKeys.CANTALOUPE_STEM,
                    VerdanceBlockRegistryKeys.CANTALOUPE,
                    VerdanceItemRegistryKeys.CANTALOUPE_SEEDS,
            settings),
            Settings.create()
                .noCollision()
                .ticksRandomly()
                .breakInstantly()
                .sounds(BlockSoundGroup.STEM)
                .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block CANTALOUPE_STEM = registerWithoutItem(
            "cantaloupe_stem",
            settings -> new StemBlock(
                VerdanceBlockRegistryKeys.CANTALOUPE,
                VerdanceBlockRegistryKeys.ATTACHED_CANTALOUPE_STEM,
                VerdanceItemRegistryKeys.CANTALOUPE_SEEDS,
            settings),
            Settings.create()
                .noCollision()
                .ticksRandomly()
                .breakInstantly()
                .sounds(BlockSoundGroup.STEM)
                .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block WHITE_CUSHION = registerCushionBlock(
            "white_cushion",
            DyeColor.WHITE
    );
    public static final Block LIGHT_GRAY_CUSHION = registerCushionBlock(
            "light_gray_cushion",
            DyeColor.LIGHT_GRAY
    );
    public static final Block GRAY_CUSHION = registerCushionBlock(
            "gray_cushion",
            DyeColor.GRAY
    );
    public static final Block BLACK_CUSHION = registerCushionBlock(
            "black_cushion",
            DyeColor.BLACK
    );
    public static final Block BROWN_CUSHION = registerCushionBlock(
            "brown_cushion",
            DyeColor.BROWN
    );
    public static final Block RED_CUSHION = registerCushionBlock(
            "red_cushion",
            DyeColor.RED
    );
    public static final Block ORANGE_CUSHION = registerCushionBlock(
            "orange_cushion",
            DyeColor.ORANGE
    );
    public static final Block YELLOW_CUSHION = registerCushionBlock(
            "yellow_cushion",
            DyeColor.YELLOW
    );
    public static final Block LIME_CUSHION = registerCushionBlock(
            "lime_cushion",
            DyeColor.LIME
    );
    public static final Block GREEN_CUSHION = registerCushionBlock(
            "green_cushion",
            DyeColor.GREEN
    );
    public static final Block CYAN_CUSHION = registerCushionBlock(
            "cyan_cushion",
            DyeColor.CYAN
    );
    public static final Block LIGHT_BLUE_CUSHION = registerCushionBlock(
            "light_blue_cushion",
            DyeColor.LIGHT_BLUE
    );
    public static final Block BLUE_CUSHION = registerCushionBlock(
            "blue_cushion",
            DyeColor.BLUE
    );
    public static final Block PURPLE_CUSHION = registerCushionBlock(
            "purple_cushion",
            DyeColor.PURPLE
    );
    public static final Block MAGENTA_CUSHION = registerCushionBlock(
            "magenta_cushion",
            DyeColor.MAGENTA
    );
    public static final Block PINK_CUSHION = registerCushionBlock(
            "pink_cushion",
            DyeColor.PINK
    );
    public static final Block WHITE_STUCCO = registerStuccoBlock(
            "white_stucco",
            DyeColor.WHITE
    );
    public static final Block WHITE_STUCCO_STAIRS = register(
            "white_stucco_stairs",
            settings -> new StairsBlock(WHITE_STUCCO.getDefaultState(), settings),
            Settings.copy(WHITE_STUCCO)
    );
    public static final Block WHITE_STUCCO_SLAB = register(
            "white_stucco_slab",
            SlabBlock::new,
            Settings.copy(WHITE_STUCCO)
    );
    public static final Block WHITE_STUCCO_WALL = register(
            "white_stucco_wall",
            WallBlock::new,
            Settings.copy(WHITE_STUCCO)
    );
    public static final Block LIGHT_GRAY_STUCCO = registerCushionBlock(
            "light_gray_stucco",
            DyeColor.LIGHT_GRAY
    );
    public static final Block LIGHT_GRAY_STUCCO_STAIRS = register(
            "light_gray_stucco_stairs",
            settings -> new StairsBlock(LIGHT_GRAY_STUCCO.getDefaultState(), settings),
            Settings.copy(LIGHT_GRAY_STUCCO)
    );
    public static final Block LIGHT_GRAY_STUCCO_SLAB = register(
            "light_gray_stucco_slab",
            SlabBlock::new,
            Settings.copy(LIGHT_GRAY_STUCCO)
    );
    public static final Block LIGHT_GRAY_STUCCO_WALL = register(
            "light_gray_stucco_wall",
            WallBlock::new,
            Settings.copy(LIGHT_GRAY_STUCCO)
    );
    public static final Block GRAY_STUCCO = registerStuccoBlock(
            "gray_stucco",
            DyeColor.GRAY
    );
    public static final Block GRAY_STUCCO_STAIRS = register(
            "gray_stucco_stairs",
            settings -> new StairsBlock(GRAY_STUCCO.getDefaultState(), settings),
            Settings.copy(GRAY_STUCCO)
    );
    public static final Block GRAY_STUCCO_SLAB = register(
            "gray_stucco_slab",
            SlabBlock::new,
            Settings.copy(GRAY_STUCCO)
    );
    public static final Block GRAY_STUCCO_WALL = register(
            "gray_stucco_wall",
            WallBlock::new,
            Settings.copy(GRAY_STUCCO)
    );
    public static final Block BLACK_STUCCO = registerStuccoBlock(
            "black_stucco",
            DyeColor.BLACK
    );
    public static final Block BLACK_STUCCO_STAIRS = register(
            "black_stucco_stairs",
            settings -> new StairsBlock(BLACK_STUCCO.getDefaultState(), settings),
            Settings.copy(BLACK_STUCCO)
    );
    public static final Block BLACK_STUCCO_SLAB = register(
            "black_stucco_slab",
            SlabBlock::new,
            Settings.copy(BLACK_STUCCO)
    );
    public static final Block BLACK_STUCCO_WALL = register(
            "black_stucco_wall",
            WallBlock::new,
            Settings.copy(BLACK_STUCCO)
    );
    public static final Block BROWN_STUCCO = registerStuccoBlock(
            "brown_stucco",
            DyeColor.BROWN
    );
    public static final Block BROWN_STUCCO_STAIRS = register(
            "brown_stucco_stairs",
            settings -> new StairsBlock(BROWN_STUCCO.getDefaultState(), settings),
            Settings.copy(BROWN_STUCCO)
    );
    public static final Block BROWN_STUCCO_SLAB = register(
            "brown_stucco_slab",
            SlabBlock::new,
            Settings.copy(BROWN_STUCCO)
    );
    public static final Block BROWN_STUCCO_WALL = register(
            "brown_stucco_wall",
            WallBlock::new,
            Settings.copy(BROWN_STUCCO)
    );
    public static final Block RED_STUCCO = registerStuccoBlock(
            "red_stucco",
            DyeColor.RED
    );
    public static final Block RED_STUCCO_STAIRS = register(
            "red_stucco_stairs",
            settings -> new StairsBlock(RED_STUCCO.getDefaultState(), settings),
            Settings.copy(RED_STUCCO)
    );
    public static final Block RED_STUCCO_SLAB = register(
            "red_stucco_slab",
            SlabBlock::new,
            Settings.copy(RED_STUCCO)
    );
    public static final Block RED_STUCCO_WALL = register(
            "red_stucco_wall",
            WallBlock::new,
            Settings.copy(RED_STUCCO)
    );
    public static final Block ORANGE_STUCCO = registerStuccoBlock(
            "orange_stucco",
            DyeColor.ORANGE
    );
    public static final Block ORANGE_STUCCO_STAIRS = register(
            "orange_stucco_stairs",
            settings -> new StairsBlock(ORANGE_STUCCO.getDefaultState(), settings),
            Settings.copy(ORANGE_STUCCO)
    );
    public static final Block ORANGE_STUCCO_SLAB = register(
            "orange_stucco_slab",
            SlabBlock::new,
            Settings.copy(ORANGE_STUCCO)
    );
    public static final Block ORANGE_STUCCO_WALL = register(
            "orange_stucco_wall",
            WallBlock::new,
            Settings.copy(ORANGE_STUCCO)
    );
    public static final Block YELLOW_STUCCO = registerStuccoBlock(
            "yellow_stucco",
            DyeColor.YELLOW
    );
    public static final Block YELLOW_STUCCO_STAIRS = register(
            "yellow_stucco_stairs",
            settings -> new StairsBlock(YELLOW_STUCCO.getDefaultState(), settings),
            Settings.copy(YELLOW_STUCCO)
    );
    public static final Block YELLOW_STUCCO_SLAB = register(
            "yellow_stucco_slab",
            SlabBlock::new,
            Settings.copy(YELLOW_STUCCO)
    );
    public static final Block YELLOW_STUCCO_WALL = register(
            "yellow_stucco_wall",
            WallBlock::new,
            Settings.copy(YELLOW_STUCCO)
    );
    public static final Block LIME_STUCCO = registerStuccoBlock(
            "lime_stucco",
            DyeColor.LIME
    );
    public static final Block LIME_STUCCO_STAIRS = register(
            "lime_stucco_stairs",
            settings -> new StairsBlock(LIME_STUCCO.getDefaultState(), settings),
            Settings.copy(LIME_STUCCO)
    );
    public static final Block LIME_STUCCO_SLAB = register(
            "lime_stucco_slab",
            SlabBlock::new,
            Settings.copy(LIME_STUCCO)
    );
    public static final Block LIME_STUCCO_WALL = register(
            "lime_stucco_wall",
            WallBlock::new,
            Settings.copy(LIME_STUCCO)
    );
    public static final Block GREEN_STUCCO = registerStuccoBlock(
            "green_stucco",
            DyeColor.GREEN
    );
    public static final Block GREEN_STUCCO_STAIRS = register(
            "green_stucco_stairs",
            settings -> new StairsBlock(GREEN_STUCCO.getDefaultState(), settings),
            Settings.copy(GREEN_STUCCO)
    );
    public static final Block GREEN_STUCCO_SLAB = register(
            "green_stucco_slab",
            SlabBlock::new,
            Settings.copy(GREEN_STUCCO)
    );
    public static final Block GREEN_STUCCO_WALL = register(
            "green_stucco_wall",
            WallBlock::new,
            Settings.copy(GREEN_STUCCO)
    );
    public static final Block CYAN_STUCCO = registerStuccoBlock(
            "cyan_stucco",
            DyeColor.CYAN
    );
    public static final Block CYAN_STUCCO_STAIRS = register(
            "cyan_stucco_stairs",
            settings -> new StairsBlock(CYAN_STUCCO.getDefaultState(), settings),
            Settings.copy(CYAN_STUCCO)
    );
    public static final Block CYAN_STUCCO_SLAB = register(
            "cyan_stucco_slab",
            SlabBlock::new,
            Settings.copy(CYAN_STUCCO)
    );
    public static final Block CYAN_STUCCO_WALL = register(
            "cyan_stucco_wall",
            WallBlock::new,
            Settings.copy(CYAN_STUCCO)
    );
    public static final Block LIGHT_BLUE_STUCCO = registerStuccoBlock(
            "light_blue_stucco",
            DyeColor.LIGHT_BLUE
    );
    public static final Block LIGHT_BLUE_STUCCO_STAIRS = register(
            "light_blue_stucco_stairs",
            settings -> new StairsBlock(LIGHT_BLUE_STUCCO.getDefaultState(), settings),
            Settings.copy(LIGHT_BLUE_STUCCO)
    );
    public static final Block LIGHT_BLUE_STUCCO_SLAB = register(
            "light_blue_stucco_slab",
            SlabBlock::new,
            Settings.copy(LIGHT_BLUE_STUCCO)
    );
    public static final Block LIGHT_BLUE_STUCCO_WALL = register(
            "light_blue_stucco_wall",
            WallBlock::new,
            Settings.copy(LIGHT_BLUE_STUCCO)
    );
    public static final Block BLUE_STUCCO = registerStuccoBlock(
            "blue_stucco",
            DyeColor.BLUE
    );
    public static final Block BLUE_STUCCO_STAIRS = register(
            "blue_stucco_stairs",
            settings -> new StairsBlock(BLUE_STUCCO.getDefaultState(), settings),
            Settings.copy(BLUE_STUCCO)
    );
    public static final Block BLUE_STUCCO_SLAB = register(
            "blue_stucco_slab",
            SlabBlock::new,
            Settings.copy(BLUE_STUCCO)
    );
    public static final Block BLUE_STUCCO_WALL = register(
            "blue_stucco_wall",
            WallBlock::new,
            Settings.copy(BLUE_STUCCO)
    );
    public static final Block PURPLE_STUCCO = registerStuccoBlock(
            "purple_stucco",
            DyeColor.PURPLE
    );
    public static final Block PURPLE_STUCCO_STAIRS = register(
            "purple_stucco_stairs",
            settings -> new StairsBlock(PURPLE_STUCCO.getDefaultState(), settings),
            Settings.copy(PURPLE_STUCCO)
    );
    public static final Block PURPLE_STUCCO_SLAB = register(
            "purple_stucco_slab",
            SlabBlock::new,
            Settings.copy(PURPLE_STUCCO)
    );
    public static final Block PURPLE_STUCCO_WALL = register(
            "purple_stucco_wall",
            WallBlock::new,
            Settings.copy(PURPLE_STUCCO)
    );
    public static final Block MAGENTA_STUCCO = registerStuccoBlock(
            "magenta_stucco",
            DyeColor.MAGENTA
    );
    public static final Block MAGENTA_STUCCO_STAIRS = register(
            "magenta_stucco_stairs",
            settings -> new StairsBlock(MAGENTA_STUCCO.getDefaultState(), settings),
            Settings.copy(MAGENTA_STUCCO)
    );
    public static final Block MAGENTA_STUCCO_SLAB = register(
            "magenta_stucco_slab",
            SlabBlock::new,
            Settings.copy(MAGENTA_STUCCO)
    );
    public static final Block MAGENTA_STUCCO_WALL = register(
            "magenta_stucco_wall",
            WallBlock::new,
            Settings.copy(MAGENTA_STUCCO)
    );
    public static final Block PINK_STUCCO = registerStuccoBlock(
            "pink_stucco",
            DyeColor.PINK
    );
    public static final Block PINK_STUCCO_STAIRS = register(
            "pink_stucco_stairs",
            settings -> new StairsBlock(PINK_STUCCO.getDefaultState(), settings),
            Settings.copy(PINK_STUCCO)
    );
    public static final Block PINK_STUCCO_SLAB = register(
            "pink_stucco_slab",
            SlabBlock::new,
            Settings.copy(PINK_STUCCO)
    );
    public static final Block PINK_STUCCO_WALL = register(
            "pink_stucco_wall",
            WallBlock::new,
            Settings.copy(PINK_STUCCO)
    );
    public static final Block SILKWORM_EGGS = register(
            "silkworm_eggs",
            SilkWormEggsBlock::new,
            Settings.create()
                .mapColor(MapColor.YELLOW)
                .sounds(BlockSoundGroup.FROGSPAWN)
                .breakInstantly().nonOpaque()
                .noCollision()
                .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block SILK_COCOON = registerWithoutItem(
            "silk_cocoon",
            SilkCocoonBlock::new,
            Settings.create()
                .strength(0.8F)
                .sounds(VerdanceBlockSoundGroups.SILK_COCOON)
    );
    public static final Block VIOLET = register(
            "violet",
            settings -> new FlowerBlock(StatusEffects.REGENERATION, 8.0f, settings),
            Settings.create()
                .mapColor(MapColor.DARK_GREEN)
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.GRASS)
                .offset(OffsetType.XZ)
                .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block POTTED_VIOLET = registerWithoutItem(
            "potted_violet",
            settings -> new FlowerPotBlock(VIOLET, settings),
            Blocks.createFlowerPotSettings()
    );
    public static final Block DESERT_BUSH = register(
            "desert_bush",
            DesertBushBlock::new,
            Settings.create()
                .mapColor(MapColor.DARK_GREEN)
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.AZALEA)
                .burnable()
                .offset(OffsetType.XZ)
                .pistonBehavior(PistonBehavior.BLOCK)
    );
    public static final Block POTTED_DESERT_BUSH = registerWithoutItem(
            "potted_desert_bush",
            settings -> new FlowerPotBlock(DESERT_BUSH, settings),
            Blocks.createFlowerPotSettings()
    );
    public static final Block YELLOW_FLOWERING_DESERT_BUSH = register(
            "yellow_flowering_desert_bush",
            settings -> new FloweringDesertBushBlock(VerdanceConfiguredFeatures.PATCH_YELLOW_FLOWERING_SHRUB_BONEMEAL, settings),
            Settings.copy(DESERT_BUSH)
    );
    public static final Block POTTED_YELLOW_FLOWERING_DESERT_BUSH = registerWithoutItem(
            "potted_yellow_flowering_desert_bush",
            settings -> new FlowerPotBlock(YELLOW_FLOWERING_DESERT_BUSH, settings),
            Blocks.createFlowerPotSettings()
    );
    public static final Block PINK_FLOWERING_DESERT_BUSH = register(
            "pink_flowering_desert_bush",
            settings -> new FloweringDesertBushBlock(VerdanceConfiguredFeatures.PATCH_PINK_FLOWERING_SHRUB_BONEMEAL, settings),
            Settings.copy(DESERT_BUSH)
    );
    public static final Block POTTED_PINK_FLOWERING_DESERT_BUSH = registerWithoutItem(
            "potted_pink_flowering_desert_bush",
            settings -> new FlowerPotBlock(PINK_FLOWERING_DESERT_BUSH, settings),
            Blocks.createFlowerPotSettings()
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
    private static Block register(String name, Function<Settings, Block> block, Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Verdance.id(name));
        var registry = Registry.register(Registries.BLOCK, key, block.apply(settings.registryKey(key)));

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Verdance.id(name));
        Registry.register(Registries.ITEM, itemKey, new BlockItem(registry, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey()));

        return registry;
    }

    private static Block registerWithoutItem(String name, Function<Settings, Block> block, Settings settings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Verdance.id(name));

        return Registry.register(Registries.BLOCK, key, block.apply(settings.registryKey(key)));
    }

    private static Block registerStuccoBlock(String name, DyeColor color) {
        return register(name, Block::new, Settings.create()
                .mapColor(color)
                .sounds(VerdanceBlockSoundGroups.STUCCO)
                .requiresTool()
                .instrument(NoteBlockInstrument.BASEDRUM)
                .strength(1.5f, 5.5f)
        );
    }

    private static Block registerCushionBlock(String name, DyeColor color) {
        return register(name, CushionBlock::new, Settings.create()
                .mapColor(color)
                .sounds(BlockSoundGroup.WOOD)
                .strength(0.2f)
                .noCollision()
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY)
        );
    }
}
