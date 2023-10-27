package com.teamabode.verdance.core.misc.datagen;

import com.teamabode.verdance.Verdance;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class VerdanceWoodType {
    public static final WoodType MULBERRY = new WoodTypeBuilder().register(Verdance.id("mulberry"), VerdanceBlockSetType.MULBERRY);

    public static void register() {}
}
