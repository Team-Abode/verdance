package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.entity.SilkCocoonBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class VerdanceBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Verdance.MOD_ID);

    public static final Supplier<BlockEntityType<SilkCocoonBlockEntity>> SILK_COCOON = register(
            "silk_cocoon",
            () -> BlockEntityType.Builder.of(SilkCocoonBlockEntity::new, VerdanceBlocks.SILK_COCOON.get()).build(null)
    );

    private static <E extends BlockEntity> Supplier<BlockEntityType<E>> register(String name, Supplier<BlockEntityType<E>> blockEntity) {
        return REGISTRY.register(name, blockEntity);
    }
}
