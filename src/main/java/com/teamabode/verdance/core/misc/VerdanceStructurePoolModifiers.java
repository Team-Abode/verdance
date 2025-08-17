package com.teamabode.verdance.core.misc;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.core.mixin.accessor.StructurePoolAccessor;
import com.teamabode.verdance.core.registry.VerdancePlacedFeatures;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.RegistryLookup;
import net.minecraft.core.RegistryAccess.Frozen;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class VerdanceStructurePoolModifiers {

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            var registryManager = server.registryAccess();
            var placedFeatures = registryManager.lookupOrThrow(Registries.PLACED_FEATURE);

            modifyStructurePool(ResourceLocation.fromNamespaceAndPath("minecraft", "village/desert/decor"), registryManager, elements -> {
                elements.put(StructurePoolElement.feature(placedFeatures.getOrThrow(VerdancePlacedFeatures.PILE_CANTALOUPE)).apply(StructureTemplatePool.Projection.RIGID), 4);
            });
        });
    }

    // All code after this point was pretty much yoinked by Chikorita Lover ;) (thanks btw!)
    private static void modifyStructurePool(ResourceLocation id, HolderLookup.Provider registries, Modifier modifier) {
        ResourceKey<StructureTemplatePool> pool = ResourceKey.create(Registries.TEMPLATE_POOL, id);
        StructurePoolAccessor accessor = (StructurePoolAccessor) registries.lookupOrThrow(Registries.TEMPLATE_POOL).getOrThrow(pool).value();
        Object2IntArrayMap<StructurePoolElement> builder = new Object2IntArrayMap<>();
        accessor.getElementCounts().forEach(pair -> builder.put(pair.getFirst(), pair.getSecond().intValue()));
        modifier.apply(builder);
        accessor.setElementCounts(builder.object2IntEntrySet().stream().map(entry -> Pair.of(entry.getKey(), entry.getIntValue())).toList());
        accessor.getElements().clear();
        builder.forEach((element, weight) -> {
            for (int i = 0; i < weight; ++i) {
                accessor.getElements().add(element);
            }
        });
    }

    @FunctionalInterface
    public interface Modifier {
        void apply(Object2IntArrayMap<StructurePoolElement> elements);
    }
}
