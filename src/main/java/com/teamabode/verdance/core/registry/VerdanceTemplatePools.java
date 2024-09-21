package com.teamabode.verdance.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import java.util.List;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePool.Projection;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;

public class VerdanceTemplatePools {
    public static final RegistryKey<StructurePool> TOWN_RUINS_BUILDINGS = createKey("town_ruins/buildings");
    public static final RegistryKey<StructurePool> TOWN_RUINS_TOWN_CENTERS = createKey("town_ruins/town_centers");
    public static final RegistryKey<StructurePool> TOWN_RUINS_TOWN_CENTER_TOPS = createKey("town_ruins/town_center_tops");
    public static final RegistryKey<StructurePool> TOWN_RUINS_DECORATIONS = createKey("town_ruins/decorations");
    public static final RegistryKey<StructurePool> TOWN_RUINS_ROADS = createKey("town_ruins/roads");
    public static final RegistryKey<StructurePool> TOWN_RUINS_ROAD_ENDS = createKey("town_ruins/road_ends");

    public static void register(Registerable<StructurePool> context) {
        var templatePools = context.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);
        var processorLists = context.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);

        var emptyPool = templatePools.getOrThrow(StructurePools.EMPTY);
        var roadEndsPool = templatePools.getOrThrow(TOWN_RUINS_ROAD_ENDS);

        var processor = processorLists.getOrThrow(VerdanceProcessorLists.TOWN_RUINS_ARCHAEOLOGY);
        var smallProcessor = processorLists.getOrThrow(VerdanceProcessorLists.TOWN_RUINS_SMALL_ARCHAEOLOGY);
        var roadProcessor = processorLists.getOrThrow(VerdanceProcessorLists.TOWN_RUINS_ROAD_ARCHAEOLOGY);

        context.register(TOWN_RUINS_BUILDINGS, new StructurePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_1", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_2", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_3", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_4", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_5", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_6", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_7", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_8", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_9", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_10", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/buildings/building_11", processor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_TOWN_CENTERS, new StructurePool(roadEndsPool, List.of(
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/town_centers/center_1", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/town_centers/center_2", processor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/town_centers/center_3", processor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_TOWN_CENTER_TOPS, new StructurePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/town_centers/center_top_1", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/town_centers/center_top_2", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/town_centers/center_top_3", smallProcessor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_DECORATIONS, new StructurePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/decorations/decoration_1", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/decorations/decoration_2", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/decorations/decoration_3", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/decorations/decoration_4", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/decorations/decoration_5", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/decorations/decoration_6", smallProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/decorations/decoration_7", smallProcessor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_ROADS, new StructurePool(roadEndsPool, List.of(
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_1", roadProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_2", roadProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_3", roadProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_4", roadProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_start", roadProcessor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_ROAD_ENDS, new StructurePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_end_1", roadProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_end_2", roadProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("verdance:town_ruins/roads/road_end_3", roadProcessor), 1)
        ), Projection.RIGID));
    }

    private static RegistryKey<StructurePool> createKey(String name) {
        return RegistryKey.of(RegistryKeys.TEMPLATE_POOL, Verdance.id(name));
    }
}
