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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class VerdanceBlocks {
    public static final Block MULBERRY_LOG = register(
            "mulberry_log",
            Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY)
    );
    public static final Block MULBERRY_WOOD = register(
            "mulberry_wood",
            Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY)
    );
    public static final Block STRIPPED_MULBERRY_LOG = register(
            "stripped_mulberry_log",
            Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW)
    );
    public static final Block STRIPPED_MULBERRY_WOOD = register(
            "stripped_mulberry_wood",
            Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW)
    );
    public static final Block MULBERRY_PLANKS = register(
            "mulberry_planks",
            Properties.of()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(2.0F, 3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .sound(SoundType.WOOD)
                .ignitedByLava()
    );
    public static final Block MULBERRY_STAIRS = register(
            "mulberry_stairs",
            new StairBlock(MULBERRY_PLANKS.defaultBlockState(), Properties.ofFullCopy(MULBERRY_PLANKS))
    );
    public static final Block MULBERRY_SLAB = register(
            "mulberry_slab",
            new SlabBlock(Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(2.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_FENCE = register(
            "mulberry_fence",
            new FenceBlock(Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(2.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_FENCE_GATE = register(
            "mulberry_fence_gate",
            new FenceGateBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .strength(2.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_DOOR = register(
            "mulberry_door",
            new DoorBlock(VerdanceBlockSetTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_TRAPDOOR = register(
            "mulberry_trapdoor",
            new TrapDoorBlock(VerdanceBlockSetTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .isValidSpawn(Blocks::never)
                    .noOcclusion()
                    .ignitedByLava())
    );
    public static final Block MULBERRY_PRESSURE_PLATE = register(
            "mulberry_pressure_plate",
            new PressurePlateBlock(VerdanceBlockSetTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .strength(0.5f)
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .pushReaction(PushReaction.DESTROY)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_BUTTON = register(
            "mulberry_button",
            Blocks.woodenButton(VerdanceBlockSetTypes.MULBERRY)
    );
    public static final Block MULBERRY_SIGN = registerWithoutItem(
            "mulberry_sign",
            new StandingSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_WALL_SIGN = registerWithoutItem(
            "mulberry_wall_sign",
            new WallSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .dropsLike(MULBERRY_SIGN)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_HANGING_SIGN = registerWithoutItem(
            "mulberry_hanging_sign",
            new CeilingHangingSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_WALL_HANGING_SIGN = registerWithoutItem(
            "mulberry_wall_hanging_sign",
            new WallHangingSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .dropsLike(MULBERRY_HANGING_SIGN)
                    .ignitedByLava())
    );
    public static final Block MULBERRY_LEAVES = register(
            "mulberry_leaves", Blocks.leaves(SoundType.GRASS)
    );
    public static final Block FLOWERING_MULBERRY_LEAVES = register(
            "flowering_mulberry_leaves", Blocks.leaves(SoundType.GRASS)
    );
    public static final Block MULBERRY_SAPLING = registerWithoutItem(
            "mulberry_sapling",
            new SaplingBlock(VerdanceSaplingGenerators.MULBERRY, Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY))
    );
    public static final Block POTTED_MULBERRY_SAPLING = registerWithoutItem(
            "potted_mulberry_sapling",
            Blocks.flowerPot(MULBERRY_SAPLING)
    );
    public static final Block CANTALOUPE = register(
            "cantaloupe",
            new Block(Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(1.0F)
                    .sound(SoundType.WOOD))
    );
    public static final Block ATTACHED_CANTALOUPE_STEM = registerWithoutItem(
            "attached_cantaloupe_stem",
            new AttachedStemBlock(
                    VerdanceBlockRegistryKeys.CANTALOUPE_STEM,
                    VerdanceBlockRegistryKeys.CANTALOUPE,
                    VerdanceItemRegistryKeys.CANTALOUPE_SEEDS,
                    Properties.of()
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.HARD_CROP)
                            .pushReaction(PushReaction.DESTROY))
    );
    public static final Block CANTALOUPE_STEM = registerWithoutItem(
            "cantaloupe_stem",
            new StemBlock(
                VerdanceBlockRegistryKeys.CANTALOUPE,
                VerdanceBlockRegistryKeys.ATTACHED_CANTALOUPE_STEM,
                VerdanceItemRegistryKeys.CANTALOUPE_SEEDS,
                Properties.of()
                        .noCollission()
                        .randomTicks()
                        .instabreak()
                        .sound(SoundType.HARD_CROP)
                        .pushReaction(PushReaction.DESTROY))
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
            new StairBlock(WHITE_STUCCO.defaultBlockState(), Properties.ofFullCopy(WHITE_STUCCO))
    );
    public static final Block WHITE_STUCCO_SLAB = register(
            "white_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(WHITE_STUCCO))
    );
    public static final Block WHITE_STUCCO_WALL = register(
            "white_stucco_wall",
            new WallBlock(Properties.ofFullCopy(WHITE_STUCCO))
    );
    public static final Block LIGHT_GRAY_STUCCO = register(
            "light_gray_stucco",
            stucco(DyeColor.LIGHT_GRAY)
    );
    public static final Block LIGHT_GRAY_STUCCO_STAIRS = register(
            "light_gray_stucco_stairs",
            new StairBlock(LIGHT_GRAY_STUCCO.defaultBlockState(), Properties.ofFullCopy(LIGHT_GRAY_STUCCO))
    );
    public static final Block LIGHT_GRAY_STUCCO_SLAB = register(
            "light_gray_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(LIGHT_GRAY_STUCCO))
    );
    public static final Block LIGHT_GRAY_STUCCO_WALL = register(
            "light_gray_stucco_wall",
            new WallBlock(Properties.ofFullCopy(LIGHT_GRAY_STUCCO))
    );
    public static final Block GRAY_STUCCO = register(
            "gray_stucco",
            stucco(DyeColor.GRAY)
    );
    public static final Block GRAY_STUCCO_STAIRS = register(
            "gray_stucco_stairs",
            new StairBlock(GRAY_STUCCO.defaultBlockState(), Properties.ofFullCopy(GRAY_STUCCO))
    );
    public static final Block GRAY_STUCCO_SLAB = register(
            "gray_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(GRAY_STUCCO))
    );
    public static final Block GRAY_STUCCO_WALL = register(
            "gray_stucco_wall",
            new WallBlock(Properties.ofFullCopy(GRAY_STUCCO))
    );
    public static final Block BLACK_STUCCO = register(
            "black_stucco",
            stucco(DyeColor.BLACK)
    );
    public static final Block BLACK_STUCCO_STAIRS = register(
            "black_stucco_stairs",
            new StairBlock(BLACK_STUCCO.defaultBlockState(), Properties.ofFullCopy(BLACK_STUCCO))
    );
    public static final Block BLACK_STUCCO_SLAB = register(
            "black_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(BLACK_STUCCO))
    );
    public static final Block BLACK_STUCCO_WALL = register(
            "black_stucco_wall",
            new WallBlock(Properties.ofFullCopy(BLACK_STUCCO))
    );
    public static final Block BROWN_STUCCO = register(
            "brown_stucco",
            stucco(DyeColor.BROWN)
    );
    public static final Block BROWN_STUCCO_STAIRS = register(
            "brown_stucco_stairs",
            new StairBlock(BROWN_STUCCO.defaultBlockState(), Properties.ofFullCopy(BROWN_STUCCO))
    );
    public static final Block BROWN_STUCCO_SLAB = register(
            "brown_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(BROWN_STUCCO))
    );
    public static final Block BROWN_STUCCO_WALL = register(
            "brown_stucco_wall",
            new WallBlock(Properties.ofFullCopy(BROWN_STUCCO))
    );
    public static final Block RED_STUCCO = register(
            "red_stucco",
            stucco(DyeColor.RED)
    );
    public static final Block RED_STUCCO_STAIRS = register(
            "red_stucco_stairs",
            new StairBlock(RED_STUCCO.defaultBlockState(), Properties.ofFullCopy(RED_STUCCO))
    );
    public static final Block RED_STUCCO_SLAB = register(
            "red_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(RED_STUCCO))
    );
    public static final Block RED_STUCCO_WALL = register(
            "red_stucco_wall",
            new WallBlock(Properties.ofFullCopy(RED_STUCCO))
    );
    public static final Block ORANGE_STUCCO = register(
            "orange_stucco",
            stucco(DyeColor.ORANGE)
    );
    public static final Block ORANGE_STUCCO_STAIRS = register(
            "orange_stucco_stairs",
            new StairBlock(ORANGE_STUCCO.defaultBlockState(), Properties.ofFullCopy(ORANGE_STUCCO))
    );
    public static final Block ORANGE_STUCCO_SLAB = register(
            "orange_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(ORANGE_STUCCO))
    );
    public static final Block ORANGE_STUCCO_WALL = register(
            "orange_stucco_wall",
            new WallBlock(Properties.ofFullCopy(ORANGE_STUCCO))
    );
    public static final Block YELLOW_STUCCO = register(
            "yellow_stucco",
            stucco(DyeColor.YELLOW)
    );
    public static final Block YELLOW_STUCCO_STAIRS = register(
            "yellow_stucco_stairs",
            new StairBlock(YELLOW_STUCCO.defaultBlockState(), Properties.ofFullCopy(YELLOW_STUCCO))
    );
    public static final Block YELLOW_STUCCO_SLAB = register(
            "yellow_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(YELLOW_STUCCO))
    );
    public static final Block YELLOW_STUCCO_WALL = register(
            "yellow_stucco_wall",
            new WallBlock(Properties.ofFullCopy(YELLOW_STUCCO))
    );
    public static final Block LIME_STUCCO = register(
            "lime_stucco",
            stucco(DyeColor.LIME)
    );
    public static final Block LIME_STUCCO_STAIRS = register(
            "lime_stucco_stairs",
            new StairBlock(LIME_STUCCO.defaultBlockState(), Properties.ofFullCopy(LIME_STUCCO))
    );
    public static final Block LIME_STUCCO_SLAB = register(
            "lime_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(LIME_STUCCO))
    );
    public static final Block LIME_STUCCO_WALL = register(
            "lime_stucco_wall",
            new WallBlock(Properties.ofFullCopy(LIME_STUCCO))
    );
    public static final Block GREEN_STUCCO = register(
            "green_stucco",
            stucco(DyeColor.GREEN)
    );
    public static final Block GREEN_STUCCO_STAIRS = register(
            "green_stucco_stairs",
            new StairBlock(GREEN_STUCCO.defaultBlockState(), Properties.ofFullCopy(GREEN_STUCCO))
    );
    public static final Block GREEN_STUCCO_SLAB = register(
            "green_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(GREEN_STUCCO))
    );
    public static final Block GREEN_STUCCO_WALL = register(
            "green_stucco_wall",
            new WallBlock(Properties.ofFullCopy(GREEN_STUCCO))
    );
    public static final Block CYAN_STUCCO = register(
            "cyan_stucco",
            stucco(DyeColor.CYAN)
    );
    public static final Block CYAN_STUCCO_STAIRS = register(
            "cyan_stucco_stairs",
            new StairBlock(CYAN_STUCCO.defaultBlockState(), Properties.ofFullCopy(CYAN_STUCCO))
    );
    public static final Block CYAN_STUCCO_SLAB = register(
            "cyan_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(CYAN_STUCCO))
    );
    public static final Block CYAN_STUCCO_WALL = register(
            "cyan_stucco_wall",
            new WallBlock(Properties.ofFullCopy(CYAN_STUCCO))
    );
    public static final Block LIGHT_BLUE_STUCCO = register(
            "light_blue_stucco",
            stucco(DyeColor.LIGHT_BLUE)
    );
    public static final Block LIGHT_BLUE_STUCCO_STAIRS = register(
            "light_blue_stucco_stairs",
            new StairBlock(LIGHT_BLUE_STUCCO.defaultBlockState(), Properties.ofFullCopy(LIGHT_BLUE_STUCCO))
    );
    public static final Block LIGHT_BLUE_STUCCO_SLAB = register(
            "light_blue_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(LIGHT_BLUE_STUCCO))
    );
    public static final Block LIGHT_BLUE_STUCCO_WALL = register(
            "light_blue_stucco_wall",
            new WallBlock(Properties.ofFullCopy(LIGHT_BLUE_STUCCO))
    );
    public static final Block BLUE_STUCCO = register(
            "blue_stucco", stucco(DyeColor.BLUE)
    );
    public static final Block BLUE_STUCCO_STAIRS = register(
            "blue_stucco_stairs",
            new StairBlock(BLUE_STUCCO.defaultBlockState(), Properties.ofFullCopy(BLUE_STUCCO))
    );
    public static final Block BLUE_STUCCO_SLAB = register(
            "blue_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(BLUE_STUCCO))
    );
    public static final Block BLUE_STUCCO_WALL = register(
            "blue_stucco_wall",
            new WallBlock(Properties.ofFullCopy(BLUE_STUCCO))
    );
    public static final Block PURPLE_STUCCO = register(
            "purple_stucco", stucco(DyeColor.PURPLE)
    );
    public static final Block PURPLE_STUCCO_STAIRS = register(
            "purple_stucco_stairs",
            new StairBlock(PURPLE_STUCCO.defaultBlockState(), Properties.ofFullCopy(PURPLE_STUCCO))
    );
    public static final Block PURPLE_STUCCO_SLAB = register(
            "purple_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(PURPLE_STUCCO))
    );
    public static final Block PURPLE_STUCCO_WALL = register(
            "purple_stucco_wall",
            new WallBlock(Properties.ofFullCopy(PURPLE_STUCCO))
    );
    public static final Block MAGENTA_STUCCO = register(
            "magenta_stucco",
            stucco(DyeColor.MAGENTA)
    );
    public static final Block MAGENTA_STUCCO_STAIRS = register(
            "magenta_stucco_stairs",
            new StairBlock(MAGENTA_STUCCO.defaultBlockState(), Properties.ofFullCopy(MAGENTA_STUCCO))
    );
    public static final Block MAGENTA_STUCCO_SLAB = register(
            "magenta_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(MAGENTA_STUCCO))
    );
    public static final Block MAGENTA_STUCCO_WALL = register(
            "magenta_stucco_wall",
            new WallBlock(Properties.ofFullCopy(MAGENTA_STUCCO))
    );
    public static final Block PINK_STUCCO = register(
            "pink_stucco",
            stucco(DyeColor.PINK)
    );
    public static final Block PINK_STUCCO_STAIRS = register(
            "pink_stucco_stairs",
            new StairBlock(PINK_STUCCO.defaultBlockState(), Properties.ofFullCopy(PINK_STUCCO))
    );
    public static final Block PINK_STUCCO_SLAB = register(
            "pink_stucco_slab",
            new SlabBlock(Properties.ofFullCopy(PINK_STUCCO))
    );
    public static final Block PINK_STUCCO_WALL = register(
            "pink_stucco_wall",
            new WallBlock(Properties.ofFullCopy(PINK_STUCCO))
    );
    public static final Block SILKWORM_EGGS = register(
            "silkworm_eggs",
            new SilkWormEggsBlock(Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.FROGSPAWN)
                    .instabreak().noOcclusion()
                    .noCollission()
                    .pushReaction(PushReaction.DESTROY))
    );
    public static final Block SILK_COCOON = registerWithoutItem(
            "silk_cocoon",
            new SilkCocoonBlock(Properties.of()
                    .strength(0.8F)
                    .sound(VerdanceBlockSoundGroups.SILK_COCOON))
    );
    public static final Block VIOLET = register(
            "violet", new FlowerBlock(MobEffects.REGENERATION, 8.0f, Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY))
    );
    public static final Block POTTED_VIOLET = registerWithoutItem(
            "potted_violet",
            Blocks.flowerPot(VIOLET)
    );
    public static final Block SHRUB = register(
            "shrub",
            new ShrubBlock(Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.AZALEA)
                    .ignitedByLava()
                    .offsetType(OffsetType.XZ)
                    .pushReaction(PushReaction.BLOCK))
    );
    public static final Block POTTED_SHRUB = registerWithoutItem(
            "potted_shrub",
            Blocks.flowerPot(SHRUB)
    );
    public static final Block YELLOW_FLOWERING_SHRUB = register(
            "yellow_flowering_shrub",
            new FloweringShrubBlock(
                    VerdanceConfiguredFeatures.PATCH_YELLOW_FLOWERING_SHRUB_BONEMEAL,
                    Properties.ofFullCopy(SHRUB))
    );
    public static final Block POTTED_YELLOW_FLOWERING_SHRUB = registerWithoutItem(
            "potted_yellow_flowering_shrub",
            Blocks.flowerPot(YELLOW_FLOWERING_SHRUB)
    );
    public static final Block PINK_FLOWERING_SHRUB = register(
            "pink_flowering_shrub",
            new FloweringShrubBlock(
                    VerdanceConfiguredFeatures.PATCH_PINK_FLOWERING_SHRUB_BONEMEAL,
                    Properties.ofFullCopy(SHRUB))
    );
    public static final Block POTTED_PINK_FLOWERING_SHRUB = registerWithoutItem(
            "potted_pink_flowering_shrub",
            Blocks.flowerPot(PINK_FLOWERING_SHRUB)
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
        var registry = Registry.register(BuiltInRegistries.BLOCK, Verdance.id(name), block);
        Registry.register(BuiltInRegistries.ITEM, Verdance.id(name), new BlockItem(registry, new Item.Properties()));
        return registry;
    }

    private static Block register(String name, Properties properties) {
        return register(name, new Block(properties));
    }

    private static <T extends Block> T registerWithoutItem(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, Verdance.id(name), block);
    }

    private static Block stucco(DyeColor color) {
        return new Block(Properties.of()
                .mapColor(color)
                .sound(VerdanceBlockSoundGroups.STUCCO)
                .requiresCorrectToolForDrops()
                .instrument(NoteBlockInstrument.BASEDRUM)
                .strength(1.5F, 5.5F)
        );
    }
    private static Block cushion(DyeColor color) {
        return new CushionBlock(Properties.of()
                .mapColor(color)
                .sound(SoundType.WOOD)
                .strength(0.2f)
                .noCollission()
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY)
        );
    }
}
