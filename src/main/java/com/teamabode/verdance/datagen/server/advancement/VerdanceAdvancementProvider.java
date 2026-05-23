package com.teamabode.verdance.datagen.server.advancement;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class VerdanceAdvancementProvider extends AdvancementProvider {

    public VerdanceAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup, ExistingFileHelper fileHelper) {
        super(output, registryLookup, fileHelper, List.of(
                new VerdanceAdvancementGenerator()
        ));
    }
}
