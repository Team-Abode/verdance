package com.teamabode.verdance.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.SilkCocoonTreeDecorator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class VerdanceTreeDecoratorTypes {

    public static final TreeDecoratorType<SilkCocoonTreeDecorator> SILK_COCOON = register("silk_cocoon", SilkCocoonTreeDecorator.CODEC);

    public static void register() {

    }

    private static <P extends TreeDecorator>TreeDecoratorType<P> register(String name, MapCodec<P> decorator) {
        return Registry.register(BuiltInRegistries.TREE_DECORATOR_TYPE, Verdance.id(name), new TreeDecoratorType<>(decorator));
    }
}
