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
                .forceAddTag(ItemTags.FLOWERS);
    }

    private void silkwormFood() {
        this.tag(VerdanceItemTags.SILKWORM_FOOD)
                .add(VerdanceItems.MULBERRY)
                .forceAddTag(ItemTags.LEAVES);
    }

    private void planks() {
        this.tag(ItemTags.PLANKS).add(VerdanceBlocks.MULBERRY_PLANKS.asItem()).setReplace(false);
    }

    private void stairs() {
        this.tag(ItemTags.STAIRS).add(VerdanceBlocks.WHITE_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.GRAY_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.BLACK_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.BROWN_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.RED_STUCCO_STAIRS.asItem()).add(VerdanceBlocks.ORANGE_STUCCO_STAIRS.asItem()).setReplace(false);
        this.tag(ItemTags.WOODEN_STAIRS).add(VerdanceBlocks.MULBERRY_STAIRS.asItem()).setReplace(false);
    }

    private void slabs() {
        this.tag(ItemTags.SLABS).add(VerdanceBlocks.WHITE_STUCCO_SLAB.asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB.asItem()).add(VerdanceBlocks.GRAY_STUCCO_SLAB.asItem()).add(VerdanceBlocks.BLACK_STUCCO_SLAB.asItem()).add(VerdanceBlocks.BROWN_STUCCO_SLAB.asItem()).add(VerdanceBlocks.RED_STUCCO_SLAB.asItem()).add(VerdanceBlocks.ORANGE_STUCCO_SLAB.asItem()).setReplace(false);
        this.tag(ItemTags.WOODEN_SLABS).add(VerdanceBlocks.MULBERRY_SLAB.asItem()).setReplace(false);
    }

    private void walls() {
        this.tag(ItemTags.WALLS).add(VerdanceBlocks.WHITE_STUCCO_WALL.asItem()).add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL.asItem()).add(VerdanceBlocks.GRAY_STUCCO_WALL.asItem()).add(VerdanceBlocks.BLACK_STUCCO_WALL.asItem()).add(VerdanceBlocks.BROWN_STUCCO_WALL.asItem()).add(VerdanceBlocks.RED_STUCCO_WALL.asItem()).add(VerdanceBlocks.ORANGE_STUCCO_WALL.asItem()).setReplace(false);
    }

    private void fences() {
        this.tag(ItemTags.WOODEN_FENCES).add(VerdanceBlocks.MULBERRY_FENCE.asItem()).setReplace(false);
    }

    private void fenceGates() {
        this.tag(ItemTags.FENCE_GATES).add(VerdanceBlocks.MULBERRY_FENCE_GATE.asItem()).setReplace(false);
    }

    private void doors() {
        this.tag(ItemTags.WOODEN_DOORS).add(VerdanceBlocks.MULBERRY_DOOR.asItem()).setReplace(false);
    }

    private void trapdoors() {
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(VerdanceBlocks.MULBERRY_TRAPDOOR.asItem()).setReplace(false);
    }

    private void pressurePlates() {
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(VerdanceBlocks.MULBERRY_PRESSURE_PLATE.asItem()).setReplace(false);
    }

    private void buttons() {
        this.tag(ItemTags.WOODEN_BUTTONS).add(VerdanceBlocks.MULBERRY_BUTTON.asItem()).setReplace(false);
    }

    private void signs() {
        this.tag(ItemTags.SIGNS).add(VerdanceItems.MULBERRY_SIGN).setReplace(false);
        this.tag(ItemTags.HANGING_SIGNS).add(VerdanceItems.MULBERRY_HANGING_SIGN).setReplace(false);
    }

    private void logs() {
        this.tag(ItemTags.LOGS_THAT_BURN).forceAddTag(VerdanceItemTags.MULBERRY_LOGS).setReplace(false);
        this.tag(VerdanceItemTags.MULBERRY_LOGS).add(VerdanceBlocks.MULBERRY_LOG.asItem()).add(VerdanceBlocks.MULBERRY_WOOD.asItem()).add(VerdanceBlocks.STRIPPED_MULBERRY_LOG.asItem()).add(VerdanceBlocks.STRIPPED_MULBERRY_WOOD.asItem()).setReplace(false);
    }

    private void boats() {
        this.tag(ItemTags.BOATS).add(VerdanceItems.MULBERRY_BOAT).setReplace(false);
        this.tag(ItemTags.CHEST_BOATS).add(VerdanceItems.MULBERRY_CHEST_BOAT).setReplace(false);
    }

    private void foods() {
        this.tag(ConventionalItemTags.FOODS).add(VerdanceItems.GRILLED_CANTALOUPE_SLICE).setReplace(false);
        this.tag(ConventionalItemTags.FRUIT_FOODS).add(VerdanceItems.CANTALOUPE_SLICE).setReplace(false);
        this.tag(ConventionalItemTags.BERRY_FOODS).add(VerdanceItems.MULBERRY).setReplace(false);
        this.tag(ItemTags.CHICKEN_FOOD).add(VerdanceItems.CANTALOUPE_SEEDS).setReplace(false);
        this.tag(ItemTags.PARROT_FOOD).add(VerdanceItems.CANTALOUPE_SEEDS).setReplace(false);
    }

    private void decoratedPotSherds() {
        this.tag(ItemTags.DECORATED_POT_SHERDS)
                .add(VerdanceItems.ABODE_POTTERY_SHERD)
                .add(VerdanceItems.FRILLS_POTTERY_SHERD)
                .add(VerdanceItems.PITCH_POTTERY_SHERD)
                .add(VerdanceItems.PRICKLE_POTTERY_SHERD)
                .add(VerdanceItems.SPIRIT_POTTERY_SHERD)
                .add(VerdanceItems.TRAP_POTTERY_SHERD)
                .setReplace(false);
    }

    private void trimTemplates() {
        this.tag(ItemTags.TRIM_TEMPLATES).add(VerdanceItems.COMMUNITY_ARMOR_TRIM_SMITHING_TEMPLATE).setReplace(false);
    }

    private void saplings() {
        this.tag(ItemTags.SAPLINGS).add(VerdanceItems.MULBERRY).setReplace(false);
    }

    private void leaves() {
        this.tag(ItemTags.LEAVES).add(VerdanceBlocks.MULBERRY_LEAVES.asItem()).add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES.asItem()).setReplace(false);
    }

    private void flowers() {
        this.tag(ItemTags.FLOWERS)
                .add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB.asItem())
                .add(VerdanceBlocks.PINK_FLOWERING_SHRUB.asItem())
                .setReplace(false);
    }

    private void smallFlowers() {
        this.tag(ItemTags.SMALL_FLOWERS).add(VerdanceBlocks.VIOLET.asItem()).setReplace(false);;
    }
}
