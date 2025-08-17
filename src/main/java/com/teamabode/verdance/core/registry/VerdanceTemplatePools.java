package com.teamabode.verdance.core.registry;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import java.util.List;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool.Projection;

public class VerdanceTemplatePools {
    public static final ResourceKey<StructureTemplatePool> TOWN_RUINS_BUILDINGS = createKey("town_ruins/buildings");
    public static final ResourceKey<StructureTemplatePool> TOWN_RUINS_TOWN_CENTERS = createKey("town_ruins/town_centers");
    public static final ResourceKey<StructureTemplatePool> TOWN_RUINS_TOWN_CENTER_TOPS = createKey("town_ruins/town_center_tops");
    public static final ResourceKey<StructureTemplatePool> TOWN_RUINS_DECORATIONS = createKey("town_ruins/decorations");
    public static final ResourceKey<StructureTemplatePool> TOWN_RUINS_ROADS = createKey("town_ruins/roads");
    public static final ResourceKey<StructureTemplatePool> TOWN_RUINS_ROAD_ENDS = createKey("town_ruins/road_ends");

    public static void register(BootstrapContext<StructureTemplatePool> context) {
        var templatePools = context.lookup(Registries.TEMPLATE_POOL);
        var processorLists = context.lookup(Registries.PROCESSOR_LIST);

        var emptyPool = templatePools.getOrThrow(Pools.EMPTY);
        var roadEndsPool = templatePools.getOrThrow(TOWN_RUINS_ROAD_ENDS);

        var processor = processorLists.getOrThrow(VerdanceProcessorLists.TOWN_RUINS_ARCHAEOLOGY);
        var smallProcessor = processorLists.getOrThrow(VerdanceProcessorLists.TOWN_RUINS_SMALL_ARCHAEOLOGY);
        var roadProcessor = processorLists.getOrThrow(VerdanceProcessorLists.TOWN_RUINS_ROAD_ARCHAEOLOGY);

        context.register(TOWN_RUINS_BUILDINGS, new StructureTemplatePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_1", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_2", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_3", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_4", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_5", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_6", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_7", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_8", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_9", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_10", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/buildings/building_11", processor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_TOWN_CENTERS, new StructureTemplatePool(roadEndsPool, List.of(
                Pair.of(StructurePoolElement.single("verdance:town_ruins/town_centers/center_1", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/town_centers/center_2", processor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/town_centers/center_3", processor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_TOWN_CENTER_TOPS, new StructureTemplatePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.single("verdance:town_ruins/town_centers/center_top_1", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/town_centers/center_top_2", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/town_centers/center_top_3", smallProcessor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_DECORATIONS, new StructureTemplatePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.single("verdance:town_ruins/decorations/decoration_1", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/decorations/decoration_2", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/decorations/decoration_3", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/decorations/decoration_4", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/decorations/decoration_5", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/decorations/decoration_6", smallProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/decorations/decoration_7", smallProcessor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_ROADS, new StructureTemplatePool(roadEndsPool, List.of(
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_1", roadProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_2", roadProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_3", roadProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_4", roadProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_start", roadProcessor), 1)
        ), Projection.RIGID));
        context.register(TOWN_RUINS_ROAD_ENDS, new StructureTemplatePool(emptyPool, List.of(
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_end_1", roadProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_end_2", roadProcessor), 1),
                Pair.of(StructurePoolElement.single("verdance:town_ruins/roads/road_end_3", roadProcessor), 1)
        ), Projection.RIGID));
    }

    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, Verdance.id(name));
    }
}
