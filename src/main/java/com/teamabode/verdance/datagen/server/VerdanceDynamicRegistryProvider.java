package com.teamabode.verdance.datagen.server;

import com.teamabode.sketch.core.registry.SketchRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;

import java.util.concurrent.CompletableFuture;

public class VerdanceDynamicRegistryProvider extends FabricDynamicRegistryProvider {

    public VerdanceDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        this.addAll(Registries.JUKEBOX_SONG, registries, entries);
        this.addAll(Registries.WOLF_VARIANT, registries, entries);
        this.addAll(SketchRegistries.BOAT_TYPE, registries, entries);
        this.addAll(Registries.CONFIGURED_FEATURE, registries, entries);
        this.addAll(Registries.PLACED_FEATURE, registries, entries);
        this.addAll(Registries.BIOME, registries, entries);
    }

    private <T> void addAll(ResourceKey<Registry<T>> registry, HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(registry));
    }

    @Override
    public String getName() {
        return "Dynamic Registries";
    }
}
