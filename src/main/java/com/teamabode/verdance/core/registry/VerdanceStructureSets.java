package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;

public class VerdanceStructureSets {
    public static final RegistryKey<StructureSet> TOWN_RUINS = createKey("town_ruins");

    public static void register(Registerable<StructureSet> context) {
        var structures = context.getRegistryLookup(RegistryKeys.STRUCTURE);
        var townRuins = structures.getOrThrow(VerdanceStructures.TOWN_RUINS);

        context.register(TOWN_RUINS, new StructureSet(townRuins, new RandomSpreadStructurePlacement(
                34, 8,
                SpreadType.LINEAR,
                422693407
        )));
    }

    private static RegistryKey<StructureSet> createKey(String name) {
        return RegistryKey.of(RegistryKeys.STRUCTURE_SET, Verdance.id(name));
    }
}
