package com.teamabode.verdance.core.misc;

import com.google.common.collect.Maps;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

public class VerdanceBlockFamilies {
    private static final Map<Block, BlockFamily> BLOCK_FAMILIES = Maps.newHashMap();
    public static final BlockFamily MULBERRY = create(VerdanceBlocks.MULBERRY_PLANKS.get())
            .stairs(VerdanceBlocks.MULBERRY_STAIRS.get())
            .slab(VerdanceBlocks.MULBERRY_SLAB.get())
            .fence(VerdanceBlocks.MULBERRY_FENCE.get())
            .fenceGate(VerdanceBlocks.MULBERRY_FENCE_GATE.get())
            .door(VerdanceBlocks.MULBERRY_DOOR.get())
            .trapdoor(VerdanceBlocks.MULBERRY_TRAPDOOR.get())
            .pressurePlate(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.get())
            .button(VerdanceBlocks.MULBERRY_BUTTON.get())
            .sign(VerdanceBlocks.MULBERRY_SIGN.get(), VerdanceBlocks.MULBERRY_WALL_SIGN.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .dontGenerateModel()
            .getFamily();
    public static final BlockFamily WHITE_STUCCO = create(VerdanceBlocks.WHITE_STUCCO.get())
            .stairs(VerdanceBlocks.WHITE_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.WHITE_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.WHITE_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily LIGHT_GRAY_STUCCO = create(VerdanceBlocks.LIGHT_GRAY_STUCCO.get())
            .stairs(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily GRAY_STUCCO = create(VerdanceBlocks.GRAY_STUCCO.get())
            .stairs(VerdanceBlocks.GRAY_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.GRAY_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.GRAY_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily BLACK_STUCCO = create(VerdanceBlocks.BLACK_STUCCO.get())
            .stairs(VerdanceBlocks.BLACK_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.BLACK_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.BLACK_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily BROWN_STUCCO = create(VerdanceBlocks.BROWN_STUCCO.get())
            .stairs(VerdanceBlocks.BROWN_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.BROWN_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.BROWN_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily RED_STUCCO = create(VerdanceBlocks.RED_STUCCO.get())
            .stairs(VerdanceBlocks.RED_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.RED_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.RED_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily ORANGE_STUCCO = create(VerdanceBlocks.ORANGE_STUCCO.get())
            .stairs(VerdanceBlocks.ORANGE_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.ORANGE_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.ORANGE_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily YELLOW_STUCCO = create(VerdanceBlocks.YELLOW_STUCCO.get())
            .stairs(VerdanceBlocks.YELLOW_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.YELLOW_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.YELLOW_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily LIME_STUCCO = create(VerdanceBlocks.LIME_STUCCO.get())
            .stairs(VerdanceBlocks.LIME_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.LIME_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.LIME_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily GREEN_STUCCO = create(VerdanceBlocks.GREEN_STUCCO.get())
            .stairs(VerdanceBlocks.GREEN_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.GREEN_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.GREEN_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco").getFamily();
    public static final BlockFamily CYAN_STUCCO = create(VerdanceBlocks.CYAN_STUCCO.get())
            .stairs(VerdanceBlocks.CYAN_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.CYAN_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.CYAN_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily LIGHT_BLUE_STUCCO = create(VerdanceBlocks.LIGHT_BLUE_STUCCO.get())
            .stairs(VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily BLUE_STUCCO = create(VerdanceBlocks.BLUE_STUCCO.get())
            .stairs(VerdanceBlocks.BLUE_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.BLUE_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.BLUE_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily PURPLE_STUCCO = create(VerdanceBlocks.PURPLE_STUCCO.get())
            .stairs(VerdanceBlocks.PURPLE_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.PURPLE_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.PURPLE_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily MAGENTA_STUCCO = create(VerdanceBlocks.MAGENTA_STUCCO.get())
            .stairs(VerdanceBlocks.MAGENTA_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.MAGENTA_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.MAGENTA_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();
    public static final BlockFamily PINK_STUCCO = create(VerdanceBlocks.PINK_STUCCO.get())
            .stairs(VerdanceBlocks.PINK_STUCCO_STAIRS.get())
            .slab(VerdanceBlocks.PINK_STUCCO_SLAB.get())
            .wall(VerdanceBlocks.PINK_STUCCO_WALL.get())
            .recipeGroupPrefix("stucco")
            .recipeUnlockedBy("has_stucco")
            .getFamily();

    private static BlockFamily.Builder create(Block baseBlock) {
        BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
        BlockFamily blockFamily = BLOCK_FAMILIES.put(baseBlock, builder.getFamily());
        if (blockFamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
        } else {
            return builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return BLOCK_FAMILIES.values().stream();
    }
}
