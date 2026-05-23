package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.Verdance;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class VerdanceBlockSetTypes {
    public static final BlockSetType MULBERRY = register("verdance:mulberry");

    private static BlockSetType register(String name) {
        var type = new BlockSetType(name);

        return BlockSetType.register(type);
    }
}
