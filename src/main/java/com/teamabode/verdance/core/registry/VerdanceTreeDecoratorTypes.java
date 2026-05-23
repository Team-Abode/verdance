package com.teamabode.verdance.core.registry;

import com.mojang.serialization.MapCodec;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.worldgen.SilkCocoonTreeDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VerdanceTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, Verdance.MOD_ID);

    public static final Supplier<TreeDecoratorType<SilkCocoonTreeDecorator>> SILK_COCOON = register("silk_cocoon", SilkCocoonTreeDecorator.CODEC);

    private static <P extends TreeDecorator> Supplier<TreeDecoratorType<P>> register(String name, MapCodec<P> decorator) {
        return REGISTRY.register(name, () -> new TreeDecoratorType<>(decorator));
    }
}
