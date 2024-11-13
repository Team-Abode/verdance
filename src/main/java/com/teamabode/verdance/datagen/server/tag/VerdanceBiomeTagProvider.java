package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.core.registry.VerdanceBiomes;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import java.util.concurrent.CompletableFuture;

public class VerdanceBiomeTagProvider extends FabricTagProvider<Biome> {

    public VerdanceBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        isForest();
        isMountain();
        isOverworld();
        snowGolemMelts();
        spawnsWarmVariantFrogs();
        strongholdBiasedTo();
        hasCantaloupe();
        hasViolet();
        hasStructure();
        conventionalTags();
    }

    private void isForest() {
        this.getOrCreateTagBuilder(BiomeTags.IS_FOREST).add(VerdanceBiomes.MULBERRY_FOREST).setReplace(false);;
    }

    private void isMountain() {
        this.getOrCreateTagBuilder(BiomeTags.IS_MOUNTAIN).add(VerdanceBiomes.MULBERRY_FOREST).setReplace(false);;
    }

    private void isOverworld() {
        this.getOrCreateTagBuilder(BiomeTags.IS_OVERWORLD)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS)
                .setReplace(false);
    }

    private void snowGolemMelts() {
        this.getOrCreateTagBuilder(BiomeTags.SNOW_GOLEM_MELTS).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
    }

    private void spawnsWarmVariantFrogs() {
        this.getOrCreateTagBuilder(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
    }

    private void strongholdBiasedTo() {
        this.getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS)
                .setReplace(false);;
    }

    private void hasCantaloupe() {
        this.getOrCreateTagBuilder(VerdanceBiomeTags.HAS_CANTALOUPE)
                .forceAddTag(BiomeTags.IS_SAVANNA)
                .add(VerdanceBiomes.SHRUBLANDS);
    }

    private void hasViolet() {
        this.getOrCreateTagBuilder(VerdanceBiomeTags.HAS_VIOLET)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .add(BiomeKeys.OLD_GROWTH_BIRCH_FOREST)
                .add(BiomeKeys.WINDSWEPT_HILLS)
                .add(BiomeKeys.WINDSWEPT_FOREST);
    }

    private void hasStructure() {
        this.getOrCreateTagBuilder(BiomeTags.MINESHAFT_HAS_STRUCTURE).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
        this.getOrCreateTagBuilder(BiomeTags.RUINED_PORTAL_STANDARD_HAS_STRUCTURE).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);
        this.getOrCreateTagBuilder(VerdanceBiomeTags.HAS_TOWN_RUINS)
                .add(VerdanceBiomes.SHRUBLANDS)
                .forceAddTag(ConventionalBiomeTags.DESERT);
    }

    private void conventionalTags() {
        this.getOrCreateTagBuilder(ConventionalBiomeTags.CLIMATE_HOT).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
        this.getOrCreateTagBuilder(ConventionalBiomeTags.VEGETATION_DENSE).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
    }
}
