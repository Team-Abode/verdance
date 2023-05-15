package com.teamabode.verdance.core.data.server.tag;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import com.teamabode.verdance.core.registry.misc.VerdanceItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class VerdanceItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public VerdanceItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    protected void addTags(HolderLookup.Provider arg) {
        planks();
        stairs();
        slabs();
        walls();
        fences();
        fenceGates();
        doors();
        trapdoors();
        pressurePlates();
        buttons();
        signs();
        logs();
        boats();
        foods();
        saplings();
        leaves();

        this.getOrCreateTagBuilder(ItemTags.MUSIC_DISCS).add(VerdanceItems.MUSIC_DISC_RANGE).setReplace(false);
    }

    private void planks() {
        this.getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(VerdanceBlocks.MULBERRY_PLANKS.asItem())
                .setReplace(false);
    }

    private void stairs() {
        this.getOrCreateTagBuilder(ItemTags.STAIRS)
                .add(VerdanceBlocks.WHITE_STUCCO_STAIRS.asItem())
                .setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(VerdanceBlocks.MULBERRY_STAIRS.asItem())
                .setReplace(false);
    }

    private void slabs() {
        this.getOrCreateTagBuilder(ItemTags.SLABS)
                .add(VerdanceBlocks.WHITE_STUCCO_SLAB.asItem())
                .setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(VerdanceBlocks.MULBERRY_SLAB.asItem())
                .setReplace(false);
    }

    private void walls() {
        this.getOrCreateTagBuilder(ItemTags.WALLS)
                .add(VerdanceBlocks.WHITE_STUCCO_WALL.asItem())
                .setReplace(false);
    }

    private void fences() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(VerdanceBlocks.MULBERRY_FENCE.asItem())
                .setReplace(false);
    }

    private void fenceGates() {
        this.getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(VerdanceBlocks.MULBERRY_FENCE_GATE.asItem())
                .setReplace(false);
    }

    private void doors() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(VerdanceBlocks.MULBERRY_DOOR.asItem())
                .setReplace(false);
    }

    private void trapdoors() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(VerdanceBlocks.MULBERRY_TRAPDOOR.asItem())
                .setReplace(false);
    }

    private void pressurePlates() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.asItem())
                .setReplace(false);
    }

    private void buttons() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(VerdanceBlocks.MULBERRY_BUTTON.asItem())
                .setReplace(false);
    }

    private void signs() {
        this.getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(VerdanceItems.MULBERRY_SIGN)
                .setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(VerdanceItems.MULBERRY_HANGING_SIGN)
                .setReplace(false);
    }

    private void logs() {
        this.getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .forceAddTag(VerdanceItemTags.MULBERRY_LOGS)
                .setReplace(false);
        this.getOrCreateTagBuilder(VerdanceItemTags.MULBERRY_LOGS)
                .add(VerdanceBlocks.MULBERRY_LOG.asItem())
                .add(VerdanceBlocks.MULBERRY_WOOD.asItem())
                .add(VerdanceBlocks.STRIPPED_MULBERRY_LOG.asItem())
                .add(VerdanceBlocks.STRIPPED_MULBERRY_WOOD.asItem())
                .setReplace(false);
    }

    private void boats() {
        this.getOrCreateTagBuilder(ItemTags.BOATS)
                .add(VerdanceItems.MULBERRY_BOAT)
                .setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(VerdanceItems.MULBERRY_CHEST_BOAT)
                .setReplace(false);
    }

    private void foods() {
        this.getOrCreateTagBuilder(ConventionalItemTags.FOODS)
                .add(VerdanceItems.CANTALOUPE_SLICE)
                .add(VerdanceItems.MULBERRY)
                .setReplace(false);
    }

    private void saplings() {
        this.getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(VerdanceItems.MULBERRY)
                .setReplace(false);
    }

    private void leaves() {
        this.getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(VerdanceBlocks.MULBERRY_LEAVES.asItem())
                .setReplace(false);
    }
}
