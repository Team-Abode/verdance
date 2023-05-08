package com.teamabode.verdance.core.data.server.tag;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.misc.VerdanceBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class VerdanceBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public VerdanceBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void addTags(HolderLookup.Provider arg) {
        shrubPlaceables();
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

        mineablePickaxe();
        mineableAxe();
    }

    private void shrubPlaceables() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.SHRUB_MAY_PLACE_ON)
                .forceAddTag(BlockTags.DIRT)
                .forceAddTag(BlockTags.SAND);
    }

    private void mineablePickaxe() {
        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(VerdanceBlocks.WHITE_STUCCO)
                .add(VerdanceBlocks.WHITE_STUCCO_STAIRS)
                .add(VerdanceBlocks.WHITE_STUCCO_SLAB)
                .add(VerdanceBlocks.WHITE_STUCCO_WALL)
                .setReplace(false);
    }

    private void mineableAxe() {
        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                .add(VerdanceBlocks.CANTALOUPE)
                .setReplace(false);
    }

    private void walls() {
        this.getOrCreateTagBuilder(BlockTags.WALLS)
                .add(VerdanceBlocks.WHITE_STUCCO_WALL)
                .setReplace(false);
    }

    private void planks() {
        this.getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(VerdanceBlocks.MULBERRY_PLANKS)
                .setReplace(false);
    }

    private void stairs() {
        this.getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(VerdanceBlocks.WHITE_STUCCO_STAIRS)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(VerdanceBlocks.MULBERRY_STAIRS)
                .setReplace(false);
    }

    private void slabs() {
        this.getOrCreateTagBuilder(BlockTags.SLABS)
                .add(VerdanceBlocks.WHITE_STUCCO_SLAB)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(VerdanceBlocks.MULBERRY_SLAB)
                .setReplace(false);
    }

    private void fences() {
        this.getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(VerdanceBlocks.MULBERRY_FENCE)
                .setReplace(false);
    }

    private void fenceGates() {
        this.getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(VerdanceBlocks.MULBERRY_FENCE_GATE)
                .setReplace(false);
    }

    private void doors() {
        this.getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(VerdanceBlocks.MULBERRY_DOOR)
                .setReplace(false);
    }

    private void trapdoors() {
        this.getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(VerdanceBlocks.MULBERRY_TRAPDOOR)
                .setReplace(false);
    }

    private void pressurePlates() {
        this.getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(VerdanceBlocks.MULBERRY_PRESSURE_PLATE)
                .setReplace(false);
    }

    private void buttons() {
        this.getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(VerdanceBlocks.MULBERRY_BUTTON)
                .setReplace(false);
    }
}
