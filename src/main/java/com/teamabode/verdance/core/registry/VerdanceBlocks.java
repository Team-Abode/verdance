package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.*;
import com.teamabode.verdance.common.feature.MulberryTreeGrower;
import com.teamabode.verdance.core.misc.datagen.VerdanceBlockSetType;
import com.teamabode.verdance.core.misc.datagen.VerdanceWoodType;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class VerdanceBlocks {
    public static final Block MULBERRY_LOG = register("mulberry_log", new RotatedPillarBlock(Properties.of().mapColor(VerdanceBlocks::getMulberryLogMapColor).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_WOOD = register("mulberry_wood", new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block STRIPPED_MULBERRY_LOG = register("stripped_mulberry_log", new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block STRIPPED_MULBERRY_WOOD = register("stripped_mulberry_wood", new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_PLANKS = register("mulberry_planks", new Block(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_STAIRS = register("mulberry_stairs", new StairBlock(MULBERRY_PLANKS.defaultBlockState(), Properties.copy(MULBERRY_PLANKS)));
    public static final Block MULBERRY_SLAB = register("mulberry_slab", new SlabBlock(Properties.copy(MULBERRY_PLANKS)));
    public static final Block MULBERRY_FENCE = register("mulberry_fence", new FenceBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_FENCE_GATE = register("mulberry_fence_gate", new FenceGateBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F), VerdanceWoodType.MULBERRY));
    public static final Block MULBERRY_DOOR = register("mulberry_door", new DoorBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(3.0F).noOcclusion(), VerdanceBlockSetType.MULBERRY));
    public static final Block MULBERRY_TRAPDOOR = register("mulberry_trapdoor", new TrapDoorBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(3.0F).noOcclusion().isValidSpawn((blockState, blockGetter, blockPos, object) -> false), VerdanceBlockSetType.MULBERRY));
    public static final Block MULBERRY_PRESSURE_PLATE = register("mulberry_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).noCollission().strength(0.5F), VerdanceBlockSetType.MULBERRY));
    public static final Block MULBERRY_BUTTON = register("mulberry_button", new ButtonBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).noCollission().strength(0.5F), VerdanceBlockSetType.MULBERRY, 30, true));

    public static final Block MULBERRY_LEAVES = register("mulberry_leaves", new LeavesBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, level, pos, entityType) -> entityType == EntityType.OCELOT || entityType == EntityType.PARROT).isSuffocating((blockState, blockGetter, blockPos) -> false).isViewBlocking((blockState, blockGetter, blockPos) -> false)));
    public static final Block FLOWERING_MULBERRY_LEAVES = register("flowering_mulberry_leaves", new LeavesBlock(Properties.copy(MULBERRY_LEAVES)));

    public static final Block MULBERRY_SAPLING = registerNoItem("mulberry_sapling", new SaplingBlock(new MulberryTreeGrower(), Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
    public static final Block POTTED_MULBERRY_SAPLING = registerNoItem("potted_mulberry_sapling", new FlowerPotBlock(MULBERRY_SAPLING, Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instabreak().noOcclusion()));

    public static final Block MULBERRY_SIGN = registerNoItem("mulberry_sign", new StandingSignBlock(Properties.copy(Blocks.OAK_SIGN).mapColor(MapColor.TERRACOTTA_YELLOW), VerdanceWoodType.MULBERRY));
    public static final Block MULBERRY_WALL_SIGN = registerNoItem("mulberry_wall_sign", new WallSignBlock(Properties.copy(Blocks.OAK_WALL_SIGN).mapColor(MapColor.TERRACOTTA_YELLOW).dropsLike(MULBERRY_SIGN), VerdanceWoodType.MULBERRY));

    public static final Block MULBERRY_HANGING_SIGN = registerNoItem("mulberry_hanging_sign", new CeilingHangingSignBlock(Properties.copy(Blocks.OAK_HANGING_SIGN).mapColor(MapColor.TERRACOTTA_YELLOW), VerdanceWoodType.MULBERRY));
    public static final Block MULBERRY_WALL_HANGING_SIGN = registerNoItem("mulberry_wall_hanging_sign", new WallHangingSignBlock(Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).mapColor(MapColor.TERRACOTTA_YELLOW).dropsLike(MULBERRY_HANGING_SIGN), VerdanceWoodType.MULBERRY));

    public static final Block CANTALOUPE = register("cantaloupe", new CantaloupeBlock(Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD)));
    public static final Block ATTACHED_CANTALOUPE_STEM = registerNoItem("attached_cantaloupe_stem", new AttachedCantaloupeStemBlock(Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));
    public static final Block CANTALOUPE_STEM = registerNoItem("cantaloupe_stem", new CantaloupeStemBlock(Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));

    public static final Block SHRUB = register("shrub", new ShrubBlock(Properties.copy(Blocks.AZALEA)));

    public static final Block WHITE_STUCCO = register("white_stucco", registerStucco(DyeColor.WHITE));
    public static final Block WHITE_STUCCO_STAIRS = register("white_stucco_stairs", new StairBlock(WHITE_STUCCO.defaultBlockState(), Properties.copy(WHITE_STUCCO)));
    public static final Block WHITE_STUCCO_SLAB = register("white_stucco_slab", new SlabBlock(Properties.copy(WHITE_STUCCO)));
    public static final Block WHITE_STUCCO_WALL = register("white_stucco_wall", new WallBlock(Properties.copy(WHITE_STUCCO)));

    public static final Block LIGHT_GRAY_STUCCO = register("light_gray_stucco", registerStucco(DyeColor.LIGHT_GRAY));
    public static final Block LIGHT_GRAY_STUCCO_STAIRS = register("light_gray_stucco_stairs", new StairBlock(LIGHT_GRAY_STUCCO.defaultBlockState(), Properties.copy(LIGHT_GRAY_STUCCO)));
    public static final Block LIGHT_GRAY_STUCCO_SLAB = register("light_gray_stucco_slab", new SlabBlock(Properties.copy(LIGHT_GRAY_STUCCO)));
    public static final Block LIGHT_GRAY_STUCCO_WALL = register("light_gray_stucco_wall", new WallBlock(Properties.copy(LIGHT_GRAY_STUCCO)));

    public static final Block GRAY_STUCCO = register("gray_stucco", registerStucco(DyeColor.GRAY));
    public static final Block GRAY_STUCCO_STAIRS = register("gray_stucco_stairs", new StairBlock(GRAY_STUCCO.defaultBlockState(), Properties.copy(GRAY_STUCCO)));
    public static final Block GRAY_STUCCO_SLAB = register("gray_stucco_slab", new SlabBlock(Properties.copy(GRAY_STUCCO)));
    public static final Block GRAY_STUCCO_WALL = register("gray_stucco_wall", new WallBlock(Properties.copy(GRAY_STUCCO)));

    public static final Block BLACK_STUCCO = register("black_stucco", registerStucco(DyeColor.BLACK));
    public static final Block BLACK_STUCCO_STAIRS = register("black_stucco_stairs", new StairBlock(BLACK_STUCCO.defaultBlockState(), Properties.copy(BLACK_STUCCO)));
    public static final Block BLACK_STUCCO_SLAB = register("black_stucco_slab", new SlabBlock(Properties.copy(BLACK_STUCCO)));
    public static final Block BLACK_STUCCO_WALL = register("black_stucco_wall", new WallBlock(Properties.copy(BLACK_STUCCO)));

    public static final Block BROWN_STUCCO = register("brown_stucco", registerStucco(DyeColor.BROWN));
    public static final Block BROWN_STUCCO_STAIRS = register("brown_stucco_stairs", new StairBlock(BROWN_STUCCO.defaultBlockState(), Properties.copy(BROWN_STUCCO)));
    public static final Block BROWN_STUCCO_SLAB = register("brown_stucco_slab", new SlabBlock(Properties.copy(BROWN_STUCCO)));
    public static final Block BROWN_STUCCO_WALL = register("brown_stucco_wall", new WallBlock(Properties.copy(BROWN_STUCCO)));

    public static final Block RED_STUCCO = register("red_stucco", registerStucco(DyeColor.RED));
    public static final Block RED_STUCCO_STAIRS = register("red_stucco_stairs", new StairBlock(RED_STUCCO.defaultBlockState(), Properties.copy(RED_STUCCO)));
    public static final Block RED_STUCCO_SLAB = register("red_stucco_slab", new SlabBlock(Properties.copy(RED_STUCCO)));
    public static final Block RED_STUCCO_WALL = register("red_stucco_wall", new WallBlock(Properties.copy(RED_STUCCO)));

    public static final Block ORANGE_STUCCO = register("orange_stucco", registerStucco(DyeColor.ORANGE));
    public static final Block ORANGE_STUCCO_STAIRS = register("orange_stucco_stairs", new StairBlock(ORANGE_STUCCO.defaultBlockState(), Properties.copy(ORANGE_STUCCO)));
    public static final Block ORANGE_STUCCO_SLAB = register("orange_stucco_slab", new SlabBlock(Properties.copy(ORANGE_STUCCO)));
    public static final Block ORANGE_STUCCO_WALL = register("orange_stucco_wall", new WallBlock(Properties.copy(ORANGE_STUCCO)));

    public static final Block SILKWORM_EGGS = register("silkworm_eggs", new SilkWormEggsBlock(Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.FROGSPAWN).instabreak().noOcclusion().noCollission().pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops()));

    private static Block registerStucco(DyeColor dyeColor) {
        return new Block(Properties.of().mapColor(dyeColor).requiresCorrectToolForDrops().strength(1.5F, 5.5F));
    }

    private static <T extends Block> T register(String name, T block) {
        var registry = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name), block);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Verdance.MOD_ID, name), new BlockItem(registry, new Item.Properties()));

        return registry;
    }

    private static <T extends Block> T registerNoItem(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name), block);
    }

    private static MapColor getMulberryLogMapColor(BlockState state) {
        return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.TERRACOTTA_YELLOW : MapColor.TERRACOTTA_GRAY;
    }

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
}
