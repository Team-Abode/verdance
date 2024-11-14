package com.teamabode.verdance.core.misc;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.core.mixin.accessor.StructurePoolAccessor;
import com.teamabode.verdance.core.registry.VerdancePlacedFeatures;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.util.Identifier;

public class VerdanceStructurePoolModifiers {

    public static void register() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            var registryManager = server.getRegistryManager();
            var placedFeatures = registryManager.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE);

            modifyStructurePool(new Identifier("minecraft", "village/desert/decor"), registryManager, elements -> {
                elements.put(StructurePoolElement.ofFeature(placedFeatures.getOrThrow(VerdancePlacedFeatures.PILE_CANTALOUPE)).apply(StructurePool.Projection.RIGID), 4);
            });
        });
    }

    // All code after this point was pretty much yoinked by Chikorita Lover ;) (thanks btw!)
    private static void modifyStructurePool(Identifier id, RegistryWrapper.WrapperLookup registries, Modifier modifier) {
        RegistryKey<StructurePool> pool = RegistryKey.of(RegistryKeys.TEMPLATE_POOL, id);
        StructurePoolAccessor accessor = (StructurePoolAccessor) registries.getWrapperOrThrow(RegistryKeys.TEMPLATE_POOL).getOrThrow(pool).value();
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
