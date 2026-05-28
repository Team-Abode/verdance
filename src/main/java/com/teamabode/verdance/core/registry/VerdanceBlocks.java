package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.*;
import com.teamabode.verdance.core.misc.VerdanceBlockSetTypes;
import com.teamabode.verdance.core.misc.VerdanceBlockSoundGroups;
import com.teamabode.verdance.core.misc.VerdanceSaplingGenerators;
import com.teamabode.verdance.core.misc.VerdanceWoodTypes;
import com.teamabode.verdance.core.misc.VerdanceBlockRegistryKeys;
import com.teamabode.verdance.core.misc.VerdanceItemRegistryKeys;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
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
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VerdanceBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.createBlocks(Verdance.MOD_ID);

    public static final Supplier<Block> MULBERRY_LOG = registerWithItem(
            "mulberry_log",
            () -> Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY)
    );
    public static final Supplier<Block> MULBERRY_WOOD = registerWithItem(
            "mulberry_wood",
            () -> Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_GRAY)
    );
    public static final Supplier<Block> STRIPPED_MULBERRY_LOG = registerWithItem(
            "stripped_mulberry_log",
            () -> Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW)
    );
    public static final Supplier<Block> STRIPPED_MULBERRY_WOOD = registerWithItem(
            "stripped_mulberry_wood",
            () -> Blocks.log(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW)
    );
    public static final Supplier<Block> MULBERRY_PLANKS = registerWithItem(
            "mulberry_planks",
            Properties.of()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(2.0F, 3.0F)
                .instrument(NoteBlockInstrument.BASS)
                .sound(SoundType.WOOD)
                .ignitedByLava()
    );
    public static final Supplier<Block> MULBERRY_STAIRS = registerWithItem(
            "mulberry_stairs",
            () -> new StairBlock(MULBERRY_PLANKS.get().defaultBlockState(), Properties.ofFullCopy(MULBERRY_PLANKS.get()))
    );
    public static final Supplier<Block> MULBERRY_SLAB = registerWithItem(
            "mulberry_slab",
            () -> new SlabBlock(Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(2.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_FENCE = registerWithItem(
            "mulberry_fence",
            () -> new FenceBlock(Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(2.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_FENCE_GATE = registerWithItem(
            "mulberry_fence_gate",
            () -> new FenceGateBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .strength(2.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_DOOR = registerWithItem(
            "mulberry_door",
            () -> new DoorBlock(VerdanceBlockSetTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_TRAPDOOR = registerWithItem(
            "mulberry_trapdoor",
            () -> new TrapDoorBlock(VerdanceBlockSetTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .strength(3.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .isValidSpawn(Blocks::never)
                    .noOcclusion()
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_PRESSURE_PLATE = registerWithItem(
            "mulberry_pressure_plate",
            () -> new PressurePlateBlock(VerdanceBlockSetTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .strength(0.5f)
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .pushReaction(PushReaction.DESTROY)
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_BUTTON = registerWithItem(
            "mulberry_button",
            () -> Blocks.woodenButton(VerdanceBlockSetTypes.MULBERRY)
    );
    public static final Supplier<Block> MULBERRY_SIGN = register(
            "mulberry_sign",
            () -> new StandingSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_WALL_SIGN = register(
            "mulberry_wall_sign",
            () -> new WallSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .dropsLike(MULBERRY_SIGN.get())
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_HANGING_SIGN = register(
            "mulberry_hanging_sign",
            () -> new CeilingHangingSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_WALL_HANGING_SIGN = register(
            "mulberry_wall_hanging_sign",
            () -> new WallHangingSignBlock(VerdanceWoodTypes.MULBERRY, Properties.of()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollission()
                    .strength(1.0F)
                    .dropsLike(MULBERRY_HANGING_SIGN.get())
                    .ignitedByLava())
    );
    public static final Supplier<Block> MULBERRY_LEAVES = registerWithItem(
            "mulberry_leaves",
            () -> Blocks.leaves(SoundType.GRASS)
    );
    public static final Supplier<Block> FLOWERING_MULBERRY_LEAVES = registerWithItem(
            "flowering_mulberry_leaves",
            () -> Blocks.leaves(SoundType.GRASS)
    );
    public static final Supplier<Block> MULBERRY_SAPLING = register(
            "mulberry_sapling",
            () -> new SaplingBlock(VerdanceSaplingGenerators.MULBERRY, Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY))
    );
    public static final Supplier<Block> POTTED_MULBERRY_SAPLING = register(
            "potted_mulberry_sapling",
            () -> Blocks.flowerPot(MULBERRY_SAPLING.get())
    );
    public static final Supplier<Block> CANTALOUPE = registerWithItem(
            "cantaloupe",
            () -> new Block(Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_GREEN)
                    .strength(1.0F)
                    .sound(SoundType.WOOD))
    );
    public static final Supplier<Block> ATTACHED_CANTALOUPE_STEM = register(
            "attached_cantaloupe_stem",
            () -> new AttachedStemBlock(
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
    public static final Supplier<Block> CANTALOUPE_STEM = register(
            "cantaloupe_stem",
            () -> new StemBlock(
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
    public static final Supplier<Block> WHITE_CUSHION = registerWithItem(
            "white_cushion",
            () -> cushion(DyeColor.WHITE)
    );
    public static final Supplier<Block> LIGHT_GRAY_CUSHION = registerWithItem(
            "light_gray_cushion",
            () -> cushion(DyeColor.LIGHT_GRAY)
    );
    public static final Supplier<Block> GRAY_CUSHION = registerWithItem(
            "gray_cushion",
            () -> cushion(DyeColor.GRAY)
    );
    public static final Supplier<Block> BLACK_CUSHION = registerWithItem(
            "black_cushion",
            () -> cushion(DyeColor.BLACK)
    );
    public static final Supplier<Block> BROWN_CUSHION = registerWithItem(
            "brown_cushion",
            () -> cushion(DyeColor.BROWN)
    );
    public static final Supplier<Block> RED_CUSHION = registerWithItem(
            "red_cushion",
            () -> cushion(DyeColor.RED)
    );
    public static final Supplier<Block> ORANGE_CUSHION = registerWithItem(
            "orange_cushion",
            () -> cushion(DyeColor.ORANGE)
    );
    public static final Supplier<Block> YELLOW_CUSHION = registerWithItem(
            "yellow_cushion",
            () -> cushion(DyeColor.YELLOW)
    );
    public static final Supplier<Block> LIME_CUSHION = registerWithItem(
            "lime_cushion",
            () -> cushion(DyeColor.LIME)
    );
    public static final Supplier<Block> GREEN_CUSHION = registerWithItem(
            "green_cushion",
            () -> cushion(DyeColor.GREEN)
    );
    public static final Supplier<Block> CYAN_CUSHION = registerWithItem(
            "cyan_cushion",
            () -> cushion(DyeColor.CYAN)
    );
    public static final Supplier<Block> LIGHT_BLUE_CUSHION = registerWithItem(
            "light_blue_cushion",
            () -> cushion(DyeColor.LIGHT_BLUE)
    );
    public static final Supplier<Block> BLUE_CUSHION = registerWithItem(
            "blue_cushion",
            () -> cushion(DyeColor.BLUE)
    );
    public static final Supplier<Block> PURPLE_CUSHION = registerWithItem(
            "purple_cushion",
            () -> cushion(DyeColor.PURPLE)
    );
    public static final Supplier<Block> MAGENTA_CUSHION = registerWithItem(
            "magenta_cushion",
            () -> cushion(DyeColor.MAGENTA)
    );
    public static final Supplier<Block> PINK_CUSHION = registerWithItem(
            "pink_cushion",
            () -> cushion(DyeColor.PINK)
    );
    public static final Supplier<Block> WHITE_STUCCO = registerWithItem(
            "white_stucco",
            () -> stucco(DyeColor.WHITE)
    );
    public static final Supplier<Block> WHITE_STUCCO_STAIRS = registerWithItem(
            "white_stucco_stairs",
            () -> new StairBlock(WHITE_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(WHITE_STUCCO.get()))
    );
    public static final Supplier<Block> WHITE_STUCCO_SLAB = registerWithItem(
            "white_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(WHITE_STUCCO.get()))
    );
    public static final Supplier<Block> WHITE_STUCCO_WALL = registerWithItem(
            "white_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(WHITE_STUCCO.get()))
    );
    public static final Supplier<Block> LIGHT_GRAY_STUCCO = registerWithItem(
            "light_gray_stucco",
            () -> stucco(DyeColor.LIGHT_GRAY)
    );
    public static final Supplier<Block> LIGHT_GRAY_STUCCO_STAIRS = registerWithItem(
            "light_gray_stucco_stairs",
            () -> new StairBlock(LIGHT_GRAY_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(LIGHT_GRAY_STUCCO.get()))
    );
    public static final Supplier<Block> LIGHT_GRAY_STUCCO_SLAB = registerWithItem(
            "light_gray_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(LIGHT_GRAY_STUCCO.get()))
    );
    public static final Supplier<Block> LIGHT_GRAY_STUCCO_WALL = registerWithItem(
            "light_gray_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(LIGHT_GRAY_STUCCO.get()))
    );
    public static final Supplier<Block> GRAY_STUCCO = registerWithItem(
            "gray_stucco",
            () -> stucco(DyeColor.GRAY)
    );
    public static final Supplier<Block> GRAY_STUCCO_STAIRS = registerWithItem(
            "gray_stucco_stairs",
            () -> new StairBlock(GRAY_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(GRAY_STUCCO.get()))
    );
    public static final Supplier<Block> GRAY_STUCCO_SLAB = registerWithItem(
            "gray_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(GRAY_STUCCO.get()))
    );
    public static final Supplier<Block> GRAY_STUCCO_WALL = registerWithItem(
            "gray_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(GRAY_STUCCO.get()))
    );
    public static final Supplier<Block> BLACK_STUCCO = registerWithItem(
            "black_stucco",
            () -> stucco(DyeColor.BLACK)
    );
    public static final Supplier<Block> BLACK_STUCCO_STAIRS = registerWithItem(
            "black_stucco_stairs",
            () -> new StairBlock(BLACK_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(BLACK_STUCCO.get()))
    );
    public static final Supplier<Block> BLACK_STUCCO_SLAB = registerWithItem(
            "black_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(BLACK_STUCCO.get()))
    );
    public static final Supplier<Block> BLACK_STUCCO_WALL = registerWithItem(
            "black_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(BLACK_STUCCO.get()))
    );
    public static final Supplier<Block> BROWN_STUCCO = registerWithItem(
            "brown_stucco",
            () -> stucco(DyeColor.BROWN)
    );
    public static final Supplier<Block> BROWN_STUCCO_STAIRS = registerWithItem(
            "brown_stucco_stairs",
            () -> new StairBlock(BROWN_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(BROWN_STUCCO.get()))
    );
    public static final Supplier<Block> BROWN_STUCCO_SLAB = registerWithItem(
            "brown_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(BROWN_STUCCO.get()))
    );
    public static final Supplier<Block> BROWN_STUCCO_WALL = registerWithItem(
            "brown_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(BROWN_STUCCO.get()))
    );
    public static final Supplier<Block> RED_STUCCO = registerWithItem(
            "red_stucco",
            () -> stucco(DyeColor.RED)
    );
    public static final Supplier<Block> RED_STUCCO_STAIRS = registerWithItem(
            "red_stucco_stairs",
            () -> new StairBlock(RED_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(RED_STUCCO.get()))
    );
    public static final Supplier<Block> RED_STUCCO_SLAB = registerWithItem(
            "red_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(RED_STUCCO.get()))
    );
    public static final Supplier<Block> RED_STUCCO_WALL = registerWithItem(
            "red_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(RED_STUCCO.get()))
    );
    public static final Supplier<Block> ORANGE_STUCCO = registerWithItem(
            "orange_stucco",
            () -> stucco(DyeColor.ORANGE)
    );
    public static final Supplier<Block> ORANGE_STUCCO_STAIRS = registerWithItem(
            "orange_stucco_stairs",
            () -> new StairBlock(ORANGE_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(ORANGE_STUCCO.get()))
    );
    public static final Supplier<Block> ORANGE_STUCCO_SLAB = registerWithItem(
            "orange_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(ORANGE_STUCCO.get()))
    );
    public static final Supplier<Block> ORANGE_STUCCO_WALL = registerWithItem(
            "orange_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(ORANGE_STUCCO.get()))
    );
    public static final Supplier<Block> YELLOW_STUCCO = registerWithItem(
            "yellow_stucco",
            () -> stucco(DyeColor.YELLOW)
    );
    public static final Supplier<Block> YELLOW_STUCCO_STAIRS = registerWithItem(
            "yellow_stucco_stairs",
            () -> new StairBlock(YELLOW_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(YELLOW_STUCCO.get()))
    );
    public static final Supplier<Block> YELLOW_STUCCO_SLAB = registerWithItem(
            "yellow_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(YELLOW_STUCCO.get()))
    );
    public static final Supplier<Block> YELLOW_STUCCO_WALL = registerWithItem(
            "yellow_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(YELLOW_STUCCO.get()))
    );
    public static final Supplier<Block> LIME_STUCCO = registerWithItem(
            "lime_stucco",
            () -> stucco(DyeColor.LIME)
    );
    public static final Supplier<Block> LIME_STUCCO_STAIRS = registerWithItem(
            "lime_stucco_stairs",
            () -> new StairBlock(LIME_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(LIME_STUCCO.get()))
    );
    public static final Supplier<Block> LIME_STUCCO_SLAB = registerWithItem(
            "lime_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(LIME_STUCCO.get()))
    );
    public static final Supplier<Block> LIME_STUCCO_WALL = registerWithItem(
            "lime_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(LIME_STUCCO.get()))
    );
    public static final Supplier<Block> GREEN_STUCCO = registerWithItem(
            "green_stucco",
            () -> stucco(DyeColor.GREEN)
    );
    public static final Supplier<Block> GREEN_STUCCO_STAIRS = registerWithItem(
            "green_stucco_stairs",
            () -> new StairBlock(GREEN_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(GREEN_STUCCO.get()))
    );
    public static final Supplier<Block> GREEN_STUCCO_SLAB = registerWithItem(
            "green_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(GREEN_STUCCO.get()))
    );
    public static final Supplier<Block> GREEN_STUCCO_WALL = registerWithItem(
            "green_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(GREEN_STUCCO.get()))
    );
    public static final Supplier<Block> CYAN_STUCCO = registerWithItem(
            "cyan_stucco",
            () -> stucco(DyeColor.CYAN)
    );
    public static final Supplier<Block> CYAN_STUCCO_STAIRS = registerWithItem(
            "cyan_stucco_stairs",
            () -> new StairBlock(CYAN_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(CYAN_STUCCO.get()))
    );
    public static final Supplier<Block> CYAN_STUCCO_SLAB = registerWithItem(
            "cyan_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(CYAN_STUCCO.get()))
    );
    public static final Supplier<Block> CYAN_STUCCO_WALL = registerWithItem(
            "cyan_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(CYAN_STUCCO.get()))
    );
    public static final Supplier<Block> LIGHT_BLUE_STUCCO = registerWithItem(
            "light_blue_stucco",
            () -> stucco(DyeColor.LIGHT_BLUE)
    );
    public static final Supplier<Block> LIGHT_BLUE_STUCCO_STAIRS = registerWithItem(
            "light_blue_stucco_stairs",
            () -> new StairBlock(LIGHT_BLUE_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(LIGHT_BLUE_STUCCO.get()))
    );
    public static final Supplier<Block> LIGHT_BLUE_STUCCO_SLAB = registerWithItem(
            "light_blue_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(LIGHT_BLUE_STUCCO.get()))
    );
    public static final Supplier<Block> LIGHT_BLUE_STUCCO_WALL = registerWithItem(
            "light_blue_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(LIGHT_BLUE_STUCCO.get()))
    );
    public static final Supplier<Block> BLUE_STUCCO = registerWithItem(
            "blue_stucco", () -> stucco(DyeColor.BLUE)
    );
    public static final Supplier<Block> BLUE_STUCCO_STAIRS = registerWithItem(
            "blue_stucco_stairs",
            () -> new StairBlock(BLUE_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(BLUE_STUCCO.get()))
    );
    public static final Supplier<Block> BLUE_STUCCO_SLAB = registerWithItem(
            "blue_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(BLUE_STUCCO.get()))
    );
    public static final Supplier<Block> BLUE_STUCCO_WALL = registerWithItem(
            "blue_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(BLUE_STUCCO.get()))
    );
    public static final Supplier<Block> PURPLE_STUCCO = registerWithItem(
            "purple_stucco", () -> stucco(DyeColor.PURPLE)
    );
    public static final Supplier<Block> PURPLE_STUCCO_STAIRS = registerWithItem(
            "purple_stucco_stairs",
            () -> new StairBlock(PURPLE_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(PURPLE_STUCCO.get()))
    );
    public static final Supplier<Block> PURPLE_STUCCO_SLAB = registerWithItem(
            "purple_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(PURPLE_STUCCO.get()))
    );
    public static final Supplier<Block> PURPLE_STUCCO_WALL = registerWithItem(
            "purple_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(PURPLE_STUCCO.get()))
    );
    public static final Supplier<Block> MAGENTA_STUCCO = registerWithItem(
            "magenta_stucco",
            () -> stucco(DyeColor.MAGENTA)
    );
    public static final Supplier<Block> MAGENTA_STUCCO_STAIRS = registerWithItem(
            "magenta_stucco_stairs",
            () -> new StairBlock(MAGENTA_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(MAGENTA_STUCCO.get()))
    );
    public static final Supplier<Block> MAGENTA_STUCCO_SLAB = registerWithItem(
            "magenta_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(MAGENTA_STUCCO.get()))
    );
    public static final Supplier<Block> MAGENTA_STUCCO_WALL = registerWithItem(
            "magenta_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(MAGENTA_STUCCO.get()))
    );
    public static final Supplier<Block> PINK_STUCCO = registerWithItem(
            "pink_stucco",
            () -> stucco(DyeColor.PINK)
    );
    public static final Supplier<Block> PINK_STUCCO_STAIRS = registerWithItem(
            "pink_stucco_stairs",
            () -> new StairBlock(PINK_STUCCO.get().defaultBlockState(), Properties.ofFullCopy(PINK_STUCCO.get()))
    );
    public static final Supplier<Block> PINK_STUCCO_SLAB = registerWithItem(
            "pink_stucco_slab",
            () -> new SlabBlock(Properties.ofFullCopy(PINK_STUCCO.get()))
    );
    public static final Supplier<Block> PINK_STUCCO_WALL = registerWithItem(
            "pink_stucco_wall",
            () -> new WallBlock(Properties.ofFullCopy(PINK_STUCCO.get()))
    );
    public static final Supplier<Block> SILKWORM_EGGS = registerWithItem(
            "silkworm_eggs",
            () -> new SilkWormEggsBlock(Properties.of()
                    .mapColor(MapColor.COLOR_YELLOW)
                    .sound(SoundType.FROGSPAWN)
                    .instabreak().noOcclusion()
                    .noCollission()
                    .pushReaction(PushReaction.DESTROY))
    );
    public static final Supplier<Block> SILK_COCOON = register(
            "silk_cocoon",
            () -> new SilkCocoonBlock(Properties.of()
                    .strength(0.8F)
                    .sound(VerdanceBlockSoundGroups.SILK_COCOON))
    );
    public static final Supplier<Block> VIOLET = registerWithItem(
            "violet", () -> new FlowerBlock(MobEffects.REGENERATION, 8.0f, Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY))
    );
    public static final Supplier<Block> POTTED_VIOLET = register(
            "potted_violet",
            () -> Blocks.flowerPot(VIOLET.get())
    );
    public static final Supplier<Block> SHRUB = registerWithItem(
            "shrub",
            () -> new ShrubBlock(Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.AZALEA)
                    .ignitedByLava()
                    .offsetType(OffsetType.XYZ)
                    .pushReaction(PushReaction.BLOCK))
    );
    public static final Supplier<Block> POTTED_SHRUB = register(
            "potted_shrub",
            () -> Blocks.flowerPot(SHRUB.get())
    );
    public static final Supplier<Block> YELLOW_FLOWERING_SHRUB = registerWithItem(
            "yellow_flowering_shrub",
            () -> new FloweringShrubBlock(
                    VerdanceConfiguredFeatures.PATCH_YELLOW_FLOWERING_SHRUB_BONEMEAL,
                    Properties.ofFullCopy(SHRUB.get()))
    );
    public static final Supplier<Block> POTTED_YELLOW_FLOWERING_SHRUB = register(
            "potted_yellow_flowering_shrub",
            () -> Blocks.flowerPot(YELLOW_FLOWERING_SHRUB.get())
    );
    public static final Supplier<Block> PINK_FLOWERING_SHRUB = registerWithItem(
            "pink_flowering_shrub",
            () -> new FloweringShrubBlock(
                    VerdanceConfiguredFeatures.PATCH_PINK_FLOWERING_SHRUB_BONEMEAL,
                    Properties.ofFullCopy(SHRUB.get()))
    );
    public static final Supplier<Block> POTTED_PINK_FLOWERING_SHRUB = register(
            "potted_pink_flowering_shrub",
            () -> Blocks.flowerPot(PINK_FLOWERING_SHRUB.get())
    );

    // Utils
    public static Supplier<Block> registerWithItem(String name, Supplier<Block> block) {
        var registry = REGISTRY.register(name, block);
        VerdanceItems.REGISTRY.register(name, () -> new BlockItem(registry.get(), new Item.Properties()));
        return registry;
    }

    private static Supplier<Block> registerWithItem(String name, Properties properties) {
        return registerWithItem(name, () -> new Block(properties));
    }

    private static <T extends Block> Supplier<T> register(String name, Supplier<T> block) {
        return REGISTRY.register(name, block);
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
