package com.teamabode.verdance.core.misc;

import net.minecraft.world.level.block.state.properties.BlockSetType;

public class VerdanceBlockSetTypes {
    public static final BlockSetType MULBERRY = register("mulberry");

    private static BlockSetType register(String name) {
        return BlockSetType.register(new BlockSetType(name));
    }
}
