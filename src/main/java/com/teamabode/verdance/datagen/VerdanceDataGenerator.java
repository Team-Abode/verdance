package com.teamabode.verdance.datagen;

import com.teamabode.verdance.core.registry.*;
import com.teamabode.verdance.datagen.client.VerdanceModelProvider;
import com.teamabode.verdance.datagen.server.*;
import com.teamabode.verdance.datagen.server.tag.VerdanceBiomeTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceBlockTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class VerdanceDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(VerdanceRecipeProvider::new);
        pack.addProvider(VerdanceBlockTagProvider::new);
        pack.addProvider(VerdanceItemTagProvider::new);
        pack.addProvider(VerdanceBiomeTagProvider::new);
        pack.addProvider(VerdanceArchaeologyLootTableProvider::new);
        pack.addProvider(VerdanceBlockLootTableProvider::new);
        pack.addProvider(VerdanceDynamicRegistryProvider::new);
        pack.addProvider(VerdanceAdvancementProvider::new);

        // Client Data
        pack.addProvider(VerdanceModelProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, VerdanceTrimPatterns::register);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, VerdanceConfiguredFeatures::register);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, VerdancePlacedFeatures::register);
        registryBuilder.addRegistry(RegistryKeys.BIOME, VerdanceBiomes::register);
        registryBuilder.addRegistry(RegistryKeys.PROCESSOR_LIST, VerdanceProcessorLists::register);
        registryBuilder.addRegistry(RegistryKeys.TEMPLATE_POOL, VerdanceTemplatePools::register);
        registryBuilder.addRegistry(RegistryKeys.STRUCTURE, VerdanceStructures::register);
        registryBuilder.addRegistry(RegistryKeys.STRUCTURE_SET, VerdanceStructureSets::register);
    }
}
