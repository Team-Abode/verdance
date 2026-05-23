package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.registry.VerdanceBiomes;
import com.teamabode.verdance.core.tag.VerdanceBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class VerdanceBiomeTagProvider extends BiomeTagsProvider {

    public VerdanceBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, Verdance.MOD_ID, existingFileHelper);
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
        this.tag(BiomeTags.IS_FOREST)
                .add(VerdanceBiomes.MULBERRY_FOREST);
    }

    private void isMountain() {
        this.tag(BiomeTags.IS_MOUNTAIN).add(VerdanceBiomes.MULBERRY_FOREST);
    }

    private void isOverworld() {
        this.tag(BiomeTags.IS_OVERWORLD)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS);
    }

    private void snowGolemMelts() {
        this.tag(BiomeTags.SNOW_GOLEM_MELTS).add(VerdanceBiomes.SHRUBLANDS);
    }

    private void spawnsWarmVariantFrogs() {
        this.tag(BiomeTags.SPAWNS_WARM_VARIANT_FROGS).add(VerdanceBiomes.SHRUBLANDS);
    }

    private void strongholdBiasedTo() {
        this.tag(BiomeTags.STRONGHOLD_BIASED_TO)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS);
    }

    private void hasCantaloupe() {
        this.tag(VerdanceBiomeTags.HAS_CANTALOUPE)
                .addTag(BiomeTags.IS_SAVANNA)
                .add(VerdanceBiomes.SHRUBLANDS);
    }

    private void hasGoldenWolf() {
        this.tag(VerdanceBiomeTags.HAS_GOLDEN_WOLF)
                .add(VerdanceBiomes.MULBERRY_FOREST);
    }

    private void hasViolet() {
        this.tag(VerdanceBiomeTags.HAS_VIOLET)
                .addTag(BiomeTags.IS_TAIGA)
                .add(Biomes.OLD_GROWTH_BIRCH_FOREST)
                .add(Biomes.WINDSWEPT_HILLS)
                .add(Biomes.WINDSWEPT_FOREST);
    }

    private void hasStructure() {
        this.tag(BiomeTags.HAS_TRIAL_CHAMBERS)
                .add(VerdanceBiomes.MULBERRY_FOREST)
                .add(VerdanceBiomes.SHRUBLANDS);
        this.tag(BiomeTags.HAS_MINESHAFT).add(VerdanceBiomes.SHRUBLANDS);
        this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).add(VerdanceBiomes.SHRUBLANDS);
        this.tag(VerdanceBiomeTags.HAS_TOWN_RUINS)
                .add(VerdanceBiomes.SHRUBLANDS)
                .addTag(Tags.Biomes.IS_DESERT);
    }

    private void conventionalTags() {
        this.tag(Tags.Biomes.IS_HOT_OVERWORLD).add(VerdanceBiomes.SHRUBLANDS);
        this.tag(Tags.Biomes.IS_DENSE_VEGETATION).add(VerdanceBiomes.SHRUBLANDS);
    }
}
