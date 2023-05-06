package com.teamabode.verdance.core.data.server.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;

import java.util.concurrent.CompletableFuture;

public class VerdanceBiomeTagProvider extends FabricTagProvider<Biome> {

    public VerdanceBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    protected void addTags(HolderLookup.Provider arg) {
        this.getOrCreateTagBuilder(BiomeTags.SPAWNS_GOLD_RABBITS).setReplace(false);

        this.getOrCreateTagBuilder(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).setReplace(false);
    }
}
