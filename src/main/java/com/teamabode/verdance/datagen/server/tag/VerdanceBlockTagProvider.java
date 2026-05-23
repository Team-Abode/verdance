package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.Verdance;
import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class VerdanceBlockTagProvider extends BlockTagsProvider {

    public VerdanceBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Verdance.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider arg) {
        walls();
        planks();
        stairs();
        slabs();
        fences();
        fenceGates();
        doors();
        trapdoors();
        pressurePlates();
        buttons();
        signs();
        logs();
        saplings();
        leaves();
        flowerPots();
        maintainsFarmland();

        mineablePickaxe();
        mineableHoe();
        mineableAxe();
        swordEfficient();

        silkMothsSpawnableOn();
        replaceableBySugarCane();
        shrubs();
        floweringShrubs();
        shrubMayPlaceOn();
        flowers();
        smallFlowers();
        crops();
    }

    private void mineablePickaxe() {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(VerdanceBlocks.WHITE_STUCCO.get())
                .add(VerdanceBlocks.WHITE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.WHITE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.WHITE_STUCCO_WALL.get())
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO.get())
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB.get())
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL.get())
                .add(VerdanceBlocks.GRAY_STUCCO.get())
                .add(VerdanceBlocks.GRAY_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.GRAY_STUCCO_SLAB.get())
                .add(VerdanceBlocks.GRAY_STUCCO_WALL.get())
                .add(VerdanceBlocks.BLACK_STUCCO.get())
                .add(VerdanceBlocks.BLACK_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.BLACK_STUCCO_SLAB.get())
                .add(VerdanceBlocks.BLACK_STUCCO_WALL.get())
                .add(VerdanceBlocks.BROWN_STUCCO.get())
                .add(VerdanceBlocks.BROWN_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.BROWN_STUCCO_SLAB.get())
                .add(VerdanceBlocks.BROWN_STUCCO_WALL.get())
                .add(VerdanceBlocks.RED_STUCCO.get())
                .add(VerdanceBlocks.RED_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.RED_STUCCO_SLAB.get())
                .add(VerdanceBlocks.RED_STUCCO_WALL.get())
                .add(VerdanceBlocks.ORANGE_STUCCO.get())
                .add(VerdanceBlocks.ORANGE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.ORANGE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.ORANGE_STUCCO_WALL.get())
                .add(VerdanceBlocks.YELLOW_STUCCO.get())
                .add(VerdanceBlocks.YELLOW_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.YELLOW_STUCCO_SLAB.get())
                .add(VerdanceBlocks.YELLOW_STUCCO_WALL.get())
                .add(VerdanceBlocks.LIME_STUCCO.get())
                .add(VerdanceBlocks.LIME_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.LIME_STUCCO_SLAB.get())
                .add(VerdanceBlocks.LIME_STUCCO_WALL.get())
                .add(VerdanceBlocks.GREEN_STUCCO.get())
                .add(VerdanceBlocks.GREEN_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.GREEN_STUCCO_SLAB.get())
                .add(VerdanceBlocks.GREEN_STUCCO_WALL.get())
                .add(VerdanceBlocks.CYAN_STUCCO.get())
                .add(VerdanceBlocks.CYAN_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.CYAN_STUCCO_SLAB.get())
                .add(VerdanceBlocks.CYAN_STUCCO_WALL.get())
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO.get())
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL.get())
                .add(VerdanceBlocks.BLUE_STUCCO.get())
                .add(VerdanceBlocks.BLUE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.BLUE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.BLUE_STUCCO_WALL.get())
                .add(VerdanceBlocks.PURPLE_STUCCO.get())
                .add(VerdanceBlocks.PURPLE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.PURPLE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.PURPLE_STUCCO_WALL.get())
                .add(VerdanceBlocks.MAGENTA_STUCCO.get())
                .add(VerdanceBlocks.MAGENTA_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.MAGENTA_STUCCO_SLAB.get())
                .add(VerdanceBlocks.MAGENTA_STUCCO_WALL.get())
                .add(VerdanceBlocks.PINK_STUCCO.get())
                .add(VerdanceBlocks.PINK_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.PINK_STUCCO_SLAB.get())
                .add(VerdanceBlocks.PINK_STUCCO_WALL.get());
    }

    private void mineableHoe() {
        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(VerdanceBlocks.MULBERRY_LEAVES.get())
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get());
    }

    private void mineableAxe() {
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(VerdanceBlocks.CANTALOUPE.get())
                .add(VerdanceBlocks.WHITE_CUSHION.get())
                .add(VerdanceBlocks.LIGHT_GRAY_CUSHION.get())
                .add(VerdanceBlocks.GRAY_CUSHION.get())
                .add(VerdanceBlocks.BLACK_CUSHION.get())
                .add(VerdanceBlocks.BROWN_CUSHION.get())
                .add(VerdanceBlocks.RED_CUSHION.get())
                .add(VerdanceBlocks.ORANGE_CUSHION.get())
                .add(VerdanceBlocks.YELLOW_CUSHION.get())
                .add(VerdanceBlocks.LIME_CUSHION.get())
                .add(VerdanceBlocks.GREEN_CUSHION.get())
                .add(VerdanceBlocks.CYAN_CUSHION.get())
                .add(VerdanceBlocks.LIGHT_BLUE_CUSHION.get())
                .add(VerdanceBlocks.BLUE_CUSHION.get())
                .add(VerdanceBlocks.PURPLE_CUSHION.get())
                .add(VerdanceBlocks.MAGENTA_CUSHION.get())
                .add(VerdanceBlocks.PINK_CUSHION.get());
    }

    private void swordEfficient() {
        this.tag(BlockTags.SWORD_EFFICIENT).add(VerdanceBlocks.CANTALOUPE.get());
    }

    private void walls() {
        this.tag(BlockTags.WALLS)
                .add(VerdanceBlocks.WHITE_STUCCO_WALL.get())
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL.get())
                .add(VerdanceBlocks.GRAY_STUCCO_WALL.get())
                .add(VerdanceBlocks.BLACK_STUCCO_WALL.get())
                .add(VerdanceBlocks.BROWN_STUCCO_WALL.get())
                .add(VerdanceBlocks.RED_STUCCO_WALL.get())
                .add(VerdanceBlocks.ORANGE_STUCCO_WALL.get())
                .add(VerdanceBlocks.YELLOW_STUCCO_WALL.get())
                .add(VerdanceBlocks.LIME_STUCCO_WALL.get())
                .add(VerdanceBlocks.GREEN_STUCCO_WALL.get())
                .add(VerdanceBlocks.CYAN_STUCCO_WALL.get())
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL.get())
                .add(VerdanceBlocks.BLUE_STUCCO_WALL.get())
                .add(VerdanceBlocks.PURPLE_STUCCO_WALL.get())
                .add(VerdanceBlocks.MAGENTA_STUCCO_WALL.get())
                .add(VerdanceBlocks.PINK_STUCCO_WALL.get());
    }

    private void planks() {
        this.tag(BlockTags.PLANKS)
                .add(VerdanceBlocks.MULBERRY_PLANKS.get());
    }

    private void stairs() {
        this.tag(BlockTags.STAIRS)
                .add(VerdanceBlocks.WHITE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.GRAY_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.BLACK_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.BROWN_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.RED_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.ORANGE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.YELLOW_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.LIME_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.GREEN_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.CYAN_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.BLUE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.PURPLE_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.MAGENTA_STUCCO_STAIRS.get())
                .add(VerdanceBlocks.PINK_STUCCO_STAIRS.get());

        this.tag(BlockTags.WOODEN_STAIRS)
                .add(VerdanceBlocks.MULBERRY_STAIRS.get());
    }

    private void slabs() {
        this.tag(BlockTags.SLABS)
                .add(VerdanceBlocks.WHITE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB.get())
                .add(VerdanceBlocks.GRAY_STUCCO_SLAB.get())
                .add(VerdanceBlocks.BLACK_STUCCO_SLAB.get())
                .add(VerdanceBlocks.BROWN_STUCCO_SLAB.get())
                .add(VerdanceBlocks.RED_STUCCO_SLAB.get())
                .add(VerdanceBlocks.ORANGE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.YELLOW_STUCCO_SLAB.get())
                .add(VerdanceBlocks.LIME_STUCCO_SLAB.get())
                .add(VerdanceBlocks.GREEN_STUCCO_SLAB.get())
                .add(VerdanceBlocks.CYAN_STUCCO_SLAB.get())
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.BLUE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.PURPLE_STUCCO_SLAB.get())
                .add(VerdanceBlocks.MAGENTA_STUCCO_SLAB.get())
                .add(VerdanceBlocks.PINK_STUCCO_SLAB.get());

        this.tag(BlockTags.WOODEN_SLABS)
                .add(VerdanceBlocks.MULBERRY_SLAB.get());
    }

    private void fences() {
        this.tag(BlockTags.WOODEN_FENCES)
                .add(VerdanceBlocks.MULBERRY_FENCE.get());
    }

    private void fenceGates() {
        this.tag(BlockTags.FENCE_GATES)
                .add(VerdanceBlocks.MULBERRY_FENCE_GATE.get());
    }

    private void doors() {
        this.tag(BlockTags.WOODEN_DOORS)
                .add(VerdanceBlocks.MULBERRY_DOOR.get());
    }

    private void trapdoors() {
        this.tag(BlockTags.WOODEN_TRAPDOORS)
                .add(VerdanceBlocks.MULBERRY_TRAPDOOR.get());
    }

    private void pressurePlates() {
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.get());
    }

    private void buttons() {
        this.tag(BlockTags.WOODEN_BUTTONS)
                .add(VerdanceBlocks.MULBERRY_BUTTON.get());
    }

    private void signs() {
        this.tag(BlockTags.STANDING_SIGNS)
                .add(VerdanceBlocks.MULBERRY_SIGN.get());
        this.tag(BlockTags.WALL_SIGNS)
                .add(VerdanceBlocks.MULBERRY_WALL_SIGN.get());
        this.tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(VerdanceBlocks.MULBERRY_HANGING_SIGN.get());
        this.tag(BlockTags.WALL_HANGING_SIGNS)
                .add(VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN.get());
    }

    private void logs() {
        this.tag(VerdanceBlockTags.MULBERRY_LOGS)
                .add(VerdanceBlocks.MULBERRY_LOG.get())
                .add(VerdanceBlocks.MULBERRY_WOOD.get())
                .add(VerdanceBlocks.STRIPPED_MULBERRY_LOG.get())
                .add(VerdanceBlocks.STRIPPED_MULBERRY_WOOD.get());
        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(VerdanceBlockTags.MULBERRY_LOGS);
        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(VerdanceBlocks.MULBERRY_LOG.get());
    }

    private void saplings() {
        this.tag(BlockTags.SAPLINGS)
                .add(VerdanceBlocks.MULBERRY_SAPLING.get());
    }

    private void leaves() {
        this.tag(BlockTags.LEAVES)
                .add(VerdanceBlocks.MULBERRY_LEAVES.get())
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get());
    }

    private void flowerPots() {
        this.tag(BlockTags.FLOWER_POTS)
                .add(VerdanceBlocks.POTTED_MULBERRY_SAPLING.get())
                .add(VerdanceBlocks.POTTED_VIOLET.get())
                .add(VerdanceBlocks.POTTED_SHRUB.get())
                .add(VerdanceBlocks.POTTED_YELLOW_FLOWERING_SHRUB.get())
                .add(VerdanceBlocks.POTTED_PINK_FLOWERING_SHRUB.get());
    }

    private void maintainsFarmland() {
        this.tag(BlockTags.MAINTAINS_FARMLAND)
                .add(VerdanceBlocks.CANTALOUPE_STEM.get())
                .add(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM.get());
    }

    private void silkMothsSpawnableOn() {
        this.tag(VerdanceBlockTags.SILK_MOTHS_SPAWNABLE_ON)
                .add(VerdanceBlocks.MULBERRY_LEAVES.get())
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get())
                .add(Blocks.GRASS_BLOCK);
    }

    private void replaceableBySugarCane() {
        this.tag(VerdanceBlockTags.REPLACEABLE_BY_SUGAR_CANE)
                .addTag(BlockTags.REPLACEABLE)
                .add(Blocks.SUGAR_CANE);
    }

    private void shrubs() {
        this.tag(VerdanceBlockTags.SHRUBS)
                .add(VerdanceBlocks.SHRUB.get())
                .add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get())
                .add(VerdanceBlocks.PINK_FLOWERING_SHRUB.get());
    }

    private void floweringShrubs() {
        this.tag(VerdanceBlockTags.FLOWERING_SHRUBS)
                .add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get())
                .add(VerdanceBlocks.PINK_FLOWERING_SHRUB.get());
    }

    private void shrubMayPlaceOn() {
        this.tag(VerdanceBlockTags.SHRUB_MAY_PLACE_ON)
                .addTag(BlockTags.DIRT)
                .addTag(BlockTags.SAND)
                .addTag(BlockTags.TERRACOTTA);
    }

    private void flowers() {
        this.tag(BlockTags.FLOWERS).addTag(VerdanceBlockTags.FLOWERING_SHRUBS);
    }

    private void smallFlowers() {
        this.tag(BlockTags.SMALL_FLOWERS).add(VerdanceBlocks.VIOLET.get());
    }

    private void crops() {
        this.tag(BlockTags.CROPS).add(VerdanceBlocks.CANTALOUPE_STEM.get());
    }
}
