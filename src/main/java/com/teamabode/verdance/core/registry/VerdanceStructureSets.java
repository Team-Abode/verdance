package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

public class VerdanceStructureSets {
    public static final ResourceKey<StructureSet> TOWN_RUINS = createKey("town_ruins");

    public static void register(BootstrapContext<StructureSet> context) {
        var structures = context.lookup(Registries.STRUCTURE);
        var townRuins = structures.getOrThrow(VerdanceStructures.TOWN_RUINS);

        context.register(TOWN_RUINS, new StructureSet(townRuins, new RandomSpreadStructurePlacement(
                34, 8,
                RandomSpreadType.LINEAR,
                422693407
        )));
    }

    private static ResourceKey<StructureSet> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Verdance.id(name));
    }
}
