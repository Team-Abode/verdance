package com.teamabode.verdance.core.data.server.tag;

import com.teamabode.verdance.core.registry.VerdanceBlocks;
import com.teamabode.verdance.core.misc.tag.VerdanceBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;

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
        signs();
        logs();
        saplings();
        leaves();
        flowerPots();

        mineablePickaxe();
        mineableHoe();
        mineableAxe();

        ghostTownReplaceable();
        silkMothsSpawnableOn();
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
                .setReplace(false);
    }

    private void mineableHoe() {
        this.getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(VerdanceBlocks.MULBERRY_LEAVES)
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES)
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
                .add(VerdanceBlocks.LIGHT_GRAY_STUCCO_WALL)
                .add(VerdanceBlocks.GRAY_STUCCO_WALL)
                .add(VerdanceBlocks.BLACK_STUCCO_WALL)
                .add(VerdanceBlocks.BROWN_STUCCO_WALL)
                .add(VerdanceBlocks.RED_STUCCO_WALL)
                .add(VerdanceBlocks.ORANGE_STUCCO_WALL)
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
        this.getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .forceAddTag(VerdanceBlockTags.MULBERRY_LOGS)
                .setReplace(false);
        this.getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(VerdanceBlocks.MULBERRY_LOG)
                .setReplace(false);
        this.getOrCreateTagBuilder(VerdanceBlockTags.MULBERRY_LOGS)
                .add(VerdanceBlocks.MULBERRY_LOG)
                .add(VerdanceBlocks.MULBERRY_WOOD)
                .add(VerdanceBlocks.STRIPPED_MULBERRY_LOG)
                .add(VerdanceBlocks.STRIPPED_MULBERRY_WOOD)
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
                .setReplace(false);
    }

    private void ghostTownReplaceable() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.GHOST_TOWN_REPLACEABLE).setReplace(false)
                .add(Blocks.SAND);
    }

    private void silkMothsSpawnableOn() {
        this.getOrCreateTagBuilder(VerdanceBlockTags.SILK_MOTHS_SPAWNABLE_ON).setReplace(false)
                .add(VerdanceBlocks.MULBERRY_LEAVES)
                .add(VerdanceBlocks.FLOWERING_MULBERRY_LEAVES)
                .add(Blocks.GRASS_BLOCK);
    }
}
