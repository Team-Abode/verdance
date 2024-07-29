package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.core.registry.VerdanceBiomes;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class VerdanceBiomeTagProvider extends FabricTagProvider<Biome> {

    public VerdanceBiomeTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.getOrCreateTagBuilder(BiomeTags.IS_FOREST)
                .add(VerdanceBiomes.MULBERRY_FOREST);
        this.getOrCreateTagBuilder(BiomeTags.IS_MOUNTAIN)
                .add(VerdanceBiomes.MULBERRY_FOREST);
        this.getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD)
                .add(VerdanceBiomes.MULBERRY_FOREST);
        this.getOrCreateTagBuilder(VerdanceBiomeTags.HAS_CANTALOUPE)
                .forceAddTag(BiomeTags.IS_SAVANNA);
        this.getOrCreateTagBuilder(VerdanceBiomeTags.HAS_GOLDEN_WOLF)
                .add(VerdanceBiomes.MULBERRY_FOREST);
        this.getOrCreateTagBuilder(VerdanceBiomeTags.HAS_VIOLET)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .add(Biomes.OLD_GROWTH_BIRCH_FOREST)
                .add(Biomes.WINDSWEPT_HILLS)
                .add(Biomes.WINDSWEPT_FOREST);
    }
}
