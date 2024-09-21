package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.Verdance;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.WoodType;

public class VerdanceWoodTypes {
    public static final WoodType MULBERRY = new WoodTypeBuilder().register(Verdance.id("mulberry"), VerdanceBlockSetTypes.MULBERRY);
}
