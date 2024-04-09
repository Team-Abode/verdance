package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.Verdance;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class VerdanceBlockSets {
    public static final BlockSetType MULBERRY = register("mulberry");

    private static BlockSetType register(String name) {
        return BlockSetType.register(new BlockSetType(name));
    }
}
