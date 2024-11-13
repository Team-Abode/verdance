package com.teamabode.verdance.datagen.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class VerdanceDynamicRegistryProvider extends FabricDynamicRegistryProvider {

    public VerdanceDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        this.addAll(RegistryKeys.TRIM_PATTERN, registries, entries);
        this.addAll(RegistryKeys.CONFIGURED_FEATURE, registries, entries);
        this.addAll(RegistryKeys.PLACED_FEATURE, registries, entries);
        this.addAll(RegistryKeys.BIOME, registries, entries);
        this.addAll(RegistryKeys.PROCESSOR_LIST, registries, entries);
        this.addAll(RegistryKeys.TEMPLATE_POOL, registries, entries);
        this.addAll(RegistryKeys.STRUCTURE, registries, entries);
        this.addAll(RegistryKeys.STRUCTURE_SET, registries, entries);
    }

    private <T> void addAll(RegistryKey<Registry<T>> registry, RegistryWrapper.WrapperLookup registries, Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(registry));
    }

    @Override
    public String getName() {
        return "Dynamic Registries";
    }
}
