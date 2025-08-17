package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.core.registry.VerdanceBiomes;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags;
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
        isForest();
        isMountain();
        isOverworld();
        snowGolemMelts();
        spawnsWarmVariantFrogs();
        strongholdBiasedTo();
        hasCantaloupe();
        hasGoldenWolf();
        hasViolet();
        hasStructure();
        conventionalTags();
    }

    private void isForest() {
        this.tag(BiomeTags.IS_FOREST).add(VerdanceBiomes.MULBERRY_FOREST).setReplace(false);;
    }

    private void isMountain() {
        this.tag(BiomeTags.IS_MOUNTAIN).add(VerdanceBiomes.MULBERRY_FOREST).setReplace(false);;
    }

    private void isOverworld() {
        this.tag(BiomeTags.IS_OVERWORLD)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS)
                .setReplace(false);
    }

    private void snowGolemMelts() {
        this.tag(BiomeTags.SNOW_GOLEM_MELTS).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
    }

    private void spawnsWarmVariantFrogs() {
        this.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
    }

    private void strongholdBiasedTo() {
        this.tag(BiomeTags.STRONGHOLD_BIASED_TO)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS)
                .setReplace(false);;
    }

    private void hasCantaloupe() {
        this.tag(VerdanceBiomeTags.HAS_CANTALOUPE)
                .forceAddTag(BiomeTags.IS_SAVANNA)
                .add(VerdanceBiomes.SHRUBLANDS);
    }

    private void hasGoldenWolf() {
        this.tag(VerdanceBiomeTags.HAS_GOLDEN_WOLF)
                .add(VerdanceBiomes.MULBERRY_FOREST);
    }

    private void hasViolet() {
        this.tag(VerdanceBiomeTags.HAS_VIOLET)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .add(Biomes.OLD_GROWTH_BIRCH_FOREST)
                .add(Biomes.WINDSWEPT_HILLS)
                .add(Biomes.WINDSWEPT_FOREST);
    }

    private void hasStructure() {
        this.tag(BiomeTags.HAS_TRIAL_CHAMBERS)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS)
                .setReplace(false);
        this.tag(BiomeTags.HAS_MINESHAFT).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
        this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);
        this.tag(VerdanceBiomeTags.HAS_TOWN_RUINS)
                .add(VerdanceBiomes.SHRUBLANDS)
                .forceAddTag(ConventionalBiomeTags.IS_DESERT);
    }

    private void conventionalTags() {
        this.tag(ConventionalBiomeTags.IS_HOT_OVERWORLD).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
        this.tag(ConventionalBiomeTags.IS_VEGETATION_DENSE).add(VerdanceBiomes.SHRUBLANDS).setReplace(false);;
    }
}
