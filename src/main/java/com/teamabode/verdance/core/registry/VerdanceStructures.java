package com.teamabode.verdance.core.registry;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet.Named;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

public class VerdanceStructures {
    public static final ResourceKey<Structure> TOWN_RUINS = createKey("town_ruins");

    public static void register(BootstrapContext<Structure> context) {
        var biomes = context.lookup(Registries.BIOME);
        var templatePools = context.lookup(Registries.TEMPLATE_POOL);

        var hasTownRuins = biomes.getOrThrow(VerdanceBiomeTags.HAS_TOWN_RUINS);
        var centersPool = templatePools.getOrThrow(VerdanceTemplatePools.TOWN_RUINS_TOWN_CENTERS);

        context.register(TOWN_RUINS, new JigsawStructure(
                new StructureSettings.Builder(hasTownRuins)
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .terrainAdapation(TerrainAdjustment.BURY)
                        .build(),
                centersPool,
                4,
                ConstantHeight.of(VerticalAnchor.absolute(-15)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Verdance.id(name));
    }
}
