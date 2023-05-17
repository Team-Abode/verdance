package com.teamabode.verdance.core.misc.datagen;

import com.teamabode.verdance.Verdance;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class VerdanceWoodType {
    public static final WoodType MULBERRY = WoodTypeRegistry.register(new ResourceLocation(Verdance.MOD_ID, "mulberry"), VerdanceBlockSetType.MULBERRY);

    public static void register() {}
}
