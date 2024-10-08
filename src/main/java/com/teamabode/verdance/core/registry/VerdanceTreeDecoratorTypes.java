package com.teamabode.verdance.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.SilkCocoonTreeDecorator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class VerdanceTreeDecoratorTypes {

    public static final TreeDecoratorType<SilkCocoonTreeDecorator> SILK_COCOON = register("silk_cocoon", SilkCocoonTreeDecorator.CODEC);

    public static void register() {

    }

    private static <P extends TreeDecorator>TreeDecoratorType<P> register(String name, MapCodec<P> decorator) {
        return Registry.register(Registries.TREE_DECORATOR_TYPE, Verdance.id(name), new TreeDecoratorType<>(decorator));
    }
}
