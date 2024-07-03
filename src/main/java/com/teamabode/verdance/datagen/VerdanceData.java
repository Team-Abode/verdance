package com.teamabode.verdance.datagen;

import com.teamabode.verdance.datagen.client.VerdanceModelProvider;
import com.teamabode.verdance.datagen.server.VerdanceArchaeologyLootTableProvider;
import com.teamabode.verdance.datagen.server.VerdanceBlockLootTableProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceBlockTagProvider;
import com.teamabode.verdance.datagen.server.tag.VerdanceItemTagProvider;
import com.teamabode.verdance.datagen.server.VerdanceRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class VerdanceData implements DataGeneratorEntrypoint {

    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(VerdanceRecipeProvider::new);
        pack.addProvider(VerdanceBlockTagProvider::new);
        pack.addProvider(VerdanceItemTagProvider::new);
        pack.addProvider(VerdanceBlockLootTableProvider::new);
        pack.addProvider(VerdanceArchaeologyLootTableProvider::new);

        // Client Data
        pack.addProvider(VerdanceModelProvider::new);
    }
}
