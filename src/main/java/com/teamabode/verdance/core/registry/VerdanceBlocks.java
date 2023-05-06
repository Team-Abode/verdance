package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.AttachedCantaloupeStemBlock;
import com.teamabode.verdance.common.block.CantaloupeBlock;
import com.teamabode.verdance.common.block.CantaloupeStemBlock;
import com.teamabode.verdance.common.block.ShrubBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class VerdanceBlocks {

    public static final Block CANTALOUPE = register("cantaloupe", new CantaloupeBlock(Properties.of(Material.VEGETABLE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD)));
    public static final Block ATTACHED_CANTALOUPE_STEM = register("attached_cantaloupe_stem", new AttachedCantaloupeStemBlock(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));
    public static final Block CANTALOUPE_STEM = registerNoItem("cantaloupe_stem", new CantaloupeStemBlock(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));

    public static final Block SHRUB = register("shrub", new ShrubBlock(Properties.copy(Blocks.AZALEA)));

    public static final Block WHITE_STUCCO = register("white_stucco", new Block(Properties.of(Material.STONE, MaterialColor.WOOL).requiresCorrectToolForDrops().strength(2.0F, 7.0F)));
    public static final Block WHITE_STUCCO_STAIRS = register("white_stucco_stairs", new StairBlock(WHITE_STUCCO.defaultBlockState(), Properties.copy(WHITE_STUCCO)));
    public static final Block WHITE_STUCCO_SLAB = register("white_stucco_slab", new SlabBlock(Properties.copy(WHITE_STUCCO)));
    public static final Block WHITE_STUCCO_WALL = register("white_stucco_wall", new WallBlock(Properties.copy(WHITE_STUCCO)));

    private static <T extends Block> T register(String name, T block) {
        var registry = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name), block);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Verdance.MOD_ID, name), new BlockItem(registry, new Item.Properties()));

        return registry;
    }

    private static <T extends Block> T registerNoItem(String name, T block) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name), block);
    }

    public static void register() {

    }
}
