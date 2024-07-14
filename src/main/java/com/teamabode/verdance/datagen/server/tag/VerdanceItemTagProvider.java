package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.core.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class VerdanceItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public VerdanceItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    protected void addTags(HolderLookup.Provider arg) {
        // TODO add conventional item tags for dyed stucco (blocks & items)

        silkMothFood();
        silkwormFood();
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
    }

    private void silkMothFood() {
        this.getOrCreateTagBuilder(VerdanceItemTags.SILK_MOTH_FOOD)
                .forceAddTag(ItemTags.FLOWERS);
    }

    private void silkwormFood() {
        this.getOrCreateTagBuilder(VerdanceItemTags.SILKWORM_FOOD)
                .add(VerdanceItems.MULBERRY)
                .forceAddTag(ItemTags.LEAVES)
                .setReplace(false);
    }

    private void planks() {
        this.getOrCreateTagBuilder(ItemTags.PLANKS).add(VerdanceBlocks.MULBERRY_PLANKS.asItem()).setReplace(false);
    }

    private void stairs() {
        this.getOrCreateTagBuilder(ItemTags.STAIRS).add(VerdanceBlocks.WHITE_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.GRAY_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.BLACK_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.BROWN_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.RED_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.ORANGE_STUCCO_STAIRS.asItem()).setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(VerdanceBlocks.MULBERRY_STAIRS.asItem()).setReplace(false);
    }

    private void slabs() {
        this.getOrCreateTagBuilder(ItemTags.SLABS).add(VerdanceBlocks.WHITE_STUCCO_SLAB.asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB.asItem()).add(VerdanceBlocks.GRAY_STUCCO_SLAB.asItem()).add(VerdanceBlocks.BLACK_STUCCO_SLAB.asItem()).add(VerdanceBlocks.BROWN_STUCCO_SLAB.asItem()).add(VerdanceBlocks.RED_STUCCO_SLAB.asItem()).add(VerdanceBlocks.ORANGE_STUCCO_SLAB.asItem()).setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(VerdanceBlocks.MULBERRY_SLAB.asItem()).setReplace(false);
    }

    private void walls() {
        this.getOrCreateTagBuilder(ItemTags.WALLS).add(VerdanceBlocks.WHITE_STUCCO_WALL.asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL.asItem()).add(VerdanceBlocks.GRAY_STUCCO_WALL.asItem()).add(VerdanceBlocks.BLACK_STUCCO_WALL.asItem()).add(VerdanceBlocks.BROWN_STUCCO_WALL.asItem()).add(VerdanceBlocks.RED_STUCCO_WALL.asItem()).add(VerdanceBlocks.ORANGE_STUCCO_WALL.asItem()).setReplace(false);
    }

    private void fences() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(VerdanceBlocks.MULBERRY_FENCE.asItem()).setReplace(false);
    }

    private void fenceGates() {
        this.getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(VerdanceBlocks.MULBERRY_FENCE_GATE.asItem()).setReplace(false);
    }

    private void doors() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(VerdanceBlocks.MULBERRY_DOOR.asItem()).setReplace(false);
    }

    private void trapdoors() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(VerdanceBlocks.MULBERRY_TRAPDOOR.asItem()).setReplace(false);
    }

    private void pressurePlates() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.asItem()).setReplace(false);
    }

    private void buttons() {
        this.getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(VerdanceBlocks.MULBERRY_BUTTON.asItem()).setReplace(false);
    }

    private void signs() {
        this.getOrCreateTagBuilder(ItemTags.SIGNS).add(VerdanceItems.MULBERRY_SIGN).setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(VerdanceItems.MULBERRY_HANGING_SIGN).setReplace(false);
    }

    private void logs() {
        this.getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).forceAddTag(VerdanceItemTags.MULBERRY_LOGS).setReplace(false);
        this.getOrCreateTagBuilder(VerdanceItemTags.MULBERRY_LOGS).add(VerdanceBlocks.MULBERRY_LOG.asItem()).add(VerdanceBlocks.MULBERRY_WOOD.asItem()).add(VerdanceBlocks.STRIPPED_MULBERRY_LOG.asItem()).add(VerdanceBlocks.STRIPPED_MULBERRY_WOOD.asItem()).setReplace(false);
    }

    private void boats() {
        this.getOrCreateTagBuilder(ItemTags.BOATS).add(VerdanceItems.MULBERRY_BOAT).setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(VerdanceItems.MULBERRY_CHEST_BOAT).setReplace(false);
    }

    private void foods() {
        this.getOrCreateTagBuilder(ConventionalItemTags.FOODS).add(VerdanceItems.GRILLED_CANTALOUPE_SLICE).setReplace(false);
        this.getOrCreateTagBuilder(ConventionalItemTags.FRUIT_FOODS).add(VerdanceItems.CANTALOUPE_SLICE).setReplace(false);
        this.getOrCreateTagBuilder(ConventionalItemTags.BERRY_FOODS).add(VerdanceItems.MULBERRY).setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(VerdanceItems.CANTALOUPE_SEEDS).setReplace(false);
        this.getOrCreateTagBuilder(ItemTags.PARROT_FOOD).add(VerdanceItems.CANTALOUPE_SEEDS).setReplace(false);
    }

    private void saplings() {
        this.getOrCreateTagBuilder(ItemTags.SAPLINGS).add(VerdanceItems.MULBERRY).setReplace(false);
    }

    private void leaves() {
        this.getOrCreateTagBuilder(ItemTags.LEAVES).add(VerdanceBlocks.MULBERRY_LEAVES.asItem()).add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.asItem()).setReplace(false);
    }
}
