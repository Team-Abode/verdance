package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.AttachedCantaloupeStemBlock;
import com.teamabode.verdance.common.block.CantaloupeBlock;
import com.teamabode.verdance.common.block.CantaloupeStemBlock;
import com.teamabode.verdance.common.block.ShrubBlock;
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
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class VerdanceBlocks {
    public static final Block MULBERRY_LOG = register("mulberry_log", new RotatedPillarBlock(Properties.of(Material.WOOD, blockState -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.TERRACOTTA_YELLOW : MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_WOOD = register("mulberry_wood", new RotatedPillarBlock(Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block STRIPPED_MULBERRY_LOG = register("stripped_mulberry_log", new RotatedPillarBlock(Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block STRIPPED_MULBERRY_WOOD = register("stripped_mulberry_wood", new RotatedPillarBlock(Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_PLANKS = register("mulberry_planks", new Block(Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_STAIRS = register("mulberry_stairs", new StairBlock(MULBERRY_PLANKS.defaultBlockState(), Properties.copy(MULBERRY_PLANKS)));
    public static final Block MULBERRY_SLAB = register("mulberry_slab", new SlabBlock(Properties.copy(MULBERRY_PLANKS)));
    public static final Block MULBERRY_FENCE = register("mulberry_fence", new FenceBlock(Properties.of(Material.WOOD, MULBERRY_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final Block MULBERRY_FENCE_GATE = register("mulberry_fence_gate", new FenceGateBlock(Properties.of(Material.WOOD, MULBERRY_PLANKS.defaultMaterialColor()).strength(2.0F, 3.0F), VerdanceWoodType.MULBERRY));
    public static final Block MULBERRY_DOOR = register("mulberry_door", new DoorBlock(Properties.of(Material.WOOD, MULBERRY_PLANKS.defaultMaterialColor()).strength(3.0F).noOcclusion(), VerdanceBlockSetType.MULBERRY));
    public static final Block MULBERRY_TRAPDOOR = register("mulberry_trapdoor", new TrapDoorBlock(Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_YELLOW).strength(3.0F).noOcclusion().isValidSpawn((blockState, blockGetter, blockPos, object) -> false), VerdanceBlockSetType.MULBERRY));
    public static final Block MULBERRY_PRESSURE_PLATE = register("mulberry_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Properties.of(Material.WOOD, MULBERRY_PLANKS.defaultMaterialColor()).noCollission().strength(0.5F), VerdanceBlockSetType.MULBERRY));
    public static final Block MULBERRY_BUTTON = register("mulberry_button", new ButtonBlock(Properties.of(Material.DECORATION).noCollission().strength(0.5F), VerdanceBlockSetType.MULBERRY, 30, true));

    public static final Block MULBERRY_LEAVES = register("mulberry_leaves", new LeavesBlock(Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn((state, level, pos, entityType) -> entityType == EntityType.OCELOT || entityType == EntityType.PARROT).isSuffocating((blockState, blockGetter, blockPos) -> false).isViewBlocking((blockState, blockGetter, blockPos) -> false)));

    public static final Block MULBERRY_SAPLING = registerNoItem("mulberry_sapling", new SaplingBlock(new MulberryTreeGrower(), Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP)));

    public static final Block MULBERRY_SIGN = registerNoItem("mulberry_sign", new StandingSignBlock(Properties.copy(Blocks.OAK_SIGN).color(MaterialColor.TERRACOTTA_YELLOW), VerdanceWoodType.MULBERRY));
    public static final Block MULBERRY_WALL_SIGN = registerNoItem("mulberry_wall_sign", new WallSignBlock(Properties.copy(Blocks.OAK_WALL_SIGN).color(MaterialColor.TERRACOTTA_YELLOW).dropsLike(MULBERRY_SIGN), VerdanceWoodType.MULBERRY));

    public static final Block MULBERRY_HANGING_SIGN = registerNoItem("mulberry_hanging_sign", new CeilingHangingSignBlock(Properties.copy(Blocks.OAK_HANGING_SIGN).color(MaterialColor.TERRACOTTA_YELLOW).requiredFeatures(FeatureFlags.UPDATE_1_20), VerdanceWoodType.MULBERRY));
    public static final Block MULBERRY_WALL_HANGING_SIGN = registerNoItem("mulberry_wall_hanging_sign", new WallHangingSignBlock(Properties.copy(Blocks.OAK_WALL_HANGING_SIGN).color(MaterialColor.TERRACOTTA_YELLOW).requiredFeatures(FeatureFlags.UPDATE_1_20).dropsLike(MULBERRY_HANGING_SIGN), VerdanceWoodType.MULBERRY));

    public static final Block CANTALOUPE = register("cantaloupe", new CantaloupeBlock(Properties.of(Material.VEGETABLE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD)));
    public static final Block ATTACHED_CANTALOUPE_STEM = registerNoItem("attached_cantaloupe_stem", new AttachedCantaloupeStemBlock(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));
    public static final Block CANTALOUPE_STEM = registerNoItem("cantaloupe_stem", new CantaloupeStemBlock(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));

    public static final Block SHRUB = register("shrub", new ShrubBlock(Properties.copy(Blocks.AZALEA)));

    public static final Block WHITE_STUCCO = register("white_stucco", new Block(Properties.of(Material.STONE, MaterialColor.WOOL).requiresCorrectToolForDrops().strength(1.5F, 5.5F)));
    public static final Block WHITE_STUCCO_STAIRS = register("white_stucco_stairs", new StairBlock(WHITE_STUCCO.defaultBlockState(), Properties.copy(WHITE_STUCCO)));
    public static final Block WHITE_STUCCO_SLAB = register("white_stucco_slab", new SlabBlock(Properties.copy(WHITE_STUCCO)));
    public static final Block WHITE_STUCCO_WALL = register("white_stucco_wall", new WallBlock(Properties.copy(WHITE_STUCCO)));

    public static final Block LIGHT_GRAY_STUCCO = register("light_gray_stucco", new Block(Properties.of(Material.STONE, MaterialColor.COLOR_LIGHT_GRAY).requiresCorrectToolForDrops().strength(1.5F, 5.5F)));
    public static final Block LIGHT_GRAY_STUCCO_STAIRS = register("light_gray_stucco_stairs", new StairBlock(LIGHT_GRAY_STUCCO.defaultBlockState(), Properties.copy(LIGHT_GRAY_STUCCO)));
    public static final Block LIGHT_GRAY_STUCCO_SLAB = register("light_gray_stucco_slab", new SlabBlock(Properties.copy(LIGHT_GRAY_STUCCO)));
    public static final Block LIGHT_GRAY_STUCCO_WALL = register("light_gray_stucco_wall", new WallBlock(Properties.copy(LIGHT_GRAY_STUCCO)));

    public static final Block GRAY_STUCCO = register("gray_stucco", new Block(Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(1.5F, 5.5F)));
    public static final Block GRAY_STUCCO_STAIRS = register("gray_stucco_stairs", new StairBlock(GRAY_STUCCO.defaultBlockState(), Properties.copy(GRAY_STUCCO)));
    public static final Block GRAY_STUCCO_SLAB = register("gray_stucco_slab", new SlabBlock(Properties.copy(GRAY_STUCCO)));
    public static final Block GRAY_STUCCO_WALL = register("gray_stucco_wall", new WallBlock(Properties.copy(GRAY_STUCCO)));

    public static final Block BLACK_STUCCO = register("black_stucco", new Block(Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).requiresCorrectToolForDrops().strength(1.5F, 5.5F)));
    public static final Block BLACK_STUCCO_STAIRS = register("black_stucco_stairs", new StairBlock(BLACK_STUCCO.defaultBlockState(), Properties.copy(BLACK_STUCCO)));
    public static final Block BLACK_STUCCO_SLAB = register("black_stucco_slab", new SlabBlock(Properties.copy(BLACK_STUCCO)));
    public static final Block BLACK_STUCCO_WALL = register("black_stucco_wall", new WallBlock(Properties.copy(BLACK_STUCCO)));

    private static <T extends Block> T register(String name, T block) {
        var registry = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name), block);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Verdance.MOD_ID, name), new BlockItem(registry, new Item.Properties()));

        return registry;
    }

    private static <T extends Block> T registerNoItem(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name), block);
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
