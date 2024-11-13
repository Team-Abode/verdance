package com.teamabode.verdance;

import com.chocohead.mm.api.ClassTinkerers;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class VerdanceEarlyRiser implements Runnable {

    @Override
    public void run() {
        MappingResolver resolver = FabricLoader.getInstance().getMappingResolver();
        String boatType = resolver.mapClassName("intermediary", "net.minecraft.class_1690$class_1692");
        String block = 'L' + resolver.mapClassName("intermediary", "net.minecraft.class_2248") + ';';

        ClassTinkerers.enumBuilder(boatType, block, String.class).addEnum("VERDANCE_MULBERRY", () -> new Object[]{VerdanceBlocks.MULBERRY_PLANKS, "mulberry"}).build();
    }
}
