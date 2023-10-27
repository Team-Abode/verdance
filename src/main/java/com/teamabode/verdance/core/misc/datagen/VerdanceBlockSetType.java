package com.teamabode.verdance.core.misc.datagen;

import com.teamabode.verdance.Verdance;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class VerdanceBlockSetType {
    public static final BlockSetType MULBERRY = new BlockSetTypeBuilder().register(Verdance.id("mulberry"));
}
