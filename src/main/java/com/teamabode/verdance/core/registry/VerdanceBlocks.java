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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class VerdanceBlocks {

    public static final Block CANTALOUPE = register("cantaloupe", new CantaloupeBlock(BlockBehaviour.Properties.of(Material.VEGETABLE, MaterialColor.COLOR_LIGHT_GREEN).strength(1.0F).sound(SoundType.WOOD)));
    public static final Block ATTACHED_CANTALOUPE_STEM = register("attached_cantaloupe_stem", new AttachedCantaloupeStemBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));
    public static final Block CANTALOUPE_STEM = registerNoItem("cantaloupe_stem", new CantaloupeStemBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.HARD_CROP)));

    public static final Block SHRUB = register("shrub", new ShrubBlock(BlockBehaviour.Properties.copy(Blocks.AZALEA)));


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
