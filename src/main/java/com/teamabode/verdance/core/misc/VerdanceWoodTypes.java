package com.teamabode.verdance.core.misc;

import com.teamabode.verdance.Verdance;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class VerdanceWoodTypes {
    public static final WoodType MULBERRY = register("verdance:mulberry", VerdanceBlockSetTypes.MULBERRY);

    private static WoodType register(String name, BlockSetType setType) {
        var type = new WoodType(name, setType);

        return WoodType.register(type);
    }
}
