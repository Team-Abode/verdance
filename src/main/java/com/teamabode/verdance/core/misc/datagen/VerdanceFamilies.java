package com.teamabode.verdance.core.misc.datagen;

import com.google.common.collect.Maps;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class VerdanceFamilies {
    private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    public static final BlockFamily MULBERRY = create(VerdanceBlocks.MULBERRY_PLANKS).stairs(VerdanceBlocks.MULBERRY_STAIRS).slab(VerdanceBlocks.MULBERRY_SLAB).fence(VerdanceBlocks.MULBERRY_FENCE).fenceGate(VerdanceBlocks.MULBERRY_FENCE_GATE).door(VerdanceBlocks.MULBERRY_DOOR).trapdoor(VerdanceBlocks.MULBERRY_TRAPDOOR).pressurePlate(VerdanceBlocks.MULBERRY_PRESSURE_PLATE).button(VerdanceBlocks.MULBERRY_BUTTON).sign(VerdanceBlocks.MULBERRY_SIGN, VerdanceBlocks.MULBERRY_WALL_SIGN).recipeGroupPrefix("wooden").recipeUnlockedBy("has_planks").getFamily();

    public static final BlockFamily WHITE_STUCCO = create(VerdanceBlocks.WHITE_STUCCO).stairs(VerdanceBlocks.WHITE_STUCCO_STAIRS).slab(VerdanceBlocks.WHITE_STUCCO_SLAB).wall(VerdanceBlocks.WHITE_STUCCO_WALL).getFamily();

    public static final BlockFamily LIGHT_GRAY_STUCCO = create(VerdanceBlocks.LIGHT_GRAY_STUCCO).stairs(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS).slab(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB).wall(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL).getFamily();

    public static final BlockFamily GRAY_STUCCO = create(VerdanceBlocks.GRAY_STUCCO).stairs(VerdanceBlocks.GRAY_STUCCO_STAIRS).slab(VerdanceBlocks.GRAY_STUCCO_SLAB).wall(VerdanceBlocks.GRAY_STUCCO_WALL).getFamily();

    public static final BlockFamily BLACK_STUCCO = create(VerdanceBlocks.BLACK_STUCCO).stairs(VerdanceBlocks.BLACK_STUCCO_STAIRS).slab(VerdanceBlocks.BLACK_STUCCO_SLAB).wall(VerdanceBlocks.BLACK_STUCCO_WALL).getFamily();

    public static final BlockFamily BROWN_STUCCO = create(VerdanceBlocks.BROWN_STUCCO).stairs(VerdanceBlocks.BROWN_STUCCO_STAIRS).slab(VerdanceBlocks.BROWN_STUCCO_SLAB).wall(VerdanceBlocks.BROWN_STUCCO_WALL).getFamily();

    public static final BlockFamily RED_STUCCO = create(VerdanceBlocks.RED_STUCCO).stairs(VerdanceBlocks.RED_STUCCO_STAIRS).slab(VerdanceBlocks.RED_STUCCO_SLAB).wall(VerdanceBlocks.RED_STUCCO_WALL).getFamily();

    public static final BlockFamily ORANGE_STUCCO = create(VerdanceBlocks.ORANGE_STUCCO).stairs(VerdanceBlocks.ORANGE_STUCCO_STAIRS).slab(VerdanceBlocks.ORANGE_STUCCO_SLAB).wall(VerdanceBlocks.ORANGE_STUCCO_WALL).getFamily();

    public static final BlockFamily YELLOW_STUCCO = create(VerdanceBlocks.YELLOW_STUCCO).stairs(VerdanceBlocks.YELLOW_STUCCO_STAIRS).slab(VerdanceBlocks.YELLOW_STUCCO_SLAB).wall(VerdanceBlocks.YELLOW_STUCCO_WALL).getFamily();

    public static final BlockFamily LIME_STUCCO = create(VerdanceBlocks.LIME_STUCCO).stairs(VerdanceBlocks.LIME_STUCCO_STAIRS).slab(VerdanceBlocks.LIME_STUCCO_SLAB).wall(VerdanceBlocks.LIME_STUCCO_WALL).getFamily();

    public static final BlockFamily GREEN_STUCCO = create(VerdanceBlocks.GREEN_STUCCO).stairs(VerdanceBlocks.GREEN_STUCCO_STAIRS).slab(VerdanceBlocks.GREEN_STUCCO_SLAB).wall(VerdanceBlocks.GREEN_STUCCO_WALL).getFamily();

    public static final BlockFamily CYAN_STUCCO = create(VerdanceBlocks.CYAN_STUCCO).stairs(VerdanceBlocks.CYAN_STUCCO_STAIRS).slab(VerdanceBlocks.CYAN_STUCCO_SLAB).wall(VerdanceBlocks.CYAN_STUCCO_WALL).getFamily();

    public static final BlockFamily LIGHT_BLUE_STUCCO = create(VerdanceBlocks.LIGHT_BLUE_STUCCO).stairs(VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS).slab(VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB).wall(VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL).getFamily();

    public static final BlockFamily BLUE_STUCCO = create(VerdanceBlocks.BLUE_STUCCO).stairs(VerdanceBlocks.BLUE_STUCCO_STAIRS).slab(VerdanceBlocks.BLUE_STUCCO_SLAB).wall(VerdanceBlocks.BLUE_STUCCO_WALL).getFamily();

    public static final BlockFamily PURPLE_STUCCO = create(VerdanceBlocks.PURPLE_STUCCO).stairs(VerdanceBlocks.PURPLE_STUCCO_STAIRS).slab(VerdanceBlocks.PURPLE_STUCCO_SLAB).wall(VerdanceBlocks.PURPLE_STUCCO_WALL).getFamily();

    public static final BlockFamily MAGENTA_STUCCO = create(VerdanceBlocks.MAGENTA_STUCCO).stairs(VerdanceBlocks.MAGENTA_STUCCO_STAIRS).slab(VerdanceBlocks.MAGENTA_STUCCO_SLAB).wall(VerdanceBlocks.MAGENTA_STUCCO_WALL).getFamily();

    public static final BlockFamily PINK_STUCCO = create(VerdanceBlocks.PINK_STUCCO).stairs(VerdanceBlocks.PINK_STUCCO_STAIRS).slab(VerdanceBlocks.PINK_STUCCO_SLAB).wall(VerdanceBlocks.PINK_STUCCO_WALL).getFamily();

    private static BlockFamily.Builder create(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = MAP.put(baseBlock, builder.getFamily());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
