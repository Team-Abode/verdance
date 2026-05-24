package com.teamabode.verdance.datagen.server.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class VerdanceLootTableProvider extends LootTableProvider {
    public VerdanceLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(VerdanceArchaeologyLootTableSubProvider::new, LootContextParamSets.ARCHAEOLOGY),
                new SubProviderEntry(VerdanceBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(VerdanceGameplayLootTableSubProvider::new, LootContextParamSets.GIFT)
        ), registries);
    }
}
