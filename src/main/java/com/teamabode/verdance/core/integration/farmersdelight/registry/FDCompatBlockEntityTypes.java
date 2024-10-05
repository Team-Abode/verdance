package com.teamabode.verdance.core.integration.farmersdelight.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.integration.farmersdelight.block.entity.CompatCabinetBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class FDCompatBlockEntityTypes {
    public static final BlockEntityType<CompatCabinetBlockEntity> CABINET = register("cabinet", BlockEntityType.Builder.create(CompatCabinetBlockEntity::new, FDCompatBlocks.MULBERRY_CABINET));

    public static void register() {

    }

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType.Builder<T> builder) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Verdance.id(name), builder.build());
    }
}
