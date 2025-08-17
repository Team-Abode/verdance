package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.common.block.entity.SilkCocoonBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class VerdanceBlockEntityTypes {
    public static final BlockEntityType<SilkCocoonBlockEntity> SILK_COCOON = register("silk_cocoon", BlockEntityType.Builder.of(SilkCocoonBlockEntity::new, VerdanceBlocks.SILK_COCOON));

    private static <E extends BlockEntity> BlockEntityType<E> register(String name, BlockEntityType.Builder<E> blockEntity) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Verdance.id(name), blockEntity.build());
    }

    public static void register() {

    }
}
