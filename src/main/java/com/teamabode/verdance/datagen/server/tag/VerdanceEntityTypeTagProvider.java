package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.core.registry.VerdanceEntityTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class VerdanceEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {

    public VerdanceEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.getOrCreateTagBuilder(EntityTypeTags.ARTHROPOD)
                .add(VerdanceEntityTypes.SILK_MOTH);
        this.getOrCreateTagBuilder(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)
                .add(VerdanceEntityTypes.SILK_MOTH);
    }
}
