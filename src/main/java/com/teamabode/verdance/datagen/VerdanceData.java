package com.teamabode.verdance.datagen;

import com.teamabode.sketch.core.registry.SketchRegistries;
import com.teamabode.verdance.core.key.*;
import com.teamabode.verdance.datagen.client.VerdanceModelProvider;
import com.teamabode.verdance.datagen.server.VerdanceArchaeologyLootTableProvider;
import com.teamabode.verdance.datagen.server.VerdanceBlockLootTableProvider;
import com.teamabode.verdance.datagen.server.VerdanceDynamicRegistryProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceBiomeTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceBlockTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceItemTagProvider;
import com.teamabode.verdance.datagen.server.VerdanceRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class VerdanceData implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(VerdanceRecipeProvider::new);
        pack.addProvider(VerdanceBlockTagProvider::new);
        pack.addProvider(VerdanceItemTagProvider::new);
        pack.addProvider(VerdanceBiomeTagProvider::new);
        pack.addProvider(VerdanceBlockLootTableProvider::new);
        pack.addProvider(VerdanceArchaeologyLootTableProvider::new);
        pack.addProvider(VerdanceDynamicRegistryProvider::new);

        // Client Data
        pack.addProvider(VerdanceModelProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.JUKEBOX_SONG, VerdanceJukeboxSongs::register);
        registryBuilder.add(Registries.WOLF_VARIANT, VerdanceWolfVariants::register);
        registryBuilder.add(SketchRegistries.BOAT_TYPE, VerdanceBoatTypes::register);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, VerdanceConfiguredFeatures::register);
        registryBuilder.add(Registries.PLACED_FEATURE, VerdancePlacedFeatures::register);
        registryBuilder.add(Registries.BIOME, VerdanceBiomes::register);
    }
}
