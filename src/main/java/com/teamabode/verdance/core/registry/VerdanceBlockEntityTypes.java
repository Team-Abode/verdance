package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.entity.SilkCocoonBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class VerdanceBlockEntityTypes {
    public static final BlockEntityType<SilkCocoonBlockEntity> SILK_COCOON = register("silk_cocoon", FabricBlockEntityTypeBuilder.create(SilkCocoonBlockEntity::new, VerdanceBlocks.SILK_COCOON));

    private static <E extends BlockEntity> BlockEntityType<E> register(String name, FabricBlockEntityTypeBuilder<E> blockEntity) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Verdance.id(name), blockEntity.build());
    }

    public static void register() {

    }
}
