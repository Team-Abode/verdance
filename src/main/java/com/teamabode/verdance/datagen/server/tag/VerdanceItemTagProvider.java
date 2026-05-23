package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.core.tag.VerdanceItemTags;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.registry.VerdanceItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class VerdanceItemTagProvider extends ItemTagsProvider {
    
    public VerdanceItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    protected void addTags(HolderLookup.Provider arg) {
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
        decoratedPotSherds();
        trimTemplates();
        saplings();
        leaves();
        flowers();
        smallFlowers();
    }

    private void silkMothFood() {
        this.tag(VerdanceItemTags.SILK_MOTH_FOOD)
                .addTag(ItemTags.FLOWERS);
    }

    private void silkwormFood() {
        this.tag(VerdanceItemTags.SILKWORM_FOOD)
                .add(VerdanceItems.MULBERRY.get())
                .addTag(ItemTags.LEAVES);
    }

    private void planks() {
        this.tag(ItemTags.PLANKS).add(VerdanceBlocks.MULBERRY_PLANKS.get().asItem());
    }

    private void stairs() {
        this.tag(ItemTags.STAIRS).add(VerdanceBlocks.WHITE_STUCCO_STAIRS.get().asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS.get().asItem()).add(VerdanceBlocks.GRAY_STUCCO_STAIRS.get().asItem()).add(VerdanceBlocks.BLACK_STUCCO_STAIRS.get().asItem()).add(VerdanceBlocks.BROWN_STUCCO_STAIRS.get().asItem()).add(VerdanceBlocks.RED_STUCCO_STAIRS.get().asItem()).add(VerdanceBlocks.ORANGE_STUCCO_STAIRS.get().asItem());
        this.tag(ItemTags.WOODEN_STAIRS).add(VerdanceBlocks.MULBERRY_STAIRS.get().asItem());
    }

    private void slabs() {
        this.tag(ItemTags.SLABS).add(VerdanceBlocks.WHITE_STUCCO_SLAB.get().asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB.get().asItem()).add(VerdanceBlocks.GRAY_STUCCO_SLAB.get().asItem()).add(VerdanceBlocks.BLACK_STUCCO_SLAB.get().asItem()).add(VerdanceBlocks.BROWN_STUCCO_SLAB.get().asItem()).add(VerdanceBlocks.RED_STUCCO_SLAB.get().asItem()).add(VerdanceBlocks.ORANGE_STUCCO_SLAB.get().asItem());
        this.tag(ItemTags.WOODEN_SLABS).add(VerdanceBlocks.MULBERRY_SLAB.get().asItem());
    }

    private void walls() {
        this.tag(ItemTags.WALLS).add(VerdanceBlocks.WHITE_STUCCO_WALL.get().asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL.get().asItem()).add(VerdanceBlocks.GRAY_STUCCO_WALL.get().asItem()).add(VerdanceBlocks.BLACK_STUCCO_WALL.get().asItem()).add(VerdanceBlocks.BROWN_STUCCO_WALL.get().asItem()).add(VerdanceBlocks.RED_STUCCO_WALL.get().asItem()).add(VerdanceBlocks.ORANGE_STUCCO_WALL.get().asItem());
    }

    private void fences() {
        this.tag(ItemTags.WOODEN_FENCES).add(VerdanceBlocks.MULBERRY_FENCE.get().asItem());
    }

    private void fenceGates() {
        this.tag(ItemTags.FENCE_GATES).add(VerdanceBlocks.MULBERRY_FENCE_GATE.get().asItem());
    }

    private void doors() {
        this.tag(ItemTags.WOODEN_DOORS).add(VerdanceBlocks.MULBERRY_DOOR.get().asItem());
    }

    private void trapdoors() {
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(VerdanceBlocks.MULBERRY_TRAPDOOR.get().asItem());
    }

    private void pressurePlates() {
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.get().asItem());
    }

    private void buttons() {
        this.tag(ItemTags.WOODEN_BUTTONS).add(VerdanceBlocks.MULBERRY_BUTTON.get().asItem());
    }

    private void signs() {
        this.tag(ItemTags.SIGNS).add(VerdanceItems.MULBERRY_SIGN.get());
        this.tag(ItemTags.HANGING_SIGNS).add(VerdanceItems.MULBERRY_HANGING_SIGN.get());
    }

    private void logs() {
        this.tag(ItemTags.LOGS_THAT_BURN).addTag(VerdanceItemTags.MULBERRY_LOGS);
        this.tag(VerdanceItemTags.MULBERRY_LOGS).add(VerdanceBlocks.MULBERRY_LOG.get().asItem()).add(VerdanceBlocks.MULBERRY_WOOD.get().asItem()).add(VerdanceBlocks.STRIPPED_MULBERRY_LOG.get().asItem()).add(VerdanceBlocks.STRIPPED_MULBERRY_WOOD.get().asItem());
    }

    private void boats() {
        this.tag(ItemTags.BOATS).add(VerdanceItems.MULBERRY_BOAT.get());
        this.tag(ItemTags.CHEST_BOATS).add(VerdanceItems.MULBERRY_CHEST_BOAT.get());
    }

    private void foods() {
        this.tag(Tags.Items.FOODS).add(VerdanceItems.GRILLED_CANTALOUPE_SLICE.get());
        this.tag(Tags.Items.FOODS_FRUIT).add(VerdanceItems.CANTALOUPE_SLICE.get());
        this.tag(Tags.Items.FOODS_BERRY).add(VerdanceItems.MULBERRY.get());
        this.tag(ItemTags.CHICKEN_FOOD).add(VerdanceItems.CANTALOUPE_SEEDS.get());
        this.tag(ItemTags.PARROT_FOOD).add(VerdanceItems.CANTALOUPE_SEEDS.get());
    }

    private void decoratedPotSherds() {
        this.tag(ItemTags.DECORATED_POT_SHERDS)
                .add(VerdanceItems.ABODE_POTTERY_SHERD.get())
                .add(VerdanceItems.FRILLS_POTTERY_SHERD.get())
                .add(VerdanceItems.PITCH_POTTERY_SHERD.get())
                .add(VerdanceItems.PRICKLE_POTTERY_SHERD.get())
                .add(VerdanceItems.SPIRIT_POTTERY_SHERD.get())
                .add(VerdanceItems.TRAP_POTTERY_SHERD.get());
    }

    private void trimTemplates() {
        this.tag(ItemTags.TRIM_TEMPLATES).add(VerdanceItems.HERITAGE_ARMOR_TRIM_SMITHING_TEMPLATE.get());
    }

    private void saplings() {
        this.tag(ItemTags.SAPLINGS).add(VerdanceItems.MULBERRY.get());
    }

    private void leaves() {
        this.tag(ItemTags.LEAVES).add(VerdanceBlocks.MULBERRY_LEAVES.get().asItem()).add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.get().asItem());
    }

    private void flowers() {
        this.tag(ItemTags.FLOWERS)
                .add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.get().asItem())
                .add(VerdanceBlocks.PINK_FLOWERING_SHRUB.get().asItem());
    }

    private void smallFlowers() {
        this.tag(ItemTags.SMALL_FLOWERS).add(VerdanceBlocks.VIOLET.get().asItem());
    }
}
