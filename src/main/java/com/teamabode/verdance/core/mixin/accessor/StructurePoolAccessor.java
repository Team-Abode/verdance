package com.teamabode.verdance.core.mixin.accessor;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

@Mixin(StructureTemplatePool.class)
public interface StructurePoolAccessor {
    @Accessor("elementCounts")
    List<Pair<StructurePoolElement, Integer>> getElementCounts();

    @Accessor("elementCounts")
    @Mutable
    void setElementCounts(List<Pair<StructurePoolElement, Integer>> list);

    @Accessor("elements")
    ObjectArrayList<StructurePoolElement> getElements();
}
