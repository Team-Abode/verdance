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
}
