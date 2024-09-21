package com.teamabode.verdance.datagen.server.tag;

import com.teamabode.verdance.core.tag.VerdanceBlockTags;
import com.teamabode.verdance.core.registry.VerdanceBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import java.util.concurrent.CompletableFuture;

public class VerdanceBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public VerdanceBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup arg) {
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
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(VerdanceBlocks.WHITE_STUCCO)
                .add(VerdanceBlocks.WHITE_STUCCO_STAIRS)
                .add(VerdanceBlocks.WHITE_STUCCO_SLAB)
                .add(VerdanceBlocks.WHITE_STUCCO_WALL)
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO)
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS)
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB)
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL)
                .add(VerdanceBlocks.GRAY_STUCCO)
                .add(VerdanceBlocks.GRAY_STUCCO_STAIRS)
                .add(VerdanceBlocks.GRAY_STUCCO_SLAB)
                .add(VerdanceBlocks.GRAY_STUCCO_WALL)
                .add(VerdanceBlocks.BLACK_STUCCO)
                .add(VerdanceBlocks.BLACK_STUCCO_STAIRS)
                .add(VerdanceBlocks.BLACK_STUCCO_SLAB)
                .add(VerdanceBlocks.BLACK_STUCCO_WALL)
                .add(VerdanceBlocks.BROWN_STUCCO)
                .add(VerdanceBlocks.BROWN_STUCCO_STAIRS)
                .add(VerdanceBlocks.BROWN_STUCCO_SLAB)
                .add(VerdanceBlocks.BROWN_STUCCO_WALL)
                .add(VerdanceBlocks.RED_STUCCO)
                .add(VerdanceBlocks.RED_STUCCO_STAIRS)
                .add(VerdanceBlocks.RED_STUCCO_SLAB)
                .add(VerdanceBlocks.RED_STUCCO_WALL)
                .add(VerdanceBlocks.ORANGE_STUCCO)
                .add(VerdanceBlocks.ORANGE_STUCCO_STAIRS)
                .add(VerdanceBlocks.ORANGE_STUCCO_SLAB)
                .add(VerdanceBlocks.ORANGE_STUCCO_WALL)
                .add(VerdanceBlocks.YELLOW_STUCCO)
                .add(VerdanceBlocks.YELLOW_STUCCO_STAIRS)
                .add(VerdanceBlocks.YELLOW_STUCCO_SLAB)
                .add(VerdanceBlocks.YELLOW_STUCCO_WALL)
                .add(VerdanceBlocks.LIME_STUCCO)
                .add(VerdanceBlocks.LIME_STUCCO_STAIRS)
                .add(VerdanceBlocks.LIME_STUCCO_SLAB)
                .add(VerdanceBlocks.LIME_STUCCO_WALL)
                .add(VerdanceBlocks.GREEN_STUCCO)
                .add(VerdanceBlocks.GREEN_STUCCO_STAIRS)
                .add(VerdanceBlocks.GREEN_STUCCO_SLAB)
                .add(VerdanceBlocks.GREEN_STUCCO_WALL)
                .add(VerdanceBlocks.CYAN_STUCCO)
                .add(VerdanceBlocks.CYAN_STUCCO_STAIRS)
                .add(VerdanceBlocks.CYAN_STUCCO_SLAB)
                .add(VerdanceBlocks.CYAN_STUCCO_WALL)
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO)
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS)
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB)
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL)
                .add(VerdanceBlocks.BLUE_STUCCO)
                .add(VerdanceBlocks.BLUE_STUCCO_STAIRS)
                .add(VerdanceBlocks.BLUE_STUCCO_SLAB)
                .add(VerdanceBlocks.BLUE_STUCCO_WALL)
                .add(VerdanceBlocks.PURPLE_STUCCO)
                .add(VerdanceBlocks.PURPLE_STUCCO_STAIRS)
                .add(VerdanceBlocks.PURPLE_STUCCO_SLAB)
                .add(VerdanceBlocks.PURPLE_STUCCO_WALL)
                .add(VerdanceBlocks.MAGENTA_STUCCO)
                .add(VerdanceBlocks.MAGENTA_STUCCO_STAIRS)
                .add(VerdanceBlocks.MAGENTA_STUCCO_SLAB)
                .add(VerdanceBlocks.MAGENTA_STUCCO_WALL)
                .add(VerdanceBlocks.PINK_STUCCO)
                .add(VerdanceBlocks.PINK_STUCCO_STAIRS)
                .add(VerdanceBlocks.PINK_STUCCO_SLAB)
                .add(VerdanceBlocks.PINK_STUCCO_WALL)
                .setReplace(false);
    }

    private void mineableHoe() {
        this.getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(VerdanceBlocks.MULBERRY_LEAVES)
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES)
                .setReplace(false);
    }

    private void mineableAxe() {
        this.getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(VerdanceBlocks.CANTALOUPE)
                .add(VerdanceBlocks.WHITE_CUSHION)
                .add(VerdanceBlocks.LIGHT_GRAY_CUSHION)
                .add(VerdanceBlocks.GRAY_CUSHION)
                .add(VerdanceBlocks.BLACK_CUSHION)
                .add(VerdanceBlocks.BROWN_CUSHION)
                .add(VerdanceBlocks.RED_CUSHION)
                .add(VerdanceBlocks.ORANGE_CUSHION)
                .add(VerdanceBlocks.YELLOW_CUSHION)
                .add(VerdanceBlocks.LIME_CUSHION)
                .add(VerdanceBlocks.GREEN_CUSHION)
                .add(VerdanceBlocks.CYAN_CUSHION)
                .add(VerdanceBlocks.LIGHT_BLUE_CUSHION)
                .add(VerdanceBlocks.BLUE_CUSHION)
                .add(VerdanceBlocks.PURPLE_CUSHION)
                .add(VerdanceBlocks.MAGENTA_CUSHION)
                .add(VerdanceBlocks.PINK_CUSHION)
                .setReplace(false);
    }

    private void swordEfficient() {
        this.getOrCreateTagBuilder(BlockTags.SWORD_EFFICIENT).add(VerdanceBlocks.CANTALOUPE);
    }

    private void walls() {
        this.getOrCreateTagBuilder(BlockTags.WALLS)
                .add(VerdanceBlocks.WHITE_STUCCO_WALL)
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL)
                .add(VerdanceBlocks.GRAY_STUCCO_WALL)
                .add(VerdanceBlocks.BLACK_STUCCO_WALL)
                .add(VerdanceBlocks.BROWN_STUCCO_WALL)
                .add(VerdanceBlocks.RED_STUCCO_WALL)
                .add(VerdanceBlocks.ORANGE_STUCCO_WALL)
                .add(VerdanceBlocks.YELLOW_STUCCO_WALL)
                .add(VerdanceBlocks.LIME_STUCCO_WALL)
                .add(VerdanceBlocks.GREEN_STUCCO_WALL)
                .add(VerdanceBlocks.CYAN_STUCCO_WALL)
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_WALL)
                .add(VerdanceBlocks.BLUE_STUCCO_WALL)
                .add(VerdanceBlocks.PURPLE_STUCCO_WALL)
                .add(VerdanceBlocks.MAGENTA_STUCCO_WALL)
                .add(VerdanceBlocks.PINK_STUCCO_WALL)
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
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_STAIRS)
                .add(VerdanceBlocks.GRAY_STUCCO_STAIRS)
                .add(VerdanceBlocks.BLACK_STUCCO_STAIRS)
                .add(VerdanceBlocks.BROWN_STUCCO_STAIRS)
                .add(VerdanceBlocks.RED_STUCCO_STAIRS)
                .add(VerdanceBlocks.ORANGE_STUCCO_STAIRS)
                .add(VerdanceBlocks.YELLOW_STUCCO_STAIRS)
                .add(VerdanceBlocks.LIME_STUCCO_STAIRS)
                .add(VerdanceBlocks.GREEN_STUCCO_STAIRS)
                .add(VerdanceBlocks.CYAN_STUCCO_STAIRS)
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_STAIRS)
                .add(VerdanceBlocks.BLUE_STUCCO_STAIRS)
                .add(VerdanceBlocks.PURPLE_STUCCO_STAIRS)
                .add(VerdanceBlocks.MAGENTA_STUCCO_STAIRS)
                .add(VerdanceBlocks.PINK_STUCCO_STAIRS)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(VerdanceBlocks.MULBERRY_STAIRS)
                .setReplace(false);
    }

    private void slabs() {
        this.getOrCreateTagBuilder(BlockTags.SLABS)
                .add(VerdanceBlocks.WHITE_STUCCO_SLAB)
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_SLAB)
                .add(VerdanceBlocks.GRAY_STUCCO_SLAB)
                .add(VerdanceBlocks.BLACK_STUCCO_SLAB)
                .add(VerdanceBlocks.BROWN_STUCCO_SLAB)
                .add(VerdanceBlocks.RED_STUCCO_SLAB)
                .add(VerdanceBlocks.ORANGE_STUCCO_SLAB)
                .add(VerdanceBlocks.YELLOW_STUCCO_SLAB)
                .add(VerdanceBlocks.LIME_STUCCO_SLAB)
                .add(VerdanceBlocks.GREEN_STUCCO_SLAB)
                .add(VerdanceBlocks.CYAN_STUCCO_SLAB)
                .add(VerdanceBlocks.LIGHT_BLUE_STUCCO_SLAB)
                .add(VerdanceBlocks.BLUE_STUCCO_SLAB)
                .add(VerdanceBlocks.PURPLE_STUCCO_SLAB)
                .add(VerdanceBlocks.MAGENTA_STUCCO_SLAB)
                .add(VerdanceBlocks.PINK_STUCCO_SLAB)
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

    private void signs() {
        this.getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(VerdanceBlocks.MULBERRY_SIGN)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(VerdanceBlocks.MULBERRY_WALL_SIGN)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(VerdanceBlocks.MULBERRY_HANGING_SIGN)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(VerdanceBlocks.MULBERRY_WALL_HANGING_SIGN)
                .setReplace(false);
    }

    private void logs() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.MULBERRY_LOGS)
                .add(VerdanceBlocks.MULBERRY_LOG)
                .add(VerdanceBlocks.MULBERRY_WOOD)
                .add(VerdanceBlocks.STRIPPED_MULBERRY_LOG)
                .add(VerdanceBlocks.STRIPPED_MULBERRY_WOOD)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .forceAddTag(VerdanceBlockTags.MULBERRY_LOGS)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(VerdanceBlocks.MULBERRY_LOG)
                .setReplace(false);
    }

    private void saplings() {
        this.getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(VerdanceBlocks.MULBERRY_SAPLING)
                .setReplace(false);
    }

    private void leaves() {
        this.getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(VerdanceBlocks.MULBERRY_LEAVES)
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES)
                .setReplace(false);
    }

    private void flowerPots() {
        this.getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(VerdanceBlocks.POTTED_MULBERRY_SAPLING)
                .add(VerdanceBlocks.POTTED_VIOLET)
                .add(VerdanceBlocks.POTTED_SHRUB)
                .add(VerdanceBlocks.POTTED_YELLOW_FLOWERING_SHRUB)
                .add(VerdanceBlocks.POTTED_PINK_FLOWERING_SHRUB)
                .setReplace(false);
    }

    private void maintainsFarmland() {
        this.getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND)
                .add(VerdanceBlocks.CANTALOUPE_STEM)
                .add(VerdanceBlocks.ATTACHED_CANTALOUPE_STEM)
                .setReplace(false);
    }

    private void silkMothsSpawnableOn() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.SILK_MOTHS_SPAWNABLE_ON).setReplace(false)
                .add(VerdanceBlocks.MULBERRY_LEAVES)
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES)
                .add(Blocks.GRASS_BLOCK);
    }

    private void replaceableBySugarCane() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.REPLACEABLE_BY_SUGAR_CANE).setReplace(false)
                .forceAddTag(BlockTags.REPLACEABLE)
                .add(Blocks.SUGAR_CANE);
    }

    private void shrubs() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.SHRUBS).setReplace(false)
                .add(VerdanceBlocks.SHRUB)
                .add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB)
                .add(VerdanceBlocks.PINK_FLOWERING_SHRUB);
    }

    private void floweringShrubs() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.FLOWERING_SHRUBS).setReplace(false)
                .add(VerdanceBlocks.YELLOW_FLOWERING_SHRUB)
                .add(VerdanceBlocks.PINK_FLOWERING_SHRUB);
    }

    private void shrubMayPlaceOn() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.SHRUB_MAY_PLACE_ON).setReplace(false)
                .forceAddTag(BlockTags.DIRT)
                .forceAddTag(BlockTags.SAND)
                .forceAddTag(BlockTags.TERRACOTTA);
    }

    private void flowers() {
        this.getOrCreateTagBuilder(BlockTags.FLOWERS).forceAddTag(VerdanceBlockTags.FLOWERING_SHRUBS);
    }

    private void smallFlowers() {
        this.getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(VerdanceBlocks.VIOLET);
    }

    private void crops() {
        this.getOrCreateTagBuilder(BlockTags.CROPS).add(VerdanceBlocks.CANTALOUPE_STEM);
    }
}
