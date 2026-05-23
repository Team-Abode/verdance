package com.teamabode.verdance.core.event;

import com.mojang.datafixers.util.Pair;
import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.mixin.accessor.StructurePoolAccessor;
import com.teamabode.verdance.core.registry.VerdancePlacedFeatures;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;

@EventBusSubscriber(modid = Verdance.MOD_ID)
public class StructureEvents {

    @SubscribeEvent
    private static void onServerAboutToStart(ServerAboutToStartEvent event) {
        var registryManager = event.getServer().registryAccess();
        var placedFeatures = registryManager.lookupOrThrow(Registries.PLACED_FEATURE);

        modifyStructurePool(ResourceLocation.fromNamespaceAndPath("minecraft", "village/desert/decor"), registryManager, elements -> {
            elements.put(StructurePoolElement.feature(placedFeatures.getOrThrow(VerdancePlacedFeatures.PILE_CANTALOUPE)).apply(StructureTemplatePool.Projection.RIGID), 4);
        });
    }

    // All code after this point was pretty much yoinked by Chikorita Lover ;) (thanks btw!)
    private static void modifyStructurePool(ResourceLocation id, HolderLookup.Provider registries, Modifier modifier) {
        ResourceKey<StructureTemplatePool> pool = ResourceKey.create(Registries.TEMPLATE_POOL, id);
        StructurePoolAccessor accessor = (StructurePoolAccessor) registries.lookupOrThrow(Registries.TEMPLATE_POOL).getOrThrow(pool).value();
        Object2IntArrayMap<StructurePoolElement> builder = new Object2IntArrayMap<>();
        accessor.getRawTemplates().forEach(pair -> builder.put(pair.getFirst(), pair.getSecond().intValue()));
        modifier.apply(builder);
        accessor.setRawTemplates(builder.object2IntEntrySet().stream().map(entry -> Pair.of(entry.getKey(), entry.getIntValue())).toList());
        accessor.getTemplates().clear();
        builder.forEach((element, weight) -> {
            for (int i = 0; i < weight; ++i) {
                accessor.getTemplates().add(element);
            }
        });
    }

    @FunctionalInterface
    public interface Modifier {
        void apply(Object2IntArrayMap<StructurePoolElement> elements);
    }
}
