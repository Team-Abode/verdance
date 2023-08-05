package com.teamabode.verdance.core.integration.general;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CompatUtils {

    public static <T extends Block> T registerBlock(String name, T block) {
        var registry = Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Verdance.MOD_ID, name), block);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Verdance.MOD_ID, name), new CompatBlockItem(registry, !(block instanceof CompatBlock)));

        return registry;
    }

    public static <T extends Item> T registerItem(String name, T item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Verdance.MOD_ID, name), item);
    }

    /*
    public static Block createMulberryCabinet() {
        if (!FabricLoader.getInstance().isModLoaded("farmersdelight")) {
            return new CompatBlock();
        }
        return new CabinetBlock();
    }

     */
}
