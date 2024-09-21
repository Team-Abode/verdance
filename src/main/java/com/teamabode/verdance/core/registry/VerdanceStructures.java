package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.registry.entry.RegistryEntryList.Named;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.Structure.Config;

public class VerdanceStructures {
    public static final RegistryKey<Structure> TOWN_RUINS = createKey("town_ruins");

    public static void register(Registerable<Structure> context) {
        var biomes = context.getRegistryLookup(RegistryKeys.BIOME);
        var templatePools = context.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        var hasTownRuins = biomes.getOrThrow(VerdanceBiomeTags.HAS_TOWN_RUINS);
        var centersPool = templatePools.getOrThrow(VerdanceTemplatePools.TOWN_RUINS_TOWN_CENTERS);

        context.register(TOWN_RUINS, new JigsawStructure(
                new Config.Builder(hasTownRuins)
                        .step(GenerationStep.Feature.UNDERGROUND_STRUCTURES)
                        .terrainAdaptation(StructureTerrainAdaptation.BURY)
                        .build(),
                centersPool,
                4,
                ConstantHeightProvider.create(YOffset.fixed(-15)),
                false,
                Heightmap.Type.WORLD_SURFACE_WG
        ));
    }

    private static RegistryKey<Structure> createKey(String name) {
        return RegistryKey.of(RegistryKeys.STRUCTURE, Verdance.id(name));
    }
}
