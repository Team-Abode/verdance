package com.teamabode.verdance.core.data;

import com.teamabode.verdance.core.data.client.VerdanceModelProvider;
import com.teamabode.verdance.core.data.server.VerdanceBlockLootTableProvider;
import com.teamabode.verdance.core.data.server.tag.VerdanceBiomeTagProvider;
import com.teamabode.verdance.core.data.server.tag.VerdanceBlockTagProvider;
import com.teamabode.verdance.core.data.server.tag.VerdanceItemTagProvider;
import com.teamabode.verdance.core.data.server.VerdanceRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class VerdanceData implements DataGeneratorEntrypoint {

    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        // Server Data
        pack.addProvider(VerdanceRecipeProvider::new);
        pack.addProvider(VerdanceBlockTagProvider::new);
        pack.addProvider(VerdanceItemTagProvider::new);
        pack.addProvider(VerdanceBiomeTagProvider::new);
        pack.addProvider(VerdanceBlockLootTableProvider::new);

        // Client Data
        pack.addProvider(VerdanceModelProvider::new);
    }
}
